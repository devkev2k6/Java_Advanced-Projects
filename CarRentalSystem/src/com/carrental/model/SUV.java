package com.carrental.model;

/**
 * Represents an SUV.
 * Demonstrates: INHERITANCE — extends Vehicle.
 *               POLYMORPHISM — its own pricing logic (weekend surcharge concept).
 */
public class SUV extends Vehicle {

    private boolean has4WD;
    private int luggageCapacity; // in litres

    public SUV(String vehicleId, String brand, String model, int year,
               double ratePerDay, boolean has4WD, int luggageCapacity) {
        super(vehicleId, brand, model, year, ratePerDay);
        this.has4WD          = has4WD;
        this.luggageCapacity = luggageCapacity;
    }

    @Override
    public String getVehicleType() {
        return "SUV";
    }

    /**
     * SUVs have a flat $20 insurance surcharge per rental.
     */
    @Override
    public double calculateRentalCost(int days) {
        return (getRatePerDay() * days) + 20.0;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + String.format(" | 4WD: %s | Luggage: %dL",
                has4WD ? "Yes" : "No", luggageCapacity);
    }

    public boolean isHas4WD()        { return has4WD; }
    public int     getLuggageCapacity() { return luggageCapacity; }
}
