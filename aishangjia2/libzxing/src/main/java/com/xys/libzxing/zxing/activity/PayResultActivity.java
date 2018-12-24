package com.xys.libzxing.zxing.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.AppContext;
import com.xys.libzxing.R;
import com.xys.libzxing.zxing.Bean.PayInfo;
import com.xys.libzxing.zxing.net.RestClient;
import com.xys.libzxing.zxing.net.callback.IError;
import com.xys.libzxing.zxing.net.callback.IFailure;
import com.xys.libzxing.zxing.net.callback.ISuccess;
import com.xys.libzxing.zxing.ui.ExtAlertDialog;
import com.xys.libzxing.zxing.utils.JSONUtil;
import com.xys.libzxing.zxing.utils.PreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PayResultActivity extends Activity {
    private Toolbar mToolbar;
    private View statusBarView;
    private TextView mTextView,mTextView2,mTextView3,mTextView4,mTextView5,mTextView6;
    private Button mButton;
    private LinearLayout ll_back;
    private String tokenId;
    private Dialog mRequestDialog;
    private  Date date;
    private String time;
    private SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        AppContext.getInstance().init(this);
        setContentView(R.layout.activity_pay_result);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTextView = findViewById(R.id.huodongdingjin);
        mTextView2 = findViewById(R.id.zhifumoney);
        mTextView3 = findViewById(R.id.payshangpin_text);
        mTextView4 = findViewById(R.id.payshijina_text);
        mTextView5 = findViewById(R.id.payfangshi_text);
        mTextView6 = findViewById(R.id.paydanhao_text);
        mButton=findViewById(R.id.btn_sure);
        mTextView.setText("支付详情");
        mRequestDialog = ExtAlertDialog.creatRequestDialog(this, "请求中...");
        mRequestDialog.setCancelable(false);
        ll_back = findViewById(R.id.ll_back);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String orderId = getIntent().getStringExtra("orderId");
        tokenId = PreferencesUtils.getString(this, "token_id");
        if (isStatusBar()) {
            initStatusBar();
            getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    initStatusBar();
                }
            });
        }
        getData(orderId);
    }

    private void getData(String orderId) {
        mRequestDialog.show();
        final String url ="http://payment.ejjzcloud.com:44000/asj-pay/payController?method=getOrderPayDetails&token_id=" + tokenId +
                "&orderId=" + orderId ;
        Log.d("付款", url);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        if (response != null) {
                            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                mRequestDialog.dismiss();
                            }
                            try {
                                PayInfo userInfo = JSONUtil.fromJson(response, PayInfo.class);
                                if (userInfo.getRet().equals("200")) {
                                    mTextView2.setText("￥"+String.valueOf(userInfo.getData().getTotalPrice()));
                                    mTextView3.setText(userInfo.getData().getShopName());
                                    if(userInfo.getData().getPayType()==1){
                                        mTextView5.setText("支付宝");
                                    }
                                    else {
                                        mTextView5.setText("微信");
                                    }
                                    mTextView6.setText(userInfo.getData().getTradeNo());
                                    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    date=new Date(Long.parseLong(userInfo.getData().getTransactionTime()));
                                    time=simpleDateFormat.format(date);
                                    mTextView4.setText(time);
                                } else {
                                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                        mRequestDialog.dismiss();
                                    }
                                    Toast.makeText(PayResultActivity.this, userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                    mRequestDialog.dismiss();
                                }
                                Toast.makeText(PayResultActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                            mRequestDialog.dismiss();
                        }
                        Toast.makeText(PayResultActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                            mRequestDialog.dismiss();
                        }
                        Toast.makeText(PayResultActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }

    protected boolean isStatusBar() {
        return true;
    }

    private void initStatusBar() {
        if (statusBarView == null) {
            int identifier = getResources().getIdentifier("statusBarBackground", "id", "android");
            statusBarView = getWindow().findViewById(identifier);
        }
        if (statusBarView != null) {
            statusBarView.setBackgroundResource(R.drawable.bg_color);
        }
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
