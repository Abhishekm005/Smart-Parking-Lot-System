package com.airtribe.smartparkinglotsystem.strategy;

public class CarFeeStrategy implements FeeStrategy {

    private static final double RATE = 50;

    @Override
    public double calculateFee(long hours) {
        return hours * RATE;
    }
}
