package com.shushang.aishangjia.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;
import com.shushang.aishangjia.Bean.ClueDetail;
import com.shushang.aishangjia.Bean.Progress;
import com.shushang.aishangjia.Bean.Response;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.base.BaseActivity;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.ui.DecorateProgressDialog;
import com.shushang.aishangjia.ui.DecorateProgressDialog2;
import com.shushang.aishangjia.ui.ExtAlertDialog;
import com.shushang.aishangjia.ui.GenderDialog;
import com.shushang.aishangjia.ui.dialog.ActionSheetDialog;
import com.shushang.aishangjia.utils.Json.JSONUtil;
import com.shushang.aishangjia.utils.OkhttpUtils.CallBackUtil;
import com.shushang.aishangjia.utils.OkhttpUtils.OkhttpUtil;
import com.xys.libzxing.zxing.utils.PreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class UpdateKehuActivity extends BaseActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    private LinearLayout mLlCustomerGender,mllSource,mllThink;
    private TextView mEtCustomerName;//客户姓名
    private TextView mEtCustomerMobile;//客户电话
    private TextView mTvCustomerGender,mTvSource,mTvThink;
    private TextView mEtXiaoQu;//小区名称
    private TextView mXiansuo;//销售线索信息
    private Button mButton,mButton2;
    private Toolbar mToolbar;
    private RelativeLayout rl_activity;
    private EditText mTvIntentionToPurchaseProduct;//本次沟通时间
    private TextView mNextTime;//下次沟通时间
    private TextView mXiansuoThink;//本次次线索意向
    private TextView mBiezhu;//本次备注信息
    private TextView mTvActivity;
    private GenderDialog mGenderDialog;
    private DecorateProgressDialog mDecorateProgressDialog;
    private DecorateProgressDialog2 mDecorateProgressDialog2;
    private String token_id = null;
    private String activityId, activityName, activityCode;
    private String username, phone, xiaoqu,  xiansuo,info,thinkbuy,nowTime,nextTime,source;
    private String sex="";
    private RadioGroup mRadioButton;
    private long lastClick;
    private static final int REQUEST_CODE_CITY = 2010;
    private static final int REQUEST_CODE_ACTIVITY = 2011;
    private static final int REQUEST_PROGRESS_ACTIVITY = 2012;
    private static final int REQUEST_INFO_ACTIVITY = 2013;
    private static final int REQUEST_BEIZHU_ACTIVITY = 2014;
    private static final int REQUEST_NEXT_BEIZHU_ACTIVITY = 2015;
    private Dialog mRequestDialog;
    private boolean isVisiable=false;
    private  List<Progress.DataListBean> mDataListBeen;
    private String progress=null;
    private int select_year,select_mounth,select_day;
    private int now_year,now_mounth,now_day;
    private  String clueId;
    @Override
    public int setLayout() {
        return R.layout.activity_update_kehu;
    }


    @Override
    public void init() {
        mEtCustomerName = (TextView) findViewById(R.id.et_customer_name);
        mEtCustomerMobile = (TextView) findViewById(R.id.et_customer_mobile);
        mLlCustomerGender = (LinearLayout) findViewById(R.id.ll_customer_gender);
        mTvCustomerGender = (TextView) findViewById(R.id.tv_customer_gender);
        mllThink= (LinearLayout) findViewById(R.id.ll_thinkbuy);
        mllSource= (LinearLayout) findViewById(R.id.ll_source);
        mTvSource= (TextView) findViewById(R.id.tv_source);
        mXiansuo = (TextView) findViewById(R.id.et_decorate_style);
        mNextTime= (TextView) findViewById(R.id.next_time);
        mXiansuoThink= (TextView) findViewById(R.id.et_xiansuo_think);
        mEtXiaoQu= (TextView) findViewById(R.id.et_customer_xiaoqu);
        mllSource= (LinearLayout) findViewById(R.id.ll_source);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(this, getString(R.string.submit));
        String genjinData = PreferencesUtils.getString(UpdateKehuActivity.this, "genjinData");
        Log.d("genjinData",genjinData);
        ClueDetail xiansuoInfo = JSONUtil.fromJson(genjinData, ClueDetail.class);
        showData(xiansuoInfo);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mButton = (Button) findViewById(R.id.btn_submit);
        token_id = PreferencesUtils.getString(this, "token_id");
        clueId=xiansuoInfo.getData().getClueId();
        mTvIntentionToPurchaseProduct = (EditText) findViewById(R.id.et_intention_to_purchase_product);
        mEtCustomerName.setOnClickListener(this);
        mLlCustomerGender.setOnClickListener(this);
        mEtXiaoQu.setOnClickListener(this);
        mButton.setOnClickListener(this);

    }

    private void showData(ClueDetail xiansuoInfo) {
        try {

//            if(xiansuoInfo.getData().getTelephone()!=null){
//                mEtCustomerMobile.setText(xiansuoInfo.getData().getTelephone());
//            }

            if(xiansuoInfo.getData().getName()!=null){
                mEtCustomerName.setText(xiansuoInfo.getData().getName());
            }

            if(xiansuoInfo.getData().getAddress()!=null){
                mEtXiaoQu.setText(xiansuoInfo.getData().getAddress());
            }

            if(xiansuoInfo.getData().getSex()!=null){
                if(xiansuoInfo.getData().getSex().equals("1")){
                    mTvCustomerGender.setText("男");
                    sex="1";
                }
                else if(xiansuoInfo.getData().getSex().equals("2")){
                    mTvCustomerGender.setText("女");
                    sex="2";
                }
                else {
                    mTvCustomerGender.setText("未知");
                    sex="0";
                }
            }

            if(xiansuoInfo.getData().getSource()!=null){
                if(xiansuoInfo.getData().getSource().equals("1")){
//                mTvSource.setText("微信引流");
                    source="1";
                }
                else if(xiansuoInfo.getData().getSource().equals("2")){
//                mTvSource.setText("客户介绍");
                    source="2";
                }
                else if(xiansuoInfo.getData().getSource().equals("3")){
//                mTvSource.setText("广告");
                    source="3";
                }
                else if(xiansuoInfo.getData().getSource().equals("4")){
//                mTvSource.setText("销售拜访");
                    source="4";
                }
                else if(xiansuoInfo.getData().getSource().equals("5")){
//                mTvSource.setText("电话");
                    source="5";
                }
                else if(xiansuoInfo.getData().getSource().equals("6")){
//                mTvSource.setText("自然进店");
                    source="6";
                }
                else if(xiansuoInfo.getData().getSource().equals("7")){
//                mTvSource.setText("网上宣传");
                    source="7";
                }
                else if(xiansuoInfo.getData().getSource().equals("8")){
//                mTvSource.setText("朋友圈宣传");
                    source="8";
                }
                else if(xiansuoInfo.getData().getSource().equals("9")){
//                mTvSource.setText("其他");
                    source="9";
                }
                else if(xiansuoInfo.getData().getSource().equals("10")){
//                mTvSource.setText("售卡");
                    source="10";
                }
                else if(xiansuoInfo.getData().getSource().equals("11")){
//                mTvSource.setText("订金");
                    source="11";
                }
                else if(xiansuoInfo.getData().getSource().equals("12")){
//                mTvSource.setText("订单");
                    source="12";
                }
            }

        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_customer_name:
                ExtAlertDialog.showEditDlg(UpdateKehuActivity.this, "修改客户名字", "修改", false, new ExtAlertDialog.IExtDlgClick2() {
                    @Override
                    public void Oclick(int result, Editable text) {
                        if(result==1){
                            if(text==null){
                                mEtCustomerName.setText("");
                            }
                            else {
                                mEtCustomerName.setText(text);
                            }
                        }
                    }
                });
                break;
            case R.id.ll_customer_gender:
                new ActionSheetDialog(UpdateKehuActivity.this)
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("男", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        mTvCustomerGender.setText("男");
                                        sex="1";
                                    }
                                })
                        .addSheetItem("女", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        mTvCustomerGender.setText("女");
                                        sex="2";
                                    }
                                })
                        .show();
                break;
            case R.id.et_customer_xiaoqu:
                ExtAlertDialog.showEditDlg(UpdateKehuActivity.this, "修改小区地址", "修改", false, new ExtAlertDialog.IExtDlgClick2() {
                    @Override
                    public void Oclick(int result, Editable text) {
                        if(result==1){
                            if(text==null){
                                mEtXiaoQu.setText("");
                            }
                            else {
                                mEtXiaoQu.setText(text);
                            }
                        }
                    }
                });
                break;
            case R.id.btn_submit:
                username=mEtCustomerName.getText().toString().replace(" ", "");
                xiaoqu=mEtXiaoQu.getText().toString().replace(" ", "");
                try {
                    if(username.equals("")||xiaoqu.equals("")||sex.equals("")){
                        Toast.makeText(this, "必填项不能为空", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mRequestDialog.show();
                        submit(username,xiaoqu,sex);
                    }
                }
                catch (Exception e){
                    ToastUtils.showLong(e.toString());
                }

                break;
        }
    }


    public void submit(String username,String xiaoqu,String sex) {
        String url =  BaseUrl.BASE_URL + "clueController.do?method=saveOrUpdateClue&loginOS=2";
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("token_id", token_id);
        paramsMap.put("clueId", clueId);
        paramsMap.put("name", username);
        paramsMap.put("address", xiaoqu);
        paramsMap.put("sex", sex);
        if(source!=null){
            paramsMap.put("source", source);
        }

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
                Log.d("创建活动",response);
                if(response!=null){
                    Response response1 = JSONUtil.fromJson(response, Response.class);
                    if(response1.getRet().equals("200")){
                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                            mRequestDialog.dismiss();
                        }
                        finish();
                    }
                    else if(response1.getRet().equals("201")){
                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                            mRequestDialog.dismiss();
                        }
                        Toast.makeText(UpdateKehuActivity.this, ""+response1.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_INFO_ACTIVITY) {

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
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        select_year=year;
        select_mounth=monthOfYear;
        select_day=dayOfMonth;
        if(select_year<now_year){
            ToastUtils.showLong("请选择当前时间以后的年份");
        }
        else if(select_year==now_year&&select_mounth<now_mounth-1){
            ToastUtils.showLong("请选择当前时间以后的月份");
        }
        else if(select_mounth==now_mounth&&select_day<now_day){
            ToastUtils.showLong("请选择当前时间以后的日期");
        }
        else {
            Calendar now = Calendar.getInstance();
            TimePickerDialog tpd = TimePickerDialog.newInstance(
                    UpdateKehuActivity.this,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    false
            );
            tpd.setAccentColor(Color.parseColor("#2196F3"));
            tpd.setTabIndicators("","");
            tpd.show(getFragmentManager(), "Timepickerdialog");
        }
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String time=select_year+"-"+select_mounth+"-"+select_day+" "+hourString+":"+minuteString+":00";
        Date date=new Date(select_year-1900,select_mounth,select_day,hourOfDay,minute,0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        Log.d("time",time);
        mNextTime.setText(simpleDateFormat.format(date));
    }


    @Override
    protected void onPause() {
        super.onPause();
        mEtXiaoQu.clearFocus();
    }


    public static boolean isMobileNO(String mobiles) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        联通：130、131、132、152、155、156、185、186
        电信：133、153、180、189、（1349卫通）
        总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
        */
        String telRegex = "[1][123456789]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

}
