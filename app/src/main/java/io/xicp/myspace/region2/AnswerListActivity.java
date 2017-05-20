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
 * Created by Administrator on 2017/5/15 0015.
 */

public class AnswerListActivity extends FragmentActivity {
    protected Fragment createFragment() {
        return new AnswerListFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_list_acitivity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.answerListContainer);
        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.answerListContainer, fragment).commit();
        }

    }
}
