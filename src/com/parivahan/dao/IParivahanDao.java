package com.parivahan.dao;

import com.parivahan.model.Ride;
import com.parivahan.model.User;
import com.parivahan.model.Vehicle;

import java.util.ArrayList;

public interface IParivahanDao {

    void addUser(User user) throws Exception;

    void addVehicle(Vehicle vehicle) throws Exception;

    void addRide(Ride ride) throws Exception;

    ArrayList<User> getAllUsers();

    User getUser(String name) throws Exception;

    Vehicle getVehicle(String name, String number) throws Exception;

    ArrayList<Ride> getRide(String origin, String destination);

    ArrayList<Ride> getRides(String origin, String destination, String vehicleName);

    ArrayList<Ride> getAllRides();


}
