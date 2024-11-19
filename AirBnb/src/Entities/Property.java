package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private List<Review> reviews; // List to store reviews for this property

    public Property(int propertyID, String address, double pricePerNight, String description, Location location,
                    List<Integer> amenityIDs, CancellationPolicy cancellationPolicy, int hostID) {
        this.propertyID = propertyID;
        this.hostID = hostID;
        this.address = address;
        this.pricePerNight = pricePerNight;
        this.description = description;
        this.location = location;
        this.amenityIDs = new ArrayList<>(amenityIDs); // Ensure amenityIDs is mutable
        this.cancellationPolicy = cancellationPolicy;
        this.reviews = new ArrayList<>(); // Initialize the reviews list
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

    /**
     * Adds a review to the property.
     *
     * @param review the review to add
     */
    public void addReview(Review review) {
        this.reviews.add(review);
    }

    /**
     * Sorts the reviews based on the given criteria.
     *
     * @param criteria the criteria to sort by ("rating" or "date")
     */
    public void sortReviews(String criteria) {
        switch (criteria.toLowerCase()) {
            case "rating" -> reviews.sort(Comparator.comparingDouble(Review::getRating).reversed());
            case "date" -> reviews.sort(Comparator.comparing(Review::getDate).reversed());
            default -> throw new IllegalArgumentException("Invalid sorting criteria. Use 'rating' or 'date'.");
        }
    }

    /**
     * Gets the sorted list of reviews.
     *
     * @return the list of reviews
     */
    public List<Review> getReviews() {
        return new ArrayList<>(reviews);
    }
}
