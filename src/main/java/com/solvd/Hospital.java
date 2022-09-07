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
    private List<DepDental> deps;
//    private List<DepSurgery> depSur;

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



    public List<DepDental> getDeps() {
        return deps;
    }
    @XmlElement(name = "dep")
    @XmlElementWrapper(name = "DEPS")
    public void setDeps(List<DepDental> deps) {
        this.deps = deps;
    }
}