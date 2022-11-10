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
    public void testGetLocationByID() {
    }

    /**
     * Test of getAllLocations method, of class LocationDaoDB.
     */
    @Test
    public void testGetAllLocations() {
    }

    /**
     * Test of addLocation method, of class LocationDaoDB.
     */
    @Test
    public void testAddLocation() {
    }

    /**
     * Test of updateLocation method, of class LocationDaoDB.
     */
    @Test
    public void testUpdateLocation() {
    }

    /**
     * Test of deleteLocationByID method, of class LocationDaoDB.
     */
    @Test
    public void testDeleteLocationByID() {
    }

    /**
     * Test of getLocationsForSuper method, of class LocationDaoDB.
     */
    @Test
    public void testGetLocationsForSuper() {
    }

}
