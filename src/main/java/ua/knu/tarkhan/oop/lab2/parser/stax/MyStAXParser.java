package ua.knu.tarkhan.oop.lab2.parser.stax;


import lombok.SneakyThrows;
import ua.knu.tarkhan.oop.lab2.domain.Hotel;
import ua.knu.tarkhan.oop.lab2.domain.TouristVoucher;
import ua.knu.tarkhan.oop.lab2.domain.Transport;
import ua.knu.tarkhan.oop.lab2.domain.Type;
import ua.knu.tarkhan.oop.lab2.validator.ValidatorXML;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.XMLEvent;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MyStAXParser {
    @SneakyThrows
    public List<TouristVoucher> parseXML(String xml_path, String xsd_path) {
        ValidatorXML.validateAgainstXSD(xml_path, xsd_path);
        List<TouristVoucher> result = new ArrayList<>();
        TouristVoucher current = new TouristVoucher();
        String tag = "";

        XMLEventReader eventReader =
                XMLInputFactory.newInstance().createXMLEventReader(new FileReader(xml_path));

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT -> {
                    String qName = event.asStartElement().getName().getLocalPart();
                    tag = qName;
                    if (qName.equalsIgnoreCase("touristVoucher")) {
                        current = new TouristVoucher();
                        current.setHotel(new Hotel());
                    }
                }
                case XMLStreamConstants.CHARACTERS -> {
                    String value = event.asCharacters().toString().trim();
                    if ("".equals(value)) {
                        continue;
                    }
                    switch (tag) {
                        case "type" -> current.setType(Type.valueOf(value));
                        case "country" -> current.setCountry(value);
                        case "days" -> current.setDays(Integer.valueOf(value));
                        case "transport" -> current.setTransport(Transport.valueOf(value));
                        case "stars" -> current.getHotel().setStars(Integer.valueOf(value));
                        case "sits" -> current.getHotel().setSits(Integer.valueOf(value));
                        case "isFood" -> current.getHotel().setIsFood(Boolean.valueOf(value));
                        case "isTV" -> current.getHotel().setIsTV(Boolean.valueOf(value));
                        case "cost" -> current.setCost(Integer.valueOf(value));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    String qName = event.asEndElement().getName().getLocalPart();
                    if (qName.equalsIgnoreCase("touristVoucher")) {
                        result.add(current);
                    }
                }
            }
        }
        return result;
    }
}
