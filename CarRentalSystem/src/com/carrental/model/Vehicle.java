package com.carrental.model;

/**
 * Abstract base class for all vehicles.
 * Demonstrates: ABSTRACTION — hides implementation details, exposes only essentials.
 */
public abstract class Vehicle {

    // ENCAPSULATION — private fields, accessed via getters/setters
    private String vehicleId;
    private String brand;
    private String model;
    private int year;
    private double ratePerDay;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String brand, String model, int year, double ratePerDay) {
        this.vehicleId  = vehicleId;
        this.brand      = brand;
        this.model      = model;
        this.year       = year;
        this.ratePerDay = ratePerDay;
        this.isAvailable = true;
    }

    // Abstract method — every subclass MUST implement its own version (Abstraction)
    public abstract String getVehicleType();

    // Abstract method — pricing logic differs by vehicle type (Polymorphism hook)
    public abstract double calculateRentalCost(int days);

    // Common display method overridden by subclasses (Polymorphism)
    public String getDetails() {
        return String.format("[%s] %s %s %s (%d) | $%.2f/day | %s",
                getVehicleType(), vehicleId, brand, model, year, ratePerDay,
                isAvailable ? "Available" : "Rented");
    }

    // --- Getters & Setters (Encapsulation) ---
    public String getVehicleId()  { return vehicleId; }
    public String getBrand()      { return brand; }
    public String getModel()      { return model; }
    public int    getYear()       { return year; }
    public double getRatePerDay() { return ratePerDay; }
    public boolean isAvailable()  { return isAvailable; }

    public void setAvailable(boolean available) { this.isAvailable = available; }
    public void setRatePerDay(double rate)       { this.ratePerDay = rate; }
}
