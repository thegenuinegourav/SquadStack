package com.ss.ParkingService;

// Created Interface to make sure all services are loosely coupled with each other.
// All outsiders will contact this service via this interface only
// Achieved Runtime Polymorphism
public interface ParkingService {
    void setParkingService(Integer capacity);
    void park(String vehicleRegNumber, Integer age);
    void printSlotNumber(Integer driverAge);
    void printSlotNumber(String vehicleRegNumber);
    void leave(Integer slotNumber);
}
