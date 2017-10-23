package nl.pit.control.car.occupancy;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class OccupancyHandler extends DefaultHandler {
    Detector detector = null;
    boolean bAddress = false;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if(qName.equalsIgnoreCase("Detector")){
            detector = new Detector(DetectorManager.getInstance(), Integer.parseInt(attributes.getValue("id")));
        }else if(qName.equalsIgnoreCase("Address")){
            bAddress = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("Detector")){
            DetectorManager.getInstance().addDetector(detector);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        String str = new String(ch, start, length);

        if(bAddress){
            detector.setAddress(Integer.parseInt(str));
            bAddress = false;
        }
    }
}
