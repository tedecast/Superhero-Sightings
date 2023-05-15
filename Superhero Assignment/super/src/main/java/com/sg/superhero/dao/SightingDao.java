/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.entities.Location;
import com.sg.superhero.entities.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface SightingDao {

    public Sighting getSightingByID(int sightingID);

    public List<Sighting> getAllSightings();

    public Sighting addSighting(Sighting sighting);

    public void updateSighting(Sighting sighting);

    public void deleteSightingByID(int sightingID);
    
    // Added in to access Location object
    public List<Sighting> getSightingsForLocation(Location location);
    
    public List<Sighting> getSightingsByDate(LocalDate date);

}
