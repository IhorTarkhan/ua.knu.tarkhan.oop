package ua.knu.tarkhan.oop.lab2.parser.sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import ua.knu.tarkhan.oop.lab2.domain.Hotel;
import ua.knu.tarkhan.oop.lab2.domain.TouristVoucher;
import ua.knu.tarkhan.oop.lab2.domain.Transport;
import ua.knu.tarkhan.oop.lab2.domain.Type;

import java.util.ArrayList;
import java.util.List;

class MyHandler extends DefaultHandler {
    private final List<TouristVoucher> result = new ArrayList<>();
    private StringBuilder value = null;
    private TouristVoucher current = null;
    private String taq = "";

    public List<TouristVoucher> getResult() {
        return result;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        taq = qName;
        value = new StringBuilder();
        if (qName.equalsIgnoreCase("touristVoucher")) {
            current = new TouristVoucher();
            current.setHotel(new Hotel());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (taq) {
            case "type" -> current.setType(Type.valueOf(value.toString().trim()));
            case "country" -> current.setCountry(value.toString().trim());
            case "days" -> current.setDays(Integer.valueOf(value.toString().trim()));
            case "transport" -> current.setTransport(Transport.valueOf(value.toString().trim()));
            case "stars" -> current.getHotel().setStars(Integer.valueOf(value.toString().trim()));
            case "sits" -> current.getHotel().setSits(Integer.valueOf(value.toString().trim()));
            case "isFood" -> current.getHotel().setIsFood(Boolean.valueOf(value.toString().trim()));
            case "isTV" -> current.getHotel().setIsTV(Boolean.valueOf(value.toString().trim()));
            case "cost" -> current.setCost(Integer.valueOf(value.toString().trim()));
        }

        if (qName.equalsIgnoreCase("touristVoucher")) {
            result.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        value.append(new String(ch, start, length));
    }
}
