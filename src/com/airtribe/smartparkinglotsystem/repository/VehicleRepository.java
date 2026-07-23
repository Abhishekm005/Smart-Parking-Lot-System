package com.airtribe.smartparkinglotsystem.repository;

import com.airtribe.smartparkinglotsystem.entities.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VehicleRepository {

    private final Map<String, Vehicle> vehicles = new HashMap<>();

    public void save(Vehicle vehicle) {
        vehicles.put(vehicle.getVehicleNumber(), vehicle);
    }

    public Vehicle findByVehicleNumber(String vehicleNumber) {
        return vehicles.get(vehicleNumber);
    }

    public  Collection<Vehicle> findAll(){
        return new ArrayList<>(vehicles.values());
    }

    public boolean exists(String vehicleNumber) {
        return vehicles.containsKey(vehicleNumber);
    }

}
