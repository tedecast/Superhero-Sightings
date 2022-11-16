/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.controller;

import com.sg.superhero.entities.Location;
import com.sg.superhero.service.SuperService;
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
public class LocationController {

    @Autowired
    SuperService service;

    @GetMapping("locations")
    public String displayPowers(Model model) {
        List<Location> locations = this.service.getAllLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }
    
    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request) {

        String locationName = request.getParameter("locationName");
        String locationDescription = request.getParameter("locationDescription");
        String locationAddress = request.getParameter("locationAddress");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");

        Location location = new Location();
        location.setName(locationName);
        location.setDescription(locationDescription);
        location.setAddress(locationAddress);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        this.service.addLocation(location);

        return "redirect:/locations";
    }

}
