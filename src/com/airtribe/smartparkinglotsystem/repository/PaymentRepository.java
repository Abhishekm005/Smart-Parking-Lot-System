package com.airtribe.smartparkinglotsystem.repository;

import com.airtribe.smartparkinglotsystem.entities.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {

    private final List<Payment> payments = new ArrayList<>();

    public void save(Payment payment) {
        payments.add(payment);
    }

    public List<Payment> getPayments() {
        return payments;
    }


}
