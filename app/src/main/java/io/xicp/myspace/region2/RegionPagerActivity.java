package io.xicp.myspace.region2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Xml;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

public class RegionPagerActivity extends FragmentActivity {
    private ViewPager viewPager;

    private List<Region> regionList = new ArrayList<Region>();


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);
        setContentView(viewPager);

        regionList = readxml();
        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
             Region region = regionList.get(position);
                return RegionFragment.newInstance(region.getEnName());
            }

            @Override
            public int getCount() {
                return regionList.size();
            }
        });
        String regionEnName = getIntent().getStringExtra(RegionFragment.EXTRA_REGION_ID);
        for(int i = 0; i < regionList.size(); i++){
            if(regionList.get(i).getEnName().equals(regionEnName)){
                viewPager.setCurrentItem(i);
                break ;
            }
        }
    }
//    public RegionFeed getFeed(){
//        try {
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser parser = factory.newSAXParser();
//            XMLReader reader = parser.getXMLReader();
//            RegionHandler handler = new RegionHandler();
//            reader.setContentHandler(handler);
//            InputSource is = new InputSource(this.getClass().getClassLoader().getResourceAsStream("assets/region.xml"));//取得本地xml文件
//
//            reader.parse(is);
//
//            return handler.getRegionFeed();
//        } catch (ParserConfigurationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (SAXException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
//    }
public List<Region> readxml() {
    List<Region> regionList = new ArrayList<Region>();
//        try {
//            File file = new File( new InputSource(this.getClass().getClassLoader().getResourceAsStream("assets/region.xml")));//取得本地xml文件);

//            if(!file.exists()){
//
//                try {
//                    file.createNewFile();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }else{
//
//            }

//            FileInputStream fis = new FileInputStream();
    // 获得pull解析器对象
    XmlPullParser parser = Xml.newPullParser();
    // 指定解析的文件和编码格式
    int eventType = 0;
    InputStream is = null;
    try {
        is = getAssets().open("region.xml");
        if (is != null) {
            parser.setInput(is, "utf-8");
        }


        eventType = parser.getEventType(); // 获得事件类型
    }catch (XmlPullParserException e){

    }catch (Exception e){

    }

    String en_name = null;
    String region_name = null;
    String region_abbreviation = null;
    String region_describe = null;
    String provicial_capital = null;
    String belong_to = null;
    try {
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagName = parser.getName(); // 获得当前节点的名称

            switch (eventType) {
                case XmlPullParser.START_TAG: // 当前等于开始节点 <person>
                    if ("region".equals(tagName)) { // <
                        en_name = parser.getAttributeValue(null, "en_name");
                    } else if ("region_name".equals(tagName)) { //
                        region_name = parser.nextText();
                    } else if ("region_abbreviation".equals(tagName)) { //
                        region_abbreviation = parser.nextText();
                    } else if ("region_describe".equals(tagName)) { //
                        region_describe = parser.nextText();
                    } else if ("provicial_capital".equals(tagName)) { //
                        provicial_capital = parser.nextText();
                    } else if ("belong_to".equals(tagName)) { //
                        belong_to = parser.nextText();
                    }


                    break;


                case XmlPullParser.END_TAG: // </persons>
                    if ("region".equals(tagName)) {
                        Region region = new Region();
                        region.setEnName(en_name);
                        region.setRegionName(region_name);
                        region.setAbbreviation(region_abbreviation);
                        region.setDescribe(region_describe);
                        region.setProvincialCapital(provicial_capital);
                        region.setBelongTo(belong_to);
                        regionList.add(region);
                        System.out.println(region);

                    }
                    break;
                default:
                    break;
            }
            eventType = parser.next(); // 获得下一个事件类型
        }
    }catch (IOException e){

    }catch (XmlPullParserException e){

    }catch (Exception e){

    }finally {

    }
    return regionList;
}
}
