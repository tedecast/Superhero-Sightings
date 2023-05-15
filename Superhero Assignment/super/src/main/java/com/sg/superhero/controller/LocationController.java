/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.controller;

import com.sg.superhero.entities.Location;
import com.sg.superhero.service.SuperService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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

    Set<ConstraintViolation<Location>> violations = new HashSet<>();
    Set<ConstraintViolation<Location>> violationsEdit = new HashSet<>();

    @GetMapping("locations")
    public String displayPowers(Model model) {
        List<Location> locations = this.service.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("errors", violations);
        return "locations";
    }

    @GetMapping("addLocation")
    public String displayAddLocation(Model model) {
        violations.clear();
        violationsEdit.clear();
        model.addAttribute("errors", violations);

        return "addLocation";
    }

    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request, Model model) {

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

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);

        if (violations.isEmpty()) {
            this.service.addLocation(location);
        } else {
            model.addAttribute("errors", violations);
            model.addAttribute("location", location);
            return "addLocation";
        }

        model.addAttribute("errors", violations);

        return "redirect:/locations";
    }

    @GetMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request) {
        int locationID = Integer.parseInt(request.getParameter("locationID"));
        this.service.deleteLocationByID(locationID);

        return "redirect:/locations";
    }

    @GetMapping("editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
        violationsEdit.clear();
        violations.clear();
        int locationID = Integer.parseInt(request.getParameter("locationID"));
        Location location = this.service.getLocationByID(locationID);

        model.addAttribute("errors", violationsEdit);
        model.addAttribute("location", location);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(HttpServletRequest request, Model model) {
        int locationID = Integer.parseInt(request.getParameter("locationID"));
        Location location = this.service.getLocationByID(locationID);

        String locationName = request.getParameter("locationName");
        String locationDescription = request.getParameter("locationDescription");
        String locationAddress = request.getParameter("locationAddress");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");

        location.setName(locationName);
        location.setDescription(locationDescription);
        location.setAddress(locationAddress);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);

        if (violations.isEmpty()) {
            this.service.updateLocation(location);
        } else {
            location = this.service.getLocationByID(location.getLocationID());
            model.addAttribute("errors", violations);
            model.addAttribute("location", location);
            return "editLocation";
        }

        return "redirect:/detailsLocation?locationID=" + location.getLocationID();
    }

    @GetMapping("detailsLocation")
    public String displayDetailsLocation(Integer locationID, Model model){

        Location location = this.service.getLocationByID(locationID);
        model.addAttribute("location", location);

        return "detailsLocation";
    }

}
