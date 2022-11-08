/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.entities.Super;
import com.sg.superhero.entities.Power;
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
public class SuperpowerDaoTest {

    @Autowired
    SuperpowerDao superpowerDao;

    @Autowired
    SuperDao superDao;

    @Autowired
    OrganizationDao organizationDao;

    public SuperpowerDaoTest() {
    }

    @BeforeEach
    public void setUp() {
        List<Super> supers = this.superDao.getAllSupers();
        for (Super superhero : supers) {
            this.superDao.deleteSuperByID(superhero.getSuperID());
        }

        List<Power> superpowers = this.superpowerDao.getAllSuperpowers();
        for (Power superpower : superpowers) {
            this.superpowerDao.deleteSuperpowerByID(superpower.getPowerID());
        }
    }

    /**
     * Test of getSuperpowerByID method, of class SuperpowerDao.
     */
    @Test
    public void testAddGetSuperpowerByID() {

        Power superpower = new Power();
        superpower.setName("Water blast");
        superpower.setDescription("Blasts water at high speed.");
        superpower = this.superpowerDao.addSuperpower(superpower);

        Power fromDao = this.superpowerDao.getSuperpowerByID(superpower.getPowerID());
        assertEquals(superpower, fromDao);
        assertNotNull(fromDao);
    }

    /**
     * Test of getAllSuperpowers method, of class SuperpowerDao.
     */
    @Test
    public void testGetAllSuperpowers() {
        Power power1 = new Power();
        power1.setName("Water blast");
        power1.setDescription("Blasts water at high speed.");
        power1 = this.superpowerDao.addSuperpower(power1);

        Power power2 = new Power();
        power2.setName("Super speed");
        power2.setDescription("Ability to run faster than the speed of light.");
        power2 = this.superpowerDao.addSuperpower(power2);

        Power power3 = new Power();
        power3.setName("Fire");
        power3.setDescription("Ability to control fire and create fire.");
        power3 = this.superpowerDao.addSuperpower(power3);

        List<Power> superpowers = this.superpowerDao.getAllSuperpowers();

        assertEquals(3, superpowers.size());
        assertTrue(superpowers.contains(power1));
        assertTrue(superpowers.contains(power2));
        assertTrue(superpowers.contains(power3));
    }

    /**
     * Test of updateSuperpower method, of class SuperpowerDao.
     */
    @Test
    public void testUpdateSuperpower() {
        Power superpower = new Power();
        superpower.setName("Water blast");
        superpower.setDescription("Blasts water at high speed.");
        superpower = this.superpowerDao.addSuperpower(superpower);

        Power fromDao = this.superpowerDao.getSuperpowerByID(superpower.getPowerID());
        assertEquals(superpower, fromDao);
        assertNotNull(fromDao);

        superpower.setPowerID(superpower.getPowerID());
        superpower.setName("'Super speed'");
        superpower.setDescription("'Ability to run faster than the speed of light.'");

        this.superpowerDao.updateSuperpower(superpower);
        assertNotEquals(superpower, fromDao);

        fromDao = this.superpowerDao.getSuperpowerByID(superpower.getPowerID());
        assertEquals(superpower, fromDao);
    }

    /**
     * Test of deleteSuperpowerByID method, of class SuperpowerDao.
     */
    @Test
    public void testDeleteSuperpowerByID() {
        Power superpower = new Power();
        superpower.setName("Water blast");
        superpower.setDescription("Blasts water at high speed.");
        superpower = this.superpowerDao.addSuperpower(superpower);
        
        Power fromDao = this.superpowerDao.getSuperpowerByID(superpower.getPowerID());
        assertEquals(superpower, fromDao);
        assertNotNull(fromDao);
        
        this.superpowerDao.deleteSuperpowerByID(superpower.getPowerID());
        
        fromDao = this.superpowerDao.getSuperpowerByID(superpower.getPowerID());
        assertNull(fromDao);

    }

    public class SuperpowerDaoImpl implements SuperpowerDao {

        public Power getSuperpowerByID(int superpowerID) {
            return null;
        }

        public List<Power> getAllSuperpowers() {
            return null;
        }

        public Power addSuperpower(Power superpower) {
            return null;
        }

        public void updateSuperpower(Power superpower) {
        }

        public void deleteSuperpowerByID(int superpowerID) {
        }
    }

}
