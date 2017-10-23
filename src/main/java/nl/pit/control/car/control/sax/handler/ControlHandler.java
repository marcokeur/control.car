package nl.pit.control.car.control.sax.handler;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ControlHandler extends DefaultHandler {

    DefaultHandler handler;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if(handler != null){
            handler.startElement(uri, localName, qName, attributes);
        }else if(qName.equalsIgnoreCase("SimulatedController")) {
            handler = new SimulatedControllerHandler();
        }else if(qName.equalsIgnoreCase("DCCPlusPlusControllerHandler")) {
            handler = new DCCPlusPlusControllerHandler();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("SimulatedController")){
            handler = null;
        }else if(qName.equalsIgnoreCase("DCCPlusPlusControllerHandler")){
            handler = null;
        }

        if(handler != null) {
            handler.endElement(uri, localName, qName);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if(handler != null){
            handler.characters(ch, start, length);
        }
    }
}
