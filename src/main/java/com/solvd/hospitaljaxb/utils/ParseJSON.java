package com.solvd.hospitaljaxb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.solvd.hospitaljaxb.Hospital;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ParseJSON implements IParse {

    @Override
    public void fileToObject(File file, Hospital hospital) {

        /** Jackson from JSON to Java Object */
        ObjectMapper mapper = new ObjectMapper();
        Hospital object = null;
        try {
            object = mapper.readValue(file, Hospital.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("from JSON file to Java Object\n");
        System.out.println(object);
        object.getPatients().forEach(p -> System.out.println(p));
        object.getDepartments().entrySet().stream()
                .peek(departmentEntry -> System.out.println(departmentEntry.getKey()))
                .flatMap(departmentEntry -> departmentEntry.getValue().getDoctors().stream())
                .forEach(doctor -> System.out.println(doctor));
    }

    @Override
    public void objectToFile(Hospital hospital, File file) {
        /** convert hospital object to JSON */
        System.out.println("\nJackson object to JSON\n");
        String jsonOut = null;
        try {
            jsonOut = new ObjectMapper().writeValueAsString(hospital);
            System.out.println(jsonOut);
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writerPP = mapper.writer(new DefaultPrettyPrinter());
            writerPP.writeValue(Paths.get("hospital.json").toFile(), hospital);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("JSON saved to file \n");
    }
}
