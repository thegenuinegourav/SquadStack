package com.ss.ParkingService;

import com.ss.Driver.Driver;
import com.ss.Vehicle.Vehicle;

// Ticket entity to encapsulate driver and vehicle required information
public class Ticket {
    private Driver driver;
    private Vehicle vehicle;

    public Ticket(Driver driver, Vehicle vehicle) {
        this.driver = driver;
        this.vehicle = vehicle;
    }

    // getter and setters to achieve encapsulation & data abstraction
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
