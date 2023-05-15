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
     * Test of getSupersForLocation method, of class SuperDaoDB.
     */
    @Test
    public void testGetSupersForLocation() {
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

        List<Super> supers = this.superDao.getSupersForLocation(location);
        assertEquals(1, supers.size());
        assertTrue(supers.contains(superhero));
    }

    /**
     * Test of getSupersForOrganization method, of class SuperDaoDB.
     */
    @Test
    public void testGetSupersForOrganization() {
        Power power = new Power();
        power.setPowerID(power.getPowerID());
        power.setName("Super human");
        power.setDescription("Enhanced human abilities.");
        power = this.powerDao.addPower(power);

        // Superhero1
        Super superhero = new Super();
        superhero.setSuperID(superhero.getSuperID());
        superhero.setPower(power);
        superhero.setType("Hero");
        superhero.setName("Captain America");
        superhero.setDescription("Super soldier");
        superhero.setOrganization(new ArrayList<Organization>());
        superhero = this.superDao.addSuper(superhero);

        // Superhero2
        Super superhero2 = new Super();
        superhero2.setSuperID(superhero2.getSuperID());
        superhero2.setPower(power);
        superhero2.setType("Hero");
        superhero2.setName("Spiderman");
        superhero2.setDescription("He can shoot webs out of his wrists like a spider!");
        superhero2.setOrganization(new ArrayList<Organization>());
        superhero2 = this.superDao.addSuper(superhero2);

        List<Super> supers = new ArrayList<>();
        supers.add(superhero);
        supers.add(superhero2);

        // Organization 1
        Organization org = new Organization();
        org.setName("The Avengers");
        org.setDescription("Best group of heroes.");
        org.setAddress("Avengers Tower, New York");
        org.setContactInfo("avengersoffice@marvel.com");
        org.setType("Hero");
        org.setSupers(supers);
        org = this.orgDao.addOrganization(org);

        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);

        superhero.setOrganization(orgs);
        this.superDao.updateSuper(superhero);
        superhero2.setOrganization(orgs);
        this.superDao.updateSuper(superhero2);

        List<Super> superOrgs = this.superDao.getSupersForOrganization(org);
        assertEquals(2, superOrgs.size());
        assertEquals("Captain America", superOrgs.get(0).getName());
        assertEquals("Spiderman", superOrgs.get(1).getName());
    }

    @Test
    public void getSupersForPower() {
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
        Super superhero2 = new Super();
        superhero2.setSuperID(superhero2.getSuperID());
        superhero2.setPower(power1);
        superhero2.setType("Hero");
        superhero2.setName("Spiderman");
        superhero2.setDescription("He can shoot webs out of his wrists like a spider!");
        superhero2.setOrganization(new ArrayList<Organization>());
        superhero2 = this.superDao.addSuper(superhero2);
        
        List<Super> supers = this.superDao.getSupersForPower(power1);
        assertEquals(2, supers.size());
        assertEquals("Captain America", supers.get(0).getName());
        assertEquals("Spiderman", supers.get(1).getName());

    }
}
