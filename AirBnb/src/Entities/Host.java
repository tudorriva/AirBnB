package Entities;

public class Host extends User implements HasId {
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

    @Override
    public int getId() {
        return userID;
    }

    @Override
    public void setId(int id) {
        this.userID = id;
    }

    @Override
    public String toString() {
        return "Host{" +
                "hostRating=" + hostRating +
                ", name='" + name + '\'' +
                ", userID=" + userID +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}