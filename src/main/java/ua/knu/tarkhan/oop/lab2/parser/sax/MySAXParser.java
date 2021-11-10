package ua.knu.tarkhan.oop.lab2.parser.sax;

import lombok.SneakyThrows;
import org.xml.sax.helpers.DefaultHandler;
import ua.knu.tarkhan.oop.lab2.domain.TouristVoucher;
import ua.knu.tarkhan.oop.lab2.parser.MyParser;
import ua.knu.tarkhan.oop.lab2.validator.ValidatorXML;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

public class MySAXParser extends DefaultHandler implements MyParser {
    @SneakyThrows
    public List<TouristVoucher> parseXML(String xml_path, String xsd_path) {
        ValidatorXML.validateAgainstXSD(xml_path, xsd_path);
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        MyHandler handler = new MyHandler();
        saxParser.parse(new File(xml_path), handler);
        return handler.getResult();
    }
}

