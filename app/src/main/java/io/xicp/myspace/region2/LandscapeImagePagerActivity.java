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
    private String landscapeItemEnName = "";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        regionEnName = getIntent().getStringExtra(RegionFragment.EXTRA_REGION_ID);
        landscapeItemEnName = getIntent().getStringExtra("landscape_item_en_name");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        Document doc = null;
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


                //获取所有comBook下的所有子元素
//                String name ="beijing";
//                Region region = new Region();
//                region.setPic(R.mipmap.beijing);
//                picImageView.setImageResource(R.mipmap.name);
                NodeList attList = comBook.getChildNodes();
                //遍历每个子元素

                for (int j = 0; j < attList.getLength() ; j++ )
                {
                    Node node = attList.item(j);
                    if(attList.item(j).getNodeName().trim().equals("region_name")){

                        continue ;
                    }else if(node.getNodeName().trim().equals("landscape")){
                        NodeList  landscapeChildNodeList = node.getChildNodes();
                        for(int k = 0; k< landscapeChildNodeList.getLength(); k++){
                            if(landscapeChildNodeList.item(k).getNodeName().trim().equals("landscapeitem")) {
                                if(landscapeChildNodeList.item(k).getAttributes().getNamedItem("en_name").getNodeValue().trim().equals(landscapeItemEnName)){
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
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yiheyuanone")){
                                                landscapeImage.setPic(R.mipmap.yiheyuanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yiheyuantwo")){
                                                landscapeImage.setPic(R.mipmap.yiheyuantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yiheyuanthree")){
                                                landscapeImage.setPic(R.mipmap.yiheyuanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yiheyuantwo")){
                                                landscapeImage.setPic(R.mipmap.yiheyuantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tiantanone")){
                                                landscapeImage.setPic(R.mipmap.tiantanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tiantantwo")){
                                                landscapeImage.setPic(R.mipmap.tiantantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tiantanthree")){
                                                landscapeImage.setPic(R.mipmap.tiantanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tiantanfour")){
                                                landscapeImage.setPic(R.mipmap.tiantanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("beihaigongyuanone")){
                                                landscapeImage.setPic(R.mipmap.beihaigongyuanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("beihaigongyuantwo")){
                                                landscapeImage.setPic(R.mipmap.beihaigongyuantwo);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("changchengone")){
                                                landscapeImage.setPic(R.mipmap.changchengone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("changchengtwo")){
                                                landscapeImage.setPic(R.mipmap.changchengtwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("changchengthree")){
                                                landscapeImage.setPic(R.mipmap.changchengthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudadaoone")){
                                                landscapeImage.setPic(R.mipmap.wudadaoone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudadaotwo")){
                                                landscapeImage.setPic(R.mipmap.wudadaotwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudadaothree")){
                                                landscapeImage.setPic(R.mipmap.wudadaothree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("panshanone")){
                                                landscapeImage.setPic(R.mipmap.panshanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("panshantwo")){
                                                landscapeImage.setPic(R.mipmap.panshantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("panshanthree")){
                                                landscapeImage.setPic(R.mipmap.panshanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("panshanfour")){
                                                landscapeImage.setPic(R.mipmap.panshanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("panshanfive")){
                                                landscapeImage.setPic(R.mipmap.panshanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("panshansix")){
                                                landscapeImage.setPic(R.mipmap.panshansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("panshanseven")){
                                                landscapeImage.setPic(R.mipmap.panshanseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("panshaneight")){
                                                landscapeImage.setPic(R.mipmap.panshaneight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("panshannine")){
                                                landscapeImage.setPic(R.mipmap.panshannine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dulesione")){
                                                landscapeImage.setPic(R.mipmap.dulesione);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dulesitwo")){
                                                landscapeImage.setPic(R.mipmap.dulesitwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dulesithree")){
                                                landscapeImage.setPic(R.mipmap.dulesithree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dulesifour")){
                                                landscapeImage.setPic(R.mipmap.dulesifour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dulesifive")){
                                                landscapeImage.setPic(R.mipmap.dulesifive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dulesisix")){
                                                landscapeImage.setPic(R.mipmap.dulesisix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangyangguanchangchengone")){
                                                landscapeImage.setPic(R.mipmap.huangyangguanchangchengone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangyangguanchangchengtwo")){
                                                landscapeImage.setPic(R.mipmap.huangyangguanchangchengtwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangyangguanchangchengthree")){
                                                landscapeImage.setPic(R.mipmap.huangyangguanchangchengthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangyangguanchangchengfour")){
                                                landscapeImage.setPic(R.mipmap.huangyangguanchangchengfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangyangguanchangchengfive")){
                                                landscapeImage.setPic(R.mipmap.huangyangguanchangchengfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yangliuqingguzhenone")){
                                                landscapeImage.setPic(R.mipmap.yangliuqingguzhenone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yangliuqingguzhentwo")){
                                                landscapeImage.setPic(R.mipmap.yangliuqingguzhentwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yangliuqingguzhenthree")){
                                                landscapeImage.setPic(R.mipmap.yangliuqingguzhenthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yangliuqingguzhenfour")){
                                                landscapeImage.setPic(R.mipmap.yangliuqingguzhenfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yangliuqingguzhenfive")){
                                                landscapeImage.setPic(R.mipmap.yangliuqingguzhenfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dongfangmingzhutaone")){
                                                landscapeImage.setPic(R.mipmap.dongfangmingzhutaone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dongfangmingzhutatwo")){
                                                landscapeImage.setPic(R.mipmap.dongfangmingzhutatwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dongfangmingzhutathree")){
                                                landscapeImage.setPic(R.mipmap.dongfangmingzhutathree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dongfangmingzhutafour")){
                                                landscapeImage.setPic(R.mipmap.dongfangmingzhutafour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dongfangmingzhutafive")){
                                                landscapeImage.setPic(R.mipmap.dongfangmingzhutafive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("shanghaiwaitanone")){
                                                landscapeImage.setPic(R.mipmap.shanghaiwaitanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("shanghaiwaitantwo")){
                                                landscapeImage.setPic(R.mipmap.shanghaiwaitantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("shanghaiwaitanthree")){
                                                landscapeImage.setPic(R.mipmap.shanghaiwaitanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("shanghaiwaitanfour")){
                                                landscapeImage.setPic(R.mipmap.shanghaiwaitanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("shanghaiwaitanfive")){
                                                landscapeImage.setPic(R.mipmap.shanghaiwaitanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yuyuanone")){
                                                landscapeImage.setPic(R.mipmap.yuyuanone);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yuyuantwo")){
                                                landscapeImage.setPic(R.mipmap.yuyuantwo);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yuyuanthree")){
                                                landscapeImage.setPic(R.mipmap.yuyuanthree);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yuyuanfour")){
                                                landscapeImage.setPic(R.mipmap.yuyuanfour);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yuyuanfive")){
                                                landscapeImage.setPic(R.mipmap.yuyuanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dianshanhuone")){
                                                landscapeImage.setPic(R.mipmap.dianshanhuone);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dianshanhutwo")){
                                                landscapeImage.setPic(R.mipmap.dianshanhutwo);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dianshanhuthree")){
                                                landscapeImage.setPic(R.mipmap.dianshanhuthree);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dianshanhufour")){
                                                landscapeImage.setPic(R.mipmap.dianshanhufour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhujiajiaoguzhenone")){
                                                landscapeImage.setPic(R.mipmap.zhujiajiaoguzhenone);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhujiajiaoguzhentwo")){
                                                landscapeImage.setPic(R.mipmap.zhujiajiaoguzhentwo);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhujiajiaoguzhenthree")){
                                                landscapeImage.setPic(R.mipmap.zhujiajiaoguzhenthree);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhujiajiaoguzhenfour")){
                                                landscapeImage.setPic(R.mipmap.zhujiajiaoguzhenfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dazushikeone")){
                                                landscapeImage.setPic(R.mipmap.dazushikeone);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dazushiketwo")){
                                                landscapeImage.setPic(R.mipmap.dazushiketwo);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dazushikethree")){
                                                landscapeImage.setPic(R.mipmap.dazushikethree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dazushikefour")){
                                                landscapeImage.setPic(R.mipmap.dazushikefour);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dazushikefive")){
                                                landscapeImage.setPic(R.mipmap.dazushikefive);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dazushikesix")){
                                                landscapeImage.setPic(R.mipmap.dazushikesix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dazushikeseven")){
                                                landscapeImage.setPic(R.mipmap.dazushikeseven);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dazushikeeight")){
                                                landscapeImage.setPic(R.mipmap.dazushikeeight);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dazushikenine")){
                                                landscapeImage.setPic(R.mipmap.dazushikenine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("fengduguichengone")){
                                                landscapeImage.setPic(R.mipmap.fengduguichengone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("fengduguichengtwo")){
                                                landscapeImage.setPic(R.mipmap.fengduguichengtwo);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("fengduguichengthree")){
                                                landscapeImage.setPic(R.mipmap.fengduguichengthree);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("fengduguichengfour")){
                                                landscapeImage.setPic(R.mipmap.fengduguichengfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("baidichengone")){
                                                landscapeImage.setPic(R.mipmap.baidichengone);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("baidichengtwo")){
                                                landscapeImage.setPic(R.mipmap.baidichengtwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("baidichengthree")){
                                                landscapeImage.setPic(R.mipmap.baidichengthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("baidichengfour")){
                                                landscapeImage.setPic(R.mipmap.baidichengfour);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("baidichengfive")){
                                                landscapeImage.setPic(R.mipmap.baidichengfive);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("baidichengsix")){
                                                landscapeImage.setPic(R.mipmap.baidichengsix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("baidichengseven")){
                                                landscapeImage.setPic(R.mipmap.baidichengseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("daningheone")){
                                                landscapeImage.setPic(R.mipmap.daningheone);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("daninghetwo")){
                                                landscapeImage.setPic(R.mipmap.daninghetwo);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("daninghethree")){
                                                landscapeImage.setPic(R.mipmap.daninghethree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("daninghefour")){
                                                landscapeImage.setPic(R.mipmap.daninghefour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinyunshanone")){
                                                landscapeImage.setPic(R.mipmap.jinyunshanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinyunshantwo")){
                                                landscapeImage.setPic(R.mipmap.jinyunshantwo);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinyunshanthree")){
                                                landscapeImage.setPic(R.mipmap.jinyunshanthree);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinyunshanfour")){
                                                landscapeImage.setPic(R.mipmap.jinyunshanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinyunshanfive")){
                                                landscapeImage.setPic(R.mipmap.jinyunshanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhaojunmuone")){
                                                landscapeImage.setPic(R.mipmap.zhaojunmuone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhaojunmutwo")){
                                                landscapeImage.setPic(R.mipmap.zhaojunmutwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhaojunmuthree")){
                                                landscapeImage.setPic(R.mipmap.zhaojunmuthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhaojunmufour")){
                                                landscapeImage.setPic(R.mipmap.zhaojunmufour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhaojunmufive")){
                                                landscapeImage.setPic(R.mipmap.zhaojunmufive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhaojunmusix")){
                                                landscapeImage.setPic(R.mipmap.zhaojunmusix);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangzhaoone")){
                                                landscapeImage.setPic(R.mipmap.wudangzhaoone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangzhaotwo")){
                                                landscapeImage.setPic(R.mipmap.wudangzhaotwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangzhaothree")){
                                                landscapeImage.setPic(R.mipmap.wudangzhaothree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangzhaofour")){
                                                landscapeImage.setPic(R.mipmap.wudangzhaofour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangzhaofive")){
                                                landscapeImage.setPic(R.mipmap.wudangzhaofive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangzhaosix")){
                                                landscapeImage.setPic(R.mipmap.wudangzhaosix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("chengjisihanlingone")){
                                                landscapeImage.setPic(R.mipmap.chengjisihanlingone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("chengjisihanlingtwo")){
                                                landscapeImage.setPic(R.mipmap.chengjisihanlingtwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("chengjisihanlingthree")){
                                                landscapeImage.setPic(R.mipmap.chengjisihanlingthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("chengjisihanlingfour")){
                                                landscapeImage.setPic(R.mipmap.chengjisihanlingfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("chengjisihanlingfive")){
                                                landscapeImage.setPic(R.mipmap.chengjisihanlingfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("chengjisihanlingsix")){
                                                landscapeImage.setPic(R.mipmap.chengjisihanlingsix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("chengjisihanlingseven")){
                                                landscapeImage.setPic(R.mipmap.chengjisihanlingseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("budalagongone")){
                                                landscapeImage.setPic(R.mipmap.budalagongone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("budalagongtwo")){
                                                landscapeImage.setPic(R.mipmap.budalagongtwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("budalagongthree")){
                                                landscapeImage.setPic(R.mipmap.budalagongthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("budalagongfour")){
                                                landscapeImage.setPic(R.mipmap.budalagongfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("budalagongfive")){
                                                landscapeImage.setPic(R.mipmap.budalagongfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("budalagongsix")){
                                                landscapeImage.setPic(R.mipmap.budalagongsix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("budalagongseven")){
                                                landscapeImage.setPic(R.mipmap.budalagongseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhashilunbusione")){
                                                landscapeImage.setPic(R.mipmap.zhashilunbusione);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhashilunbusitwo")){
                                                landscapeImage.setPic(R.mipmap.zhashilunbusitwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhashilunbusithree")){
                                                landscapeImage.setPic(R.mipmap.zhashilunbusithree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhashilunbusifour")){
                                                landscapeImage.setPic(R.mipmap.zhashilunbusifour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhashilunbusifive")){
                                                landscapeImage.setPic(R.mipmap.zhashilunbusifive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhashilunbusisix")){
                                                landscapeImage.setPic(R.mipmap.zhashilunbusisix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhashilunbusiseven")){
                                                landscapeImage.setPic(R.mipmap.zhashilunbusiseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhashilunbusieight")){
                                                landscapeImage.setPic(R.mipmap.zhashilunbusieight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhumulangmafengone")){
                                                landscapeImage.setPic(R.mipmap.zhumulangmafengone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhumulangmafengtwo")){
                                                landscapeImage.setPic(R.mipmap.zhumulangmafengtwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhumulangmafengthree")){
                                                landscapeImage.setPic(R.mipmap.zhumulangmafengthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhumulangmafengfour")){
                                                landscapeImage.setPic(R.mipmap.zhumulangmafengfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhumulangmafengfive")){
                                                landscapeImage.setPic(R.mipmap.zhumulangmafengfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhumulangmafengsix")){
                                                landscapeImage.setPic(R.mipmap.zhumulangmafengsix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhumulangmafengseven")){
                                                landscapeImage.setPic(R.mipmap.zhumulangmafengseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yaluzangbudaxiaguone")){
                                                landscapeImage.setPic(R.mipmap.yaluzangbudaxiaguone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yaluzangbudaxiagutwo")){
                                                landscapeImage.setPic(R.mipmap.yaluzangbudaxiagutwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yaluzangbudaxiaguthree")){
                                                landscapeImage.setPic(R.mipmap.yaluzangbudaxiaguthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yaluzangbudaxiagufour")){
                                                landscapeImage.setPic(R.mipmap.yaluzangbudaxiagufour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yaluzangbudaxiagufive")){
                                                landscapeImage.setPic(R.mipmap.yaluzangbudaxiagufive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("guilinone")){
                                                landscapeImage.setPic(R.mipmap.guilinone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("guilintwo")){
                                                landscapeImage.setPic(R.mipmap.guilintwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("guilinthree")){
                                                landscapeImage.setPic(R.mipmap.guilinthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("guilinfour")){
                                                landscapeImage.setPic(R.mipmap.guilinfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("guilinfive")){
                                                landscapeImage.setPic(R.mipmap.guilinfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("guilinsix")){
                                                landscapeImage.setPic(R.mipmap.guilinsix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yangsuoone")){
                                                landscapeImage.setPic(R.mipmap.yangsuoone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yangsuotwo")){
                                                landscapeImage.setPic(R.mipmap.yangsuotwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yangsuothree")){
                                                landscapeImage.setPic(R.mipmap.yangsuothree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("xianggangone")){
                                                landscapeImage.setPic(R.mipmap.xianggangone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("xianggangtwo")){
                                                landscapeImage.setPic(R.mipmap.xianggangtwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qingmadaqiaoone")){
                                                landscapeImage.setPic(R.mipmap.qingmadaqiaoone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("aomenone")){
                                                landscapeImage.setPic(R.mipmap.aomenone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("xinpujingone")){
                                                landscapeImage.setPic(R.mipmap.xinpujingone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jingbohuone")){
                                                landscapeImage.setPic(R.mipmap.jingbohuone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jingbohutwo")){
                                                landscapeImage.setPic(R.mipmap.jingbohutwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jingbohuthree")){
                                                landscapeImage.setPic(R.mipmap.jingbohuthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jingbohufour")){
                                                landscapeImage.setPic(R.mipmap.jingbohufour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taiyangdaoone")){
                                                landscapeImage.setPic(R.mipmap.taiyangdaoone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taiyangdaotwo")){
                                                landscapeImage.setPic(R.mipmap.taiyangdaotwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taiyangdaothree")){
                                                landscapeImage.setPic(R.mipmap.taiyangdaothree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taiyangdaofour")){
                                                landscapeImage.setPic(R.mipmap.taiyangdaofour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taiyangdaofive")){
                                                landscapeImage.setPic(R.mipmap.taiyangdaofive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taiyangdaosix")){
                                                landscapeImage.setPic(R.mipmap.taiyangdaosix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taiyangdaoseven")){
                                                landscapeImage.setPic(R.mipmap.taiyangdaoseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudalianchione")){
                                                landscapeImage.setPic(R.mipmap.wudalianchione);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudalianchitwo")){
                                                landscapeImage.setPic(R.mipmap.wudalianchitwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudalianchithree")){
                                                landscapeImage.setPic(R.mipmap.wudalianchithree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudalianchifour")){
                                                landscapeImage.setPic(R.mipmap.wudalianchifour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudalianchifive")){
                                                landscapeImage.setPic(R.mipmap.wudalianchifive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudalianchisix")){
                                                landscapeImage.setPic(R.mipmap.wudalianchisix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudalianchiseven")){
                                                landscapeImage.setPic(R.mipmap.wudalianchiseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudalianchieight")){
                                                landscapeImage.setPic(R.mipmap.wudalianchieight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudalianchinine")){
                                                landscapeImage.setPic(R.mipmap.wudalianchinine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudalianchiten")){
                                                landscapeImage.setPic(R.mipmap.wudalianchiten);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("changbaishantianchione")){
                                                landscapeImage.setPic(R.mipmap.changbaishantianchione);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("changbaishantianchitwo")){
                                                landscapeImage.setPic(R.mipmap.changbaishantianchitwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("changbaishantianchithree")){
                                                landscapeImage.setPic(R.mipmap.changbaishantianchithree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("shenyanggugongone")){
                                                landscapeImage.setPic(R.mipmap.shenyanggugongone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("shenyanggugongtwo")){
                                                landscapeImage.setPic(R.mipmap.shenyanggugongtwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("shenyanggugongthree")){
                                                landscapeImage.setPic(R.mipmap.shenyanggugongthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("shenyanggugongfour")){
                                                landscapeImage.setPic(R.mipmap.shenyanggugongfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("shenyanggugongfive")){
                                                landscapeImage.setPic(R.mipmap.shenyanggugongfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinshitanone")){
                                                landscapeImage.setPic(R.mipmap.jinshitanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinshitantwo")){
                                                landscapeImage.setPic(R.mipmap.jinshitantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinshitanthree")){
                                                landscapeImage.setPic(R.mipmap.jinshitanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinshitanfour")){
                                                landscapeImage.setPic(R.mipmap.jinshitanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinshitanfive")){
                                                landscapeImage.setPic(R.mipmap.jinshitanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinshitansix")){
                                                landscapeImage.setPic(R.mipmap.jinshitansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinshitanseven")){
                                                landscapeImage.setPic(R.mipmap.jinshitanseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinshitaneight")){
                                                landscapeImage.setPic(R.mipmap.jinshitaneight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinshitannine")){
                                                landscapeImage.setPic(R.mipmap.jinshitannine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jinshitanten")){
                                                landscapeImage.setPic(R.mipmap.jinshitanten);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("bishushanzhuangone")){
                                                landscapeImage.setPic(R.mipmap.bishushanzhuangone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("bishushanzhuangtwo")){
                                                landscapeImage.setPic(R.mipmap.bishushanzhuangtwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("bishushanzhuangthree")){
                                                landscapeImage.setPic(R.mipmap.bishushanzhuangthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("bishushanzhuangfour")){
                                                landscapeImage.setPic(R.mipmap.bishushanzhuangfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("bishushanzhuangfive")){
                                                landscapeImage.setPic(R.mipmap.bishushanzhuangfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("bishushanzhuangsix")){
                                                landscapeImage.setPic(R.mipmap.bishushanzhuangsix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("bishushanzhuangseven")){
                                                landscapeImage.setPic(R.mipmap.bishushanzhuangseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("bishushanzhuangeight")){
                                                landscapeImage.setPic(R.mipmap.bishushanzhuangeight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("bishushanzhuangnine")){
                                                landscapeImage.setPic(R.mipmap.bishushanzhuangnine);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("beidaiheone")){
                                                landscapeImage.setPic(R.mipmap.beidaiheone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("beidaihetwo")){
                                                landscapeImage.setPic(R.mipmap.beidaihetwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("beidaihethree")){
                                                landscapeImage.setPic(R.mipmap.beidaihethree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("beidaihefour")){
                                                landscapeImage.setPic(R.mipmap.beidaihefour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("pingyaoguchengone")){
                                                landscapeImage.setPic(R.mipmap.pingyaoguchengone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("pingyaoguchengtwo")){
                                                landscapeImage.setPic(R.mipmap.pingyaoguchengtwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("pingyaoguchengthree")){
                                                landscapeImage.setPic(R.mipmap.pingyaoguchengthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("pingyaoguchengfour")){
                                                landscapeImage.setPic(R.mipmap.pingyaoguchengfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("pingyaoguchengfive")){
                                                landscapeImage.setPic(R.mipmap.pingyaoguchengfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("pingyaoguchengsix")){
                                                landscapeImage.setPic(R.mipmap.pingyaoguchengsix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("pingyaoguchengseven")){
                                                landscapeImage.setPic(R.mipmap.pingyaoguchengseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("pingyaoguchengeight")){
                                                landscapeImage.setPic(R.mipmap.pingyaoguchengeight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yungangshikuone")){
                                                landscapeImage.setPic(R.mipmap.yungangshikuone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yungangshikutwo")){
                                                landscapeImage.setPic(R.mipmap.yungangshikutwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yungangshikuthree")){
                                                landscapeImage.setPic(R.mipmap.yungangshikuthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yungangshikufour")){
                                                landscapeImage.setPic(R.mipmap.yungangshikufour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yungangshikufive")){
                                                landscapeImage.setPic(R.mipmap.yungangshikufive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yungangshikusix")){
                                                landscapeImage.setPic(R.mipmap.yungangshikusix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yungangshikuseven")){
                                                landscapeImage.setPic(R.mipmap.yungangshikuseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yungangshikueight")){
                                                landscapeImage.setPic(R.mipmap.yungangshikueight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yungangshikunine")){
                                                landscapeImage.setPic(R.mipmap.yungangshikunine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yungangshikuten")){
                                                landscapeImage.setPic(R.mipmap.yungangshikuten);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wutaishanone")){
                                                landscapeImage.setPic(R.mipmap.wutaishanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wutaishantwo")){
                                                landscapeImage.setPic(R.mipmap.wutaishantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wutaishanthree")){
                                                landscapeImage.setPic(R.mipmap.wutaishanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wutaishanfour")){
                                                landscapeImage.setPic(R.mipmap.wutaishanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wutaishanfive")){
                                                landscapeImage.setPic(R.mipmap.wutaishanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wutaishansix")){
                                                landscapeImage.setPic(R.mipmap.wutaishansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wutaishanseven")){
                                                landscapeImage.setPic(R.mipmap.wutaishanseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wutaishaneight")){
                                                landscapeImage.setPic(R.mipmap.wutaishaneight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wutaishannine")){
                                                landscapeImage.setPic(R.mipmap.wutaishannine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taishanone")){
                                                landscapeImage.setPic(R.mipmap.taishanone);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taishantwo")){
                                                landscapeImage.setPic(R.mipmap.taishantwo);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taishanthree")){
                                                landscapeImage.setPic(R.mipmap.taishanthree);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taishanfour")){
                                                landscapeImage.setPic(R.mipmap.taishanfour);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taishanfive")){
                                                landscapeImage.setPic(R.mipmap.taishanfive);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taishansix")){
                                                landscapeImage.setPic(R.mipmap.taishansix);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taishanseven")){
                                                landscapeImage.setPic(R.mipmap.taishanseven);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taishaneight")){
                                                landscapeImage.setPic(R.mipmap.taishaneight);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taishannine")){
                                                landscapeImage.setPic(R.mipmap.taishannine);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("taishanten")){
                                                landscapeImage.setPic(R.mipmap.taishanten);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("penglaigeone")){
                                                landscapeImage.setPic(R.mipmap.penglaigeone);
                                            }



                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("penglaigetwo")){
                                                landscapeImage.setPic(R.mipmap.penglaigetwo);
                                            }


                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("penglaigethree")){
                                                landscapeImage.setPic(R.mipmap.penglaigethree);
                                            }


                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("penglaigefour")){
                                                landscapeImage.setPic(R.mipmap.penglaigefour);
                                            }


                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("penglaigefive")){
                                                landscapeImage.setPic(R.mipmap.penglaigefive);
                                            }


                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("penglaigesix")){
                                                landscapeImage.setPic(R.mipmap.penglaigesix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("songshanshaolinone")){
                                                landscapeImage.setPic(R.mipmap.songshanshaolinone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("songshanshaolintwo")){
                                                landscapeImage.setPic(R.mipmap.songshanshaolintwo);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("longmenshikuone")){
                                                landscapeImage.setPic(R.mipmap.longmenshikuone);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("longmenshikutwo")){
                                                landscapeImage.setPic(R.mipmap.longmenshikutwo);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("longmenshikuthree")){
                                                landscapeImage.setPic(R.mipmap.longmenshikuthree);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("longmenshikufour")){
                                                landscapeImage.setPic(R.mipmap.longmenshikufour);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("longmenshikufive")){
                                                landscapeImage.setPic(R.mipmap.longmenshikufive);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("longmenshikusix")){
                                                landscapeImage.setPic(R.mipmap.longmenshikusix);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("longmenshikuseven")){
                                                landscapeImage.setPic(R.mipmap.longmenshikuseven);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("longmenshikueight")){
                                                landscapeImage.setPic(R.mipmap.longmenshikueight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("baimasione")){
                                                landscapeImage.setPic(R.mipmap.baimasione);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("baimasitwo")){
                                                landscapeImage.setPic(R.mipmap.baimasitwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("baimasithree")){
                                                landscapeImage.setPic(R.mipmap.baimasithree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("baimasifour")){
                                                landscapeImage.setPic(R.mipmap.baimasifour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("baimasifive")){
                                                landscapeImage.setPic(R.mipmap.baimasifive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("baimasisix")){
                                                landscapeImage.setPic(R.mipmap.baimasisix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("baimasiseven")){
                                                landscapeImage.setPic(R.mipmap.baimasiseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huqiuone")){
                                                landscapeImage.setPic(R.mipmap.huqiuone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huqiutwo")){
                                                landscapeImage.setPic(R.mipmap.huqiutwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huqiuthree")){
                                                landscapeImage.setPic(R.mipmap.huqiuthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huqiufour")){
                                                landscapeImage.setPic(R.mipmap.huqiufour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huqiufive")){
                                                landscapeImage.setPic(R.mipmap.huqiufive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huqiusix")){
                                                landscapeImage.setPic(R.mipmap.huqiusix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huqiuseven")){
                                                landscapeImage.setPic(R.mipmap.huqiuseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huqiueight")){
                                                landscapeImage.setPic(R.mipmap.huqiueight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huqiunine")){
                                                landscapeImage.setPic(R.mipmap.huqiunine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huqiuten")){
                                                landscapeImage.setPic(R.mipmap.huqiuten);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhuozhengyuanone")){
                                                landscapeImage.setPic(R.mipmap.zhuozhengyuanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhuozhengyuantwo")){
                                                landscapeImage.setPic(R.mipmap.zhuozhengyuantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhuozhengyuanthree")){
                                                landscapeImage.setPic(R.mipmap.zhuozhengyuanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhuozhengyuanfour")){
                                                landscapeImage.setPic(R.mipmap.zhuozhengyuanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhuozhengyuanfive")){
                                                landscapeImage.setPic(R.mipmap.zhuozhengyuanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhuozhengyuansix")){
                                                landscapeImage.setPic(R.mipmap.zhuozhengyuansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("zhuozhengyuanseven")){
                                                landscapeImage.setPic(R.mipmap.zhuozhengyuanseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangshanone")){
                                                landscapeImage.setPic(R.mipmap.huangshanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangshantwo")){
                                                landscapeImage.setPic(R.mipmap.huangshantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangshanthree")){
                                                landscapeImage.setPic(R.mipmap.huangshanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangshanfour")){
                                                landscapeImage.setPic(R.mipmap.huangshanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangshanfive")){
                                                landscapeImage.setPic(R.mipmap.huangshanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangshansix")){
                                                landscapeImage.setPic(R.mipmap.huangshansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangshanseven")){
                                                landscapeImage.setPic(R.mipmap.huangshanseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangshaneight")){
                                                landscapeImage.setPic(R.mipmap.huangshaneight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangshannine")){
                                                landscapeImage.setPic(R.mipmap.huangshannine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangshanten")){
                                                landscapeImage.setPic(R.mipmap.huangshanten);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuhuashanone")){
                                                landscapeImage.setPic(R.mipmap.jiuhuashanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuhuashantwo")){
                                                landscapeImage.setPic(R.mipmap.jiuhuashantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuhuashanthree")){
                                                landscapeImage.setPic(R.mipmap.jiuhuashanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuhuashanfour")){
                                                landscapeImage.setPic(R.mipmap.jiuhuashanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuhuashanfive")){
                                                landscapeImage.setPic(R.mipmap.jiuhuashanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuhuashansix")){
                                                landscapeImage.setPic(R.mipmap.jiuhuashansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuhuashanseven")){
                                                landscapeImage.setPic(R.mipmap.jiuhuashanseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuhuashaneight")){
                                                landscapeImage.setPic(R.mipmap.jiuhuashaneight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuhuashannine")){
                                                landscapeImage.setPic(R.mipmap.jiuhuashannine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuhuashanten")){
                                                landscapeImage.setPic(R.mipmap.jiuhuashanten);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("hangzhouxihuone")){
                                                landscapeImage.setPic(R.mipmap.hangzhouxihuone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("hangzhouxihutwo")){
                                                landscapeImage.setPic(R.mipmap.hangzhouxihutwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("hangzhouxihuthree")){
                                                landscapeImage.setPic(R.mipmap.hangzhouxihuthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("hangzhouxihufour")){
                                                landscapeImage.setPic(R.mipmap.hangzhouxihufour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("hangzhouxihufive")){
                                                landscapeImage.setPic(R.mipmap.hangzhouxihufive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("hangzhouxihusix")){
                                                landscapeImage.setPic(R.mipmap.hangzhouxihusix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("hangzhouxihuseven")){
                                                landscapeImage.setPic(R.mipmap.hangzhouxihuseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("hangzhouxihueight")){
                                                landscapeImage.setPic(R.mipmap.hangzhouxihueight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("hangzhouxihunine")){
                                                landscapeImage.setPic(R.mipmap.hangzhouxihunine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("hangzhouxihuten")){
                                                landscapeImage.setPic(R.mipmap.hangzhouxihuten);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qiandaohuone")){
                                                landscapeImage.setPic(R.mipmap.qiandaohuone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qiandaohutwo")){
                                                landscapeImage.setPic(R.mipmap.qiandaohutwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qiandaohuthree")){
                                                landscapeImage.setPic(R.mipmap.qiandaohuthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qiandaohufour")){
                                                landscapeImage.setPic(R.mipmap.qiandaohufour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qiandaohufive")){
                                                landscapeImage.setPic(R.mipmap.qiandaohufive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qiandaohusix")){
                                                landscapeImage.setPic(R.mipmap.qiandaohusix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yandangshanone")){
                                                landscapeImage.setPic(R.mipmap.yandangshanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yandangshantwo")){
                                                landscapeImage.setPic(R.mipmap.yandangshantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yandangshanthree")){
                                                landscapeImage.setPic(R.mipmap.yandangshanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yandangshanfour")){
                                                landscapeImage.setPic(R.mipmap.yandangshanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yandangshanfive")){
                                                landscapeImage.setPic(R.mipmap.yandangshanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yandangshansix")){
                                                landscapeImage.setPic(R.mipmap.yandangshansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yandangshanseven")){
                                                landscapeImage.setPic(R.mipmap.yandangshanseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yandangshaneight")){
                                                landscapeImage.setPic(R.mipmap.yandangshaneight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wuyishanone")){
                                                landscapeImage.setPic(R.mipmap.wuyishanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wuyishantwo")){
                                                landscapeImage.setPic(R.mipmap.wuyishantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wuyishanthree")){
                                                landscapeImage.setPic(R.mipmap.wuyishanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wuyishanfour")){
                                                landscapeImage.setPic(R.mipmap.wuyishanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wuyishanfive")){
                                                landscapeImage.setPic(R.mipmap.wuyishanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wuyishansix")){
                                                landscapeImage.setPic(R.mipmap.wuyishansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wuyishanseven")){
                                                landscapeImage.setPic(R.mipmap.wuyishanseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("gulangyuone")){
                                                landscapeImage.setPic(R.mipmap.gulangyuone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("gulangyutwo")){
                                                landscapeImage.setPic(R.mipmap.gulangyutwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("gulangyuthree")){
                                                landscapeImage.setPic(R.mipmap.gulangyuthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("gulangyufour")){
                                                landscapeImage.setPic(R.mipmap.gulangyufour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("gulangyufive")){
                                                landscapeImage.setPic(R.mipmap.gulangyufive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("meizhoudaoone")){
                                                landscapeImage.setPic(R.mipmap.meizhoudaoone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("meizhoudaotwo")){
                                                landscapeImage.setPic(R.mipmap.meizhoudaotwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("meizhoudaothree")){
                                                landscapeImage.setPic(R.mipmap.meizhoudaothree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("meizhoudaofour")){
                                                landscapeImage.setPic(R.mipmap.meizhoudaofour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("meizhoudaofive")){
                                                landscapeImage.setPic(R.mipmap.meizhoudaofive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("meizhoudaosix")){
                                                landscapeImage.setPic(R.mipmap.meizhoudaosix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("meizhoudaoseven")){
                                                landscapeImage.setPic(R.mipmap.meizhoudaoseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("meizhoudaoeight")){
                                                landscapeImage.setPic(R.mipmap.meizhoudaoeight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("lushanone")){
                                                landscapeImage.setPic(R.mipmap.lushanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("lushantwo")){
                                                landscapeImage.setPic(R.mipmap.lushantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("lushanthree")){
                                                landscapeImage.setPic(R.mipmap.lushanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("lushanfour")){
                                                landscapeImage.setPic(R.mipmap.lushanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("lushanfive")){
                                                landscapeImage.setPic(R.mipmap.lushanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("lushansix")){
                                                landscapeImage.setPic(R.mipmap.lushansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("lushanseven")){
                                                landscapeImage.setPic(R.mipmap.lushanseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("lushaneight")){
                                                landscapeImage.setPic(R.mipmap.lushaneight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("lushannine")){
                                                landscapeImage.setPic(R.mipmap.lushannine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("lushanten")){
                                                landscapeImage.setPic(R.mipmap.lushanten);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tengwanggeone")){
                                                landscapeImage.setPic(R.mipmap.tengwanggeone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tengwanggetwo")){
                                                landscapeImage.setPic(R.mipmap.tengwanggetwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tengwanggethree")){
                                                landscapeImage.setPic(R.mipmap.tengwanggethree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tengwanggefour")){
                                                landscapeImage.setPic(R.mipmap.tengwanggefour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wulingyuanone")){
                                                landscapeImage.setPic(R.mipmap.wulingyuanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wulingyuantwo")){
                                                landscapeImage.setPic(R.mipmap.wulingyuantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wulingyuanthree")){
                                                landscapeImage.setPic(R.mipmap.wulingyuanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wulingyuanfour")){
                                                landscapeImage.setPic(R.mipmap.wulingyuanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wulingyuanfive")){
                                                landscapeImage.setPic(R.mipmap.wulingyuanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wulingyuansix")){
                                                landscapeImage.setPic(R.mipmap.wulingyuansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wulingyuanseven")){
                                                landscapeImage.setPic(R.mipmap.wulingyuanseven);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("fenghuangguchengone")){
                                                landscapeImage.setPic(R.mipmap.fenghuangguchengone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("fenghuangguchengtwo")){
                                                landscapeImage.setPic(R.mipmap.fenghuangguchengtwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("fenghuangguchengthree")){
                                                landscapeImage.setPic(R.mipmap.fenghuangguchengthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("fenghuangguchengfour")){
                                                landscapeImage.setPic(R.mipmap.fenghuangguchengfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("fenghuangguchengfive")){
                                                landscapeImage.setPic(R.mipmap.fenghuangguchengfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangshanone")){
                                                landscapeImage.setPic(R.mipmap.wudangshanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangshantwo")){
                                                landscapeImage.setPic(R.mipmap.wudangshantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangshanthree")){
                                                landscapeImage.setPic(R.mipmap.wudangshanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangshanfour")){
                                                landscapeImage.setPic(R.mipmap.wudangshanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangshanfive")){
                                                landscapeImage.setPic(R.mipmap.wudangshanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangshansix")){
                                                landscapeImage.setPic(R.mipmap.wudangshansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangshanseven")){
                                                landscapeImage.setPic(R.mipmap.wudangshanseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangshaneight")){
                                                landscapeImage.setPic(R.mipmap.wudangshaneight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("wudangshannine")){
                                                landscapeImage.setPic(R.mipmap.wudangshannine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huanghelouone")){
                                                landscapeImage.setPic(R.mipmap.huanghelouone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangheloutwo")){
                                                landscapeImage.setPic(R.mipmap.huangheloutwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huanghelouthree")){
                                                landscapeImage.setPic(R.mipmap.huanghelouthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangheloufour")){
                                                landscapeImage.setPic(R.mipmap.huangheloufour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangheloufive")){
                                                landscapeImage.setPic(R.mipmap.huangheloufive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huanghelousix")){
                                                landscapeImage.setPic(R.mipmap.huanghelousix);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huanghelouseven")){
                                                landscapeImage.setPic(R.mipmap.huanghelouseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("danxiashanone")){
                                                landscapeImage.setPic(R.mipmap.danxiashanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("danxiashantwo")){
                                                landscapeImage.setPic(R.mipmap.danxiashantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("danxiashanthree")){
                                                landscapeImage.setPic(R.mipmap.danxiashanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("danxiashanfour")){
                                                landscapeImage.setPic(R.mipmap.danxiashanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("danxiashanfive")){
                                                landscapeImage.setPic(R.mipmap.danxiashanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("danxiashansix")){
                                                landscapeImage.setPic(R.mipmap.danxiashansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("danxiashanseven")){
                                                landscapeImage.setPic(R.mipmap.danxiashanseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("luofushanone")){
                                                landscapeImage.setPic(R.mipmap.luofushanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("luofushantwo")){
                                                landscapeImage.setPic(R.mipmap.luofushantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("luofushanthree")){
                                                landscapeImage.setPic(R.mipmap.luofushanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("luofushanfour")){
                                                landscapeImage.setPic(R.mipmap.luofushanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("luofushanfive")){
                                                landscapeImage.setPic(R.mipmap.luofushanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("luofushansix")){
                                                landscapeImage.setPic(R.mipmap.luofushansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("luofushanseven")){
                                                landscapeImage.setPic(R.mipmap.luofushanseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("luofushaneight")){
                                                landscapeImage.setPic(R.mipmap.luofushaneight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("luofushannine")){
                                                landscapeImage.setPic(R.mipmap.luofushannine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tianyahaijiaoone")){
                                                landscapeImage.setPic(R.mipmap.tianyahaijiaoone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tianyahaijiaotwo")){
                                                landscapeImage.setPic(R.mipmap.tianyahaijiaotwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tianyahaijiaothree")){
                                                landscapeImage.setPic(R.mipmap.tianyahaijiaothree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tianyahaijiaofour")){
                                                landscapeImage.setPic(R.mipmap.tianyahaijiaofour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tianyahaijiaofive")){
                                                landscapeImage.setPic(R.mipmap.tianyahaijiaofive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tianyahaijiaosix")){
                                                landscapeImage.setPic(R.mipmap.tianyahaijiaosix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tianyahaijiaoseven")){
                                                landscapeImage.setPic(R.mipmap.tianyahaijiaoseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tianyahaijiaoeight")){
                                                landscapeImage.setPic(R.mipmap.tianyahaijiaoeight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("tianyahaijiaonine")){
                                                landscapeImage.setPic(R.mipmap.tianyahaijiaonine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yalongwanone")){
                                                landscapeImage.setPic(R.mipmap.yalongwanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yalongwantwo")){
                                                landscapeImage.setPic(R.mipmap.yalongwantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yalongwanthree")){
                                                landscapeImage.setPic(R.mipmap.yalongwanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yalongwanfour")){
                                                landscapeImage.setPic(R.mipmap.yalongwanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("yalongwanfive")){
                                                landscapeImage.setPic(R.mipmap.yalongwanfive);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qingchengshandujiangyanone")){
                                                landscapeImage.setPic(R.mipmap.qingchengshandujiangyanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qingchengshandujiangyantwo")){
                                                landscapeImage.setPic(R.mipmap.qingchengshandujiangyantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qingchengshandujiangyanthree")){
                                                landscapeImage.setPic(R.mipmap.qingchengshandujiangyanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qingchengshandujiangyanfour")){
                                                landscapeImage.setPic(R.mipmap.qingchengshandujiangyanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qingchengshandujiangyanfive")){
                                                landscapeImage.setPic(R.mipmap.qingchengshandujiangyanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qingchengshandujiangyansix")){
                                                landscapeImage.setPic(R.mipmap.qingchengshandujiangyansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qingchengshandujiangyanseven")){
                                                landscapeImage.setPic(R.mipmap.qingchengshandujiangyanseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qingchengshandujiangyaneight")){
                                                landscapeImage.setPic(R.mipmap.qingchengshandujiangyaneight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qingchengshandujiangyannine")){
                                                landscapeImage.setPic(R.mipmap.qingchengshandujiangyannine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("qingchengshandujiangyanten")){
                                                landscapeImage.setPic(R.mipmap.qingchengshandujiangyanten);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafoone")){
                                                landscapeImage.setPic(R.mipmap.leshandafoone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafotwo")){
                                                landscapeImage.setPic(R.mipmap.leshandafotwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafothree")){
                                                landscapeImage.setPic(R.mipmap.leshandafothree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafofour")){
                                                landscapeImage.setPic(R.mipmap.leshandafofour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafofive")){
                                                landscapeImage.setPic(R.mipmap.leshandafofive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafosix")){
                                                landscapeImage.setPic(R.mipmap.leshandafosix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafoseven")){
                                                landscapeImage.setPic(R.mipmap.leshandafoseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafoeight")){
                                                landscapeImage.setPic(R.mipmap.leshandafoeight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafonine")){
                                                landscapeImage.setPic(R.mipmap.leshandafonine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafoten")){
                                                landscapeImage.setPic(R.mipmap.leshandafoten);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafoeleven")){
                                                landscapeImage.setPic(R.mipmap.leshandafoeleven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafotwelve")){
                                                landscapeImage.setPic(R.mipmap.leshandafotwelve);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafothirteen")){
                                                landscapeImage.setPic(R.mipmap.leshandafothirteen);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafofourteen")){
                                                landscapeImage.setPic(R.mipmap.leshandafofourteen);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("leshandafofifteen")){
                                                landscapeImage.setPic(R.mipmap.leshandafofifteen);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("emeishanone")){
                                                landscapeImage.setPic(R.mipmap.emeishanone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("emeishantwo")){
                                                landscapeImage.setPic(R.mipmap.emeishantwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("emeishanthree")){
                                                landscapeImage.setPic(R.mipmap.emeishanthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("emeishanfour")){
                                                landscapeImage.setPic(R.mipmap.emeishanfour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("emeishanfive")){
                                                landscapeImage.setPic(R.mipmap.emeishanfive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("emeishansix")){
                                                landscapeImage.setPic(R.mipmap.emeishansix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("emeishanseven")){
                                                landscapeImage.setPic(R.mipmap.emeishanseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuzhaigouone")){
                                                landscapeImage.setPic(R.mipmap.jiuzhaigouone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuzhaigoutwo")){
                                                landscapeImage.setPic(R.mipmap.jiuzhaigoutwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuzhaigouthree")){
                                                landscapeImage.setPic(R.mipmap.jiuzhaigouthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuzhaigoufour")){
                                                landscapeImage.setPic(R.mipmap.jiuzhaigoufour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuzhaigoufive")){
                                                landscapeImage.setPic(R.mipmap.jiuzhaigoufive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuzhaigousix")){
                                                landscapeImage.setPic(R.mipmap.jiuzhaigousix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuzhaigouseven")){
                                                landscapeImage.setPic(R.mipmap.jiuzhaigouseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuzhaigoueight")){
                                                landscapeImage.setPic(R.mipmap.jiuzhaigoueight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuzhaigounine")){
                                                landscapeImage.setPic(R.mipmap.jiuzhaigounine);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuzhaigouten")){
                                                landscapeImage.setPic(R.mipmap.jiuzhaigouten);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuzhaigoueleven")){
                                                landscapeImage.setPic(R.mipmap.jiuzhaigoueleven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuzhaigoutwelve")){
                                                landscapeImage.setPic(R.mipmap.jiuzhaigoutwelve);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("jiuzhaigouthirteen")){
                                                landscapeImage.setPic(R.mipmap.jiuzhaigouthirteen);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangguoshupubuone")){
                                                landscapeImage.setPic(R.mipmap.huangguoshupubuone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangguoshupubutwo")){
                                                landscapeImage.setPic(R.mipmap.huangguoshupubutwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangguoshupubuthree")){
                                                landscapeImage.setPic(R.mipmap.huangguoshupubuthree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangguoshupubufour")){
                                                landscapeImage.setPic(R.mipmap.huangguoshupubufour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangguoshupubufive")){
                                                landscapeImage.setPic(R.mipmap.huangguoshupubufive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangguoshupubusix")){
                                                landscapeImage.setPic(R.mipmap.huangguoshupubusix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("huangguoshupubuseven")){
                                                landscapeImage.setPic(R.mipmap.huangguoshupubuseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dalisantaone")){
                                                landscapeImage.setPic(R.mipmap.dalisantaone);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dalisantatwo")){
                                                landscapeImage.setPic(R.mipmap.dalisantatwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dalisantathree")){
                                                landscapeImage.setPic(R.mipmap.dalisantathree);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dalisantafour")){
                                                landscapeImage.setPic(R.mipmap.dalisantafour);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dalisantafive")){
                                                landscapeImage.setPic(R.mipmap.dalisantafive);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dalisantasix")){
                                                landscapeImage.setPic(R.mipmap.dalisantasix);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dalisantaseven")){
                                                landscapeImage.setPic(R.mipmap.dalisantaseven);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dalisantaeight")){
                                                landscapeImage.setPic(R.mipmap.dalisantaeight);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("dalisantanine")){
                                                landscapeImage.setPic(R.mipmap.dalisantanine);
                                            }

                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("xianggelilasongzanlinsione")){
                                                landscapeImage.setPic(R.mipmap.xianggelilasongzanlinsione);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("xianggelilasongzanlinsitwo")){
                                                landscapeImage.setPic(R.mipmap.xianggelilasongzanlinsitwo);
                                            }
                                            if(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue().trim().equals("xianggelilasongzanlinsithree")){
                                                landscapeImage.setPic(R.mipmap.xianggelilasongzanlinsithree);
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

                return LandscapeImageFragment.newInstance(landscapeImage.getImageEnName(),landscapeImage.getImageDescribe(),landscapeImage.getPic());
            }

            @Override
            public int getCount() {
                return landscapeImageList.size();
            }
        });
        String landscapeImageId = getIntent().getStringExtra(LandscapeImageFragment.EXTRA_LANDSCAPE_IMAGE_ID);
        for(int i = 0; i < landscapeImageList.size(); i++){

            if(landscapeImageList.get(i).getImageEnName().equals(landscapeImageId)){
                viewPager.setCurrentItem(i);
                break ;
            }
        }
    }
}
