package com.solvd;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.solvd.department.DepDental;
import com.solvd.department.DepSurgery;
import com.solvd.department.Department;
import org.apache.commons.io.FileUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<Doctor> dentists = new ArrayList<>();
        dentists.add(dentist);
        dentists.add(dentist);

        List<Doctor> surgeons = new ArrayList<>();
        surgeons.add(surgeon);
        surgeons.add(surgeon);

        DepDental depDental = new DepDental();
        depDental.setDepName("dental");
        depDental.setDoctors(dentists);

        DepSurgery depSurgery = new DepSurgery();
        depSurgery.setDepName("surgery");
        depSurgery.setDoctors(surgeons);


        Hospital hospital = new Hospital();
        hospital.setAddress("Minsk");
        hospital.setPatients(patients);
//        hospital.setDeps(departments);

        Map<String, Department> departments2 = new HashMap<>();
        departments2.put("dnt", depDental);
        departments2.put("sur", depSurgery);
        hospital.setDepartments(departments2);

        System.out.println("\nPRINT HOSPITAL");
        hospital.getPatients().forEach(p -> System.out.println(p));


        // JAXB

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
//        FileUtils.write(filejaxb, result); // Deprecated
        FileUtils.writeStringToFile(filejaxb, result, Charset.defaultCharset());


        // get variables from xml file, created before
        System.out.println("\nUnmarshaller create");
        Unmarshaller um = context.createUnmarshaller();
        System.out.println("\nget variables from xml file, created before");
        Hospital hospital2 = (Hospital) um.unmarshal(new FileReader("jaxb.xml"));

        System.out.println(hospital2);
        hospital2.getPatients().forEach(p -> System.out.println(p));
        hospital2.getDepartments().entrySet().stream()
                .peek(departmentEntry -> System.out.println(departmentEntry.getKey()))
                .flatMap(departmentEntry -> departmentEntry.getValue().getDoctors().stream())
                .forEach(doctor -> System.out.println(doctor));

        // Jackson

        System.out.println("\nTEST Jackson Hosital");
        // convert hospital object to JSON
        String jsonOut = new ObjectMapper().writeValueAsString(hospital);
        System.out.println(jsonOut);
        // create object mapper instance
        ObjectMapper mapper = new ObjectMapper();
        // create an instance of DefaultPrettyPrinter
        ObjectWriter writerPP = mapper.writer(new DefaultPrettyPrinter());

        // convert object to JSON file
//        mapper.writeValue(Paths.get("hospital.json").toFile(), hospital);
        // convert object to JSON file DefaultPrettyPrinter
        writerPP.writeValue(Paths.get("hospital.json").toFile(), hospital);



        // convert JSON to Java Object
        Hospital hospital3 = mapper.readValue(jsonOut, Hospital.class);
        System.out.println("convert JSON to Java Object");
        System.out.println(hospital3);
        hospital3.getPatients().forEach(p -> System.out.println(p));
        hospital3.getDepartments().entrySet().stream()
                .peek(departmentEntry -> System.out.println(departmentEntry.getKey()))
                .flatMap(departmentEntry -> departmentEntry.getValue().getDoctors().stream())
                .forEach(doctor -> System.out.println(doctor));
    }
}