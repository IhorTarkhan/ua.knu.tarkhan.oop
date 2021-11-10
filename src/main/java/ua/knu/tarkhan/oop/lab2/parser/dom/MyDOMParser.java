package ua.knu.tarkhan.oop.lab2.parser.dom;

import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ua.knu.tarkhan.oop.lab2.domain.Hotel;
import ua.knu.tarkhan.oop.lab2.domain.TouristVoucher;
import ua.knu.tarkhan.oop.lab2.domain.Transport;
import ua.knu.tarkhan.oop.lab2.domain.Type;
import ua.knu.tarkhan.oop.lab2.parser.MyParser;
import ua.knu.tarkhan.oop.lab2.validator.ValidatorXML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyDOMParser implements MyParser {
    @SneakyThrows
    public List<TouristVoucher> parseXML(String xml_path, String xsd_path) {
        ValidatorXML.validateAgainstXSD(xml_path, xsd_path);
        List<TouristVoucher> knives = new ArrayList<>();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        Document document;
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        document = builder.parse(new File(xml_path));
        Element rootElement = document.getDocumentElement();
        NodeList nodes = rootElement.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element element) {
                knives.add(
                        new TouristVoucher(
                                Type.valueOf(getString(element, "type")),
                                getString(element, "country"),
                                Integer.valueOf(getString(element, "days")),
                                Transport.valueOf(getString(element, "transport")),
                                new Hotel(
                                        Integer.valueOf(getString(element, "stars")),
                                        Integer.valueOf(getString(element, "sits")),
                                        Boolean.valueOf(getString(element, "isFood")),
                                        Boolean.valueOf(getString(element, "isTV"))
                                ),
                                Integer.valueOf(getString(element, "cost"))
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
