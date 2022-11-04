/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.entities.Hero;
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

    public Hero getHeroByID(int heroID);

    public List<Hero> getAllHeroes();

    public Hero addHero(Hero hero);

    public void updateHero(Hero hero);

    public void deleteHeroByID(int heroID);

    // Added in to access other objects 
    public List<Hero> getHeroesForSighting(Sighting sighting);

    public List<Hero> getHeroesForSuperpower(Superpower superpower);

    public List<Hero> getHeroesForLocation(Location location);

    public List<Hero> getHeroesForOrganization(Organization organization);

}
