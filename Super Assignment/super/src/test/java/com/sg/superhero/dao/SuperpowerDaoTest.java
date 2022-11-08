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
    PowerDao powerDao;

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

        List<Power> superpowers = this.powerDao.getAllPowers();
        for (Power superpower : superpowers) {
            this.powerDao.deletePowerByID(superpower.getPowerID());
        }
    }

    /**
     * Test of getSuperpowerByID method, of class PowerDao.
     */
    @Test
    public void testAddGetSuperpowerByID() {

        Power superpower = new Power();
        superpower.setName("Water blast");
        superpower.setDescription("Blasts water at high speed.");
        superpower = this.powerDao.addPower(superpower);

        Power fromDao = this.powerDao.getPowerByID(superpower.getPowerID());
        assertEquals(superpower, fromDao);
        assertNotNull(fromDao);
    }

    /**
     * Test of getAllSuperpowers method, of class PowerDao.
     */
    @Test
    public void testGetAllSuperpowers() {
        Power power1 = new Power();
        power1.setName("Water blast");
        power1.setDescription("Blasts water at high speed.");
        power1 = this.powerDao.addPower(power1);

        Power power2 = new Power();
        power2.setName("Super speed");
        power2.setDescription("Ability to run faster than the speed of light.");
        power2 = this.powerDao.addPower(power2);

        Power power3 = new Power();
        power3.setName("Fire");
        power3.setDescription("Ability to control fire and create fire.");
        power3 = this.powerDao.addPower(power3);

        List<Power> superpowers = this.powerDao.getAllPowers();

        assertEquals(3, superpowers.size());
        assertTrue(superpowers.contains(power1));
        assertTrue(superpowers.contains(power2));
        assertTrue(superpowers.contains(power3));
    }

    /**
     * Test of updateSuperpower method, of class PowerDao.
     */
    @Test
    public void testUpdateSuperpower() {
        Power superpower = new Power();
        superpower.setName("Water blast");
        superpower.setDescription("Blasts water at high speed.");
        superpower = this.powerDao.addPower(superpower);

        Power fromDao = this.powerDao.getPowerByID(superpower.getPowerID());
        assertEquals(superpower, fromDao);
        assertNotNull(fromDao);

        superpower.setPowerID(superpower.getPowerID());
        superpower.setName("'Super speed'");
        superpower.setDescription("'Ability to run faster than the speed of light.'");

        this.powerDao.updatePower(superpower);
        assertNotEquals(superpower, fromDao);

        fromDao = this.powerDao.getPowerByID(superpower.getPowerID());
        assertEquals(superpower, fromDao);
    }

    /**
     * Test of deleteSuperpowerByID method, of class PowerDao.
     */
    @Test
    public void testDeleteSuperpowerByID() {
        Power superpower = new Power();
        superpower.setName("Water blast");
        superpower.setDescription("Blasts water at high speed.");
        superpower = this.powerDao.addPower(superpower);
        
        Power fromDao = this.powerDao.getPowerByID(superpower.getPowerID());
        assertEquals(superpower, fromDao);
        assertNotNull(fromDao);
        
        this.powerDao.deletePowerByID(superpower.getPowerID());
        
        fromDao = this.powerDao.getPowerByID(superpower.getPowerID());
        assertNull(fromDao);

    }

    public class SuperpowerDaoImpl implements PowerDao {

        public Power getPowerByID(int superpowerID) {
            return null;
        }

        public List<Power> getAllPowers() {
            return null;
        }

        public Power addPower(Power superpower) {
            return null;
        }

        public void updatePower(Power superpower) {
        }

        public void deletePowerByID(int superpowerID) {
        }
    }

}
