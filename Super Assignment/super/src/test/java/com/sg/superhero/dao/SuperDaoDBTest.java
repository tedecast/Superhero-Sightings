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
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Teresa
 */
@SpringBootTest
public class SuperDaoDBTest {

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

    public SuperDaoDBTest() {
    }

    @BeforeEach
    public void setUp() {

        List<Super> supers = this.superDao.getAllSupers();
        for (Super superhero : supers) {
            this.superDao.deleteSuperByID(superhero.getSuperID());
        }

        List<Superpower> powers = this.superpowerDao.getAllSuperpowers();
        for (Superpower power : powers) {
            this.superpowerDao.deleteSuperpowerByID(power.getSuperpowerID());
        }

        List<Organization> orgs = this.orgDao.getAllOrganizations();
        for (Organization org : orgs) {
            this.orgDao.deleteOrganizationByID(org.getOrganizationID());
        }

        List<Sighting> sightings = this.sightingDao.getAllSightings();
        for (Sighting sighting : sightings) {
            this.sightingDao.deleteSightingByID(sighting.getSightingID());
        }

        List<Location> locations = this.locationDao.getAllLocations();
        for (Location location : locations) {
            this.locationDao.deleteLocationByID(location.getLocationID());
        }
    }

    /**
     * Test of getSuperByID method, of class SuperDaoDB.
     */
    @Test
    public void testAddGetSuperByID() {
        
        Superpower superpower = new Superpower();
        superpower.setSuperpowerID(superpower.getSuperpowerID());
        superpower.setName("Super human");
        superpower.setDescription("Enhanced human abilities.");
        superpower = this.superpowerDao.addSuperpower(superpower);
        
        Superpower fromPowerDao = this.superpowerDao.getSuperpowerByID(superpower.getSuperpowerID());
        assertEquals(superpower, fromPowerDao);
        assertNotNull(fromPowerDao);

        Super superhero = new Super();
        superhero.setSuperID(superhero.getSuperID());
        superhero.setSuperpower(superpower);
        superhero.setType("Hero");
        superhero.setName("Captain America");
        superhero.setDescription("Super soldier");
        superhero.setOrganization(new ArrayList<Organization>());
        superhero = this.superDao.addSuper(superhero);
        this.superDao.getSupersForSuperpower(superpower);
        
        Super fromSuperDao = this.superDao.getSuperByID(superhero.getSuperID());
        fromSuperDao.setSuperpower(superpower);
        
        assertNotNull(fromSuperDao);
        assertEquals(superhero.getSuperpower(), fromPowerDao);
        assertEquals(superhero, fromSuperDao);
        assertTrue(superhero.equals(fromSuperDao));
    }

    /**
     * Test of getAllSupers method, of class SuperDaoDB.
     */
    @Test
    public void testGetAllSupers() {
    }

    /**
     * Test of updateSuper method, of class SuperDaoDB.
     */
    @Test
    public void testUpdateSuper() {
    }

    /**
     * Test of deleteSuperByID method, of class SuperDaoDB.
     */
    @Test
    public void testDeleteSuperByID() {
    }

    /**
     * Test of getSupersForSighting method, of class SuperDaoDB.
     */
    @Test
    public void testGetSupersForSighting() {
    }

    /**
     * Test of getSupersForSuperpower method, of class SuperDaoDB.
     */
    @Test
    public void testGetSupersForSuperpower() {
    }

    /**
     * Test of getSupersForLocation method, of class SuperDaoDB.
     */
    @Test
    public void testGetSupersForLocation() {
    }

    /**
     * Test of getSupersForOrganization method, of class SuperDaoDB.
     */
    @Test
    public void testGetSupersForOrganization() {
    }

}
