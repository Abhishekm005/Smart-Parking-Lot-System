package com.airtribe.smartparkinglotsystem.service;

import com.airtribe.smartparkinglotsystem.entities.ParkingTicket;
import com.airtribe.smartparkinglotsystem.entities.Payment;
import com.airtribe.smartparkinglotsystem.repository.PaymentRepository;
import com.airtribe.smartparkinglotsystem.util.IdGenerator;

public class PaymentService {

    private final PaymentRepository paymentRepo;

    public PaymentService(PaymentRepository paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    public Payment makePayment(ParkingTicket ticket) {

        Payment payment = new Payment(IdGenerator.generatePaymentId(),
                ticket, ticket.getParkingFee());

        paymentRepo.save(payment);
        return payment;
    }
}
