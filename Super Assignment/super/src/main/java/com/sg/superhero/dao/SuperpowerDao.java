/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.entities.Power;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface SuperpowerDao {

    public Power getSuperpowerByID(int superpowerID);

    public List<Power> getAllSuperpowers();

    public Power addSuperpower(Power superpower);

    public void updateSuperpower(Power superpower);

    public void deleteSuperpowerByID(int superpowerID);
}
