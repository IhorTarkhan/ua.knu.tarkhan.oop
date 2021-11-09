package ua.knu.tarkhan.oop.lab2.parser.sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import ua.knu.tarkhan.oop.lab2.domain.TouristVoucher;

import java.util.ArrayList;
import java.util.List;

class MyHandler extends DefaultHandler {

    private final List<TouristVoucher> knives = new ArrayList<>();
    private StringBuilder data = null;

    private Integer id = null;
    private String type = null;
    private String handy = null;
    private String origin = null;
    private Integer length = null;
    private Integer width = null;
    private String mob = null;
    private String moh = null;
    private Boolean fuller = null;
    private Integer weight = null;
    private Boolean value = null;

    private String taq = "no taq";

    public List<TouristVoucher> getData() {
        return knives;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

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
        }

        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (taq) {
            case "id" -> this.id = Integer.parseInt(data.toString());
            case "type" -> this.type = data.toString();
            case "handy" -> this.handy = data.toString();
            case "origin" -> this.origin = data.toString();
            case "length" -> this.length = Integer.parseInt(data.toString());
            case "width" -> this.width = Integer.parseInt(data.toString());
            case "mob" -> this.mob = data.toString();
            case "moh" -> this.moh = data.toString();
            case "fuller" -> this.fuller = Boolean.parseBoolean(data.toString());
            case "weight" -> this.weight = Integer.parseInt(data.toString());
            case "value" -> this.value = Boolean.parseBoolean(data.toString());
        }
        taq = "no taq";

        if (qName.equalsIgnoreCase("knife")) {
//            knives.add(new Knife(id, type, handy, origin, length, width,
//                    mob, moh, fuller, weight, value));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        data.append(new String(ch, start, length));
    }
}
