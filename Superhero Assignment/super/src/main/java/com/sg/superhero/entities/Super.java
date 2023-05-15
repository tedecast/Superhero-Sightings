/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.entities;

import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Teresa
 */
public class Super {

    private int superID;
    
    @NotNull(message="You must choose a power.")
    private Power power;
    
    @Size(min = 1, message="Super must belong to at least 1 organization.")
    private List<Organization> organization;
    
    @NotBlank(message = "You must choose a type.")
    @Size(max = 10, message = "Type must be fewer than 10 characters")
    private String type;
    
    @NotBlank(message = "Super Name must not be blank.")
    @Size(min = 1, max = 50, message = "Super Name must be between 1-50 characters")
    private String name;
    
    @NotBlank(message = "Description must not be blank.")
    @Size(min = 1, max = 255, message = "Description must be between 1-255 characters")
    private String description;

    public int getSuperID() {
        return superID;
    }

    public void setSuperID(int superID) {
        this.superID = superID;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public List<Organization> getOrganization() {
        return organization;
    }

    public void setOrganization(List<Organization> organization) {
        this.organization = organization;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.superID;
        hash = 37 * hash + Objects.hashCode(this.power);
        hash = 37 * hash + Objects.hashCode(this.organization);
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.description);
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
        final Super other = (Super) obj;
        if (this.superID != other.superID) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.power, other.power)) {
            return false;
        }
        if (!Objects.equals(this.organization, other.organization)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Super{" + "superID=" + superID + ", power=" + power + ", organization=" + organization + ", type=" + type + ", name=" + name + ", description=" + description + '}';
    }
  
}
