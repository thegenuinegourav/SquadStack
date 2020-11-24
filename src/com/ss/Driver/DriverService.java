package com.ss.Driver;

import com.ss.Vehicle.Vehicle;

// Created Interface to make sure all services are loosely coupled with each other.
// All outsiders will contact this service via this interface only
// Achieved Runtime Polymorphism
public interface DriverService {
    Driver addDriver(Integer age, Vehicle vehicle);
    Driver getDriver(Integer age);
    void printAllVehicles(Integer age);
}
