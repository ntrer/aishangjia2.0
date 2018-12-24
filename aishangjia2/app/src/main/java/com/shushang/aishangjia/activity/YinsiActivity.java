package com.shushang.aishangjia.activity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shushang.aishangjia.R;
import com.shushang.aishangjia.base.BaseActivity;

public class YinsiActivity extends BaseActivity {

    private WebView wb_img;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar=findViewById(R.id.toolbar);
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
                showProgress("页面加载中");//开始加载动画
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                removeProgress();//当加载结束时移除动画
            }
        });

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wb_img.loadUrl("http://sh.ejjzcloud.com/PrivacyAgreement.html");

    }

    @Override
    public int setLayout() {
        return R.layout.activity_yinsi;
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

}
