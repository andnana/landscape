package io.xicp.myspace.region2;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class MainActivity extends FragmentActivity {
    private ImageButton variousRegionImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getSupportActionBar() != null){
//            this.getSupportActionBar().hide();
//        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        variousRegionImageButton = (ImageButton)findViewById(R.id.variousregions);
        variousRegionImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegionListActivity.class);

                startActivity(intent);
            }
        });
    }

}
