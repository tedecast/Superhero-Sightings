/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.entities.Location;
import com.sg.entities.Super;
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
public class LocationDaoDB implements LocationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location getLocationByID(int locationID) {
        try {
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM location WHERE locationID = ?";
            
            return this.jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), locationID);
            
        } catch (DataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM location";
        return this.jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO location (name, description, address, latitude, longitude)" 
                + "VALUES(?,?,?,?,?)";
        
        this.jdbc.update(INSERT_LOCATION, 
                location.getName(), 
                location.getDescrption(), 
                location.getAddress(), 
                location.getLatitude(), 
                location.getLongitude());
        
        int newLocationID = this.jdbc.queryForObject("SELECT LAST_INSERT_LOCATIONID()", Integer.class);
        location.setLocationID(newLocationID);
        return location;
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE location SET name = ?, description = ?, address = ?, latitude = ?, longitude = ?"
                + "WHERE locationID = ?";
        
        this.jdbc.update(UPDATE_LOCATION, 
                location.getLocationID(), 
                location.getName(),
                location.getDescrption(),
                location.getAddress(), 
                location.getLatitude(), 
                location.getLongitude());
    }

    @Override
    @Transactional
    public void deleteLocationByID(int locationID) {
        final String DELETE_SIGHTING_LOCATION = "DELETE FROM sighting_location WHERE locationID = ?";
        this.jdbc.update(DELETE_SIGHTING_LOCATION, locationID);
        
        final String DELETE_LOCATION = "DELETE FROM location WHERE locationID = ?";
        this.jdbc.update(DELETE_LOCATION, locationID);
    }

    @Override
    public List<Location> getLocationsForSuper(Super superhero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setLocationID(rs.getInt("locationID"));
            location.setName(rs.getString("name"));
            location.setDescrption(rs.getString("description"));
            location.setAddress(rs.getString("address"));
            location.setLatitude(rs.getString("latitude"));
            location.setLongitude(rs.getString("longitude"));
            return location;
        }
    }
}
