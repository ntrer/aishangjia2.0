package com.shushang.aishangjia.fragment.ActivityFragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.shushang.aishangjia.base.BaseViewPagerAdapter2;

public class ActivityFragmentAdapter extends BaseViewPagerAdapter2 {

    public ActivityFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }
}
