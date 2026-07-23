package com.airtribe.smartparkinglotsystem.service;

import com.airtribe.smartparkinglotsystem.entities.ParkingTicket;
import com.airtribe.smartparkinglotsystem.entities.TicketStatus;
import com.airtribe.smartparkinglotsystem.exception.VehicleNotFoundException;
import com.airtribe.smartparkinglotsystem.repository.ParkingTicketRepository;

import java.time.LocalDateTime;

public class CheckOutService {

    private final ParkingLotAllocationService allocationService;
    private final ParkingTicketRepository parkingTicketRepo;
    private final FeeCalculationService feeCalculationService;

    public CheckOutService(ParkingLotAllocationService allocationService,
                           ParkingTicketRepository parkingTicketRepo,
                           FeeCalculationService feeCalculationService) {
        this.allocationService = allocationService;
        this.parkingTicketRepo = parkingTicketRepo;
        this.feeCalculationService = feeCalculationService;
    }

    public ParkingTicket checkout(String vehicleNumber) {

        ParkingTicket ticket = parkingTicketRepo.getActiveTicket(vehicleNumber);
        if (ticket == null) {
            throw new VehicleNotFoundException("Vehicle number " + vehicleNumber + " not found");
        }

        ticket.setExitTime(LocalDateTime.now());

        double fee = feeCalculationService.calculateFee(ticket);

        ticket.setParkingFee(fee);

        ticket.setStatus(TicketStatus.PAID);
        allocationService.releaseParkingLot(ticket.getParkingLot());
        return ticket;
    }

}
