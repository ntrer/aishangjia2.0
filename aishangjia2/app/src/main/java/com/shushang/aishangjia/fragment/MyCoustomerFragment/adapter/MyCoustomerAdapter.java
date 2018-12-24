package com.shushang.aishangjia.fragment.MyCoustomerFragment.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.aishangjia.Bean.MyClues;
import com.shushang.aishangjia.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyCoustomerAdapter extends BaseQuickAdapter<MyClues.DataListBean,BaseViewHolder> {

    public MyCoustomerAdapter(int layoutResId, @Nullable List<MyClues.DataListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyClues.DataListBean item) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(item.getCjsj());
        helper.setText(R.id.goutong_time_text,simpleDateFormat.format(date));

        if(item.getName()!=null&&item.getSex()!=null){
            if(item.getSex().equals("1")){
                helper.getView(R.id.sex1).setVisibility(View.VISIBLE);
                helper.getView(R.id.sex2).setVisibility(View.GONE);
                helper.setText(R.id.people,item.getName());
            }
            else if(item.getSex().equals("2")){
                helper.getView(R.id.sex1).setVisibility(View.GONE);
                helper.getView(R.id.sex2).setVisibility(View.VISIBLE);
                helper.setText(R.id.people,item.getName());
            }
            else {
                helper.setText(R.id.people,item.getName()+"(未知)");
                helper.getView(R.id.sex1).setVisibility(View.GONE);
                helper.getView(R.id.sex2).setVisibility(View.GONE);
            }

        }
        else if(item.getName()!=null&&item.getSex()==null){
            helper.setText(R.id.people,item.getName());
        }

        if(String.valueOf(item.getActivityCounts())!=null){
            helper.setText(R.id.activity_time_count,item.getActivityCounts()+"");
        }


        if(String.valueOf(item.getActivityCounts())==null||item.getActivityCounts()==0){
            helper.getView(R.id.dingjin_pic).setVisibility(View.GONE);
            helper.getView(R.id.dingjin_pic2).setVisibility(View.VISIBLE);
            helper.getView(R.id.dingjin_pic3).setVisibility(View.GONE);
        }

        if(String.valueOf(item.getActivityCounts())!=null&&String.valueOf(item.getGoodsOrderCounts())!=null){
            if(item.getActivityCounts()>0&&item.getGoodsOrderCounts()==0){
                helper.getView(R.id.dingjin_pic).setVisibility(View.GONE);
                helper.getView(R.id.dingjin_pic2).setVisibility(View.GONE);
                helper.getView(R.id.dingjin_pic3).setVisibility(View.VISIBLE);
            }
            else if(item.getGoodsOrderCounts()>0){
                helper.getView(R.id.dingjin_pic).setVisibility(View.VISIBLE);
                helper.getView(R.id.dingjin_pic2).setVisibility(View.GONE);
                helper.getView(R.id.dingjin_pic3).setVisibility(View.GONE);
            }
        }


        if(String.valueOf(item.getClueActionCounts())!=null){
            helper.setText(R.id.genjin_count_text,item.getClueActionCounts()+"");
        }

        if(String.valueOf(item.getGoodsOrderCounts())!=null){
            helper.setText(R.id.pay_count_text,item.getGoodsOrderCounts()+"");
        }

        if(String.valueOf(item.getTotalAmount())!=null){
            helper.setText(R.id.pay_money_text,item.getTotalAmount()+"");
        }

        if(item.getTelephone()!=null){
            helper.setText(R.id.phone,item.getTelephone());
        }

    }
}
