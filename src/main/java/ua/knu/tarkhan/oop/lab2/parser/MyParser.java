package ua.knu.tarkhan.oop.lab2.parser;

import ua.knu.tarkhan.oop.lab2.domain.TouristVoucher;

import java.util.List;

public interface MyParser {
    List<TouristVoucher> parseXML(String xml_path, String xsd_path);
}
