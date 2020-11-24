package com.ss.Vehicle;

// Vehicle entity
public class Vehicle {
    // assuming vehicleNumber to be primary key
    private String vehicleNumber;

    // system is flexible enough to store other information for Vehicle entity such as follows
    private String model;
    private String make;
    private int year;
    private int numWheels;
    private int mileage;
    // other details of vehicle, etc

    public Vehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    // getter and setters to achieve encapsulation & data abstraction
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
