package com.solvd.hospitaljaxb.utils;


import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public String marshal(LocalDate date) {
        if (date == null) return null;
        return date.toString();
    }

    @Override
    public LocalDate unmarshal(String string) {
        return LocalDate.parse(string);
    }
}