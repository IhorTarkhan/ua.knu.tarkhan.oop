package ua.knu.tarkhan.oop.lab2;

import ua.knu.tarkhan.oop.Path;
import ua.knu.tarkhan.oop.lab2.parser.dom.MyDOMParser;
import ua.knu.tarkhan.oop.lab2.parser.sax.MySAXParser;

public class Main {
    public static void main(String[] args) {
        String xml_path = Path.ROOT + "lab2/data/touristVouchers.xml";
        String xsd_path = Path.ROOT + "lab2/data/touristVouchers.xsd";

        System.out.println("DOM parser");
        new MyDOMParser().parseXML(xml_path, xsd_path).forEach(System.out::println);

        System.out.println("SAX parser");
        new MySAXParser().parseXML(xml_path, xsd_path).forEach(System.out::println);
    }
}
