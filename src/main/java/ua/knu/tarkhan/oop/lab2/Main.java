package ua.knu.tarkhan.oop.lab2;

import ua.knu.tarkhan.oop.Path;
import ua.knu.tarkhan.oop.lab2.parser.dom.MyDOMParser;

public class Main {
    public static void main(String[] args) {
        System.out.println("DOM parser");
        MyDOMParser p = new MyDOMParser(Path.ROOT + "lab2/data/touristVouchers.xml", Path.ROOT + "lab2/data/touristVouchers.xsd");
        var knives = p.parseXML();
        for (var k : knives) {
            System.out.println(k);
        }
    }
}
