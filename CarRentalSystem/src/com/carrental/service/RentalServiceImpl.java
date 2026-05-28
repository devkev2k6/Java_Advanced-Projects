package com.carrental.service;

import com.carrental.exception.RecordNotFoundException;
import com.carrental.exception.VehicleNotAvailableException;
import com.carrental.model.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Concrete implementation of RentalService.
 * Demonstrates: POLYMORPHISM — works with any Vehicle subtype through the parent reference.
 */
public class RentalServiceImpl implements RentalService {

    // Using Map for O(1) lookups — good practice to highlight in interviews
    private Map<String, Vehicle>      vehicles      = new LinkedHashMap<>();
    private Map<String, Customer>     customers     = new LinkedHashMap<>();
    private Map<String, RentalRecord> rentalRecords = new LinkedHashMap<>();

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getVehicleId(), vehicle);
        System.out.println("✔ Vehicle added: " + vehicle.getDetails());
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
        System.out.println("✔ Customer registered: " + customer);
    }

    @Override
    public RentalRecord rentVehicle(String customerId, String vehicleId, int days)
            throws VehicleNotAvailableException, RecordNotFoundException {

        Customer customer = customers.get(customerId);
        if (customer == null)
            throw new RecordNotFoundException("Customer not found: " + customerId);

        Vehicle vehicle = vehicles.get(vehicleId);
        if (vehicle == null)
            throw new RecordNotFoundException("Vehicle not found: " + vehicleId);

        if (!vehicle.isAvailable())
            throw new VehicleNotAvailableException(vehicleId);

        // Mark vehicle as rented
        vehicle.setAvailable(false);

        // Create record
        RentalRecord record = new RentalRecord(customer, vehicle, days);
        rentalRecords.put(record.getRentalId(), record);
        customer.incrementRentals();

        System.out.println("\n✔ Rental confirmed! " + record);
        return record;
    }

    @Override
    public void returnVehicle(String rentalId) throws RecordNotFoundException {
        RentalRecord record = rentalRecords.get(rentalId);
        if (record == null)
            throw new RecordNotFoundException("Rental record not found: " + rentalId);

        if (record.getStatus() != RentalRecord.Status.ACTIVE)
            throw new RecordNotFoundException("Rental " + rentalId + " is already closed.");

        record.getVehicle().setAvailable(true);
        record.markReturned();
        System.out.println("\n✔ Vehicle returned. Record updated: " + record);
    }

    @Override
    public List<Vehicle> getAvailableVehicles() {
        return vehicles.values().stream()
                .filter(Vehicle::isAvailable)
                .collect(Collectors.toList());
    }

    @Override
    public List<RentalRecord> getAllRentals() {
        return new ArrayList<>(rentalRecords.values());
    }

    @Override
    public List<RentalRecord> getRentalsByCustomer(String customerId) {
        return rentalRecords.values().stream()
                .filter(r -> r.getCustomer().getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    @Override
    public void printReceipt(String rentalId) throws RecordNotFoundException {
        RentalRecord r = rentalRecords.get(rentalId);
        if (r == null)
            throw new RecordNotFoundException("Rental not found: " + rentalId);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("              CAR RENTAL SYSTEM — RECEIPT");
        System.out.println("=".repeat(60));
        System.out.printf("  Rental ID   : %s%n", r.getRentalId());
        System.out.printf("  Customer    : %s (ID: %s)%n",
                r.getCustomer().getName(), r.getCustomer().getCustomerId());
        System.out.printf("  Vehicle     : %s %s (%s)%n",
                r.getVehicle().getBrand(), r.getVehicle().getModel(),
                r.getVehicle().getVehicleType());
        System.out.printf("  Vehicle ID  : %s%n", r.getVehicle().getVehicleId());
        System.out.printf("  Rental Date : %s%n", r.getRentalDate());
        System.out.printf("  Return Date : %s%n", r.getReturnDate());
        System.out.printf("  Days        : %d%n", r.getRentalDays());
        System.out.printf("  Rate/Day    : $%.2f%n", r.getVehicle().getRatePerDay());
        System.out.printf("  Total Cost  : $%.2f%n", r.getTotalCost());
        System.out.printf("  Status      : %s%n", r.getStatus());
        System.out.println("=".repeat(60) + "\n");
    }
}
