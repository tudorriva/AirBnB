package Entities;

import Repository.HasId;

public class Location implements HasId {
    private int locationID;
    private String city;
    private String country;

    public Location(int locationID, String city, String country) {
        this.locationID = locationID;
        this.city = city;
        this.country = country;
    }

    public void getPropertiesByLocation() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    @Override
    public int getId() {
        return locationID;
    }

    @Override
    public void setId(int id) {
        this.locationID = id;
    }
}
