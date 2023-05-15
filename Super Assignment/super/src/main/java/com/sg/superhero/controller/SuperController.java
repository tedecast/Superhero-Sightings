/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.controller;

import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.Power;
import com.sg.superhero.entities.Super;
import com.sg.superhero.service.SuperService;
import java.util.ArrayList;
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
public class SuperController {

    @Autowired
    SuperService service;

    Set<ConstraintViolation<Super>> violations = new HashSet<>();
    Set<ConstraintViolation<Super>> violationsEdit = new HashSet<>();

    @GetMapping("supers")
    public String displaySupers(Model model) {
        List<Super> supers = this.service.getAllSupers();
        List<Power> powers = this.service.getAllPowers();
        List<Organization> orgs = this.service.getAllOrganizations();
        model.addAttribute("supers", supers);
        model.addAttribute("powers", powers);
        model.addAttribute("organizations", orgs);
        return "supers";
    }

    @GetMapping("addSuper")
    public String displayAddsuper(Model model) {

        violations.clear();
        violationsEdit.clear();
        List<Organization> organizations = this.service.getAllOrganizations();
        List<Power> powers = this.service.getAllPowers();

        model.addAttribute("powers", powers);
        model.addAttribute("organizations", organizations);
        model.addAttribute("errors", violations);

        return "addSuper";
    }

    @PostMapping("addSuper")
    public String addSuper(Super superhero, HttpServletRequest request, Model model) {

        List<Organization> organizations = this.service.getAllOrganizations();
        List<Power> powers = this.service.getAllPowers();

        model.addAttribute("powers", powers);
        model.addAttribute("organizations", organizations);

        String powerID = request.getParameter("powerID");

        if (powerID.equals("No Power")) {
            superhero.setPower(null);
        } else {
            superhero.setPower(this.service.getPowerByID(Integer.parseInt(powerID)));
        }

        String type = request.getParameter("superType");
        String superName = request.getParameter("superName");
        String superDescription = request.getParameter("superDescription");

        superhero.setType(type);
        superhero.setName(superName);
        superhero.setDescription(superDescription);

        String[] orgIDs = request.getParameterValues("organizationID");
        List<Organization> orgs = new ArrayList<>();

        if (orgIDs != null) {
            for (String orgID : orgIDs) {
                orgs.add(this.service.getOrganizationByID(Integer.parseInt(orgID)));
            }
        }

        superhero.setOrganization(orgs);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superhero);

        if (violations.isEmpty()) {
            this.service.addSuper(superhero);
        } else {
            model.addAttribute("errors", violations);
            model.addAttribute("superhero", superhero);
            return "addSuper";
        }

        model.addAttribute("errors", violations);

        return "redirect:/supers";
    }

    @GetMapping("deleteSuper")
    public String deleteSuper(HttpServletRequest request) {
        int superID = Integer.parseInt(request.getParameter("superID"));
        this.service.deleteSuperByID(superID);

        return "redirect:/supers";
    }

    @GetMapping("editSuper")
    public String editSuper(HttpServletRequest request, Model model) {
        
        violationsEdit.clear();
        violations.clear();
        
        int superID = Integer.parseInt(request.getParameter("superID"));
        Super superhero = this.service.getSuperByID(superID);
        List<Power> powers = this.service.getAllPowers();
        List<Organization> orgs = this.service.getAllOrganizations();
        List<Organization> superOrgs = this.service.getOrganizationsForSuper(superhero);

        model.addAttribute("errors", violationsEdit);
        model.addAttribute("superhero", superhero);
        model.addAttribute("powers", powers);
        model.addAttribute("organizations", orgs);
        model.addAttribute("superOrgs", superOrgs);

        return "editSuper";
    }

    @PostMapping("editSuper")
    public String performEditSuper(HttpServletRequest request, Model model) {

        violationsEdit.clear();

        int superID = Integer.parseInt(request.getParameter("superID"));
        Super superhero = this.service.getSuperByID(superID);

        String powerID = request.getParameter("powerID");

        if (powerID.equals("No Power")) {
            superhero.setPower(null);
        } else {
            superhero.setPower(this.service.getPowerByID(Integer.parseInt(powerID)));
        }

        String type = request.getParameter("superType");
        String superName = request.getParameter("superName");
        String superDescription = request.getParameter("superDescription");

        superhero.setSuperID(superID);
        superhero.setType(type);
        superhero.setName(superName);
        superhero.setDescription(superDescription);

        String[] orgIDs = request.getParameterValues("organizationID");
        List<Organization> orgs = new ArrayList<>();
        if (orgIDs != null) {
            for (String orgID : orgIDs) {
                orgs.add(this.service.getOrganizationByID(Integer.parseInt(orgID)));
            }
        }

        superhero.setOrganization(orgs);

        List<Power> powers = this.service.getAllPowers();
        List<Organization> organizations = this.service.getAllOrganizations();
        List<Organization> superOrgs = this.service.getOrganizationsForSuper(superhero);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superhero);

        if (violations.isEmpty()) {
            this.service.updateSuper(superhero);
        } else {
            superhero = this.service.getSuperByID(superhero.getSuperID());
            model.addAttribute("errors", violations);
            model.addAttribute("superhero", superhero);
            model.addAttribute("powers", powers);
            model.addAttribute("organizations", organizations);
            model.addAttribute("superOrgs", superOrgs);
            return "editSuper";
        }
        return "redirect:/detailsSuper?superID=" + superhero.getSuperID();
    }

    @GetMapping("detailsSuper")
    public String displayDetailsSuper(Integer superID, Model model) {

        Super superhero = this.service.getSuperByID(superID);
        model.addAttribute("superhero", superhero);

        return "detailsSuper";
    }
}
