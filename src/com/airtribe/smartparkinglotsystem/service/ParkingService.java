package com.airtribe.smartparkinglotsystem.service;

import com.airtribe.smartparkinglotsystem.entities.ParkingTicket;
import com.airtribe.smartparkinglotsystem.entities.Vehicle;

public class ParkingService {

    private final CheckInService checkInService;
    private final CheckOutService checkOutService;
    private final PaymentService paymentService;

    public ParkingService(CheckInService checkInService,
                          CheckOutService checkOutService,
                          PaymentService paymentService) {
        this.checkInService = checkInService;
        this.checkOutService = checkOutService;
        this.paymentService = paymentService;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        return checkInService.checkIn(vehicle);
    }

    public ParkingTicket checkOut(String vehicleNumber) {
        ParkingTicket ticket = checkOutService.checkout(vehicleNumber);

        return paymentService.makePayment(ticket).getTicket();
    }
}