package com.solvd;

import org.apache.commons.io.FileUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws JAXBException, IOException {
        //создание объекта для сериализации в XML
        Patient patient = new Patient();
        patient.name = "Alex";
        patient.age = 33;
        patient.weight = 66;

        Dentist dentist = new Dentist();
        dentist.name = "Denis";
        dentist.age = 44;
        dentist.weight = 88;
        dentist.setSpec("dentist");

        Surgeon surgeon = new Surgeon();
        surgeon.name = "Sergey";
        surgeon.age = 66;
        surgeon.weight = 77;
        surgeon.setSpec("surgeon");

        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        patients.add(patient);

        List<Dentist> dentists = new ArrayList<>();
        dentists.add(dentist);
        dentists.add(dentist);

        List<Surgeon> surgeons = new ArrayList<>();
        surgeons.add(surgeon);
        surgeons.add(surgeon);

        Dental dental =new Dental();
        Surgery surgery =new Surgery();
        dental.setDentists(dentists);
        dental.setName("DENTAL");
        surgery.setSurgeons(surgeons);
        surgery.setName("SURGERY");

        Hospital hospital = new Hospital();
        hospital.setAddress("Minsk");
        hospital.setPatients(patients);
        hospital.setDentists(dentists);
        hospital.setSurgeons(surgeons);

//        List<Department> departments = new ArrayList<>();
//        departments.add(dental);
//        departments.add(surgery);
//        hospital.setDepartments(departments);

        System.out.println("\nPRINT HOSPITAL");
        hospital.getPatients().forEach(p -> System.out.println(p));
//        departments.stream().forEach(department -> System.out.println(department.getName()));
//        hospital.getDepartments().forEach(department -> System.out.println(department.getName()));
//        hospital.getDepartments().get(0).getDoctors().forEach(doctor -> System.out.println(doctor));
//        hospital.getDepartments().get(1).getDoctors().forEach(doctor -> System.out.println(doctor));

//        hospital.getDepartments().stream()
//                .flatMap(d -> d.getDoctors().stream())
//                .forEach(doctor -> System.out.println(doctor));


        //писать результат сериализации будем в Writer(StringWriter)
        StringWriter writer = new StringWriter();

        //создание объекта Marshaller, который выполняет сериализацию
        JAXBContext context = JAXBContext.newInstance(Hospital.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // сама сериализация
        marshaller.marshal(hospital, writer);

        //преобразовываем в строку все записанное в StringWriter
        String result = writer.toString();
        System.out.println(result);

        File filejaxb = new File("jaxb.xml");
        FileUtils.write(filejaxb, result);


        // get variables from xml file, created before
        System.out.println("\nUnmarshaller create");
        Unmarshaller um = context.createUnmarshaller();
        System.out.println("\nget variables from xml file, created before");
        Hospital hospital2 = (Hospital) um.unmarshal(new FileReader("jaxb.xml"));

        System.out.println(hospital2);
        hospital2.getPatients().forEach(p -> System.out.println(p));
        hospital2.getDentists().forEach(d -> System.out.println(d));
        hospital2.getSurgeons().forEach(d -> System.out.println(d));
    }}