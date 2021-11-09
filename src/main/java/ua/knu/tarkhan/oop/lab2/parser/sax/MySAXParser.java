package ua.knu.tarkhan.oop.lab2.parser.sax;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.knu.tarkhan.oop.lab2.domain.TouristVoucher;
import ua.knu.tarkhan.oop.lab2.validator.ValidatorXML;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MySAXParser extends DefaultHandler {
    private final String xml_path;
    private final String xsd_path;

    public MySAXParser(String xml_path, String xsd_path) {
        this.xsd_path = xsd_path;
        this.xml_path = xml_path;
    }

    public List<TouristVoucher> parseXML() {
        ValidatorXML.validateAgainstXSD(xml_path, xsd_path);
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handler = new MyHandler();
            saxParser.parse(new File(xml_path), handler);
            return handler.getData();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

