package nl.pit.control.car.layout.block.sax.handler;

import nl.pit.control.car.layout.LayoutManager;
import nl.pit.control.car.layout.block.BasicBlock;
import nl.pit.control.car.layout.block.BlockFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BasicBlockHandler extends DefaultHandler{
    private BasicBlock basicBlock = null;

    boolean bName = false;
    boolean bLength = false;
    boolean bNext = false;
    boolean bPrev = false;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("BasicBlock")) {
            basicBlock = (BasicBlock)BlockFactory.getBlock("Basic");
            basicBlock.setId(Integer.parseInt(attributes.getValue("id")));
        } else if (qName.equalsIgnoreCase("name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("length")) {
            bLength = true;
        } else if (qName.equalsIgnoreCase("next")) {
            bNext = true;
        } else if (qName.equalsIgnoreCase("prev")) {
            bPrev = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("BasicBlock")) {
            LayoutManager.getInstance().addBlock(basicBlock);
            basicBlock = null;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bName) {
            basicBlock.setName(new String(ch, start, length));
            bName = false;
        } else if (bLength) {
            basicBlock.setLength(Integer.parseInt(new String(ch, start, length)));
            bLength = false;
        } else if (bNext){
            basicBlock.setNextId(Integer.parseInt(new String(ch, start, length)));
            bNext = false;
        } else if(bPrev){
            basicBlock.setPrevId(Integer.parseInt(new String(ch, start, length)));
            bPrev = false;
        }
    }
}
