package com.carrental.service;

import com.carrental.exception.RecordNotFoundException;
import com.carrental.exception.VehicleNotAvailableException;
import com.carrental.model.*;

import java.util.List;

/**
 * Interface defining rental operations.
 * Demonstrates: ABSTRACTION via interface — callers depend on contract, not implementation.
 */
public interface RentalService {

    void         addVehicle(Vehicle vehicle);
    void         addCustomer(Customer customer);
    RentalRecord rentVehicle(String customerId, String vehicleId, int days)
                     throws VehicleNotAvailableException, RecordNotFoundException;
    void         returnVehicle(String rentalId)
                     throws RecordNotFoundException;
    List<Vehicle>      getAvailableVehicles();
    List<RentalRecord> getAllRentals();
    List<RentalRecord> getRentalsByCustomer(String customerId);
    void         printReceipt(String rentalId) throws RecordNotFoundException;
}
