package com.airtribe.smartparkinglotsystem.entities;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {

    private int floorId;
    private String floorName;

    private List<ParkingLot>  parkingLots;

    public ParkingFloor(int floorId, String floorName) {
        this.floorId = floorId;
        this.floorName = floorName;
        this.parkingLots = new ArrayList<>();
    }

    public int getFloorId() {
        return floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    @Override
    public String toString() {
        return "Floor Id: " + floorId + ", Floor Name: " + floorName;
    }
}
