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

//        Power superpower = new Power();
//        superpower.setName("Water blast");
//        superpower.setDescription("Blasts water at high speed.");
//        superpower = this.superpowerDao.addSuperpower(superpower);
//
//        Power fromPowerDao = this.superpowerDao.getSuperpowerByID(superpower.getSuperpowerID());
//        assertEquals(superpower, fromPowerDao);
//        assertNotNull(fromPowerDao);
//
//        List<Superpower> superpowers = new ArrayList<>();
//        superpowers.add(superpower);
//
//        Super superhero = new Super();
//        superhero.setSuperpower(superpower);
//        superhero.setType("Hero");
//        superhero.setName("Captain America");
//        superhero.setDescription("Super soldier");
////        superhero.setOrganization(orgs);
//        superhero = this.superDao.addSuper(superhero);
//
//        Super fromSuperDao = this.superDao.getSuperByID(superhero.getSuperID());
//        assertEquals(superhero, fromSuperDao);
//        assertNotNull(fromSuperDao);
//
//        List<Super> supers = new ArrayList<>();
//        supers.add(superhero);

        Organization org = new Organization();
        org.setName("The Avengers");
        org.setDescription("Best group of heroes.");
        org.setAddress("Avengers Tower, New York");
        org.setContactInfo("avengersoffice@marvel.com");
        org.setType("Hero");

//        List<Organization> orgs = new ArrayList<>();
//        orgs.add(org);

        org.setSupers(new ArrayList<Super>());
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
