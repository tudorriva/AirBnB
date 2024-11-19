package Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Property implements HasId, Bookable {
    private int propertyID;
    private int hostID;
    private String address;
    private double pricePerNight;
    private String description;
    private Location location;
    private List<Integer> amenityIDs;
    private CancellationPolicy cancellationPolicy;

    public Property(int propertyID, String address, double pricePerNight, String description, Location location, List<Integer> amenityIDs, CancellationPolicy cancellationPolicy, int hostID) {
        this.propertyID = propertyID;
        this.hostID = hostID;
        this.address = address;
        this.pricePerNight = pricePerNight;
        this.description = description;
        this.location = location;
        this.amenityIDs = new ArrayList<>(amenityIDs); // Ensure amenityIDs is mutable
        this.cancellationPolicy = cancellationPolicy;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Integer> getAmenityIDs() {
        return amenityIDs;
    }

    public void setAmenityIDs(List<Integer> amenityIDs) {
        this.amenityIDs = new ArrayList<>(amenityIDs); // Ensure amenityIDs is mutable
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

    @Override
    public boolean checkAvailability(Date checkIn, Date checkOut) {
        return false;
    }

    @Override
    public int getId() {
        return propertyID;
    }

    @Override
    public void setId(int id) {
        this.propertyID = propertyID;
    }
}