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
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("addSighting")
    public String addSighting(Sighting sighting, HttpServletRequest request) {

        String locationID = request.getParameter("locationID");
        sighting.setLocation(this.service.getLocationByID(Integer.parseInt(locationID)));

        String superID = request.getParameter("superID");
        sighting.setSuperhero(this.service.getSuperByID(Integer.parseInt(superID)));

        String date = request.getParameter("date");
        LocalDate sightingDate = LocalDate.parse(date);
        String description = request.getParameter("sightingDescription");

        sighting.setDate(sightingDate);
        sighting.setDescription(description);

        this.service.addSighting(sighting);

        return "redirect:/sightings";
    }

}
