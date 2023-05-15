/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.controller;

import com.sg.superhero.entities.Power;
import com.sg.superhero.entities.Super;
import com.sg.superhero.service.SuperService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    Set<ConstraintViolation<Power>> violations = new HashSet<>();
    Set<ConstraintViolation<Power>> violationsEdit = new HashSet<>();

    @GetMapping("powers")
    public String displayPowers(Model model) {
        List<Power> powers = this.service.getAllPowers();
        model.addAttribute("powers", powers);
        model.addAttribute("errors", violations);
        return "powers";
    }

    @GetMapping("addPower")
    public String displayAddPowers(Model model) {
        violations.clear();
        violationsEdit.clear();
        model.addAttribute("errors", violations);

        return "addPower";
    }

    @PostMapping("addPower")
    public String addPower(HttpServletRequest request, Model model) {
        
        String powerName = request.getParameter("powerName");
        String powerDescription = request.getParameter("powerDescription");

        Power power = new Power();
        power.setName(powerName);
        power.setDescription(powerDescription);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(power);

        if (violations.isEmpty()) {
            this.service.addPower(power);
        } else {
            model.addAttribute("errors", violations);
            model.addAttribute("power", power);
            return "addPower";
        }

        model.addAttribute("errors", violations);

        return "redirect:/powers";
    }

    @GetMapping("deletePower")
    public String deletePower(HttpServletRequest request) {
        int powerID = Integer.parseInt(request.getParameter("powerID"));
        this.service.deletePowerByID(powerID);

        return "redirect:/powers";
    }

    @GetMapping("editPower")
    public String editPower(Integer powerID, Model model) {
        violationsEdit.clear();
        violations.clear();
        Power power = this.service.getPowerByID(powerID);
        model.addAttribute("errors", violationsEdit);
        model.addAttribute("power", power);
        return "editPower";
    }

    @PostMapping("editPower")
    public String performEditPower(@Valid Power power, BindingResult result, HttpServletRequest request, Model model) {

        int powerID = Integer.parseInt(request.getParameter("powerID"));
        power = this.service.getPowerByID(powerID);

        power.setName(request.getParameter("powerName"));
        power.setDescription(request.getParameter("powerDescription"));
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violationsEdit = validate.validate(power);

        if (violationsEdit.isEmpty()) {
            this.service.updatePower(power);
        } else {
            power = this.service.getPowerByID(power.getPowerID());
            model.addAttribute("errors", violationsEdit);
            model.addAttribute("power", power);
            return "editPower";
        }

        this.service.updatePower(power);

        return "redirect:/detailsPower?powerID=" + power.getPowerID();
    }

    @GetMapping("detailsPower")
    public String displayDetailsPower(Integer powerID, Model model) {
        Power power = this.service.getPowerByID(powerID);
        model.addAttribute("power", power);

        List<Super> supers = this.service.getSupersForPower(power);
        model.addAttribute("supers", supers);

        return "detailsPower";
    }
}
