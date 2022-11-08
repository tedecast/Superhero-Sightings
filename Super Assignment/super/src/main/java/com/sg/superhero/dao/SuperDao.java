/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.entities.Super;
import com.sg.superhero.entities.Location;
import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.Sighting;
import com.sg.superhero.entities.Power;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface SuperDao {

    public Super getSuperByID(int superID);

    public List<Super> getAllSupers();

    public Super addSuper(Super superhero);

    public void updateSuper(Super superhero);

    public void deleteSuperByID(int superID);

    // Added in to access other objects 
    public List<Super> getSupersForSighting(Sighting sighting);
    
    // optional, select statements 
    public List<Super> getSupersForPower(Power superpower);

    public List<Super> getSupersForLocation(Location location);

    public List<Super> getSupersForOrganization(Organization organization);

}
