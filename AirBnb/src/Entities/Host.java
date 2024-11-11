package Entities;

public class Host extends User {
    private double hostRating;

    public Host(int userID, String name, String email, String phone, double hostRating) {
        super(userID, name, email, phone);
        this.hostRating = hostRating;
    }

    public void listProperty() {
    }

    public void viewBookings() {
    }

    public double getHostRating() {
        return hostRating;
    }

    public void setHostRating(double hostRating) {
        this.hostRating = hostRating;
    }
}