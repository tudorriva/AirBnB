package Entities;

import java.util.Date;

public class Payment implements HasId, Payable {
    private int paymentID;
    private double amount;
    private Date date;
    private boolean processed;


    public Payment(int paymentID, double amount, Date date) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.date = date;
        this.processed = false;
    }

    @Override
    public void processPayment() {
       this.processed = true;
    }

    public boolean isProcessed() {
        return processed;
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

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", amount=" + amount +
                ", date=" + date +
                ", processed=" + processed +
                '}';
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