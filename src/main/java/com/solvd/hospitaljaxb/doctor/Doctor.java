package com.solvd.hospitaljaxb.doctor;

import com.solvd.hospitaljaxb.Human;

import java.math.BigDecimal;


public class Doctor extends Human {

    private Spec spec;
    private BigDecimal price;

    public Doctor() {
    }

    public String toString() {
        return ("Doctor: " + super.getName() + " " + this.spec + " " + this.price + "$");
    }

    public Spec getSpec() {
        return spec;
    }

    public void setSpec(Spec spec) {
        this.spec = spec;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}