package com.carrental;

import com.carrental.exception.RecordNotFoundException;
import com.carrental.exception.VehicleNotAvailableException;
import com.carrental.model.*;
import com.carrental.service.RentalService;
import com.carrental.service.RentalServiceImpl;

/**
 * Entry point for the Car Rental System.
 *
 * OOP Concepts demonstrated:
 *  - Abstraction    : Vehicle (abstract class), RentalService (interface)
 *  - Encapsulation  : All model fields private with getters/setters
 *  - Inheritance    : Car, SUV, Truck extend Vehicle
 *  - Polymorphism   : calculateRentalCost() behaves differently per vehicle type
 */
public class Main {

    public static void main(String[] args) {

        RentalService rentalService = new RentalServiceImpl();

        // ── 1. Add Vehicles ──────────────────────────────────────────────
        System.out.println("\n========== ADDING VEHICLES ==========");

        // POLYMORPHISM: each Vehicle reference points to a different subclass
        Vehicle car1   = new Car  ("V001", "Toyota",  "Camry",    2022, 55.0,  5, "Automatic");
        Vehicle car2   = new Car  ("V002", "Honda",   "Civic",    2021, 45.0,  5, "Manual");
        Vehicle suv1   = new SUV  ("V003", "Ford",    "Explorer", 2023, 80.0,  true,  450);
        Vehicle suv2   = new SUV  ("V004", "Hyundai", "Creta",    2022, 70.0,  false, 400);
        Vehicle truck1 = new Truck("V005", "Tata",    "T16",      2020, 90.0,  2.5,   true);

        rentalService.addVehicle(car1);
        rentalService.addVehicle(car2);
        rentalService.addVehicle(suv1);
        rentalService.addVehicle(suv2);
        rentalService.addVehicle(truck1);

        // ── 2. Register Customers ─────────────────────────────────────────
        System.out.println("\n========== REGISTERING CUSTOMERS ==========");

        Customer c1 = new Customer("C001", "Arjun Sharma",  "arjun@email.com",  "DL-4521");
        Customer c2 = new Customer("C002", "Priya Mehta",   "priya@email.com",  "DL-7832");
        Customer c3 = new Customer("C003", "Rahul Verma",   "rahul@email.com",  "DL-3301");

        rentalService.addCustomer(c1);
        rentalService.addCustomer(c2);
        rentalService.addCustomer(c3);

        // ── 3. Show Available Vehicles ────────────────────────────────────
        System.out.println("\n========== AVAILABLE VEHICLES ==========");
        rentalService.getAvailableVehicles()
                .forEach(v -> System.out.println("  " + v.getDetails()));

        // ── 4. Rent Vehicles ──────────────────────────────────────────────
        System.out.println("\n========== RENTING VEHICLES ==========");
        RentalRecord rental1 = null, rental2 = null, rental3 = null;

        try {
            rental1 = rentalService.rentVehicle("C001", "V001", 5);   // Car, 5 days
            rental2 = rentalService.rentVehicle("C002", "V003", 10);  // SUV, 10 days
            rental3 = rentalService.rentVehicle("C003", "V005", 3);   // Truck, 3 days
        } catch (VehicleNotAvailableException | RecordNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // ── 5. Try Renting an Already-Rented Vehicle (Error Handling) ────
        System.out.println("\n========== ATTEMPTING DOUBLE RENTAL (expected error) ==========");
        try {
            rentalService.rentVehicle("C002", "V001", 2); // V001 is already rented
        } catch (VehicleNotAvailableException e) {
            System.out.println("✖ Caught: " + e.getMessage());
        } catch (RecordNotFoundException e) {
            System.out.println("✖ Caught: " + e.getMessage());
        }

        // ── 6. Print Receipts ─────────────────────────────────────────────
        System.out.println("\n========== RECEIPTS ==========");
        try {
            if (rental1 != null) rentalService.printReceipt(rental1.getRentalId());
            if (rental2 != null) rentalService.printReceipt(rental2.getRentalId());
        } catch (RecordNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // ── 7. Return a Vehicle ───────────────────────────────────────────
        System.out.println("\n========== RETURNING VEHICLE ==========");
        try {
            if (rental1 != null) rentalService.returnVehicle(rental1.getRentalId());
        } catch (RecordNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // ── 8. Available Vehicles After Returns ───────────────────────────
        System.out.println("\n========== AVAILABLE VEHICLES AFTER RETURN ==========");
        rentalService.getAvailableVehicles()
                .forEach(v -> System.out.println("  " + v.getDetails()));

        // ── 9. All Rental History ─────────────────────────────────────────
        System.out.println("\n========== ALL RENTAL RECORDS ==========");
        rentalService.getAllRentals()
                .forEach(r -> System.out.println("  " + r));

        // ── 10. Rentals by Customer ───────────────────────────────────────
        System.out.println("\n========== RENTALS FOR CUSTOMER C002 ==========");
        rentalService.getRentalsByCustomer("C002")
                .forEach(r -> System.out.println("  " + r));

        System.out.println("\n========== DEMO COMPLETE ==========\n");
    }
}
