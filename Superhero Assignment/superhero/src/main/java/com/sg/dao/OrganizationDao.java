/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.entities.Super;
import com.sg.entities.Organization;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface OrganizationDao {

    public Organization getOrganizationByID(int organizationID);

    public List<Organization> getAllOrganizations();

    public Organization addOrganization(Organization organization);

    public void updateOrganization(Organization organization);

    public void deleteOrganizationByID(int organizationID);
    
    // Added in to access Super Object
    public List<Organization> getOrganizationsForHero(Super hero);

}
