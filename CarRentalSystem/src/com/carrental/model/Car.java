package com.carrental.model;

/**
 * Represents a Car.
 * Demonstrates: INHERITANCE — extends Vehicle and reuses its fields/methods.
 *               POLYMORPHISM — overrides getVehicleType() and calculateRentalCost().
 */
public class Car extends Vehicle {

    private int numSeats;
    private String transmission; // "Manual" or "Automatic"

    public Car(String vehicleId, String brand, String model, int year,
               double ratePerDay, int numSeats, String transmission) {
        super(vehicleId, brand, model, year, ratePerDay);
        this.numSeats    = numSeats;
        this.transmission = transmission;
    }

    @Override
    public String getVehicleType() {
        return "CAR";
    }

    /**
     * Cars get a 10% discount for rentals longer than 7 days.
     */
    @Override
    public double calculateRentalCost(int days) {
        double total = getRatePerDay() * days;
        if (days > 7) total *= 0.90;
        return total;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + String.format(" | %d seats | %s", numSeats, transmission);
    }

    public int    getNumSeats()     { return numSeats; }
    public String getTransmission() { return transmission; }
}
