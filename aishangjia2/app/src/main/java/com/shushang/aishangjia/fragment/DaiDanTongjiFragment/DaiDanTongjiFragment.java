package com.shushang.aishangjia.fragment.DaiDanTongjiFragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;
import com.shushang.aishangjia.Bean.DaidanTongji;
import com.shushang.aishangjia.Bean.HuoDongTongji;
import com.shushang.aishangjia.Bean.Response;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.fragment.DaiDanTongjiFragment.adapter.DaiDanTongjiAdapter;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.ui.ExtAlertDialog;
import com.shushang.aishangjia.utils.Json.JSONUtil;
import com.shushang.aishangjia.utils.OkhttpUtils.CallBackUtil;
import com.shushang.aishangjia.utils.OkhttpUtils.OkhttpUtil;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;
import com.xys.libzxing.zxing.ui.dialog.ActionSheetDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class DaiDanTongjiFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{


    List<DaidanTongji.DataListBean> SignPeopleData = new ArrayList<>();
    List<DaidanTongji.DataListBean> SignPeopleData2 = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private DaiDanTongjiAdapter mMyCoustomerAdapter;
    private View mView;
    LinearLayout mLlNoData;
    private int page=1;
    private TextView mTextView1,mTextView2,mTextView3,mTextView4;
    private boolean isAll=false;
    private String  token_id = PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "token_id");
    private  String activity_id,merchantId;
    private String signPelpleUrl;
    private String url;
    private Button mButton1,mButton2,mButton3,mButton4;
    private EditText mEditText;
    private String type=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "type");
    private int select_year,select_mounth,select_day;
    private int now_year,now_mounth,now_day;
    private String startDate,endDate,mode,situation;
    private Dialog mRequestDialog,mRequestDialog2;
    private String Datetime,Datetime2;
    private boolean isSubmit=false;
    private boolean isFirst=true;
    public DaiDanTongjiFragment() {
        // Required empty public constructor
    }

    public static DaiDanTongjiFragment newInstance() {
        return new DaiDanTongjiFragment();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        EventBus.getDefault().register(this);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_daidanjisuan, null);
        mRecyclerView=view.findViewById(R.id.rv_daidanjisuana);
        mSwipeRefreshLayout=view.findViewById(R.id.srl_daidanjisuan);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mLlNoData=view.findViewById(R.id.ll_no_data);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(getActivity(), getString(R.string.submit));
        mRequestDialog2= ExtAlertDialog.creatRequestDialog(getActivity(), "加载中...");
        return view;
    }



    private void getTongjiData(final String activity_id, final String merchantId){
//        mRequestDialog2.show();
        isFirst=true;
            if(isAll){
                signPelpleUrl = BaseUrl.BASE_URL+"activityController.do?method=getStatisticsForActivity&token_id="+token_id+"&activityId="+ activity_id +"&loginOS=2";

            }
            else {
                signPelpleUrl = BaseUrl.BASE_URL+"activityController.do?method=getStatisticsForActivity&token_id="+token_id+"&activityId="+ activity_id +"&merchantId="+merchantId+"&loginOS=2";

            }
            mSwipeRefreshLayout.setRefreshing(true);
            Log.d("signPelpleUrl2",signPelpleUrl);
            RestClient.builder()
                    .url(signPelpleUrl)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Log.d("SignP",response);
                            mSwipeRefreshLayout.setRefreshing(false);
                            if (response != null) {
                                if(mRequestDialog2!=null&&mRequestDialog2.isShowing()){
                                    mRequestDialog2.dismiss();
                                }
                                try {
                                    HuoDongTongji yiXiangJin = JSONUtil.fromJson(response, HuoDongTongji.class);
                                    if(yiXiangJin.getRet().equals("200")){
                                        if(yiXiangJin.getData()!=null){
                                            showTongjiData(yiXiangJin);
                                            getData(activity_id,merchantId);
                                        }
                                        else {
                                            showTongjiData(yiXiangJin);
                                            getData(activity_id,merchantId);
                                        }
                                    }
                                    else if(yiXiangJin.getRet().equals("101")){
                                        if(mRequestDialog2!=null&&mRequestDialog2.isShowing()){
                                            mRequestDialog2.dismiss();
                                        }
                                        Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                        PreferencesUtils.putString(getActivity(),"token_id",null);
                                        startActivity(new Intent(getActivity(), LoginActivity2.class));
                                        getActivity().finish();
                                    }
                                    else if(yiXiangJin.getRet().equals("201")){
                                        if(mRequestDialog2!=null&&mRequestDialog2.isShowing()){
                                            mRequestDialog2.dismiss();
                                        }
                                        Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){
                                    if(mRequestDialog2!=null&&mRequestDialog2.isShowing()){
                                        mRequestDialog2.dismiss();
                                    }
                                    Log.d("出错了",e.toString());
                                }
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            mSwipeRefreshLayout.setRefreshing(false);
                            if(mRequestDialog2!=null&&mRequestDialog2.isShowing()){
                                mRequestDialog2.dismiss();
                            }
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            mSwipeRefreshLayout.setRefreshing(false);
                            if(mRequestDialog2!=null&&mRequestDialog2.isShowing()){
                                mRequestDialog2.dismiss();
                            }
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                        }
                    })
                    .build()
                    .get();


    }

    private void showTongjiData(HuoDongTongji yiXiangJin) {
        mView=View.inflate(MyApplication.getInstance().getApplicationContext(), R.layout.heardview_daidanjisuan,null);
        mTextView1=mView.findViewById(R.id.yaoqing_text);
        mTextView2=mView.findViewById(R.id.merchat_text);
        mTextView3=mView.findViewById(R.id.sign_text);
        mTextView4=mView.findViewById(R.id.duihuan_text);
        mButton1=mView.findViewById(R.id.jisuan_start_time);
        mButton2=mView.findViewById(R.id.jisuan_end_time);
        mButton3=mView.findViewById(R.id.jisuan_fencheng_fangshi);
        mEditText=mView.findViewById(R.id.jisuan_bili);
        mButton4=mView.findViewById(R.id.jisuan_btn);
        mTextView1.setText(yiXiangJin.getData().getShoukaCount());
        mTextView2.setText(yiXiangJin.getData().getMerchantCount());
        mTextView3.setText(yiXiangJin.getData().getQdCount());
        mTextView4.setText(yiXiangJin.getData().getLipinCount());

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePickerDialog=DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
                        select_year=year;
                        select_mounth=monthOfYear;
                        select_day=dayOfMonth;
                        Calendar now = Calendar.getInstance();
                        TimePickerDialog tpd = TimePickerDialog.newInstance(
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
                                        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
                                        String minuteString = minute < 10 ? "0"+minute : ""+minute;
                                        String time=select_year+"-"+select_mounth+"-"+select_day+" "+hourString+":"+minuteString+":00";
                                        Date date=new Date(select_year-1900,select_mounth,select_day,hourOfDay,minute,0);
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
                                        Log.d("time",time);
                                        Datetime=simpleDateFormat.format(date);
                                        mButton1.setText(simpleDateFormat.format(date));
                                        }
                                        },
                                now.get(Calendar.HOUR_OF_DAY),
                                now.get(Calendar.MINUTE),
                                false
                        );
                        tpd.setAccentColor(Color.parseColor("#2196F3"));
                        tpd.setTabIndicators("","");
                        tpd.show(getActivity().getFragmentManager(), "Timepickerdialog");
                    }
                },      now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.setAccentColor(getResources().getColor(R.color.colorPrimary));
                datePickerDialog.setAutoHighlight(true);
                datePickerDialog.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePickerDialog=DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
                                select_year=year;
                                select_mounth=monthOfYear;
                                select_day=dayOfMonth;

                                Calendar now = Calendar.getInstance();
                                TimePickerDialog tpd = TimePickerDialog.newInstance(
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
                                                String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
                                                String minuteString = minute < 10 ? "0"+minute : ""+minute;
                                                String time=select_year+"-"+select_mounth+"-"+select_day+" "+hourString+":"+minuteString+":00";
                                                Date date=new Date(select_year-1900,select_mounth,select_day,hourOfDay,minute,0);
                                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
                                                Log.d("time",time);
                                                Datetime2=simpleDateFormat.format(date);
                                                mButton2.setText(simpleDateFormat.format(date));
                                                }
                                                },
                                        now.get(Calendar.HOUR_OF_DAY),
                                        now.get(Calendar.MINUTE),
                                        false
                                );
                                tpd.setAccentColor(Color.parseColor("#2196F3"));
                                tpd.setTabIndicators("","");

                                tpd.show(getActivity().getFragmentManager(), "Timepickerdialog");
                                }
                                },now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.setAccentColor(getResources().getColor(R.color.colorPrimary));
                datePickerDialog.setAutoHighlight(true);
                datePickerDialog.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(getActivity())
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("不计算", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        mButton3.setText("不计算");
                                        mEditText.setEnabled(false);
                                        mode="0";
                                    }
                                })
                        .addSheetItem("每单固定金额", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        mButton3.setText("每单固定金额");
                                        mEditText.setEnabled(true);
                                        mode="1";
                                    }
                                })
                        .addSheetItem("成交金额的百分比", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        mButton3.setText("成交金额的百分比");
                                        mEditText.setEnabled(true);
                                        mode="2";
                                    }
                                })
                        .show();
            }
        });

        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDate=mButton1.getText().toString();
                endDate=mButton2.getText().toString();
                situation=mEditText.getText().toString();
//                if(Datetime==null){
//                    ToastUtils.showLong("请输入开始时间");
//                    return;
//                }
//
//                if(Datetime2==null){
//                    ToastUtils.showLong("请输入结束时间");
//                    return;
//                }

                if(mode!=null){
                    if(!mode.equals("0")){
                        if(situation==null|situation.equals("")){
                            ToastUtils.showLong("请输入佣金/比例");
                            return;
                        }
                    }
                    else {
                        situation="0";
                    }
                }
                else {
                    ToastUtils.showLong("请选择分成方式");
                    return;
                }

                if(merchantId==null){
                    merchantId="";
                }
//                if(Datetime==null|Datetime2==null|mode==null|situation==null){
//                    ToastUtils.showLong("请填写完整");
//                }
                if(mode==null|situation==null){
                    ToastUtils.showLong("请填写完整");
                }
                else {
                    submit(Datetime,Datetime2,situation,activity_id,merchantId,mode);
                }
            }
        });

    }

    private void submit(String startDate, String endDate, String situation, final String activity_id, final String merchantId,String modal) {
        isFirst=false;
        mRequestDialog.show();
        String url = BaseUrl.BASE_URL+"orderController.do?method=subOrderCountForApp&token_id="+token_id+
                "&page=1"+"&rows=10&loginOS=2"+"&activityId="+ activity_id;
        HashMap<String, String> paramsMap = new HashMap<>();
        if(startDate!=null){
            paramsMap.put("startDate", startDate);
        }
        if(endDate!=null){
            paramsMap.put("endDate", endDate);
        }
        paramsMap.put("situation", situation);
        paramsMap.put("mode", modal);
        try {
            OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
                @Override
                public void onFailure(Call call, Exception e) {
                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                        mRequestDialog.dismiss();
                    }
                    ToastUtils.showLong(e.toString());
                }
                @Override
                public void onResponse(String response) {
                    try {
                        if(response!=null){
                            Log.d("daidan",response.toString());
                            Response response1 = JSONUtil.fromJson(response, Response.class);
                            if(response1.getRet().equals("200")){
                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                    mRequestDialog.dismiss();
                                }
                                Datetime=null;
                                Datetime2=null;
                                mode=null;
                                getTongjiData2(activity_id,merchantId,response);
                            }
                            else if(response1.getRet().equals("201")){
                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                    mRequestDialog.dismiss();
                                }
                                Toast.makeText(getActivity(), ""+response1.getMsg(), Toast.LENGTH_SHORT).show();
                            }
//                            else if(){
//
//                            }
                        }
                    }
                    catch (Exception e){
                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                            mRequestDialog.dismiss();
                        }
                        ToastUtils.showLong(e.toString());
                    }

                }
            });
        }
        catch (Exception e){
            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                mRequestDialog.dismiss();
            }
            ToastUtils.showLong(e.toString());
        }

    }




    private void submit2(String startDate, String endDate, String situation, final String activity_id, final String merchantId,String modal) {
        String url = BaseUrl.BASE_URL+"orderController.do?method=subOrderCountForApp&token_id="+token_id+"&page=1"+
                "&rows=10&loginOS=2"+"&activityId="+ activity_id+"&startDate="+startDate+"&endDate="+endDate+"&situation="+situation+"&modal="+modal;
        Log.d("getRoleType", url);
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
                                    Log.d("daidan",response.toString());
                                    Response response1 = JSONUtil.fromJson(response, Response.class);
                                    if(response1.getRet().equals("200")){
                                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                            mRequestDialog.dismiss();
                                        }
                                        Datetime=null;
                                        Datetime2=null;
                                        mode=null;
                                        getTongjiData2(activity_id,merchantId,response);
                                    }
                                    else if(response1.getRet().equals("201")){
                                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                            mRequestDialog.dismiss();
                                        }
                                        Toast.makeText(getActivity(), ""+response1.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
//                            else if(){
//
//                            }
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




    private void getTongjiData2(final String activity_id, final String merchantId, final String response1) {
        if(isAll){
            signPelpleUrl = BaseUrl.BASE_URL+"activityController.do?method=getStatisticsForActivity&token_id="+token_id+"&activityId="+ activity_id +"&loginOS=2";

        }
        else {
            signPelpleUrl = BaseUrl.BASE_URL+"activityController.do?method=getStatisticsForActivity&token_id="+token_id+"&activityId="+ activity_id +"&merchantId="+merchantId+"&loginOS=2";

        }
//        mSwipeRefreshLayout.setRefreshing(true);
        Log.d("signPelpleUrl2",signPelpleUrl);
        RestClient.builder()
                .url(signPelpleUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("SignP",response);
//                        mSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            try {
                                HuoDongTongji yiXiangJin = JSONUtil.fromJson(response, HuoDongTongji.class);
                                if(yiXiangJin.getRet().equals("200")){
                                    if(yiXiangJin.getData()!=null){
                                        showTongjiData(yiXiangJin);
                                        DaidanTongji yiXiangJin2 = JSONUtil.fromJson(response1, DaidanTongji.class);
                                    if(yiXiangJin.getRet().equals("200")){
                                        SignPeopleData = yiXiangJin2.getDataList();
                                        if(SignPeopleData.size()!=0){
                                            showTabData(SignPeopleData);
                                            mLlNoData.setVisibility(View.GONE);
                                        }
                                        else {
                                            showTabData(SignPeopleData);
                                            mLlNoData.setVisibility(View.VISIBLE);
                                        }
                                    }
                                    }
                                    else {
                                        showTongjiData(yiXiangJin);

                                    }
                                }
                                else if(yiXiangJin.getRet().equals("101")){
                                    Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                    PreferencesUtils.putString(getActivity(),"token_id",null);
                                    startActivity(new Intent(getActivity(), LoginActivity2.class));
                                    getActivity().finish();
                                }
                                else if(yiXiangJin.getRet().equals("201")){
                                    Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (Exception e){
//                                mSwipeRefreshLayout.setRefreshing(false);
                                Log.d("出错了",e.toString());
                            }
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
//                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
//                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }


    private void getData(String activity_id, String merchantId) {
//        if(isAll){
//            url = BaseUrl.BASE_URL+"orderController.do?method=subOrderCountForApp&token_id="+token_id+"&page=1"+"&rows=10&loginOS=2"+"&activityId="+ activity_id;
//
//        }
//        else {
            url = BaseUrl.BASE_URL+"orderController.do?method=subOrderCountForApp&token_id="+token_id+"&page=1"+"&rows=10&loginOS=2"+"&activityId="+ activity_id;

//        }
//        mSwipeRefreshLayout.setRefreshing(true);
        Log.d("getDaidan",url);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("SignP",response);
                        mSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            try {
                                DaidanTongji yiXiangJin = JSONUtil.fromJson(response, DaidanTongji.class);
                                if(yiXiangJin.getRet().equals("200")){
                                    SignPeopleData = yiXiangJin.getDataList();
                                    if(SignPeopleData.size()!=0){
                                        showTabData(SignPeopleData);
                                        if(isFirst){
                                            mLlNoData.setVisibility(View.VISIBLE);
                                        }
                                        else {
                                            mLlNoData.setVisibility(View.GONE);
                                        }
                                    }
                                    else {
                                        showTabData(SignPeopleData);
                                        mLlNoData.setVisibility(View.VISIBLE);
                                    }
                                }
                                else if(yiXiangJin.getRet().equals("101")){
                                    Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                    PreferencesUtils.putString(getActivity(),"token_id",null);
                                    startActivity(new Intent(getActivity(), LoginActivity2.class));
                                    getActivity().finish();
                                }
                                else if(yiXiangJin.getRet().equals("201")){
                                    Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (Exception e){
                                mSwipeRefreshLayout.setRefreshing(false);
                                Log.d("出错了",e.toString());
                            }
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                })
                .build()
                .get();
    }



    private void showTabData(List<DaidanTongji.DataListBean> signPeopleData) {
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        if(isFirst){
            mMyCoustomerAdapter=new DaiDanTongjiAdapter(R.layout.item_daidantongji,SignPeopleData2);
        }
        else {
            mMyCoustomerAdapter=new DaiDanTongjiAdapter(R.layout.item_daidantongji,signPeopleData);
        }
        mRecyclerView.setAdapter(mMyCoustomerAdapter);
        mRecyclerView.scrollToPosition(0);
        mMyCoustomerAdapter.addHeaderView(mView);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if(messageEvent.getMessage().equals("活动拓客")){
//            getData();
        }
        else if(messageEvent.getMessage().equals("获取所有商户信息")){
            isAll=true;
            isFirst=true;
            activity_id=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "huodongactivityId");
            getTongjiData(activity_id, "");
        }
        else if(messageEvent.getMessage().equals("获取商户信息")){
            isAll=false;
            isFirst=true;
            activity_id=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "huodongactivityId");
            merchantId=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "huodongmerchantId");
            getTongjiData(activity_id,merchantId);
        }
        else if(messageEvent.getMessage().equals("选择活动")){
            isFirst=true;
            activity_id=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "huodongactivityId");
            merchantId=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "huodongmerchantId");
            getTongjiData(activity_id,merchantId);
        }
    }



//    @Override
//    public void onRefresh() {
//        if(isAll){
////            getTongjiData(activity_id, "");
//        }
//        else {
////            getTongjiData(activity_id, merchantId);
//        }
//
//    }

//    @Override
//    public void onLoadMoreRequested() {
//        if(isAll){
//            loadMore("");
//        }
//        else {
//            loadMore(merchantId);
//        }
//
//    }


//    private void loadMore(String merchantId) {
//        page=page+1;
//        String url = BaseUrl.BASE_URL+"orderController.do?method=subOrderCountForApp&token_id="+token_id+"&page="+page+"&rows=10&loginOS=2&merchantId="+merchantId;
//        try {
//            RestClient.builder()
//                    .url(url)
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String response) {
//                            if(response!=null) {
//                                Log.d("nnnnnnn",response);
//                                YiXiangJin yiXiangJin = JSONUtil.fromJson(response, YiXiangJin.class);
//                                if(yiXiangJin.getRet().equals("200")){
//                                    if(page>yiXiangJin.getIntmaxPage()){
//                                        page=1;
//                                        mMyCoustomerAdapter.loadMoreComplete();
//                                        mMyCoustomerAdapter.loadMoreEnd();
//                                    }
//                                    else if(yiXiangJin.getDataList().size()!=0){
//                                        List<YiXiangJin.DataListBean> dataList = yiXiangJin.getDataList();
//                                        LoadMoreData(dataList);
//                                        Log.d("load","loadMoreComplete");
//                                        mMyCoustomerAdapter.loadMoreComplete();
//                                    }
//                                    else if(yiXiangJin.getDataList().size()==0){
//                                        Log.d("load","loadMoreEnd");
//                                        mMyCoustomerAdapter.loadMoreComplete();
//                                        mMyCoustomerAdapter.loadMoreEnd();
//                                    }
//                                }
//                                else {
//                                    mMyCoustomerAdapter.loadMoreComplete();
//                                    mMyCoustomerAdapter.loadMoreEnd();
//                                }
//                            }
//                        }
//                    })
//                    .failure(new IFailure() {
//                        @Override
//                        public void onFailure() {
//                            Toast.makeText(getActivity(), "错误了", Toast.LENGTH_SHORT).show();
//                            mMyCoustomerAdapter.loadMoreComplete();
//                            mMyCoustomerAdapter.loadMoreEnd();
//                        }
//                    })
//                    .error(new IError() {
//                        @Override
//                        public void onError(int code, String msg) {
//                            Toast.makeText(getActivity(), "错误了"+code+msg, Toast.LENGTH_SHORT).show();
//                            mMyCoustomerAdapter.loadMoreComplete();
//                            mMyCoustomerAdapter.loadMoreEnd();
//                        }
//                    })
//                    .build()
//                    .get();
//        }
//        catch (Exception e){
//
//        }
//
//
//    }


//    private void LoadMoreData(List<YiXiangJin.DataListBean> dataList) {
//        if(dataList.size()!=0){
//            mMyCoustomerAdapter.addData(dataList);
//            mMyCoustomerAdapter.loadMoreComplete();
//        }else {
//            mMyCoustomerAdapter.loadMoreComplete();
//        }
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    @Override
    public void onRefresh() {
//      getTongjiData(activity_id,merchantId);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
