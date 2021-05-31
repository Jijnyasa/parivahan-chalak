package com.parivahan.model;

import java.util.Objects;

public class User {

    public static enum Gender {
        M("male"),
        F("female");

        private String genderDescription;

        Gender(String genderDescription) {
            this.genderDescription = genderDescription;
        }

        public String getGenderDescription() {
            return genderDescription;
        };
    }

    private String name;

    private int age;



    private Gender gender;

    public User(String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                name.equals(user.name) &&
                gender == user.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, gender);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
