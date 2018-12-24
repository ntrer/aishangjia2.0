package com.shushang.aishangjia.fragment.DaiDanTongjiFragment.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.aishangjia.Bean.DaidanTongji;
import com.shushang.aishangjia.R;

import java.util.List;

public class DaiDanTongjiAdapter extends BaseQuickAdapter<DaidanTongji.DataListBean,BaseViewHolder> {


    public DaiDanTongjiAdapter(@LayoutRes int layoutResId, @Nullable List<DaidanTongji.DataListBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final DaidanTongji.DataListBean item) {
        if(item.getMerchantName()!=null){
            helper.setText(R.id.chengjiao_shanghu_text,item.getMerchantSubName());
        }

        if(item.getMerchantSubName()!=null){
            helper.setText(R.id.shouka_shanghu_text,item.getMerchantName());
        }

        if(String.valueOf(item.getOrderCount())!=null){
            helper.setText(R.id.chengjiao_danshu_text,item.getOrderCount()+"");
        }

        if(String.valueOf(item.getOrderMoney())!=null){
            helper.setText(R.id.chengjiao_jine_text,item.getOrderMoney()+"");
        }


        if(item.getMode()!=null){
            if (item.getMode().equals("0")){
                helper.setText(R.id.fencheng_fangshi_text,"不计算");
            }
            else if(item.getMode().equals("1")){
                helper.setText(R.id.fencheng_fangshi_text,"每单固定金额");
            }
            else {
                helper.setText(R.id.fencheng_fangshi_text,"成交金额的百分比");
            }
        }

     if(String.valueOf(item.getSituation())!=null){
            helper.setText(R.id.fencheng_zhi_text,item.getSituation()+"");
     }

        if(String.valueOf(item.getMode_count())!=null){
            helper.setText(R.id.yingfu_yongjin_text,item.getMode_count()+"");
        }


    }
}
