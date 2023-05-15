/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.entities;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Teresa
 */
public class Organization {

    private int organizationID;

    @NotBlank(message = "Name must not be blank.")
    @Size(min = 1, max = 50, message = "Name must be between 1-50 characters.")
    private String name;

    @NotBlank(message = "Description must not be blank.")
    @Size(min = 1, max = 255, message = "Descripition must be between 1-255 characters.")
    private String description;

    @NotBlank(message = "Address must not be blank.")
    @Size(min = 1, max = 255, message = "Address must be between 1-255 characters.")
    private String address;
    
    @Size(max = 100, message = "Contact Info. must be less than 100 characters." )
    private String contactInfo;

    @NotBlank(message = "You must choose a type.")
    private String type;
    
    private List<Super> supers;

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Super> getSupers() {
        return supers;
    }

    public void setSupers(List<Super> supers) {
        this.supers = supers;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.organizationID;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.description);
        hash = 17 * hash + Objects.hashCode(this.address);
        hash = 17 * hash + Objects.hashCode(this.contactInfo);
        hash = 17 * hash + Objects.hashCode(this.type);
        hash = 17 * hash + Objects.hashCode(this.supers);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organization other = (Organization) obj;
        if (this.organizationID != other.organizationID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.contactInfo, other.contactInfo)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.supers, other.supers)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Organization{" + "organizationID=" + organizationID + ", name=" + name + ", description=" + description + ", address=" + address + ", contactInfo=" + contactInfo + ", type=" + type + ", supers=" + supers + '}';
    }

}
