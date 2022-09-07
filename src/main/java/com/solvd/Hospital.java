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
    private List<Dentist> dentists;
    private List<Surgeon> surgeons;
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
    @XmlElementWrapper(name = "patients")
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Dentist> getDentists() {
        return dentists;
    }

    @XmlElement(name = "Dentist")
    @XmlElementWrapper(name = "dentists")
    public void setDentists(List<Dentist> dentists) {
        this.dentists = dentists;
    }

    public List<Surgeon> getSurgeons() {
        return surgeons;
    }

    @XmlElement(name = "Surgeon")
    @XmlElementWrapper(name = "surgeons")
    public void setSurgeons(List<Surgeon> surgeons) {
        this.surgeons = surgeons;
    }

    public List<Dental> getFords() {
        return dentals;
    }
    @XmlElement(name = "Ford")
    @XmlElementWrapper(name = "fords")
    public void setFords(List<Dental> dentals) {
        this.dentals = dentals;
    }

    public List<Surgery> getAudis() {
        return surgeries;
    }
    @XmlElement(name = "Audi")
    @XmlElementWrapper(name = "audis")
    public void setAudis(List<Surgery> surgeries) {
        this.surgeries = surgeries;
    }
}