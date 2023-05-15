/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.dao.OrganizationDaoDB.OrganizationMapper;
import com.sg.superhero.dao.PowerDaoDB.PowerMapper;
import com.sg.superhero.dao.SuperDaoDB.SuperMapper;
import com.sg.superhero.entities.Location;
import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.Sighting;
import com.sg.superhero.entities.Super;
import com.sg.superhero.entities.Power;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Teresa
 */
@Repository
public class SightingDaoDB implements SightingDao {

    @Autowired
    JdbcTemplate jdbc;

    private Power getPowerForSuper(int powerID) {
        try {
            final String GET_POWER = "SELECT p.powerID, p.name, p.description FROM Power p "
                    + "JOIN Super s ON p.powerID = s.powerID WHERE s.superID = ?";
            return this.jdbc.queryForObject(GET_POWER, new PowerMapper(), powerID);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private Super getSuperForSighting(int sightingID) {
        final String GET_SUPER_FOR_SIGHTING = "SELECT s.* FROM Super s "
                + "JOIN Sighting si ON si.superID = s.superID "
                + "WHERE si.sightingID = ?";
        Super superhero = this.jdbc.queryForObject(GET_SUPER_FOR_SIGHTING, new SuperMapper(), sightingID);
        superhero.setPower(this.getPowerForSuper(superhero.getSuperID()));
        superhero.setOrganization(this.getOrganizationsForSuper(superhero.getSuperID()));
        return superhero;
    }

    private Location getLocationForSighting(int sightingID) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM Location l "
                + "JOIN Sighting s ON s.LocationId = l.LocationId WHERE s.SightingId = ?";
        return jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationDaoDB.LocationMapper(), sightingID);
    }

    @Override
    public Sighting getSightingByID(int sightingID) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM Sighting WHERE SightingID = ?";

            Sighting sighting = this.jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingID);
            sighting.setSuperhero(this.getSuperForSighting(sightingID));
            sighting.setLocation(this.getLocationForSighting(sightingID));

            return sighting;

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sighting ORDER BY date DESC";
        List<Sighting> sightings = this.jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());

        this.associateLocationsForSightings(sightings);
        return sightings;
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO sighting(superID, locationID, date, description)" + "VALUES(?,?,?,?)";

        this.jdbc.update(INSERT_SIGHTING,
                sighting.getSuperhero().getSuperID(),
                sighting.getLocation().getLocationID(),
                Timestamp.valueOf(sighting.getDate().atTime(12, 0)),
                sighting.getDescription());

        int newSightingID = this.jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingID(newSightingID);

        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE Sighting SET superID = ?, locationID = ?, date = ?, description = ? "
                + "WHERE sightingID = ?";

        this.jdbc.update(UPDATE_SIGHTING,
                sighting.getSuperhero().getSuperID(),
                sighting.getLocation().getLocationID(),
                Timestamp.valueOf(sighting.getDate().atTime(12, 0)),
                sighting.getDescription(),
                sighting.getSightingID());
    }

    @Override
    @Transactional
    public void deleteSightingByID(int sightingID) {
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE SightingID = ?";
        this.jdbc.update(DELETE_SIGHTING, sightingID);

    }

    @Override
    public List<Sighting> getSightingsForLocation(Location location) {
        final String SELECT_SIGHTINGS_FOR_LOCATION = "SELECT * FROM Sighting WHERE LocationID = ?";
        List<Sighting> sighting = this.jdbc.query(SELECT_SIGHTINGS_FOR_LOCATION, new SightingMapper(), location.getLocationID());
        this.associateLocationsForSightings(sighting);
        return sighting;
    }

    private void associateLocationsForSightings(List<Sighting> sightings) {
        for (Sighting sighting : sightings) {
            sighting.setLocation(this.getLocationForSighting(sighting.getSightingID()));
            sighting.setSuperhero(this.getSuperForSighting(sighting.getSightingID()));
        }
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        final String SELECT_SIGHTINGS_BY_DATE = "SELECT * FROM Sighting WHERE Date = ?";
        List<Sighting> sightings = this.jdbc.query(SELECT_SIGHTINGS_BY_DATE, new SightingMapper(), date);

        this.associateLocationsForSightings(sightings);
        return sightings;
    }
    
    private List<Organization> getOrganizationsForSuper(int organizationID) {
        final String SELECT_ORG_FOR_SUPER = "SELECT o.organizationID, o.name, o.description, o.address, o.contactInfo, o.type "
                + "FROM SuperOrganization so "
                + "JOIN Organization o ON so.organizationID = o.organizationID "
                + "WHERE so.superID = ?";
        return this.jdbc.query(SELECT_ORG_FOR_SUPER, new OrganizationMapper(), organizationID);
    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("sightingID"));
            sighting.setDate(rs.getDate("date").toLocalDate());
            sighting.setDescription(rs.getString("description"));

            return sighting;
        }
    }
}
