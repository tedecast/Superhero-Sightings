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
public class OrganizationController {
    
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
    
    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> orgs = this.orgDao.getAllOrganizations();
        model.addAttribute("organizations", orgs);
        return "organizations";
    }
}
