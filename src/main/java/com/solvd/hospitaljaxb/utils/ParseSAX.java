package com.solvd.hospitaljaxb.utils;

import com.solvd.hospitaljaxb.Hospital;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class ParseSAX implements IParse {

    @Override
    public void fileToObject(File file, Hospital hospital) {
        /** SAX parser. from XML to JavaObject */
        System.out.println("\nStart SAX parser. from XML to JavaObject\n");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            SAXparser saxClass = new SAXparser(hospital);
            parser.parse(file, saxClass);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nHospital SAX parser:\n");
        System.out.println(hospital);
        hospital.getPatients().forEach(p -> System.out.println(p));
        hospital.getDepartments().entrySet().stream()
                .peek(departmentEntry -> System.out.println(departmentEntry.getKey()))
                .flatMap(departmentEntry -> departmentEntry.getValue().getDoctors().stream())
                .forEach(doctor -> System.out.println(doctor));
    }

    @Override
    public void objectToFile(Hospital object, File file) {
    }
}
