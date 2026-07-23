package com.airtribe.smartparkinglotsystem.entities;

import java.sql.Time;
import java.time.LocalDateTime;

public class ParkingTicket {

    private long parkingTicketID;
    private Vehicle vehicle;
    private ParkingLot parkingLot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double parkingFee;
    private TicketStatus status;

    public ParkingTicket(long parkingTicketID,
                         Vehicle vehicle,
                         ParkingLot parkingLot) {

        this.parkingTicketID = parkingTicketID;
        this.vehicle = vehicle;
        this.parkingLot = parkingLot;
        this.entryTime = LocalDateTime.now();
        this.status = TicketStatus.ACTIVE;
    }
    public long getParkingTicketID() {
        return parkingTicketID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public double getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(double parkingFee) {
        this.parkingFee = parkingFee;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return "Parking Ticket Id: " + parkingTicketID
                + ", Vehicle: " + vehicle
                + ", Parking Lot: " + parkingLot
                + ", Parking Fee: " + parkingFee;
    }
}
