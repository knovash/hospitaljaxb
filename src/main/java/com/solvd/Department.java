package com.solvd;

public abstract class Department extends Human {
    private String car;

    public Department() {
    }

    public String toString() {
        return ("Department: " + super.name + " " + super.age + " " + super.weight + " " + this.car);
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}