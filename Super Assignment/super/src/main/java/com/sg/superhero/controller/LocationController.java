/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.controller;

import com.sg.superhero.entities.Location;
import com.sg.superhero.service.SuperService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Teresa
 */
@Controller
public class LocationController {

    @Autowired
    SuperService service;

    @GetMapping("locations")
    public String displayPowers(Model model) {
        List<Location> locations = this.service.getAllLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }

}
