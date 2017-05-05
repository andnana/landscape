package io.xicp.myspace.region2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
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
    private RegionFeed regionFeed;
    private List<Region> regionList = new ArrayList<Region>();


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);
        setContentView(viewPager);
        regionFeed = getFeed();
        regionList = regionFeed.getAllRegionList();
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
        String regionId = getIntent().getStringExtra(RegionFragment.EXTRA_REGION_ID);
        for(int i = 0; i < regionList.size(); i++){
            if(regionList.get(i).getEnName().equals(regionId)){
                viewPager.setCurrentItem(i);
                break ;
            }
        }
    }
    public RegionFeed getFeed(){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            RegionHandler handler = new RegionHandler();
            reader.setContentHandler(handler);
            InputSource is = new InputSource(this.getClass().getClassLoader().getResourceAsStream("assets/region.xml"));//取得本地xml文件
            System.out.println("abcd");
            System.out.println(is+"is");

            reader.parse(is);

            return handler.getRegionFeed();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
