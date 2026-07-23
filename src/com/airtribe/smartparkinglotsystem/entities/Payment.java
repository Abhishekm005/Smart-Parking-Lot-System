package com.airtribe.smartparkinglotsystem.entities;

import java.time.LocalDateTime;

public class Payment {

    private Long paymentId;
    private ParkingTicket ticket;
    private double price;
    private LocalDateTime paymentTime;

    public Payment(Long paymentId, ParkingTicket ticket, double price) {

        this.paymentId = paymentId;
        this.ticket = ticket;
        this.price = price;
        this.paymentTime = LocalDateTime.now();
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public double getPrice() {
        return price;
    }

    public ParkingTicket getTicket() {
        return ticket;
    }

    @Override
    public String toString() {
        return "Payment Id: " + paymentId + ", Ticket: " + ticket + ", Price: " + price;
    }
}
