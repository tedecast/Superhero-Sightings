/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.controller;

import com.sg.superhero.entities.Location;
import com.sg.superhero.entities.Sighting;
import com.sg.superhero.entities.Super;
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
public class SightingController {

    @Autowired
    SuperService service;

    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Super> supers = this.service.getAllSupers();
        List<Sighting> sightings = this.service.getAllSightings();
        List<Location> locations = this.service.getAllLocations();
        
        model.addAttribute("supers", supers);
        model.addAttribute("sightings", sightings);
        model.addAttribute("locations", locations);
        return "sightings";
    }

}
