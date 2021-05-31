package com.parivahan.model;

import java.util.Objects;

public class Vehicle {

    private User owner;

    private String name;

    private String number;

    public Vehicle(User owner, String name, String number) {
        this.owner = owner;
        this.name = name;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return owner.equals(vehicle.owner) &&
                name.equals(vehicle.name) &&
                number.equals(vehicle.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, name, number);
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
