/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.entities.Super;
import com.sg.superhero.entities.Power;
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
public class PowerDaoTest {

    @Autowired
    PowerDao powerDao;

    @Autowired
    SuperDao superDao;

    @Autowired
    OrganizationDao organizationDao;

    public PowerDaoTest() {
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
    }

    @Test
    public void testAddGetPowerByID() {

        Power superpower = new Power();
        superpower.setName("Water blast");
        superpower.setDescription("Blasts water at high speed.");
        superpower = this.powerDao.addPower(superpower);

        Power fromDao = this.powerDao.getPowerByID(superpower.getPowerID());
        assertEquals(superpower, fromDao);
        assertNotNull(fromDao);
    }

    /**
     * Test of getAllPowers method, of class PowerDao.
     */
    @Test
    public void testGetAllPowers() {
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

        List<Power> powers = this.powerDao.getAllPowers();

        assertEquals(3, powers.size());
        assertTrue(powers.contains(power1));
        assertTrue(powers.contains(power2));
        assertTrue(powers.contains(power3));
    }

    /**
     * Test of updatePower method, of class PowerDao.
     */
    @Test
    public void testUpdatePower() {
        Power power = new Power();
        power.setName("Water blast");
        power.setDescription("Blasts water at high speed.");
        power = this.powerDao.addPower(power);

        Power fromDao = this.powerDao.getPowerByID(power.getPowerID());
        assertEquals(power, fromDao);
        assertNotNull(fromDao);

        power.setPowerID(power.getPowerID());
        power.setName("Super speed");
        power.setDescription("Ability to run faster than the speed of light.");

        this.powerDao.updatePower(power);
        assertNotEquals(power, fromDao);

        fromDao = this.powerDao.getPowerByID(power.getPowerID());
        assertEquals(power, fromDao);
    }

    /**
     * Test of deletePowerByID method, of class PowerDao.
     */
    @Test
    public void testDeletePowerByID() {
        Power power = new Power();
        power.setName("Water blast");
        power.setDescription("Blasts water at high speed.");
        power = this.powerDao.addPower(power);
        
        Power fromDao = this.powerDao.getPowerByID(power.getPowerID());
        assertEquals(power, fromDao);
        assertNotNull(fromDao);
        
        this.powerDao.deletePowerByID(power.getPowerID());
        
        fromDao = this.powerDao.getPowerByID(power.getPowerID());
        assertNull(fromDao);

    }

    public class PowerDaoImpl implements PowerDao {

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
