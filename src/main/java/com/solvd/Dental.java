package com.solvd;

import java.util.List;

public class Dental extends Department {
    private List<Dentist> dentists;

    public List<Dentist> getDentists() {
        return dentists;
    }

    public void setDentists(List<Dentist> dentists) {
        this.dentists = dentists;
    }
}
