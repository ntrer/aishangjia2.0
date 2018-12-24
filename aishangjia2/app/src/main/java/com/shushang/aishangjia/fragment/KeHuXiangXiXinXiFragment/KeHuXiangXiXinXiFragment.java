package com.shushang.aishangjia.fragment.KeHuXiangXiXinXiFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.shushang.aishangjia.Bean.ClueDetail;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.utils.Json.JSONUtil;
import com.xys.libzxing.zxing.utils.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KeHuXiangXiXinXiFragment extends BaseFragment {

    private String clueId;
    private String  token_id;
    private ClueDetail.DataBean data;
    private TextView mTextView1,mTextView2,mTextView3,mTextView4,mTextView5,mTextView6,mTextView7,mTextView8,mTextView9,mTextView10;

    public KeHuXiangXiXinXiFragment() {
        // Required empty public constructor
    }

    public static KeHuXiangXiXinXiFragment newInstance() {
        return new KeHuXiangXiXinXiFragment();
    }



    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        EventBus.getDefault().register(this);
        clueId=getActivity().getIntent().getStringExtra("clueId");
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        getData(token_id,clueId);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_kehuxiangxixinxi, null);
        mTextView1=view.findViewById(R.id.activity_count_text);
        mTextView2=view.findViewById(R.id.chengjiaoliang_text);
        mTextView3=view.findViewById(R.id.chengjiaoe_text);
        mTextView4=view.findViewById(R.id.pingjun_chengjiaoe_text);
        mTextView5=view.findViewById(R.id.sign_count_text);
        mTextView6=view.findViewById(R.id.genjin_count_text);
        mTextView7=view.findViewById(R.id.chuangjianren_text);
        mTextView8=view.findViewById(R.id.nexttime_text);
        mTextView9=view.findViewById(R.id.dingdanchengjiaoliang_text);
        mTextView10=view.findViewById(R.id.dingdanchengjiaoe_text);
        return view;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if(messageEvent.getMessage().equals("跟进记录")){
            getData(token_id,clueId);
        }
    }



    private void getData(String token_id, String clueId) {
        String url = BaseUrl.BASE_URL+"clueController.do?method=getClueDetail&token_id=" + token_id+"&loginOS=2&clueId="+clueId;
        Log.d("BaseUrl", url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
//                            mSwipeRefreshLayout.setRefreshing(false);
                            if (response != null) {
                                try {
                                    ClueDetail yiXiangJin = JSONUtil.fromJson(response, ClueDetail.class);
                                    if(yiXiangJin.getRet().equals("200")){
                                        data = yiXiangJin.getData();
                                        if(data!=null){
                                            showData(data);
                                        }
                                        else {
                                            showData(data);
                                        }
                                    }
                                    else if(yiXiangJin.getRet().equals("101")){
                                        Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                        com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils.putString(getActivity(),"token_id",null);
                                        startActivity(new Intent(getActivity(), LoginActivity2.class));
                                        getActivity().finish();
                                    }
                                    else if(yiXiangJin.getRet().equals("201")){
                                        Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){
                                    ToastUtils.showLong(e.toString());
                                }
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                        }
                    })
                    .build()
                    .get();
        }
        catch (Exception e){
            ToastUtils.showLong("获取数据错误了");
        }
    }

    private void showData(ClueDetail.DataBean data) {
        if(String.valueOf(data.getActivityCounts())!=null){
            mTextView1.setText(data.getActivityCounts()+"次");
        }
        else {
            mTextView1.setText("0次");
        }

        if(String.valueOf(data.getOrderCounts())!=null){
            mTextView2.setText(data.getOrderCounts()+"件");
        }
        else {
            mTextView2.setText("0件");
        }

        if(String.valueOf(data.getOrderTotalAmount())!=null){
            mTextView3.setText(data.getOrderTotalAmount()+"元");
        }
        else {
            mTextView3.setText("0.0元");
        }

        if(String.valueOf(data.getAvgAmount())!=null&&!String.valueOf(data.getAvgAmount()).equals("NaN")){
            mTextView4.setText(data.getAvgAmount()+"元");
        }
        else {
            mTextView4.setText("0.0元");
        }

        if(String.valueOf(data.getQdCounts())!=null){
            mTextView5.setText(data.getQdCounts()+"次");
        }
        else {
            mTextView5.setText("0次");
        }

        if(String.valueOf(data.getClueActionCounts())!=null){
            mTextView6.setText(data.getClueActionCounts()+"次");
        }
        else {
            mTextView6.setText("0次");
        }

        if(data.getChuangjianrenName()!=null){
            mTextView7.setText(data.getChuangjianrenName());
        }
        else {
            mTextView7.setText("无");
        }


        if(String.valueOf(data.getNextTime())!=null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
            //获取当前时间
            Date date = new Date(data.getNextTime());
            mTextView8.setText(simpleDateFormat.format(date));
        }
        else {
            mTextView8.setText("无预约");
        }

        if(String.valueOf(data.getGoodsOrderCounts())!=null){
            mTextView9.setText(data.getGoodsOrderCounts()+"件");
        }
        else {
            mTextView9.setText("0件");
        }

        if(String.valueOf(data.getTotalAmount())!=null){
            mTextView10.setText(data.getTotalAmount()+"元");
        }
        else {
            mTextView10.setText("0.0元");
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


}
