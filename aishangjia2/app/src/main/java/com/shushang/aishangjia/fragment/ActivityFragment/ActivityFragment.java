package com.shushang.aishangjia.fragment.ActivityFragment;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.codingending.popuplayout.PopupLayout;
import com.shushang.aishangjia.Bean.ActionCustomersBean;
import com.shushang.aishangjia.Bean.ActivityBean;
import com.shushang.aishangjia.Bean.ActivityListNew;
import com.shushang.aishangjia.Bean.CustomersBean;
import com.shushang.aishangjia.Bean.MerchantsBean;
import com.shushang.aishangjia.Bean.Response;
import com.shushang.aishangjia.Bean.RoleType;
import com.shushang.aishangjia.Bean.UserData;
import com.shushang.aishangjia.Bean.info;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.AppPeopleActivity;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.activity.NoOnLineActivity;
import com.shushang.aishangjia.activity.ProActivityActivity2;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.fragment.ActivityFragment.adapter.ActivityFragmentAdapter;
import com.shushang.aishangjia.fragment.ActivityFragment.adapter.TabRecyclerViewAdapter3;
import com.shushang.aishangjia.fragment.ActivityFragment.adapter.TabRecyclerViewAdapter4;
import com.shushang.aishangjia.fragment.DaiDanTongjiFragment.DaiDanTongjiFragment;
import com.shushang.aishangjia.fragment.HuodongDingjinFragment.HoudongDingjinFragment;
import com.shushang.aishangjia.fragment.ShouKaFragment.ShouKaFragment;
import com.shushang.aishangjia.greendao.ActionCustomersBeanDao;
import com.shushang.aishangjia.greendao.ActivityBeanDao;
import com.shushang.aishangjia.greendao.CustomersBeanDao;
import com.shushang.aishangjia.greendao.DaoMaster;
import com.shushang.aishangjia.greendao.DaoSession;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.ui.ExtAlertDialog;
import com.shushang.aishangjia.utils.OkhttpUtils.CallBackUtil;
import com.shushang.aishangjia.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.ui.dialog.ActionSheetDialog;
import com.xys.libzxing.zxing.utils.JSONUtil;
import com.zaaach.toprightmenu.MenuItem;
import com.zaaach.toprightmenu.TopRightMenu;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class ActivityFragment extends BaseFragment {

    private TopRightMenu mTopRightMenu;
    private RelativeLayout mLinearLayout;
    private LinearLayout mLinearLayout2;
    private ImageView mImageView1,mImageView2,mImageView3,mImageView4,mImageView5;
    Toolbar mToolbar;
    ViewPager mViewpager;
    private RecyclerView mRecyclerView;
    private String type;
    private int height,xoff;
    private boolean isFirst=true;
    private String token_id = null;
    private TextView mTitle,mTextView2;
    private TabRecyclerViewAdapter3 tabRecyclerViewAdapter;
    private TabRecyclerViewAdapter4 mTabRecyclerViewAdapter;
    private static final int REQUEST_CODE_SCAN= 2005;
    private static final int REQUEST_CODE_HUODONG= 2006;
    private static final int REQUEST_CODE_DINGJIN= 2007;
    private static final int REQUEST_CODE_HUODONG_XUANZE= 1007;
    private List<ActivityListNew.DataListBean> dataList=new ArrayList<>();
    private List<MerchantsBean.DataListBean> merchants=new ArrayList<>();
    private String activityId,activityId2,activityName,merchantsId,activityCode,isUnderLine,isCheck;
    private String roleType;
    private Dialog mRequestDialog;
    private  List<ActionCustomersBean> actionCustomers=new ArrayList<>();
    private List<CustomersBean> customers=new ArrayList<>();
    private ActivityBean mActivityBean;
    private ActionCustomersBeanDao actionCustomersBeanDao;
    private CustomersBeanDao customersBeanDao;
    private ActivityBeanDao activityBeanDao;
    private static DaoSession daoSession;
//    private List<ActivityList.DataListBean> dataList=new ArrayList<>();
    private List<ActionCustomersBean> actionCustomers3=new ArrayList<>();
    private List<info> mInfos=new ArrayList<>();
    private String infos;
    private String sync_time;
    private String merchant_name;
    private String url;
    private RecyclerView mRecyclerView2;
    private View mView;
    private ImageView tabTextView,mImageView;
    private PopupLayout popupLayout;
    private String openWxPay=null;
    private String openZfbPay=null;
    private boolean useRadius=true;//是否使用圆角特性
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        SetUpViewPager(mViewpager);
        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MessageEvent("获取所有商户信息"));
                mTextView2.setTextColor(getResources().getColor(R.color.colorPrimary));
                tabRecyclerViewAdapter.setThisPosition(100);
                tabRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View initView() {
        PreferencesUtils.putString(mContext,"huodongactivityId","");
        PreferencesUtils.putString(mContext,"huodongmerchantId","");
        PreferencesUtils.putString(mContext,"syyroleType","");
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        if(screenWidth<=1000){
            xoff=-150;
        }
        else {
            xoff=-180;
        }
        View view = View.inflate(mContext, R.layout.activity_activity_detail_new, null);
        mToolbar=view.findViewById(R.id.toolbar);
        mTitle=view.findViewById(R.id.title_name);
        mTextView2=view.findViewById(R.id.all);
        mViewpager=view.findViewById(R.id.activity_viewpager);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(getActivity(), getString(R.string.request));
        mRequestDialog.setCancelable(false);
        mRecyclerView=view.findViewById(R.id.rl_tab);
        mLinearLayout=view.findViewById(R.id.more_menu);
        mLinearLayout2=view.findViewById(R.id.scan_ll);
        mImageView1=view.findViewById(R.id.huodong_shouka);
        mImageView1.setSelected(true);
        type=PreferencesUtils.getString(getActivity(),"type");
        mImageView=view.findViewById(R.id.more);
        mView=View.inflate(getActivity(),R.layout.tablist,null);
        tabTextView=mView.findViewById(R.id.quit_item);
        mRecyclerView2=mView.findViewById(R.id.rv_sign2);
        popupLayout=PopupLayout.init(getActivity(),mView);
        popupLayout.setUseRadius(useRadius);
        popupLayout.setHeight(850,true);//手动设置弹出布局的高度
        mImageView4=view.findViewById(R.id.select_activity);
        mImageView2=view.findViewById(R.id.huodong_dingjin);
        mImageView3=view.findViewById(R.id.daidan_tongji);
        mImageView5=view.findViewById(R.id.plus);
        tabTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(popupLayout!=null){
                    popupLayout.dismiss();
                }
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupLayout.show();//默认从底部弹出
            }
        });
        if(type.equals("5")){
            mImageView5.setVisibility(View.GONE);
            mLinearLayout2.setVisibility(View.VISIBLE);
        }
        else {
            mImageView5.setVisibility(View.VISIBLE);
            mLinearLayout2.setVisibility(View.GONE);
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

        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type.equals("5")){
                    if(roleType==null){
                        ToastUtils.showLong("请先设置活动相关人员，再进行操作");
                    }
                    else if(roleType.equals("0")){
                        new ActionSheetDialog(getActivity())
                                .builder()
                                .setCancelable(false)
                                .setCanceledOnTouchOutside(true)
                                .addSheetItem("在线签到", ActionSheetDialog.SheetItemColor.Blue,
                                        new ActionSheetDialog.OnSheetItemClickListener() {
                                            @Override
                                            public void onClick(int which) {
                                                Intent intent=new Intent(getActivity(), CaptureActivity.class);
                                                intent.putExtra("type",roleType);
                                                if(activityId2==null){
                                                    intent.putExtra("activityId",activityId);
                                                }
                                                else {
                                                    intent.putExtra("activityId",activityId2);
                                                }
                                                startActivityForResult(intent,REQUEST_CODE_SCAN);
                                            }
                                        })
                                .addSheetItem("离线签到", ActionSheetDialog.SheetItemColor.Blue,
                                        new ActionSheetDialog.OnSheetItemClickListener() {
                                            @Override
                                            public void onClick(int which) {
                                                ExtAlertDialog.showSureDlg(getActivity(), "进入离线模式", getString(R.string.message), getString(R.string.sure), new ExtAlertDialog.IExtDlgClick() {
                                                    @Override
                                                    public void Oclick(int result) {
                                                        if(result==1){
                                                            sync_time=PreferencesUtils.getString(getActivity(),"dataSync");
                                                            mRequestDialog.show();
                                                            new Handler().postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    getUserData2();
                                                                }
                                                            },1000);
                                                        }
                                                    }
                                                });
                                            }
                                        })
                                .show();
                    }
                    else if(roleType.equals("1")){


                        new ActionSheetDialog(getActivity())
                                .builder()
                                .setCancelable(false)
                                .setCanceledOnTouchOutside(true)
                                .addSheetItem("收银", ActionSheetDialog.SheetItemColor.Blue,
                                        new ActionSheetDialog.OnSheetItemClickListener() {
                                            @Override
                                            public void onClick(int which) {
                                                Intent intent=new Intent(getActivity(), CaptureActivity.class);
                                                intent.putExtra("type",roleType);
                                                if(activityId2==null){
                                                    intent.putExtra("activityId",activityId);
                                                }
                                                else {
                                                    intent.putExtra("activityId",activityId2);
                                                }
                                                startActivityForResult(intent,REQUEST_CODE_SCAN);
                                            }
                                        })
                                .addSheetItem("对账", ActionSheetDialog.SheetItemColor.Blue,
                                        new ActionSheetDialog.OnSheetItemClickListener() {
                                            @Override
                                            public void onClick(int which) {
                                                Intent intent=new Intent(getActivity(), CaptureActivity.class);
                                                intent.putExtra("type","11");
                                                if(activityId2==null){
                                                    intent.putExtra("activityId",activityId);
                                                }
                                                else {
                                                    intent.putExtra("activityId",activityId2);
                                                }
                                                startActivityForResult(intent,REQUEST_CODE_SCAN);
                                            }
                                        })
                                .show();
                    }
                    else if(roleType.equals("2")){
                        Intent intent=new Intent(getActivity(), CaptureActivity.class);
                        intent.putExtra("type",roleType);
                        if(activityId2==null){
                            intent.putExtra("activityId",activityId);
                        }
                        else {
                            intent.putExtra("activityId",activityId2);
                        }
                        startActivityForResult(intent,REQUEST_CODE_SCAN);
                    }
                }

                else {
                    mTopRightMenu = new TopRightMenu(getActivity());
                    List<MenuItem> menuItems = new ArrayList<>();
                    if(type.equals("5")){
                        menuItems.add(new MenuItem(R.mipmap.qr_scan, "扫一扫"));
                    }
                    menuItems.add(new MenuItem(R.mipmap.huodng_add, "活动拓客"));
                    menuItems.add(new MenuItem(R.mipmap.huodong_mony, "活动订金"));
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
//                                if(type.equals("5")){
//                                    if(position==0){
//                                        if(roleType==null){
//                                            ToastUtils.showLong("请先设置活动相关人员，在进行操作");
//                                        }
//                                        else if(roleType.equals("0")){
//                                            new ActionSheetDialog(getActivity())
//                                                    .builder()
//                                                    .setCancelable(false)
//                                                    .setCanceledOnTouchOutside(true)
//                                                    .addSheetItem("在线签到", ActionSheetDialog.SheetItemColor.Blue,
//                                                            new ActionSheetDialog.OnSheetItemClickListener() {
//                                                                @Override
//                                                                public void onClick(int which) {
//                                                                    Intent intent=new Intent(getActivity(), CaptureActivity.class);
//                                                                    intent.putExtra("type",roleType);
//                                                                    if(activityId2==null){
//                                                                        intent.putExtra("activityId",activityId);
//                                                                    }
//                                                                    else {
//                                                                        intent.putExtra("activityId",activityId2);
//                                                                    }
//                                                                    startActivityForResult(intent,REQUEST_CODE_SCAN);
//                                                                }
//                                                            })
//                                                    .addSheetItem("离线签到", ActionSheetDialog.SheetItemColor.Blue,
//                                                            new ActionSheetDialog.OnSheetItemClickListener() {
//                                                                @Override
//                                                                public void onClick(int which) {
//                                                                    ExtAlertDialog.showSureDlg(getActivity(), "进入离线模式", getString(R.string.message), getString(R.string.sure), new ExtAlertDialog.IExtDlgClick() {
//                                                                        @Override
//                                                                        public void Oclick(int result) {
//                                                                            if(result==1){
//                                                                                sync_time=PreferencesUtils.getString(getActivity(),"dataSync");
//                                                                                mRequestDialog.show();
//                                                                                new Handler().postDelayed(new Runnable() {
//                                                                                    @Override
//                                                                                    public void run() {
//                                                                                        getUserData2();
//                                                                                    }
//                                                                                },1000);
//                                                                            }
//                                                                        }
//                                                                    });
//                                                                }
//                                                            })
//                                                    .show();
//                                        }
//                                        else if(roleType.equals("1")){
//                                            Intent intent=new Intent(getActivity(), CaptureActivity.class);
//                                            intent.putExtra("type",roleType);
//                                            startActivityForResult(intent,REQUEST_CODE_SCAN);
//                                        }
//                                        else if(roleType.equals("2")){
//                                            Intent intent=new Intent(getActivity(), CaptureActivity.class);
//                                            intent.putExtra("type",roleType);
//                                            startActivityForResult(intent,REQUEST_CODE_SCAN);
//                                        }
//                                    }
//                                    else if(position==1){
//                                        Intent intent=new Intent(getActivity(), AppPeopleActivity.class);
//                                        if(activityId2==null){
//                                            intent.putExtra("activityId",activityId);
//                                        }
//                                        else {
//                                            intent.putExtra("activityId",activityId2);
//                                        }
//                                        intent.putExtra("activityName",activityName);
//                                        intent.putExtra("activityCode",activityCode);
//                                        intent.putExtra("isUnderLine",isUnderLine);
//                                        intent.putExtra("isCheck",isCheck);
//                                        startActivityForResult(intent,REQUEST_CODE_HUODONG);
//                                    }
//                                    else {
//                                        //表示所有权限都授权了
//                                        Intent openCameraIntent = new Intent(getActivity(), CaptureActivity.class);
//                                        if(activityId2==null){
//                                            openCameraIntent.putExtra("activityId",activityId);
//                                        }
//                                        else {
//                                            openCameraIntent.putExtra("activityId",activityId2);
//                                        }
//                                        openCameraIntent.putExtra("type", "3");
////                                        openCameraIntent.putExtra("event","6");
//                                        startActivityForResult(openCameraIntent, REQUEST_CODE_DINGJIN );
//                                    }
//                                }
//                                else {
                                    if(position==0){
                                        Intent intent=new Intent(getActivity(), AppPeopleActivity.class);
                                        if(activityId2==null){
                                            intent.putExtra("activityId",activityId);
                                        }
                                        else {
                                            intent.putExtra("activityId",activityId2);
                                        }
                                        intent.putExtra("activityName",activityName);
                                        intent.putExtra("activityCode",activityCode);
                                        intent.putExtra("isUnderLine",isUnderLine);
                                        intent.putExtra("isCheck",isCheck);
                                        startActivityForResult(intent,REQUEST_CODE_HUODONG);
                                    }
                                    else if(position==1){
                                        //表示所有权限都授权了
                                        Intent openCameraIntent = new Intent(getActivity(), CaptureActivity.class);
                                        if(activityId2==null){
                                            openCameraIntent.putExtra("activityId",activityId);
                                        }
                                        else {
                                            openCameraIntent.putExtra("activityId",activityId2);
                                        }
                                        openCameraIntent.putExtra("type", "3");
//                                        openCameraIntent.putExtra("event","6");
                                        startActivityForResult(openCameraIntent, REQUEST_CODE_DINGJIN );
                                    }
                                }
//                            }
                            })
                            .showAsDropDown(mLinearLayout, xoff, 0);
//                        .showAsDropDown(moreBtn);
                }

            }
        });

        mImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), ProActivityActivity2.class),REQUEST_CODE_HUODONG_XUANZE);
            }
        });

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

    private void getUserData2() {
        String token_id= PreferencesUtils.getString(mContext,"token_id");
        if(activityId2!=null){
             url= BaseUrl.BASE_URL+"phoneApi/outLineController.do?method=downloadData&token_id="+token_id+"&activityId="+activityId2+"&time=";

        }
        else {
             url= BaseUrl.BASE_URL+"phoneApi/outLineController.do?method=downloadData&token_id="+token_id+"&activityId="+activityId+"&time=";

        }
        Log.d("userDaata",activityId2+"===="+url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            try {
                                if(response!=null){
                                    try {
                                        UserData userData = com.shushang.aishangjia.utils.Json.JSONUtil.fromJson(response, UserData.class);
                                        if (userData.getRet().equals("101")) {
                                            Toast.makeText(mContext, ""+userData.getMsg(), Toast.LENGTH_SHORT).show();
                                            PreferencesUtils.putString(mContext, "token_id", null);
                                            startActivity(new Intent(getActivity(), LoginActivity2.class));
                                            getActivity().finish();
                                        } else {
                                            if (userData.getRet().equals("200")) {
                                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                                    mRequestDialog.dismiss();
                                                }
                                                Log.d("用户数据",response);
                                                actionCustomers = userData.getData().getActionCustomers();
                                                customers = userData.getData().getCustomers();
                                                mActivityBean=userData.getData().getActivity();
                                                if(customers.size()!=0&&mActivityBean!=null&&actionCustomers.size()!=0){
                                                    insertInToDataBase2(actionCustomers,customers,mActivityBean);
                                                }
                                                else {
                                                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                                        mRequestDialog.dismiss();
                                                    }
                                                    ToastUtils.showLong("暂无活动签到数据");
                                                }
                                            }
                                            else if(userData.getRet().equals("201")){
                                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                                    mRequestDialog.dismiss();
                                                }
                                                Toast.makeText(mContext, ""+userData.getMsg(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                    catch (Exception e){
                                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                            mRequestDialog.dismiss();
                                        }
                                        ToastUtils.showLong(""+e);

                                    }
                                }
                            }
                            catch (Exception e){
                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                    mRequestDialog.dismiss();
                                }
                                ToastUtils.showLong(""+e);

                            }

                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                mRequestDialog.dismiss();
                            }
                            if(sync_time!=null&&!sync_time.equals("")){
                                ExtAlertDialog.showSureDlg(getActivity(), "提醒","本次数据未同步成功,本地数据库会以您上次同步的数据为基准," +
                                        "您上次同步的时间为："+sync_time+",您是否确定继续进入离线模式(如您需要同步,请先检查网络情况是否良好,如仍未解决,请联系客服", getString(R.string.sure), new ExtAlertDialog.IExtDlgClick() {
                                    @Override
                                    public void Oclick(int result) {
                                        if(result==1){
                                            Intent openCameraIntent = new Intent(getActivity(), NoOnLineActivity.class);
                                            startActivity(openCameraIntent);
                                        }
                                    }
                                });
                            }
                            else {
                                try {
                                    ToastUtils.showLong(sync_time);
                                }
                                catch (Exception e){
                                    ToastUtils.showLong("您当前无法进入离线模式，请先连接到网络，同步数据后再进入");
                                }
                            }

                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                mRequestDialog.dismiss();
                            }
                            if(sync_time!=null&&!sync_time.equals("")){
                                ExtAlertDialog.showSureDlg(getActivity(), "提醒","本次数据未同步成功,本地数据库会以您上次同步的数据为基准," +
                                        "您上次同步的时间为："+sync_time+",您是否确定继续进入离线模式(如您需要同步,请先检查网络情况是否良好,如仍未解决,请联系客服", getString(R.string.sure), new ExtAlertDialog.IExtDlgClick() {
                                    @Override
                                    public void Oclick(int result) {
                                        if(result==1){
                                            Intent openCameraIntent = new Intent(getActivity(), NoOnLineActivity.class);
                                            startActivity(openCameraIntent);
                                        }
                                    }
                                });
                            }
                            else {
                                try {
                                    ToastUtils.showLong(sync_time);
                                }
                                catch (Exception e){
                                    ToastUtils.showLong("您当前无法进入离线模式，请先同步数据后再进入");
                                }
                            }
                        }
                    })
                    .build()
                    .get();
        }
        catch (Exception e){
            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                mRequestDialog.dismiss();
            }
            ToastUtils.showLong(""+e);
        }
    }

    private void insertInToDataBase2(List<ActionCustomersBean> actionCustomers, List<CustomersBean> customers, ActivityBean activityBean) {
        try {
//            mRequestDialog.show();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            PreferencesUtils.putString(getActivity(),"dataSync",df.format(new Date())+"");
            actionCustomersBeanDao = MyApplication.getDaoInstant().getActionCustomersBeanDao();
            customersBeanDao = MyApplication.getDaoInstant().getCustomersBeanDao();
            activityBeanDao = MyApplication.getDaoInstant().getActivityBeanDao();
            activityBeanDao.insert(activityBean);
            actionCustomersBeanDao.deleteAll();
            customersBeanDao.deleteAll();
            activityBeanDao.deleteAll();
            activityBeanDao.insert(activityBean);
            if(actionCustomers !=null&& actionCustomers.size()>0) {
                for (int i = 0; i< actionCustomers.size(); i++){
                    ActionCustomersBean actionCustomersBean = actionCustomers.get(i);
                    actionCustomersBeanDao.insert(actionCustomersBean);
                }
            }


            if(customers !=null&& customers.size()>0) {
                customersBeanDao.insertInTx(customers);
//                for (int i = 0; i< customers.size(); i++){
//                    CustomersBean customersBean = customers.get(i);
//                    customersBeanDao.insert(customersBean);
//                }
            }

            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                mRequestDialog.dismiss();
            }

            Intent openCameraIntent = new Intent(getActivity(), NoOnLineActivity.class);
            if(activityId2==null){
                openCameraIntent.putExtra("activityId",activityId);
            }
            else {
                openCameraIntent.putExtra("activityId",activityId2);
            }
            startActivity(openCameraIntent);
        }
        catch (Exception e){
            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                mRequestDialog.dismiss();
            }
            ToastUtils.showLong(e.toString());
            Log.d("数据库错误",e.toString());
        }
    }


    @Override
    public void initData() {
        super.initData();
        getData();
        setUpDataBase();
        List<ActionCustomersBean> actionCustomersBeans = actionCustomersBeanDao.loadAll();
        getUpdateInfo(actionCustomersBeans);
    }

    private void getData() {
//        if(roleType==null){
//            mRequestDialog.show();
//        }
        String url = BaseUrl.BASE_URL+"activityController.do?method=getActivity&token_id=" + token_id+"&loginOS=2";
        Log.d("BaseUrl", url);
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
                                try {
                                    ActivityListNew activityList = JSONUtil.fromJson(response, ActivityListNew.class);
                                    if(activityList.getRet().equals("200")){
                                        List<ActivityListNew.DataListBean> dataList = activityList.getDataList();
                                        if(dataList.size()!=0){
                                            activityId = dataList.get(0).getActivityId();
                                            activityName=dataList.get(0).getActivityName();
                                            activityCode=dataList.get(0).getActivityCode()+"";
                                            isCheck=dataList.get(0).getIsCheck();
                                            isUnderLine=dataList.get(0).getIsUnderLine();
                                            openWxPay=String.valueOf(dataList.get(0).getOpenWxPay());
                                            openZfbPay=String.valueOf(dataList.get(0).getOpenZfbPay());
                                            PreferencesUtils.putString(mContext,"openWxPay",openWxPay);
                                            PreferencesUtils.putString(mContext,"openZfbPay",openZfbPay);
                                            getMerchats(activityId);
                                            mTitle.setText(activityList.getDataList().get(0).getActivityName());
                                            getRoleType(activityId);
                                            PreferencesUtils.putString(mContext,"huodongactivityId",activityId);
                                            EventBus.getDefault().post(new MessageEvent("获取所有商户信息"));
                                        }
                                        else {

                                        }
                                        isFirst=false;
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
                    Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
                }
            })
                    .build()
                    .get();
        }
        catch (Exception e){
            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                mRequestDialog.dismiss();
            }
            ToastUtils.showLong("获取数据错误了");
        }
    }

    private void getMerchats(String activityId) {
        String url = BaseUrl.BASE_URL+"merchantController.do?method=getMerchantsByActivityId&token_id=" + token_id+"&loginOS=2&activityId="+activityId;
        Log.d("getMerchats", url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            if(response!=null){
                                try {
                                    MerchantsBean activityList = JSONUtil.fromJson(response, MerchantsBean.class);
                                    if(activityList.getRet().equals("200")){
                                        merchants= activityList.getDataList();
                                        if(merchants!=null){
                                            if(merchants.size()>10){
                                                mImageView.setVisibility(View.VISIBLE);
                                                showTabData(merchants);
                                                showTabData2(merchants);
                                            }
                                            else {
                                                mImageView.setVisibility(View.GONE);
                                                showTabData(merchants);
                                            }
                                        }
                                        else {
                                            merchant_name=PreferencesUtils.getString(getActivity(),"shangjia_name");
                                            showTabData(merchants);
                                        }
                                        isFirst=false;
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
                    Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
                }
            })
                    .build()
                    .get();
        }
        catch (Exception e){
            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                mRequestDialog.dismiss();
            }
            ToastUtils.showLong("获取数据错误了");
        }
    }

    private void showTabData2(List<MerchantsBean.DataListBean> merchants) {
        mTabRecyclerViewAdapter=new TabRecyclerViewAdapter4(R.layout.tab_items2,merchants);
        tabRecyclerViewAdapter.setThisPosition(100);
        initRecyclerView2(tabRecyclerViewAdapter);
    }

    private void initRecyclerView2(final TabRecyclerViewAdapter3 tabRecyclerViewAdapter) {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        mRecyclerView2.setLayoutManager(gridLayoutManager);
        mRecyclerView2.setAdapter(mTabRecyclerViewAdapter);
        mTabRecyclerViewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                mTitle.setTextColor(getResources().getColor(R.color.white));
                tabRecyclerViewAdapter.setThisPosition(position);
                tabRecyclerViewAdapter.notifyDataSetChanged();
                mRecyclerView.scrollToPosition(position);
                merchantsId=merchants.get(position).getMerchantId();
                Log.d("merchantsId",merchantsId);
                PreferencesUtils.putString(mContext,"huodongmerchantId",merchantsId);
                EventBus.getDefault().post(new MessageEvent("获取商户信息"));
                popupLayout.dismiss();

            }
        });
    }

    private void getRoleType(String activityId) {
        String url = BaseUrl.BASE_URL+"activityController.do?method=getRoleAtAcitvity&token_id="+token_id+"&loginOS=2&activityId="+activityId;
        Log.d("getRoleType", url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            if(response!=null){
                                try {
                                    RoleType activityList = JSONUtil.fromJson(response, RoleType.class);
                                    if(activityList.getRet().equals("200")){
                                        String data = activityList.getData();
                                        if(data!=null){
                                            switch (data){
                                                case "签到员":
                                                    roleType="0";
                                                    //这里进行数据库上传下载
                                                    UpDate();
                                                    break;
                                                case "收银员":
                                                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                                        mRequestDialog.dismiss();
                                                    }
                                                    roleType="1";
                                                    PreferencesUtils.putString(getActivity(),"syyroleType",roleType);
                                                    Log.d("roleType",roleType);
                                                    break;
                                                case "礼品发放员":
                                                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                                        mRequestDialog.dismiss();
                                                    }
                                                    roleType="2";
                                                    break;
                                            }
                                        }
                                        else {
                                            roleType=null;
                                            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                                mRequestDialog.dismiss();
                                            }
                                            if(type.equals("5")){
                                                ToastUtils.showLong("你不是该场活动的工作人员");
                                            }
                                        }
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
                    Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
                }
            })
                    .build()
                    .get();
        }
        catch (Exception e){
            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                mRequestDialog.dismiss();
            }
            ToastUtils.showLong("获取数据错误了");
        }
    }

    private void showTabData(List<MerchantsBean.DataListBean> merchants) {
        tabRecyclerViewAdapter=new TabRecyclerViewAdapter3(R.layout.tab_items,merchants);
        if(merchants!=null&&merchants.size()>1){
            mTextView2.setText("全部");
            tabRecyclerViewAdapter.setThisPosition(100);
        }
        else {
            mTextView2.setText(merchant_name);
            Log.d("bianse","colorPrimary");
            tabRecyclerViewAdapter.setThisPosition(100);
        }
        initRecyclerView(tabRecyclerViewAdapter);
    }

    private void initRecyclerView(final TabRecyclerViewAdapter3 tabRecyclerViewAdapter) {
        final LinearLayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(tabRecyclerViewAdapter);
        tabRecyclerViewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTextView2.setTextColor(getResources().getColor(R.color.darker_gray));
                tabRecyclerViewAdapter.setThisPosition(position);
                tabRecyclerViewAdapter.notifyDataSetChanged();
                mRecyclerView.scrollToPosition(position);
                merchantsId=merchants.get(position).getMerchantId();
                Log.d("merchantsId",merchantsId);
                PreferencesUtils.putString(mContext,"huodongmerchantId",merchantsId);
                EventBus.getDefault().post(new MessageEvent("获取商户信息"));
            }
        });

    }

    private void SetUpViewPager(ViewPager bookViewpager) {
        ActivityFragmentAdapter adapter = new ActivityFragmentAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(ShouKaFragment.newInstance(), "");
        adapter.addFragment(HoudongDingjinFragment.newInstance(), "");
        adapter.addFragment(DaiDanTongjiFragment.newInstance(), "");
        bookViewpager.setAdapter(adapter);
        bookViewpager.setCurrentItem(0, true);
        bookViewpager.setOffscreenPageLimit(3);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_HUODONG_XUANZE&&resultCode==1007){
            mRequestDialog.show();
            mTextView2.setTextColor(getResources().getColor(R.color.colorPrimary));
            activityId2=data.getStringExtra("activityId");
            Log.d("IDD",activityId2);
            activityCode=data.getStringExtra("activityCode");
            activityName=data.getStringExtra("activityName");
            isCheck=data.getStringExtra("isCheck");
            isUnderLine=data.getStringExtra("isUnderLine");
            openWxPay=String.valueOf(data.getStringExtra("openWxPay"));
            openZfbPay=String.valueOf(data.getStringExtra("openZfbPay"));
            PreferencesUtils.putString(mContext,"openWxPay",openWxPay);
            PreferencesUtils.putString(mContext,"openZfbPay",openZfbPay);
            mTitle.setText(data.getStringExtra("activityName"));
            getMerchats(activityId2);
            getRoleType(activityId2);
            PreferencesUtils.putString(mContext,"huodongactivityId",activityId2);
            PreferencesUtils.putString(mContext,"huodongmerchantId",merchantsId);
            EventBus.getDefault().post(new MessageEvent("获取所有商户信息"));
        }
        else if(requestCode==REQUEST_CODE_HUODONG){
            EventBus.getDefault().post(new MessageEvent("活动拓客"));
        }
        else if(requestCode==REQUEST_CODE_DINGJIN){
            EventBus.getDefault().post(new MessageEvent("活动订金"));
        }
        else if(requestCode==REQUEST_CODE_SCAN){
            EventBus.getDefault().post(new MessageEvent("活动拓客"));
            EventBus.getDefault().post(new MessageEvent("活动订金"));
        }
    }




    private void UpDate() {
        if(infos==null||infos.equals("")){
//            mRequestDialog.show();
            getUserData();
        }
        else {
//            mRequestDialog.show();
            String url = BaseUrl.BASE_URL+"phoneApi/outLineController.do?method=uploadData&token_id="+token_id;
            HashMap<String, String> paramsMap = new HashMap<>();
            paramsMap.put("info", infos);
            OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
                @Override
                public void onFailure(Call call, Exception e) {
                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                        mRequestDialog.dismiss();
                    }
                    ToastUtils.showLong("当前网络链接有问题，同步数据库失败，无法退出离线模式");
                }

                @Override
                public void onResponse(String response) {
                    Log.d("创建活动",response);
                    if(response!=null){
                        try {
                            Response response1 = com.shushang.aishangjia.utils.Json.JSONUtil.fromJson(response, Response.class);
                            if(response1.getRet().equals("200")){
                                getUserData();
                            }
                            else if(response1.getRet().equals("201")){
                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                    mRequestDialog.dismiss();
                                }
                                Toast.makeText(getActivity(), ""+response1.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                            else if (response1.getRet().equals("101")) {
                                Toast.makeText(getActivity(), ""+response1.getMsg(), Toast.LENGTH_SHORT).show();
                                com.xys.libzxing.zxing.utils.PreferencesUtils.putString(getActivity(), "token_id", null);
                                startActivity(new Intent(getActivity(), LoginActivity2.class));
                                getActivity().finish();
                            }
                            else {
                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                    mRequestDialog.dismiss();
                                }
                                Toast.makeText(getActivity(), ""+response1.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception e){
                            ToastUtils.showLong(e.toString());
                        }

                    }

                }
            });

        }

    }


    private void getUpdateInfo(List<ActionCustomersBean> actionCustomersBeans) {
        if(actionCustomersBeans.size()!=0){
            try {
                for (int i=0;i<actionCustomersBeans.size();i++){
                    if(actionCustomersBeans.get(i).getQdsucess().equals("1")&&actionCustomersBeans.get(i).getIsSign()!=null){
                        actionCustomers3.add(actionCustomersBeans.get(i));
                    }
                }

                for (int i=0;i<actionCustomers3.size();i++){
                    info info=new info();
                    info.setCustomerActionId(actionCustomers3.get(i).getCustomerActionId());
                    info.setQdsj(String.valueOf(actionCustomers3.get(i).getQdsj()));
                    info.setQdsuccess(actionCustomers3.get(i).getQdsucess());
                    info.setLqsj(String.valueOf(actionCustomers3.get(i).getLqsj()));
                    info.setLqsuccess(actionCustomers3.get(i).getLqsuccess());
                    mInfos.add(info);
                }

                infos = JSONUtil.toJSON(mInfos);
                Log.d("Updateinfos",infos+"");
            }
            catch (Exception e){
                ToastUtils.showLong("操作失败，请重试");
            }
        }

    }



    private void setUpDataBase() {
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(getActivity(),"dsx.db",null);
        SQLiteDatabase db=helper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(db);
        daoSession=daoMaster.newSession();
        actionCustomersBeanDao = daoSession.getActionCustomersBeanDao();
        customersBeanDao =daoSession.getCustomersBeanDao();
        activityBeanDao=daoSession.getActivityBeanDao();
    }


    private void getUserData() {
        String token_id= com.xys.libzxing.zxing.utils.PreferencesUtils.getString(getActivity(),"token_id");
        if(activityId2!=null){
            url= BaseUrl.BASE_URL+"phoneApi/outLineController.do?method=downloadData&token_id="+token_id+"&activityId="+activityId2+"&time=";

        }
        else {
            url= BaseUrl.BASE_URL+"phoneApi/outLineController.do?method=downloadData&token_id="+token_id+"&activityId="+activityId+"&time=";

        }
        Log.d("userDaata",url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            try {
                                if(response!=null){
                                    try {
                                        UserData userData = com.shushang.aishangjia.utils.Json.JSONUtil.fromJson(response, UserData.class);
                                        if (userData.getRet().equals("101")) {
                                            Toast.makeText(getActivity(), ""+userData.getMsg(), Toast.LENGTH_SHORT).show();
                                            com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils.putString(getActivity(), "token_id", null);
                                            startActivity(new Intent(getActivity(), LoginActivity2.class));
                                            getActivity().finish();
                                        } else {
                                            if (userData.getRet().equals("200")) {
                                                actionCustomers = userData.getData().getActionCustomers();
                                                customers = userData.getData().getCustomers();
                                                mActivityBean=userData.getData().getActivity();
                                                Log.d("mActivityBean",mActivityBean.toString());
                                                if(customers.size()!=0&&mActivityBean!=null&&actionCustomers.size()!=0){
                                                    insertInToDataBase(actionCustomers,customers,mActivityBean);
                                                }
                                                else {
                                                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                                        mRequestDialog.dismiss();
                                                    }
                                                    ToastUtils.showLong("暂无活动签到数据");
                                                }
                                            }
                                            else if(userData.getRet().equals("201")){
                                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                                    mRequestDialog.dismiss();
                                                }
                                                Toast.makeText(getActivity(), ""+userData.getMsg(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                    catch (Exception e){
                                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                            mRequestDialog.dismiss();
                                        }
                                        ToastUtils.showLong(e.toString());

                                    }
                                }
                            }
                            catch (Exception e){
                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                    mRequestDialog.dismiss();
                                }
                                ToastUtils.showLong(e.toString());

                            }

                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                mRequestDialog.dismiss();
                            }
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                mRequestDialog.dismiss();
                            }
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

    private void insertInToDataBase(List<ActionCustomersBean> actionCustomers, List<CustomersBean> customers, ActivityBean activityBean) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            com.xys.libzxing.zxing.utils.PreferencesUtils.putString(getActivity(),"dataSync",df.format(new Date())+"");
            actionCustomersBeanDao = MyApplication.getDaoInstant().getActionCustomersBeanDao();
            customersBeanDao = MyApplication.getDaoInstant().getCustomersBeanDao();
            activityBeanDao = MyApplication.getDaoInstant().getActivityBeanDao();
            activityBeanDao.insert(activityBean);
            actionCustomersBeanDao.deleteAll();
            customersBeanDao.deleteAll();
            activityBeanDao.deleteAll();
            activityBeanDao.insert(activityBean);
            if(actionCustomers !=null&& actionCustomers.size()>0) {
                actionCustomersBeanDao.insertInTx(actionCustomers);
//                for (int i = 0; i< actionCustomers.size(); i++){
//                    ActionCustomersBean actionCustomersBean = actionCustomers.get(i);
//                    actionCustomersBeanDao.insert(actionCustomersBean);
//                }
            }
            if(customers !=null&& customers.size()>0) {
                customersBeanDao.insertInTx(customers);
//                for (int i = 0; i< customers.size(); i++){
//                    CustomersBean customersBean = customers.get(i);
//                    customersBeanDao.insert(customersBean);
//                }
            }
            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                mRequestDialog.dismiss();
            }
            ToastUtils.showLong("同步数据库成功");
        }
        catch (Exception e){
            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                mRequestDialog.dismiss();
            }
            ToastUtils.showLong(e.toString());
            Log.d("数据库错误",e.toString());
        }

    }


}
