/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.dao.OrganizationDaoDB.OrganizationMapper;
import com.sg.superhero.dao.PowerDaoDB.PowerMapper;
import com.sg.superhero.dao.SightingDaoDB.SightingMapper;
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
      // Creates lists of supers for corresponding sightings 
//    private void associateSupersAndSightings(List<Super> supers) {
//        for (Super superhero : supers) {
//            superhero.setSighting(this.getSightingsForSuper(superhero.getSuperID()));
//        }
//    }
//    @Override
//    public List<Super> getSupersForSighting(Sighting sighting) {
//        final String SELECT_SUPERS_FOR_SIGHTINGS = "SELECT * FROM sighting WHERE superID = ?";
//
//        List<Super> supers = this.jdbc.query(SELECT_SUPERS_FOR_SIGHTINGS, new SuperMapper(), sighting.getSightingID());
//
//        //this.associateSupersAndSightings(supers);
//        return supers;
//    }


    @Override
    public Super getSuperByID(int superID) {
        try {
            final String GET_SUPER_BY_ID = "SELECT * FROM super WHERE superID = ?";
            Super superhero = this.jdbc.queryForObject(GET_SUPER_BY_ID, new SuperMapper(), superID);
            superhero.setPower(this.getPowerForSuper(superID));
            superhero.setOrganization(this.getOrganizationsForSuper(superID));
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
        return supers;
    }

    private void associatePowerOrganization(List<Super> supers) {
        for (Super superhero : supers) {
            superhero.setPower(this.getPowerForSuper(superhero.getSuperID()));
            superhero.setOrganization(this.getOrganizationsForSuper(superhero.getSuperID()));
        }
    }


    private Power getPowerForSuper(int powerID) {
        try {
            final String GET_POWER = "SELECT p.powerID, p.name, p.description FROM Power p "
                    + "JOIN Super s ON p.powerID = s.powerID WHERE s.superID = ?";
            return this.jdbc.queryForObject(GET_POWER, new PowerMapper(), powerID);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private List<Organization> getOrganizationsForSuper(int organizationID) {
        final String GET_ORG_FOR_SUPER = "SELECT o.organizationID, o.name, o.description, o.address, o.contactinfo, o.type "
                + "FROM SuperOrganization so "
                + "JOIN Organization o ON so.organizationID = o.organizationID "
                + "WHERE so.superID = ?";
        List<Organization> organizations = this.jdbc.query(GET_ORG_FOR_SUPER, new OrganizationMapper(), organizationID);
        for (Organization organization : organizations) {
            organization.setSupers(this.getSupersForOrganization(organization));
        }
        return organizations;
    }

    @Override
    @Transactional
    public Super addSuper(Super superhero) {
        final String INSERT_SUPER = "INSERT INTO super(PowerID, Type, Name, Description) "
                + "VALUES(?,?,?,?)";
        this.jdbc.update(INSERT_SUPER,
                superhero.getPower().getPowerID(),
                superhero.getType(),
                superhero.getName(),
                superhero.getDescription());
        
        int newSuperID = this.jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superhero.setSuperID(newSuperID);
        this.insertSuperOrganization(superhero);

        //this.insertSuperSighting(superhero);
        return superhero;
    }

    private void insertSuperOrganization(Super superhero) {
        final String INSERT_SUPER_ORG = "INSERT INTO SuperOrganization VALUES(?,?)";
        for (Organization organization : superhero.getOrganization()) {
            this.jdbc.update(INSERT_SUPER_ORG, superhero.getSuperID(), organization.getOrganizationID());
        }
    }

    @Override
    @Transactional
    public void updateSuper(Super superhero) {
        final String UPDATE_SUPER = "UPDATE Super SET PowerID = ?, Type = ?,"
                + " Name = ?, " + "Description = ? WHERE SuperID = ?";

        this.jdbc.update(UPDATE_SUPER,
                superhero.getPower().getPowerID(),
                superhero.getType(),
                superhero.getName(),
                superhero.getDescription(),
                superhero.getSuperID());

        final String DELETE_SUPER_ORGANIZATION = "DELETE FROM SuperOrganization WHERE superID = ?";
        this.jdbc.update(DELETE_SUPER_ORGANIZATION, superhero.getSuperID());
        this.insertSuperOrganization(superhero);
    }


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

  
    @Override
    public List<Super> getSupersForLocation(Location location) {
        final String GET_SUPERS_FOR_LOCATION = "SELECT s.superID, s.powerID, s.type, s.name, s.description "
                + "FROM Sighting si "
                + "JOIN Super s ON si.superID = s.superID "
                + "WHERE si.locationID = ?";

        List<Super> supers = this.jdbc.query(GET_SUPERS_FOR_LOCATION, new SuperMapper(), location.getLocationID());
        for (Super superhero : supers) {
            superhero.setPower(this.getPowerForSuper(superhero.getSuperID()));
            superhero.setOrganization(this.getOrganizationsForSuper(superhero.getSuperID()));
        }
        return supers;
    }

    @Override
    public List<Super> getSupersForOrganization(Organization organization) {
        final String GET_SUPERS_FOR_ORG = "SELECT s.superID, s.powerID, s.type, s.name, s.description "
                + "FROM SuperOrganization so "
                + "JOIN Super s ON so.superID = s.superID "
                + "WHERE so.organizationID = ?";
        
        List<Super> supers = this.jdbc.query(GET_SUPERS_FOR_ORG, new SuperMapper(), organization.getOrganizationID());
        for (Super superhero : supers) {
            superhero.setPower(this.getPowerForSuper(superhero.getSuperID()));
        }
         
        return supers;
    }

    public static final class SuperMapper implements RowMapper<Super> {

        @Override
        public Super mapRow(ResultSet rs, int index) throws SQLException {
            Super superhero = new Super();
            superhero.setSuperID(rs.getInt("superID"));
            superhero.setType(rs.getString("type"));
            superhero.setName(rs.getString("name"));
            superhero.setDescription(rs.getString("description"));

            return superhero;
        }
    }
}