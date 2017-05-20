package io.xicp.myspace.region2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import cse.der.qsw.AdManager;
import cse.der.qsw.nm.bn.BannerManager;
import cse.der.qsw.nm.bn.BannerViewListener;
import cse.der.qsw.onlineconfig.OnlineConfigCallBack;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;
;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


/**
 * Created by Administrator on 2017/5/13 0013.
 */

public class TestFragment extends Fragment {
  private   List<Region> regionList = new ArrayList<Region>();

    private RadioGroup answerRadioGroup;
    private RadioButton answerRadioButtonOne;
    private RadioButton answerRadioButtionTwo;
    private RadioButton answerRadioButtionThree;
    private RadioButton answerRadioButtionFour;
    private TextView questionTextView;
    private ProgressBar testFinishPercent;
    private Button confirmButton;
    private TextView aaaaa;
    private Landscape landscape;
    private int landscapeCount = 0;
    private int answerIndex = 0;
    private Answer answerSwap = new Answer();
    private LinearLayout bannerLayout;
    private View bannerView;
    private  Region region2;
    private List<String> regionNameListOne = new ArrayList<String>();
    private List<String> regionNameListTwo = new ArrayList<String>();
    private List<Answer> answerList = new ArrayList<Answer>();
    private boolean noClick = true;
    private int regionRandomInt = -1;
    private int region2LandscapeRandomInt;
    private Random random = new Random();
    int correctRegionRandomInt = 0;
    private  Answer answer1 = new Answer();
    private boolean globalIsCorrect = false;
    int answerRegionRandomIntOne = 0;
    int     answerRegionRandomIntTwo = 0;
    int answerRegionRandomIntThree = 0;
    int   answerRegionRandomIntFour = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        Document doc = null;
        //获取DOM解析器
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            //解析XML文档，并获取该XML文档对应的Document
            doc = builder.parse(getResources().getAssets().open("region.xml"));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
            for (int i = 0; i < nodeList.getLength(); i++) {


                Node regionNode = nodeList.item(i);

            Region region = new Region();



                    //获取所有comBook下的所有子元素

                    NodeList regionChildNodes = regionNode.getChildNodes();
                    //遍历每个子元素

                    for (int j = 0; j < regionChildNodes.getLength(); j++) {
                        Node node = regionChildNodes.item(j);
                        if (node.getNodeName().trim().equals("region_name")) {
                            region.setRegionName(node.getTextContent().trim());
                            continue;
                        } else if (node.getNodeName().trim().equals("landscape")) {

                            NodeList landscapeChildNodeList = node.getChildNodes();
                            for (int k = 0; k < landscapeChildNodeList.getLength(); k++) {
                                if (landscapeChildNodeList.item(k).getNodeName().trim().equals("landscapeitem")) {
                                    Landscape landscape = new Landscape();
                                    landscape.setRegion_name(regionChildNodes.item(j).getTextContent().trim());
                                    landscape.setEnName(landscapeChildNodeList.item(k).getAttributes().getNamedItem("en_name").getNodeValue().trim());
                                    NodeList nodeList0 = landscapeChildNodeList.item(k).getChildNodes();
                                    for (int r = 0; r < nodeList0.getLength(); r++) {
                                        if (nodeList0.item(r).getNodeName().trim().equals("name")) {
                                            landscape.setName(nodeList0.item(r).getTextContent());
                                        } else if (nodeList0.item(r).getNodeName().trim().equals("describe")) {
                                            landscape.setDescribe(nodeList0.item(r).getTextContent());
                                        } else if (nodeList0.item(r).getNodeName().trim().equals("image")) {


                                        }
                                    }
                                    region.getLandscapeList().add(landscape);
                                }

                            }


                        }


                    }


                        regionList.add(region);



            }


        for(Region region:regionList){
            regionNameListOne.add(region.getRegionName());
        }
        RegionEnName[] regionEnNameArr = RegionEnName.values();
        setRegionRandomInt();
        region2 = regionList.get(getRegionRandomInt());


        while(region2.getLandscapeList().size() <= 0){
            regionList.remove(getRegionRandomInt());
            setRegionRandomInt();
            region2 = regionList.get(getRegionRandomInt());

        }
        setRegion2LandscapeRandomInt();
        landscape = region2.getLandscapeList().get(getRegion2LandscapeRandomInt());
        for(Region region : regionList){
            for(Landscape landscape : region.getLandscapeList()){

                landscapeCount++;
            }
        }

    }
    class MyOnlineConfigCallBack implements OnlineConfigCallBack{
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
public void setRegion2LandscapeRandomInt(){
    if(region2.getLandscapeList().size() > 0){
        region2LandscapeRandomInt =  random.nextInt(region2.getLandscapeList().size());

    }else{
        region2LandscapeRandomInt = -1;
    }

}

public int getRegion2LandscapeRandomInt(){
    return region2LandscapeRandomInt;
}
public  void setRegionRandomInt(){

    regionRandomInt = random.nextInt(regionList.size());

}
public int getRegionRandomInt(){
    return regionRandomInt;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.test_fragment, container, false);
        AdManager.getInstance(getActivity()).init("3cf12c7d8c82c297", "a8414f5f3deeea12", false);
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

        ImageView backImageView = (ImageView) v.findViewById(R.id.back);
        answerRadioGroup = (RadioGroup)v.findViewById(R.id.answer);
        answerRadioButtonOne = (RadioButton) v.findViewById(R.id.answerone);
        answerRadioButtionTwo = (RadioButton)v.findViewById(R.id.answertwo);
        answerRadioButtionThree = (RadioButton)v.findViewById(R.id.answerthree);
        answerRadioButtionFour = (RadioButton)v.findViewById(R.id.answerfour);
        questionTextView = (TextView) v.findViewById(R.id.question);
        aaaaa = (TextView)v.findViewById(R.id.aaaaa);

        confirmButton = (Button)v.findViewById(R.id.confirm);
        testFinishPercent = (ProgressBar)v.findViewById(R.id.test_finish_percent);
        testFinishPercent.setMax(landscapeCount);
        questionTextView.setText(landscape.getName()+"属于哪个地区?");
        aaaaa.setText(region2.getRegionName());


        answer1.setQuestion(landscape.getName());
        answer1.setAnswer(region2.getRegionName());
         correctRegionRandomInt = random.nextInt(4);


        switch (correctRegionRandomInt){
            case 0:


                answerRadioButtonOne.setText(region2.getRegionName());
                regionNameListOne.remove(region2.getRegionName());
                regionNameListTwo.add(region2.getRegionName());

                answerRegionRandomIntTwo = random.nextInt(regionNameListOne.size());
                answerRadioButtionTwo.setText(regionNameListOne.get(answerRegionRandomIntTwo));
                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntTwo));
                regionNameListOne.remove(answerRegionRandomIntTwo);

                 answerRegionRandomIntThree = random.nextInt(regionNameListOne.size());
                answerRadioButtionThree.setText(regionNameListOne.get(answerRegionRandomIntThree));
                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntThree));
                regionNameListOne.remove(answerRegionRandomIntThree);

                 answerRegionRandomIntFour = random.nextInt(regionNameListOne.size());
                answerRadioButtionFour.setText(regionNameListOne.get(answerRegionRandomIntFour));
                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntFour));
                regionNameListOne.remove(answerRegionRandomIntFour);


                break;
            case 1:

                answerRadioButtionTwo.setText(region2.getRegionName());
                regionNameListOne.remove(region2.getRegionName());
                regionNameListTwo.add(region2.getRegionName());

                answerRegionRandomIntOne = random.nextInt(regionNameListOne.size());
                answerRadioButtonOne.setText(regionNameListOne.get(answerRegionRandomIntOne));
                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntOne));
                regionNameListOne.remove(answerRegionRandomIntOne);




                answerRegionRandomIntThree = random.nextInt(regionNameListOne.size());
                answerRadioButtionThree.setText(regionNameListOne.get(answerRegionRandomIntThree));
                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntThree));
                regionNameListOne.remove(answerRegionRandomIntThree);

                answerRegionRandomIntFour = random.nextInt(regionNameListOne.size());
                answerRadioButtionFour.setText(regionNameListOne.get(answerRegionRandomIntFour));
                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntFour));
                regionNameListOne.remove(answerRegionRandomIntFour);


                break;
            case 2:

                answerRadioButtionThree.setText(region2.getRegionName());
                regionNameListOne.remove(region2.getRegionName());
                regionNameListTwo.add(region2.getRegionName());

                answerRegionRandomIntOne = random.nextInt(regionNameListOne.size());
                answerRadioButtonOne.setText(regionNameListOne.get(answerRegionRandomIntOne));
                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntOne));
                regionNameListOne.remove(answerRegionRandomIntOne);

                answerRegionRandomIntTwo = random.nextInt(regionNameListOne.size());
                answerRadioButtionTwo.setText(regionNameListOne.get(answerRegionRandomIntTwo));
                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntTwo));
                regionNameListOne.remove(answerRegionRandomIntTwo);




                answerRegionRandomIntFour = random.nextInt(regionNameListOne.size());
                answerRadioButtionFour.setText(regionNameListOne.get(answerRegionRandomIntFour));
                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntFour));
                regionNameListOne.remove(answerRegionRandomIntFour);


            break;
            case 3:

                answerRadioButtionFour.setText(region2.getRegionName());
                regionNameListOne.remove(region2.getRegionName());
                regionNameListTwo.add(region2.getRegionName());

                answerRegionRandomIntOne = random.nextInt(regionNameListOne.size());
                answerRadioButtonOne.setText(regionNameListOne.get(answerRegionRandomIntOne));
                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntOne));
                regionNameListOne.remove(answerRegionRandomIntOne);

                answerRegionRandomIntTwo = random.nextInt(regionNameListOne.size());
                answerRadioButtionTwo.setText(regionNameListOne.get(answerRegionRandomIntTwo));
                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntTwo));
                regionNameListOne.remove(answerRegionRandomIntTwo);

                answerRegionRandomIntThree = random.nextInt(regionNameListOne.size());
                answerRadioButtionThree.setText(regionNameListOne.get(answerRegionRandomIntThree));
                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntThree));
                regionNameListOne.remove(answerRegionRandomIntThree);





            break;
            default:

        }

        answerRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                noClick = false;
                if(answerRadioButtonOne.getId() == checkedId && correctRegionRandomInt == 0){
                    globalIsCorrect = true;

                }else if((answerRadioButtionTwo.getId() == checkedId) && (correctRegionRandomInt == 1)) {
                    globalIsCorrect = true;

                }else if((answerRadioButtionThree.getId() == checkedId) && (correctRegionRandomInt == 2)){
                    globalIsCorrect = true;

                }else if((answerRadioButtionFour.getId()) == checkedId && (correctRegionRandomInt == 3)){
                    globalIsCorrect = true;

                }else {
                    globalIsCorrect = false;

                }


            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Answer answer = new Answer();

                if(answerIndex == 0){

                    region2.getLandscapeList().remove(getRegion2LandscapeRandomInt());
                    if ((correctRegionRandomInt == 0) && noClick) {
                        answer1.setIsCorrect("回答正确");
                    }else if(globalIsCorrect){
                        answer1.setIsCorrect("回答正确");
                    }else{
                        answer1.setIsCorrect("回答错误");
                    }

                    answerList.add(answer1);
                }else{
                    if ((correctRegionRandomInt == 0) && noClick) {
                        answer.setIsCorrect("回答正确");
                    }else if(globalIsCorrect){
                        answer.setIsCorrect("回答正确");
                    }else{
                        answer.setIsCorrect("回答错误");
                    }
                        answer.setQuestion(answerSwap.getQuestion());
                        answer.setAnswer(answerSwap.getAnswer());
                    answerList.add(answer);
                }

                if(regionList.size() > 0){
                    answerIndex ++;
                    testFinishPercent.setProgress(answerIndex);
                    regionNameListOne.addAll(regionNameListTwo);
                    regionNameListTwo.clear();




                    if( region2.getLandscapeList().size() < 1){
                        regionList.remove(getRegionRandomInt());
                    }




                    if(regionList.size() > 0) {
                        setRegionRandomInt();
                        region2 = regionList.get(getRegionRandomInt());

                        while (region2.getLandscapeList().size() <= 0) {
                            //check if beyond last region
                            if (regionList.size() > 1) {
                                regionList.remove(getRegionRandomInt());
                                setRegionRandomInt();
                                region2 = regionList.get(getRegionRandomInt());

                            }


                        }
                        if(regionList.size() > 0){
                        setRegion2LandscapeRandomInt();
                        landscape = region2.getLandscapeList().get(getRegion2LandscapeRandomInt());
                        //add info to userinterface
                        aaaaa.setText(region2.getRegionName());


                            if ((correctRegionRandomInt == 0) && noClick) {


                                Toast.makeText(getActivity(), R.string.correct, Toast.LENGTH_SHORT).show();
                            } else if (globalIsCorrect) {

                                Toast.makeText(getActivity(), R.string.correct, Toast.LENGTH_SHORT).show();
                            } else {


                                Toast.makeText(getActivity(), R.string.wrong, Toast.LENGTH_SHORT).show();
                            }
                            globalIsCorrect = false;

                            noClick = true;




                        questionTextView.setText(landscape.getName() + "属于哪个地区?");


                            answerSwap.setQuestion(landscape.getName());
                            answerSwap.setAnswer(region2.getRegionName());
                            region2.getLandscapeList().remove(getRegion2LandscapeRandomInt());


                        correctRegionRandomInt = random.nextInt(4);


                        switch (correctRegionRandomInt) {
                            case 0:


                                answerRadioButtonOne.setText(region2.getRegionName());
                                regionNameListOne.remove(region2.getRegionName());
                                regionNameListTwo.add(region2.getRegionName());

                                answerRegionRandomIntTwo = random.nextInt(regionNameListOne.size());
                                answerRadioButtionTwo.setText(regionNameListOne.get(answerRegionRandomIntTwo));
                                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntTwo));
                                regionNameListOne.remove(answerRegionRandomIntTwo);

                                answerRegionRandomIntThree = random.nextInt(regionNameListOne.size());
                                answerRadioButtionThree.setText(regionNameListOne.get(answerRegionRandomIntThree));
                                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntThree));
                                regionNameListOne.remove(answerRegionRandomIntThree);

                                answerRegionRandomIntFour = random.nextInt(regionNameListOne.size());
                                answerRadioButtionFour.setText(regionNameListOne.get(answerRegionRandomIntFour));
                                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntFour));
                                regionNameListOne.remove(answerRegionRandomIntFour);


                                break;
                            case 1:

                                answerRadioButtionTwo.setText(region2.getRegionName());
                                regionNameListOne.remove(region2.getRegionName());
                                regionNameListTwo.add(region2.getRegionName());

                                answerRegionRandomIntOne = random.nextInt(regionNameListOne.size());
                                answerRadioButtonOne.setText(regionNameListOne.get(answerRegionRandomIntOne));
                                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntOne));
                                regionNameListOne.remove(answerRegionRandomIntOne);


                                answerRegionRandomIntThree = random.nextInt(regionNameListOne.size());
                                answerRadioButtionThree.setText(regionNameListOne.get(answerRegionRandomIntThree));
                                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntThree));
                                regionNameListOne.remove(answerRegionRandomIntThree);

                                answerRegionRandomIntFour = random.nextInt(regionNameListOne.size());
                                answerRadioButtionFour.setText(regionNameListOne.get(answerRegionRandomIntFour));
                                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntFour));
                                regionNameListOne.remove(answerRegionRandomIntFour);


                                break;
                            case 2:

                                answerRadioButtionThree.setText(region2.getRegionName());
                                regionNameListOne.remove(region2.getRegionName());
                                regionNameListTwo.add(region2.getRegionName());

                                answerRegionRandomIntOne = random.nextInt(regionNameListOne.size());
                                answerRadioButtonOne.setText(regionNameListOne.get(answerRegionRandomIntOne));
                                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntOne));
                                regionNameListOne.remove(answerRegionRandomIntOne);

                                answerRegionRandomIntTwo = random.nextInt(regionNameListOne.size());
                                answerRadioButtionTwo.setText(regionNameListOne.get(answerRegionRandomIntTwo));
                                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntTwo));
                                regionNameListOne.remove(answerRegionRandomIntTwo);


                                answerRegionRandomIntFour = random.nextInt(regionNameListOne.size());
                                answerRadioButtionFour.setText(regionNameListOne.get(answerRegionRandomIntFour));
                                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntFour));
                                regionNameListOne.remove(answerRegionRandomIntFour);


                                break;
                            case 3:

                                answerRadioButtionFour.setText(region2.getRegionName());
                                regionNameListOne.remove(region2.getRegionName());
                                regionNameListTwo.add(region2.getRegionName());

                                answerRegionRandomIntOne = random.nextInt(regionNameListOne.size());
                                answerRadioButtonOne.setText(regionNameListOne.get(answerRegionRandomIntOne));
                                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntOne));
                                regionNameListOne.remove(answerRegionRandomIntOne);

                                answerRegionRandomIntTwo = random.nextInt(regionNameListOne.size());
                                answerRadioButtionTwo.setText(regionNameListOne.get(answerRegionRandomIntTwo));
                                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntTwo));
                                regionNameListOne.remove(answerRegionRandomIntTwo);

                                answerRegionRandomIntThree = random.nextInt(regionNameListOne.size());
                                answerRadioButtionThree.setText(regionNameListOne.get(answerRegionRandomIntThree));
                                regionNameListTwo.add(regionNameListOne.get(answerRegionRandomIntThree));
                                regionNameListOne.remove(answerRegionRandomIntThree);


                                break;
                            default:

                        }


                        answerRadioButtonOne.setChecked(true);
                        answerRadioButtionTwo.setChecked(false);
                        answerRadioButtionThree.setChecked(false);
                        answerRadioButtionFour.setChecked(false);
                    }

                    }
                    if(regionList.size() < 1){
                        //last landscape question if correct
                        if ((correctRegionRandomInt == 0) && noClick) {


                            Toast.makeText(getActivity(), R.string.correct, Toast.LENGTH_SHORT).show();
                        } else if (globalIsCorrect) {


                            Toast.makeText(getActivity(), R.string.correct, Toast.LENGTH_SHORT).show();
                        } else {


                            Toast.makeText(getActivity(), R.string.wrong, Toast.LENGTH_SHORT).show();
                        }





                            addElement(answerList);





                        Intent intent = new Intent(getActivity(), AnswerListActivity.class);
//                        intent.putExtra("answer_list", (Serializable)answerList);
                        startActivity(intent);
                        getActivity().finish();
//                        Toast.makeText(getActivity(),"游戏结束", Toast.LENGTH_SHORT).show();
                    }








                }


            }
        });
        backImageView = (ImageView)v.findViewById(R.id.back);
        backImageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getActivity().finish();
            }
        });
        return v;
    }
    public void addElement(List<Answer> answerList){
        File file = new File(Environment.getExternalStorageDirectory(),
                "answer2.xml");
        if(!file.exists()){

            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{

        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
// 获得一个序列化工具
        XmlSerializer serializer = Xml.newSerializer();
        try {
            serializer.setOutput(fos, "utf-8");

        // 设置文件头
        serializer.startDocument("utf-8", true);
        serializer.startTag(null, "answers");
            int i = 0;

        for (Answer answer : answerList) {
            i++;
            serializer.startTag(null, "answer");
            serializer.attribute(null, "id", String.valueOf(i));
            // 写姓名
            serializer.startTag(null, "question");
            serializer.text(answer.getQuestion());
            serializer.endTag(null, "question");
            // 写性别
            serializer.startTag(null, "correct_answer");
            serializer.text(answer.getAnswer());
            serializer.endTag(null, "correct_answer");
            // 写年龄
            serializer.startTag(null, "is_correct");
            serializer.text(answer.getIsCorrect());
            serializer.endTag(null, "is_correct");

            serializer.endTag(null, "answer");
        }
        serializer.endTag(null, "answers");
        serializer.endDocument();
        fos.close();
        Toast.makeText(getActivity(), "写入成功",  Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
        e.printStackTrace();
        Toast.makeText(getActivity(), "写入失败",  Toast.LENGTH_SHORT).show();
    }



        }





//
//    //保存xml文件
//    public void saveXml(Document document){
//
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        try {
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource domSource = new DOMSource(document);
//
//            StreamResult streamResult = new StreamResult( getActivity().openFileOutput("answer.xml",MODE_PRIVATE));
//
//            transformer.transform(domSource, streamResult);
//            InputStream is =  getActivity().openFileInput("answer.xml");
//            byte[] buffer = new byte[1024];
//            int count = 0;
//            while (true) {
//                count++;
//                int len = is.read(buffer);
//                if (len == -1) {
//                    break;
//                }
//                String content = new String(buffer);
//                System.out.println(content);
//            }
//            is.close();
//
//
//
//
//} catch (TransformerConfigurationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (TransformerException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }catch (IOException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//        }
//
//    }

}
