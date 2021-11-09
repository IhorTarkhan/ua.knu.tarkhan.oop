package ua.knu.tarkhan.oop.lab2.parser.stax;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyStAXParser {
    /*private final String xml_path;
    private final String xsd_path;

    public MyStAXParser(String xml_path, String xsd_path) {
        this.xml_path = xml_path;
        this.xsd_path = xsd_path;
    }

    private boolean validateXMLByXSD() {
        return ValidatorXML.validateAgainstXSD(xml_path, xsd_path);
    }

    public List<Knife> parseXML() throws IOException {
        if (!validateXMLByXSD())
            throw new IOException();
        List<Knife> knives = new ArrayList<>();
        Integer id = null;
        String type = "";
        String handy = "";
        String origin = null;
        int length = 100;
        int width = 5;
        String mob = null;
        String moh = null;
        boolean fuller = true;
        int weight = 2;
        boolean value = false;

        String taq = "no taq";

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader(xml_path));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("id")) {
                            taq = "id";
                        } else if (qName.equalsIgnoreCase("type")) {
                            taq = "type";
                        } else if (qName.equalsIgnoreCase("handy")) {
                            taq = "handy";
                        } else if (qName.equalsIgnoreCase("origin")) {
                            taq = "origin";
                        } else if (qName.equalsIgnoreCase("length")) {
                            taq = "length";
                        } else if (qName.equalsIgnoreCase("width")) {
                            taq = "width";
                        } else if (qName.equalsIgnoreCase("material_of_blade")) {
                            taq = "mob";
                        } else if (qName.equalsIgnoreCase("material_of_handle")) {
                            taq = "moh";
                        } else if (qName.equalsIgnoreCase("fuller")) {
                            taq = "fuller";
                        } else if (qName.equalsIgnoreCase("weight")) {
                            taq = "weight";
                        } else if (qName.equalsIgnoreCase("value")) {
                            taq = "value";
                        } else {
                            taq = "no taq";
                        }
                    }
                    case XMLStreamConstants.CHARACTERS -> {
                        Characters characters = event.asCharacters();
                        switch (taq) {
                            case "id" -> id = Integer.parseInt(characters.toString());
                            case "type" -> type = characters.toString();
                            case "handy" -> handy = characters.toString();
                            case "origin" -> origin = characters.toString();
                            case "length" -> length = Integer.parseInt(characters.toString());
                            case "width" -> width = Integer.parseInt(characters.toString());
                            case "mob" -> mob = characters.toString();
                            case "moh" -> moh = characters.toString();
                            case "fuller" -> fuller = Boolean.parseBoolean(characters.toString());
                            case "weight" -> weight = Integer.parseInt(characters.toString());
                            case "value" -> value = Boolean.parseBoolean(characters.toString());
                        }
                        taq = "no taq";
                    }
                    case XMLStreamConstants.END_ELEMENT -> {
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equalsIgnoreCase("knife")) {
                            knives.add(new Knife(id, type, handy, origin, length, width,
                                    mob, moh, fuller, weight, value));
                        }
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return knives;
    }*/

}
