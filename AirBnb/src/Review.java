import java.util.Date;

public class Review {
    private int reviewID;
    private double rating;
    private String comment;
    private Date date;

    public Review(int reviewID, double rating, String comment, Date date) {
        this.reviewID = reviewID;
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

    public double getRating() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}