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
            final String GET_POWER_BY_ID = "SELECT * FROM Power WHERE PowerID = ?";

            return this.jdbc.queryForObject(GET_POWER_BY_ID, new PowerMapper(), powerID);

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        final String GET_ALL_POWERS = "SELECT * from Power";
        return this.jdbc.query(GET_ALL_POWERS, new PowerMapper());
    }

    @Override
    @Transactional
    public Power addPower(Power power) {
        final String INSERT_POWER = "INSERT INTO power(name, description) "
                + "VALUES(?,?)";

        this.jdbc.update(INSERT_POWER,
                power.getName(),
                power.getDescription());

        int newPowerID = this.jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        power.setPowerID(newPowerID);

        return power;
    }

    @Override
    public void updatePower(Power power) {
        final String UPDATE_POWER = "UPDATE Power "
                + "SET name = ?, description = ? "
                + "WHERE PowerID = ?";

        this.jdbc.update(UPDATE_POWER,
                power.getName(),
                power.getDescription(),
                power.getPowerID());
    }

    @Override
    @Transactional
    public void deletePowerByID(int powerID) {
        final String UPDATE_POWERS = "UPDATE Super SET powerID = null WHERE powerID = ?";
        this.jdbc.update(UPDATE_POWERS, powerID);

        final String DELETE_SUPERPOWER = "DELETE FROM Power WHERE PowerID = ?";
        this.jdbc.update(DELETE_SUPERPOWER, powerID);
    }

    public static final class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int index) throws SQLException {
            Power superpower = new Power();
            superpower.setPowerID(rs.getInt("powerID"));
            superpower.setName(rs.getString("name"));
            superpower.setDescription(rs.getString("description"));

            return superpower;
        }
    }
}
