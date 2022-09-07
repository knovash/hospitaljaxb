package com.solvd;

//public abstract class Department extends Human {
public abstract class Department extends Human {
    private String dep;

    public Department() {
    }

    public String toString() {
        return ("Department: " + " " + this.dep);
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }
}