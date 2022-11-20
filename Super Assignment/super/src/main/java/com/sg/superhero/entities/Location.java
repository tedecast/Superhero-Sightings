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
public class Location {

    private int locationID;

    @NotBlank(message = "Location Name must not be blank.")
    @Size(min = 1, max = 50, message = "Location Name must be between 1-50 characters.")
    private String name;

    @NotBlank(message = "Description must not be blank.")
    @Size(min = 1, max = 255, message = "Description must be between 1-255 characters.")
    private String description;

    @NotBlank(message = "Address must not be blank.")
    @Size(min = 1, max = 255, message = "Address must be between 1-255 characters.")
    private String address;

    @NotBlank(message = "Latitude must not be blank.")
    @Size(min = 1, max = 50, message = "Latitude must be between 1-50 characters.")
    private String latitude;

    @NotBlank(message = "Longitude must not be blank.")
    @Size(min = 1, max = 50, message = "Longitude must be between 1-50 characters.")
    private String longitude;

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.locationID;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.address);
        hash = 89 * hash + Objects.hashCode(this.latitude);
        hash = 89 * hash + Objects.hashCode(this.longitude);
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
        final Location other = (Location) obj;
        if (this.locationID != other.locationID) {
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
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Location{" + "locationID=" + locationID + ", name=" + name + ", description=" + description + ", address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}
