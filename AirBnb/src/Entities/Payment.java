package Entities;

import Repository.HasId;

import java.util.Date;

public class Payment implements HasId {
    private int paymentID;
    private double amount;
    private Date date;

    public Payment(int paymentID, double amount, Date date) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.date = date;
    }

    public void processPayment() {
        // Logic to process payment *We'll do it in the future*
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
}

    @Override
    public int getId() {
        return paymentID;
    }

    @Override
    public void setId(int id) {
        this.paymentID = id;
    }
}