package com.shushang.aishangjia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.necer.ncalendar.calendar.WeekCalendar;
import com.necer.ncalendar.listener.OnWeekCalendarChangedListener;
import com.necer.ncalendar.utils.MyLog;
import com.shushang.aishangjia.Bean.Activity;
import com.shushang.aishangjia.Bean.SignList;
import com.shushang.aishangjia.Bean.SignTabData;
import com.shushang.aishangjia.MainActivity;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.adapter.ActivityAdapter;
import com.shushang.aishangjia.base.BaseActivity;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.fragment.ScanFragment.adapter.ScanAdapter;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.ui.MyFab.SuspensionFab;
import com.shushang.aishangjia.utils.Json.JSONUtil;
import com.xys.libzxing.zxing.utils.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class MoBaiActivity extends BaseActivity implements View.OnClickListener, OnWeekCalendarChangedListener,BaseQuickAdapter.RequestLoadMoreListener,SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private WeekCalendar ncalendar;
    private RecyclerView mRecyclerView;
    private ScanAdapter mScanAdapter;
    private  List<SignList.DataListBean> dataList=new ArrayList<>();
    private TextView mTextView,mTextView1,mTextView2,mTextView3,mTextView4;
    private boolean isMounth=false;
    //    private ScanFragment mScanFragment;
    private static final int REQUEST_CODE_SIGN= 2003;
    private static final int REQUEST_CODE_ADD= 2004;
    private static final int REQUEST_CODE_ACTIVITY = 2005;
    private static final int REQUEST_CODE_DAILY = 2006;
    private static final int REQUEST_CODE_XIANSUO = 2002;
    private LocalDate time=null;
    private String token_id=null;
    private String resourceName=null;
    private View head=null;
    private MainActivity mMainActivity;
    private boolean isFirst=true;
    private int page=1;
    private boolean isClick=false;
    private SuspensionFab fabTop;
    private Handler fabHandler=new Handler();
    private Toolbar mToolbar;
    private LinearLayout ll_nodata;
    private static final int REQUEST_CODE_SCAN = 2002;
    private String activityId=null;
    private String activityCode=null;
    private String activityName=null;
    private LinearLayout mTakePhoto;
    List<Activity.DataListBean> activityData;
    private ActivityAdapter mActivityAdapter;
    private List<String> name=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token_id= PreferencesUtils.getString(MoBaiActivity.this,"token_id");
        resourceName= PreferencesUtils.getString(MoBaiActivity.this,"ResourceName");
        mSwipeRefreshLayout=findViewById(R.id.srl_sign);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView=findViewById(R.id.rv_data);
        mTextView=findViewById(R.id.this_month);
        mTakePhoto=findViewById(R.id.take_photo);
        fabTop= (SuspensionFab) findViewById(R.id.fab_top);
        ncalendar = (WeekCalendar) findViewById(R.id.weekCalendar);
        ll_nodata=findViewById(R.id.ll_no_data);
        mToolbar=findViewById(R.id.toolbar);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ncalendar.setOnWeekCalendarChangedListener(this);
        mTextView.setOnClickListener(this);
        mTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MoBaiActivity.this,SignActivity.class),REQUEST_CODE_SIGN);
            }
        });
        EventBus.getDefault().register(this);
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_scan;
    }

    @Override
    public void init() {

    }

    public void getData(final LocalDate time, final String token_id) {
        String url= BaseUrl.BASE_URL+"phoneApi/customerManager.do?method=getManagerSignins&token_id="+token_id+"&date="+time+"&page=1&rows=10&type=0";
        mSwipeRefreshLayout.setRefreshing(true);
        Log.d("6666666666666",url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            try {
                                Log.d("6666666666666",response);
                                if(response!=null){
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    SignList signList = JSONUtil.fromJson(response, SignList.class);
                                    if(signList.getRet().equals("101")){
                                        Toast.makeText(MoBaiActivity.this, ""+signList.getMsg(), Toast.LENGTH_SHORT).show();
                                        PreferencesUtils.putString(MoBaiActivity.this,"token_id",null);
                                        startActivity(new Intent(MoBaiActivity.this, LoginActivity2.class));
                                        finish();
                                    }
                                    else if(signList.getRet().equals("200")){
                                        if(signList.getDataList()!=null){
                                            dataList = signList.getDataList();
                                            getTabData(time,token_id);
                                            if(dataList.size()!=0){
                                                showTabData(dataList);
                                                ll_nodata.setVisibility(View.GONE);
                                            }
                                            else {
                                                showTabData(dataList);
                                                ll_nodata.setVisibility(View.VISIBLE);
                                            }
                                        }
                                    }
                                    else if(signList.getRet().equals("201")){
                                        Toast.makeText(MoBaiActivity.this, ""+signList.getMsg(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            catch (Exception e){

                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            mSwipeRefreshLayout.setRefreshing(false);
                            Toast.makeText(MoBaiActivity.this, "获取数据错误了！！！！", Toast.LENGTH_SHORT).show();
                        }
                    }).error(new IError() {
                @Override
                public void onError(int code, String msg) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(MoBaiActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
                }
            })
                    .build()
                    .get();
        }
        catch (Exception e){
            mSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(MoBaiActivity.this, ""+e, Toast.LENGTH_SHORT).show();
        }

    }


    private void getTabData(LocalDate time,String token_id){
        String url= BaseUrl.BASE_URL+"phoneApi/customerManager.do?method=getSigninCount&token_id="+token_id+"&date="+time+"&type=0";
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            try {
                                Log.d("wocaocaocacoacoa",response);
                                if(response!=null){
                                    SignTabData signTabData = JSONUtil.fromJson(response, SignTabData.class);
                                    if(signTabData.getRet().equals("200")){
                                        if(signTabData.getData()!=null){
                                            mTextView1.setText(String.valueOf(signTabData.getData().getTargetNum()));
                                            mTextView2.setText(String.valueOf(signTabData.getData().getDoNum()));
                                            mTextView3.setText(String.valueOf(Math.round(Float.parseFloat(signTabData.getData().getDoRate())*100))+"%");
                                            mTextView4.setText(String.valueOf(Math.round(Float.parseFloat(signTabData.getData().getEfficiencyRatio())))+"%");

                                        }
                                    }
                                    else if(signTabData.getRet().equals("201")){
                                        Toast.makeText(MoBaiActivity.this, ""+signTabData.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            catch (Exception e){
                                ToastUtils.showLong(e+"");
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(MoBaiActivity.this, "获取数据错误了！！！！", Toast.LENGTH_SHORT).show();
                        }
                    }).error(new IError() {
                @Override
                public void onError(int code, String msg) {
                    Toast.makeText(MoBaiActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
                }
            })
                    .build()
                    .get();
        }
        catch (Exception e){
            Toast.makeText(MoBaiActivity.this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    private void getTabMounthData(LocalDate time,String token_id){
        String url= BaseUrl.BASE_URL+"phoneApi/customerManager.do?method=getSigninCount&token_id="+token_id+"&date="+time+"&type=1";
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            try {
                                Log.d("wocaocaocacoacoa",response);
                                if(response!=null){
                                    SignTabData signTabData = JSONUtil.fromJson(response, SignTabData.class);
                                    if(signTabData.getRet().equals("200")){
                                        if(signTabData.getData()!=null){
                                            mTextView1.setText(String.valueOf(signTabData.getData().getTargetNum()));
                                            mTextView2.setText(String.valueOf(signTabData.getData().getDoNum()));
                                            mTextView3.setText(String.valueOf(Math.round(Float.parseFloat(signTabData.getData().getDoRate())*100))+"%");
                                            mTextView4.setText(String.valueOf(Math.round(Float.parseFloat(signTabData.getData().getEfficiencyRatio())))+"%");
                                        }
                                    }
                                    else if(signTabData.getRet().equals("201")){
                                        Toast.makeText(MoBaiActivity.this, ""+signTabData.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            catch (Exception e){
                                Toast.makeText(MoBaiActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(MoBaiActivity.this, "获取数据错误了！！！！", Toast.LENGTH_SHORT).show();
                        }
                    }).error(new IError() {
                @Override
                public void onError(int code, String msg) {
                    Toast.makeText(MoBaiActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
                }
            })
                    .build()
                    .get();
        }
        catch (Exception e){
            Toast.makeText(MoBaiActivity.this, ""+e, Toast.LENGTH_SHORT).show();
        }

    }




    private void showTabData(List<SignList.DataListBean> dataList) {
        try {
            head = View.inflate(MoBaiActivity.this, R.layout.headerview2, null);
            mTextView1=head.findViewById(R.id.num1);
            mTextView2=head.findViewById(R.id.num2);
            mTextView3=head.findViewById(R.id.num3);
            mTextView4=head.findViewById(R.id.num4);
            mScanAdapter = new ScanAdapter(R.layout.item_tongji, dataList);
            final LinearLayoutManager linermanager=new LinearLayoutManager(MoBaiActivity.this);
            mRecyclerView.setLayoutManager(linermanager);
            mScanAdapter.addHeaderView(head);
            mScanAdapter.setOnLoadMoreListener(this, mRecyclerView);
            //重复执行动画
            mScanAdapter.isFirstOnly(false);
            mRecyclerView.setAdapter(mScanAdapter);
        }
        catch (Exception e){
            ToastUtils.showLong(""+e);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        loadMore();
    }

    private void loadMore() {
        String type=null;
        if(isMounth){
            type="1";
        }else {
            type="0";
        }
        page=page+1;
        String url= BaseUrl.BASE_URL+"phoneApi/customerManager.do?method=getManagerSignins&token_id="+token_id+"&date="+time+"&page="+page+"&rows=10&type="+type;
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            try {
                                if(response!=null){
                                    SignList signList = JSONUtil.fromJson(response, SignList.class);
                                    if(signList.getRet().equals("200")){
                                        if(page>signList.getIntmaxPage()){
                                            page=1;
                                            mScanAdapter.loadMoreComplete();
                                            mScanAdapter.loadMoreEnd();
                                        }
                                        else if(signList.getDataList().size()!=0){
                                            List<SignList.DataListBean> dataList = signList.getDataList();
                                            LoadMoreData(dataList);
                                            mScanAdapter.loadMoreComplete();
                                        }
                                    }
                                    else if(signList.getDataList().size()==0){
                                        mScanAdapter.loadMoreComplete();
                                        mScanAdapter.loadMoreEnd();
                                    }
                                }
                            }
                            catch (Exception e){
                                Toast.makeText(MoBaiActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(MoBaiActivity.this, "错误了", Toast.LENGTH_SHORT).show();
                            mScanAdapter.loadMoreComplete();
                            mScanAdapter.loadMoreEnd();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Toast.makeText(MoBaiActivity.this, "错误了"+code+msg, Toast.LENGTH_SHORT).show();
                            mScanAdapter.loadMoreComplete();
                            mScanAdapter.loadMoreEnd();
                        }
                    })
                    .build()
                    .get();
        }
        catch (Exception e){
            Toast.makeText(MoBaiActivity.this, ""+e, Toast.LENGTH_SHORT).show();
        }


    }

    private void LoadMoreData(List<SignList.DataListBean> dataList) {
        if(isMounth){
            if(dataList.size()!=0){
                mScanAdapter.addData(dataList);
                mScanAdapter.loadMoreComplete();
            }
        }
        else {
            if(dataList.size()!=0){
                mScanAdapter.addData(dataList);
                mScanAdapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void onRefresh() {
        if(isMounth){
            getMounthData(time,token_id);
        }
        else {
            getData(time,token_id);
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.this_month:
                isMounth=true;
                isClick=true;
                ncalendar.toToday();
                getMounthData(time,token_id);
        }
    }

    private void getMounthData(final LocalDate time, final String token_id) {
        String url= BaseUrl.BASE_URL+"phoneApi/customerManager.do?method=getManagerSignins&token_id="+token_id+"&date="+time+"&page=1&rows=10&type=1";
        mSwipeRefreshLayout.setRefreshing(true);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Log.d("77777777",response);
                            if(response!=null){
                                isClick=false;
                                mSwipeRefreshLayout.setRefreshing(false);
                                try {
                                    SignList signList = JSONUtil.fromJson(response, SignList.class);
                                    if(signList.getRet().equals("101")){
                                        Toast.makeText(MoBaiActivity.this, ""+signList.getMsg(), Toast.LENGTH_SHORT).show();
                                        PreferencesUtils.putString(MoBaiActivity.this,"token_id",null);
                                        startActivity(new Intent(MoBaiActivity.this, LoginActivity2.class));
                                        finish();
                                    }
                                    else if(signList.getRet().equals("200")){
                                        if(signList.getDataList()!=null){
                                            dataList = signList.getDataList();
                                            getTabMounthData(time,token_id);
                                            if(dataList.size()!=0){
                                                showTabData(dataList);
                                                ll_nodata.setVisibility(View.GONE);
                                            }
                                            else {
                                                showTabData(dataList);
                                                ll_nodata.setVisibility(View.VISIBLE);
                                            }
                                        }
                                    }
                                    else if(signList.getRet().equals("201")){
                                        Toast.makeText(MoBaiActivity.this, ""+signList.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){
                                    Toast.makeText(MoBaiActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            mSwipeRefreshLayout.setRefreshing(false);
                            Toast.makeText(MoBaiActivity.this, "获取数据错误了"+msg, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            mSwipeRefreshLayout.setRefreshing(false);
                            Toast.makeText(MoBaiActivity.this, "获取数据错误了！", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build()
                    .get();
        }
        catch (Exception e){
            ToastUtils.showLong(e+"");
        }

    }



    public void setDate(View view) {
        ncalendar.setDate("2017-12-31");
    }

    public void toToday(View view) {
        ncalendar.toToday();
    }

    public void toNextPager(View view) {
        ncalendar.toNextPager();
    }

    public void toLastPager(View view) {
        ncalendar.toLastPager();
    }

    @Override
    public void onWeekCalendarChanged(LocalDate date) {
        MyLog.d("dateTime::" + date);
        time=date;
        if(!isClick){
            isMounth=false;
            getData(time,token_id);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if(messageEvent.getMessage().equals("陌拜签到")){
            if(isMounth){
                getMounthData(time,token_id);
            }
            else {
                getData(time,token_id);
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SIGN) {
            if(isMounth){
                getMounthData(time,token_id);
            }
            else {
                getData(time,token_id);
            }
        }else if(requestCode==REQUEST_CODE_ADD){

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fabHandler.removeCallbacksAndMessages(MoBaiActivity.this);
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
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
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }


}
