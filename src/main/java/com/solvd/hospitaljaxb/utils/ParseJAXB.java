package com.solvd.hospitaljaxb.utils;

import com.solvd.hospitaljaxb.Hospital;
import org.apache.commons.io.FileUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

public class ParseJAXB implements IParse {

    @Override
    public void fileToObject(File file, Hospital hospital) {

        /** JAXB parser from XML to JavaObject and from object to XML */
        System.out.println("\nStart JAXB parser. from XML to JavaObject\n");
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Hospital.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        /** from XML to Object */
        Hospital object = null;
        Unmarshaller um = null;
        try {
            um = context.createUnmarshaller();
            object = (Hospital) um.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        System.out.println(object);
        object.getPatients().forEach(p -> System.out.println(p));
        object.getDepartments().entrySet().stream()
                .peek(departmentEntry -> System.out.println(departmentEntry.getKey()))
                .flatMap(departmentEntry -> departmentEntry.getValue().getDoctors().stream())
                .forEach(doctor -> System.out.println(doctor));
    }

    @Override
    public void objectToFile(Hospital hospital, File file) {
        /** from Object to XML */
        System.out.println("\nJAXB objectToFile\n");
        StringWriter writer = new StringWriter();
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Hospital.class);
            Marshaller marshaller = null;
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(hospital, writer);
            String result = writer.toString();
            System.out.println(result);
            FileUtils.writeStringToFile(file, result, Charset.defaultCharset());
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}