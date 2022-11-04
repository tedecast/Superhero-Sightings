/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

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
public class SuperpowerDaoDB implements SuperpowerDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Superpower getSuperpowerByID(int superpowerID) {
        try {
            final String GET_SUPERPOWER_BY_ID = "SELECT * FROM superpower WHERE superpowerID = ?";
            
            return this.jdbc.queryForObject(GET_SUPERPOWER_BY_ID, new SuperpowerMapper(), superpowerID);
            
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        final String GET_ALL_SUPERPOWERS = "SELECT * from superpower"; 
        return this.jdbc.query(GET_ALL_SUPERPOWERS, new SuperpowerMapper());
    }

    @Override
    @Transactional
    public Superpower addSuperpower(Superpower superpower) {
        final String INSERT_SUPERPOWER = "INSERT INTO superpower(name, description) " + 
                "VALUES(?,?)";
        this.jdbc.update(INSERT_SUPERPOWER, 
                superpower.getName(), 
                superpower.getDescription());
        
        int newSuperpowerID = this.jdbc.queryForObject("SELECT LAST_INSERT_SUPERPOWERID()", Integer.class);
        
        superpower.setSuperpowerID(newSuperpowerID);
        return superpower;
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        final String UPDATE_SUPERPOWER = "UPDATE superpower SET name = ?, description = ?, WHERE superpowerID = ?";
        
        this.jdbc.update(UPDATE_SUPERPOWER, 
                superpower.getSuperpowerID(),
                superpower.getName(), 
                superpower.getDescription());
    }

    @Override
    @Transactional
    public void deleteSuperpowerByID(int superpowerID) {
        final String DELETE_SUPER_SUPERPOWER = "DELETE FROM super_superpower WHERE superpowerID = ?";
        this.jdbc.update(DELETE_SUPER_SUPERPOWER, superpowerID);
        
        final String DELETE_SUPERPOWER = "DELETE FROM superpower WHERE superpowerID = ?";
        this.jdbc.update(DELETE_SUPERPOWER, superpowerID);
    }
    
    public static final class SuperpowerMapper implements RowMapper<Superpower> {
        
        @Override
        public Superpower mapRow(ResultSet rs, int index) throws SQLException {
            Superpower superpower = new Superpower();
            superpower.setSuperpowerID(rs.getInt("superpowerID"));
            superpower.setName(rs.getString("name"));
            superpower.setDescription(rs.getString("description"));
            
            return superpower;
        }
    }
}
