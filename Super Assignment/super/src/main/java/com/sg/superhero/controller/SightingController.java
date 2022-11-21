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
public class SightingController {

    @Autowired
    SuperService service;

    Set<ConstraintViolation<Sighting>> violations = new HashSet<>();
    Set<ConstraintViolation<Sighting>> violationsEdit = new HashSet<>();

    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Super> supers = this.service.getAllSupers();
        List<Sighting> sightings = this.service.getAllSightings();
        List<Location> locations = this.service.getAllLocations();
        LocalDate now = LocalDate.now();

        model.addAttribute("supers", supers);
        model.addAttribute("sightings", sightings);
        model.addAttribute("locations", locations);
        model.addAttribute("now", now);
        return "sightings";
    }

    @GetMapping("addSighting")
    public String displayAddSuper(Model model) {

        violations.clear();
        violationsEdit.clear();
        
        List<Super> supers = this.service.getAllSupers();
        List<Sighting> sightings = this.service.getAllSightings();
        List<Location> locations = this.service.getAllLocations();
        LocalDate now = LocalDate.now();

        model.addAttribute("supers", supers);
        model.addAttribute("sightings", sightings);
        model.addAttribute("locations", locations);
        model.addAttribute("now", now);
        model.addAttribute("errors", violations);

        return "addSighting";
    }

    @PostMapping("addSighting")
    public String addSighting(Sighting sighting, HttpServletRequest request, Model model) {

        String locationID = request.getParameter("locationID");
        sighting.setLocation(this.service.getLocationByID(Integer.parseInt(locationID)));

        String superID = request.getParameter("superID");
        sighting.setSuperhero(this.service.getSuperByID(Integer.parseInt(superID)));

        String date = request.getParameter("date");
        LocalDate sightingDate = LocalDate.parse(date);
        String description = request.getParameter("sightingDescription");

        sighting.setDate(sightingDate);
        sighting.setDescription(description);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(sighting);

        List<Location> locations = this.service.getAllLocations();
        List<Super> supers = this.service.getAllSupers();
        LocalDate now = LocalDate.now();
        
        if (violations.isEmpty()) {
            this.service.addSighting(sighting);
        } else {
            model.addAttribute("locations", locations);
            model.addAttribute("supers", supers);
            model.addAttribute("sighting", sighting);
            model.addAttribute("now", now);
            model.addAttribute("errors", violations);
            return "addSighting";
        }

        return "redirect:/sightings";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(HttpServletRequest request) {
        int sightingID = Integer.parseInt(request.getParameter("sightingID"));
        this.service.deleteSightingByID(sightingID);

        return "redirect:/sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(HttpServletRequest request, Model model) {
        violations.clear();
        violationsEdit.clear();
        
        int sightingID = Integer.parseInt(request.getParameter("sightingID"));
        Sighting sighting = this.service.getSightingByID(sightingID);
        List<Location> locations = this.service.getAllLocations();
        List<Super> supers = this.service.getAllSupers();

        model.addAttribute("errors", violations);
        model.addAttribute("sighting", sighting);
        model.addAttribute("locations", locations);
        model.addAttribute("supers", supers);

        return "editSighting";
    }

    @PostMapping("editSighting")
    public String performEditSighting(HttpServletRequest request, Model model) {
        int sightingID = Integer.parseInt(request.getParameter("sightingID"));
        Sighting sighting = this.service.getSightingByID(sightingID);

        String date = request.getParameter("date");
        LocalDate sightingDate = LocalDate.parse(date);

        int locationID = Integer.parseInt(request.getParameter("locationID"));
        sighting.setLocation(this.service.getLocationByID(locationID));

        int superID = Integer.parseInt(request.getParameter("superID"));
        sighting.setSuperhero(this.service.getSuperByID(superID));

        String description = request.getParameter("sightingDescription");

        sighting.setDate(sightingDate);
        sighting.setDescription(description);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violationsEdit = validate.validate(sighting);

        List<Location> locations = this.service.getAllLocations();
        List<Super> supers = this.service.getAllSupers();

        if (violationsEdit.isEmpty()) {
            this.service.updateSighting(sighting);
        } else {
            sighting = this.service.getSightingByID(sighting.getSightingID());
            model.addAttribute("errors", violationsEdit);
            model.addAttribute("sighting", sighting);
            model.addAttribute("locations", locations);
            model.addAttribute("supers", supers);
            return "editSighting";
        }

        return "redirect:/detailsSighting?sightingID=" + sighting.getSightingID();

    }

    @GetMapping("detailsSighting")
    public String displayDetailsSighting(Integer sightingID, Model model) {

        Sighting sighting = this.service.getSightingByID(sightingID);
        model.addAttribute("sighting", sighting);

        return "detailsSighting";
    }

    @GetMapping("sightingsByDate")
    public String displaySightingsByDate(String date, Model model) {
        LocalDate sightingDate = LocalDate.parse(date);
        List<Sighting> sightings = this.service.getSightingsByDate(sightingDate);
        List<Location> locations = this.service.getAllLocations();
        List<Super> supers = this.service.getAllSupers();
        LocalDate now = LocalDate.now();

        model.addAttribute("sightings", sightings);
        model.addAttribute("locations", locations);
        model.addAttribute("supers", supers);
        model.addAttribute("now", now);
        return "sightings";

    }
}
