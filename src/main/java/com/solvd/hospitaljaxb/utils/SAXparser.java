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

    private Stack<String> level = new Stack<>();
    private String key;
    private Hospital hospital;
    private String levelUp;
    private Doctor d = new Doctor();
    private Patient p = new Patient();
    private List<Doctor> docs = new ArrayList<>();

    public SAXparser(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        System.out.println("\n>>> ENTER" + localName + " <" + qName + ">    key " + key);
        level.add(qName);
        if ("patient".equals(qName)) p = new Patient();
        if ("doctor".equals(qName)) d = new Doctor();
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
        if ("doctor".equals(levelUp)) {
            if ("name".equals(level.peek())) {
                d.setName(content);
            }
            if ("spec".equals(level.peek())) {
                Spec spec = Spec.valueOf(content);
                d.setSpec(spec);
            }
            if ("price".equals(level.peek())) {
                BigDecimal price = new BigDecimal(content);
                d.setPrice(price);
            }
        }
        if ("Patient".equals(levelUp)) {
            if (level.peek().equals("name")) {
                p.setName(content);
            }
            if ("gender".equals(level.peek())) {
                Human.Gender gender = Human.Gender.valueOf(content);
                p.setGender(gender);
            }
            if ("credit".equals(level.peek())) {
                BigDecimal credit = new BigDecimal(content);
                p.setCredit(credit);
            }
            if ("dob".equals(level.peek())) {
                p.setDob(content);
            }
        }
        if ("hospital".equals(levelUp)) {
            if ("address".equals(level.peek())) {
                hospital.setAddress(content);
            }
            if ("phone".equals(level.peek())) {
                hospital.setPhone(content);
            }
        }
        if ("key".equals(level.peek())) {
            key = content;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.println("<<< EXIT " + localName + " <" + qName + ">");
        if ("Patient".equals(qName)) {
            System.out.println("END PATIENTS add p " + p.toString());
            hospital.getPatients().add(p);
            p = new Patient();
        }
        if ("doctor".equals(qName)) {
            System.out.println("END DOCTOR add d " + d.toString());
            docs.add(d);
        }
        if ("DOCTORS".equals(qName)) {
            System.out.println("key " + key);
            hospital.getDepartments().get(key).setDoctors(docs);
            docs = new ArrayList<>();
        }
        level.pop();
    }
}