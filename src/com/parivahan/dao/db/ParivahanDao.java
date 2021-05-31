package com.parivahan.dao.db;

import com.parivahan.dao.IParivahanDao;
import com.parivahan.model.Ride;
import com.parivahan.model.User;
import com.parivahan.model.Vehicle;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ParivahanDao implements IParivahanDao {

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<Ride> rides = new ArrayList<>();

    public User getUser(String name) throws Exception {
        User existingUser = users.stream().filter(p -> p.getName().equals(name)).findFirst().get();
        if (existingUser != null) {
            return existingUser;
        } else {
            throw new Exception("User does not exist");
        }
    }

    public void addUser(User user) throws Exception {
        boolean isUserExists = users.stream().anyMatch(u -> u.equals(user));
        if (!isUserExists) {
            users.add(user);
        } else {
            throw new Exception("User already exists");
        }
    }

    public void addVehicle(Vehicle vehicle) throws Exception {
        boolean isVehicleExists = vehicles.stream().anyMatch(u -> u.equals(vehicle));
        if (!isVehicleExists) {
            vehicles.add(vehicle);
        } else {
            throw new Exception("Vehicle already exists");
        }
    }

    public Vehicle getVehicle(String name, String number) throws Exception {
        Vehicle existingVehicle = vehicles.stream().filter(p -> p.getName().equals(name) && p.getNumber().equals(number)).findFirst().get();
        if (existingVehicle != null) {
            return existingVehicle;
        } else {
            throw new Exception("Vehicle does not exist");
        }
    }

    public void addRide(Ride ride) throws Exception {
        boolean isSameRide = false;
        boolean isRideNotPossible = false;
        if(rides.size() != 0)
        {
            isSameRide = rides.stream().anyMatch(r -> r.equals(ride));
            isRideNotPossible = rides.stream().anyMatch(r -> (r.getRideGiver().equals(ride.getRideGiver()) &&
                    r.getVehicle().equals(ride.getVehicle())));
        }
        if(!isSameRide && !isRideNotPossible) {
            rides.add(ride);
        } else {
            throw new Exception("Either the ride is same or something bogus");
        }
    }

    public ArrayList<Ride> getRide(String origin, String destination) {
        return rides.stream().filter(r -> r.getOrigin().equals(origin) && r.getDestination().equals(destination))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Ride> getRides(String origin, String destination, String vehicleName) {
        return rides.stream().filter(r -> r.getOrigin().equals(origin) &&
                r.getDestination().equals(destination) &&
                r.getVehicle().getName().equals(vehicleName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public ArrayList<Ride> getAllRides() {
        return rides;
    }







}
