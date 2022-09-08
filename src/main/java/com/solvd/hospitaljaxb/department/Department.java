package com.solvd.hospitaljaxb.department;

import com.solvd.hospitaljaxb.doctor.Doctor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
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
    @XmlElement(name = "name")
    public void setDepName(String depName) {
        this.depName = depName;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }
    @XmlElement(name = "doctor")
    @XmlElementWrapper(name = "DOCTORS")
    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}