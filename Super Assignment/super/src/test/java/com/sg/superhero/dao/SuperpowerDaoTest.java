/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.entities.Super;
import com.sg.superhero.entities.Superpower;
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
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Super> supers = this.superDao.getAllSupers();
        for (Super superhero : supers) {
            this.superDao.deleteSuperByID(superhero.getSuperID());
        }
        
        List<Superpower> superpowers = this.superpowerDao.getAllSuperpowers();
        for (Superpower superpower : superpowers){
            this.superpowerDao.deleteSuperpowerByID(superpower.getSuperpowerID());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getSuperpowerByID method, of class SuperpowerDao.
     */
    @Test
    public void testGetSuperpowerByID() {
    }

    /**
     * Test of getAllSuperpowers method, of class SuperpowerDao.
     */
    @Test
    public void testGetAllSuperpowers() {
    }

    /**
     * Test of addSuperpower method, of class SuperpowerDao.
     */
    @Test
    public void testAddSuperpower() {
    }

    /**
     * Test of updateSuperpower method, of class SuperpowerDao.
     */
    @Test
    public void testUpdateSuperpower() {
    }

    /**
     * Test of deleteSuperpowerByID method, of class SuperpowerDao.
     */
    @Test
    public void testDeleteSuperpowerByID() {
    }

    public class SuperpowerDaoImpl implements SuperpowerDao {

        public Superpower getSuperpowerByID(int superpowerID) {
            return null;
        }

        public List<Superpower> getAllSuperpowers() {
            return null;
        }

        public Superpower addSuperpower(Superpower superpower) {
            return null;
        }

        public void updateSuperpower(Superpower superpower) {
        }

        public void deleteSuperpowerByID(int superpowerID) {
        }
    }
    
}
