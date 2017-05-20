package io.xicp.myspace.region2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;

import cse.der.qsw.AdManager;
import cse.der.qsw.nm.bn.BannerManager;
import cse.der.qsw.nm.bn.BannerViewListener;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class LandscapeImageListActivity extends FragmentActivity {
    protected Fragment createFragment() {
        return new LandscapeImageListFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landscape_image_list_activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.landscapeImageListContainer);
        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.landscapeImageListContainer, fragment).commit();
        }

    }
}
