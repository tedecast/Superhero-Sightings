/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.Super;
import com.sg.superhero.entities.Superpower;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Teresa
 */
@SpringBootTest
public class OrganizationDaoDBTest {

    @Autowired
    SuperDao superDao;

    @Autowired
    SuperpowerDao superpowerDao;

    @Autowired
    OrganizationDao orgDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingDao sightingDao;

    public OrganizationDaoDBTest() {
    }

    @BeforeEach
    public void setUp() {
        List<Organization> orgs = this.orgDao.getAllOrganizations();
        for (Organization org : orgs) {
            this.orgDao.deleteOrganizationByID(org.getOrganizationID());
        }

        List<Super> supers = this.superDao.getAllSupers(); 
            for (Super superhero : supers) {
                this.superDao.deleteSuperByID(superhero.getSuperID());
            }
        
        List<Superpower> powers = this.superpowerDao.getAllSuperpowers();
        for (Superpower power : powers){
            this.superpowerDao.deleteSuperpowerByID(power.getSuperpowerID());
        }
    }

    /**
     * Test of getOrganizationByID method, of class OrganizationDaoDB.
     */
    @Test
    public void testAddGetOrganizationByID() {
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetAllOrganizations() {
    }

    /**
     * Test of updateOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testUpdateOrganization() {
    }

    /**
     * Test of deleteOrganizationByID method, of class OrganizationDaoDB.
     */
    @Test
    public void testDeleteOrganizationByID() {
    }

    /**
     * Test of getOrganizationsForSuper method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetOrganizationsForSuper() {
    }

}
