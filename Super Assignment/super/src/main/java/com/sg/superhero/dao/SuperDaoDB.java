/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.dao.OrganizationDaoDB.OrganizationMapper;
import com.sg.superhero.dao.SightingDaoDB.SightingMapper;
import com.sg.superhero.dao.PowerDaoDB.SuperpowerMapper;
import com.sg.superhero.entities.Location;
import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.Sighting;
import com.sg.superhero.entities.Super;
import com.sg.superhero.entities.Power;
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

    // Used to get the sighting associated with a super.
//    private Sighting getSightingForSuper(int sightingID) {
//        final String SELECT_SIGHTINGS_FOR_SUPER = "SELECT si.* FROM sighting si"
//                + "JOIN super_sighting ssi ON ssi.sightingID = si.sightingID WHERE ssi.superID = ?";
//        return this.jdbc.queryForObject(SELECT_SIGHTINGS_FOR_SUPER, new SightingMapper(), sightingID);
//    }
//    
    // move to sightings
    private List<Sighting> getSightingsForSuper(int sightingID) {
        final String SELECT_SIGHTINGS_FOR_HERO = "SELECT * FROM sighting WHERE superID = ?";
        List<Sighting> sightings = this.jdbc.query(SELECT_SIGHTINGS_FOR_HERO, new SightingMapper(), sightingID);
        return sightings;
    }

     @Override
        public Super getSuperByID(int superID) {
            try {
                final String GET_SUPER_BY_ID = "SELECT * FROM super WHERE superID = ?";
            Super superhero = this.jdbc.queryForObject(GET_SUPER_BY_ID, new SuperMapper(), superID);
                //superhero.setSuperpower(this.getSuperpowerForSuper(superhero.getSuperpower().getSuperpowerID()));
                //superhero.setSighting(this.getSightingsForSuper(superID));
                // location?
                return superhero;
            } catch (DataAccessException ex) {
            return null;
            }
        }

    @Override
    public List<Super> getAllSupers() {
        final String GET_ALL_SUPERS = "SELECT * FROM super";
        List<Super> supers = this.jdbc.query(GET_ALL_SUPERS, new SuperMapper());
                
        this.associatePowerOrganization(supers);
//        for (Super superhero : supers){
//            superhero.setSuperpower(this.getSuperpowerForSuper(superhero.getSuperID()));
//            superhero.setOrganization(this.getOrganizationsForSuper(superhero.getSuperID()));
//        }
        return supers;
    }

    @Override
    @Transactional
    public Super addSuper(Super superhero) {
        final String INSERT_SUPER = "INSERT INTO super(superpowerID, type, name, description) "
                + "VALUES(?,?,?,?)";
        //if (superhero.getSuperpower() != null) {
            this.jdbc.update(INSERT_SUPER,
                    superhero.getPower().getPowerID(),
                    superhero.getType(),
                    superhero.getName(),
                    superhero.getDescription());
//        } else {
//            this.jdbc.update(INSERT_SUPER, 
//                    null,
//                    superhero.getType(),
//                    superhero.getName(),
//                    superhero.getDescription());
//        }

        int newSuperID = this.jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superhero.setSuperID(newSuperID);
        this.insertSuperOrganization(superhero);

        //this.insertSuperSighting(superhero);
        return superhero;
    }

    private void insertSuperOrganization(Super superhero){
        final String INSERT_SUPER_ORG = "INSERT INTO SuperOrganization VALUES(?,?)";
        for (Organization organization : superhero.getOrganization()) {
            this.jdbc.update(INSERT_SUPER_ORG, superhero.getSuperID(), organization.getOrganizationID());
        }
    }
    
    private void associatePowerOrganization(List<Super> supers){
        for (Super superhero : supers){
            superhero.setPower(this.getSuperpowerForSuper(superhero.getSuperID()));
            superhero.setOrganization(this.getOrganizationsForSuper(superhero.getSuperID()));
        }
    }
    
    @Override
    @Transactional
    public void updateSuper(Super superhero) {
        final String UPDATE_SUPER = "UPDATE Super SET SuperpowerID = ?, Type = ?,"
                + " Name = ?, " + "Description = ? WHERE SuperID = ?";

        this.jdbc.update(UPDATE_SUPER,
                superhero.getPower().getPowerID(),
                superhero.getType(),
                superhero.getName(),
                superhero.getDescription(),
                superhero.getSuperID());

        final String DELETE_SUPER_SIGHTING = "DELETE FROM sighting WHERE superID = ?";

        this.jdbc.update(DELETE_SUPER_SIGHTING, superhero.getSuperID());
        // Insert back after update
        //this.insertSuperSighting(superhero);
    }

    // Move to sighting
    // Inserts super into sighting
//    private void insertSuperSighting(Super superhero) {
//        final String INSERT_SIGHTING = "INSERT INTO sighting(superID, locationID, date, description) "
//                + "VALUES(?,?,?,?)";
//
//        for (Sighting sighting : superhero.getSighting()) {
//            this.jdbc.update(INSERT_SIGHTING,
//                    superhero.getSuperID(),
//                    sighting.getLocation().getLocationID(),
//                    sighting.getDate(),
//                    sighting.getDescription());
//            int newSightingID = this.jdbc.queryForObject("SELECT LAST_INSERT_SIGHTINGID()", Integer.class);
//            sighting.setSightingID(newSightingID);
//        }
//    }
    @Override
    @Transactional
    public void deleteSuperByID(int superID) {
        final String DELETE_SUPER_SIGHTING = "DELETE FROM sighting WHERE superID = ?";
        this.jdbc.update(DELETE_SUPER_SIGHTING, superID);

        final String DELETE_SUPER_ORGANIZATION = "DELETE FROM superOrganization WHERE superID = ?";
        this.jdbc.update(DELETE_SUPER_ORGANIZATION, superID);

        final String DELETE_SUPER = "DELETE FROM super WHERE superID = ?";
        this.jdbc.update(DELETE_SUPER, superID);
    }

    // Creates lists of supers for corresponding sightings 
//    private void associateSupersAndSightings(List<Super> supers) {
//        for (Super superhero : supers) {
//            superhero.setSighting(this.getSightingsForSuper(superhero.getSuperID()));
//        }
//    }
    @Override
    public List<Super> getSupersForSighting(Sighting sighting) {
        final String SELECT_SUPERS_FOR_SIGHTINGS = "SELECT * FROM sightings WHERE superID = ?";

        List<Super> supers = this.jdbc.query(SELECT_SUPERS_FOR_SIGHTINGS, new SuperMapper(), sighting.getSightingID());

        //this.associateSupersAndSightings(supers);
        return supers;
    }

    @Override
    public List<Super> getSupersForSuperpower(Power power) {
        final String SELECT_SUPERS_FOR_SUPERPOWER = "SELECT * FROM super WHERE superpowerID = ?";

        List<Super> supers = this.jdbc.query(SELECT_SUPERS_FOR_SUPERPOWER,
                new SuperMapper(), power.getPowerID());

        return supers;
    }

    private List<Organization> getOrganizationsForSuper(int organizationID) {
        final String GET_ORG_FOR_SUPER = "SELECT o.organizationID, o.name, o.description, o.address, o.contactinfo, o.type "
                + "FROM superOrganization so "
                + "JOIN organization o ON so.organizationID = o.organizationID "
                + "WHERE so.superID = ?";
        List<Organization> organizations = this.jdbc.query(GET_ORG_FOR_SUPER, new OrganizationMapper(), organizationID);
        for (Organization organization : organizations) {
            organization.setSupers(this.getSupersForOrganization(organization));
        }
        return organizations;
    }

    @Override
    public List<Super> getSupersForLocation(Location location) {
        final String GET_SUPERS_FOR_LOCATION = "SELECT s.superID, s.superpowerID, s.type, s.name, s.description "
                + "FROM sighting si"
                + "JOIN super s ON si.sightingID = s.superID "
                + "WHERE si.locationID = ?";

        List<Super> supers = this.jdbc.query(GET_SUPERS_FOR_LOCATION, new SuperMapper(), location.getLocationID());
        for (Super superhero : supers) {
            superhero.setPower(this.getSuperpowerForSuper(superhero.getSuperID()));
            superhero.setOrganization(this.getOrganizationsForSuper(superhero.getSuperID()));
        }
        return supers;
    }

    // create private method to return power for hero, pass hero ids, write queries to get the powers
    private Power getSuperpowerForSuper(int superpowerID) {
        try {
            final String GET_SUPERPOWER = "SELECT sp.superpowerID, sp.name, sp.description "
                    + "JOIN super s ON sp = s.superpowerID WHERE s.superID = ?";
            return this.jdbc.queryForObject(GET_SUPERPOWER, new SuperpowerMapper(), superpowerID);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Super> getSupersForOrganization(Organization organization) {
        final String GET_SUPERS_FOR_ORG = "SELECT s.superID, s.type, s.name, s.description "
                + "FROM superOrganization so "
                + "JOIN super s ON so.superID = s.superID "
                + "WHERE so.organizationID = ?";
        List<Super> supers = this.jdbc.query(GET_SUPERS_FOR_ORG, new SuperMapper(), organization.getOrganizationID());
        for (Super superhero : supers) {
            superhero.setPower(this.getSuperpowerForSuper(superhero.getSuperID()));
        }
        return supers;
    }

    public static final class SuperMapper implements RowMapper<Super> {

        @Override
        public Super mapRow(ResultSet rs, int index) throws SQLException {
            Super superhero = new Super();
            superhero.setSuperID(rs.getInt("superID"));
            // need to get list of organizations
            superhero.setType(rs.getString("type"));
            superhero.setName(rs.getString("name"));
            superhero.setDescription(rs.getString("description"));

            return superhero;
        }
    }
}
