package com.airtribe.smartparkinglotsystem.entities;

public class ParkingLot {

    private long parkingLotId;
    private String floorName;
    private ParkingLotType  parkingLotType;
    private boolean available;

    public ParkingLot(long parkingLotId,
                      String floorName,
                      ParkingLotType parkingLotType) {

        this.parkingLotId = parkingLotId;
        this.floorName = floorName;
        this.parkingLotType = parkingLotType;
        this.available = true;
    }

    public long getParkingLotId() {
        return parkingLotId;
    }


    public String getFloorName() {
        return floorName;
    }

    public ParkingLotType getParkingLotType() {
        return parkingLotType;
    }

    public synchronized boolean isAvailable() {
        return available;
    }

    public synchronized boolean occupyIfAvailable() {
        if(available) {
            available = false;
            return true;
        }
        return false;
    }

    public synchronized void releaseParkingLot() {
        this.available = true;
    }

    @Override
    public String toString() {
        return "Parking Lot Id: " + parkingLotId
                + ", Floor Name: " + floorName
                + ", Parking Lot Type: " + parkingLotType;
    }


}
