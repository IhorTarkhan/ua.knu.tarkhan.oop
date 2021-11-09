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
import java.util.ArrayList;
import java.util.List;

public class MySAXParser extends DefaultHandler {
    public List<TouristVoucher> parseXML(String xml_path, String xsd_path) {
        ValidatorXML.validateAgainstXSD(xml_path, xsd_path);
        List<TouristVoucher> data = new ArrayList<>();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handler = new MyHandler();
            saxParser.parse(new File(xml_path), handler);
            data = handler.getResult();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

