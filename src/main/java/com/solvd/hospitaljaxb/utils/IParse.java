package com.solvd.hospitaljaxb.utils;

import com.solvd.hospitaljaxb.Hospital;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public interface IParse {
    public void fileToObject(File file, Hospital hospital) throws JAXBException, ParserConfigurationException, SAXException, IOException;
    public void objectToFile(Hospital hospital, File file) throws JAXBException, IOException;
}
