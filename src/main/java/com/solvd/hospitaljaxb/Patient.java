package com.solvd.hospitaljaxb;

import java.math.BigDecimal;

public class Patient extends Human {

    private BigDecimal credit;

    public Patient() {
    }

    public String toString() {
        return ("Patient: " + super.getName() + " " + super.getGender() + " " + this.credit + "$");
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }
}