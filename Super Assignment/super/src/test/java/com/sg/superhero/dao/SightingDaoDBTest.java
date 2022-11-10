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
        // Super 1
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

        // Sighting 1
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

        // Super 2
        Power power2 = new Power();
        power2.setPowerID(power2.getPowerID());
        power2.setName("Spidey senses");
        power2.setDescription("Powers that are like a spider-human hybrid");
        power2 = this.powerDao.addPower(power2);

        Power fromPowerDao2 = this.powerDao.getPowerByID(power2.getPowerID());
        assertEquals(power2, fromPowerDao2);
        assertNotNull(fromPowerDao2);

        Super superhero2 = new Super();
        superhero2.setSuperID(superhero2.getSuperID());
        superhero2.setPower(power2);
        superhero2.setType("Hero");
        superhero2.setName("Spiderman");
        superhero2.setDescription("He can shoot webs out of his wrists like a spider!");
        superhero2.setOrganization(new ArrayList<Organization>());
        superhero2 = this.superDao.addSuper(superhero2);

        // Sighting 2
        Sighting sighting2 = new Sighting();
        sighting2.setSuperhero(superhero2);
        sighting2.setLocation(location);
        sighting2.setDate(date);
        sighting2.setDescription("Test description 2");
        sighting2 = this.sightingDao.addSighting(sighting2);

        Sighting fromDao = this.sightingDao.getSightingByID(sighting.getSightingID());
        assertEquals(sighting, fromDao);

        List<Sighting> sightings = this.sightingDao.getAllSightings();
        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }

    /**
     * Test of updateSighting method, of class SightingDaoDB.
     */
    @Test
    public void testUpdateSighting() {
        // Super 1
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

        // Sighting 1
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

        // Super 2
        Power power2 = new Power();
        power2.setPowerID(power2.getPowerID());
        power2.setName("Spidey senses");
        power2.setDescription("Powers that are like a spider-human hybrid");
        power2 = this.powerDao.addPower(power2);

        Power fromPowerDao2 = this.powerDao.getPowerByID(power2.getPowerID());
        assertEquals(power2, fromPowerDao2);
        assertNotNull(fromPowerDao2);

        Super superhero2 = new Super();
        superhero2.setSuperID(superhero2.getSuperID());
        superhero2.setPower(power2);
        superhero2.setType("Hero");
        superhero2.setName("Spiderman");
        superhero2.setDescription("He can shoot webs out of his wrists like a spider!");
        superhero2.setOrganization(new ArrayList<Organization>());
        superhero2 = this.superDao.addSuper(superhero2);

        // Location 2
        Location location2 = new Location();
        location2.setName("Test name 2");
        location2.setDescription("Test description 2");
        location2.setAddress("Test address 2");
        location2.setLatitude("11.52");
        location2.setLongitude("20.32");
        this.locationDao.addLocation(location2);

        // Updated sighting
        sighting.setSuperhero(superhero2);
        sighting.setLocation(location2);
        LocalDate date2 = LocalDate.of(2022, 07, 30);
        sighting.setDate(date2);
        sighting.setDescription("Test description 2");

        this.sightingDao.updateSighting(sighting);
        assertNotEquals(sighting, fromDao);

        fromDao = this.sightingDao.getSightingByID(sighting.getSightingID());
        assertEquals(sighting, fromDao);
    }

    /**
     * Test of deleteSightingByID method, of class SightingDaoDB.
     */
    @Test
    public void testDeleteSightingByID() {
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

        this.sightingDao.deleteSightingByID(sighting.getSightingID());

        fromDao = this.sightingDao.getSightingByID(sighting.getSightingID());
        assertNull(fromDao);
    }

    /**
     * Test of getSightingsForLocation method, of class SightingDaoDB.
     */
    @Test
    public void testGetSightingsForLocation() {
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

        // Sighting 1 
        Sighting sighting = new Sighting();
        sighting.setSuperhero(superhero);
        sighting.setLocation(location);
        LocalDate date = LocalDate.of(2022, 07, 29);
        sighting.setDate(date);
        sighting.setDescription("Test description");
        sighting = this.sightingDao.addSighting(sighting);

        // Sighting 2
        Sighting sighting2 = new Sighting();
        sighting2.setSuperhero(superhero);
        sighting2.setLocation(location);
        sighting2.setDate(date);
        sighting2.setDescription("Test description 2");
        sighting2 = this.sightingDao.addSighting(sighting2);

        List<Sighting> sightings = this.sightingDao.getSightingsForLocation(location);
        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }

    /**
     * Test of getSightingsByDate method, of class SightingDaoDB.
     */
    @Test
    public void testGetSightingsByDate() {
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

        // Sighting 1 
        Sighting sighting = new Sighting();
        sighting.setSuperhero(superhero);
        sighting.setLocation(location);
        LocalDate date = LocalDate.of(2022, 07, 29);
        sighting.setDate(date);
        sighting.setDescription("Test description");
        sighting = this.sightingDao.addSighting(sighting);

        // Sighting 2
        Sighting sighting2 = new Sighting();
        sighting2.setSuperhero(superhero);
        sighting2.setLocation(location);
        sighting2.setDate(date);
        sighting2.setDescription("Test description 2");
        sighting2 = this.sightingDao.addSighting(sighting2);

        List<Sighting> sightings = this.sightingDao.getSightingsByDate(date);

        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
        assertEquals(2, sightings.size());
    }

}
