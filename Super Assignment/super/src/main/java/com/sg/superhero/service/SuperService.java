/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.service;

import com.sg.superhero.entities.Location;
import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.Power;
import com.sg.superhero.entities.Sighting;
import com.sg.superhero.entities.Super;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface SuperService {

//    Power
    public Power getPowerByID(int powerID);

    public List<Power> getAllPowers();

    public Power addPower(Power power);

    public void updatePower(Power power);

    public void deletePowerByID(int powerID);

//    Super
    public Super getSuperByID(int superID);

    public List<Super> getAllSupers();

    public Super addSuper(Super superhero);

    public void updateSuper(Super superhero);

    public void deleteSuperByID(int superID);

    public List<Super> getSupersForLocation(Location location);

    public List<Super> getSupersForOrganization(Organization organization);
    
    public List<Super> getSupersForPower(Power power);

//    Organization
    public Organization getOrganizationByID(int organizationID);

    public List<Organization> getAllOrganizations();

    public Organization addOrganization(Organization organization);

    public void updateOrganization(Organization organization);

    public void deleteOrganizationByID(int organizationID);

    public List<Organization> getOrganizationsForSuper(Super superhero);

//    Location
    public Location getLocationByID(int locationID);

    public List<Location> getAllLocations();

    public Location addLocation(Location location);

    public void updateLocation(Location location);

    public void deleteLocationByID(int locationID);

    public List<Location> getLocationsForSuper(Super superhero);

//    Sighting
    public Sighting getSightingByID(int sightingID);

    public List<Sighting> getAllSightings();

    public Sighting addSighting(Sighting sighting);

    public void updateSighting(Sighting sighting);

    public void deleteSightingByID(int sightingID);

    public List<Sighting> getSightingsForLocation(Location location);

    public List<Sighting> getSightingsByDate(LocalDate date);

}
