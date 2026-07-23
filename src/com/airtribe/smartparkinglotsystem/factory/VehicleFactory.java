package com.airtribe.smartparkinglotsystem.factory;

import com.airtribe.smartparkinglotsystem.entities.*;
import com.airtribe.smartparkinglotsystem.exception.InvalidVehicleException;
import com.airtribe.smartparkinglotsystem.util.IdGenerator;

public class VehicleFactory {

    private VehicleFactory() {}

    public static Vehicle createVehicle(String vehicleNumber, String ownerName, VehicleType vehicleType) {
        long vehicleId = IdGenerator.generateVehicleId();

        switch(vehicleType) {
            case CAR:
                return new Car(vehicleId, vehicleType, vehicleNumber, ownerName);

            case MOTORCYCLE:
                return new Motorcycle(vehicleId, vehicleType, vehicleNumber, ownerName);

            case BUS:
                return new Bus(vehicleId, vehicleType, vehicleNumber, ownerName);

            default:
                throw new InvalidVehicleException("Invalid vehicle type");
        }
    }
}
