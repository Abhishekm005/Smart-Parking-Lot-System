package com.airtribe.smartparkinglotsystem.factory;

import com.airtribe.smartparkinglotsystem.entities.VehicleType;
import com.airtribe.smartparkinglotsystem.strategy.BusFeeStrategy;
import com.airtribe.smartparkinglotsystem.strategy.CarFeeStrategy;
import com.airtribe.smartparkinglotsystem.strategy.FeeStrategy;
import com.airtribe.smartparkinglotsystem.strategy.MotorcycleFeeStrategy;

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
