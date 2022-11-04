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

    public int getSuperpowerID() {
        return superpowerID;
    }

    public void setSuperpowerID(int superpowerID) {
        this.superpowerID = superpowerID;
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
        int hash = 7;
        hash = 97 * hash + this.superID;
        hash = 97 * hash + this.superpowerID;
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.descrption);
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
        if (this.superpowerID != other.superpowerID) {
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
        return true;
    }

    @Override
    public String toString() {
        return "Super{" + "superID=" + superID + ", superpowerID=" + superpowerID + ", type=" + type + ", name=" + name + ", descrption=" + descrption + '}';
    }

    
}
