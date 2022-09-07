package com.solvd;

import java.util.List;

public class DepSurgery extends Department {


    private List<Surgeon> surgeons;

    public List<Surgeon> getSurgeons() {
        return surgeons;
    }

    public void setSurgeons(List<Surgeon> surgeons) {
        this.surgeons = surgeons;
    }
}