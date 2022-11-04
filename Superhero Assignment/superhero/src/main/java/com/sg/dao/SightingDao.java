/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.entities.Location;
import com.sg.entities.Sighting;
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

}
