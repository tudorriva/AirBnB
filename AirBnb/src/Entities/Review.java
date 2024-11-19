package Entities;

import java.util.Date;

public class Review implements HasId {
    private int reviewID;
    private int guestID;
    private int propertyID;
    private double rating; // Used for sorting by rating
    private String comment;
    private Date date; // Used for sorting by date

    public Review(int reviewID, int guestID, int propertyID, double rating, String comment, Date date) {
        this.reviewID = reviewID;
        this.guestID = guestID;
        this.propertyID = propertyID;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public void addRating(double newRating) {
        this.rating = newRating;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public double getRating() { // Getter for rating (used in sorting)
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() { // Getter for date (used in sorting)
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int getId() {
        return reviewID;
    }

    @Override
    public void setId(int id) {
        this.reviewID = id;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", guestID=" + guestID +
                ", propertyID=" + propertyID +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }

    public int getPropertyID() {
        return propertyID;
    }
}
