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
public class SuperController {

    @Autowired
    SuperService service;

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

    @PostMapping("addSuper")
    public String addSuper(Super superhero, HttpServletRequest request) {

        String powerID = request.getParameter("powerID");
        superhero.setPower(this.service.getPowerByID(Integer.parseInt(powerID)));

        String type = request.getParameter("superType");
        String superName = request.getParameter("superName");
        String superDescription = request.getParameter("superDescription");

        superhero.setType(type);
        superhero.setName(superName);
        superhero.setDescription(superDescription);

        String[] orgIDs = request.getParameterValues("organizationID");
        List<Organization> orgs = new ArrayList<>();
        for (String orgID : orgIDs) {
            orgs.add(this.service.getOrganizationByID(Integer.parseInt(orgID)));
        }
        superhero.setOrganization(orgs);

        this.service.addSuper(superhero);

        return "redirect:/supers";
    }

    @GetMapping("editSuper")
    public String editSuper(HttpServletRequest request, Model model) {
        int superID = Integer.parseInt(request.getParameter("superID"));
        Super superhero = this.service.getSuperByID(superID);
        List<Power> powers = this.service.getAllPowers();
        List<Organization> orgs = this.service.getAllOrganizations();

        model.addAttribute("superhero", superhero);
        model.addAttribute("powers", powers);
        model.addAttribute("organizations", orgs);

        return "editSuper";
    }

    @PostMapping("editSuper")
    public String performEditSuper(HttpServletRequest request) {
        int superID = Integer.parseInt(request.getParameter("superID"));
        Super superhero = this.service.getSuperByID(superID);

        int powerID = Integer.parseInt(request.getParameter("powerID"));

        superhero.setPower(this.service.getPowerByID(powerID));

        String type = request.getParameter("superType");
        String superName = request.getParameter("superName");
        String superDescription = request.getParameter("superDescription");

        superhero.setSuperID(superID);
        superhero.setType(type);
        superhero.setName(superName);
        superhero.setDescription(superDescription);

//        String[] orgIDs = request.getParameterValues("organizationID");
//        List<Organization> orgs = new ArrayList<>();
//        for (String orgID : orgIDs) {
//            orgs.add(this.service.getOrganizationByID(Integer.parseInt(orgID)));
//        }
//        superhero.setOrganization(superhero.getOrganization());
        this.service.updateSuper(superhero);

        return "redirect:/supers";

    }
}
