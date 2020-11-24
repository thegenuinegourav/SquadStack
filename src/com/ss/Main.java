package com.ss;

import com.ss.Driver.DriverService;
import com.ss.Driver.DriverServiceImpl;
import com.ss.ParkingService.ParkingService;
import com.ss.ParkingService.ParkingServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // injecting Driver & Parking Service
        DriverService driverService = DriverServiceImpl.getInstance();
        ParkingService parkingService = ParkingServiceImpl.getInstance();

        try {
            // read the input file
            URL url = Main.class.getResource("input.txt");
            File myObj = new File(url.getPath());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitStr = data.trim().split("\\s+"); // split string by space
                Integer capacity, driverAge, slot;
                String vehicleRegNumber;
                // search for command & call the required service method as per the command keywords
                switch(splitStr[0]) {
                    case "Create_parking_lot":
                        capacity = Integer.parseInt(splitStr[1]);
                        parkingService.setParkingService(capacity);
                        break;
                    case "Park":
                        vehicleRegNumber = splitStr[1];
                        driverAge = Integer.parseInt(splitStr[3]);
                        parkingService.park(vehicleRegNumber,driverAge);
                        break;
                    case "Slot_numbers_for_driver_of_age":
                        driverAge = Integer.parseInt(splitStr[1]);
                        parkingService.printSlotNumber(driverAge);
                        break;
                    case "Slot_number_for_car_with_number":
                        vehicleRegNumber = splitStr[1];
                        parkingService.printSlotNumber(vehicleRegNumber);
                        break;
                    case "Leave":
                        slot = Integer.parseInt(splitStr[1]);
                        parkingService.leave(slot);
                        break;
                    case "Vehicle_registration_number_for_driver_of_age":
                        driverAge = Integer.parseInt(splitStr[1]);
                        driverService.printAllVehicles(driverAge);
                        break;
                    default:
                        System.out.println("INVALID COMMAND!");
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not found error!!.");
            e.printStackTrace();
        }
    }
}
