/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.controller;

import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.dao.PowerDao;
import com.sg.superhero.dao.SightingDao;
import com.sg.superhero.dao.SuperDao;
import com.sg.superhero.entities.Organization;
import com.sg.superhero.entities.Power;
import com.sg.superhero.entities.Super;
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
    SuperDao superDao;

    @Autowired
    PowerDao powerDao;

    @Autowired
    OrganizationDao orgDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingDao sightingDao;

    @GetMapping("supers")
    public String displaySupers(Model model) {
        List<Super> supers = this.superDao.getAllSupers();
        List<Power> powers = this.powerDao.getAllPowers();
        List<Organization> orgs = this.orgDao.getAllOrganizations();
        model.addAttribute("supers", supers);
        model.addAttribute("powers", powers);
        model.addAttribute("organizations", orgs);
        return "supers";
    }

    @PostMapping("addSuper")
    public String addSuper(Super superhero, HttpServletRequest request) {

        String powerID = request.getParameter("powerID");
        superhero.setPower(this.powerDao.getPowerByID(Integer.parseInt(powerID)));

        String type = request.getParameter("superType");
        String superName = request.getParameter("superName");
        String superDescription = request.getParameter("superDescription");

        superhero.setType(type);
        superhero.setName(superName);
        superhero.setDescription(superDescription);

        String[] orgIDs = request.getParameterValues("organizationID");
        List<Organization> orgs = new ArrayList<>();
        for (String orgID : orgIDs) {
            orgs.add(this.orgDao.getOrganizationByID(Integer.parseInt(orgID)));
        }
        superhero.setOrganization(orgs);

        this.superDao.addSuper(superhero);

        return "redirect:/supers";
    }
}
