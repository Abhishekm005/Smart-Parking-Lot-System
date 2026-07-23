package com.airtribe.smartparkinglotsystem.repository;

import com.airtribe.smartparkinglotsystem.entities.ParkingFloor;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloorRepository {

    private final List<ParkingFloor> floors = new ArrayList<>();

    public void save(ParkingFloor floor) {
        floors.add(floor);
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }
}
