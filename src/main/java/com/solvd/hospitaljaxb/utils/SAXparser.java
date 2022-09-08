package com.solvd.hospitaljaxb.utils;

import com.solvd.hospitaljaxb.Hospital;
import com.solvd.hospitaljaxb.Human;
import com.solvd.hospitaljaxb.Patient;
import com.solvd.hospitaljaxb.doctor.Doctor;


import com.solvd.hospitaljaxb.doctor.Spec;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.helpers.DefaultHandler;

public class SAXparser extends DefaultHandler {

    Stack<String> level = new Stack<>();
    Stack<String> levelHi = new Stack<>();
    List<Patient> patients = new ArrayList<>();
    String key;
    Hospital hospital;
    private String levelUp;
    private String department;
    Doctor d = new Doctor();
    Patient p = new Patient();
    List<Doctor> docs = new ArrayList<>();

    public SAXparser(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        System.out.println("\n>>> ENTER" + localName + " <" + qName + ">    key " + key);
        level.add(qName);
//        System.out.println("level " + level);
        if (qName == "patient") p = new Patient();
        if (qName == "doctor") d = new Doctor();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length);
        System.out.println("CHARACTERS content: " + content);
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
//            if (level.peek() == "resdate") {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
//                LocalDate resdate = LocalDate.parse(content, formatter);
//                d.setResDate(resdate);
//            }
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
//            if (level.peek() == "dob") {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
//                LocalDate dob = LocalDate.parse(content, formatter);
//                p.setDateOfBirth(dob);
//            }
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
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("<<< EXIT " + localName + " <" + qName + ">");
        if (qName == "Patient") {
            hospital.getPatients().forEach(p -> System.out.println(p));
            System.out.println("END PATIENTS add p " + p.toString());
            hospital.getPatients().add(p);
            hospital.getPatients().forEach(p -> System.out.println(p));
            p = new Patient();

        }
        if (qName == "doctor") {
            System.out.println("END DOCTOR add d " + d.toString());
            docs.add(d);
        }
        if (qName == "DOCTORS") {
            System.out.println("------------ D E P A R T M E N T    key " + key);
            hospital.getDepartments().get(key).setDoctors(docs);
            docs = new ArrayList<>();
        }
        level.pop();
    }
}
