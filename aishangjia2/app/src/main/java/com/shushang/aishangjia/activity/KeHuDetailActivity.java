package com.shushang.aishangjia.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.shushang.aishangjia.Bean.ClueDetail;
import com.shushang.aishangjia.Bean.QuitCard;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.adapter.KeHuDetailAdapter;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseActivity;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.fragment.KeHuGenjinFragment.KeHuGenjinFragment;
import com.shushang.aishangjia.fragment.KeHuXiangXiXinXiFragment.KeHuXiangXiXinXiFragment;
import com.shushang.aishangjia.fragment.KehuDingdanxnixiFragment.KehuDingdanXInxiFragment;
import com.shushang.aishangjia.fragment.KehuDingjinFragment.KehuDingjinFragment;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.ui.ExtAlertDialog;
import com.shushang.aishangjia.utils.Json.JSONUtil;
import com.xys.libzxing.zxing.utils.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class KeHuDetailActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.loading)
    ProgressBar mLoading;
    @BindView(R.id.dingjin_pic)
    CircleImageView mDingjinPic;
    TextView mPeople;
    TextView mCoustomerAddress;
    TextView mPhone;
    @BindView(R.id.coustomer_info)
    RelativeLayout mCoustomerInfo;
    ImageView mXiangxixinxi;
    ImageView mDingdanxinxi;
    ImageView mGenjinjilu;
    ImageView mXiangxixinxi2;
    ImageView mDingdanxinxi2;
    ImageView mGenjinjilu2;
    ImageView mDingjinxinxi;
    LinearLayout mCoustomerType;
    LinearLayout mCoustomerType2;
    ViewPager mKehuxiangqingViewpager,mKehuxiangqingViewpager2;
    private String clueId;
    private String  token_id;
    private ClueDetail.DataBean data;
    private RelativeLayout mloading;
    private Button mButton1,mButton2,mButton3;
    private ClueDetail yiXiangJin;
    private static final int REQUEST_UPDATE_XIANSUO = 8012;
    private Dialog modifyPasswordOkDialog;
    private Dialog mRequestDialog;
    private String type;
    @Override
    public void init() {
        mToolbar=findViewById(R.id.toolbar);
//        mCoustomerType=findViewById(R.id.coustomer_type);
        mCoustomerType2=findViewById(R.id.coustomer_type2);
        mloading=findViewById(R.id.loading);
        mPeople=findViewById(R.id.people);
        mPhone=findViewById(R.id.phone);
        mButton1=findViewById(R.id.edit);
        mButton2=findViewById(R.id.quit_card);
        mButton3=findViewById(R.id.genjin);
//        mXiangxixinxi=findViewById(R.id.xiangxixinxi);
//        mDingdanxinxi=findViewById(R.id.dingdanxinxi);
//        mGenjinjilu=findViewById(R.id.genjinjilu);
        mXiangxixinxi2=findViewById(R.id.xiangxixinxi2);
        mDingdanxinxi2=findViewById(R.id.dingdanxinxi2);
        mGenjinjilu2=findViewById(R.id.genjinjilu2);
        mDingjinxinxi=findViewById(R.id.dingjinxinxi);
        type= PreferencesUtils.getString(KeHuDetailActivity.this,"type");
            mXiangxixinxi2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mKehuxiangqingViewpager.setCurrentItem(0);
                }
            });

            mDingjinxinxi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mKehuxiangqingViewpager.setCurrentItem(1);
                }
            });

            mDingdanxinxi2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mKehuxiangqingViewpager.setCurrentItem(2);
                }
            });

            mGenjinjilu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mKehuxiangqingViewpager.setCurrentItem(3);
                }
            });

        mCoustomerAddress=findViewById(R.id.coustomer_address);
        mKehuxiangqingViewpager=findViewById(R.id.kehuxiangqing_viewpager);
        modifyPasswordOkDialog = ExtAlertDialog.createModifyPasswordOkDialog2(KeHuDetailActivity.this);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(this, "退卡中...");
//        mButton3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1=new Intent(KeHuDetailActivity.this,NewPeopleDetailActivity.class);
//                intent1.putExtra("data",data);
//                startActivityForResult(intent1,REQUEST_UPDATE_XIANSUO);
//            }
//        });

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(KeHuDetailActivity.this,UpdateKehuActivity.class);
                startActivityForResult(intent1,REQUEST_UPDATE_XIANSUO);
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExtAlertDialog.showSureDlg(KeHuDetailActivity.this, null,"确定退卡吗？", "退卡", new ExtAlertDialog.IExtDlgClick() {
                    @Override
                    public void Oclick(int result) {
                        if (result == 1) {
                            quitCard();
                        }
                    }
                });

            }
        });

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(KeHuDetailActivity.this,UpdateXiansuoActivity.class);
                startActivityForResult(intent1,REQUEST_UPDATE_XIANSUO);
            }
        });
    }

    private void quitCard() {
        if(KeHuDetailActivity.this!=null&&!KeHuDetailActivity.this.isDestroyed()&&!KeHuDetailActivity.this.isFinishing()){
            mRequestDialog.show();
        }
        String url = BaseUrl.BASE_URL+"clueController.do?method=cancelClue&token_id=" + token_id+"&loginOS=2&ids="+clueId;
        Log.d("quitCard", url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            mloading.setVisibility(View.GONE);
//                            mSwipeRefreshLayout.setRefreshing(false);
                            if (response != null) {
                                try {
                                    QuitCard quitCard = JSONUtil.fromJson(response, QuitCard.class);
                                    if(quitCard.getRet().equals("200")){
                                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                            mRequestDialog.dismiss();
                                        }
                                        if(KeHuDetailActivity.this!=null&&!KeHuDetailActivity.this.isDestroyed()&&!KeHuDetailActivity.this.isFinishing()){
                                            modifyPasswordOkDialog.show();
                                        }
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(modifyPasswordOkDialog!=null&&modifyPasswordOkDialog.isShowing()){
                                                    modifyPasswordOkDialog.dismiss();
                                                    EventBus.getDefault().post(new MessageEvent("退卡"));
                                                    finish();
                                                }
                                            }
                                        },1000);
                                    }
                                    else if(quitCard.getRet().equals("101")){
                                        Toast.makeText(KeHuDetailActivity.this, ""+quitCard.getMsg(), Toast.LENGTH_SHORT).show();
                                        com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils.putString(KeHuDetailActivity.this,"token_id",null);
                                        startActivity(new Intent(KeHuDetailActivity.this, LoginActivity2.class));
                                        finish();
                                    }
                                    else if(quitCard.getRet().equals("201")){
                                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                            mRequestDialog.dismiss();
                                        }
                                        mloading.setVisibility(View.GONE);
                                        Toast.makeText(KeHuDetailActivity.this, ""+quitCard.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                    else if(quitCard.getRet().equals("-996")){
                                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                            mRequestDialog.dismiss();
                                        }
                                        mloading.setVisibility(View.GONE);
                                        Toast.makeText(KeHuDetailActivity.this, "您无权退卡", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){
                                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                        mRequestDialog.dismiss();
                                    }
                                    mloading.setVisibility(View.GONE);
                                    ToastUtils.showLong(e.toString());
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
                            mloading.setVisibility(View.GONE);
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                mRequestDialog.dismiss();
                            }
                            mloading.setVisibility(View.GONE);
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                        }
                    })
                    .build()
                    .get();
        }
        catch (Exception e){
            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                mRequestDialog.dismiss();
            }
            mloading.setVisibility(View.GONE);
            ToastUtils.showLong("获取数据错误了");
        }

    }

    @Override
    public int setLayout() {
        return R.layout.activity_coustomer_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mXiangxixinxi2.setSelected(true);
        SetUpViewPager(mKehuxiangqingViewpager);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        clueId=getIntent().getStringExtra("clueId");
        token_id = PreferencesUtils.getString(KeHuDetailActivity.this, "token_id");
        getData(token_id,clueId);
        mKehuxiangqingViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                if(type.equals("5")){
//                    if (position == 0) {
//                        mXiangxixinxi.setSelected(true);
//                        mDingjinxinxi.setSelected(false);
//                        mGenjinjilu.setSelected(false);
//                    } else if (position == 1) {
//                        mXiangxixinxi.setSelected(false);
//                        mDingjinxinxi.setSelected(true);
//                        mGenjinjilu.setSelected(false);
//                    } else {
//                        mXiangxixinxi.setSelected(false);
//                        mDingjinxinxi.setSelected(false);
//                        mGenjinjilu.setSelected(true);
//                    }
//                }
//                else {
                    if (position == 0) {
                        mXiangxixinxi2.setSelected(true);
                        mDingjinxinxi.setSelected(false);
                        mDingdanxinxi2.setSelected(false);
                        mGenjinjilu2.setSelected(false);
                    } else if (position == 1) {
                        mXiangxixinxi2.setSelected(false);
                        mDingjinxinxi.setSelected(true);
                        mDingdanxinxi2.setSelected(false);
                        mGenjinjilu2.setSelected(false);
                    }
                    else if(position==2){
                        mXiangxixinxi2.setSelected(false);
                        mDingjinxinxi.setSelected(false);
                        mDingdanxinxi2.setSelected(true);
                        mGenjinjilu2.setSelected(false);
                    }
                    else {
                        mXiangxixinxi2.setSelected(false);
                        mDingjinxinxi.setSelected(false);
                        mDingdanxinxi2.setSelected(false);
                        mGenjinjilu2.setSelected(true);
                    }
                }

//            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getData(String token_id, String clueId) {
        mloading.setVisibility(View.VISIBLE);
        String url = BaseUrl.BASE_URL+"clueController.do?method=getClueDetail&token_id=" + token_id+"&loginOS=2&clueId="+clueId;
        Log.d("BaseUrl", url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            mloading.setVisibility(View.GONE);
//                            mSwipeRefreshLayout.setRefreshing(false);
                            if (response != null) {
                                try {
                                     yiXiangJin = JSONUtil.fromJson(response, ClueDetail.class);
                                    if(yiXiangJin.getRet().equals("200")){
                                         data = yiXiangJin.getData();
                                        if(data!=null){
                                            PreferencesUtils.putString(KeHuDetailActivity.this, "genjinData",response);
                                            showData(data);
                                        }
                                        else {
                                            showData(data);
                                        }
                                    }
                                    else if(yiXiangJin.getRet().equals("101")){
                                        Toast.makeText(KeHuDetailActivity.this, ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                        com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils.putString(KeHuDetailActivity.this,"token_id",null);
                                        startActivity(new Intent(KeHuDetailActivity.this, LoginActivity2.class));
                                        finish();
                                    }
                                    else if(yiXiangJin.getRet().equals("201")){
                                        mloading.setVisibility(View.GONE);
                                        Toast.makeText(KeHuDetailActivity.this, ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){
                                    mloading.setVisibility(View.GONE);
                                   ToastUtils.showLong(e.toString());
                                }
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            mloading.setVisibility(View.GONE);
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            mloading.setVisibility(View.GONE);
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                        }
                    })
                    .build()
                    .get();
        }
        catch (Exception e){
            mloading.setVisibility(View.GONE);
            ToastUtils.showLong("获取数据错误了");
        }
    }

    private void showData(ClueDetail.DataBean data) {
        if(data.getName()!=null){
            mPeople.setText(data.getName());
        }

        if(data.getTelephone()!=null){
            mPhone.setText(data.getTelephone());
        }

        if(data.getAddress()!=null){
            mCoustomerAddress.setText(data.getAddress());
        }

    }


    private void SetUpViewPager(ViewPager bookViewpager) {
        KeHuDetailAdapter adapter = new KeHuDetailAdapter(getSupportFragmentManager());
        adapter.addFragment(KeHuXiangXiXinXiFragment.newInstance(), "");
//        if(type.equals("5")){
//            adapter.addFragment(KehuDingjinFragment.newInstance(), "");
//        }
//        else {
            adapter.addFragment(KehuDingjinFragment.newInstance(), "");
            adapter.addFragment(KehuDingdanXInxiFragment.newInstance(), "");
//        }
        adapter.addFragment(KeHuGenjinFragment.newInstance(), "");
        bookViewpager.setAdapter(adapter);
        bookViewpager.setCurrentItem(0, true);
        bookViewpager.setOffscreenPageLimit(3);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_UPDATE_XIANSUO){
            EventBus.getDefault().post(new MessageEvent("跟进记录"));
            getData(token_id,clueId);
        }
    }


    /**
     * 退出activity的动画效果不起作用，要在java代码里写
     * 复写activity的finish方法，在overridePendingTransition中写入自己的动画效果
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
