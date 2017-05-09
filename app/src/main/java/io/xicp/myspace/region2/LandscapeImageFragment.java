package io.xicp.myspace.region2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.youmi.android.AdManager;
import net.youmi.android.normal.banner.BannerManager;
import net.youmi.android.normal.banner.BannerViewListener;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class LandscapeImageFragment extends Fragment {
    public static final String EXTRA_LANDSCAPE_IMAGE_ID = "io.xicp.myspace.region2.landscape_image_id";
    public static final String DESCRIBE ="describe";
    private String imageEnName = "";
    private String describe = "";
    private int pic = 0;
    private View bannerView;
    private ImageView picImageView ;
    private TextView describeTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   AdManager.getInstance(getActivity()).init("3cf12c7d8c82c297", "a8414f5f3deeea12", true);
        bannerView = BannerManager.getInstance(getActivity())
                .getBannerView(getActivity(),  new BannerViewListener(){

                    *//**
                     * 请求广告成功
                     *//*
                    @Override
                    public void onRequestSuccess() {

                    }

                    *//**
                     * 切换广告条
                     *//*
                    @Override
                    public void onSwitchBanner() {

                    }

                    *//**
                     * 请求广告失败
                     *//*
                    @Override
                    public void onRequestFailed() {

                    }
                });*/

//        regionEnName = getActivity().getIntent().getStringExtra(EXTRA_REGION_ID);
        imageEnName = getArguments().getString(EXTRA_LANDSCAPE_IMAGE_ID);
        describe = getArguments().getString(DESCRIBE);
        pic = getArguments().getInt("pic");
        System.out.println(imageEnName);
        System.out.println("##$$$");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("xcfdsdf$");
        View v = inflater.inflate(R.layout.activity_landscape_image_detail, container, false);
        picImageView = (ImageView)v.findViewById(R.id.pic);
        describeTextView  = (TextView)v.findViewById(R.id.image_describe);
        picImageView.setImageResource(pic);
        describeTextView.setText(describe);
        System.out.println(describe+"describeTextView.setText(describe)");
        return v ;
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
