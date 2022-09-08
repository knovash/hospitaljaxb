package com.solvd.hospitaljaxb;

import com.solvd.hospitaljaxb.utils.LocalDateAdapter;
import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Patient extends Human {

    private BigDecimal credit;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dob;

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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}