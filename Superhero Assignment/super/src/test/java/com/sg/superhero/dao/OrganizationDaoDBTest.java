/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.Super;
import com.sg.superhero.entities.Power;
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
public class OrganizationDaoDBTest {

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

        List<Power> powers = this.powerDao.getAllPowers();
        for (Power power : powers) {
            this.powerDao.deletePowerByID(power.getPowerID());
        }
    }

    /**
     * Test of getOrganizationByID method, of class OrganizationDaoDB.
     */
    @Test
    public void testAddGetOrganizationByID() {

        Power power = new Power();
        power.setName("Super human");
        power.setDescription("Enhanced human abilities.");
        power = this.powerDao.addPower(power);

        Power fromPowerDao = this.powerDao.getPowerByID(power.getPowerID());
        assertEquals(power, fromPowerDao);
        assertNotNull(fromPowerDao);

        Super superhero = new Super();
        superhero.setPower(power);
        superhero.setType("Hero");
        superhero.setName("Captain America");
        superhero.setDescription("Super soldier");
        superhero.setOrganization(new ArrayList<Organization>());
        superhero = this.superDao.addSuper(superhero);

        Super fromSuperDao = this.superDao.getSuperByID(superhero.getSuperID());
        assertEquals(superhero, fromSuperDao);
        assertNotNull(fromSuperDao);

        List<Super> supers = new ArrayList<>();
        supers.add(superhero);

        Organization org = new Organization();
        org.setName("The Avengers");
        org.setDescription("Best group of heroes.");
        org.setAddress("Avengers Tower, New York");
        org.setContactInfo("avengersoffice@marvel.com");
        org.setType("Hero");

        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);

        org.setSupers(supers);
        org = this.orgDao.addOrganization(org);

        Organization fromOrgDao = this.orgDao.getOrganizationByID(org.getOrganizationID());
        assertEquals(org, fromOrgDao);
        assertNotNull(org);
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetAllOrganizations() {
        Power power = new Power();
        power.setName("Super human");
        power.setDescription("Enhanced human abilities.");
        power = this.powerDao.addPower(power);

        Power fromPowerDao = this.powerDao.getPowerByID(power.getPowerID());
        assertEquals(power, fromPowerDao);
        assertNotNull(fromPowerDao);

        Super superhero = new Super();
        superhero.setPower(power);
        superhero.setType("Hero");
        superhero.setName("Captain America");
        superhero.setDescription("Super soldier");
        superhero.setOrganization(new ArrayList<Organization>());
        superhero = this.superDao.addSuper(superhero);

        Super fromSuperDao = this.superDao.getSuperByID(superhero.getSuperID());
        assertEquals(superhero, fromSuperDao);
        assertNotNull(fromSuperDao);

        List<Super> supers = new ArrayList<>();
        supers.add(superhero);

        // Organization 1
        Organization org1 = new Organization();
        org1.setName("The Avengers");
        org1.setDescription("Best group of heroes.");
        org1.setAddress("Avengers Tower, New York");
        org1.setContactInfo("avengersoffice@marvel.com");
        org1.setType("Hero");

        List<Organization> orgs = new ArrayList<>();
        orgs.add(org1);

        org1.setSupers(supers);
        org1 = this.orgDao.addOrganization(org1);

        Organization fromOrgDao = this.orgDao.getOrganizationByID(org1.getOrganizationID());
        assertEquals(org1, fromOrgDao);
        assertNotNull(org1);

        // Organization 2
        Organization org2 = new Organization();
        org2.setName("Arkham Asylum");
        org2.setDescription("This is where the bad guys get locked up.");
        org2.setAddress("Gotham City");
        org2.setContactInfo("arkhamasylum@dc.com");
        org2.setType("Villain");

        orgs.add(org2);

        org2.setSupers(supers);
        org2 = this.orgDao.addOrganization(org2);

        Organization fromOrgDao2 = this.orgDao.getOrganizationByID(org2.getOrganizationID());
        assertEquals(org2, fromOrgDao2);
        assertNotNull(org2);

        assertEquals(2, orgs.size());
        assertTrue(orgs.contains(org1));
        assertTrue(orgs.contains(org2));
    }

    /**
     * Test of updateOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testUpdateOrganization() {

        Power power = new Power();
        power.setName("Super human");
        power.setDescription("Enhanced human abilities.");
        power = this.powerDao.addPower(power);

        Power fromPowerDao = this.powerDao.getPowerByID(power.getPowerID());
        assertEquals(power, fromPowerDao);
        assertNotNull(fromPowerDao);

        Super superhero = new Super();
        superhero.setPower(power);
        superhero.setType("Hero");
        superhero.setName("Captain America");
        superhero.setDescription("Super soldier");
        superhero.setOrganization(new ArrayList<Organization>());
        superhero = this.superDao.addSuper(superhero);

        Super fromSuperDao = this.superDao.getSuperByID(superhero.getSuperID());
        assertEquals(superhero, fromSuperDao);
        assertNotNull(fromSuperDao);

        List<Super> supers = new ArrayList<>();
        supers.add(superhero);

        Organization org = new Organization();
        org.setName("The Avengers");
        org.setDescription("Best group of heroes.");
        org.setAddress("Avengers Tower, New York");
        org.setContactInfo("avengersoffice@marvel.com");
        org.setType("Hero");

        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);

        org.setSupers(supers);
        org = this.orgDao.addOrganization(org);

        Organization fromOrgDao = this.orgDao.getOrganizationByID(org.getOrganizationID());
        assertEquals(org, fromOrgDao);
        assertNotNull(org);

        org.setName("Arkham Asylum");
        org.setDescription("This is where the bad guys get locked up.");
        org.setAddress("Gotham City");
        org.setContactInfo("arkhamasylum@dc.com");
        org.setType("Villain");
        this.orgDao.updateOrganization(org);
        assertNotEquals(org, fromOrgDao);

        fromOrgDao = this.orgDao.getOrganizationByID(org.getOrganizationID());
        assertEquals(org, fromOrgDao);
    }

    /**
     * Test of deleteOrganizationByID method, of class OrganizationDaoDB.
     */
    @Test
    public void testDeleteOrganizationByID() {
        Power power = new Power();
        power.setName("Super human");
        power.setDescription("Enhanced human abilities.");
        power = this.powerDao.addPower(power);

        Power fromPowerDao = this.powerDao.getPowerByID(power.getPowerID());
        assertEquals(power, fromPowerDao);
        assertNotNull(fromPowerDao);

        List<Power> powers = new ArrayList<>();
        powers.add(power);

        Super superhero = new Super();
        superhero.setPower(power);
        superhero.setType("Hero");
        superhero.setName("Captain America");
        superhero.setDescription("Super soldier");
        superhero.setOrganization(new ArrayList<Organization>());
        superhero = this.superDao.addSuper(superhero);

        Super fromSuperDao = this.superDao.getSuperByID(superhero.getSuperID());
        assertEquals(superhero, fromSuperDao);
        assertNotNull(fromSuperDao);

        List<Super> supers = new ArrayList<>();
        supers.add(superhero);

        Organization org = new Organization();
        org.setName("The Avengers");
        org.setDescription("Best group of heroes.");
        org.setAddress("Avengers Tower, New York");
        org.setContactInfo("avengersoffice@marvel.com");
        org.setType("Hero");

        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);

        org.setSupers(supers);
        org = this.orgDao.addOrganization(org);

        Organization fromOrgDao = this.orgDao.getOrganizationByID(org.getOrganizationID());
        assertEquals(org, fromOrgDao);
        assertNotNull(org);

        // Delete organization 
        this.orgDao.deleteOrganizationByID(org.getOrganizationID());
        fromOrgDao = orgDao.getOrganizationByID(org.getOrganizationID());
        assertNull(fromOrgDao);
    }

    /**
     * Test of getOrganizationsForSuper method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetOrganizationsForSuper() {
        // Super 1
        Power power = new Power();
        power.setName("Super human");
        power.setDescription("Enhanced human abilities.");
        power = this.powerDao.addPower(power);

        Super superhero = new Super();
        superhero.setPower(power);
        superhero.setType("Hero");
        superhero.setName("Captain America");
        superhero.setDescription("Super soldier");
        superhero.setOrganization(new ArrayList<Organization>());
        superhero = this.superDao.addSuper(superhero);

        List<Super> supers = new ArrayList<>();
        supers.add(superhero);

        // Organization 1
        Organization org = new Organization();
        org.setName("The Avengers");
        org.setDescription("Best group of heroes.");
        org.setAddress("Avengers Tower, New York");
        org.setContactInfo("avengersoffice@marvel.com");
        org.setType("Hero");
        org.setSupers(supers);
        org = this.orgDao.addOrganization(org);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(org);

        // Organization 2
        Organization org2 = new Organization();
        org2.setName("Test 2");
        org2.setDescription("Test description 2");
        org2.setAddress("Test address");
        org2.setContactInfo("test@email.com");
        org2.setType("Villain");
        org2.setSupers(supers);
        org2 = this.orgDao.addOrganization(org2);

        organizations.add(org2);

        superhero.setOrganization(organizations);
        this.superDao.updateSuper(superhero);
        
        // Test
        List<Organization> orgs = this.orgDao.getOrganizationsForSuper(superhero);
        assertEquals(2, orgs.size());
        
        assertEquals("The Avengers", orgs.get(0).getName());
        assertEquals("Hero", orgs.get(0).getType());
        
        assertEquals("Test 2", orgs.get(1).getName());
        assertEquals("Villain", orgs.get(1).getType());
    }
}
