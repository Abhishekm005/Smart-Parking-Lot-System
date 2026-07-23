package com.airtribe.smartparkinglotsystem.service;

import com.airtribe.smartparkinglotsystem.entities.ParkingLot;
import com.airtribe.smartparkinglotsystem.entities.ParkingLotType;
import com.airtribe.smartparkinglotsystem.entities.VehicleType;
import com.airtribe.smartparkinglotsystem.repository.ParkingLotRepository;

public class ParkingLotAllocationService {

    private final ParkingLotRepository parkingLotRepo;

    public ParkingLotAllocationService(ParkingLotRepository parkingLotRepo) {
        this.parkingLotRepo = parkingLotRepo;
    }

    public ParkingLot allocateParkingLot(VehicleType vehicleType ){
        ParkingLotType lotType = ParkingLotType.valueOf(vehicleType.name());

        ParkingLot lot = parkingLotRepo.findAvailableLot(lotType);
        if(lot != null){
            lot.occupyParkingLot();
        }
        return lot;
    }

    public void releaseParkingLot(ParkingLot parkingLot){
        parkingLot.releaseParkingLot();
    }
}
