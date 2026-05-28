package com.carrental.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a single rental transaction.
 */
public class RentalRecord {

    public enum Status { ACTIVE, RETURNED, CANCELLED }

    private static int counter = 1;

    private String      rentalId;
    private Customer    customer;
    private Vehicle     vehicle;
    private LocalDate   rentalDate;
    private LocalDate   returnDate;
    private int         rentalDays;
    private double      totalCost;
    private Status      status;

    public RentalRecord(Customer customer, Vehicle vehicle, int rentalDays) {
        this.rentalId   = "RNT" + String.format("%03d", counter++);
        this.customer   = customer;
        this.vehicle    = vehicle;
        this.rentalDays = rentalDays;
        this.rentalDate = LocalDate.now();
        this.returnDate = rentalDate.plusDays(rentalDays);
        this.totalCost  = vehicle.calculateRentalCost(rentalDays);
        this.status     = Status.ACTIVE;
    }

    public void markReturned() { this.status = Status.RETURNED; }
    public void markCancelled() { this.status = Status.CANCELLED; }

    public String    getRentalId()   { return rentalId; }
    public Customer  getCustomer()   { return customer; }
    public Vehicle   getVehicle()    { return vehicle; }
    public LocalDate getRentalDate() { return rentalDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public int       getRentalDays() { return rentalDays; }
    public double    getTotalCost()  { return totalCost; }
    public Status    getStatus()     { return status; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return String.format(
            "%-8s | Customer: %-15s | Vehicle: %s %s | Days: %2d | Cost: $%7.2f | %s -> %s | [%s]",
            rentalId, customer.getName(),
            vehicle.getBrand(), vehicle.getModel(),
            rentalDays, totalCost,
            rentalDate.format(fmt), returnDate.format(fmt),
            status
        );
    }
}
