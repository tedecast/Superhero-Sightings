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
public interface PowerDao {

    public Power getPowerByID(int powerID);

    public List<Power> getAllPowers();

    public Power addPower(Power power);

    public void updatePower(Power power);

    public void deletePowerByID(int powerID);
}
