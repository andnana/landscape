package io.xicp.myspace.region2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class LandscapeListFragment extends ListFragment {
//    private LandscapeFeed landscapeFeed;
    private List<Landscape> landscapeList = new ArrayList<Landscape>();
    private String regionEnName = "";
    private String landscapeEnName = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActivity().setTitle(R.string.app_name);
//        landscapeEnName = getArguments().getString(RegionFragment.EXTRA_REGION_ID);
        regionEnName = getActivity().getIntent().getStringExtra(RegionFragment.EXTRA_REGION_ID);
        System.out.println(regionEnName);
        System.out.println("#$#$#$");
//        landscapeFeed = getLandscapeFeed();
//
//        landscapeList = landscapeFeed.getAlllandscapeList();
//        System.out.println(landscapeList.size());


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
                                landscape.setEnName(landscapeChildNodeList.item(k).getAttributes().getNamedItem("en_name").getNodeValue().trim());
                                NodeList nodeList0 = landscapeChildNodeList.item(k).getChildNodes();
                                for(int r = 0; r < nodeList0.getLength(); r++){
                                    if (nodeList0.item(r).getNodeName().trim().equals("name")) {
                                        landscape.setName(nodeList0.item(r).getTextContent());
                                    } else if (nodeList0.item(r).getNodeName().trim().equals("describe")) {
                                        landscape.setDescribe(nodeList0.item(r).getTextContent());
                                    } else if (nodeList0.item(r).getNodeName().trim().equals("image")) {
                                        LandscapeImage landscapeImage = new LandscapeImage();

                                        landscapeImage.setImageEnName(nodeList0.item(r).getAttributes().getNamedItem("image_en_name").getNodeValue());
                                        NodeList nodeList1 = nodeList0.item(r).getChildNodes();
                                        for (int u = 0; u < nodeList1.getLength(); u++) {
                                            if (nodeList1.item(u).getNodeName().trim().equals("image_describe")) {

                                                landscapeImage.setImageDescribe(nodeList1.item(u).getTextContent().trim());
                                            }
                                        }
                                        landscape.addImage(landscapeImage);
                                    }
                                }
                          landscapeList.add(landscape);
                            }

                        }
                        break label1 ;
                    }


                }

            }


        }
        LandscapeAdapter adapter = new LandscapeAdapter(landscapeList);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Landscape landscape = landscapeList.get(position);
//        System.out.println(region.getRegionName());
//        System.out.println(region.getEnName());
//        Intent intent = new Intent(getActivity(), RegionActivity.class);
        Intent intent = new Intent(getActivity(), LandscapeImageListActivity.class);
        intent.putExtra(RegionFragment.EXTRA_REGION_ID, regionEnName);
        intent.putExtra("landscape_item_en_name", landscape.getEnName());
        startActivity(intent);
    }

    private class LandscapeAdapter extends ArrayAdapter<Landscape> {
        public LandscapeAdapter(List<Landscape> landscape){
            super(getActivity(), 0, landscape);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_landscape, null);
            }
            Landscape landscape = getItem(position);
            TextView regionNameTextView = (TextView)convertView.findViewById(R.id.name);
            regionNameTextView.setText(landscape.getName());
            TextView abbreviationTextView = (TextView)convertView.findViewById(R.id.describe);
            abbreviationTextView.setText(landscape.getDescribe());

            return convertView;

        }
    }
/*    public LandscapeFeed getLandscapeFeed(){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            LandscapeHandler handler = new LandscapeHandler();
            reader.setContentHandler(handler);
            InputSource is = new InputSource(this.getClass().getClassLoader().getResourceAsStream("assets/region.xml"));//取得本地xml文件
            System.out.println("abcd");
            System.out.println(is+"is");

            reader.parse(is);

            return handler.getLandscapeFeed();
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
    }*/
}
