/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.dao.SightingDaoDB.SightingMapper;
import com.sg.superhero.entities.Location;
import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.Sighting;
import com.sg.superhero.entities.Super;
import com.sg.superhero.entities.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class SuperDaoDB implements SuperDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Super getSuperByID(int superID) {
        // create private method to return power for hero, pass hero ids, write queries to get the powers
        try {
            final String GET_SUPER_BY_ID = "SELECT * FROM super WHERE superID = ?";
            return this.jdbc.queryForObject(GET_SUPER_BY_ID, new SuperMapper(), superID);

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Super> getAllSupers() {
        final String GET_ALL_SUPERS = "SELECT * FROM super";
        return this.jdbc.query(GET_ALL_SUPERS, new SuperMapper());
    }

    @Override
    @Transactional
    public Super addSuper(Super superhero) {
        final String INSERT_SUPER = "INSERT INTO super(type, name, description) "
                + "VALUES(?,?,?)";
        this.jdbc.update(INSERT_SUPER,
                superhero.getType(),
                superhero.getName(),
                superhero.getDescrption());

        int newSuperID = this.jdbc.queryForObject("SELECT LAST_INSERT_SUPERID()", Integer.class);
        superhero.setSuperID(newSuperID);

        return superhero;
    }

    @Override
    @Transactional
    public void updateSuper(Super superhero) {
        final String UPDATE_SUPER = "UPDATE super SET type = ?"
                + "name = ?, " + "description = ? WHERE superID = ?";

        this.jdbc.update(UPDATE_SUPER,
                superhero.getSuperID(),
                superhero.getSuperpower().getSuperpowerID(),
                superhero.getType(),
                superhero.getName(),
                superhero.getDescrption());
    }

    private void insertSighting(Super superhero) {
        final String INSERT_SIGHTING = "INSERT INTO sighting(sightingID, superID, locationID, date, description) "
                + "VALUES(?,?,?,?,?)";

//        for(Sighting sighting : superhero.get)
    }

    @Override
    @Transactional
    public void deleteSuperByID(int superID) {
        final String DELETE_SUPER_SIGHTING = "DELETE FROM sighting WHERE superID = ?";
        this.jdbc.update(DELETE_SUPER_SIGHTING, superID);

        final String DELETE_SUPER_ORGANIZATION = "DELTE FROM superOrganization WHERE superID = ?";
        this.jdbc.update(DELETE_SUPER_ORGANIZATION, superID);

        final String DELETE_SUPER = "DELETE FROM super WHERE superID = ?";
        this.jdbc.update(DELETE_SUPER, superID);
    }

    @Override
    public List<Super> getSupersForSighting(Sighting sighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Used to get all the sightings associated with a super.
    private Sighting getSightingsForSuper(int sightingID) {
        final String SELECT_SIGHTINGS_FOR_SUPER = "SELECT si.* FROM sighting si"
                + "JOIN super_sighting ssi ON ssi.sightingID = si.sightingID WHERE ssi.superID = ?";
        return this.jdbc.queryForObject(SELECT_SIGHTINGS_FOR_SUPER, new SightingMapper(), sightingID);
    }

    private void associateSupersAndSightings(List<Super> supers) {
        for (Super superhero : supers) {
            superhero.setSighting(this.getSightingsForSuper(superhero.getSuperID()));
        }
    }

    @Override
    public List<Super> getSupersForSuperpower(Superpower superpower) {
        final String SELECT_SUPERS_FOR_SUPERPOWER = "SELECT * FROM super WHERE superpowerID = ?";

        List<Super> supers = this.jdbc.query(SELECT_SUPERS_FOR_SUPERPOWER,
                new SuperMapper(), superpower.getSuperpowerID());

        return supers;
    }

    @Override
    public List<Super> getSupersForLocation(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Super> getSupersForOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static final class SuperMapper implements RowMapper<Super> {

        @Override
        public Super mapRow(ResultSet rs, int index) throws SQLException {
            Super superhero = new Super();
            superhero.setSuperID(rs.getInt("superID"));
            // need to get list of organizations
            superhero.setType(rs.getString("type"));
            superhero.setName(rs.getString("name"));
            superhero.setDescrption(rs.getString("description"));

            return superhero;
        }
    }
}
