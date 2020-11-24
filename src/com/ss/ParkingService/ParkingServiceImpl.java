package com.ss.ParkingService;

import com.ss.Driver.Driver;
import com.ss.Driver.DriverService;
import com.ss.Driver.DriverServiceImpl;
import com.ss.Vehicle.Vehicle;
import com.ss.Vehicle.VehicleService;
import com.ss.Vehicle.VehicleServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

// Parking Service Implementation for all required operations to be done with our parking lot
// Achieved Inheritance by implementing interface
public class ParkingServiceImpl implements ParkingService {

    // Using Singleton Pattern to make sure only one instance of each service will get create.
    private static ParkingServiceImpl parkingService;
    private ParkingServiceImpl() {
    }
    public static synchronized ParkingServiceImpl getInstance() {
        if(parkingService==null) parkingService = new ParkingServiceImpl();
        return parkingService;
    }

    // To store capacity of a parking lot
    private Integer capacity;

    // Using Min-Heap Data Structure to store all available/free spots
    private PriorityQueue<Integer> availableSpots;

    // Slot -> Ticket Mapping
    // Using HashMap to store all tickets data with an index on slot number
    // Why HashMap? POST, GET, UPDATE & DELETE request will take O(1) i.e. constant time complexity.
    HashMap<Integer, Ticket> slots = new HashMap<>();

    // Driver Age -> List of Parking Slot Numbers Mapping
    // Using HashMap to store all list of slots data with an index on driver age
    // Why HashMap? POST, GET, UPDATE & DELETE request will take O(1) i.e. constant time complexity.
    HashMap<Integer, List<Integer>> ageSlotMap = new HashMap<>();

    // Vehicle Reg Number -> Parking Slot Number Mapping
    // Using HashMap to store all slots data with an index on vehicle reg number
    // Why HashMap? POST, GET, UPDATE & DELETE request will take O(1) i.e. constant time complexity.
    HashMap<String, Integer>  vehicleSlotMap = new HashMap<>();


    // Injecting instances of Driver & Vehicle Service
    private DriverService driverService = DriverServiceImpl.getInstance();
    private VehicleService vehicleService = VehicleServiceImpl.getInstance();

    // Runtime Polymorphism via Overriding
    // Method to create parking lot with given size
    @Override
    public void setParkingService(Integer capacity) {
        this.capacity = capacity;
        availableSpots = new PriorityQueue<>();
        for(Integer i=1;i<=capacity;i++)
            availableSpots.add(i);
        slots = new HashMap<>();
        ageSlotMap = new HashMap<>();
        vehicleSlotMap = new HashMap<>();
        System.out.println("Created parking of " + capacity.toString() + " slots.");
    }

    // Method to park given vehicle with given driver age
    // Time Complexity : O(1)
    @Override
    public void park(String vehicleRegNumber, Integer age) {
        // get the closest slot [smallest index]
        Integer slot = availableSpots.poll();
        if(slot==null) {
            System.out.println("Parking Capacity is full! Can't park anymore cars.");
            return;
        }
        Vehicle vehicle = vehicleService.addVehicle(vehicleRegNumber);
        if(vehicle==null) return;
        Driver driver = driverService.addDriver(age,vehicle);

        slots.put(slot,new Ticket(driver,vehicle));

        List<Integer> slotsOwnedByAge;
        if(!ageSlotMap.containsKey(driver.getAge()))
            slotsOwnedByAge = new ArrayList<>();
        else
            slotsOwnedByAge = ageSlotMap.get(driver.getAge());
        slotsOwnedByAge.add(slot);
        ageSlotMap.put(driver.getAge(),slotsOwnedByAge);

        vehicleSlotMap.put(vehicleRegNumber,slot);

        System.out.println("Car with vehicle registeration number " + vehicleRegNumber + " has been parked at slot number " + slot.toString());
    }

    // Method to print slot numbers of all vehicles which have drivers with given age
    // Time Complexity : O(1)
    @Override
    public void printSlotNumber(Integer driverAge) {
        if(!ageSlotMap.containsKey(driverAge)) {
            System.out.println("No slot has been assigned to cars with driver age as " + driverAge.toString());
            return;
        }
        System.out.print("Slot Numbers of all cars which have drivers with age as " + driverAge.toString() + " are as follows : ");
        List<Integer> slotsOwnedByAge = ageSlotMap.get(driverAge);
        int i=0;
        for(i=0;i<slotsOwnedByAge.size()-1;i++)
            System.out.print(slotsOwnedByAge.get(i).toString() + " , ");
        System.out.print(slotsOwnedByAge.get(i).toString());
        System.out.println();
    }

    // Method to print slot number of vehicle with given vehicle registration number
    // Time Complexity : O(1)
    @Override
    public void printSlotNumber(String vehicleRegNumber) {
        if(!vehicleSlotMap.containsKey(vehicleRegNumber)) {
            System.out.println("No slot has been assigned to a car with vehicle registration number as " + vehicleRegNumber);
            return;
        }
        System.out.println("Slot Number of cars which have vehicle registration number as " + vehicleRegNumber +
                " is as follows : " + vehicleSlotMap.get(vehicleRegNumber));
    }

    // Method to vacate given slot
    // Time Complexity : O(1)
    @Override
    public void leave(Integer slotNumber) {
        if(!slots.containsKey(slotNumber)) {
            System.out.println("This slot is already vacated!");
            return;
        }
        availableSpots.add(slotNumber);
        Ticket ticket = slots.get(slotNumber);
        slots.remove(slotNumber);
        vehicleSlotMap.remove(ticket.getVehicle().getVehicleNumber());
        ageSlotMap.remove(ticket.getDriver().getAge());
        System.out.println("Slot number "+slotNumber.toString()+" vacated, the car with vehicle registration number "
                +ticket.getVehicle().getVehicleNumber()+" left the space, the driver of the car was of age " + ticket.getDriver().getAge().toString());

    }
}
