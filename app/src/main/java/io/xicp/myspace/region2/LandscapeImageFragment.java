package io.xicp.myspace.region2;


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


/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class LandscapeImageFragment extends Fragment {
    public static final String EXTRA_LANDSCAPE_IMAGE_ID = "io.xicp.myspace.region2.landscape_image_id";
    public static final String DESCRIBE ="describe";
    private String imageEnName = "";
    private String describe = "";
    private int pic = 0;

    private ImageView picImageView ;
    private TextView describeTextView;

    private ImageView backImageView;
    private  LinearLayout bannerLayout;
    private View bannerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageEnName = getArguments().getString(EXTRA_LANDSCAPE_IMAGE_ID);
        describe = getArguments().getString(DESCRIBE);
        pic = getArguments().getInt("pic");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_landscape_image_detail, container, false);
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

// 将广告条加入到布局中
        bannerLayout = (LinearLayout)v.findViewById(R.id.ll_banner);
        AdManager.getInstance(getActivity()).asyncGetOnlineConfig("isOpen",new MyOnlineConfigCallBack());
        picImageView = (ImageView)v.findViewById(R.id.pic);
        describeTextView  = (TextView)v.findViewById(R.id.image_describe);
        picImageView.setImageResource(pic);
//        URL picURL = null;
//        Bitmap pngBM = null;
//        try {
//             picURL = new URL("http://b364.photo.store.qq.com/psb?/V11bHTS32pSZIQ/WoNCkgiHA73ROxuwTTnq*AXmZmc*kIitdrPmMx*lDVg!/b/dGwBAAAAAAAA&bo=LAHIAAAAAAARB9U!&rf=viewer_4");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        try {
//             pngBM = BitmapFactory.decodeStream(picURL.openStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        picImageView.setImageBitmap(pngBM);
        describeTextView.setText(describe);
        backImageView = (ImageView)v.findViewById(R.id.back);
        backImageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getActivity().finish();
            }
        });
        return v ;
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

    public static LandscapeImageFragment newInstance(String imageEnName, String describe, int pic){
        Bundle args = new Bundle();
        args.putString(EXTRA_LANDSCAPE_IMAGE_ID, imageEnName);
        args.putString(DESCRIBE, describe);
        args.putInt("pic", pic);
        LandscapeImageFragment fragment = new LandscapeImageFragment();
        fragment.setArguments(args);
        return fragment;

    }

}
