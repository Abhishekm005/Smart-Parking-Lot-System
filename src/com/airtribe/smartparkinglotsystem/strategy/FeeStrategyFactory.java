package com.airtribe.smartparkinglotsystem.strategy;

import com.airtribe.smartparkinglotsystem.entities.VehicleType;

public final class FeeStrategyFactory {

    private FeeStrategyFactory() {
    }

    public static FeeStrategy getFeeStrategy(VehicleType vehicleType) {
        if (vehicleType == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null");
        }

        switch (vehicleType) {
            case CAR:
                return new CarFeeStrategy();
            case BUS:
                return new BusFeeStrategy();
            case MOTORCYCLE:
                return new MotorcycleFeeStrategy();
            default:
                throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType);
        }
    }
}
