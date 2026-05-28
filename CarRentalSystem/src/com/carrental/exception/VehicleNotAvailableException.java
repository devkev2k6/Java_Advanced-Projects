package com.carrental.exception;

public class VehicleNotAvailableException extends Exception {
    public VehicleNotAvailableException(String vehicleId) {
        super("Vehicle '" + vehicleId + "' is not available for rental.");
    }
}
