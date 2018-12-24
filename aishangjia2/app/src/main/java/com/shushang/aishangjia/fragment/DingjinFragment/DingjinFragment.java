package com.shushang.aishangjia.fragment.DingjinFragment;

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

import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.DailyOrderActivity;
import com.shushang.aishangjia.activity.GoodsActivity;
import com.shushang.aishangjia.activity.ProActivityActivity;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.fragment.DingDanFragment.DingDanFragment;
import com.shushang.aishangjia.fragment.DingjinFragment.adapter.DingjinAdapter;
import com.shushang.aishangjia.fragment.YiXiangJinFragment.YiXiangJinFragment;
import com.shushang.aishangjia.ui.segmentView;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;
import com.zaaach.toprightmenu.MenuItem;
import com.zaaach.toprightmenu.TopRightMenu;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class DingjinFragment extends BaseFragment  {

    private TopRightMenu mTopRightMenu;
    segmentView mSegmentView;
    private LinearLayout mLinearLayout;
    Toolbar mToolbar;
    ViewPager mViewpager;
    private ImageView mImageView;
    private int xoff;
    private String type;
    private static final int REQUEST_CODE_RICHANGDINGJIN= 2008;
    private static final int REQUEST_CODE_HUODONG= 2009;
    private static final int REQUEST_CODE_DINGDAN= 2010;
    private static final int REQUEST_CODE_DINGJIN= 2007;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        SetUpViewPager(mViewpager);
        mSegmentView.setOnSegmentViewClickListener(new segmentView.onSegmentViewClickListener() {
            @Override
            public void onSegmentViewClick(View view, int position) {
                if(position==0){
                    mViewpager.setCurrentItem(0,true);
                }
                else if(position==1){
                    mViewpager.setCurrentItem(1,true);
                }
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
        View view = View.inflate(mContext, R.layout.fragment_dingjin, null);
        mSegmentView=view.findViewById(R.id.segmentView);
        type=PreferencesUtils.getString(getActivity(),"type");
        mToolbar=view.findViewById(R.id.toolbar);
        mViewpager=view.findViewById(R.id.viewpager);
        mLinearLayout=view.findViewById(R.id.more_menu);
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopRightMenu = new TopRightMenu(getActivity());
                List<MenuItem> menuItems = new ArrayList<>();
                if(type.equals("5")){
                    menuItems.add(new MenuItem(R.mipmap.huo, "活动订金"));
                }
                else {
                    menuItems.add(new MenuItem(R.mipmap.ri, "日常订金"));
                    menuItems.add(new MenuItem(R.mipmap.huo, "活动订金"));
                    menuItems.add(new MenuItem(R.mipmap.add_menu, "添加订单"));
                }
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
                                if(type.equals("5")){
                                    if(position==0){
                                        //表示所有权限都授权了
                                        Intent openCameraIntent = new Intent(getActivity(), ProActivityActivity.class);
                                        openCameraIntent.putExtra("type", "1");
//                                    openCameraIntent.putExtra("event","6");
                                        startActivityForResult(openCameraIntent, REQUEST_CODE_DINGJIN );
                                    }
                                }
                                else {
                                    if(position==0){
                                        startActivityForResult(new Intent(getActivity(), DailyOrderActivity.class),REQUEST_CODE_RICHANGDINGJIN);
                                    }
                                    else if(position==1){
//                                    startActivityForResult(new Intent(getActivity(), AppPeopleActivity.class),REQUEST_CODE_HUODONG);
                                        //表示所有权限都授权了
                                        Intent openCameraIntent = new Intent(getActivity(), ProActivityActivity.class);
                                        openCameraIntent.putExtra("type", "3");
//                                    openCameraIntent.putExtra("event","6");
                                        startActivityForResult(openCameraIntent, REQUEST_CODE_DINGJIN );
                                    }
                                    else {
                                        startActivityForResult(new Intent(getActivity(), GoodsActivity.class),REQUEST_CODE_DINGDAN);
                                    }
                                }
                            }
                        })
                        .showAsDropDown(mLinearLayout, xoff, 0);
//                        .showAsDropDown(moreBtn);
            }
        });
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              if(position==0){
                 mSegmentView.setSelectTextColor(0);
              }
              else {
                  mSegmentView.setSelectTextColor(1);
              }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }



    private void SetUpViewPager(ViewPager bookViewpager) {
        DingjinAdapter adapter = new DingjinAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(YiXiangJinFragment.newInstance(), "");
        adapter.addFragment(DingDanFragment.newInstance(), "");
        bookViewpager.setAdapter(adapter);
        bookViewpager.setCurrentItem(0, true);
        bookViewpager.setOffscreenPageLimit(2);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_RICHANGDINGJIN){
            EventBus.getDefault().post(new MessageEvent("添加订金"));
        }else if(requestCode==REQUEST_CODE_DINGDAN){
            EventBus.getDefault().post(new MessageEvent("活动订金"));
        }
        else if(requestCode==REQUEST_CODE_DINGJIN){
            EventBus.getDefault().post(new MessageEvent("活动订金"));
        }
    }
}
