package io.xicp.myspace.region2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class LandscapeHandler extends DefaultHandler {
    final int NAME = 1;
    final int EN_NAME = 2;

    final int DESCRIBE = 3;
    final int IMAGE_DESCRIBE = 4;


    private LandscapeFeed landscapeFeed;
    private Landscape landscape;
    private Integer currentstate = 0;
    private LandscapeImage landscapeImage;
    public LandscapeHandler(){}
    public LandscapeFeed getLandscapeFeed(){
        return landscapeFeed;
    }
    @Override
    public void startDocument()  throws SAXException {
        System.out.println("startDocument");
        landscapeFeed = new LandscapeFeed();

    }
    @Override
    public void endDocument()  throws SAXException {
        System.out.println("endDocument");
    }
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes)   throws SAXException {
        System.out.println("startElement");

        if(localName.equals("landscape")){
            currentstate = 0;
            landscape = new Landscape();
            landscapeImage = new LandscapeImage();
            landscape.setEnName(attributes.getValue("en_name"));
            return;
        }
        if(localName.equals("image")){


            landscape.setEnName(attributes.getValue("en_name"));
            return;
        }
        if(localName.equals("name")){
            currentstate = NAME;
            return;
        }
        if(localName.equals("describe")){
            currentstate = DESCRIBE;
            return;
        }
        if(localName.equals("en_name")){
            currentstate = EN_NAME;
            return;
        }

        if(localName.equals("image_describe")){
            currentstate = IMAGE_DESCRIBE;

            return;
        }
        currentstate = 0;
    }
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        System.out.println("endElement");
        if(localName.equals("landscape")){
            landscape.addImage(landscapeImage);
            landscapeFeed.addItem(landscape);
            return;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        System.out.println("characters");
        System.out.println("length" + length);
        // TODO Auto-generated method stub
        String theString = new String(ch, start, length);
        switch(currentstate){
            case NAME:
                landscape.setName(theString);
                currentstate = 0;
                break;
            case DESCRIBE:
                landscape.setDescribe(theString);
                currentstate = 0;
                break;
            case IMAGE_DESCRIBE:
                landscapeImage.setImageDescribe(theString);
                landscape.addImage(landscapeImage);

                currentstate = 0;
                break;
            default:
                return;
        }
    }
}
