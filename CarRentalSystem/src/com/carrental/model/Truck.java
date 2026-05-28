package com.carrental.model;

/**
 * Represents a Truck.
 * Demonstrates: INHERITANCE + POLYMORPHISM with payload-based pricing.
 */
public class Truck extends Vehicle {

    private double payloadCapacityTons;
    private boolean hasLiftgate;

    public Truck(String vehicleId, String brand, String model, int year,
                 double ratePerDay, double payloadCapacityTons, boolean hasLiftgate) {
        super(vehicleId, brand, model, year, ratePerDay);
        this.payloadCapacityTons = payloadCapacityTons;
        this.hasLiftgate         = hasLiftgate;
    }

    @Override
    public String getVehicleType() {
        return "TRUCK";
    }

    /**
     * Trucks charge an extra $15/day for payload > 2 tons.
     */
    @Override
    public double calculateRentalCost(int days) {
        double base = getRatePerDay() * days;
        if (payloadCapacityTons > 2.0) base += 15.0 * days;
        return base;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + String.format(" | Payload: %.1f tons | Liftgate: %s",
                payloadCapacityTons, hasLiftgate ? "Yes" : "No");
    }

    public double  getPayloadCapacityTons() { return payloadCapacityTons; }
    public boolean isHasLiftgate()          { return hasLiftgate; }
}
