package com.solvd;

//public abstract class Doctor extends Human {
public class Doctor extends Human {
    private String spec;


    public Doctor() {
    }

    public String toString() {
        return ("Doctor: " + super.name + " " + super.age + " " + super.weight + " " + this.spec);
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}