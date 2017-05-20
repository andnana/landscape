package io.xicp.myspace.region2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cse.der.qsw.AdManager;
import cse.der.qsw.nm.bn.BannerManager;
import cse.der.qsw.nm.bn.BannerViewListener;

/**
 * Created by Administrator on 2017/5/13 0013.
 */

public class AboutFragment extends Fragment {
    private View bannerView;
    private ImageView backImageView;
    private LinearLayout bannerLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdManager.getInstance(getActivity()).init("3cf12c7d8c82c297", "a8414f5f3deeea12", false);
        bannerView = BannerManager.getInstance(getActivity())
                .getBannerView(getActivity(), new BannerViewListener() {

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
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.about_fragment, container, false);
        backImageView = (ImageView)v.findViewById(R.id.back);
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

        return v;
    }
    class MyOnlineConfigCallBack implements cse.der.qsw.onlineconfig.OnlineConfigCallBack{
        @Override
        public void onGetOnlineConfigSuccessful(String key, String value) {
            // TODO Auto-generated method stub
            // 获取在线参数成功
            System.out.println("000000000");
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
