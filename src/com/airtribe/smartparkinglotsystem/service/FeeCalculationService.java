package com.airtribe.smartparkinglotsystem.service;

import com.airtribe.smartparkinglotsystem.entities.ParkingTicket;
import com.airtribe.smartparkinglotsystem.factory.FeeStrategyFactory;
import com.airtribe.smartparkinglotsystem.strategy.FeeStrategy;

import java.time.Duration;

public class FeeCalculationService {

    public double calculateFee(ParkingTicket  parkingTicket) {

        long hours = Duration.between(parkingTicket.getEntryTime(),
                parkingTicket.getExitTime()).toHours();

        if (hours == 0) {
            hours = 1;
        }
        FeeStrategy strategy = FeeStrategyFactory.getFeeStrategy(
                parkingTicket.getVehicle().getVehicleType());

        return strategy.calculateFee(hours);
    }
}
