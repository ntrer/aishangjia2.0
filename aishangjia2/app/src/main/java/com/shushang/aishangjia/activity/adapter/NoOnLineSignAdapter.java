package com.shushang.aishangjia.activity.adapter;

import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.aishangjia.Bean.ActionCustomersBean;
import com.shushang.aishangjia.R;

import java.text.SimpleDateFormat;
import java.util.List;


public class NoOnLineSignAdapter extends BaseQuickAdapter<ActionCustomersBean,BaseViewHolder> {


    public NoOnLineSignAdapter(int layoutResId, @Nullable List<ActionCustomersBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ActionCustomersBean actionCustomersBean) {
        if(actionCustomersBean!=null){
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
                baseViewHolder.setText(R.id.goutong_time_text,simpleDateFormat.format(actionCustomersBean.getQdsj()));
                baseViewHolder.setText(R.id.activity_card_num,actionCustomersBean.getCardNum());
                baseViewHolder.setText(R.id.merchat_name,"("+actionCustomersBean.getCustomerName()+")");
                baseViewHolder.setText(R.id.phone,actionCustomersBean.getCustomerMobile());
                baseViewHolder.setText(R.id.shanghu_name,actionCustomersBean.getMerchantName());
            }
            catch (Exception e){
                ToastUtils.showLong(e.toString());
            }
        }
    }
}
