package com.solvd;

import java.util.List;

public class Surgery extends Department {

    private List<Surgeon> surgeons;

    public List<Surgeon> getSurgeons() {
        return surgeons;
    }

    public void setSurgeons(List<Surgeon> surgeons) {
        this.surgeons = surgeons;
    }
}
