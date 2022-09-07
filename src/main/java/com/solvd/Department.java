package com.solvd;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

//public abstract class Department extends Human {
public abstract class Department {
    private String name;

    public Department() {
    }

    public String toString() {
        return ("Department: " + " " + this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}