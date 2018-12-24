package com.shushang.aishangjia.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.shushang.aishangjia.Bean.Voice;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.base.BaseActivity;
import com.shushang.aishangjia.ui.baiduUi.BaiduASRDialogTheme;
import com.shushang.aishangjia.utils.speech.asrpartialjson.AsrPartialJsonData;

import java.util.ArrayList;

public class SpeechActivity extends BaseActivity  {

    private TextView mEditText;
    private Button mButton;
    private boolean logTime = true;
    private Toolbar mToolbar;
    private String final_result;
    private int mTheme = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
        initView();
        initPermission();
        mTheme=BaiduASRDialogTheme.THEME_BLUE_DEEPBG;
//        start();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSpeech(SpeechActivity.this);
            }
        });

    }

    @Override
    public int setLayout() {
        return R.layout.activity_speech;
    }

    @Override
    public void init() {

    }

    private void initView() {
//        tvResult = (TextView) findViewById(R.id.tvResult);
//        tvParseResult = (TextView) findViewById(R.id.tvParseResult);
//        btnStartRecord = (Button) findViewById(R.id.btnStartRecord);
//        btnStopRecord = (Button) findViewById(R.id.btnStopRecord);
        mEditText=findViewById(R.id.content);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        mButton=findViewById(R.id.btn_restart);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String permissions[] = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm :permissions){
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                //进入到这里代表没有权限.

            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()){
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }




    private void start() {
        mEditText.setText("");

//        printResult("输入参数：" + json);
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    private void parseAsrPartialJsonData(String data) {
        Gson gson = new Gson();
        AsrPartialJsonData jsonData = gson.fromJson(data, AsrPartialJsonData.class);
        String resultType = jsonData.getResult_type();
        if(resultType != null && resultType.equals("partial_result")){
            final_result = jsonData.getBest_result();
            mEditText.setText(final_result);

        }
        else if(resultType != null && resultType.equals("final_result")){
            final_result = jsonData.getBest_result();
            mEditText.setText(final_result);
            gotoActivity(final_result);
        }
    }



    public void initSpeech(final Context context) {
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(context, null);
        //2.设置accent、language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        //3.设置回调接口
        mDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean isLast) {
                if (!isLast) {
                    //解析语音
                    //返回的result为识别后的汉字,直接赋值到TextView上即可
                    String result = parseVoice(recognizerResult.getResultString());
                    mEditText.setText(result);
                }
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        //4.显示dialog，接收语音输入
        mDialog.show();
        //获取字体所在的控件，设置为"",隐藏字体，
        TextView txt = (TextView)mDialog.getWindow().getDecorView().findViewWithTag("textlink");
        txt.setText("");
    }

    /**
     * 解析语音json
     */
    public String parseVoice(String resultString) {
        Gson gson = new Gson();
        Voice voiceBean = gson.fromJson(resultString, Voice.class);
        StringBuffer sb = new StringBuffer();
        ArrayList<Voice.WSBean> ws = voiceBean.ws;
        for (Voice.WSBean wsBean : ws) {
            String word = wsBean.cw.get(0).w;
            sb.append(word);
        }
        return sb.toString();
    }









    private void gotoActivity(String final_result) {
      if(final_result.contains("拓客")){
           startActivity(new Intent(SpeechActivity.this,AppPeopleActivity.class));
           finish();
      }
      else if(final_result.contains("线索")){
          startActivity(new Intent(SpeechActivity.this,XiansuoActivity.class));
          finish();
      }
      else if(final_result.contains("日常订金")){
          startActivity(new Intent(SpeechActivity.this,DailyOrderActivity.class));
          finish();
      }
      else if(final_result.contains("订单")){
          startActivity(new Intent(SpeechActivity.this,GoodsActivity.class));
          finish();
      }


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


}
