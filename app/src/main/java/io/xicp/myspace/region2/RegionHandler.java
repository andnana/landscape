package io.xicp.myspace.region2;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class RegionHandler extends DefaultHandler {
    final int REGION_NAME = 1;
    final int REGION_ABBREVIATION = 2;
    final int REGION_DESCRIBE = 3;
    final int PROVICIAL_CAPITAL = 4;
    final int BELONG_TO = 5;
    final int EN_NAME = 6;
    int currentstate = 0;
    private RegionFeed regionFeed;
    private Region region;
    public RegionHandler(){}
    public RegionFeed getRegionFeed(){
        return regionFeed;
    }
    @Override
    public void startDocument()  throws SAXException {
    System.out.println("startDocument");
        regionFeed = new RegionFeed();

    }
    @Override
    public void endDocument()  throws SAXException {
        System.out.println("endDocument");
    }
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes)   throws SAXException {
        System.out.println("startElement");
        if(localName.equals("regions")){
            currentstate = 0;
            return;
        }
        if(localName.equals("region")){
            region = new Region();
            region.setEnName(attributes.getValue("en_name"));
            return;
        }
        if(localName.equals("region_name")){
            currentstate = REGION_NAME;
            return;
        }
        if(localName.equals("region_abbreviation")){
            currentstate = REGION_ABBREVIATION;
            return;
        }
        if(localName.equals("region_describe")){
            currentstate = REGION_DESCRIBE;
            return;
        }
        if(localName.equals("provicial_capital")){
            currentstate = PROVICIAL_CAPITAL;
            return;
        }
        if(localName.equals("belong_to")){
            currentstate = BELONG_TO;
            return;
        }

        currentstate = 0;
    }
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        System.out.println("endElement");
        if(localName.equals("region")){
            regionFeed.addItem(region);
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
            case REGION_NAME:
                region.setRegionName(theString);
                currentstate = 0;
                break;
            case REGION_ABBREVIATION:
                region.setAbbreviation(theString);
                currentstate = 0;
                break;
            case REGION_DESCRIBE:
                region.setDescribe(theString);
                System.out.println(theString);
                currentstate = 0;
                break;
            case PROVICIAL_CAPITAL:
                region.setProvincialCapital(theString);
                currentstate = 0;
                break;
            case BELONG_TO:
                region.setBelongTo(theString);
                System.out.println(theString);
                System.out.println(region.getBelongTo());
                currentstate = 0;
                break;
            case EN_NAME:
                region.setEnName(theString);

                currentstate = 0;
            default:
                return;
        }
    }

}
