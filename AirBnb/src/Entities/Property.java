package Entities;

import Repository.HasId;

public class Property implements HasId {
    private int propertyID;
    private String address;
    private double pricePerNight;
    private String description;

    public Property(int propertyID, String address, double pricePerNight, String description) {
        this.propertyID = propertyID;
        this.address = address;
        this.pricePerNight = pricePerNight;
        this.description = description;
    }

    public void addReview() {
    }

    public boolean checkAvailability() {
        return true;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getId() {
        return propertyID;
    }

    @Override
    public void setId(int id) {
        this.propertyID = id;
    }
}