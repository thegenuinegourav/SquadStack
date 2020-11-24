package com.ss.Driver;

import com.ss.Vehicle.Vehicle;

import java.util.Set;

// Driver entity
public class Driver {
    // assuming age to be primary key
    private Integer age;

    // One To Many Mapping with Vehicle class, it stores number of vehicles owned by the drivers with this age.
    private Set<Vehicle> vehiclesOwned;

    // system is flexible enough to store other information for Driver entity such as follows
    private String name;
    private String address;
    private String licenseNumber;
    private String aadhaarNumber;
    // other details of driver, etc

    public Driver(Integer age) {
        this.age = age;
    }

    // getter and setters to achieve encapsulation & data abstraction
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Vehicle> getVehiclesOwned() {
        return vehiclesOwned;
    }

    public void setVehiclesOwned(Set<Vehicle> vehiclesOwned) {
        this.vehiclesOwned = vehiclesOwned;
    }
}
