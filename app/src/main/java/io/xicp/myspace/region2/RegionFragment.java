package io.xicp.myspace.region2;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cse.der.qsw.AdManager;
import cse.der.qsw.nm.bn.BannerManager;
import cse.der.qsw.nm.bn.BannerViewListener;
import cse.der.qsw.onlineconfig.OnlineConfigCallBack;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Administrator on 2017/4/29 0029.
 */

public class RegionFragment extends Fragment{
    public static final String EXTRA_REGION_ID = "io.xicp.myspace.region2.region_id";
    private ImageView landscapeRegionImageView;
    private TextView regionNameTextView;
    private TextView belongtoTextView;
    private TextView provinceCapitalTextView;
    private TextView describeTextView;
    private TextView abbreviationTextView;
    private ImageView picImageView;
    private String regionEnName = "";
    private ImageView leftRightTipsImageView;
    private ImageView   backImageView;
    private  LinearLayout bannerLayout;
    private View bannerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

//        regionEnName = getActivity().getIntent().getStringExtra(EXTRA_REGION_ID);
        regionEnName = getArguments().getString(EXTRA_REGION_ID);

    }
    public static RegionFragment newInstance(String regionEnName){
        Bundle args = new Bundle();
        args.putString(EXTRA_REGION_ID, regionEnName);
        RegionFragment fragment = new RegionFragment();
        fragment.setArguments(args);
        return fragment;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_region_detail, container, false);
        landscapeRegionImageView = (ImageView) v.findViewById(R.id.landscaperegion);
        landscapeRegionImageView.setClickable(true);
//        landscapeRegionImageView.setFocusableInTouchMode(true);
//        landscapeRegionImageView.setFocusable(true);

        regionNameTextView = (TextView)v.findViewById(R.id.region_name);
        belongtoTextView = (TextView)v.findViewById(R.id.belongto);
        provinceCapitalTextView = (TextView)v.findViewById(R.id.provincialCapital);
        describeTextView = (TextView)v.findViewById(R.id.describe);
        abbreviationTextView = (TextView)v.findViewById(R.id.abbreviation);
        picImageView = (ImageView)v.findViewById(R.id.pic);
        leftRightTipsImageView = (ImageView)v.findViewById(R.id.left_right_tips);
        backImageView = (ImageView)v.findViewById(R.id.back);
        landscapeRegionImageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), LandscapeListActivity.class);
                intent.putExtra(RegionFragment.EXTRA_REGION_ID, regionEnName);
                startActivity(intent);
            }

        });
        backImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               getActivity().finish();
            }
        });
// 获取要嵌入广告条的布局
          bannerLayout = (LinearLayout)v.findViewById(R.id.ll_banner);
        AdManager.getInstance(getActivity()).asyncGetOnlineConfig("isOpen",new MyOnlineConfigCallBack());
// 将广告条加入到布局中
        ViewGroup p = (ViewGroup)bannerView.getParent();
        if(p != null){
            p.removeAllViewsInLayout();
        }


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        Document doc = null;

        SharedPreferences sharedata = getActivity().getSharedPreferences("fristrun", 0);
        Boolean isLeftRight = sharedata.getBoolean("isLeftRight", true);
        if (isLeftRight) {
            if(leftRightTipsImageView != null){
                leftRightTipsImageView.setVisibility(View.VISIBLE);
            }

        } else {
            if(leftRightTipsImageView != null) {
                leftRightTipsImageView.setVisibility(View.INVISIBLE);
            }
        }
        if(leftRightTipsImageView != null){
            leftRightTipsImageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    leftRightTipsImageView.setVisibility(View.INVISIBLE);
                    SharedPreferences.Editor sharedata = getActivity().getSharedPreferences("fristrun", 0).edit();
                    sharedata.putBoolean("isLeftRight", false);
                    sharedata.commit();
                }
            });
        }

        //获取DOM解析器
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            //解析XML文档，并获取该XML文档对应的Document
             doc = builder.parse(getResources().getAssets().open("region.xml"));

        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(SAXException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

		/*
		我们在JavaScript中编程时的document也是Document类的实例，
		因为XML文档的元素通常没有ID属性，
		否则也可通过getElementById()方法来访问各元素。
		*/
        //获得根节点的方法，getDocumentElement
        Element bookList = doc.getDocumentElement();
        //获取根元素所包含的所有“计算机书籍”子元素，
        //如果传入*作为参数，可获取所有子元素
        NodeList nodeList = bookList.getElementsByTagName("region");
        //遍历每个子元素
        label1:
        for (int i = 0; i < nodeList.getLength() ; i++ )
        {


            Node comBook = nodeList.item(i);

            //获取ISBN属性节点
            if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(regionEnName)){
                if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.BEIJING.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.beijing);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.TIANJIN.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.tianjin);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.SHANGHAI.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.shanghai);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.CHONGQING.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.chongqing);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.NEIMENGGU.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.neimenggu);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.XINJIANG.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.xinjiang);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.XIZANG.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.xizang);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.NINGXIA.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.ningxia);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.GUANGXI.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.guangxi);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.HONGKONG.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.hongkong);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.AOMEN.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.aomen);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.HEILONGJIANG.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.heilongjiang);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.JILIN.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.jilin);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.LIAONING.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.liaoning);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.HEBEI.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.hebei);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.SHANXIMOUNTAIN.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.shanximountain);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.QINGHAI.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.qinghai);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.SHANDONG.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.shandong);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.HENAN.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.henan);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.JIANGSU.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.jiangsu);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.ANHUI.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.anhui);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.ZHEJIANG.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.zhejiang);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.FUJIAN.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.fujian);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.JIANGXI.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.jiangxi);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.HUNAN.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.hunan);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.HUBEI.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.hubei);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.GUANGDONG.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.guangdong);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.TAIWAN.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.taiwan);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.HAINAN.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.hainan);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.GANSU.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.gansu);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.SHANXIEAR.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.shanxiear);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.SICHUAN.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.sichuan);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.GUIZHOU.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.guizhou);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.YUNNAN.toString().toLowerCase())){
                    picImageView.setImageResource(R.mipmap.yunnan);
                }

                //获取所有comBook下的所有子元素

                NodeList attList = comBook.getChildNodes();
                //遍历每个子元素

                for (int j = 0; j < attList.getLength() ; j++ )
                {
                        Node node = attList.item(j);
                    if(attList.item(j).getNodeName().trim().equals("region_name")){
                        regionNameTextView.setText(attList.item(j).getTextContent().trim());
                        continue ;
                    }else if(node.getNodeName().trim().equals("region_abbreviation")){
                        abbreviationTextView.setText(attList.item(j).getTextContent().trim());
                        continue ;
                }else if(node.getNodeName().trim().equals("region_describe")){
                        describeTextView.setText(attList.item(j).getTextContent().trim());
                        continue ;
                }else if(node.getNodeName().trim().equals("provicial_capital")){
                        provinceCapitalTextView.setText(attList.item(j).getTextContent().trim());
                            continue ;
                }else if(node.getNodeName().trim().equals("belong_to")){
                        belongtoTextView.setText(attList.item(j).getTextContent().trim());

                            continue ;
                    }


                }
                break label1;
            }


        }




        return v;

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
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 展示广告条窗口的 onDestroy() 回调方法中调用
        BannerManager.getInstance(getActivity()).onDestroy();
    }


}
