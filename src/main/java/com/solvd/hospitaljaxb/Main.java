package com.solvd.hospitaljaxb;

import com.solvd.hospitaljaxb.department.DepDental;
import com.solvd.hospitaljaxb.department.DepEmergency;
import com.solvd.hospitaljaxb.department.DepSurgery;
import com.solvd.hospitaljaxb.department.Department;
import com.solvd.hospitaljaxb.doctor.Doctor;
import com.solvd.hospitaljaxb.utils.ParseJAXB;
import com.solvd.hospitaljaxb.utils.ParseJSON;
import com.solvd.hospitaljaxb.utils.ParseSAX;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws JAXBException, IOException, ParserConfigurationException, SAXException {

        // create empty hospital
        List<Patient> patients = new ArrayList<>();
        List<Doctor> dentists = new ArrayList<>();
        List<Doctor> surgeons = new ArrayList<>();
        List<Doctor> emergencys = new ArrayList<>();
        Map<String, Department> departments = new HashMap<>();
        DepDental dental = new DepDental();
        DepSurgery surgery = new DepSurgery();
        DepEmergency emergency = new DepEmergency();
        dental.setDoctors(dentists);
        surgery.setDoctors(surgeons);
        emergency.setDoctors(emergencys);
        departments.put("dental", dental);
        departments.put("surgery", surgery);
        departments.put("emergency", emergency);
        // resource file
        URL resource = Main.class.getClassLoader().getResource("hospital.xml");
        File resHospital = new File(resource.getFile());
        URL resource2 = Main.class.getClassLoader().getResource("hospital2.xml");
        File resHospital2 = new File(resource2.getFile());

        // SAX parser. from XML to JavaObject
        Hospital hospitalSAX = new Hospital();
        hospitalSAX.setPatients(patients);
        hospitalSAX.setDepartments(departments);
        ParseSAX parseSAX = new ParseSAX();
        parseSAX.fileToObject(resHospital, hospitalSAX);

        // JAXB parser from XML to JavaObject
        Hospital hospitalJAXB = new Hospital();
        hospitalJAXB.setPatients(patients);
        hospitalJAXB.setDepartments(departments);
        ParseJAXB parseJAXB = new ParseJAXB();
        parseJAXB.fileToObject(resHospital2, hospitalJAXB);
        // JAXB parser from object to XML.
        File filejaxb = new File("jaxb.xml");
        parseJAXB.objectToFile(hospitalJAXB, filejaxb);

        // Jackson parser from object to JSON.
        ParseJSON parseJSON = new ParseJSON();
        File filejson = new File("hospital.json");
        parseJSON.objectToFile(hospitalJAXB, filejson);
        // Jackson parser from JSON to JavaObject
        Hospital hospitalJSON = new Hospital();
        hospitalJSON.setPatients(patients);
        hospitalJSON.setDepartments(departments);
        parseJSON.fileToObject(filejson, hospitalJSON);
    }
}