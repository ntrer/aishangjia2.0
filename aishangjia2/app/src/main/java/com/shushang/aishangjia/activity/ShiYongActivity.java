package com.shushang.aishangjia.activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.shushang.aishangjia.R;
import com.shushang.aishangjia.base.BaseActivity;
import com.shushang.aishangjia.ui.ExtAlertDialog;

public class ShiYongActivity extends BaseActivity {

    private WebView wb_img;
    private Toolbar mToolbar;
    //退出时的时间
    private long mExitTime;
    private Dialog mRequestDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar=findViewById(R.id.toolbar);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(ShiYongActivity.this, "加载中...");
        mRequestDialog.setCancelable(false);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wb_img=findViewById(R.id.WebView);
        WebSettings settings = wb_img.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setBuiltInZoomControls(false); //选择内置缩放机制与单独缩放控件；
        settings.setDisplayZoomControls(false); //是否显示缩放控件
        settings.setSupportZoom(false);  //是否支持缩放
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        wb_img.setInitialScale(145); //第一次显示的缩放比例，例子是120%;
        wb_img.getSettings().setJavaScriptEnabled(true);//启用js
        wb_img.getSettings().setBlockNetworkImage(false);//解决图片不显示
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wb_img.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mRequestDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                    mRequestDialog.dismiss();
                }
            }
        });

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wb_img.loadUrl("https://a3.rabbitpre.com/m2/aUe1ZjG7xV");

    }

    @Override
    public int setLayout() {
        return R.layout.activity_shiyong;
    }

    @Override
    public void init() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 退出activity的动画效果不起作用，要在java代码里写
     * 复写activity的finish方法，在overridePendingTransition中写入自己的动画效果
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK && wb_img.canGoBack()){
            wb_img.goBack();
            return true;
        }else{
            if((System.currentTimeMillis()-mExitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出页面", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
        return true;
    }


}
