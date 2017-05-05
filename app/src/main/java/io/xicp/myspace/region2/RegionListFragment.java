package io.xicp.myspace.region2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
 * Created by Administrator on 2017/4/29 0029.
 */

public class RegionListFragment extends ListFragment {
    private RegionFeed regionFeed;
    private List<Region> regionList = new ArrayList<Region>();
    private ImageView upDownTipsImageView ;
    private ImageView clickTipsImageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.region_title);
        System.out.println("#$#$#$");
        regionFeed = getFeed();

            regionList = regionFeed.getAllRegionList();
            System.out.println(regionList.size());
        upDownTipsImageView = (ImageView)getActivity().findViewById(R.id.up_down_tips);
        clickTipsImageView = (ImageView)getActivity().findViewById(R.id.click_tips);
        SharedPreferences sharedata = getActivity().getSharedPreferences("fristrun", 0);
        Boolean isUpDown = sharedata.getBoolean("isUpDown", true);
        if (isUpDown) {
            if(upDownTipsImageView != null){
                upDownTipsImageView.setVisibility(View.VISIBLE);
            }


        } else {
            if(upDownTipsImageView != null) {
                upDownTipsImageView.setVisibility(View.INVISIBLE);
            }
        }
        Boolean isListClick = sharedata.getBoolean("isListClick",false);
        if(isListClick){
            if(clickTipsImageView != null){
                clickTipsImageView.setVisibility(View.VISIBLE);
            }
        }else {
            if(clickTipsImageView != null){
                clickTipsImageView.setVisibility(View.INVISIBLE);
            }
        }
        if(upDownTipsImageView != null){
            upDownTipsImageView.setOnClickListener(new View.OnClickListener() {
                SharedPreferences.Editor sharedata = getActivity().getSharedPreferences("fristrun", 0).edit();
                public void onClick(View v) {
                    upDownTipsImageView.setVisibility(View.INVISIBLE);
                    sharedata.putBoolean("isUpDown", false);
                    if(clickTipsImageView != null){
                        clickTipsImageView.setVisibility(View.VISIBLE);
                        sharedata.putBoolean("isListClick", true);
                    }




                    sharedata.commit();
                }
            });
        }
        if(clickTipsImageView != null){
            clickTipsImageView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    clickTipsImageView.setVisibility(View.INVISIBLE);
                    SharedPreferences.Editor sharedata = getActivity().getSharedPreferences("fristrun", 0).edit();
                    sharedata.putBoolean("isListClick", false);
                    sharedata.commit();
                }
            });
        }

//        regionList.add(new Region("abc"));
//        regionList.add(new Region("def"));
//        ArrayAdapter<Region> adapter = new ArrayAdapter<Region>(getActivity(),android.R.layout.simple_list_item_1,regionList);
//        setListAdapter(adapter);
        RegionAdapter adapter = new RegionAdapter(regionList);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Region region = regionFeed.getRegionItem(position);
//        System.out.println(region.getRegionName());
//        System.out.println(region.getEnName());
//        Intent intent = new Intent(getActivity(), RegionActivity.class);
        Intent intent = new Intent(getActivity(), RegionPagerActivity.class);
        intent.putExtra(RegionFragment.EXTRA_REGION_ID, region.getEnName());
        startActivity(intent);
    }
    private class RegionAdapter extends ArrayAdapter<Region> {
        public RegionAdapter(List<Region> regions){
            super(getActivity(), 0, regions);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_region, null);
            }
            Region region = getItem(position);
            TextView regionNameTextView = (TextView)convertView.findViewById(R.id.region_name);
            regionNameTextView.setText(region.getRegionName());
            TextView abbreviationTextView = (TextView)convertView.findViewById(R.id.abbreviation);
            abbreviationTextView.setText(region.getAbbreviation());
            TextView blongToTextView = (TextView)convertView.findViewById(R.id.belong_to);
            blongToTextView.setText(region.getBelongTo());
            return convertView;

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
