package com.shushang.aishangjia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;

import com.blankj.utilcode.util.ToastUtils;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.ShiYongActivity;
import com.shushang.aishangjia.activity.YinsiActivity;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;

public class SettingFragment extends PreferenceFragment {

    private FingerprintManagerCompat fingerprintManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_activity);
        fingerprintManager = FingerprintManagerCompat.from(getActivity());
        final CheckBoxPreference checkboxPref = (CheckBoxPreference) getPreferenceManager()
                .findPreference("switch");

        checkboxPref.setChecked(PreferencesUtils.getBoolean(getActivity(),"checked"));

        final Preference listPreference = (Preference) getPreferenceManager()
                .findPreference("yinsi");

        final Preference listPreference2 = (Preference) getPreferenceManager()
                .findPreference("shiyong");


        checkboxPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    if (!fingerprintManager.isHardwareDetected()) {
                        ToastUtils.showLong("您没有指纹传感器");
                        checkboxPref.setChecked(false);
                        PreferencesUtils.putBoolean(getActivity(),"checked",false);

                    } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                        ToastUtils.showLong("您的手机没有注册指纹");
                        checkboxPref.setChecked(false);
                        PreferencesUtils.putBoolean(getActivity(),"checked",false);
                    }
                }
                else {
                    ToastUtils.showLong("您的安卓系统版本低于6.0，不支持指纹开启app");
                    checkboxPref.setChecked(false);
                    PreferencesUtils.putBoolean(getActivity(),"checked",false);
                }
                return false;
            }
        });

        checkboxPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            /**
             * @param preference The changed Preference.
             * @param newValue   The new value of the Preference.
             * @return True to update the state of the Preference with the new value.
             */
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean checked = Boolean.valueOf(newValue.toString());
                //保存到SharedPreferences中
                PreferencesUtils.putBoolean(getActivity(),"checked",checked);
//                if(checked){
//                    if (fingerprintManager.isHardwareDetected()) {
//                        ToastUtils.showLong("您没有指纹传感器");
//                        //保存到SharedPreferences中
//                        PreferencesUtils.putBoolean(getActivity(),"checked",false);
//
//                    } else if (!fingerprintManager.hasEnrolledFingerprints()) {
//                        ToastUtils.showLong("您的手机没有注册指纹");
//                        //保存到SharedPreferences中
//                        PreferencesUtils.putBoolean(getActivity(),"checked",false);
//
//                    }
//                    else {
//                        //保存到SharedPreferences中
//                        PreferencesUtils.putBoolean(getActivity(),"checked",true);
//                    }
//                }
//                else {

//                }
                return true;
            }
        });

        listPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(getActivity(),YinsiActivity.class));
                return false;
            }
        });

        listPreference2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(getActivity(),ShiYongActivity.class));
                return false;
            }
        });

    }
}
