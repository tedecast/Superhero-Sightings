/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

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
public class PowerDaoDB implements PowerDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Power getPowerByID(int powerID) {
        try {
            final String GET_SUPERPOWER_BY_ID = "SELECT * FROM superpower WHERE superpowerID = ?";

            return this.jdbc.queryForObject(GET_SUPERPOWER_BY_ID, new SuperpowerMapper(), powerID);

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        final String GET_ALL_SUPERPOWERS = "SELECT * from superpower";
        return this.jdbc.query(GET_ALL_SUPERPOWERS, new SuperpowerMapper());
    }

    @Override
    @Transactional
    public Power addPower(Power power) {
        final String INSERT_SUPERPOWER = "INSERT INTO superpower(name, description) "
                + "VALUES(?,?)";

        this.jdbc.update(INSERT_SUPERPOWER,
                power.getName(),
                power.getDescription());

        int newSuperpowerID = this.jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        power.setPowerID(newSuperpowerID);

        return power;
    }

    @Override
    public void updatePower(Power power) {
        final String UPDATE_SUPERPOWER = "UPDATE Superpower "
                + "SET name = ?, description = ? "
                + "WHERE SuperpowerID = ?";

        this.jdbc.update(UPDATE_SUPERPOWER,
                power.getName(),
                power.getDescription(),
                power.getPowerID());
    }

    @Override
    @Transactional
    public void deletePowerByID(int superpowerID) {
        final String DELETE_SUPER_SUPERPOWER = "DELETE FROM Super WHERE SuperpowerID = ?";
        this.jdbc.update(DELETE_SUPER_SUPERPOWER, superpowerID);

        final String DELETE_SUPERPOWER = "DELETE FROM Superpower WHERE SuperpowerID = ?";
        this.jdbc.update(DELETE_SUPERPOWER, superpowerID);
    }

    public static final class SuperpowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int index) throws SQLException {
            Power superpower = new Power();
            superpower.setPowerID(rs.getInt("superpowerID"));
            superpower.setName(rs.getString("name"));
            superpower.setDescription(rs.getString("description"));

            return superpower;
        }
    }
}
