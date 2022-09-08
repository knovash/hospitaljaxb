package com.solvd.hospitaljaxb.doctor;

public enum Spec {
    CARD("spec. Cardiologist"), DNT("spec. Dentist"), EMR("spec. Emergency"), INF("spec. Infectiologist"), SUR("spec. Surgeon");

    private final String displayName;

    Spec(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}