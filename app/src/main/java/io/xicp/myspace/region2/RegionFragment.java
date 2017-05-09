package io.xicp.myspace.region2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.youmi.android.AdManager;
import net.youmi.android.normal.banner.BannerManager;
import net.youmi.android.normal.banner.BannerViewListener;

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

public class RegionFragment extends Fragment implements View.OnClickListener{
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
        System.out.println(regionEnName);
    System.out.println("##$$$");
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
        System.out.println("xcfdsdf$");
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
        landscapeRegionImageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), LandscapeListActivity.class);
                intent.putExtra(RegionFragment.EXTRA_REGION_ID, regionEnName);
                startActivity(intent);
            }
        });

// 获取要嵌入广告条的布局
         LinearLayout bannerLayout = (LinearLayout)v.findViewById(R.id.ll_banner);

// 将广告条加入到布局中
        ViewGroup p = (ViewGroup)bannerView.getParent();
        if(p != null){
            p.removeAllViewsInLayout();
        }
            bannerLayout.addView(bannerView);

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
            System.out.println(doc+"doc");
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
            System.out.println("------------第" + i + "个地区--------------");

            Node comBook = nodeList.item(i);
            System.out.println(comBook.getAttributes().getLength());
            //获取ISBN属性节点
            if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(regionEnName)){
                if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.BEIJING)){
                    picImageView.setImageResource(R.mipmap.beijing);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.TIANJIN)){
                    picImageView.setImageResource(R.mipmap.tianjin);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.SHANGHAI)){
                    picImageView.setImageResource(R.mipmap.shanghai);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.CHONGQING)){
                    picImageView.setImageResource(R.mipmap.chongqing);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.NEIMENGGU)){
                    picImageView.setImageResource(R.mipmap.neimenggu);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.XINJIANG)){
                    picImageView.setImageResource(R.mipmap.xinjiang);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.XIZANG)){
                    picImageView.setImageResource(R.mipmap.xizang);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.NINGXIA)){
                    picImageView.setImageResource(R.mipmap.ningxia);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.GUANGXI)){
                    picImageView.setImageResource(R.mipmap.guangxi);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.HONGKONG)){
                    picImageView.setImageResource(R.mipmap.hongkong);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.AOMEN)){
                    picImageView.setImageResource(R.mipmap.aomen);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.HEILONGJIANG)){
                    picImageView.setImageResource(R.mipmap.heilongjiang);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.JILIN)){
                    picImageView.setImageResource(R.mipmap.jilin);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.LIAONING)){
                    picImageView.setImageResource(R.mipmap.liaoning);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.HEBEI)){
                    picImageView.setImageResource(R.mipmap.hebei);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.SHANXIMOUNTAIN)){
                    picImageView.setImageResource(R.mipmap.shanximountain);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.QINGHAI)){
                    picImageView.setImageResource(R.mipmap.qinghai);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.SHANDONG)){
                    picImageView.setImageResource(R.mipmap.shandong);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.HENAN)){
                    picImageView.setImageResource(R.mipmap.henan);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.JIANGSU)){
                    picImageView.setImageResource(R.mipmap.jiangsu);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.ANHUI)){
                    picImageView.setImageResource(R.mipmap.anhui);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.ZHEJIANG)){
                    picImageView.setImageResource(R.mipmap.zhejiang);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.FUJIAN)){
                    picImageView.setImageResource(R.mipmap.fujian);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.JIANGXI)){
                    picImageView.setImageResource(R.mipmap.jiangxi);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.HUNAN)){
                    picImageView.setImageResource(R.mipmap.hunan);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.HUBEI)){
                    picImageView.setImageResource(R.mipmap.hubei);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.GUANGDONG)){
                    picImageView.setImageResource(R.mipmap.guangdong);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.TAIWAN)){
                    picImageView.setImageResource(R.mipmap.taiwan);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.HAINAN)){
                    picImageView.setImageResource(R.mipmap.hainan);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.GANSU)){
                    picImageView.setImageResource(R.mipmap.gansu);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.SHANXIEAR)){
                    picImageView.setImageResource(R.mipmap.shanxiear);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.SICHUAN)){
                    picImageView.setImageResource(R.mipmap.sichuan);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.GUIZHOU)){
                    picImageView.setImageResource(R.mipmap.guizhou);
                }else if(comBook.getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(RegionEnName.YUNNAN)){
                    picImageView.setImageResource(R.mipmap.yunnan);
                }

                //获取所有comBook下的所有子元素
//                String name ="beijing";
//                Region region = new Region();
//                region.setPic(R.mipmap.beijing);
//                picImageView.setImageResource(R.mipmap.name);
                NodeList attList = comBook.getChildNodes();
                //遍历每个子元素
               System.out.println(attList.getLength()+"length");
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 展示广告条窗口的 onDestroy() 回调方法中调用
        BannerManager.getInstance(getActivity()).onDestroy();
    }

    @Override
    public void onClick(View v) {

    }
}
