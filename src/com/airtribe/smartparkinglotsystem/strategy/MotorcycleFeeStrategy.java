package com.airtribe.smartparkinglotsystem.strategy;

public class MotorcycleFeeStrategy implements FeeStrategy{

    private static final double RATE = 25;

    @Override
    public double calculateFee(long hours) {
        return hours * RATE;
    }
}
