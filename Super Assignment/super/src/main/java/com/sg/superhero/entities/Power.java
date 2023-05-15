/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.entities;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Teresa
 */
public class Power {

    private int PowerID;

    @NotBlank(message = "Power Name must not be blank.")
    @Size(min = 1, max = 50, message = "Power Name must be betwen 1-50 characters.")
    private String name;
    
    @NotBlank(message = "Power Description must not be blank.")
    @Size(min = 1, max = 255, message = "Power Description must be between 1-255 characters.")
    private String description;

    public Power() {
    }

    public int getPowerID() {
        return PowerID;
    }

    public void setPowerID(int PowerID) {
        this.PowerID = PowerID;
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
        int hash = 7;
        hash = 29 * hash + this.PowerID;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.description);
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
        final Power other = (Power) obj;
        if (this.PowerID != other.PowerID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Power{" + "PowerID=" + PowerID + ", name=" + name + ", description=" + description + '}';
    }

}
