package com.shushang.aishangjia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.widget.Toast;

import com.shushang.aishangjia.MainActivity2;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.base.BaseActivity;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;

public class FingerActivity extends BaseActivity {

    private boolean isQuit=false;
    private FingerprintManagerCompat fingerprintManager;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            fingerprintManager.authenticate(null, 0, null, new FingerAuthenticateCallBack(), handler);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fingerprintManager = FingerprintManagerCompat.from(this);
            try {
                //这里去新建一个结果的回调，里面回调显示指纹验证的信息
                fingerprintManager.authenticate(null, 0, null, new FingerAuthenticateCallBack(), null);
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    @Override
    public int setLayout() {
        return R.layout.activity_finger;
    }

    @Override
    public void init() {

    }


    public class FingerAuthenticateCallBack extends FingerprintManagerCompat.AuthenticationCallback {
        private static final String TAG = "FingerAuthenticateCallBack";

        // 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            if(!isQuit){
                handler.sendMessageDelayed(new Message(), 1000 * 30);
                PreferencesUtils.putString(FingerActivity.this, "company", null);
                PreferencesUtils.putString(FingerActivity.this, "user_name", null);
                PreferencesUtils.putString(FingerActivity.this, "password", null);
                PreferencesUtils.putString(FingerActivity.this, "token_id", null);
                PreferencesUtils.putString(FingerActivity.this, "type", null);
                PreferencesUtils.putBoolean(FingerActivity.this,"checked",false);
                Toast.makeText(FingerActivity.this, "多次解锁失败，请重新登录", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FingerActivity.this,LoginActivity2.class));
                finish();
            }
        }

        // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
        @Override
        public void onAuthenticationFailed() {
            Toast.makeText(FingerActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            Toast.makeText(FingerActivity.this, ""+helpString, Toast.LENGTH_SHORT).show();
        }

        // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult
                                                      result) {
            handler.sendMessageDelayed(new Message(), 1000 * 30);
            startActivity(new Intent(FingerActivity.this,MainActivity2.class));
            Toast.makeText(FingerActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        isQuit=true;
        if(handler!=null){
            handler.removeCallbacksAndMessages(this);
        }
    }
}
