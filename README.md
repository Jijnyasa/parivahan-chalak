# parivahan-chalak

## Problem Statement
Implementing a ride sharing application backend with a in memory data structures

## Features
    The application allows users to share rides on a route.
    Users can either offer a shared ride (Driver) or consume a shared ride (Passenger).
    Users can search and select one from multiple available rides on a route with the same source and destination.

Requirements:

    Application should allow user onboarding.
        add_user(user_detail)
            Add basic user details

        add_vehicle(vehicle_detail)
            Add the user’s vehicle(s) details

    User should be able to offer a shared ride on a route with details.
        offer_ride(ride_detail)

    Ride will have details like vehicle, origin, destination, available seats. (A ride will have no intermediate stops.)
    Users can select a ride from multiple offered rides using a selection strategy. (A user can only request  a ride (only for 1 or 2 people))
        select_ride(source, destination, seats, selection_strategy)
            Prefered Vehicle (Activa/Polo/XUV)
            Most Vacant.

    System should be able to end the ride. User can only offer a ride for a given vehicle, once there are no active offered rides for that vehicle.
        end_ride(ride_details)

    Find total rides offered/taken by all users.
        print_ride_stats()
        
## TestCases
The application would be tested by using jshell. The command for the same is  
    `jshell> /env --class-path out/production/parivahan-chalak`

The commands to run in jshell are listed below

```import com.parivahan.model.Ride;
 import com.parivahan.dao.db.ParivahanDao;
 import com.parivahan.service.ParivahanService;
 
 ParivahanDao parivahanDaoInMemory = new ParivahanDao();
 ParivahanService parivahanService = new ParivahanService(parivahanDaoInMemory);
 
 parivahanService.addUser("Rohan", "M", 36);
 parivahanService.addUser("Shashank", "M", 29);
 parivahanService.addUser("Nandini", "F", 29);
 parivahanService.addUser("Shipra", "F", 27);
 parivahanService.addUser("Gaurav", "M", 29);
 parivahanService.addUser("Rahul", "M", 35);
 
 parivahanService.addVehicle("Rohan", "Swift", "KA-01-12345");
 parivahanService.addVehicle("Shipra", "Activa", "KA-12-12332");
 parivahanService.addVehicle("Shipra", "Polo", "KA-05-41491");
 parivahanService.addVehicle("Rahul", "XUV", "KA-05-1234");
 parivahanService.addVehicle("Shashank", "Baleno", "TS-05-62395");
 
 parivahanService.offerRide("Rohan", "Hyderabad", 1, "Swift", "KA-01-12345", "Bangalore");
 parivahanService.offerRide("Shipra", "Bangalore", 1, "Activa", "KA-12-12332", "Mysore");
 parivahanService.offerRide("Shipra", "Bangalore", 2, "Polo", "KA-05-41491", "Mysore");
 parivahanService.offerRide("Shashank", "Hyderabad", 2, "Baleno", "TS-05-62395", "Bangalore");
 parivahanService.offerRide("Rahul", "Hyderabad", 5, "XUV", "KA-05-1234", "Bangalore");
 parivahanService.offerRide("Shashank", "Hyderabad", 2, "Baleno", "TS-05-62395", "Bangalore");
 parivahanService.offerRide("Rohan", "Bangalore", 1, "Swift", "KA-01-12345", "Pune");
 
 parivahanService.selectRide("Nandini", "Bangalore", "Mysore", 1, "Most Vacant");
 parivahanService.selectRide("Gaurav", "Bangalore", "Mysore", 1, "Activa");
 parivahanService.selectRide("Shashank", "Mumbai", "Bangalore", 1, "Most Vacant");
 parivahanService.selectRide("Rohan", "Hyderabad", "Bangalore", 1, "Baleno");
 
 parivahanService.printRideStats()