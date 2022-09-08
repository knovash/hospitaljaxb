package com.solvd.hospitaljaxb.utils;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.solvd.hospitaljaxb.Hospital;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ParseJSON implements IParse{

    @Override
    public void fileToObject(File file, Hospital hospital) throws IOException {

        // Jackson from JSON to Java Object
        ObjectMapper mapper = new ObjectMapper(); // create object mapper instance
        Hospital object = mapper.readValue(file, Hospital.class);
        System.out.println("convert JSON to Java Object");
        System.out.println(object);
        object.getPatients().forEach(p -> System.out.println(p));
        object.getDepartments().entrySet().stream()
                .peek(departmentEntry -> System.out.println(departmentEntry.getKey()))
                .flatMap(departmentEntry -> departmentEntry.getValue().getDoctors().stream())
                .forEach(doctor -> System.out.println(doctor));
    }

    @Override
    public void objectToFile(Hospital hospital, File file) throws IOException {
        // Jackson
        System.out.println("\nTEST Jackson Hosital");
        // convert hospital object to JSON
        String jsonOut = new ObjectMapper().writeValueAsString(hospital);
        System.out.println(jsonOut);
        // create object mapper instance
        ObjectMapper mapper = new ObjectMapper();
        // create an instance of DefaultPrettyPrinter
        ObjectWriter writerPP = mapper.writer(new DefaultPrettyPrinter());
        // convert object to JSON file DefaultPrettyPrinter
        writerPP.writeValue(Paths.get("hospital.json").toFile(), hospital);
    }
}
