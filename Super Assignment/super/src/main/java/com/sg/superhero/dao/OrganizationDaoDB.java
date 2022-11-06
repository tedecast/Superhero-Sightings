/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.Super;
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
public class OrganizationDaoDB implements OrganizationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organization getOrganizationByID(int organizationID) {
        try {
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM organization WHERE organizationID = ?";
            
            return this.jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), organizationID);
        } catch (DataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM organization";
        return this.jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String INSERT_ORGANIZATION = "INSERT INTO organization (name, description, address, contactInfo, type)"
                + "VALUES(?,?,?,?,?)";
        this.jdbc.update(INSERT_ORGANIZATION, 
                organization.getName(), 
                organization.getDescription(), 
                organization.getAddress(), 
                organization.getContactInfo(), 
                organization.getType());
        
        int newOrganizationID = this.jdbc.queryForObject("SELECT LAST_INSERT_ORGANIZATIONID()", Integer.class);
        organization.setOrganizationID(newOrganizationID);
        
        return organization;
    }

    @Override
    @Transactional
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE organization SET name = ?, description = ?, address = ?, "
                + "contactInfo = ?, type = ? WHERE organizationID = ?";
        
        this.jdbc.update(UPDATE_ORGANIZATION, 
                organization.getOrganizationID(), 
                organization.getName(), 
                organization.getDescription(), 
                organization.getAddress(), 
                organization.getContactInfo(), 
                organization.getType());
        
//        final String DELETE_SUPER_ORGANIZATION = "DELETE FROM superOrganization WHERE organizationID = ?";
//        this.jdbc.update(DELETE_SUPER_ORGANIZATION, organization.getOrganizationID());
    }

    @Override
    public void deleteOrganizationByID(int organizationID) {
       final String DELETE_ORGANIZATION_SUPER = "DELETE FROM superOrganization WHERE organizationID = ?";
       this.jdbc.update(DELETE_ORGANIZATION_SUPER, organizationID);
       
       final String DELETE_ORGANIZATION = "DELETE FROM organization where organizationID = ?";
       this.jdbc.update(DELETE_ORGANIZATION, organizationID);
    }

    @Override
    public List<Organization> getOrganizationsForSuper(Super superhero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationID(rs.getInt("organizationID"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setAddress(rs.getString("address"));
            organization.setContactInfo(rs.getString("contactInfo"));
            organization.setType(rs.getString("type"));
            return organization;
        }
    }

}
