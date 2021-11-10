package ua.knu.tarkhan.oop.lab2.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.knu.tarkhan.oop.Path;

class ValidatorXMLTest {
    public static final String ROOT = Path.ROOT.replace("main", "test");
    public static final String PATH_XSD = ROOT + "lab2/data/touristVouchers.xsd";
    public static final String VALID_XML = ROOT + "lab2/data/touristVouchers-test1.xml";
    public static final String INVALID_XML = ROOT + "lab2/data/touristVouchers-test2.xml";

    @Test
    void invalidFileTest() {
        Assertions.assertThrows(RuntimeException.class, () -> ValidatorXML.validateAgainstXSD(INVALID_XML, PATH_XSD));
    }

    @Test
    void validFileTest() {
        ValidatorXML.validateAgainstXSD(VALID_XML, PATH_XSD);
    }
}
