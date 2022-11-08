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
import com.sg.superhero.entities.Power;
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
    PowerDao powerDao;

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

        List<Power> powers = this.powerDao.getAllPowers();
        for (Power power : powers) {
            this.powerDao.deletePowerByID(power.getPowerID());
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

        Power power = new Power();
        power.setPowerID(power.getPowerID());
        power.setName("Super human");
        power.setDescription("Enhanced human abilities.");
        power = this.powerDao.addPower(power);

        Power fromPowerDao = this.powerDao.getPowerByID(power.getPowerID());
        assertEquals(power, fromPowerDao);
        assertNotNull(fromPowerDao);

        Super superhero = new Super();
        superhero.setSuperID(superhero.getSuperID());
        superhero.setPower(power);
        superhero.setType("Hero");
        superhero.setName("Captain America");
        superhero.setDescription("Super soldier");
        superhero.setOrganization(new ArrayList<Organization>());
        superhero = this.superDao.addSuper(superhero);

        Super fromSuperDao = this.superDao.getSuperByID(superhero.getSuperID());
        fromSuperDao.setPower(power);

        assertNotNull(fromSuperDao);
        assertEquals(superhero.getPower(), fromPowerDao);
        assertEquals(superhero, fromSuperDao);
        assertTrue(superhero.equals(fromSuperDao));
    }

    /**
     * Test of getAllSupers method, of class SuperDaoDB.
     */
    @Test
    public void testGetAllSupers() {
//        Power superpower1 = new Power();
//        superpower1.setSuperpowerID(superpower1.getSuperpowerID());
//        superpower1.setName("Super human");
//        superpower1.setDescription("Enhanced human abilities.");
//        superpower1 = this.superpowerDao.addSuperpower(superpower1);
//
//        Power fromPowerDao1 = this.superpowerDao.getSuperpowerByID(superpower1.getSuperpowerID());
//        assertEquals(superpower1, fromPowerDao1);
//        assertNotNull(fromPowerDao1);
//
//        Super superhero1 = new Super();
//        superhero1.setSuperID(superhero1.getSuperID());
//        superhero1.setSuperpower(superpower1);
//        superhero1.setType("Hero");
//        superhero1.setName("Captain America");
//        superhero1.setDescription("Super soldier");
//        superhero1.setOrganization(new ArrayList<Organization>());
//        superhero1 = this.superDao.addSuper(superhero1);
//
//        Super fromSuperDao1 = this.superDao.getSuperByID(superhero1.getSuperID());
//        fromSuperDao1.setSuperpower(superpower1);
//        this.superDao.updateSuper(superhero1);
//
//        assertNotNull(fromSuperDao1);
//        assertEquals(superhero1.getSuperpower(), fromPowerDao1);
//        assertEquals(superhero1, fromSuperDao1);
//        assertTrue(superhero1.equals(fromSuperDao1));
//
//        // Super2 
//        Power superpower2 = new Power();
//        superpower2.setSuperpowerID(superpower2.getSuperpowerID());
//        superpower2.setName("Spidey senses");
//        superpower2.setDescription("Powers that are like a spider-human hybrid");
//        superpower2 = this.superpowerDao.addSuperpower(superpower2);
//
//        Power fromPowerDao2 = this.superpowerDao.getSuperpowerByID(superpower2.getSuperpowerID());
//        assertEquals(superpower2, fromPowerDao2);
//        assertNotNull(fromPowerDao2);
//
//        Super superhero2 = new Super();
//        superhero2.setSuperID(superhero2.getSuperID());
//        superhero2.setSuperpower(superpower2);
//        superhero2.setType("Hero");
//        superhero2.setName("Spiderman");
//        superhero2.setDescription("He can shoot webs out of his wrists like a spider!");
//        superhero2.setOrganization(new ArrayList<Organization>());
//        superhero2 = this.superDao.addSuper(superhero2);
//
//        Super fromSuperDao2 = this.superDao.getSuperByID(superhero2.getSuperID());
//        fromSuperDao2.setSuperpower(superpower2);
//        this.superDao.updateSuper(superhero2);
//
//        assertNotNull(fromSuperDao2);
//        assertEquals(superhero2.getSuperpower(), fromPowerDao2);
//        assertEquals(superhero2, fromSuperDao2);
//        assertTrue(superhero2.equals(fromSuperDao2));
//
//        List<Super> supers = this.superDao.getAllSupers();
//
//        assertEquals(2, supers.size());
//        assertTrue(supers.contains(superhero1));
//        assertTrue(supers.contains(superhero2));
    }

    /**
     * Test of updateSuper method, of class SuperDaoDB.
     */
    @Test
    public void testUpdateSuper() {
        Power power = new Power();
        power.setPowerID(power.getPowerID());
        power.setName("Super human");
        power.setDescription("Enhanced human abilities.");
        power = this.powerDao.addPower(power);

        Power fromPowerDao = this.powerDao.getPowerByID(power.getPowerID());
        assertEquals(power, fromPowerDao);
        assertNotNull(fromPowerDao);

        Super superhero = new Super();
        superhero.setSuperID(superhero.getSuperID());
        superhero.setPower(power);
        superhero.setType("Hero");
        superhero.setName("Captain America");
        superhero.setDescription("Super soldier");
        superhero.setOrganization(new ArrayList<Organization>());
        superhero = this.superDao.addSuper(superhero);

        Super fromSuperDao = this.superDao.getSuperByID(superhero.getSuperID());
        fromSuperDao.setPower(power);
        this.superDao.updateSuper(superhero);

        assertNotNull(fromSuperDao);
        assertEquals(superhero.getPower(), fromPowerDao);
        assertEquals(superhero, fromSuperDao);
        assertTrue(superhero.equals(fromSuperDao));

        // Update Super
        superhero.setSuperID(superhero.getSuperID());
        superhero.setPower(superhero.getPower());
        superhero.setType("Hero");
        superhero.setName("Spiderman");
        superhero.setDescription("He can shoot webs out of his wrists like a spider!");
        superhero.setOrganization(new ArrayList<Organization>());

        fromSuperDao = this.superDao.getSuperByID(superhero.getSuperID());
        fromSuperDao.setPower(superhero.getPower());
        this.superDao.updateSuper(superhero);
        
        assertNotEquals(superhero, fromSuperDao);
        
        fromSuperDao = this.superDao.getSuperByID(superhero.getSuperID());
        fromSuperDao.setPower(superhero.getPower());
        assertEquals(superhero, fromSuperDao);
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
