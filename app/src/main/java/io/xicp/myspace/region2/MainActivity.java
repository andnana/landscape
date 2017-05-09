package io.xicp.myspace.region2;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import net.youmi.android.AdManager;
import net.youmi.android.normal.banner.BannerManager;
import net.youmi.android.normal.banner.BannerViewListener;

//import cn.waps.AppConnect;

public class MainActivity extends FragmentActivity {
    private ImageView variousRegionImageButton;
    private LinearLayout adlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getSupportActionBar() != null){
//            this.getSupportActionBar().hide();
//        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        AdManager.getInstance(MainActivity.this).init("3cf12c7d8c82c297", "a8414f5f3deeea12", true);
        View bannerView = BannerManager.getInstance(MainActivity.this)
                .getBannerView(MainActivity.this,  new BannerViewListener(){

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
        LinearLayout bannerLayout = (LinearLayout) findViewById(R.id.ll_banner);

// 将广告条加入到布局中
        bannerLayout.addView(bannerView);
//        AppConnect.getInstance("577aeaaf04ba1d12e8917104ab3409cb","default",this);
//        adlayout  =(LinearLayout)findViewById(R.id.AdLinearLayout);
//        AppConnect.getInstance(this).showBannerAd(this, adlayout);
        variousRegionImageButton = (ImageView) findViewById(R.id.variousregions);
        variousRegionImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegionListActivity.class);

                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 展示广告条窗口的 onDestroy() 回调方法中调用
        BannerManager.getInstance(MainActivity.this).onDestroy();
    }

}
