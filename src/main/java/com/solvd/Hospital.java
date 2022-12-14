package com.solvd;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
//@XmlType(propOrder = {"address","patients", "surgeons", "dentists"})
public class Hospital
{
    private String address;
    private List<Patient> patients;
    private List<Dentist> dentists;
    private List<Surgeon> surgeons;
//    private List<Department> departments;


    public Hospital()
    {
    }

    public String toString() {
        return ("Hospital: " + this.address + " patients:" + this.patients.size());
    }

    public String getAddress() {
        return address;
    }
    @XmlElement
    public void setAddress(String address) {
        this.address = address;
    }

    public List<Patient> getPatients() {
        return patients;
    }
    @XmlElement
//    @XmlElementWrapper(name = "PATIENTS")
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Dentist> getDentists() {
        return dentists;
    }
    @XmlElement(name = "dentist")
    @XmlElementWrapper(name = "DENTISTS")
    public void setDentists(List<Dentist> dentists) {
        this.dentists = dentists;
    }

    public List<Surgeon> getSurgeons() {
        return surgeons;
    }
    @XmlElement(name = "surgeon")
    @XmlElementWrapper(name = "SURGEONS")
    public void setSurgeons(List<Surgeon> surgeons) {
        this.surgeons = surgeons;
    }

//    public List<Department> getDepartments() {
//        return departments;
//    }
//
//    @XmlElement
////    @XmlElementWrapper(name = "DEPARTMENTS")
//    public void setDepartments(List<Department> departments) {
//        this.departments = departments;
//    }
}