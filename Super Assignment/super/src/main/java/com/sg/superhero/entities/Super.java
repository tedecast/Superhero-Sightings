/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Teresa
 */
public class Super {

    private int superID;
    private Superpower superpower;
    //private List<Sighting> sighting;
    private List<Organization> organization;
    private String type;
    private String name;
    private String descrption;

    public int getSuperID() {
        return superID;
    }

    public void setSuperID(int superID) {
        this.superID = superID;
    }

    public Superpower getSuperpower() {
        return superpower;
    }

    public void setSuperpower(Superpower superpower) {
        this.superpower = superpower;
    }

    public List<Sighting> getSighting() {
        return sighting;
    }

    public void setSighting(List<Sighting> sighting) {
        this.sighting = sighting;
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

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.superID;
        hash = 41 * hash + Objects.hashCode(this.superpower);
        hash = 41 * hash + Objects.hashCode(this.sighting);
        hash = 41 * hash + Objects.hashCode(this.organization);
        hash = 41 * hash + Objects.hashCode(this.type);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.descrption);
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
        if (!Objects.equals(this.descrption, other.descrption)) {
            return false;
        }
        if (!Objects.equals(this.superpower, other.superpower)) {
            return false;
        }
        if (!Objects.equals(this.sighting, other.sighting)) {
            return false;
        }
        if (!Objects.equals(this.organization, other.organization)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Super{" + "superID=" + superID + ", superpower=" + superpower + ", sighting=" + sighting + ", organization=" + organization + ", type=" + type + ", name=" + name + ", descrption=" + descrption + '}';
    }
    
}
