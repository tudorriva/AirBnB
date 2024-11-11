public class Guest extends User {
    private double guestRating;

    public Guest(int userID, String name, String email, String phone, double guestRating) {
        super(userID, name, email, phone);
        this.guestRating = guestRating;
    }

    public void bookProperty() {
    }

    public void leaveReview() {
    }

    public double getGuestRating() {
        return guestRating;
    }

    public void setGuestRating(double guestRating) {
        this.guestRating = guestRating;
    }
}
