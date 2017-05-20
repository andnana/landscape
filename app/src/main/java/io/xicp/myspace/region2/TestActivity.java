package io.xicp.myspace.region2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;

/**
 * Created by Administrator on 2017/5/13 0013.
 */

public class TestActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.test_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.testFragment);
        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.testFragment, fragment).commit();
        }
    }

    protected Fragment createFragment(){
        return new TestFragment();

    }
}
