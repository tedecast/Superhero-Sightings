/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.controller;

import com.sg.superhero.entities.Organization;
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
public class OrganizationController {

    @Autowired
    SuperService service;

    Set<ConstraintViolation<Organization>> violations = new HashSet<>();
    Set<ConstraintViolation<Organization>> violationsEdit = new HashSet<>();

    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> orgs = this.service.getAllOrganizations();
        model.addAttribute("organizations", orgs);
        return "organizations";
    }

    @GetMapping("addOrganization")
    public String displayAddLocation(Model model) {
        violations.clear();
        violationsEdit.clear();
        model.addAttribute("errors", violations);
        return "addOrganization";
    }

    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request, Model model) {
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

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(org);

        if (violations.isEmpty()) {
            this.service.addOrganization(org);
        } else {
            model.addAttribute("errors", violations);
            model.addAttribute("organization", org);
            return "addOrganization";
        }

        model.addAttribute("errors", violations);

        return "redirect:/organizations";
    }

    @GetMapping("deleteOrg")
    public String deleteOrganization(HttpServletRequest request) {
        int orgID = Integer.parseInt(request.getParameter("organizationID"));
        this.service.deleteOrganizationByID(orgID);

        return "redirect:/organizations";
    }

    @GetMapping("editOrg")
    public String editOrganization(HttpServletRequest request, Model model) {
        violationsEdit.clear();
        violations.clear();
        int orgID = Integer.parseInt(request.getParameter("organizationID"));
        Organization org = this.service.getOrganizationByID(orgID);

        model.addAttribute("errors", violationsEdit);
        model.addAttribute("organization", org);
        return "editOrg";
    }

    @PostMapping("editOrg")
    public String performEditOrganization(HttpServletRequest request, Model model) {
        int orgID = Integer.parseInt(request.getParameter("organizationID"));
        Organization org = this.service.getOrganizationByID(orgID);

        org.setName(request.getParameter("orgName"));
        org.setDescription(request.getParameter("orgDescription"));
        org.setAddress(request.getParameter("orgAddress"));
        org.setContactInfo(request.getParameter("contactInfo"));
        org.setType(request.getParameter("orgType"));

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violationsEdit = validate.validate(org);

        if (violationsEdit.isEmpty()) {
            this.service.updateOrganization(org);
        } else {
            org = this.service.getOrganizationByID(org.getOrganizationID());
            model.addAttribute("errors", violationsEdit);
            model.addAttribute("organization", org);
            return "editOrg";
        }

        return "redirect:/detailsOrg?organizationID=" + org.getOrganizationID();
    }

    @GetMapping("detailsOrg")
    public String displayDetailsOrganization(Integer organizationID, Model model) {
        Organization organization = this.service.getOrganizationByID(organizationID);
        model.addAttribute("organization", organization);

        return "detailsOrg";
    }
}
