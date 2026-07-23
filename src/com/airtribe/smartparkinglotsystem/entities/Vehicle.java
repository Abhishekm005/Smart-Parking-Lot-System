package com.airtribe.smartparkinglotsystem.entities;

public abstract class Vehicle {

    private long vehicleId;
    private VehicleType vehicleType;
    private String vehicleNumber;
    private String ownerName;

    public Vehicle(long vehicleId,
                   VehicleType vehicleType,
                   String vehicleNumber,
                   String ownerName)
    {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public VehicleType getVehicleType() {
         return vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String toString() {
        return "Vehicle Id: " + vehicleId
                + ", Vehicle Type: " + vehicleType
                + ", Vehicle Number: " + vehicleNumber
                + ", Owner  " + ownerName;
    }
}
