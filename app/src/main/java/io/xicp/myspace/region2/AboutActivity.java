package io.xicp.myspace.region2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/5/13 0013.
 */

public class AboutActivity extends FragmentActivity  {

        private LinearLayout adlayout;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.about_fragment);
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.about_fragment);
            if(fragment == null){
                fragment = createFragment();
                fm.beginTransaction().add(R.id.about_fragment, fragment).commit();
            }


        }
    protected Fragment createFragment(){
        return new AboutFragment();

    }




}
