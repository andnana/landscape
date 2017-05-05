package io.xicp.myspace.region2;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2017/4/29 0029.
 */

public class RegionActivity extends SingleFragmentActivity {

@Override
    protected Fragment createFragment(){
//        return new RegionFragment();
    String regionId = getIntent().getStringExtra(RegionFragment.EXTRA_REGION_ID);
    return RegionFragment.newInstance(regionId);
    }
}
