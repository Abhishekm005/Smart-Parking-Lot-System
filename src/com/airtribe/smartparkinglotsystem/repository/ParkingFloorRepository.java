package com.airtribe.smartparkinglotsystem.repository;

import com.airtribe.smartparkinglotsystem.entities.ParkingFloor;
import com.airtribe.smartparkinglotsystem.entities.ParkingLot;
import com.airtribe.smartparkinglotsystem.entities.ParkingLotType;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloorRepository {

    private final List<ParkingFloor> floors = new ArrayList<>();

    public void save(ParkingFloor floor) {
        synchronized (floors) {
            floors.add(floor);
        }
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public ParkingFloor getFloorbyID(int id) {
        synchronized (floors) {
            for (ParkingFloor floor : floors) {
                if(floor.getFloorId() == id)
                    return floor;
            }
        }
        return null;
    }

    public void addParkingLotToFloor(ParkingLot parkingLot, int floorId ) {
        ParkingFloor floor = getFloorbyID(floorId);
        if(floor != null){
            floor.addParkingLot(parkingLot);
        }else {
            ParkingFloor newFloor = new ParkingFloor(floorId, "Floor-" + floorId);
            newFloor.addParkingLot(parkingLot);
            save(newFloor);
        }
    }

    public List<ParkingLot> getAvailableParkingLots() {
        List<ParkingLot> availableLots = new ArrayList<>();
        List<ParkingFloor> snapshots = getFloors();
        for (ParkingFloor floor : snapshots) {
            synchronized (floor.getParkingLots()) {
                for(ParkingLot parkingLot : floor.getParkingLots()){
                    if(parkingLot.isAvailable()){
                        availableLots.add(parkingLot);
                    }
                }
            }
        }
        return availableLots;
    }

    public ParkingLot findAvailableParkingLot(ParkingLotType lotType) {
        List<ParkingFloor> snapshots = getFloors();
        for(ParkingFloor floor : snapshots){
            synchronized (floor.getParkingLots()) {
                for(ParkingLot parkingLot : floor.getParkingLots()){
                    if(parkingLot.isAvailable() && parkingLot.getParkingLotType().equals(lotType)){
                        return parkingLot;
                    }
                }
            }
        }
        return null;
    }

    public ParkingLot findAvailableLotByTypeAndFloor(ParkingLotType lotType, int floorId) {
        ParkingFloor floor = getFloorbyID(floorId);
        if(floor != null){
            synchronized (floor.getParkingLots()) {
                for(ParkingLot parkingLot : floor.getParkingLots()){
                    if(parkingLot.isAvailable() && parkingLot.getParkingLotType() == (lotType)){
                        return parkingLot;
                    }
                }
            }
        }
        return null;
    }
}
