package com.shushang.aishangjia.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.noober.background.BackgroundLibrary;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.ui.ExtAlertDialog;
import com.shushang.aishangjia.utils.ActivityManager.ActivityStackManager;
import com.shushang.aishangjia.utils.permissionUtil;

import java.util.ArrayList;
import java.util.List;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/10/24
 *     desc  : base about activity
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity {

    //退出时的时间
    private long mExitTime;
    private PermissionListener mListener;
    private static final int PERMISSION_REQUESTCODE = 100;
    private static final int REQUEST_PERMISSION_SETTING = 600;
    private View statusBarView;
    private final String TAG = BaseActivity.class.getName();
    private Toast toast = null;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BackgroundLibrary.inject2(this);
        super.onCreate(savedInstanceState);
        ActivityStackManager.getActivityStackManager().pushActivity(this);
        setContentView(setLayout());
        if (isStatusBar()) {
            initStatusBar();
            getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    initStatusBar();
                }
            });
        }
        //延时加载数据.
//        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
//            @Override
//            public boolean queueIdle() {
//                if (isStatusBar()) {
//                    initStatusBar();
//                    getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//                        @Override
//                        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                            initStatusBar();
//                        }
//                    });
//                }
//                //只走一次
//                return false;
//            }
//        });

        //沉浸式状态栏
//        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.drawable.bg_color), false);
        //各种初始化操作
        init();
        JAnalyticsInterface.onPageStart(this,this.getClass().getCanonicalName());
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



    public abstract int setLayout();

    public abstract void  init();

    //动态申请权限
    public void requestRunPermisssion(String[] permissions, PermissionListener listener){
        mListener = listener;
        List<String> permissionLists = new ArrayList<>();
        for(String permission : permissions){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                permissionLists.add(permission);
            }
        }

        if(!permissionLists.isEmpty()){
            ActivityCompat.requestPermissions(this, permissionLists.toArray(new String[permissionLists.size()]), PERMISSION_REQUESTCODE);
        }else{
            //表示全都授权了
            mListener.onGranted();
        }
    }

   //权限申请后的操作
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUESTCODE:
                if(grantResults.length > 0){
                    //存放没授权的权限
                    List<String> deniedPermissions = new ArrayList<>();
                    for(int i = 0; i < grantResults.length; i++){
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if(grantResult==PackageManager.PERMISSION_DENIED){
                            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                                    permissions[i])){
                                reGetPermission();
                            }
                            else {
                                deniedPermissions.add(permission);
                            }

                        }
                    }
                    if(deniedPermissions.isEmpty()){
                        //说明都授权了
                        mListener.onGranted();
                    }else{
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
        JAnalyticsInterface.onPageStart(this,this.getClass().getCanonicalName());
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
        JAnalyticsInterface.onPageEnd(this,this.getClass().getCanonicalName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JAnalyticsInterface.onPageEnd(this,this.getClass().getCanonicalName());
        ActivityStackManager.getActivityStackManager().popActivity(this);
    }

    private void reGetPermission() {
        ExtAlertDialog.showSureDlg(this, "警告", "权限被拒绝，部分功能将无法使用，请重新授予权限", "确定", new ExtAlertDialog.IExtDlgClick() {
            @Override
            public void Oclick(int result) {
                if(result==1){
                    permissionUtil.GoToSetting(BaseActivity.this);
                    finish();
                }
            }
        });
    }


    /**
     * @param context 上下文对象
     * @param start   其实activity
     * @param target  目标activity
     */
    public void jumpToActivity(Context context, Class start, Class target) {
        Intent intent = new Intent(context, target);
        context.startActivity(intent);
        finish();
    }

    /**
     * @param context
     * @param text
     */
    public void showToastLong(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public void showToastLong(String text) {
        if (toast == null) {
            toast = Toast.makeText(BaseActivity.this, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    /**
     * @param context
     * @param text
     */
    public void showToastShort(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public void showToastShort(String text) {
        if (toast == null) {
            toast = Toast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }


    //-----显示ProgressDialog
    public void showProgress(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(BaseActivity.this, ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);//设置点击不消失
        }
        if (progressDialog.isShowing()) {
            progressDialog.setMessage(message);
        } else {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }
    //------取消ProgressDialog
    public void removeProgress(){
        if (progressDialog==null){
            return;
        }
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }

    }


}
