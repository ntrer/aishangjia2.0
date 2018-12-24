package com.shushang.aishangjia;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.shushang.aishangjia.activity.FingerActivity;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseActivity;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;

public class SplashActivity extends BaseActivity {

    private boolean isAlive,isclick,checked;
    private String tokenId;
    private Handler mHandler=new Handler();
    @Override
    public int setLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {
        isAlive=PreferencesUtils.getBoolean(MyApplication.getInstance().getApplicationContext(),"isAlive");
        isclick=PreferencesUtils.getBoolean(MyApplication.getInstance().getApplicationContext(),"isclick");
        checked=PreferencesUtils.getBoolean(MyApplication.getInstance().getApplicationContext(),"checked");
        tokenId=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "token_id");
        if(String.valueOf(isAlive)==null){
            PreferencesUtils.putBoolean(SplashActivity.this,"isAlive",true);
            if(tokenId!=null&&checked){
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this,FingerActivity.class));
                        finish();
                    }
                },2000);
            }
            else {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this,LoginActivity2.class));
                        Log.d("我回来了", "我回来了1");
                        finish();
                    }
                },2000);
            }
        }
        else if(isAlive==true){
            if(tokenId!=null&&checked){
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this,FingerActivity.class));
                        finish();
                    }
                },2000);
            }
            else {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this,LoginActivity2.class));
                        Log.d("我回来了", "我回来了2");
                        finish();
                    }
                },2000);
            }
        }
        else if(isAlive==false&&String.valueOf(isclick)==null){
            PreferencesUtils.putBoolean(SplashActivity.this,"isAlive",true);
            if(tokenId!=null&&checked){
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this,FingerActivity.class));
                        finish();
                    }
                },2000);
            }
            else {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this,LoginActivity2.class));
                        Log.d("我回来了", "我回来了3");
                        finish();
                    }
                },2000);
            }
        }
        else if(isAlive==false&&isclick==true){
            PreferencesUtils.putBoolean(SplashActivity.this,"isAlive",true);
            if(tokenId!=null&&checked){
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this,FingerActivity.class));
                        finish();
                    }
                },2000);
            }
            else {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(SplashActivity.this,LoginActivity2.class);
                        Log.d("我回来了", "我回来了3");
                        intent.putExtra("tuisong","推送");
                        startActivity(intent);
                        finish();
                    }
                },2000);
            }
        }
        else if(isAlive==false&&isclick==false){
            Log.d("isAlive","++++++++++++++++++++++++++++");
            PreferencesUtils.putBoolean(SplashActivity.this,"isAlive",true);
            if(tokenId!=null&&checked){
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this,FingerActivity.class));
                        finish();
                    }
                },2000);
            }
            else {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this,LoginActivity2.class));
                        Log.d("我回来了", "我回来了4");
                        finish();
                    }
                },2000);
            }
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mHandler!=null){
          mHandler.removeCallbacksAndMessages(null);
        }
    }
}
