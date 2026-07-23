package com.airtribe.smartparkinglotsystem.strategy;

public class BusFeeStrategy implements FeeStrategy{

    private static final double RATE = 100;

    @Override
    public double calculateFee(long hours) {
        return hours * RATE;
    }
}
