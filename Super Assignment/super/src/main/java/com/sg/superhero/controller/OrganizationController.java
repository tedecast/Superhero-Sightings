/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.controller;

import com.sg.superhero.entities.Organization;
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
public class OrganizationController {

    @Autowired
    SuperService service;

    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> orgs = this.service.getAllOrganizations();
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

        this.service.addOrganization(org);

        return "redirect:/organizations";
    }

    @GetMapping("deleteOrg")
    public String deleteOrganization (HttpServletRequest request) {
        int orgID = Integer.parseInt(request.getParameter("organizationID"));
        this.service.deleteOrganizationByID(orgID);
        
        return "redirect:/organizations";
    }
    
    @GetMapping("editOrg")
    public String editPower(HttpServletRequest request, Model model) {
        int orgID = Integer.parseInt(request.getParameter("organizationID"));
        Organization org = this.service.getOrganizationByID(orgID);

        model.addAttribute("organization", org);
        return "editOrg";
    }
    
    @PostMapping("editOrg")
    public String performEditOrganization (HttpServletRequest request) {
        int orgID = Integer.parseInt(request.getParameter("organizationID"));
        Organization org = this.service.getOrganizationByID(orgID);

        org.setName(request.getParameter("orgName"));
        org.setDescription(request.getParameter("orgDescription"));
        org.setAddress(request.getParameter("orgAddress"));
        org.setContactInfo(request.getParameter("contactInfo"));
        org.setType(request.getParameter("orgType"));

        this.service.updateOrganization(org);

        return "redirect:/organizations";
    }
    
    @GetMapping("detailsOrg")
    public String displayDetailsOrganization (HttpServletRequest request, Model model){
        int orgID = Integer.parseInt(request.getParameter("organizationID"));
        
        Organization organization = this.service.getOrganizationByID(orgID);
        model.addAttribute("organization", organization);
        
        return "detailsOrg";
    }
}
