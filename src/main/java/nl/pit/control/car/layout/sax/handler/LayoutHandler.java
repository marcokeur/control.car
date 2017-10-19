package nl.pit.control.car.layout.sax.handler;

import nl.pit.control.car.layout.block.sax.handler.BasicBlockHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LayoutHandler extends DefaultHandler{

    boolean bLayout = false;
    ContentHandler handler;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if(qName.equalsIgnoreCase("BasicBlock")) {
            handler = new BasicBlockHandler();
        }
        if(handler != null) {
            handler.startElement(uri, localName, qName, attributes);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(handler != null) {
            handler.endElement(uri, localName, qName);
        }

        if(qName == "BasicBlock") {
            handler = null;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if(handler != null) {
            handler.characters(ch, start, length);
        }
    }

}
