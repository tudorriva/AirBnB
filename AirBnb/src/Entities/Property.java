package Entities;

import Repository.HasId;

import java.util.Date;

public class Property implements HasId , Bookable{
    private int propertyID;
    private int hostID;
    private String address;
    private double pricePerNight;
    private String description;
    private Location location;
    private Amenity amenity;
    private CancellationPolicy cancellationPolicy;

    public Property(int propertyID, String address, double pricePerNight, String description, Location location, Amenity amenity, CancellationPolicy cancellationPolicy, int hostID) {
        this.propertyID = propertyID;
        this.hostID = hostID;
        this.address = address;
        this.pricePerNight = pricePerNight;
        this.description = description;
        this.location = location;
        this.amenity = amenity;
        this.cancellationPolicy = cancellationPolicy;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Amenity getAmenity() {
        return amenity;
    }

    public void setAmenity(Amenity amenity) {
        this.amenity = amenity;
    }

    public CancellationPolicy getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(CancellationPolicy cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
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

    public int getHostID() {
        return hostID;
    }

    public void setHostID(int hostID) {
        this.hostID = hostID;
    }

    @Override
    public int getId() {
        return propertyID;
    }

    @Override
    public void setId(int id) {
        this.propertyID = id;
    }

    @Override
    public boolean checkAvailability(Date checkIn, Date checkOut) {
        return false; // to be implemented
    }


}