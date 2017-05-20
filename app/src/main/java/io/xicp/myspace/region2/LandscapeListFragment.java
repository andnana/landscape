package io.xicp.myspace.region2;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
 * Created by Administrator on 2017/5/7 0007.
 */

public class LandscapeListFragment extends Fragment {
//    private LandscapeFeed landscapeFeed;
    private List<Landscape> landscapeList = new ArrayList<Landscape>();
    private String regionEnName = "";


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("region_en_name",regionEnName);
    }

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            regionEnName = savedInstanceState.getString("region_en_name");
        }

        regionEnName = getActivity().getIntent().getStringExtra(RegionFragment.EXTRA_REGION_ID);



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

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.landscape_list_activity_fragment, container, false);
        ImageView backImageView = (ImageView)v.findViewById(R.id.back);
        ListView landscapeListView = (ListView)v.findViewById(R.id.landscapeList);
        LandscapeAdapter adapter = new LandscapeAdapter(landscapeList);
        landscapeListView.setAdapter(adapter);
        landscapeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                Landscape landscape = landscapeList.get(position);

                Intent intent = new Intent(getActivity(), LandscapeImageListActivity.class);
                intent.putExtra(RegionFragment.EXTRA_REGION_ID, regionEnName);
                intent.putExtra("landscape_item_en_name", landscape.getEnName());
                startActivity(intent);
            }
        });
        backImageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                Intent intent = new Intent(getActivity(), RegionPagerActivity.class);
//                intent.putExtra(RegionFragment.EXTRA_REGION_ID, regionEnName);
//                startActivity(intent);
                getActivity().finish();
            }
        });
        return v ;

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
            TextView describeTextView = (TextView)convertView.findViewById(R.id.describe);
            describeTextView.setText(landscape.getDescribe());

            return convertView;

        }
    }

}
