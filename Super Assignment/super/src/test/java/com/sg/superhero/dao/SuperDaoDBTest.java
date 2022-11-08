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

        assertEquals(superhero.getPower(), fromPowerDao);
        assertEquals(superhero, fromSuperDao);
        assertTrue(superhero.equals(fromSuperDao));
    }

    /**
     * Test of getAllSupers method, of class SuperDaoDB.
     */
    @Test
    public void testGetAllSupers() {
        Power power1 = new Power();
        power1.setPowerID(power1.getPowerID());
        power1.setName("Super human");
        power1.setDescription("Enhanced human abilities.");
        power1 = this.powerDao.addPower(power1);

        Power fromPowerDao1 = this.powerDao.getPowerByID(power1.getPowerID());
        assertEquals(power1, fromPowerDao1);
        assertNotNull(fromPowerDao1);

        Super superhero1 = new Super();
        superhero1.setSuperID(superhero1.getSuperID());
        superhero1.setPower(power1);
        superhero1.setType("Hero");
        superhero1.setName("Captain America");
        superhero1.setDescription("Super soldier");
        superhero1.setOrganization(new ArrayList<Organization>());
        superhero1 = this.superDao.addSuper(superhero1);

        // Super2 
        Power superpower2 = new Power();
        superpower2.setPowerID(superpower2.getPowerID());
        superpower2.setName("Spidey senses");
        superpower2.setDescription("Powers that are like a spider-human hybrid");
        superpower2 = this.powerDao.addPower(superpower2);

        Power fromPowerDao2 = this.powerDao.getPowerByID(superpower2.getPowerID());
        assertEquals(superpower2, fromPowerDao2);
        assertNotNull(fromPowerDao2);

        Super superhero2 = new Super();
        superhero2.setSuperID(superhero2.getSuperID());
        superhero2.setPower(superpower2);
        superhero2.setType("Hero");
        superhero2.setName("Spiderman");
        superhero2.setDescription("He can shoot webs out of his wrists like a spider!");
        superhero2.setOrganization(new ArrayList<Organization>());
        superhero2 = this.superDao.addSuper(superhero2);

        List<Super> supers = this.superDao.getAllSupers();

        assertEquals(2, supers.size());
        assertTrue(supers.contains(superhero1));
        assertTrue(supers.contains(superhero2));
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

        assertNotNull(fromSuperDao);
        assertEquals(superhero.getPower(), fromPowerDao);
        assertEquals(superhero, fromSuperDao);
        assertTrue(superhero.equals(fromSuperDao));

        this.superDao.deleteSuperByID(superhero.getSuperID());
        fromSuperDao = this.superDao.getSuperByID(superhero.getSuperID());
        assertNull(fromSuperDao);
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
