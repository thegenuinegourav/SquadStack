package com.ss.Driver;

import com.ss.Vehicle.Vehicle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// Driver Service Implementation to interact with Driver entity & operations
// Achieved Inheritance by implementing interface
public class DriverServiceImpl implements DriverService{
    // Using HashMap to store all drivers data with an index on age (primary key)
    // Why HashMap? POST, GET, UPDATE & DELETE request on Driver Service will take O(1) i.e. constant time complexity.
    private HashMap<Integer, Driver> drivers = new HashMap<>();

    // Using Singleton Pattern to make sure only one instance of each service will get create.
    private static DriverServiceImpl driverService;
    private DriverServiceImpl() {
    }
    public static synchronized DriverServiceImpl getInstance() {
        if(driverService==null) driverService = new DriverServiceImpl();
        return driverService;
    }

    // Runtime Polymorphism via Overriding
    // Utility Method to add driver into system
    @Override
    public Driver addDriver(Integer age, Vehicle vehicle) {
        Driver driver = new Driver(age);
        Set<Vehicle> vehicleOwned = driver.getVehiclesOwned();
        if(vehicleOwned==null)
            vehicleOwned = new HashSet<>();
        vehicleOwned.add(vehicle);
        driver.setVehiclesOwned(vehicleOwned);
        drivers.put(age,driver);
        return driver;
    }

    // Utility Method to return Driver object given age
    @Override
    public Driver getDriver(Integer age) {
        if(!drivers.containsKey(age)) {
            System.out.println("No Driver with age " + age.toString() + " is present in the system.");
            return null;
        }
        return drivers.get(age);
    }

    // Method to print all vehicle registration number of cars parked by driver with given age
    // Time Complexity : O(1)
    @Override
    public void printAllVehicles(Integer age) {
        if(!drivers.containsKey(age)) {
            System.out.println("No vehicle found parked by the driver of age " + age.toString() + ".");
            return;
        }
        Driver driver = drivers.get(age);
        Iterator<Vehicle> it = driver.getVehiclesOwned().iterator();
        System.out.print("Vehicle Registeration Numbers of all cars parked by driver of age " + age.toString() + " are as follows: ");
        while(it.hasNext()){
            System.out.print(it.next().getVehicleNumber() + " | ");
        }
        System.out.println();
    }
}
