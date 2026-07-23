package com.airtribe.smartparkinglotsystem.util;

public class IdGenerator {

    private static long  vehicleId = 1;
    private static long ticketId = 1;
    private static long paymentId = 1;
    private static long parkingLotId = 1;

    private IdGenerator() {

    }

    public static long generateVehicleId() {
        return vehicleId++;
    }

    public static long generateTicketId() {
        return ticketId++;
    }

    public static long generatePaymentId() {
        return paymentId++;
    }
    public static long generateParkingLotId() {
        return parkingLotId++;
    }
}
