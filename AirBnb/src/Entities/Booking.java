package Entities;

import java.util.Date;

public class Booking implements HasId, Payable{
    private int bookingID;
    private Date checkInDate;
    private Date checkOutDate;
    private double totalPrice;
    private int guestID;
    private int propertyID;
    private Payment payment;

    public int getGuestID() {
        return guestID;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalPrice=" + totalPrice +
                ", guestID=" + guestID +
                ", propertyID=" + propertyID +
                ", payment=" + payment +
                '}';
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Booking(int bookingID, Date checkOutDate, Date checkInDate, double totalPrice, int guestID, int propertyID, Payment payment) {
        this.bookingID = bookingID;
        this.checkOutDate = checkOutDate;
        this.checkInDate = checkInDate;
        this.totalPrice = totalPrice;
        this.guestID = guestID;
        this.propertyID = propertyID;
        this.payment = payment;
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

    @Override
    public int getId() {
        return bookingID;
    }

    @Override
    public void setId(int id) {
        this.bookingID = id;
    }

    @Override
    public void processPayment() {
        // Logic to process payment *We'll do it in the future*
    }

    public int getPropertyId() {
        return propertyID;
    }
}