/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.controller;

import com.sg.superhero.entities.Power;
import com.sg.superhero.entities.Super;
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
public class PowerController {

    @Autowired
    SuperService service;

    @GetMapping("powers")
    public String displayPowers(Model model) {
        List<Power> powers = this.service.getAllPowers();
        model.addAttribute("powers", powers);
        return "powers";
    }

    @PostMapping("addPower")
    public String addPower(HttpServletRequest request) {

        String powerName = request.getParameter("powerName");
        String powerDescription = request.getParameter("powerDescription");

        Power power = new Power();
        power.setName(powerName);
        power.setDescription(powerDescription);

        this.service.addPower(power);

        return "redirect:/powers";
    }

    @GetMapping("deletePower")
    public String deletePower(HttpServletRequest request) {
        int powerID = Integer.parseInt(request.getParameter("powerID"));
        this.service.deletePowerByID(powerID);

        return "redirect:/powers";
    }

    @GetMapping("/edit/editPower")
    public String editPower(HttpServletRequest request, Model model) {
        int powerID = Integer.parseInt(request.getParameter("powerID"));
        Power power = this.service.getPowerByID(powerID);

        model.addAttribute("power", power);
        return "editPower";
    }

    @PostMapping("/edit/editPower")
    public String performEditPower (HttpServletRequest request) {
        int powerID = Integer.parseInt(request.getParameter("powerID"));
        Power power = this.service.getPowerByID(powerID);

        power.setName(request.getParameter("powerName"));
        power.setDescription(request.getParameter("powerDescription"));

        this.service.updatePower(power);

        return "redirect:/powers";
    }
    
    @GetMapping("detailsPower")
    public String displayDetailsPower (HttpServletRequest request, Model model){
        int powerID = Integer.parseInt(request.getParameter("powerID")); 
        Power power = this.service.getPowerByID(powerID);
        model.addAttribute("power", power);
        
        List<Super> supers = this.service.getSupersForPower(power);
        model.addAttribute("supers", supers);
        
        return "detailsPower";
    }
}
