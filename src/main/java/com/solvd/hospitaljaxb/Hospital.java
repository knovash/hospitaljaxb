package com.solvd.hospitaljaxb;

import com.solvd.hospitaljaxb.department.Department;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Map;

@XmlRootElement
@XmlType(propOrder = {"address","phone", "patients", "departments"})
public class Hospital {
    private String address;
    private String phone;
    private List<Patient> patients;
    private Map<String, Department> departments;

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

    public String getPhone() {
        return phone;
    }
    @XmlElement(name = "phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    @XmlElement(name = "Patient")
    @XmlElementWrapper(name = "PATIENTS")
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public Map<String, Department> getDepartments() {
        return departments;
    }

    @XmlElement(name = "department")
    @XmlElementWrapper(name = "DEPARTMENTS")
    public void setDepartments(Map<String, Department> departments) {
        this.departments = departments;
    }
}