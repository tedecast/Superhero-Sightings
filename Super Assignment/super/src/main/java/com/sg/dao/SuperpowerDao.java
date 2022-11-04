/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.entities.Superpower;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface SuperpowerDao {

    public Superpower getSuperPowerByID(int superpowerID);

    public List<Superpower> getAllSuperpowers();

    public Superpower addSuperpower(Superpower superpower);

    public void updateSuperpower(Superpower superpower);

    public void deleteSuperpowerByID(int superpowerID);
}
