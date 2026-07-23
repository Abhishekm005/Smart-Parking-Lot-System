package com.airtribe.smartparkinglotsystem.repository;

import com.airtribe.smartparkinglotsystem.entities.ParkingLot;
import com.airtribe.smartparkinglotsystem.entities.ParkingLotType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParkingLotRepository {

    private final List<ParkingLot> parkingLots = new ArrayList<>();

    public void add(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public List<ParkingLot> getAllParkingLots() {
        return parkingLots;
    }

    public List<ParkingLot> getAvailableParkingLots() {
        List<ParkingLot> availableLots = new ArrayList<>();
        for (ParkingLot parkingLot : parkingLots) {
            if(parkingLot.isAvailable()) {
                availableLots.add(parkingLot);
            }
        }
        return availableLots;
    }

    public ParkingLot findAvailableLot(ParkingLotType type){
        for(ParkingLot lot : parkingLots){
            if(lot.isAvailable() && lot.getParkingLotType() == type){
                return lot;
            }
        }
        return null;
    }
}
