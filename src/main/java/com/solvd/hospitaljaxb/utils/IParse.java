package com.solvd.hospitaljaxb.utils;

import com.solvd.hospitaljaxb.Hospital;

import java.io.File;

public interface IParse {

    void fileToObject(File file, Hospital hospital);

    void objectToFile(Hospital hospital, File file);
}
