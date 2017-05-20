package io.xicp.myspace.region2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class MainFragment extends Fragment {
    private ImageView variousRegionImageView;
    private ImageView aboutImageView;
    private ImageView testImageView;
    private LinearLayout adlayout;
    private TextView exitTextView;
    private static boolean isAdCanShow = false;
    private View bannerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


//        AdManager.getInstance(getActivity()).asyncGetOnlineConfig("isOpen", new OnlineConfigCallBack() {
//            @Override
//            public void onGetOnlineConfigSuccessful(String key, String value) {
//                // TODO Auto-generated method stub
//                // 获取在线参数成功
//
//                if (key.equals("isOpen")) {
//                    if (value.equals("1")) {
//
//
//                        // 这里设置广告开关——开启
//                        isAdCanShow = true;
//                    } else if (value.equals("0")) {
//                        // 这里设置广告开关——关闭
//                        isAdCanShow = false;
//
//                    }
//                }else{
//                    System.out.println("ccccccccc");
//                }
//            }
//
//            @Override
//            public void onGetOnlineConfigFailed(String key) {
//                // TODO Auto-generated method stub
//                System.out.println("ddddddddd");
//                // 获取在线参数失败，可能原因有：键值未设置或为空、网络异常、服务器异常
//            }
//        });






    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// 获取要嵌入广告条的布局

        View v = inflater.inflate(R.layout.activity_main, container, false);
        LinearLayout bannerLayout = (LinearLayout) v.findViewById(R.id.ll_banner);
        // 将广告条加入到布局中

        if(isAdCanShow){
            bannerLayout.addView(bannerView);
        }
        variousRegionImageView = (ImageView) v.findViewById(R.id.variousregions);
        variousRegionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegionListActivity.class);

                startActivity(intent);
            }
        });
        aboutImageView = (ImageView)v.findViewById(R.id.about);
        aboutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);

                startActivity(intent);
            }
        });
        testImageView = (ImageView)v.findViewById(R.id.test);
        testImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AnswerListActivity.class);

                startActivity(intent);
            }
        });
        exitTextView = (TextView)v.findViewById(R.id.exit);
        exitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setMessage("确定退出系统吗？")
                        .setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                })
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        getActivity().finish();
                                    }
                                }).show();
            }
        });
     return v;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 展示广告条窗口的 onDestroy() 回调方法中调用
        BannerManager.getInstance(getActivity()).onDestroy();
    }
}
