/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.entities.Location;
import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.Power;
import com.sg.superhero.entities.Sighting;
import com.sg.superhero.entities.Super;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class SightingDaoDBTest {

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

    public SightingDaoDBTest() {
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
     * Test of getSightingByID method, of class SightingDaoDB.
     */
    @Test
    public void testAddGetSightingByID() {
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

        Location location = new Location();
        location.setName("Test name");
        location.setDescription("Test description");
        location.setAddress("Test address");
        location.setLatitude("11.5");
        location.setLongitude("20.3");
        this.locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setSuperhero(superhero);
        sighting.setLocation(location);
        LocalDate date = LocalDate.of(2022, 07, 29);
        sighting.setDate(date);
        sighting.setDescription("Test description");
        sighting = this.sightingDao.addSighting(sighting);
        
        Sighting fromDao = this.sightingDao.getSightingByID(sighting.getSightingID());
        assertEquals(sighting, fromDao);
    }

    /**
     * Test of getAllSightings method, of class SightingDaoDB.
     */
    @Test
    public void testGetAllSightings() {
    }

    /**
     * Test of updateSighting method, of class SightingDaoDB.
     */
    @Test
    public void testUpdateSighting() {
    }

    /**
     * Test of deleteSightingByID method, of class SightingDaoDB.
     */
    @Test
    public void testDeleteSightingByID() {
    }

    /**
     * Test of getSightingsForLocation method, of class SightingDaoDB.
     */
    @Test
    public void testGetSightingsForLocation() {
    }

    /**
     * Test of getSightingsByDate method, of class SightingDaoDB.
     */
    @Test
    public void testGetSightingsByDate() {
    }

}
