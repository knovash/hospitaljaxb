package com.solvd.hospitaljaxb;

import java.math.BigDecimal;

public class Patient extends Human {

    private BigDecimal credit;
    private String dob;

    public Patient() {
    }

    public String toString() {
        return ("Patient: " + super.getName() + " " + super.getGender() + " " + this.credit + "$" + " " + this.dob);
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}