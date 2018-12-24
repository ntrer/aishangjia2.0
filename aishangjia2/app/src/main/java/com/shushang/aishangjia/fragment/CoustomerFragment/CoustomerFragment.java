package com.shushang.aishangjia.fragment.CoustomerFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.ProActivityActivity;
import com.shushang.aishangjia.activity.XiansuoActivity;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.fragment.AllCoustomerFragment.AllCoustomerFragment;
import com.shushang.aishangjia.fragment.CoustomerFragment.adapter.CoustomerFragmentAdapter;
import com.shushang.aishangjia.fragment.GenJInFragment.GenjinFragment;
import com.shushang.aishangjia.fragment.MyCoustomerFragment.MyCoustomerFragment;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;
import com.zaaach.toprightmenu.MenuItem;
import com.zaaach.toprightmenu.TopRightMenu;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class CoustomerFragment extends BaseFragment {

    private TopRightMenu mTopRightMenu;
    private LinearLayout mLinearLayout,mLinearLayout2;
    private ImageView mImageView1,mImageView2,mImageView3;
    private static final int REQUEST_CODE_RICHANG= 2003;
    private static final int REQUEST_CODE_HUODONG= 2004;
    Toolbar mToolbar;
    ViewPager mViewpager;
    private int height,xoff;
    private String type;
    private TextView mTextView;
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if(savedInstanceState==null){
            SetUpViewPager(mViewpager);
        }
        mImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewpager.setCurrentItem(0);
            }
        });

        mImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewpager.setCurrentItem(1);
            }
        });

        mImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewpager.setCurrentItem(2);
            }
        });

    }

    @Override
    public View initView() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        if(screenWidth<=1000){
            xoff=-150;
        }
        else {
            xoff=-180;
        }
        View view = View.inflate(mContext, R.layout.fragment_coustomer, null);
        mLinearLayout2=view.findViewById(R.id.segmentView);
        mToolbar=view.findViewById(R.id.toolbar);
        mViewpager=view.findViewById(R.id.coustomer_viewpager);
        mImageView1=view.findViewById(R.id.kehu_my);
        mImageView2=view.findViewById(R.id.kehu_all);
        mImageView3=view.findViewById(R.id.kehu_genjin);
        mImageView1.setSelected(true);
        mLinearLayout=view.findViewById(R.id.more_menu);
        type=PreferencesUtils.getString(getActivity(),"type");
        if(!type.equals("5")){
            mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTopRightMenu = new TopRightMenu(getActivity());
                    List<MenuItem> menuItems = new ArrayList<>();
                    menuItems.add(new MenuItem(R.mipmap.richang_tuoke, "线索拓客"));
                    menuItems.add(new MenuItem(R.mipmap.huodng_add, "活动拓客"));
                    mTopRightMenu
                            .setHeight(RecyclerView.LayoutParams.WRAP_CONTENT)     //默认高度480
                            .setWidth(RecyclerView.LayoutParams.WRAP_CONTENT)      //默认宽度wrap_content
                            .showIcon(true)     //显示菜单图标，默认为true
                            .dimBackground(true)           //背景变暗，默认为true
                            .needAnimationStyle(true)   //显示动画，默认为true
                            .setAnimationStyle(R.style.TRM_ANIM_STYLE)  //默认为R.style.TRM_ANIM_STYLE
                            .addMenuList(menuItems)
                            .setOnMenuItemClickListener(new TopRightMenu.OnMenuItemClickListener() {
                                @Override
                                public void onMenuItemClick(int position) {
                                    if(position==0){
                                        startActivityForResult(new Intent(getActivity(), XiansuoActivity.class),REQUEST_CODE_RICHANG);
                                    }
                                    else {
                                        Intent intent=new Intent(getActivity(), ProActivityActivity.class);
                                        intent.putExtra("type", "6");
                                        startActivityForResult(intent,REQUEST_CODE_HUODONG);
                                    }
                                }
                            })
                            .showAsDropDown(mLinearLayout, xoff, 0);
//                        .showAsDropDown(moreBtn);

                }
            });
        }
        else {
            mLinearLayout.setVisibility(View.GONE);
        }

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
             if(position==0){
                 mImageView1.setSelected(true);
                 mImageView2.setSelected(false);
                 mImageView3.setSelected(false);
             }
             else if(position==1){
                 mImageView1.setSelected(false);
                 mImageView2.setSelected(true);
                 mImageView3.setSelected(false);
             }
             else {
                 mImageView1.setSelected(false);
                 mImageView2.setSelected(false);
                 mImageView3.setSelected(true);
             }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }


    private void SetUpViewPager(ViewPager bookViewpager) {
        CoustomerFragmentAdapter adapter = new CoustomerFragmentAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(MyCoustomerFragment.newInstance(), "");
        adapter.addFragment(AllCoustomerFragment.newInstance(), "");
        adapter.addFragment(GenjinFragment.newInstance(), "");
        bookViewpager.setAdapter(adapter);
        bookViewpager.setCurrentItem(0, true);
        bookViewpager.setOffscreenPageLimit(3);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_HUODONG){
            EventBus.getDefault().post(new MessageEvent("活动拓客"));
        }
        else {
            EventBus.getDefault().post(new MessageEvent("活动拓客"));
        }
    }
}
