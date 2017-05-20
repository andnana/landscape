package io.xicp.myspace.region2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


/**
 * Created by Administrator on 2017/4/29 0029.
 */

public class RegionActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }

    protected Fragment createFragment(){
//        return new RegionFragment();
    String regionId = getIntent().getStringExtra(RegionFragment.EXTRA_REGION_ID);
    return RegionFragment.newInstance(regionId);
    }
}
