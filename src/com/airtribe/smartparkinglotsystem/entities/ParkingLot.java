package com.airtribe.smartparkinglotsystem.entities;

import java.time.LocalDateTime;

public class ParkingLot {

    private long parkingLotId;
    private String parkingFloorNumber;
    private ParkingLotType  parkingLotType;
    private boolean available;

    public ParkingLot(long parkingLotId,
                      String parkingFloorNumber,
                      ParkingLotType parkingLotType) {

        this.parkingLotId = parkingLotId;
        this.parkingFloorNumber = parkingFloorNumber;
        this.parkingLotType = parkingLotType;
        this.available = true;
    }

    public long getParkingLotId() {
        return parkingLotId;
    }


    public String getParkingFloorNumber() {
        return parkingFloorNumber;
    }

    public ParkingLotType getParkingLotType() {
        return parkingLotType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void occupyParkingLot() {
        this.available = false;
    }

    public void releaseParkingLot() {
        this.available = true;
    }

    @Override
    public String toString() {
        return "Parking Lot Id: " + parkingLotId
                + ", Parking Floor Number: " + parkingFloorNumber
                + ", Parking Lot Type: " + parkingLotType;
    }


}
