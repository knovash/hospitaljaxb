package com.solvd.hospitaljaxb.utils;

import com.solvd.hospitaljaxb.Hospital;
import com.solvd.hospitaljaxb.Human;
import com.solvd.hospitaljaxb.Patient;
import com.solvd.hospitaljaxb.doctor.Doctor;

import com.solvd.hospitaljaxb.doctor.Spec;
import org.xml.sax.Attributes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.helpers.DefaultHandler;

public class SAXparser extends DefaultHandler {

    Stack<String> level = new Stack<>();
    String key;
    Hospital hospital;
    private String levelUp;
    Doctor d = new Doctor();
    Patient p = new Patient();
    List<Doctor> docs = new ArrayList<>();

    public SAXparser(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        System.out.println("\n>>> ENTER" + localName + " <" + qName + ">    key " + key);
        level.add(qName);
        if (qName == "patient") p = new Patient();
        if (qName == "doctor") d = new Doctor();
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String content = new String(ch, start, length);
        if (!content.isEmpty() && !content.contains("\n")) {
            System.out.println("level: " + level + " content:" + content);
        }
        if (content.isEmpty() | content.contains("\n")) {
            levelUp = level.peek();
            System.out.println("LEVEL UP " + levelUp);
        }
        if (levelUp == "doctor") {
            if (level.peek() == "name") {
                d.setName(content);
            }
            if (level.peek() == "spec") {
                Spec spec = Spec.valueOf(content);
                d.setSpec(spec);
            }
            if (level.peek() == "price") {
                BigDecimal price = new BigDecimal(content);
                d.setPrice(price);
            }
        }

        if (levelUp == "Patient") {
            if (level.peek() == "name") {
                p.setName(content);
            }
            if (level.peek() == "gender") {
                Human.Gender gender = Human.Gender.valueOf(content);
                p.setGender(gender);
            }
            if (level.peek() == "credit") {
                BigDecimal credit = new BigDecimal(content);
                p.setCredit(credit);
            }
            if (level.peek() == "dob") {
                p.setDob(content);
            }
        }

        if (levelUp == "hospital") {
            if (level.peek() == "address") {
                hospital.setAddress(content);
            }
            if (level.peek() == "phone") {
                hospital.setPhone(content);
            }
        }

        if (level.peek() == "key") {
            key = content;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.println("<<< EXIT " + localName + " <" + qName + ">");
        if (qName == "Patient") {
            System.out.println("END PATIENTS add p " + p.toString());
            hospital.getPatients().add(p);
            p = new Patient();
        }
        if (qName == "doctor") {
            System.out.println("END DOCTOR add d " + d.toString());
            docs.add(d);
        }
        if (qName == "DOCTORS") {
            System.out.println("key " + key);
            hospital.getDepartments().get(key).setDoctors(docs);
            docs = new ArrayList<>();
        }
        level.pop();
    }
}