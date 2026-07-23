package com.airtribe.smartparkinglotsystem.service;

import com.airtribe.smartparkinglotsystem.entities.ParkingLot;
import com.airtribe.smartparkinglotsystem.entities.ParkingTicket;
import com.airtribe.smartparkinglotsystem.entities.Vehicle;
import com.airtribe.smartparkinglotsystem.exception.ParkingFullException;
import com.airtribe.smartparkinglotsystem.repository.ParkingTicketRepository;
import com.airtribe.smartparkinglotsystem.util.IdGenerator;

public class CheckInService {

    private final ParkingLotAllocationService allocationService;

    private final ParkingTicketRepository parkingTicketRepo;

    public CheckInService(ParkingLotAllocationService allocationService,
                          ParkingTicketRepository parkingTicketRepo) {
        this.allocationService = allocationService;
        this.parkingTicketRepo = parkingTicketRepo;
    }

    public ParkingTicket checkIn(Vehicle vehicle){
        ParkingLot lot = allocationService.allocateParkingLot(vehicle.getVehicleType());

        if (lot == null){
            throw new ParkingFullException("Parking Lot Full");
        }
        ParkingTicket ticket = new ParkingTicket(IdGenerator.generateTicketId(), vehicle, lot);

        parkingTicketRepo.save(ticket);
        return ticket;
    }
}
