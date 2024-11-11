package Entities;

import java.util.Date;

public class Booking {
    private int bookingID;
    private Date checkInDate;
    private Date checkOutDate;
    private double totalPrice;

    public Booking(int bookingID, Date checkInDate, Date checkOutDate, double totalPrice) {
        this.bookingID = bookingID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
    }

    public double calculateTotal() {
        return totalPrice;
    }

    public void cancelBooking() {
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}