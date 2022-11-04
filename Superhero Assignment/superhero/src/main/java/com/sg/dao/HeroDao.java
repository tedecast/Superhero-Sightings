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
public interface HeroDao {

    public Super getHeroByID(int heroID);

    public List<Super> getAllHeroes();

    public Super addHero(Super hero);

    public void updateHero(Super hero);

    public void deleteHeroByID(int heroID);

    // Added in to access other objects 
    public List<Super> getHeroesForSighting(Sighting sighting);

    public List<Super> getHeroesForSuperpower(Superpower superpower);

    public List<Super> getHeroesForLocation(Location location);

    public List<Super> getHeroesForOrganization(Organization organization);

}
