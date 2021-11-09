package ua.knu.tarkhan.oop.lab2;

import ua.knu.tarkhan.oop.Path;
import ua.knu.tarkhan.oop.lab2.validator.ValidatorXML;

public class Main {
    public static void main(String[] args) {
        ValidatorXML.validateAgainstXSD(Path.ROOT + "lab2/data/touristVouchers.xml", Path.ROOT + "lab2/data/touristVouchers.xsd");
    }
}
