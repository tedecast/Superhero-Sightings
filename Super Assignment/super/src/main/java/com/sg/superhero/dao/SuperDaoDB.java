/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

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

    @Override
    public void deleteSuperByID(int superID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Super> getSupersForSighting(Sighting sighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Super> getSupersForSuperpower(Superpower superpower) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
