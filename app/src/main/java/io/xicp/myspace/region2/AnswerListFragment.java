package io.xicp.myspace.region2;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import cse.der.qsw.AdManager;
import cse.der.qsw.nm.bn.BannerManager;
import cse.der.qsw.nm.bn.BannerViewListener;
import cse.der.qsw.onlineconfig.OnlineConfigCallBack;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class AnswerListFragment extends Fragment {
    private List<Answer> answerList = new ArrayList<Answer>();
    private double answerCount = 0;
    private double correctCount = 0;
    private Integer score = 0;
    private LinearLayout bannerLayout;
    private View bannerView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//            answerList = (List<Answer>)(List)getActivity().getIntent().getSerializableExtra("answer_list");


    if(answerList == null || answerList.size() < 1 ){
        answerList = readxml();
    }
    answerCount = answerList.size();
    for(Answer answer : answerList){
        if(answer.getIsCorrect().trim().equals("回答正确")){
            correctCount++;
        }
    }
    double a = correctCount / answerCount * 100;
    score = Integer.valueOf(Double.valueOf(a).intValue());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.answer_list_acitivity_fragment, container, false);
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
        backImageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getActivity().finish();
            }
        });
        TextView testTextView = (TextView)v.findViewById(R.id.test);
        testTextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), TestActivity.class);

                startActivity(intent);
                getActivity().finish();
            }
        });
        TextView testResultInfoTextView = (TextView)v.findViewById(R.id.test_result_info);
        testResultInfoTextView.setText("测试成绩：" + "一共" + Integer.valueOf(Double.valueOf(answerCount).intValue()) + "道题，" + "答对" + Integer.valueOf(Double.valueOf(correctCount).intValue()) + "道题");
        TextView scoreTextView = (TextView)v.findViewById(R.id.score);

        scoreTextView.setText(score.toString() + "分");
        ListView landscapeListView = (ListView)v.findViewById(R.id.answerList);
        AnswerAdapter adapter = new AnswerAdapter(answerList);
        landscapeListView.setAdapter(adapter);

        return v;
    }
    private class AnswerAdapter extends ArrayAdapter<Answer> {
        public AnswerAdapter(List<Answer> answerList){
            super(getActivity(), 0, answerList);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_answer, null);
            }
            Answer answer = getItem(position);

            TextView questionTextView = (TextView)convertView.findViewById(R.id.question);
            questionTextView.setText(answer.getQuestion());
            TextView correctAnswerTextView = (TextView)convertView.findViewById(R.id.correct_answer);
            correctAnswerTextView.setText((answer.getAnswer()));
            TextView isCorrect = (TextView)convertView.findViewById(R.id.is_correct);
            isCorrect.setText(answer.getIsCorrect());
            return convertView;

        }
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



    public List<Answer> readxml() {
        List<Answer> answerList = new ArrayList<Answer>();
        try {
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
            FileInputStream fis = new FileInputStream(file);
            // 获得pull解析器对象
            XmlPullParser parser = Xml.newPullParser();
            // 指定解析的文件和编码格式
            if(fis != null){
                parser.setInput(fis, "utf-8");
            }


            int eventType = parser.getEventType(); // 获得事件类型

            String id = null;
            String question = null;
            String correct_answer = null;
            String is_correct = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName(); // 获得当前节点的名称

                switch (eventType) {
                    case XmlPullParser.START_TAG: // 当前等于开始节点 <person>
                        if ("answers".equals(tagName)) { // <persons>
                        } else if ("answer".equals(tagName)) { // <person id="1">
                            id = parser.getAttributeValue(null, "id");
                        } else if ("question".equals(tagName)) { // <name>
                            question = parser.nextText();
                        } else if ("correct_answer".equals(tagName)) { // <age>
                            correct_answer = parser.nextText();
                        } else if ("is_correct".equals(tagName)) { // <age>
                            is_correct = parser.nextText();
                        }
                        break;


                    case XmlPullParser.END_TAG: // </persons>
                        if ("answer".equals(tagName)) {
                            Answer answer = new Answer();
                            answer.setQuestion(question);
                            answer.setAnswer(correct_answer);
                            answer.setIsCorrect(is_correct);
                            answerList.add(answer);

                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next(); // 获得下一个事件类型
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return answerList;
    }

}
