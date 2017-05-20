package io.xicp.myspace.region2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;

import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import cse.der.qsw.AdManager;
import cse.der.qsw.nm.bn.BannerManager;
import cse.der.qsw.nm.bn.BannerViewListener;
import cse.der.qsw.onlineconfig.OnlineConfigCallBack;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2017/4/29 0029.
 */

public class RegionListFragment extends Fragment {

    private List<Region> regionList = new ArrayList<Region>();
    private ImageView upDownTipsImageView ;
    private ImageView clickTipsImageView;
    private LinearLayout bannerLayout;
    private View bannerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActivity().setTitle(R.string.app_name);

//        regionFeed = readxml();

            regionList = readxml();




    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.region_list_activity_fragment, container, false);

        AdManager.getInstance(getActivity()).init("3cf12c7d8c82c297", "a8414f5f3deeea12", true);
         bannerView = BannerManager.getInstance(getActivity())
                .getBannerView(getActivity(),  new BannerViewListener(){

                    /**
                     * 请求广告成功
                     */
                    @Override
                    public void onRequestSuccess() {

                    }

                    /**
                     * 切换广告条
                     */
                    @Override
                    public void onSwitchBanner() {

                    }

                    /**
                     * 请求广告失败
                     */
                    @Override
                    public void onRequestFailed() {

                    }
                });

// 获取要嵌入广告条的布局
        bannerLayout = (LinearLayout)v.findViewById(R.id.ll_banner);
        AdManager.getInstance(getActivity()).asyncGetOnlineConfig("isOpen",new MyOnlineConfigCallBack());
// 将广告条加入到布局中




        ImageView backImageView = (ImageView)v.findViewById(R.id.back);
        ListView regionListView = (ListView)v.findViewById(R.id.regionList);
        RegionAdapter adapter = new RegionAdapter(regionList);
        regionListView.setAdapter(adapter);
        regionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                Region region = regionList.get(position);

                Intent intent = new Intent(getActivity(), RegionPagerActivity.class);
                intent.putExtra(RegionFragment.EXTRA_REGION_ID, region.getEnName());
                startActivity(intent);
            }
        });
        backImageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                Intent intent = new Intent(getActivity(), LandscapeListActivity.class);
//                intent.putExtra(RegionFragment.EXTRA_REGION_ID, regionEnName);
//                startActivity(intent);
                getActivity().finish();
            }
        });


        upDownTipsImageView = (ImageView)v.findViewById(R.id.up_down_tips);
        clickTipsImageView = (ImageView)v.findViewById(R.id.click_tips);


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
        return v ;

    }

    class MyOnlineConfigCallBack implements OnlineConfigCallBack {
        @Override
        public void onGetOnlineConfigSuccessful(String key, String value) {
            // TODO Auto-generated method stub
            // 获取在线参数成功

            if (key.equals("isOpen")) {
                if (value.equals("1")) {

                    bannerLayout.addView(bannerView);
                    // 这里设置广告开关——开启

                } else if (value.equals("0")) {
                    // 这里设置广告开关——关闭


                }
            }else{

            }
        }

        @Override
        public void onGetOnlineConfigFailed(String key) {
            // TODO Auto-generated method stub

            // 获取在线参数失败，可能原因有：键值未设置或为空、网络异常、服务器异常
        }
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
             is = getActivity().getAssets().open("region.xml");
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
