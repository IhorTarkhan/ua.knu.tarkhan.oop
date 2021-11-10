package ua.knu.tarkhan.oop.lab2.parser;

import org.junit.jupiter.api.Test;
import ua.knu.tarkhan.oop.Path;
import ua.knu.tarkhan.oop.lab2.domain.Hotel;
import ua.knu.tarkhan.oop.lab2.domain.TouristVoucher;
import ua.knu.tarkhan.oop.lab2.domain.Transport;
import ua.knu.tarkhan.oop.lab2.domain.Type;
import ua.knu.tarkhan.oop.lab2.parser.dom.MyDOMParser;
import ua.knu.tarkhan.oop.lab2.parser.sax.MySAXParser;
import ua.knu.tarkhan.oop.lab2.parser.stax.MyStAXParser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyParserTest {
    public static final String ROOT = Path.ROOT.replace("main", "test");
    public static final String VALID_XML = ROOT + "lab2/data/touristVouchers-test1.xml";
    public static final String INVALID_XML = ROOT + "lab2/data/touristVouchers-test2.xml";
    public static final String PATH_XSD = ROOT + "lab2/data/touristVouchers.xsd";
    public static final List<TouristVoucher> LIST = List.of(
            new TouristVoucher(
                    Type.WEEKEND,
                    "UK",
                    3,
                    Transport.AIR,
                    new Hotel(5, 1, true, true),
                    3000
            ),
            new TouristVoucher(
                    Type.EXCURSION,
                    "USA",
                    5,
                    Transport.RAILWAY,
                    new Hotel(3, 1, true, false),
                    15000
            ));

    void invalidFileTest(MyParser myParser) {
        assertThrows(RuntimeException.class, () -> myParser.parseXML(MyParserTest.INVALID_XML, MyParserTest.PATH_XSD));
    }

    void validFileTest(MyParser myParser) {
        List<TouristVoucher> touristVouchers = myParser.parseXML(MyParserTest.VALID_XML, MyParserTest.PATH_XSD);
        assertEquals(MyParserTest.LIST.size(), touristVouchers.size());
        for (int i = 0; i < MyParserTest.LIST.size(); i++) {
            assertEquals(MyParserTest.LIST.get(i), touristVouchers.get(i));
        }
    }

    @Test
    void domParsTest() {
        invalidFileTest(new MyDOMParser());
        validFileTest(new MyDOMParser());
    }

    @Test
    void saxParsTest() {
        invalidFileTest(new MySAXParser());
        validFileTest(new MySAXParser());
    }

    @Test
    void staxParsTest() {
        invalidFileTest(new MyStAXParser());
        validFileTest(new MyStAXParser());
    }
}
