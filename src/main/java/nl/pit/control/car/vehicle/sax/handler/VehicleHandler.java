package nl.pit.control.car.vehicle.sax.handler;

import nl.pit.control.car.vehicle.Car;
import nl.pit.control.car.vehicle.Vehicle;
import nl.pit.control.car.vehicle.VehicleManager;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class VehicleHandler extends DefaultHandler{

    Vehicle v = null;

    boolean bAddress = false;
    boolean bLength = false;
    boolean bStep = false;
    Integer stepIndex;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if(qName.equalsIgnoreCase("Vehicle")) {
            if(attributes.getValue("type").equalsIgnoreCase("Car")) {
                v = new Car();
            }
        }else if(qName.equalsIgnoreCase("Address")){
            bAddress = true;
        }else if(qName.equalsIgnoreCase("Length")){
            bLength = true;
        }else if(qName.equalsIgnoreCase("Step")){
            stepIndex = Integer.parseInt(attributes.getValue("index"));
            bStep = true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(qName == "Vehicle") {
            VehicleManager.getInstance().addVehicle(v);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        String str = new String(ch, start,length);
        if(bAddress){
            v.setAddress(Integer.parseInt(str));
            bAddress = false;
        }

        if(bLength){
            v.setLength(Integer.parseInt(str));
            bLength = false;
        }

        if(bStep){
            v.addSpeedStep(stepIndex, Double.parseDouble(str));
            bStep = false;
            stepIndex = null;
        }
    }

}
