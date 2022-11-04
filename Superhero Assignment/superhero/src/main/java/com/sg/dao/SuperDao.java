/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.entities.Super;
import com.sg.entities.Location;
import com.sg.entities.Organization;
import com.sg.entities.Sighting;
import com.sg.entities.Superpower;
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

    public void deleteHeroByID(int superID);

    // Added in to access other objects 
    public List<Super> getSupersForSighting(Sighting sighting);

    public List<Super> getSupersForSuperpower(Superpower superpower);

    public List<Super> getSupersForLocation(Location location);

    public List<Super> getSupersForOrganization(Organization organization);

}
