/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.entities.Location;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface LocationDao {

    public Location getLocationByID(int locationID);

    public List<Location> getAllLocations();

    public Location addLocation(Location location);

    public void updateLocation(Location location);

    public void deleteLocationByID(int locationID);

}
