package io.xicp.myspace.region2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;

import net.youmi.android.AdManager;
import net.youmi.android.normal.banner.BannerManager;
import net.youmi.android.normal.banner.BannerViewListener;

/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class LandscapeListActivity extends FragmentActivity {
    protected Fragment createFragment() {
        return new LandscapeListFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landscape_list_activity_fragment);
        AdManager.getInstance(LandscapeListActivity.this).init("3cf12c7d8c82c297", "a8414f5f3deeea12", true);
        View bannerView = BannerManager.getInstance(LandscapeListActivity.this)
                .getBannerView(LandscapeListActivity.this,  new BannerViewListener(){

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
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.landscapeListContainer);
        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.landscapeList, fragment).commit();
        }

    }
}
