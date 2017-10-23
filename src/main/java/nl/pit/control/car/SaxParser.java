package nl.pit.control.car;

import nl.pit.control.car.control.sax.handler.ControlHandler;
import nl.pit.control.car.layout.block.sax.handler.BasicBlockHandler;
import nl.pit.control.car.layout.sax.handler.LayoutHandler;
import nl.pit.control.car.occupancy.OccupancyHandler;
import nl.pit.control.car.vehicle.sax.handler.VehicleHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class SaxParser extends DefaultHandler{
    public static void parse(InputStream xml) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        DefaultHandler handler = new SaxParser();

        saxParser.parse(xml, handler);
    }

    ContentHandler handler;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if(handler != null) {
            handler.startElement(uri, localName, qName, attributes);
        }else if(qName.equalsIgnoreCase("Control")) {
            if(handler == null) {
                handler = new ControlHandler();
            }else{
                throw new SAXException("Handler expected to be empty.");
            }
        }else if(qName.equalsIgnoreCase("Layout")) {
            if(handler == null) {
                handler = new LayoutHandler();
            }else{
                throw new SAXException("Handler expected to be empty.");
            }
        }else if(qName.equalsIgnoreCase("Vehicles")) {
            if(handler == null) {
                handler = new VehicleHandler();
            }else{
                throw new SAXException("Handler expected to be empty.");
            }
        }else if(qName.equalsIgnoreCase("Occupancy")) {
            if(handler == null) {
                handler = new OccupancyHandler();
            }else{
                throw new SAXException("Handler expected to be empty.");
            }
        }


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("Layout") ||
                qName.equalsIgnoreCase("Control") ||
                qName.equalsIgnoreCase("Vehicles")) {
            handler = null;
        }

        if(handler != null) {
            handler.endElement(uri, localName, qName);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if(handler != null) {
            handler.characters(ch, start, length);
        }
    }
}
