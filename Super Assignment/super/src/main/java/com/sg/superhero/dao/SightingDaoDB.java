/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.dao.LocationDaoDB.LocationMapper;
import com.sg.superhero.dao.OrganizationDaoDB.OrganizationMapper;
import com.sg.superhero.dao.SuperpowerDaoDB.SuperpowerMapper;
import com.sg.superhero.entities.Location;
import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.Sighting;
import com.sg.superhero.entities.Super;
import com.sg.superhero.entities.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public Sighting getSightingByID(int sightingID) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM sightingID = ?";
            
            return this.jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingID);
            
        } catch (DataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sightings";
        return this.jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO sighting(date, description)" + "VALUES(?,?)";
        
        this.jdbc.update(INSERT_SIGHTING, 
                sighting.getDate(), 
                sighting.getDescription());
        
        int newSightingID = this.jdbc.queryForObject("SELECT LAST_INSERT_SIGHTINGID", Integer.class);
        sighting.setSightingID(newSightingID);
        
        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE sighting SET date = ?, description = ? " 
                + "WHERE sightingID = ?";
        
        this.jdbc.update(UPDATE_SIGHTING, 
                sighting.getSightingID(), 
                sighting.getDate(), 
                sighting.getDescription());
    }

    @Override
    @Transactional
    public void deleteSightingByID(int sightingID) {
        final String DELETE_SIGHTING = "DELETE FROM sighting WHERE sightingID = ?";
        this.jdbc.update(DELETE_SIGHTING, sightingID);
    }

    @Override
    public List<Sighting> getSightingsForLocation(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getSightingsBySuper(Super supers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Superpower SuperpowerForSuper(int superpowerID){
        try {
            final String SELECT_SP_FOR_SUPER = "SELECT sp.* FROM Superpower sp "
                    + "JOIN Super s ON sp.superpowerID = s.superpowerID WHERE s.superpowerID = ?";
            return this.jdbc.queryForObject(SELECT_SP_FOR_SUPER, new SuperpowerMapper(), superpowerID);
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    private List<Organization> getOrganizationsForSuper(int organizationID){
        final String SELECT_ORG_FOR_SUPER = "SELECT o.organizationID, o.name, o.description, o.address, o.contactInfo, o.type "
                + "FROM SuperOrganization so "
                + "JOIN Organization o ON so.organizationID = o.organizationID "
                + "WHERE so.superID = ?";
        return this.jdbc.query(SELECT_ORG_FOR_SUPER, new OrganizationMapper(), organizationID);
    }
    
    private Location getLocationForSighting(Sighting sighting){
        final String GET_LOCATION_FOR_SIGHTING = "SELECT l.* FROM Sighting si "
              + "JOIN Location l ON si.locationId = l.locationID "
              + "WHERE si.locationID = ?";
        return this.jdbc.queryForObject(GET_LOCATION_FOR_SIGHTING, new LocationMapper(), sighting.getSightingID());
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
