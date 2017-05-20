package io.xicp.myspace.region2;

import android.content.Intent;
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



        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.landscapeListContainer);
        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.landscapeListContainer, fragment).commit();
        }

    }
}
