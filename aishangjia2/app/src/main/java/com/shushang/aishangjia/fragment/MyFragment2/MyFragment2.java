package com.shushang.aishangjia.fragment.MyFragment2;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.activity.ResetPwdActivity;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.ui.ExtAlertDialog;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 17/02/02
 *     desc  : demo about FragmentUtils
 * </pre>
 */
public class MyFragment2 extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fragment_app_bg)
    RelativeLayout mFragmentAppBg;
    @BindView(R.id.fragment_app_lianmeng_kehu)
    LinearLayout mFragmentAppLianmengKehu;
    @BindView(R.id.fragment_app_lianmeng_jizhang)
    LinearLayout mFragmentAppLianmengJizhang;
    @BindView(R.id.fragment_app_lianmeng_gonggao)
    LinearLayout mFragmentAppLianmengGonggao;
    @BindView(R.id.fragment_app_lianmeng_caiwu)
    LinearLayout mFragmentAppLianmengCaiwu;
    @BindView(R.id.first_line)
    LinearLayout mFirstLine;
    @BindView(R.id.fragment_app_caozuo_liucheng)
    LinearLayout mFragmentAppCaozuoLiucheng;
    @BindView(R.id.fragment_app_reset_pwd)
    LinearLayout mFragmentAppResetPwd;
    @BindView(R.id.fragment_app_quit)
    LinearLayout mFragmentAppQuit;
    @BindView(R.id.second_line)
    LinearLayout mSecondLine;
    Unbinder unbinder;
    private String token_id = null;
    private LinearLayout ll_reset;
    private LinearLayout ll_quit;
    private LinearLayout ll_check;
    private String username = null;
    private String shangjia = null;
    private TextView mTextView1, mTextView2;
    private TextView mVersionCode;
    private String versionCode;
    private TextView user_name,merchant_name;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
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
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        username = PreferencesUtils.getString(mContext, "xingming");
        shangjia = PreferencesUtils.getString(mContext, "shangjia_name");
        user_name.setText(username);
        merchant_name.setText(shangjia);
        versionCode = getVersionCode(mContext);
    }


    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart("MyFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd("MyFragment");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.re_password:
                startActivity(new Intent(getActivity(), ResetPwdActivity.class));
                break;
            case R.id.quit:
                ExtAlertDialog.showSureDlg(getActivity(), null, getString(R.string.logout_tip), getString(R.string.exit_login), new ExtAlertDialog.IExtDlgClick() {
                    @Override
                    public void Oclick(int result) {
                        if (result == 1) {
                            PreferencesUtils.putString(mContext, "company", null);
                            PreferencesUtils.putString(mContext, "user_name", null);
                            PreferencesUtils.putString(mContext, "password", null);
                            PreferencesUtils.putString(mContext, "token_id", null);
                            PreferencesUtils.putString(mContext, "type", null);
                            startActivity(new Intent(getActivity(), LoginActivity2.class));
                            getActivity().finish();

                        }


                    }
                });
                break;
        }
    }


    public static String getVersionCode(Context mContext) {
        String versionCode = null;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
