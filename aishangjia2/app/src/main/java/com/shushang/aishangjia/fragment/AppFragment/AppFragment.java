package com.shushang.aishangjia.fragment.AppFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.shushang.aishangjia.Bean.GongGao;
import com.shushang.aishangjia.Bean.MenuItem;
import com.shushang.aishangjia.Bean.QuanXian;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.GongGaoActivity;
import com.shushang.aishangjia.activity.JiZhangActivity;
import com.shushang.aishangjia.activity.LianMengActivity;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.activity.MoBaiActivity;
import com.shushang.aishangjia.activity.ResetPwdActivity;
import com.shushang.aishangjia.activity.SettingActivity;
import com.shushang.aishangjia.activity.ZhangDanActivity;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.fragment.AppFragment.adapter.AppAdapter;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.ui.ExtAlertDialog;
import com.shushang.aishangjia.utils.Json.JSONUtil;
import com.shushang.aishangjia.utils.OnMultiClickListener;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


public class AppFragment extends BaseFragment  {

    private static final int REQUEST_CODE_GONGGAO= 2013;
    private static final int REQUEST_CODE_MYXIANSUO = 9090;
    private static final int REQUEST_CODE_NEW_PEOPLE =2662;
    private Toolbar mToolbar;
    private TextView mTextView1,mTextView2;
    private RecyclerView mRecyclerView;
    private AppAdapter adapter;
    private List<MenuItem> list;
    private TextView mTextView;
    private Vibrator vibrator;
    private String type=null;
    private String lianmengtype= PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "type");
    private String ResourceName2= PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(),"ResourceName2");
    private LinearLayout mLinearLayout1,mLinearLayout2,mLinearLayout3,mLinearLayout4,mLinearLayout5,mLinearLayout6,mLinearLayout7,mLinearLayout8;
    private View mView;
    private String  token_id = PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "token_id");
    private String shangjia_id= PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "shangjia_id");
    private Dialog mRequestDialog;
    private String leagueFlag=null;
    private String jizhang=null;
    private List<String> mQuanXians=new ArrayList<>();
    private TextView user_name,merchant_name;
    private String username = null;
    private String shangjia = null;
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        mRecyclerView=rootView.findViewById(R.id.rv_app);
        mToolbar=rootView.findViewById(R.id.toolbar);
        type=PreferencesUtils.getString(getActivity(), "type");
        leagueFlag=PreferencesUtils.getString(mContext,"leagueFlag");
        String quanxian = PreferencesUtils.getString(mContext, "quanxian");
        if(quanxian!=null&&!quanxian.equals("")){
            QuanXian quanXian = JSONUtil.fromJson(quanxian, QuanXian.class);
            mQuanXians.addAll(quanXian.getDataList());
        }
        mLinearLayout1=rootView.findViewById(R.id.fragment_app_lianmeng_caiwu);
        mLinearLayout2=rootView.findViewById(R.id.fragment_app_lianmeng_gonggao);
        mLinearLayout3=rootView.findViewById(R.id.fragment_app_lianmeng_kehu);
        mLinearLayout4=rootView.findViewById(R.id.fragment_app_lianmeng_jizhang);
        mLinearLayout5=rootView.findViewById(R.id.fragment_app_caozuo_liucheng);
        mLinearLayout6=rootView.findViewById(R.id.fragment_app_reset_pwd);
        mLinearLayout7=rootView.findViewById(R.id.fragment_app_sign);
        mLinearLayout8=rootView.findViewById(R.id.fragment_app_quit);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(getActivity(), getString(R.string.request));
        mRequestDialog.setCancelable(false);
        username = PreferencesUtils.getString(getActivity(), "xingming");
        shangjia = PreferencesUtils.getString(getActivity(), "shangjia_name");
        user_name.setText(username);
        merchant_name.setText(shangjia);
        mTextView1=rootView.findViewById(R.id.unread);
//        if(lianmengtype!=null&&lianmengtype.equals("7")){
//            if(ResourceName2!=null){
//                mLinearLayout4.setVisibility(View.VISIBLE);
//            }
//            else {
//                mLinearLayout4.setVisibility(View.GONE);
//            }
//        }


        mLinearLayout1.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
//                if(leagueFlag.equals("1")){
                    startActivity(new Intent(getActivity(),ZhangDanActivity.class));
//                }
//                else {
//                    ToastUtils.showLong("您没有参加联盟");
//                }
            }
        });

        mLinearLayout2.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
//                if(leagueFlag.equals("1")){
                    startActivityForResult(new Intent(getActivity(),GongGaoActivity.class),REQUEST_CODE_GONGGAO);
//                }
//                else {
//                    ToastUtils.showLong("您没有参加联盟");
//                }
            }
        });



        mLinearLayout3.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
//                if(leagueFlag.equals("1")){
                    startActivity(new Intent(getActivity(),LianMengActivity.class));
//                }
//                else {
//                    ToastUtils.showLong("您没有参加联盟");
//                }
            }
        });

       mLinearLayout4.setOnClickListener(new OnMultiClickListener() {
           @Override
           public void onMultiClick(View v) {
               if(leagueFlag.equals("1")){
                   if(mQuanXians.size()!=0){
                       for (int i = 0; i < mQuanXians.size(); i++) {
                           if(mQuanXians.get(i).equals("2212")){
                               jizhang="2212";
                               startActivity(new Intent(getActivity(),JiZhangActivity.class));
                               break;
                           }
                       }
                       if(jizhang==null||jizhang.equals("")){
                           ToastUtils.showLong("您没有记账权限");
                       }
                   }
               }
               else {
                   ToastUtils.showLong("您没有参加联盟");
               }
           }
       });

       mLinearLayout5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getActivity(), SettingActivity.class));
           }
       });

        mLinearLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ResetPwdActivity.class));
            }
        });

        mLinearLayout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MoBaiActivity.class));
            }
        });

        mLinearLayout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExtAlertDialog.showSureDlg(getActivity(), null, getString(R.string.logout_tip), getString(R.string.exit_login), new ExtAlertDialog.IExtDlgClick() {
                    @Override
                    public void Oclick(int result) {
                        if (result == 1) {
                            PreferencesUtils.putString(mContext, "company", null);
                            PreferencesUtils.putString(mContext, "user_name", null);
                            PreferencesUtils.putString(mContext, "password", null);
                            PreferencesUtils.putString(mContext, "token_id", null);
                            PreferencesUtils.putString(mContext, "type", null);
//                            PreferencesUtils.putBoolean(mContext,"checked",false);
                            startActivity(new Intent(getActivity(), LoginActivity2.class));
                            getActivity().finish();

                        }

                    }
                });
            }
        });

        mRequestDialog.show();
        if(shangjia_id==null){
            ExtAlertDialog.showSureDlg3(getActivity(), "提醒", "您需要重新登录来获取联盟ID！", "确定",false, new ExtAlertDialog.IExtDlgClick() {
                @Override
                public void Oclick(int result) {
                    if(result==1){
                        startActivity(new Intent(getActivity(),LoginActivity2.class));
                        PreferencesUtils.putString(mContext, "company", null);
                        PreferencesUtils.putString(mContext, "user_name", null);
                        PreferencesUtils.putString(mContext, "password", null);
                        PreferencesUtils.putString(mContext, "token_id", null);
                        PreferencesUtils.putString(mContext, "type", null);
                        getActivity().finish();
                    }
                }
            });
        }
        getData(token_id);
    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_app_new, null);
        user_name=view.findViewById(R.id.user_name);
        merchant_name=view.findViewById(R.id.merchant_name);
        return view;
    }


    @Override
    public void initData() {
        super.initData();
    }

    public void getData(String token_id) {
        String url= BaseUrl.BASE_URL+"phoneLeagueController.do?method=getNoticesByMerchantId&token_id="+token_id+"&page=1&rows=10&isRead=0";
        Log.d("BaseUrl",url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                mRequestDialog.dismiss();
                            }
                            if(response!=null){
                                Log.d("AppPeopleActivity",response);
                                try {
                                    GongGao test = JSONUtil.fromJson(response, GongGao.class);
                                    if(test.getRet().equals("101")){
                                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                            mRequestDialog.dismiss();
                                        }
                                        Toast.makeText(getActivity(), ""+test.getMsg(), Toast.LENGTH_SHORT).show();
                                        PreferencesUtils.putString(getActivity(),"token_id",null);
                                        startActivity(new Intent(getActivity(), LoginActivity2.class));
                                        getActivity().finish();
                                    }
                                    else if(test.getRet().equals("200")){
                                        int size = test.getDataList().size();
                                        if(size>0){
                                            mTextView1.setVisibility(View.VISIBLE);
                                            mTextView1.setText(size+"");
                                        }
                                        else {
                                            EventBus.getDefault().post(new MessageEvent("无未读"));
                                            mTextView1.setVisibility(View.GONE);
                                        }
                                    }
                                    else if(test.getRet().equals("201")){
                                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                            mRequestDialog.dismiss();
                                        }
                                        Toast.makeText(getActivity(), ""+test.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){
                                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                        mRequestDialog.dismiss();
                                    }
                                    Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getActivity(), "获取数据错误了！！！！", Toast.LENGTH_SHORT).show();
                        }
                    }).error(new IError() {
                @Override
                public void onError(int code, String msg) {
                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                        mRequestDialog.dismiss();
                    }
                    Toast.makeText(getActivity(), ""+msg, Toast.LENGTH_SHORT).show();
                }
            })
                    .build()
                    .get();
        }
        catch (Exception e){
            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                mRequestDialog.dismiss();
            }
            ToastUtils.showLong(e.toString());
        }

    }









    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_GONGGAO){
            getData(token_id);
        }
//        else if(requestCode==REQUEST_CODE_MYXIANSUO){
//            getData2(token_id);
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        vibrator.cancel();
    }
}
