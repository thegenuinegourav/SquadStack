package com.ss.Vehicle;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Vehicle Service Implementation to interact with Vehicle entity & operations
// Achieved Inheritance by implementing interface
public class VehicleServiceImpl implements VehicleService {
    // Using HashMap to store all vehicle data with an index on vehicle reg number (primary key)
    // Why HashMap? POST, GET, UPDATE & DELETE request on Vehicle Service will take O(1) i.e. constant time complexity.
    private HashMap<String,Vehicle> vehicles = new HashMap<>();

    // Using Singleton Pattern to make sure only one instance of each service will get create.
    private static VehicleServiceImpl vehicleService;
    private VehicleServiceImpl() {
    }
    public static synchronized VehicleServiceImpl getInstance() {
        if(vehicleService==null) vehicleService = new VehicleServiceImpl();
        return vehicleService;
    }

    // Runtime Polymorphism via Overriding
    // Utility Method to validate vehicle reg number as per valid indian car plates using regex
    @Override
    public boolean isValidVehicleRegisterationNumber(String number) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}-[0-9]{2}-[A-Z]{2}-[0-9]{4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(number);
        return matcher.find();
    }

    // Utility Method to add vehicle into system
    @Override
    public Vehicle addVehicle(String vehicleRegisterationNumber) {
        if(!isValidVehicleRegisterationNumber(vehicleRegisterationNumber)) {
            System.out.println("Vehicle Registeration Number : " + vehicleRegisterationNumber + " is not valid.");
            return null;
        }
        Vehicle vehicle = new Vehicle(vehicleRegisterationNumber);
        vehicles.put(vehicle.getVehicleNumber(),vehicle);
        return vehicle;
    }

    // Utility Method to return Vehicle object given vehicleRegisterationNumber
    @Override
    public Vehicle getVehicle(String vehicleRegisterationNumber) {
        if(!vehicles.containsKey(vehicleRegisterationNumber)) {
            System.out.println("No Vehicle with registeration number " + vehicleRegisterationNumber + " is present in the system.");
            return null;
        }
        return vehicles.get(vehicleRegisterationNumber);
    }
}

