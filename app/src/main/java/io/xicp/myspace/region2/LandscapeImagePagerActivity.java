package io.xicp.myspace.region2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class LandscapeImagePagerActivity  extends FragmentActivity {
    private ViewPager viewPager;
    private String regionEnName = "";
    private List<LandscapeImage> landscapeImageList = new ArrayList<LandscapeImage>();
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        regionEnName = getIntent().getStringExtra(RegionFragment.EXTRA_REGION_ID);
        System.out.println(regionEnName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        Document doc = null;
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

                        continue ;
                    }else if(node.getNodeName().trim().equals("landscape")){
                        NodeList  landscapeChildNodeList = node.getChildNodes();
                        for(int k = 0; k< landscapeChildNodeList.getLength(); k++){
                            if(landscapeChildNodeList.item(k).getNodeName().trim().equals("landscapeitem")) {
                                Landscape landscape = new Landscape();
                                landscape.setRegion_name(attList.item(j).getTextContent().trim());
                                NodeList nodeList0 = landscapeChildNodeList.item(k).getChildNodes();
                                for(int r = 0; r < nodeList0.getLength(); r++){
                                    if (nodeList0.item(r).getNodeName().trim().equals("name")) {
                                        landscape.setName(nodeList0.item(r).getTextContent());
                                    } else if (nodeList0.item(r).getNodeName().trim().equals("describe")) {
                                        landscape.setDescribe(nodeList0.item(r).getTextContent());
                                    } else if (nodeList0.item(r).getNodeName().trim().equals("image")) {

                                        LandscapeImage landscapeImage = new LandscapeImage();
                                        landscapeImage.setImageEnName(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue());

                                        if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("gugongone")){
                                            landscapeImage.setPic(R.mipmap.gugongone);
                                        }
                                        if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("gugongtwo")){
                                            landscapeImage.setPic(R.mipmap.gugongtwo);
                                        }
                                        if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("gugongthree")){
                                            landscapeImage.setPic(R.mipmap.gugongthree);
                                        }

                                        NodeList nodeList1 = nodeList0.item(r).getChildNodes();
                                        for (int u = 0; u < nodeList1.getLength(); u++) {
                                            if (nodeList1.item(u).getNodeName().trim().equals("image_describe")) {

                                                landscapeImage.setImageDescribe(nodeList1.item(u).getTextContent().trim());
                                                    break ;
                                            }
                                        }
                                        landscapeImageList.add(landscapeImage);

                                    }
                                }

                            }

                        }
                        for(int p = 0; p < landscapeImageList.size(); p++){
                            System.out.println(landscapeImageList.get(p).getImageDescribe());
                        }
                        break label1 ;
                    }


                }

            }


        }


        viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);
        setContentView(viewPager);

        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                LandscapeImage landscapeImage = landscapeImageList.get(position);
                System.out.println(landscapeImage.getImageDescribe()+"123123");
                return LandscapeImageFragment.newInstance(landscapeImage.getImageEnName(),landscapeImage.getImageDescribe(),landscapeImage.getPic());
            }

            @Override
            public int getCount() {
                return landscapeImageList.size();
            }
        });
        String landscapeImageId = getIntent().getStringExtra(LandscapeImageFragment.EXTRA_LANDSCAPE_IMAGE_ID);
        for(int i = 0; i < landscapeImageList.size(); i++){
//            System.out.println(landscapeImageList.get(i));
            if(landscapeImageList.get(i).getImageEnName().equals(landscapeImageId)){
                viewPager.setCurrentItem(i);
                break ;
            }
        }
    }
}
