package com.solvd.department;

import com.solvd.Doctor;

import javax.xml.bind.annotation.*;
import java.util.List;

//public abstract class Department extends Human {
public class Department {
    private String depName;
    private List<Doctor> doctors;

    public Department() {
    }

    public String toString() {
        return ("Department: " + " " + this.depName);
    }

    public String getDepName() {
        return depName;
    }
    public void setDepName(String depName) {
        this.depName = depName;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }
    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}