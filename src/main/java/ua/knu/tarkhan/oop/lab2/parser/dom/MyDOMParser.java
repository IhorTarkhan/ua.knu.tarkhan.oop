package ua.knu.tarkhan.oop.lab2.parser.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ua.knu.tarkhan.oop.lab2.domain.Hotel;
import ua.knu.tarkhan.oop.lab2.domain.TouristVoucher;
import ua.knu.tarkhan.oop.lab2.domain.Transport;
import ua.knu.tarkhan.oop.lab2.domain.Type;
import ua.knu.tarkhan.oop.lab2.validator.ValidatorXML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyDOMParser {
    private final String xml_path;
    private final String xsd_path;

    public MyDOMParser(String xml_path, String xsd_path) {
        this.xml_path = xml_path;
        this.xsd_path = xsd_path;
    }

    public List<TouristVoucher> parseXML() {
        ValidatorXML.validateAgainstXSD(xml_path, xsd_path);
        List<TouristVoucher> knives = new ArrayList<>();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        Document document;
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            document = builder.parse(new File(xml_path));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        Element rootElement = document.getDocumentElement();
        NodeList nodes = rootElement.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element element) {
                knives.add(
                        new TouristVoucher(
                                Type.valueOf(getString(element, "type")),
                                getString(element, "country"),
                                Integer.parseInt(getString(element, "days")),
                                Transport.valueOf(getString(element, "transport")),
                                new Hotel(
                                        Integer.parseInt(getString(element, "stars")),
                                        Integer.parseInt(getString(element, "sits")),
                                        Boolean.valueOf(getString(element, "isFood")),
                                        Boolean.valueOf(getString(element, "isTV"))
                                ),
                                Integer.parseInt(getString(element, "cost"))
                        )
                );
            }
        }
        return knives;
    }

    private String getString(Element element, String key) {
        return element.getElementsByTagName(key).item(0).getTextContent();
    }
}
