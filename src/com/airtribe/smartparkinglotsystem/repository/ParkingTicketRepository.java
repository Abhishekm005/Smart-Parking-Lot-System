package com.airtribe.smartparkinglotsystem.repository;

import com.airtribe.smartparkinglotsystem.entities.ParkingLot;
import com.airtribe.smartparkinglotsystem.entities.ParkingTicket;
import com.airtribe.smartparkinglotsystem.entities.TicketStatus;
import com.airtribe.smartparkinglotsystem.entities.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingTicketRepository {

    private final List<ParkingTicket> tickets = new ArrayList<>();

    public void save(ParkingTicket ticket) {
        tickets.add(ticket);
    }

    public List<ParkingTicket> getTickets() {
        return tickets;
    }

    public ParkingTicket getActiveTicket(String vehicleNumber) {
        for (ParkingTicket ticket : tickets) {
            if (ticket.getVehicle().getVehicleNumber().equals(vehicleNumber)
                    && ticket.getStatus().equals(TicketStatus.ACTIVE)) {
                return ticket;
            }
        }
        return null;
    }


}
