package com.parivahan.model;

import java.util.ArrayList;

public class Ride {

    private User rideGiver ;

    private ArrayList<User> rideTakers;

    private Vehicle vehicle;

    private String origin;

    private String destination;

    private int availableSeats;

    private boolean isAvailable;

    public Ride(User rideGiver, Vehicle vehicle, String origin, String destination, int availableSeats) {
        this.rideGiver = rideGiver;
        this.vehicle = vehicle;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;

        this.rideTakers = new ArrayList<>();
    }


    public User getRideGiver() {
        return rideGiver;
    }

    public void setRideGiver(User rideGiver) {
        this.rideGiver = rideGiver;
    }

    public ArrayList<User> getRideTakers() {
        return rideTakers;
    }

    public void setRideTakers(ArrayList<User> rideTakers) {
        this.rideTakers = rideTakers;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
