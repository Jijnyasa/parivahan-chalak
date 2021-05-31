package com.parivahan.service;

import com.parivahan.dao.IParivahanDao;
import com.parivahan.model.Ride;
import com.parivahan.model.User;
import com.parivahan.model.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

public class ParivahanService {

    private IParivahanDao parivahanDao;

    public ParivahanService(IParivahanDao parivahanDao) {
        this.parivahanDao = parivahanDao;
    }

    public void addUser(String name, String gender, int age) {
        User user = new User(name, getGender(gender), age);
        try {
            parivahanDao.addUser(user);
            System.out.println("User added " + user.getName());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addVehicle(String owner, String carName, String carNumber) {
        try {
            User existingUser = parivahanDao.getUser(owner);
            Vehicle vehicle = new Vehicle(existingUser, carName, carNumber);
            parivahanDao.addVehicle(vehicle);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void offerRide(String name, String origin, int availableSeats,
                          String vehicleName, String vehicleNumber, String destination) {
        try {
            User existingOwner = parivahanDao.getUser(name);
            Vehicle vehicle = parivahanDao.getVehicle(vehicleName, vehicleNumber);
            Ride ride = new Ride(existingOwner, vehicle, origin, destination, availableSeats);
            parivahanDao.addRide(ride);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Ride selectRide(String rideTaker, String origin, String destination, int seats, String preferenceOption) {
        Ride selectedRide = null;

        try {
            User rideWanter = parivahanDao.getUser(rideTaker);
            Preference userPreference = getPreferenceEnum(preferenceOption);

            if (userPreference.equals(Preference.MOST_VACANT)) {
                ArrayList<Ride> matchedRides = parivahanDao.getRide(origin, destination);
               matchedRides.sort(Comparator.comparingInt(Ride::getAvailableSeats));
               selectedRide= matchedRides.get(matchedRides.size() - 1);

               if(selectedRide.getAvailableSeats() < seats) {
                   throw new Exception("Seats not available");
               }
           } else {
               selectedRide = parivahanDao.getRides(origin, destination, userPreference.getPreferenceType()).stream().findFirst().get();
           }
            if (selectedRide != null) {
                selectedRide.getRideTakers().add(rideWanter);
                selectedRide.setAvailableSeats(
                        selectedRide.getAvailableSeats() - seats
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return selectedRide;
    }

    public void printRideStats() {
        ArrayList<User> getAllUserData = parivahanDao.getAllUsers();
        HashMap<User, HashMap<String, Integer>> userStats = new HashMap<>();

        getAllUserData.stream().forEach(user -> userStats.put(user,
                new HashMap<>() {{
                    put("Taken", 0);
                    put("Offered", 0);
                }}));
        ArrayList<Ride> getAllRides = parivahanDao.getAllRides();
        getAllRides.stream().forEach(
                ride -> userStats.get(ride.getRideGiver()).put("Offered", userStats.get(ride.getRideGiver()).get("Offered") + 1)
        );
        getAllRides.stream().
                filter(ride -> ride.getRideTakers() != null)
                .forEach(ride -> ride.getRideTakers().stream().forEach(
                        r -> userStats.get(r).put("Taken", userStats.get(r).get("Taken") + 1)
                )
        );
        userStats.entrySet().stream().forEach(
                userHashMapEntry -> System.out.println("User " + userHashMapEntry.getKey().getName() +
                        " has taken " + userHashMapEntry.getValue().get("Taken") + " seats" +
                        " and offered " + userHashMapEntry.getValue().get("Offered")
                        )
        );
    }

    private User.Gender getGender(String gender) {
        return User.Gender.valueOf(gender);
    }

    private Preference getPreferenceEnum(String preferenceOption) throws Exception {
        Preference option  = Arrays.stream(Preference.values()).filter(p -> p.getPreferenceType().equals(preferenceOption)).findFirst().get();
        return option;
    }

    private enum Preference {
        POLO("Polo"),
        ACTIVA("Activa"),
        XUV("XUV"),
        MOST_VACANT("Most Vacant");

        private String preferenceType;

        Preference(String preferenceType) {
            this.preferenceType = preferenceType;
        }

        private String getPreferenceType() {
            return this.preferenceType;
        }

    }
}
