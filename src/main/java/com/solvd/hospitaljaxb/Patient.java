package com.solvd;

public class Patient extends Human {

    public Patient() {
    }

    public String toString() {
        return ("Patient: " + super.name + " " + super.age + " " + super.weight);
    }
}