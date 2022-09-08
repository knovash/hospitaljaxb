package com.solvd.hospitaljaxb;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.solvd.hospitaljaxb.department.DepDental;
import com.solvd.hospitaljaxb.department.DepEmergency;
import com.solvd.hospitaljaxb.department.DepSurgery;
import com.solvd.hospitaljaxb.department.Department;
import com.solvd.hospitaljaxb.doctor.Doctor;
import com.solvd.hospitaljaxb.utils.SAXparser;
import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Paths;
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

        // SAX parser. from XML to JavaObject
        Hospital hospitalSAX = new Hospital();
        hospitalSAX.setPatients(patients);
        hospitalSAX.setDepartments(departments);

        System.out.println("\nStart SAX parser. from XML to JavaObject\n");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXparser saxClass = new SAXparser(hospitalSAX);
        URL resource = Main.class.getClassLoader().getResource("hospital.xml");
        File resHospital = new File(resource.getFile());
        parser.parse(resHospital, saxClass);

        System.out.println("\nHospital SAX parser:\n");
        System.out.println(hospitalSAX);
        hospitalSAX.getPatients().forEach(p -> System.out.println(p));
        hospitalSAX.getDepartments().entrySet().stream()
                .peek(departmentEntry -> System.out.println(departmentEntry.getKey()))
                .flatMap(departmentEntry -> departmentEntry.getValue().getDoctors().stream())
                .forEach(doctor -> System.out.println(doctor));

        // JAXB parser from XML to JavaObject and from object to XML.
        System.out.println("\nStart JAXB parser. from XML to JavaObject\n");
        //писать результат сериализации будем в Writer(StringWriter)
        StringWriter writer = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(Hospital.class);

        // from XML to Object
        System.out.println("\nUnmarshaller create");
        Unmarshaller um = context.createUnmarshaller();
        System.out.println("\nget variables from xml file, created before");
        Hospital hospitalJAXB = (Hospital) um.unmarshal(resHospital);

        System.out.println(hospitalJAXB);
        hospitalJAXB.getPatients().forEach(p -> System.out.println(p));
        hospitalJAXB.getDepartments().entrySet().stream()
                .peek(departmentEntry -> System.out.println(departmentEntry.getKey()))
                .flatMap(departmentEntry -> departmentEntry.getValue().getDoctors().stream())
                .forEach(doctor -> System.out.println(doctor));

        // from Object to XML
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // сама сериализация
        marshaller.marshal(hospitalJAXB, writer);
        //преобразовываем в строку все записанное в StringWriter
        String result = writer.toString();
        System.out.println(result);
        File filejaxb = new File("jaxb.xml");
        FileUtils.writeStringToFile(filejaxb, result, Charset.defaultCharset());

        // Jackson
        System.out.println("\nTEST Jackson Hosital");
        // convert hospital object to JSON
        String jsonOut = new ObjectMapper().writeValueAsString(hospitalJAXB);
        System.out.println(jsonOut);
        // create object mapper instance
        ObjectMapper mapper = new ObjectMapper();
        // create an instance of DefaultPrettyPrinter
        ObjectWriter writerPP = mapper.writer(new DefaultPrettyPrinter());

        // convert object to JSON file DefaultPrettyPrinter
        writerPP.writeValue(Paths.get("hospital.json").toFile(), hospitalJAXB);

        // from JSON to Java Object
        Hospital hospitalJSON = mapper.readValue(jsonOut, Hospital.class);
        System.out.println("convert JSON to Java Object");
        System.out.println(hospitalJSON);
        hospitalJSON.getPatients().forEach(p -> System.out.println(p));
        hospitalJSON.getDepartments().entrySet().stream()
                .peek(departmentEntry -> System.out.println(departmentEntry.getKey()))
                .flatMap(departmentEntry -> departmentEntry.getValue().getDoctors().stream())
                .forEach(doctor -> System.out.println(doctor));
    }
}