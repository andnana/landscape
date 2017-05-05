package io.xicp.myspace.region2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Administrator on 2017/4/29 0029.
 */

public class RegionListActivity extends FragmentActivity {

    protected Fragment createFragment() {
        return new RegionListFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.region_list_activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.regionListContainer);
        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.regionList, fragment).commit();
        }

    }
}