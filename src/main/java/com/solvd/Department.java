package com.solvd;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

public abstract class Department<D extends Doctor> {
    private String name;
    private List<D> doctors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<D> getDoctors() {
        return doctors;
    }
    @XmlElement(name = "doctor")
    @XmlElementWrapper(name = "DOCTORS")
    public void setDoctors(List<D> doctors) {
        this.doctors = doctors;
    }
}
