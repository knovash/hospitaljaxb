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
    private List<Department> deps;

    public Hospital() {
    }

    public String toString() {
        return ("Hospital: " + this.address + " patients:" + this.patients.size());
    }

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

    public List<Department> getDeps() {
        return deps;
    }
    @XmlElement(name = "department")
    @XmlElementWrapper(name = "DEPARTMENTS")
    public void setDeps(List<Department> deps) {
        this.deps = deps;
    }
}