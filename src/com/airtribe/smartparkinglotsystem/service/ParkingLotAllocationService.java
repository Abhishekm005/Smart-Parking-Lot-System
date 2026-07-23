package com.airtribe.smartparkinglotsystem.service;

import com.airtribe.smartparkinglotsystem.entities.ParkingFloor;
import com.airtribe.smartparkinglotsystem.entities.ParkingLot;
import com.airtribe.smartparkinglotsystem.entities.ParkingLotType;
import com.airtribe.smartparkinglotsystem.entities.VehicleType;
import com.airtribe.smartparkinglotsystem.repository.ParkingFloorRepository;
import com.airtribe.smartparkinglotsystem.repository.ParkingLotRepository;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotAllocationService {

    private final ParkingFloorRepository parkingFloorRepo;

    public ParkingLotAllocationService(ParkingFloorRepository parkingFloorRepo) {
        this.parkingFloorRepo = parkingFloorRepo;
    }

    public ParkingLot allocateParkingLot(VehicleType vehicleType ){
        ParkingLotType lotType = ParkingLotType.valueOf(vehicleType.name());
        List<ParkingFloor> floors = parkingFloorRepo.getFloors();
        for(ParkingFloor floor : floors){
            List<ParkingLot> lots = floor.getParkingLots();
            synchronized (lots) {
                for(ParkingLot lot : lots){
                    if(lot.getParkingLotType() == (lotType) && lot.occupyIfAvailable()){
                        return lot;
                    }
                }
            }
        }
        return null;
    }

    public ParkingLot allocateParkingLotOnFloor(VehicleType vehicleType, int floorId){
        ParkingLotType lotType = ParkingLotType.valueOf(vehicleType.name());
        ParkingFloor floor = parkingFloorRepo.getFloorbyID(floorId);
        if(floor == null) return null;
        List<ParkingLot> lots = floor.getParkingLots();
        synchronized (lots) {
            for(ParkingLot lot : lots){
                if(lot.getParkingLotType() == (lotType) && lot.occupyIfAvailable()){
                    return lot;
                }
            }
        }
        return null;
    }

    public void releaseParkingLot(ParkingLot parkingLot){
        parkingLot.releaseParkingLot();
    }
}
