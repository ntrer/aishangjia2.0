package com.shushang.aishangjia.activity.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.aishangjia.Bean.ShouKaDetail;
import com.shushang.aishangjia.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SellCardAdapter extends BaseQuickAdapter<ShouKaDetail.DataBean.OrderVosBean,BaseViewHolder> {

    public SellCardAdapter(int layoutResId, @Nullable List<ShouKaDetail.DataBean.OrderVosBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShouKaDetail.DataBean.OrderVosBean item) {
//        helper.setText(R.id.people,item.getCustomerName());
//        helper.setText(R.id.phone,item.getCustomerPhone());
        if(item.getSource().equals("0")){
            helper.setText(R.id.dingjin_type,"日常");
        }
        else {
            helper.setText(R.id.dingjin_type,"活动");
        }

        if(item.getStatus().equals("0")){
            helper.getView(R.id.yituidan).setVisibility(View.GONE);
            helper.getView(R.id.yishiyong).setVisibility(View.GONE);
        }
        else if(item.getStatus().equals("100")||item.getStatus().equals("-300")){
            helper.getView(R.id.yishiyong).setVisibility(View.VISIBLE);
            helper.getView(R.id.yituidan).setVisibility(View.GONE);
        }
        else if(item.getStatus().equals("-200")){
            helper.getView(R.id.yishiyong).setVisibility(View.GONE);
            helper.getView(R.id.yituidan).setVisibility(View.VISIBLE);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(item.getCjsj());
        String time=simpleDateFormat.format(date);
        helper.setText(R.id.create_time_text,time);
        if(item.getOrderMerchantName()!=null){
            helper.setText(R.id.label,item.getOrderMerchantName().toString());
        }
        helper.setText(R.id.money,String.valueOf(item.getTotalPrice())+"元");
    }
}
