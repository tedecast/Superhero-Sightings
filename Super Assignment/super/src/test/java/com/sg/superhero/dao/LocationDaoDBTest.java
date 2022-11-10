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
public class LocationDaoDBTest {

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

    public LocationDaoDBTest() {

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
     * Test of getLocationByID method, of class LocationDaoDB.
     */
    @Test
    public void testAddGetLocationByID() {
        Location location = new Location();
        location.setName("Test name");
        location.setDescription("Test description");
        location.setAddress("Test address");
        location.setLatitude("11.5");
        location.setLongitude("20.3");
        this.locationDao.addLocation(location);

        Location fromDao = this.locationDao.getLocationByID(location.getLocationID());
        assertEquals(location, fromDao);
    }

    /**
     * Test of getAllLocations method, of class LocationDaoDB.
     */
    @Test
    public void testGetAllLocations() {
        // Location 1
        Location location = new Location();
        location.setName("Test name");
        location.setDescription("Test description");
        location.setAddress("Test address");
        location.setLatitude("11.5");
        location.setLongitude("20.3");
        this.locationDao.addLocation(location);

        //Location 2
        Location location2 = new Location();
        location2.setName("Test name 2");
        location2.setDescription("Test description 2");
        location2.setAddress("Test address 2");
        location2.setLatitude("11.50");
        location2.setLongitude("20.30");
        this.locationDao.addLocation(location2);

        List<Location> locations = this.locationDao.getAllLocations();
        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));
    }

    /**
     * Test of updateLocation method, of class LocationDaoDB.
     */
    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setName("Test name");
        location.setDescription("Test description");
        location.setAddress("Test address");
        location.setLatitude("11.5");
        location.setLongitude("20.3");
        this.locationDao.addLocation(location);

        Location fromDao = this.locationDao.getLocationByID(location.getLocationID());
        assertEquals(location, fromDao);

        location.setName("Test name 2");
        location.setDescription("Test description 2");
        location.setAddress("Test address 2");
        location.setLatitude("11.50");
        location.setLongitude("20.30");
        this.locationDao.updateLocation(location);
        assertNotEquals(location, fromDao);

        fromDao = this.locationDao.getLocationByID(location.getLocationID());
        assertEquals(location, fromDao);
    }

    /**
     * Test of deleteLocationByID method, of class LocationDaoDB.
     */
    @Test
    public void testDeleteLocationByID() {
        Power power = new Power();
        power.setPowerID(power.getPowerID());
        power.setName("Super human");
        power.setDescription("Enhanced human abilities.");
        power = this.powerDao.addPower(power);

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

        this.locationDao.deleteLocationByID(location.getLocationID());

        Sighting fromSightingDao = this.sightingDao.getSightingByID(sighting.getSightingID());
        assertNull(fromSightingDao);

        Location fromLocationDao = this.locationDao.getLocationByID(location.getLocationID());
        assertNull(fromLocationDao);
    }

    /**
     * Test of getLocationsForSuper method, of class LocationDaoDB.
     */
    @Test
    public void testGetLocationsForSuper() {
        Power power = new Power();
        power.setPowerID(power.getPowerID());
        power.setName("Super human");
        power.setDescription("Enhanced human abilities.");
        power = this.powerDao.addPower(power);

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
        
        List<Location> locations = this.locationDao.getLocationsForSuper(superhero);
        assertTrue(locations.contains(location));
    }

}
