package com.carrental.model;

/**
 * Represents a customer.
 * Demonstrates: ENCAPSULATION — all fields private, accessed via getters/setters.
 */
public class Customer {

    private String customerId;
    private String name;
    private String email;
    private String licenseNumber;
    private int    totalRentals;

    public Customer(String customerId, String name, String email, String licenseNumber) {
        this.customerId     = customerId;
        this.name           = name;
        this.email          = email;
        this.licenseNumber  = licenseNumber;
        this.totalRentals   = 0;
    }

    public void incrementRentals() { this.totalRentals++; }

    public String getCustomerId()    { return customerId; }
    public String getName()          { return name; }
    public String getEmail()         { return email; }
    public String getLicenseNumber() { return licenseNumber; }
    public int    getTotalRentals()  { return totalRentals; }

    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("Customer[%s] %s | %s | License: %s | Rentals: %d",
                customerId, name, email, licenseNumber, totalRentals);
    }
}
