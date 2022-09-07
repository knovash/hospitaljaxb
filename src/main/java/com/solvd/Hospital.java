package com.solvd;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
//@XmlType(propOrder = {"address","patients", "surgeons", "dentists"})
public class Hospital {
    private String address;
    private List<Patient> patients;
//    private List<Dentist> dentists;
//    private List<Surgeon> surgeons;
    private List<Dental> dentals;
    private List<Surgery> surgeries;

    public Hospital() {
    }

    public String toString() {
        return ("Hospital: " + this.address + " patients:" + this.patients.size());
    }

//    @XmlElement(name = "patient")
//    @XmlElementWrapper(name = "PATIENTS")


    public String getAddress() {
        return address;
    }

    @XmlElement(name = "address")
    public void setAddress(String address) {
        this.address = address;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    @XmlElement(name = "Patient")
    @XmlElementWrapper(name = "PATIENTS")
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Dental> getDentals() {
        return dentals;
    }
    @XmlElement(name = "Dental")
    @XmlElementWrapper(name = "DENTALS")
    public void setDentals(List<Dental> dentals) {
        this.dentals = dentals;
    }

    public List<Surgery> getSurgeries() {
        return surgeries;
    }
    @XmlElement(name = "Surgery")
    @XmlElementWrapper(name = "SURGERIES")
    public void setSurgeries(List<Surgery> surgeries) {
        this.surgeries = surgeries;
    }
}