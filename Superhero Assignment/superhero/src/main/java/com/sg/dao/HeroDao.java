/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.entities.Hero;
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
}
