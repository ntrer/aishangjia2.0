/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xys.libzxing.zxing.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.tts.client.SpeechSynthesizer;
import com.google.zxing.Result;
import com.xys.libzxing.AppContext;
import com.xys.libzxing.R;
import com.xys.libzxing.greendao.ActionCustomersBeanDao;
import com.xys.libzxing.greendao.ActivityBeanDao;
import com.xys.libzxing.greendao.CustomersBeanDao;
import com.xys.libzxing.greendao.DaoMaster;
import com.xys.libzxing.greendao.DaoSession;
import com.xys.libzxing.zxing.Bean.ActionCustomersBean;
import com.xys.libzxing.zxing.Bean.ActivityBean;
import com.xys.libzxing.zxing.Bean.ChaxunInfo;
import com.xys.libzxing.zxing.Bean.CustomersBean;
import com.xys.libzxing.zxing.Bean.DingjinInfo;
import com.xys.libzxing.zxing.Bean.UserData;
import com.xys.libzxing.zxing.Bean.UserInfo;
import com.xys.libzxing.zxing.Bean.ZhiFu;
import com.xys.libzxing.zxing.camera.CameraManager;
import com.xys.libzxing.zxing.decode.DecodeThread;
import com.xys.libzxing.zxing.net.RestClient;
import com.xys.libzxing.zxing.net.callback.IError;
import com.xys.libzxing.zxing.net.callback.IFailure;
import com.xys.libzxing.zxing.net.callback.ISuccess;
import com.xys.libzxing.zxing.ui.ExtAlertDialog;
import com.xys.libzxing.zxing.ui.dialog.ActionSheetDialog;
import com.xys.libzxing.zxing.utils.AudioUtil;
import com.xys.libzxing.zxing.utils.BaseUrl;
import com.xys.libzxing.zxing.utils.BeepManager;
import com.xys.libzxing.zxing.utils.CaptureActivityHandler;
import com.xys.libzxing.zxing.utils.InactivityTimer;
import com.xys.libzxing.zxing.utils.JSONUtil;
import com.xys.libzxing.zxing.utils.OkhttpUtils.CallBackUtil;
import com.xys.libzxing.zxing.utils.OkhttpUtils.OkhttpUtil;
import com.xys.libzxing.zxing.utils.PreferencesUtils;
import com.xys.libzxing.zxing.utils.TTSUtils;
import com.xys.libzxing.zxing.utils.VoiceUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

/**
 * This activity opens the camera and does the actual scanning on a background
 * thread. It draws a viewfinder to help the user place the barcode correctly,
 * shows feedback as the image processing is happening, and then overlays the
 * results when a scan is successful.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 * @author Sean Owen
 */
public final class CaptureActivity extends Activity implements SurfaceHolder.Callback {

    private static final String TAG = CaptureActivity.class.getSimpleName();
    private static DaoSession daoSession;
    private CameraManager cameraManager;
    private CaptureActivityHandler handler;
    private Handler scanHandler = new Handler();
    private InactivityTimer inactivityTimer;
    private BeepManager beepManager;
    private ProgressBar mProgressBar;
    private SurfaceView scanPreview = null;
    private RelativeLayout scanContainer;
    private RelativeLayout scanCropView;
    private RelativeLayout mRelativeLayout, mRelativeLayout2, mRelativeLayout3, mRelativeLayout4;
    private LinearLayout ll_shop, ll_user, ll_edit, ll_user2, ll_edit2;
    private ImageView scanLine;
    private Button mBtn, mBtn2;
    private EditText money, money2;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4, mTextView5,
            mTextView6, mTextView7, mTextView8, mTextView9, mTextView10, mTextView11,
            mTextView12, mTextView13, mTextView14, mTextView15, mTextView16, mTextView17, mTextView18, mTextView19;
    private Rect mCropRect = null;
    private boolean isHasSurface = false;
    private List<ActionCustomersBean> actionCustomers;
    private List<CustomersBean> customers;
    private ActionCustomersBeanDao actionCustomersBeanDao;
    private CustomersBeanDao customersBeanDao;
    private ActivityBeanDao activityBeanDao;
    private ActivityBean mActivityBean;
    private View statusBarView;
    private boolean isExit = false;
    private LinearLayout mLinearLayout;
    private TextView mQiandao, mDuihuan, mShouyin;
    private ImageView mImageView;
    private Toolbar mToolbar;
    private Dialog modifyPasswordOkDialog;
    private String payType;
    private LinearLayout ll_back;
    private Handler pahHandler;
    private Handler loadingHandler = new Handler();
    private String text, text2;
    //    private List<ActionCustomersBean> actionCustomersBeans ;
//    private List<CustomersBean> customersBeans ;
    private String type;
    private Dialog mRequestDialog;
    private String openWxPay=null;
    private String openZfbPay=null;
    public Handler getHandler() {
        return handler;
    }

    private String syyroleType = null;
    private String tokenId, activityId, merchantId, userId;
    private VoiceUtils utils;
    private TTSUtils mTTSUtils;

    public CameraManager getCameraManager() {
        return cameraManager;
    }

    private SpeechSynthesizer mSpeechSynthesizer;
    private String orderId, orderId2, totalPrice, totalPrice2, merchantname, authCode;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        AppContext.getInstance().init(this);
        pahHandler = new Handler();
        AudioUtil audioUtil = AudioUtil.getInstance(this);
        int mediaMaxVolume = audioUtil.getMediaMaxVolume();
        int mediaVolume = audioUtil.getMediaVolume();
        if (mediaVolume != mediaMaxVolume) {
            audioUtil.setMediaVolume(mediaMaxVolume);
        }
//        mRequestDialog = ExtAlertDialog.creatRequestDialog(this, getString(R.string.loadingData));
        tokenId = PreferencesUtils.getString(this, "token_id");
        syyroleType = PreferencesUtils.getString(this.getApplicationContext(), "syyroleType");
        openWxPay=PreferencesUtils.getString(this,"openWxPay");
        openZfbPay=PreferencesUtils.getString(this,"openZfbPay");
        // TODO: 2018/11/18 这个获取的位置要改变
//        activityId = PreferencesUtils.getString(this, "activityId");
        PreferencesUtils.putString(this, "signed", null);
//
//        if (type.equals("0")) {
//            setContentView(R.layout.activity_capture);
//        } else if (type.equals("1")) {
//            setContentView(R.layout.activity_capture3);
//            initChild();
//        } else if (type.equals("2")) {
//            setContentView(R.layout.activity_capture2);
//        } else if (type.equals("3")) {
//            setContentView(R.layout.activity_capture4);
//            initChild2();
//        } else if (type.equals("4")) {
//            setContentView(R.layout.activity_capture2);
//        }
//        else if (type.equals("5")) {
        setContentView(R.layout.activity_capture5);
        initChild();
        initChild2();
        initChild3();
        initChild4();
//        }
//        SoftHideKeyBoardUtil.assistActivity(this);

        if (isStatusBar()) {
            initStatusBar();
            getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    initStatusBar();
                }
            });
        }
        Intent intent = getIntent();//获取传来的intent对象
        activityId = intent.getStringExtra("activityId");
        orderId2 = intent.getStringExtra("orderId");
        totalPrice2 = intent.getStringExtra("totalPrice");
        merchantname = intent.getStringExtra("merchantName");
//        Toast.makeText(this, ""+activityId, Toast.LENGTH_SHORT).show();

        if (orderId2 != null) {
            type = "10";
            mRelativeLayout3.setVisibility(View.VISIBLE);
            mRelativeLayout2.setVisibility(View.GONE);
            mRelativeLayout.setVisibility(View.GONE);
            mTextView11.setText(merchantname);
            mTextView12.setText(orderId2);
            mTextView13.setText(totalPrice2);
            mTextView10.setText("付款");
        } else {
            if (intent.getStringExtra("type") == null) {
                type = "0";
            } else {
                type = intent.getStringExtra("type");//获取键值对的键名
                if (type.equals("0")) {
                    mRelativeLayout.setVisibility(View.GONE);
                    mRelativeLayout2.setVisibility(View.GONE);
                    mTextView10.setText("签到");
                } else if (type.equals("1")) {
                    mRelativeLayout.setVisibility(View.VISIBLE);
                    mRelativeLayout2.setVisibility(View.GONE);
                    mTextView10.setText("收银");
                } else if (type.equals("2")) {
                    mRelativeLayout.setVisibility(View.GONE);
                    mRelativeLayout2.setVisibility(View.GONE);
                    mTextView10.setText("兑换");
                } else if (type.equals("3")) {
                    mRelativeLayout.setVisibility(View.GONE);
                    mRelativeLayout2.setVisibility(View.VISIBLE);
                    mTextView10.setText("活动订金");
                } else if (type.equals("4")) {
                    reStart();
                    mRelativeLayout.setVisibility(View.GONE);
                    mRelativeLayout2.setVisibility(View.GONE);
                    mTextView10.setText("活动拓客");
                } else if (type.equals("5")) {
                    mRelativeLayout.setVisibility(View.GONE);
                    mRelativeLayout2.setVisibility(View.GONE);
                    mTextView10.setText("签到");
                } else if (type.equals("11")) {
                    mRelativeLayout.setVisibility(View.GONE);
                    mRelativeLayout2.setVisibility(View.GONE);
                    mRelativeLayout3.setVisibility(View.GONE);
                    mTextView10.setText("对账");
                }
            }
        }
        modifyPasswordOkDialog = ExtAlertDialog.createModifyPasswordOkDialog2(CaptureActivity.this);
        scanPreview = findViewById(R.id.capture_preview);
        scanContainer = findViewById(R.id.capture_container);
        scanCropView = findViewById(R.id.capture_crop_view);
        scanLine = (ImageView) findViewById(R.id.capture_scan_line);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(this, "下单中...");
        mRequestDialog.setCancelable(false);

        mProgressBar = findViewById(R.id.loading);
        inactivityTimer = new InactivityTimer(this);
        beepManager = new BeepManager(this);

        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation
                .RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT,
                0.9f);
        animation.setDuration(4500);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.RESTART);
        scanLine.startAnimation(animation);
        setUpDataBase();
        reStart();
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

    protected boolean isStatusBar() {
        return true;
    }

    private void initChild() {
        ll_back = findViewById(R.id.ll_back);
        mImageView = findViewById(R.id.back);
        mTextView10 = findViewById(R.id.huodongdingjin);
        mBtn = findViewById(R.id.btn_submit2);
        mRelativeLayout = findViewById(R.id.rl_info2);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        ll_shop = findViewById(R.id.shop_info);
        ll_user = findViewById(R.id.user_info2);
        ll_edit = findViewById(R.id.edit2);
        mTextView1 = findViewById(R.id.shop_id);
        mTextView2 = findViewById(R.id.shop_name);
        mTextView3 = findViewById(R.id.shop_code);
        mTextView4 = findViewById(R.id.user_id2);
        mTextView5 = findViewById(R.id.user_name2);
        mTextView6 = findViewById(R.id.user_code2);
        money = findViewById(R.id.pro_money2);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ActionSheetDialog(CaptureActivity.this)
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("现金", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        payType = "1";
                                        mRequestDialog.show();
                                        loadingHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                pay();
                                            }
                                        }, 1000);
                                    }
                                })
                        .addSheetItem("刷卡", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        payType = "2";
                                        mRequestDialog.show();
                                        loadingHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                pay();
                                            }
                                        }, 1000);
                                    }
                                })
                        .addSheetItem("支付宝", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        payType = "3";
                                        mRequestDialog.show();
                                        loadingHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (type.equals("3")) {
                                                    pay2(payType);
                                                } else {
                                                    pay();
                                                }
                                            }
                                        }, 1000);
                                    }
                                })
                        .addSheetItem("微信", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        payType = "4";
                                        mRequestDialog.show();
                                        loadingHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (type.equals("3")) {
                                                    pay2(payType);
                                                } else {
                                                    pay();
                                                }
                                            }
                                        }, 1000);
                                    }
                                })
                        .addSheetItem("其他", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        payType = "5";
                                        mRequestDialog.show();
                                        loadingHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                pay();
                                            }
                                        }, 1000);
                                    }
                                })
                        .show();

            }
        });


    }


//    private void pay(String payType){
//        reStart();
//        if (mTextView1.getText().equals("") || mTextView4.getText().equals("")) {
//            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
//                mRequestDialog.dismiss();
//            }
//            Toast.makeText(CaptureActivity.this, "请先扫码", Toast.LENGTH_SHORT).show();
//        } else {
//            try {
////                mProgressBar.setVisibility(View.VISIBLE);
//                closeKeybord(money, getApplicationContext());
//                String url = BaseUrl.BASE_URL + "phoneApi/activityController.do?method=saveOrder";
//                HashMap<String, String> paramsMap = new HashMap<>();
//                paramsMap.put("token_id", tokenId);
//                paramsMap.put("activity_id", activityId);
//                paramsMap.put("merchant_id", merchantId);
//                paramsMap.put("user_id", userId);
//                paramsMap.put("money", money.getText().toString());
//                paramsMap.put("payType",payType);
////                    String url=BaseUrl.BASE_URL+"phoneApi/activityController.do?method=saveOrder&token_id="+tokenId+"&user_id="+userId+"&merchant_id="+merchantId+"&activity_id="+activityId+"&money="+money.getText();
//                Log.d("ScanUrl", url);
//
//                OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
//                    @Override
//                    public void onFailure(Call call, Exception e) {
//                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
//                            mRequestDialog.dismiss();
//                        }
//                        Toast.makeText(CaptureActivity.this, "下单失败", Toast.LENGTH_SHORT).show();
//                        scanHandler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                reStart();
//                            }
//                        }, 1000);
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        if (response != null) {
//                            Log.d("ScanUrl", response);
//                            try {
//                                UserInfo userInfo = JSONUtil.fromJson(response, UserInfo.class);
//                                if (userInfo.getRet().equals("200")) {
//                                    speak(0,"下单成功");
//                                    Toast.makeText(CaptureActivity.this, "下单成功", Toast.LENGTH_SHORT).show();
//                                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
//                                        mRequestDialog.dismiss();
//                                    }
//                                    mTextView1.setText("");
//                                    mTextView2.setText("");
//                                    mTextView3.setText("");
//                                    mTextView4.setText("");
//                                    mTextView5.setText("");
//                                    mTextView6.setText("");
////                                ll_shop.setVisibility(View.GONE);
////                                ll_user.setVisibility(View.GONE);
////                                ll_edit.setVisibility(View.GONE);
//                                    money.setText("");
//                                    scanHandler.postDelayed(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            reStart();
//                                        }
//                                    }, 1000);
//                                } else {
//                                    scanHandler.postDelayed(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            reStart();
//                                        }
//                                    }, 1000);
//                                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
//                                        mRequestDialog.dismiss();
//                                    }
//                                    speak(0,"下单失败");
//                                    Toast.makeText(CaptureActivity.this, "下单失败" + userInfo.getMsg(), Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (Exception e) {
//                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
//                                    mRequestDialog.dismiss();
//                                }
//                                scanHandler.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        reStart();
//                                    }
//                                }, 1000);
//                            }
//                        }
//
//                    }
//                });
//
//
////                    RestClient.builder()
////                            .url(url)
////                            .success(new ISuccess() {
////                                @Override
////                                public void onSuccess(String response) {
////                                    Log.d("ScanUrl",response);
////                                    UserInfo userInfo = JSONUtil.fromJson(response, UserInfo.class);
////                                    if(userInfo.getRet().equals("200")){
////                                        Toast.makeText(CaptureActivity.this, "下单成功", Toast.LENGTH_SHORT).show();
////                                        mProgressBar.setVisibility(View.GONE);
////                                        ll_shop.setVisibility(View.GONE);
////                                        ll_user.setVisibility(View.GONE);
////                                        ll_edit.setVisibility(View.GONE);
////                                        money.setText("");
////                                        reStart();
////                                    }
////                                    else {
////                                        mProgressBar.setVisibility(View.GONE);
////                                        Toast.makeText(CaptureActivity.this, "下单失败"+userInfo.getMsg(), Toast.LENGTH_SHORT).show();
////                                    }
////                                }
////                            }).failure(new IFailure() {
////                        @Override
////                        public void onFailure() {
////                            mProgressBar.setVisibility(View.GONE);
////                            Toast.makeText(CaptureActivity.this, "下单失败", Toast.LENGTH_SHORT).show();
////                        }
////                    }).error(new IError() {
////                        @Override
////                        public void onError(int code, String msg) {
////                            mProgressBar.setVisibility(View.GONE);
////                            Toast.makeText(CaptureActivity.this, "下单失败"+code+msg, Toast.LENGTH_SHORT).show();
////                        }
////                    })
////                            .build()
////                            .post();
//            } catch (Exception e) {
//                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
//                    mRequestDialog.dismiss();
//                }
//                Toast.makeText(CaptureActivity.this, "出错" + e.toString(), Toast.LENGTH_SHORT).show();
//                scanHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        reStart();
//                    }
//                }, 1000);
//            }
//        }
//    }


    private void pay() {
        reStart();
        if (mTextView1.getText().equals("") || mTextView4.getText().equals("")) {
            if (mRequestDialog != null && mRequestDialog.isShowing()) {
                mRequestDialog.dismiss();
            }
            Toast.makeText(CaptureActivity.this, "请先扫码", Toast.LENGTH_SHORT).show();
        } else {
            try {
//                mProgressBar.setVisibility(View.VISIBLE);
                closeKeybord(money, getApplicationContext());
                String url = BaseUrl.BASE_URL + "phoneApi/activityController.do?method=saveOrder";
                HashMap<String, String> paramsMap = new HashMap<>();
                paramsMap.put("token_id", tokenId);
                paramsMap.put("activity_id", activityId);
                paramsMap.put("merchant_id", merchantId);
                paramsMap.put("user_id", userId);
                paramsMap.put("orderNumber", text);
                paramsMap.put("money", money.getText().toString());
                if(payType.equals("3")){
                    if(openZfbPay!=null) {
                        if (openZfbPay.equals("1")) {
                            payType="21";
                            paramsMap.put("payType", payType);
                        }
                    }
                }
                else if(payType.equals("4")){
                    if(openWxPay!=null){
                        if (openWxPay.equals("1")) {
                            payType="22";
                            paramsMap.put("payType", payType);
                        }
                    }
                }
                else {
                    paramsMap.put("payType", payType);
                }
                paramsMap.put("payType", payType);
                Log.d("ScanUrl", url);
                OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {
                        if (mRequestDialog != null && mRequestDialog.isShowing()) {
                            mRequestDialog.dismiss();
                        }
                        Toast.makeText(CaptureActivity.this, "下单失败", Toast.LENGTH_SHORT).show();
                        scanHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reStart();
                            }
                        }, 1000);
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            Log.d("ScanUrl", response);
                            try {
                                final DingjinInfo userInfo = JSONUtil.fromJson(response, DingjinInfo.class);
                                if (userInfo.getRet().equals("200")) {
                                    if(payType.equals("21")){
                                        if(openZfbPay!=null){
                                            if(openZfbPay.equals("1")){
                                                speak(0, "下单成功,请扫描付款码进行付款");
                                            }
                                        }
                                    }
                                    else if(payType.equals("22")){
                                        if(openWxPay!=null){
                                            if(openWxPay.equals("1")){
                                                speak(0, "下单成功,请扫描付款码进行付款");
                                            }
                                        }
                                    }
                                    else {
                                        speak(0, "下单成功");
                                    }
                                    Toast.makeText(CaptureActivity.this, "下单成功", Toast.LENGTH_SHORT).show();
                                    if (mRequestDialog != null && mRequestDialog.isShowing()) {
                                        mRequestDialog.dismiss();
                                    }
                                    mTextView1.setText("");
                                    mTextView2.setText("");
                                    mTextView3.setText("");
                                    mTextView4.setText("");
                                    mTextView5.setText("");
                                    mTextView6.setText("");
                                    money.setText("");
                                    scanHandler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            reStart();
                                            if(payType.equals("21")){
                                                if(openZfbPay!=null){
                                                    if(openZfbPay.equals("1")){
                                                        if (syyroleType != null) {
                                                            if (!syyroleType.equals("")) {
                                                                if (syyroleType.equals("1")) {
                                                                    type = "10";
                                                                    mRelativeLayout3.setVisibility(View.VISIBLE);
                                                                    mRelativeLayout2.setVisibility(View.GONE);
                                                                    mRelativeLayout.setVisibility(View.GONE);
                                                                    mTextView11.setText(userInfo.getData().getOrderMerchantName());
                                                                    mTextView12.setText(userInfo.getData().getOrderId());
                                                                    mTextView13.setText(String.valueOf(userInfo.getData().getTotalPrice()));
                                                                    mTextView10.setText("付款");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            else if(payType.equals("22")){
                                                if(openWxPay!=null){
                                                    if(openWxPay.equals("1")){
                                                        if (syyroleType != null) {
                                                            if (!syyroleType.equals("")) {
                                                                if (syyroleType.equals("1")) {
                                                                    type = "10";
                                                                    mRelativeLayout3.setVisibility(View.VISIBLE);
                                                                    mRelativeLayout2.setVisibility(View.GONE);
                                                                    mRelativeLayout.setVisibility(View.GONE);
                                                                    mTextView11.setText(userInfo.getData().getOrderMerchantName());
                                                                    mTextView12.setText(userInfo.getData().getOrderId());
                                                                    mTextView13.setText(String.valueOf(userInfo.getData().getTotalPrice()));
                                                                    mTextView10.setText("付款");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                        }
                                    }, 1000);
                                } else {
                                    scanHandler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            reStart();
                                        }
                                    }, 1000);
                                    if (mRequestDialog != null && mRequestDialog.isShowing()) {
                                        mRequestDialog.dismiss();
                                    }
                                    speak(0, "下单失败");
                                    Toast.makeText(CaptureActivity.this, "下单失败" + userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                if (mRequestDialog != null && mRequestDialog.isShowing()) {
                                    mRequestDialog.dismiss();
                                }
                                scanHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        reStart();
                                    }
                                }, 1000);
                            }
                        }

                    }
                });
            } catch (Exception e) {
                if (mRequestDialog != null && mRequestDialog.isShowing()) {
                    mRequestDialog.dismiss();
                }
                Toast.makeText(CaptureActivity.this, "出错" + e.toString(), Toast.LENGTH_SHORT).show();
                scanHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reStart();
                    }
                }, 1000);
            }
        }
    }

    private void initChild3() {
        mRelativeLayout3 = findViewById(R.id.dd_info);
        mTextView11 = findViewById(R.id.shanghuname);
        mTextView12 = findViewById(R.id.dingdan_code);
        mTextView13 = findViewById(R.id.dingdan_money);
    }

    private void initChild4() {
        mRelativeLayout4 = findViewById(R.id.rl_info3);
        mTextView14 = findViewById(R.id.shanghuming2);
        mTextView15 = findViewById(R.id.dingjinid);
        mTextView16 = findViewById(R.id.dingjinjine);
        mTextView17 = findViewById(R.id.dingjinbianhao);
        mTextView18 = findViewById(R.id.zhifufangshi);
        mTextView19 = findViewById(R.id.shoukuanren);
    }

    private void initChild2() {
        mBtn2 = findViewById(R.id.btn_submit);
        mRelativeLayout2 = findViewById(R.id.rl_info);
        ll_user2 = findViewById(R.id.user_info);
        ll_edit2 = findViewById(R.id.edit);
        mTextView7 = findViewById(R.id.user_id);
        mTextView8 = findViewById(R.id.user_name);
        mTextView9 = findViewById(R.id.user_code);
        money2 = findViewById(R.id.pro_money);
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new ActionSheetDialog(CaptureActivity.this)
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("现金", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        payType = "1";
                                        mRequestDialog.show();
                                        loadingHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                pay2(payType);
                                            }
                                        }, 1000);
                                    }
                                })
                        .addSheetItem("刷卡", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        payType = "2";
                                        mRequestDialog.show();
                                        loadingHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                pay2(payType);
                                            }
                                        }, 1000);
                                    }
                                })
                        .addSheetItem("支付宝", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        payType = "3";
                                        mRequestDialog.show();
                                        loadingHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                pay2(payType);
                                            }
                                        }, 1000);
                                    }
                                })
                        .addSheetItem("微信", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        payType = "4";
                                        mRequestDialog.show();
                                        loadingHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                pay2(payType);
                                            }
                                        }, 1000);
                                    }
                                })
                        .addSheetItem("其他", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        payType = "5";
                                        mRequestDialog.show();
                                        loadingHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                pay2(payType);
                                            }
                                        }, 1000);
                                    }
                                })
                        .show();

            }
        });


    }


    private void pay2(final String payType) {
        pahHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                reStart();
                if (money2.getText().equals("")) {
                    if (mRequestDialog != null && mRequestDialog.isShowing()) {
                        mRequestDialog.dismiss();
                    }
                    Toast.makeText(CaptureActivity.this, "请先扫码", Toast.LENGTH_SHORT).show();
                } else {
                    try {
//                        mProgressBar.setVisibility(View.VISIBLE);
                        closeKeybord(money, getApplicationContext());
                        String url = BaseUrl.BASE_URL + "orderController.do?method=saveOrderByAc";
                        HashMap<String, String> paramsMap = new HashMap<>();
                        paramsMap.put("loginOS", "2");
                        paramsMap.put("token_id", tokenId);
//                        paramsMap.put("orderNumber", text);
                        paramsMap.put("activity_id", activityId);
                        paramsMap.put("user_id", userId);
                        paramsMap.put("money", money2.getText().toString());
                        paramsMap.put("payType", payType);
                        Log.d("token_id", "orderNumber" + "-----" + userId);
//                    String url=BaseUrl.BASE_URL+"phoneApi/activityController.do?method=saveOrder&token_id="+tokenId+"&user_id="+userId+"&merchant_id="+merchantId+"&activity_id="+activityId+"&money="+money.getText();
                        Log.d("ScanUrl2", url);

                        OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
                            @Override
                            public void onFailure(Call call, Exception e) {
                                if (mRequestDialog != null && mRequestDialog.isShowing()) {
                                    mRequestDialog.dismiss();
                                }
                                Toast.makeText(CaptureActivity.this, "下单失败", Toast.LENGTH_SHORT).show();
                                scanHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        reStart();
                                    }
                                }, 1000);
                            }

                            @Override
                            public void onResponse(String response) {
                                if (response != null) {
                                    Log.d("ScanUrl", response);
                                    try {
                                        UserInfo userInfo = JSONUtil.fromJson(response, UserInfo.class);
                                        if (userInfo.getRet().equals("200")) {
                                            speak(0, "下单成功");
                                            Toast.makeText(CaptureActivity.this, "下单成功", Toast.LENGTH_SHORT).show();
                                            if (mRequestDialog != null && mRequestDialog.isShowing()) {
                                                mRequestDialog.dismiss();
                                            }
                                            mTextView1.setText("");
                                            mTextView2.setText("");
                                            mTextView3.setText("");
                                            mTextView4.setText("");
                                            mTextView5.setText("");
                                            mTextView6.setText("");
                                            money.setText("");
                                            scanHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    finish();
                                                }
                                            }, 2000);
                                        } else {
                                            scanHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    reStart();
                                                }
                                            }, 1000);
                                            if (mRequestDialog != null && mRequestDialog.isShowing()) {
                                                mRequestDialog.dismiss();
                                            }
                                            speak(0, "下单失败");
                                            Toast.makeText(CaptureActivity.this, "下单失败" + userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        if (mRequestDialog != null && mRequestDialog.isShowing()) {
                                            mRequestDialog.dismiss();
                                        }
                                        scanHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                reStart();
                                            }
                                        }, 1000);
                                    }
                                }

                            }
                        });
                    } catch (Exception e) {
                        if (mRequestDialog != null && mRequestDialog.isShowing()) {
                            mRequestDialog.dismiss();
                        }
                        Toast.makeText(CaptureActivity.this, "出错" + e.toString(), Toast.LENGTH_SHORT).show();
                        scanHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reStart();
                            }
                        }, 1000);
                    }
                }
            }
        }, 700);

    }


    /**
     * 关闭软键盘
     */
    public static void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // CameraManager must be initialized here, not in onCreate(). This is
        // necessary because we don't
        // want to open the camera driver and measure the screen size if we're
        // going to show the help on
        // first launch. That led to bugs where the scanning rectangle was the
        // wrong size and partially
        // off screen.
        cameraManager = new CameraManager(getApplication());

        handler = null;

        if (isHasSurface) {
            // The activity was paused but not stopped, so the surface still
            // exists. Therefore
            // surfaceCreated() won't be called, so init the camera here.
            initCamera(scanPreview.getHolder());
        } else {
            // Install the callback and wait for surfaceCreated() to init the
            // camera.
            scanPreview.getHolder().addCallback(this);
        }

        inactivityTimer.onResume();
    }

    @Override
    protected void onPause() {
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        inactivityTimer.onPause();
        beepManager.close();
        cameraManager.closeDriver();
        if (!isHasSurface) {
            scanPreview.getHolder().removeCallback(this);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
//        mTTSUtils.release();
//        mSpeechSynthesizer.release();
        if (mSpeechSynthesizer != null) {
            mSpeechSynthesizer.release();
        }
        loadingHandler.removeCallbacksAndMessages(null);
        pahHandler.removeCallbacksAndMessages(null);
        scanHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (holder == null) {
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!isHasSurface) {
            isHasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isHasSurface = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * A valid barcode has been found, so give an indication of success and show
     * the results.
     *
     * @param rawResult The contents of the barcode.
     * @param bundle    The extras
     */
    public void handleDecode(Result rawResult, Bundle bundle) {
        try {
            inactivityTimer.onActivity();
            beepManager.playBeepSoundAndVibrate();
            if (handler != null) {
                handler.quitSynchronously();
                handler = null;
            }
            inactivityTimer.onPause();
            beepManager.close();
            cameraManager.closeDriver();
            if (!isHasSurface) {
                scanPreview.getHolder().removeCallback(this);
            }
            super.onPause();
            mProgressBar.setVisibility(View.VISIBLE);
            if (rawResult.getText().startsWith("1")) {
                text = rawResult.getText();
            } else {
                text2 = rawResult.getText();
            }

            if (type.equals("1")) {
                final String url = BaseUrl.BASE_URL + "phoneApi/activityController.do?method=silver&token_id=" + tokenId + "&code_num=" + rawResult.getText() + "&activity_id=" + activityId;
                Log.d("收银", url);
                RestClient.builder()
                        .url(url)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                if (response != null) {
                                    Log.d("收银", response);
                                    try {
                                        UserInfo userInfo = JSONUtil.fromJson(response, UserInfo.class);
                                        if (userInfo.getRet().equals("200")) {
                                            if (userInfo.getData() != null) {
                                                if (userInfo.getData().getType().equals("2")) {
                                                    merchantId = String.valueOf(userInfo.getData().getMerchant_id());
                                                    mTextView2.setText(userInfo.getData().getMerchant_name());
                                                    mTextView1.setText(text);
                                                    mTextView3.setText(userInfo.getData().getMerchant_code());
                                                    mProgressBar.setVisibility(View.GONE);
//                                        ll_shop.setVisibility(View.VISIBLE);
                                                    scanHandler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            reStart();
                                                        }
                                                    }, 1000);
                                                } else if (userInfo.getData().getType().equals("1")) {
                                                    userId = String.valueOf(userInfo.getData().getUser_id());
                                                    mTextView6.setText(String.valueOf(userInfo.getData().getAddress().toString()));
                                                    mTextView4.setText(String.valueOf(userInfo.getData().getUser_name()));
                                                    mTextView5.setText(String.valueOf(userInfo.getData().getUser_phone()));
                                                    mProgressBar.setVisibility(View.GONE);
//                                        mRelativeLayout.setVisibility(View.VISIBLE);
//                                        ll_user.setVisibility(View.VISIBLE);
//                                        ll_edit.setVisibility(View.VISIBLE);
                                                    scanHandler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            reStart();
                                                        }
                                                    }, 1000);

                                                }
                                            }
                                        } else {
                                            mProgressBar.setVisibility(View.GONE);
                                            speak(0, userInfo.getMsg());
                                            Toast.makeText(CaptureActivity.this, "" + userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                                            scanHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    reStart();
                                                }
                                            }, 1000);
                                        }
                                    } catch (Exception e) {
                                        mProgressBar.setVisibility(View.GONE);
                                        speak(0, "收银失败");
                                    }
                                }
                            }
                        })
                        .build()
                        .get();
            } else if (type.equals("3")) {
                final String url = BaseUrl.BASE_URL + "phoneApi/activityController.do?method=silver&token_id=" + tokenId + "&code_num=" + rawResult.getText() + "&activity_id=" + activityId;
                Log.d("收银", url);
                RestClient.builder()
                        .url(url)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                if (response != null) {
                                    Log.d("收银", response);
                                    try {
                                        UserInfo userInfo = JSONUtil.fromJson(response, UserInfo.class);
                                        if (userInfo.getRet().equals("200")) {
                                            mProgressBar.setVisibility(View.GONE);
                                            if (userInfo.getData() != null) {
                                                if (userInfo.getData().getType().equals("2")) {
                                                    merchantId = String.valueOf(userInfo.getData().getMerchant_id());
                                                    if (userInfo.getData().getMerchant_address() == null) {
                                                        mTextView2.setText("");
                                                    } else {
                                                        mTextView2.setText(String.valueOf(userInfo.getData().getMerchant_address().toString()));
                                                    }
                                                    mTextView1.setText(userInfo.getData().getMerchant_name());
                                                    mTextView3.setText(userInfo.getData().getMerchant_code());
                                                    mProgressBar.setVisibility(View.GONE);
//                                        ll_shop.setVisibility(View.VISIBLE);
                                                    scanHandler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            reStart();
                                                        }
                                                    }, 1000);
                                                } else if (userInfo.getData().getType().equals("1")) {
                                                    userId = String.valueOf(userInfo.getData().getUser_id());
                                                    mTextView9.setText(String.valueOf(userInfo.getData().getAddress().toString()));
                                                    mTextView7.setText(String.valueOf(userInfo.getData().getUser_name()));
                                                    mTextView8.setText(String.valueOf(userInfo.getData().getUser_phone()));
                                                    mProgressBar.setVisibility(View.GONE);
//                                        mRelativeLayout.setVisibility(View.VISIBLE);
//                                        ll_user.setVisibility(View.VISIBLE);
//                                        ll_edit.setVisibility(View.VISIBLE);
                                                    scanHandler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            reStart();
                                                        }
                                                    }, 1000);

                                                } else if (userInfo.getData().getType().equals("0")) {
                                                    userId = String.valueOf(userInfo.getData().getUser_id());
                                                    mTextView9.setText(String.valueOf(userInfo.getData().getAddress().toString()));
                                                    mTextView7.setText(String.valueOf(userInfo.getData().getUser_name()));
                                                    mTextView8.setText(String.valueOf(userInfo.getData().getUser_phone()));
                                                    mProgressBar.setVisibility(View.GONE);
//                                        mRelativeLayout.setVisibility(View.VISIBLE);
//                                        ll_user.setVisibility(View.VISIBLE);
//                                        ll_edit.setVisibility(View.VISIBLE);
                                                    scanHandler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            reStart();
                                                        }
                                                    }, 1000);
                                                }
                                            }
                                        } else {
                                            mProgressBar.setVisibility(View.GONE);
                                            Toast.makeText(CaptureActivity.this, "" + userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                                            scanHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    reStart();
                                                }
                                            }, 1000);
                                        }
                                    } catch (Exception e) {

                                    }
                                }
                            }
                        })
                        .build()
                        .get();
//                final String url = BaseUrl.BASE_URL + "phoneApi/activityController.do?method=silver&token_id=" + tokenId + "&code_num=" + rawResult.getText() + "&activity_id=" + activityId;
//                Log.d("收银", url);
//                RestClient.builder()
//                        .url(url)
//                        .success(new ISuccess() {
//                            @Override
//                            public void onSuccess(String response) {
//                                if (response != null) {
//                                    Log.d("收银", response);
//                                    try {
//                                        UserInfo userInfo = JSONUtil.fromJson(response, UserInfo.class);
//                                        if (userInfo.getRet().equals("200")) {
//                                            if (userInfo.getData() != null) {
//                                                if (userInfo.getData().getType().equals("2")) {
//                                                    merchantId = String.valueOf(userInfo.getData().getMerchant_id());
//                                                    mTextView2.setText(userInfo.getData().getMerchant_name());
//                                                    mTextView1.setText(text);
//                                                    mTextView3.setText(userInfo.getData().getMerchant_code());
//                                                    mProgressBar.setVisibility(View.GONE);
////                                        ll_shop.setVisibility(View.VISIBLE);
//                                                    scanHandler.postDelayed(new Runnable() {
//                                                        @Override
//                                                        public void run() {
//                                                            reStart();
//                                                        }
//                                                    }, 1000);
//                                                } else if (userInfo.getData().getType().equals("1")) {
//                                                    userId = String.valueOf(userInfo.getData().getUser_id());
//                                                    mTextView6.setText(String.valueOf(userInfo.getData().getAddress().toString()));
//                                                    mTextView4.setText(String.valueOf(userInfo.getData().getUser_name()));
//                                                    mTextView5.setText(String.valueOf(userInfo.getData().getUser_phone()));
//                                                    mProgressBar.setVisibility(View.GONE);
////                                        mRelativeLayout.setVisibility(View.VISIBLE);
////                                        ll_user.setVisibility(View.VISIBLE);
////                                        ll_edit.setVisibility(View.VISIBLE);
//                                                    scanHandler.postDelayed(new Runnable() {
//                                                        @Override
//                                                        public void run() {
//                                                            reStart();
//                                                        }
//                                                    }, 1000);
//
//                                                }
//                                            }
//                                        } else {
//                                            mProgressBar.setVisibility(View.GONE);
//                                            speak(0, userInfo.getMsg());
//                                            Toast.makeText(CaptureActivity.this, "" + userInfo.getMsg(), Toast.LENGTH_SHORT).show();
//                                            scanHandler.postDelayed(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                                    reStart();
//                                                }
//                                            }, 1000);
//                                        }
//                                    } catch (Exception e) {
//                                        mProgressBar.setVisibility(View.GONE);
//                                        speak(0, "收银失败");
//                                    }
//                                }
//                            }
//                        })
//                        .build()
//                        .get();
            } else if (type.equals("0")) {
                final String url = BaseUrl.BASE_URL + "phoneApi/activityController.do?method=signin&token_id=" + tokenId + "&card_num=" + rawResult.getText() + "&activity_id=" + activityId;
                Log.d("签到链接", "" + url);
                RestClient.builder()
                        .url(url)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {

                                if (response != null) {
                                    Log.d("签到", response);
                                    try {
                                        UserInfo userInfo = JSONUtil.fromJson(response, UserInfo.class);
                                        if (userInfo.getRet().equals("200")) {
                                            if (userInfo.getData() != null) {
                                                mProgressBar.setVisibility(View.GONE);
                                                speak(0, userInfo.getData().getUser_name() + "签到成功");
//                                            mTTSUtils.speak(userInfo.getData().getUser_name()+"签到成功");
//                                            mSpeechSynthesizer.speak(userInfo.getData().getUser_name()+"签到成功");
                                                Toast.makeText(CaptureActivity.this, userInfo.getData().getUser_name() + "--" + userInfo.getData().getUser_phone() + ":" + "签到成功", Toast.LENGTH_SHORT).show();
                                                scanHandler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        reStart();
                                                    }
                                                }, 1500);
                                            } else {
                                                mProgressBar.setVisibility(View.GONE);
//                                            mTTSUtils.speak("签到失败");
//                                            mSpeechSynthesizer.speak("签到失败");
                                                speak(0, "签到失败");
                                                Toast.makeText(CaptureActivity.this, "签到失败", Toast.LENGTH_SHORT).show();
                                            }
                                            scanHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    reStart();
                                                }
                                            }, 1000);
                                        } else {
                                            mProgressBar.setVisibility(View.GONE);
//                                        mTTSUtils.speak(userInfo.getMsg());
//                                        mSpeechSynthesizer.speak(userInfo.getMsg());
                                            speak(0, userInfo.getMsg());
                                            Toast.makeText(CaptureActivity.this, userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                                            scanHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    reStart();
                                                }
                                            }, 1500);
                                        }
                                    } catch (Exception e) {
                                        scanHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                reStart();
                                            }
                                        }, 1500);
                                    }
                                }
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure() {
                                mProgressBar.setVisibility(View.GONE);
//                            mTTSUtils.speak("签到失败");
//                            mSpeechSynthesizer.speak("签到失败");
                                speak(0, "签到失败");
                                Toast.makeText(CaptureActivity.this, "签到失败", Toast.LENGTH_SHORT).show();
                                scanHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        reStart();
                                    }
                                }, 1500);
                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int code, String msg) {
                                mProgressBar.setVisibility(View.GONE);
//                            mTTSUtils.speak("签到失败");
//                            mSpeechSynthesizer.speak("签到失败");
                                speak(0, "签到失败");
                                Toast.makeText(CaptureActivity.this, "签到失败" + msg, Toast.LENGTH_SHORT).show();
                                scanHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        reStart();
                                    }
                                }, 1000);

                            }
                        })
                        .build()
                        .get();

            } else if (type.equals("2")) {
                final String url = BaseUrl.BASE_URL + "phoneApi/activityController.do?method=getGift&coupon_code=" + rawResult.getText() + "&token_id=" + tokenId + "&activity_id=" + activityId;
                Log.d("领取", url);
                RestClient.builder()
                        .url(url)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                if (response != null) {
                                    Log.d("领取", response);
                                    try {
                                        UserInfo userInfo = JSONUtil.fromJson(response, UserInfo.class);
                                        if (userInfo.getRet().equals("200")) {
                                            mProgressBar.setVisibility(View.GONE);
                                            speak(0, "领取成功");
                                            Toast.makeText(CaptureActivity.this, "领取成功", Toast.LENGTH_SHORT).show();
                                        } else {
                                            mProgressBar.setVisibility(View.GONE);
                                            speak(0, userInfo.getMsg());
                                            Toast.makeText(CaptureActivity.this, userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                                        }
                                        scanHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                reStart();
                                            }
                                        }, 1000);
                                    } catch (Exception e) {
                                        mProgressBar.setVisibility(View.GONE);
                                        Toast.makeText(CaptureActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                                        speak(0, "领取失败");
                                        scanHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                reStart();
                                            }
                                        }, 1500);
                                    }
                                }

                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure() {
                                mProgressBar.setVisibility(View.GONE);
                                speak(0, "领取失败");
                                Toast.makeText(CaptureActivity.this, "领取失败", Toast.LENGTH_SHORT).show();
                                scanHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        reStart();
                                    }
                                }, 1500);

                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int code, String msg) {
                                mProgressBar.setVisibility(View.GONE);
                                speak(0, "领取失败" + msg);
                                Toast.makeText(CaptureActivity.this, "领取失败" + msg + code, Toast.LENGTH_SHORT).show();
                                scanHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        reStart();
                                    }
                                }, 1000);

                            }
                        })
                        .build()
                        .get();
            } else if (type.equals("4")) {
                if (rawResult.getText().equals("") || rawResult.getText() == null) {
                    speak(0, "无效的条形码");
                    Toast.makeText(this, "无效的条形码", Toast.LENGTH_SHORT).show();
                    scanHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            reStart();
                        }
                    }, 1500);
                } else {
                    speak(0, "签到成功");
                    PreferencesUtils.putString(getApplicationContext(), "cardNum", rawResult.getText());
                    mProgressBar.setVisibility(View.GONE);
                    finish();
                }

            } else if (type.equals("5")) {
                if (rawResult.getText().equals("") || rawResult.getText() == null) {
                    PreferencesUtils.putString(this, "signed", null);
                } else {
                    PreferencesUtils.putString(this, "signed", "true");
                }
                Log.d("number", rawResult.getText());
                Date date = new Date();
                String phone = null;
                List<ActionCustomersBean> actionCustomersBeans = actionCustomersBeanDao.loadAll();
                List<CustomersBean> customersBeans = customersBeanDao.loadAll();
                String cardNum = rawResult.getText().substring(0, 1);
                if (cardNum.equals("0")) {//二维码
                    String QrCode = rawResult.getText().substring(1, rawResult.getText().length());
                    String customerTime = QrCode.substring((QrCode.length()) - 6);
                    String nowTime = date.getDate() + "" + date.getHours() + "" + date.getMinutes() + "";
                    if (Integer.parseInt(nowTime) - Integer.parseInt(customerTime) > 1) {
                        speak(0, "二维码已过期");
                        Toast.makeText(this, "二维码已过期", Toast.LENGTH_SHORT).show();
                        PreferencesUtils.putString(this, "signed", null);
                        mProgressBar.setVisibility(View.GONE);
                        scanHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reStart();
                            }
                        }, 1000);
                    } else {
                        String customerCode = QrCode.substring(0, QrCode.length() - 8);
                        for (int i = 0; i < customersBeans.size(); i++) {
                            if (customerCode.equals(String.valueOf(customersBeans.get(i).getCustomerCode()))) {
                                phone = customersBeans.get(i).getCustomerMobile();
                                ActionCustomersBean unique = actionCustomersBeanDao.queryBuilder().where(ActionCustomersBeanDao.Properties.CustomerMobile.eq(phone),
                                        ActionCustomersBeanDao.Properties.ActivityId.eq(activityBeanDao.loadAll().get(0).getActivityId())).unique();
                                if (unique != null) {
                                    if (unique.getQdsucess().equals("1")) {
                                        speak(0, unique.getCustomerName() + ":" + "已签到");
                                        Toast.makeText(this, unique.getCustomerName() + ":" + "已签到", Toast.LENGTH_SHORT).show();
                                        isExit = true;
                                    } else {
                                        unique.setQdsj(date.getTime());
                                        unique.setQdsucess("1");
                                        unique.setIsSign("1");
                                        Log.d("customerCode", unique.getActivityId());
                                        speak(0, unique.getCustomerName() + "签到成功");
                                        Toast.makeText(this, unique.getCustomerName() + "签到成功", Toast.LENGTH_SHORT).show();
                                        actionCustomersBeanDao.update(unique);
                                        isExit = true;
                                    }
                                } else {
                                    speak(0, "该客户不存在");
                                    Toast.makeText(this, "该客户不存在", Toast.LENGTH_SHORT).show();
                                    PreferencesUtils.putString(this, "signed", null);
                                }
                                break;
                            }
                        }
                        if (!isExit) {
                            speak(0, "该客户不存在");
                            Toast.makeText(this, "该客户不存在", Toast.LENGTH_SHORT).show();
                            PreferencesUtils.putString(this, "signed", null);

                        }
                        mProgressBar.setVisibility(View.GONE);
                        scanHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reStart();
                            }
                        }, 1000);
                    }
                } else {//卡号
                    Log.d("IDD", activityBeanDao.loadAll().size() + "=======" + activityId + "---------------" + actionCustomersBeans.size());
                    for (int i = 0; i < actionCustomersBeans.size(); i++) {
                        if (rawResult.getText().equals(actionCustomersBeans.get(i).getCardNum())) {
                            if (actionCustomersBeans.get(i).getQdsucess().equals("1")) {
                                speak(0, actionCustomersBeans.get(i).getCustomerName() + ":" + "已签到");
                                Toast.makeText(this, actionCustomersBeans.get(i).getCustomerName() + ":" + "已签到", Toast.LENGTH_SHORT).show();
                                isExit = true;
                                mProgressBar.setVisibility(View.GONE);
                                scanHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        reStart();
                                    }
                                }, 1000);

                            }
//                       activityBeanDao.loadAll().get(0).getActivityId()
                            else if (actionCustomersBeans.get(i).getActivityId().equals(activityId)) {
                                Log.d("IDD", actionCustomersBeans.get(i).getActivityId() + "=======" + activityBeanDao.loadAll().get(0).getActivityId());
                                ActionCustomersBean unique = actionCustomersBeanDao.queryBuilder().where(ActionCustomersBeanDao.Properties.CardNum.eq(rawResult.getText())).unique();
                                if (unique != null) {
                                    unique.setQdsucess("1");
                                    unique.setQdsj(date.getTime());
                                    unique.setIsSign("1");
                                    actionCustomersBeanDao.update(unique);
                                    speak(0, actionCustomersBeans.get(i).getCustomerName() + "" + actionCustomersBeans.get(i).getCustomerMobile() + "签到成功");
                                    Toast.makeText(this, actionCustomersBeans.get(i).getCustomerName() + "--" + actionCustomersBeans.get(i).getCustomerMobile() + ":" + "签到成功", Toast.LENGTH_SHORT).show();
                                    isExit = true;
                                    mProgressBar.setVisibility(View.GONE);
                                    scanHandler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            reStart();
                                        }
                                    }, 1000);
                                }

                            } else {
                                Log.d("IDD", actionCustomersBeans.get(i).getActivityId() + "=======" + activityBeanDao.loadAll().get(0).getActivityId());
                                speak(0, "不是本场活动客户");
                                Toast.makeText(this, "不是本场活动客户", Toast.LENGTH_SHORT).show();
                                isExit = true;
                                PreferencesUtils.putString(this, "signed", null);
                                mProgressBar.setVisibility(View.GONE);
                                scanHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        reStart();
                                    }
                                }, 1000);
                            }
                            break;
                        }
                    }
                    if (!isExit) {
                        speak(0, "无效卡号");
                        Toast.makeText(this, "无效卡号", Toast.LENGTH_SHORT).show();
                        PreferencesUtils.putString(this, "signed", null);
                        mProgressBar.setVisibility(View.GONE);
                        scanHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reStart();
                            }
                        }, 1000);
                    } else {
                        isExit = false;
                    }

                }
            } else if (type.equals("10")) {
                orderId = mTextView12.getText().toString();
                totalPrice = mTextView13.getText().toString();
                final String url = "http://payment.ejjzcloud.com:44000/asj-pay/payController?method=tradePay&tokenId=" + tokenId +
                        "&orderId=" + orderId + "&totalPrice=" + totalPrice + "&type=3&authCode=" + rawResult.getText();
                Log.d("付款", url);
                RestClient.builder()
                        .url(url)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                if (response != null) {
                                    Log.d("付款", response);
                                    try {
                                        ZhiFu userInfo = JSONUtil.fromJson(response, ZhiFu.class);
                                        if (userInfo.getCode() == 1) {
                                            mProgressBar.setVisibility(View.GONE);
                                            speak(0, userInfo.getMsg());
                                            Toast.makeText(CaptureActivity.this, userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                                            scanHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    reStart();
                                                    type = "1";
                                                    mRelativeLayout.setVisibility(View.VISIBLE);
                                                    mRelativeLayout2.setVisibility(View.GONE);
                                                    mRelativeLayout3.setVisibility(View.GONE);
                                                    mTextView10.setText("收银");
                                                   Intent intent=new Intent(CaptureActivity.this,PayResultActivity.class);
                                                   intent.putExtra("orderId",orderId);
                                                   startActivity(intent);
                                                }
                                            }, 1000);

                                        } else if (userInfo.getCode() == 2) {
                                            mProgressBar.setVisibility(View.GONE);
                                            speak(0, userInfo.getMsg());
                                            Toast.makeText(CaptureActivity.this, userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                                            scanHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    reStart();
                                                }
                                            }, 1000);
                                        } else if (userInfo.getCode() == -1) {
                                            mProgressBar.setVisibility(View.GONE);
                                            speak(0, userInfo.getMsg());
                                            Toast.makeText(CaptureActivity.this, userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                                            scanHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    reStart();
                                                }
                                            }, 1000);
                                        } else if (userInfo.getCode() == -2) {
                                            mProgressBar.setVisibility(View.GONE);
                                            speak(0, userInfo.getMsg());
                                            Toast.makeText(CaptureActivity.this, userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                                            scanHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    reStart();
                                                }
                                            }, 1000);
                                        } else if (userInfo.getCode() == 0) {
                                            mProgressBar.setVisibility(View.GONE);
                                            speak(0, userInfo.getMsg());
                                            Toast.makeText(CaptureActivity.this, userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                                            scanHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    reStart();
                                                }
                                            }, 1000);
                                        }

                                    } catch (Exception e) {
                                        mProgressBar.setVisibility(View.GONE);
                                        Toast.makeText(CaptureActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                                        speak(0, "付款失败");
                                        scanHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                reStart();
                                            }
                                        }, 1500);
                                    }
                                }
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure() {
                                mProgressBar.setVisibility(View.GONE);
                                speak(0, "网络异常，请重新付款");
                                Toast.makeText(CaptureActivity.this, "支付异常，请重新付款", Toast.LENGTH_SHORT).show();
                                scanHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        reStart();
                                    }
                                }, 1000);
                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int code, String msg) {
                                mProgressBar.setVisibility(View.GONE);
                                speak(0, "网络异常，请重新付款");
                                Toast.makeText(CaptureActivity.this, "支付异常，请重新付款", Toast.LENGTH_SHORT).show();
                                scanHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        reStart();
                                    }
                                }, 1000);
                            }
                        })
                        .build()
                        .get();
            } else if (type.equals("11")) {
                final String url = BaseUrl.BASE_URL+"orderController.do?method=getOrderByOrderNumber&token_id=" + tokenId +
                        "&orderNumber=" + text+ "&activity_id=" + activityId ;
                Log.d("付款", url);
                RestClient.builder()
                        .url(url)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                if (response != null) {
                                    Log.d("付款", response);
                                    try {
                                        ChaxunInfo userInfo = JSONUtil.fromJson(response, ChaxunInfo.class);
                                        if (userInfo.getRet().equals("200")) {
                                            speak(0, "查询成功");
                                            Toast.makeText(CaptureActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                                            mProgressBar.setVisibility(View.GONE);
                                            mRelativeLayout4.setVisibility(View.VISIBLE);
                                            mTextView14.setText(userInfo.getData().getOrderMerchantName());
                                            mTextView15.setText(userInfo.getData().getOrderId());
                                            mTextView16.setText(String.valueOf(userInfo.getData().getTotalPrice()));
                                            mTextView17.setText(userInfo.getData().getOrderNum()+"");
                                            if(userInfo.getData().getPayType().equals("1")){
                                                mTextView18.setText("现金");
                                            }
                                            else if(userInfo.getData().getPayType().equals("2")){
                                                mTextView18.setText("刷卡");
                                            }
                                            else if(userInfo.getData().getPayType().equals("3")){
                                                mTextView18.setText("支付宝");
                                            }
                                            else if(userInfo.getData().getPayType().equals("4")){
                                                mTextView18.setText("微信");
                                            }
                                            else if(userInfo.getData().getPayType().equals("5")){
                                                mTextView18.setText("其他");
                                            }
                                            mTextView19.setText(userInfo.getData().getOrderManagerName());
                                        } else {
                                            mProgressBar.setVisibility(View.GONE);
                                            mRelativeLayout4.setVisibility(View.GONE);
                                            speak(0, userInfo.getMsg());
                                            Toast.makeText(CaptureActivity.this, userInfo.getMsg(), Toast.LENGTH_SHORT).show();
                                        }
                                        scanHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                reStart();
                                            }
                                        }, 1000);
                                    } catch (Exception e) {
                                        mProgressBar.setVisibility(View.GONE);
                                        mRelativeLayout4.setVisibility(View.GONE);
                                        Toast.makeText(CaptureActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                                        speak(0, "查询失败");
                                        scanHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                reStart();
                                            }
                                        }, 1500);
                                    }
                                }
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure() {
                                mProgressBar.setVisibility(View.GONE);
                                mRelativeLayout4.setVisibility(View.GONE);
                                Toast.makeText(CaptureActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                                scanHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        reStart();
                                    }
                                }, 1000);
                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int code, String msg) {
                                mProgressBar.setVisibility(View.GONE);
                                mRelativeLayout4.setVisibility(View.GONE);
                                Toast.makeText(CaptureActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                                scanHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        reStart();
                                    }
                                }, 1000);
                            }
                        })
                        .build()
                        .get();
            }
        } catch (Exception e) {
            mProgressBar.setVisibility(View.GONE);
            mRelativeLayout4.setVisibility(View.GONE);
            Toast.makeText(this, "" + e.toString(), Toast.LENGTH_SHORT).show();
        }
//        Intent resultIntent = new Intent();
//        bundle.putInt("width", mCropRect.width());
//        bundle.putInt("height", mCropRect.height());
//        bundle.putString("result", rawResult.getText());
//        resultIntent.putExtras(bundle);
//        this.setResult(RESULT_OK, resultIntent);
//        CaptureActivity.this.finish();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (cameraManager.isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            cameraManager.openDriver(surfaceHolder);
            // Creating the handler starts the preview, which can also throw a
            // RuntimeException.
            if (handler == null) {
                handler = new CaptureActivityHandler(this, cameraManager, DecodeThread.ALL_MODE);
            }
            initCrop();
        } catch (IOException ioe) {
            Log.w(TAG, ioe);
            displayFrameworkBugMessageAndExit();
        } catch (RuntimeException e) {
            // Barcode Scanner has seen crashes in the wild of this variety:
            // java.?lang.?RuntimeException: Fail to connect to camera service
            Log.w(TAG, "Unexpected error initializing camera", e);
            displayFrameworkBugMessageAndExit();
        }
    }

    private void displayFrameworkBugMessageAndExit() {
        // camera error
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("Camera error");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }

        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });
        builder.show();
    }

    public void restartPreviewAfterDelay(long delayMS) {
        if (handler != null) {
            handler.sendEmptyMessageDelayed(R.id.restart_preview, delayMS);
        }
    }

    public void reStart() {
        cameraManager = new CameraManager(getApplication());
        handler = null;
        if (isHasSurface) {
            initCamera(scanPreview.getHolder());
        } else {
            scanPreview.getHolder().addCallback(this);
        }
        inactivityTimer.onResume();
    }


    public Rect getCropRect() {
        return mCropRect;
    }

    /**
     * 初始化截取的矩形区域
     */
    private void initCrop() {
        int cameraWidth = cameraManager.getCameraResolution().y;
        int cameraHeight = cameraManager.getCameraResolution().x;

        /** 获取布局中扫描框的位置信息 */
        int[] location = new int[2];
        scanCropView.getLocationInWindow(location);

        int cropLeft = location[0];
        int cropTop = location[1] - getStatusBarHeight();

        int cropWidth = scanCropView.getWidth();
        int cropHeight = scanCropView.getHeight();

        /** 获取布局容器的宽高 */
        int containerWidth = scanContainer.getWidth();
        int containerHeight = scanContainer.getHeight();

        /** 计算最终截取的矩形的左上角顶点x坐标 */
        int x = cropLeft * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的左上角顶点y坐标 */
        int y = cropTop * cameraHeight / containerHeight;

        /** 计算最终截取的矩形的宽度 */
        int width = cropWidth * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的高度 */
        int height = cropHeight * cameraHeight / containerHeight;

        /** 生成最终的截取的矩形 */
        mCropRect = new Rect(x, y, width + x, height + y);
    }

    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    private void setUpDataBase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "dsx.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        actionCustomersBeanDao = daoSession.getActionCustomersBeanDao();
        customersBeanDao = daoSession.getCustomersBeanDao();
        activityBeanDao = daoSession.getActivityBeanDao();
//        getUserData();
    }


    private void getUserData() {
        String url = BaseUrl.BASE_URL + "phoneApi/outLineController.do?method=downloadData&token_id=" + tokenId + "&activityId=" + activityId + "&time=";
        Log.d("userDaata", url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            try {
                                if (response != null) {
                                    try {
                                        UserData userData = JSONUtil.fromJson(response, UserData.class);
                                        if (userData.getRet().equals("101")) {

                                        } else {
                                            if (userData.getRet().equals("200")) {
                                                actionCustomers = userData.getData().getActionCustomers();
                                                customers = userData.getData().getCustomers();
                                                insertInToDataBase(actionCustomers, customers);
                                            } else if (userData.getRet().equals("201")) {
                                                Toast.makeText(CaptureActivity.this, "" + userData.getMsg(), Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        }
                                    } catch (Exception e) {
                                        Toast.makeText(CaptureActivity.this, "更新数据库失败，请检查网络连接情况", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            } catch (Exception e) {
                                Toast.makeText(CaptureActivity.this, "更新数据库失败，请检查网络连接情况", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(CaptureActivity.this, "更新数据库失败，请检查网络连接情况", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .build()
                    .get();
        } catch (Exception e) {
            Toast.makeText(CaptureActivity.this, "更新数据库失败，请检查网络连接情况", Toast.LENGTH_SHORT).show();
            finish();
        }

    }


    private void insertInToDataBase(List<ActionCustomersBean> actionCustomers, List<CustomersBean> customers) {
        actionCustomersBeanDao = daoSession.getActionCustomersBeanDao();
        customersBeanDao = daoSession.getCustomersBeanDao();
        actionCustomersBeanDao.deleteAll();
        customersBeanDao.deleteAll();
        if (actionCustomers != null && actionCustomers.size() > 0) {
            for (int i = 0; i < actionCustomers.size(); i++) {
                ActionCustomersBean actionCustomersBean = actionCustomers.get(i);
                actionCustomersBeanDao.insert(actionCustomersBean);
            }
        }

        if (customers != null && customers.size() > 0) {
            customersBeanDao.insertInTx(customers);
//            for (int i = 0; i < customers.size(); i++) {
//                CustomersBean customersBean = customers.get(i);
//                customersBeanDao.insert(customersBean);
//            }
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


    private void speak(int speaker, String text) {
        //若是每次都这样是不是会有内存问题呢？需要思考改进
        VoiceUtils utils = new VoiceUtils();
        utils.init(this, speaker);
        mSpeechSynthesizer = utils.getSyntheszer();
        this.mSpeechSynthesizer.speak(text);
    }


}