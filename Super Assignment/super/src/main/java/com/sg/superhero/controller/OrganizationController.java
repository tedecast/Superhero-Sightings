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

    @PostMapping("addOrganization")
    public String addStudent(HttpServletRequest request) {

        String orgName = request.getParameter("orgName");
        String orgDescription = request.getParameter("orgDescription");
        String orgAddress = request.getParameter("orgAddress");
        String contactInfo = request.getParameter("contactInfo");
        String orgType = request.getParameter("orgType");

        Organization org = new Organization();
        org.setName(orgName);
        org.setDescription(orgDescription);
        org.setAddress(orgAddress);
        org.setContactInfo(contactInfo);
        org.setType(orgType);

        this.orgDao.addOrganization(org);

        return "redirect:/organizations";
    }

    @GetMapping("deleteOrg")
    public String deleteOrganization (HttpServletRequest request) {
        int orgID = Integer.parseInt(request.getParameter("organizationID"));
        this.orgDao.deleteOrganizationByID(orgID);
        
        return "redirect:/organizations";
    }
    
    @GetMapping("editOrg")
    public String editPower(HttpServletRequest request, Model model) {
        int orgID = Integer.parseInt(request.getParameter("organizationID"));
        Organization org = this.orgDao.getOrganizationByID(orgID);

        model.addAttribute("organization", org);
        return "editOrg";
    }
}
