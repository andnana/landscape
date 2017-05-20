package io.xicp.myspace.region2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cse.der.qsw.AdManager;
import cse.der.qsw.nm.bn.BannerManager;
import cse.der.qsw.nm.bn.BannerViewListener;
import cse.der.qsw.onlineconfig.OnlineConfigCallBack;




public class MainActivity extends FragmentActivity {
    private ImageView variousRegionImageView;
    private ImageView aboutImageView;
    private ImageView testImageView;

    private TextView  exitTextView;
    private LinearLayout   bannerLayout;
    private View bannerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        AdManager.getInstance(MainActivity.this).init("3cf12c7d8c82c297", "a8414f5f3deeea12", false);
         bannerView = BannerManager.getInstance(MainActivity.this)
                .getBannerView(MainActivity.this,  new BannerViewListener(){

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
           bannerLayout = (LinearLayout) findViewById(R.id.ll_banner);

        AdManager.getInstance(MainActivity.this).asyncGetOnlineConfig("isOpen",new MyOnlineConfigCallBack());




        variousRegionImageView = (ImageView) findViewById(R.id.variousregions);
        variousRegionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegionListActivity.class);

                startActivity(intent);
            }
        });
        aboutImageView = (ImageView)findViewById(R.id.about);
        aboutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);

                startActivity(intent);
            }
        });
        testImageView = (ImageView)findViewById(R.id.test);
        testImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnswerListActivity.class);

                startActivity(intent);
            }
        });
        exitTextView = (TextView)findViewById(R.id.exit);
        exitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("确定退出系统吗？")
                        .setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                })
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        finish();
                                    }
                                }).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 展示广告条窗口的 onDestroy() 回调方法中调用
        BannerManager.getInstance(MainActivity.this).onDestroy();
    }
      class MyOnlineConfigCallBack implements cse.der.qsw.onlineconfig.OnlineConfigCallBack{
        @Override
        public void onGetOnlineConfigSuccessful(String key, String value) {
            // TODO Auto-generated method stub
            // 获取在线参数成功

            if (key.equals("isOpen")) {
                if (value.equals("1")) {

                    bannerLayout.addView(bannerView);
                    // 这里设置广告开关——开启

                } else if (value.equals("0")) {
                    // 这里设置广告开关——关闭



                }
            }else{

            }
        }

        @Override
        public void onGetOnlineConfigFailed(String key) {
            // TODO Auto-generated method stub

            // 获取在线参数失败，可能原因有：键值未设置或为空、网络异常、服务器异常
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        // 按下键盘上返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            new AlertDialog.Builder(this)
                    .setMessage("确定退出系统吗？")
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            })
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    finish();
                                }
                            }).show();

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
