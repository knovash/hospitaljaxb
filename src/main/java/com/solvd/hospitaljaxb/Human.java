package com.solvd.hospitaljaxb;

public abstract class Human {
    private String name;
    private Gender gender;

    public Human() {
    }

    public enum Gender {
        MALE("man"), FEMALE("woman");

        private final String displayName;

        Gender(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public String toString() {
        return ("Human: " + this.name + " " + this.gender);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}