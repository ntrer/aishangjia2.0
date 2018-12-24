package com.shushang.aishangjia.fragment.KehuDingjinFragment.adapter;

import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.aishangjia.Bean.kehudingjin;
import com.shushang.aishangjia.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MoneyPeopleRecyclerViewAdapter3 extends BaseQuickAdapter<kehudingjin.DataListBean,BaseViewHolder> {

    private Handler mHandler;

    public MoneyPeopleRecyclerViewAdapter3(@LayoutRes int layoutResId, @Nullable List<kehudingjin.DataListBean >data, Handler handler) {
        super(layoutResId, data);
        this.mHandler=handler;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final kehudingjin.DataListBean item) {
        Log.d("asdasdas",item.getOrder().getChuangjianren());
        helper.setText(R.id.people,item.getOrder().getCustomerName());
        helper.setText(R.id.phone,item.getOrder().getCustomerPhone());
        if(item.getOrder().getSource().equals("0")){
            helper.setText(R.id.dingjin_type,"日常");
        }
        else {
            helper.setText(R.id.dingjin_type,"活动");
        }

        if(item.getOrder().getStatus().equals("0")){
            helper.getView(R.id.yituidan).setVisibility(View.GONE);
            helper.getView(R.id.yishiyong).setVisibility(View.GONE);
        }
        else if(item.getOrder().getStatus().equals("100")||item.getOrder().getStatus().equals("-300")){
            helper.getView(R.id.yishiyong).setVisibility(View.VISIBLE);
            helper.getView(R.id.yituidan).setVisibility(View.GONE);
        }
        else if(item.getOrder().getStatus().equals("-200")){
            helper.getView(R.id.yishiyong).setVisibility(View.GONE);
            helper.getView(R.id.yituidan).setVisibility(View.VISIBLE);
        }


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(item.getOrder().getCjsj());
        String time=simpleDateFormat.format(date);
        helper.setText(R.id.create_time_text,time);
        if(item.getOrder().getOrderMerchantName()!=null){
            helper.setText(R.id.label,item.getOrder().getOrderMerchantName().toString());
        }
        helper.setText(R.id.money,String.valueOf(item.getOrder().getTotalPrice())+"元");


    }
}

