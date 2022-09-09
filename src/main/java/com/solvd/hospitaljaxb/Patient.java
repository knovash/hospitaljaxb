package com.solvd.hospitaljaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
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

    //    public LocalDate getDob() {
    public String getDob() {
        return dob;
    }

    //    public void setDob(LocalDate dob) {
    public void setDob(String dob) {
        this.dob = dob;
    }
}