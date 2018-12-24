package com.shushang.aishangjia.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shushang.aishangjia.Bean.ActivityListNew;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.adapter.ActivityAdapter;
import com.shushang.aishangjia.base.BaseActivity;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.xys.libzxing.zxing.utils.JSONUtil;
import com.xys.libzxing.zxing.utils.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.List;

public class ProActivityActivity2 extends BaseActivity {
    private RecyclerView mRecyclerView;
    private String token_id=null;
    private String activityId=null;
    private String activityCode=null;
    private String activityName=null;
    private String isUnderLine,isCheck;
    private String openWxPay=null;
    private String openZfbPay=null;
    List<ActivityListNew.DataListBean> dataList;
    private ActivityAdapter mActivityAdapter;
    private Toolbar mToolbar;
    private String type,event;
    private ProgressBar mProgressBar;
    private LinearLayout ll_nodata;
    @Override
    public int setLayout() {
        return R.layout.activity_pro_activity;
    }

    @Override
    public void init() {
        mRecyclerView= (RecyclerView) findViewById(R.id.rv_activity);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        mProgressBar=findViewById(R.id.loading);
        ll_nodata= (LinearLayout) findViewById(R.id.ll_no_data);
//        Intent intent=getIntent();
//        type=intent.getStringExtra("type");
//        Log.d("typesss",type);
//        event=intent.getStringExtra("event");
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        token_id= PreferencesUtils.getString(this,"token_id");
        initData(token_id);
        initRecyclerView();
    }

    private void initRecyclerView() {
        final LinearLayoutManager linermanager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linermanager);
    }

    private void initData(String token_id) {
        mProgressBar.setVisibility(View.VISIBLE);
        String url= BaseUrl.BASE_URL+"activityController.do?method=getActivity&token_id="+token_id+"&loginOS=2";
        Log.d("BaseUrl",url);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("活动列表",response);
                        if(response!=null){
                            try {
                                ActivityListNew activity = JSONUtil.fromJson(response, ActivityListNew.class);
                                if(activity.getRet().equals("101")){
                                    Toast.makeText(ProActivityActivity2.this, ""+activity.getMsg(), Toast.LENGTH_SHORT).show();
                                    PreferencesUtils.putString(ProActivityActivity2.this,"token_id",null);
                                    startActivity(new Intent(ProActivityActivity2.this, LoginActivity2.class));
                                    finish();
                                }
                                else if(activity.getRet().equals("200")){
                                    if( activity.getDataList()!=null){
                                        mProgressBar.setVisibility(View.GONE);
                                        dataList = activity.getDataList();
                                        if(dataList.size()==0){
                                            ll_nodata.setVisibility(View.VISIBLE);
                                        }
                                        else {
                                            showData(dataList);
                                            ll_nodata.setVisibility(View.GONE);
                                        }
                                    }
                                    else {
                                        ll_nodata.setVisibility(View.VISIBLE);
                                        mProgressBar.setVisibility(View.GONE);
                                        ToastUtils.showLong(""+activity.getMsg());
                                    }
                                }
                                else {
                                    mProgressBar.setVisibility(View.GONE);
                                    ll_nodata.setVisibility(View.VISIBLE);
                                }
                            }
                            catch (Exception e){
                                mProgressBar.setVisibility(View.GONE);
                                ll_nodata.setVisibility(View.VISIBLE);
                                ToastUtils.showLong(""+e);
                            }
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        ll_nodata.setVisibility(View.VISIBLE);
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(ProActivityActivity2.this, "获取数据错误了！！！！", Toast.LENGTH_SHORT).show();
                    }
                }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                ll_nodata.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(ProActivityActivity2.this, ""+msg, Toast.LENGTH_SHORT).show();
            }
        })
                .build()
                .get();
    }

    private void showData(final List<ActivityListNew.DataListBean> dataList) {
        mActivityAdapter=new ActivityAdapter(R.layout.item_activity_new,dataList);
        mRecyclerView.setAdapter(mActivityAdapter);
        mRecyclerView.scrollToPosition(0);
        if(dataList.size()>0){
            mActivityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    activityId=dataList.get(position).getActivityId();
                    activityName=dataList.get(position).getActivityName();
                    isUnderLine=dataList.get(position).getIsUnderLine();
                    isCheck=dataList.get(position).getIsCheck();
                    openWxPay=String.valueOf(dataList.get(position).getOpenWxPay());
                    openZfbPay=String.valueOf(dataList.get(position).getOpenZfbPay());
                    activityCode=String.valueOf(dataList.get(position).getActivityCode());
                    PreferencesUtils.putString(getApplicationContext(),"activityId",activityId);
                    PreferencesUtils.putString(getApplicationContext(),"activityName",activityName);
                    PreferencesUtils.putString(getApplicationContext(),"activityCode",activityCode);
                    Intent intent=getIntent();
                    intent.putExtra("activityId",activityId);
                    intent.putExtra("activityName",activityName);
                    intent.putExtra("activityCode",activityCode);
                    intent.putExtra("isUnderLine",isUnderLine);
                    intent.putExtra("isCheck",isCheck);
                    intent.putExtra("openWxPay",openWxPay);
                    intent.putExtra("openZfbPay",openZfbPay);
                    intent.putExtra("merchantsList", (Serializable) dataList.get(position).getMerchants());
                    setResult(1007,intent);
                    finish();
                }
            });
        }
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

    /**
     * 退出activity的动画效果不起作用，要在java代码里写
     * 复写activity的finish方法，在overridePendingTransition中写入自己的动画效果
     */
    @Override
    public void finish() {
        super.finish();
        if(event!=null&&event.equals("6")){
            LogUtils.d("777");
            EventBus.getDefault().post(new MessageEvent(""));
        }
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==110){
            EventBus.getDefault().post(new MessageEvent("活动订金"));
            finish();
        }
    }
}
