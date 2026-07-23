package com.airtribe.smartparkinglotsystem.entities;

public class Bus extends Vehicle {

    public Bus(long vehicleId, VehicleType vehicleType,
               String vehicleNumber, String ownerName){

        super(vehicleId, vehicleType, vehicleNumber, ownerName);
    }
}
