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
    public void fileToObject(File file, Hospital hospital) throws JAXBException {

        // JAXB parser from XML to JavaObject and from object to XML.
        System.out.println("\nStart JAXB parser. from XML to JavaObject\n");
        JAXBContext context = JAXBContext.newInstance(Hospital.class);
        // from XML to Object
        Unmarshaller um = context.createUnmarshaller();
        Hospital object = (Hospital) um.unmarshal(file);

        System.out.println(object);
        object.getPatients().forEach(p -> System.out.println(p));
        object.getDepartments().entrySet().stream()
                .peek(departmentEntry -> System.out.println(departmentEntry.getKey()))
                .flatMap(departmentEntry -> departmentEntry.getValue().getDoctors().stream())
                .forEach(doctor -> System.out.println(doctor));
    }

    @Override
    public void objectToFile(Hospital hospital, File file) throws JAXBException, IOException {
        System.out.println("\nJAXB objectToFile\n");
        //писать результат сериализации будем в Writer(StringWriter)
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Hospital.class);
        // from Object to XML
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // сама сериализация
        marshaller.marshal(hospital, writer);
        //преобразовываем в строку все записанное в StringWriter
        String result = writer.toString();
        System.out.println(result);
//        File filejaxb = new File("jaxb.xml");
        FileUtils.writeStringToFile(file, result, Charset.defaultCharset());
    }
}