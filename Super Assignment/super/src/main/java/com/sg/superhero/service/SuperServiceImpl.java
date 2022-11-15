/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.dao.PowerDao;
import com.sg.superhero.dao.SightingDao;
import com.sg.superhero.dao.SuperDao;
import com.sg.superhero.entities.Location;
import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.Power;
import com.sg.superhero.entities.Sighting;
import com.sg.superhero.entities.Super;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Teresa
 */
@Service
public class SuperServiceImpl implements SuperService{

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
    
    
//    Power
    @Override
    public Power getPowerByID(int powerID) {
        return this.powerDao.getPowerByID(powerID);
    }

    @Override
    public List<Power> getAllPowers() {
        return this.powerDao.getAllPowers();
    }

    @Override
    public Power addPower(Power power) {
       return this.powerDao.addPower(power);
    }

    @Override
    public void updatePower(Power power) {
        this.powerDao.updatePower(power);
    }

    @Override
    public void deletePowerByID(int powerID) {
        this.powerDao.deletePowerByID(powerID);
    }
    
//    Super
    @Override
    public Super getSuperByID(int superID) {
        return this.superDao.getSuperByID(superID);
    }

    @Override
    public List<Super> getAllSupers() {
        return this.superDao.getAllSupers();
    }

    @Override
    public Super addSuper(Super superhero) {
        return this.superDao.addSuper(superhero);
    }

    @Override
    public void updateSuper(Super superhero) {
        this.superDao.updateSuper(superhero);
    }

    @Override
    public void deleteSuperByID(int superID) {
        this.superDao.deleteSuperByID(superID);
    }

    @Override
    public List<Super> getSupersForLocation(Location location) {
        return this.superDao.getSupersForLocation(location);
    }

    @Override
    public List<Super> getSupersForOrganization(Organization organization) {
        return this.superDao.getSupersForOrganization(organization);
    }

//    Organization
    @Override
    public Organization getOrganizationByID(int organizationID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> getAllOrganizations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organization addOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOrganizationByID(int organizationID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> getOrganizationsForSuper(Super superhero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//    Location
    @Override
    public Location getLocationByID(int locationID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> getAllLocations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Location addLocation(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateLocation(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteLocationByID(int locationID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> getLocationsForSuper(Super superhero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    Sighting
    @Override
    public Sighting getSightingByID(int sightingID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getAllSightings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSighting(Sighting sighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSightingByID(int sightingID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getSightingsForLocation(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
