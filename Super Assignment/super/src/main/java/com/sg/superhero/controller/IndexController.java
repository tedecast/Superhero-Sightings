/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.controller;

import com.sg.superhero.entities.Sighting;
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
public class IndexController {
    
    @Autowired
    SuperService service;
    
    @GetMapping("/")
    public String index(Model model){
        List<Sighting> sightings = this.service.getAllSightings();
        if(sightings.size() > 10){
            sightings = sightings.subList(0, 10);
        }
        model.addAttribute("sightings", sightings);
        return "index";
    }
    
}
