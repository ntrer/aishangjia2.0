package com.shushang.aishangjia.fragment.KehuDingdanxnixiFragment.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.aishangjia.Bean.ClueGoods;
import com.shushang.aishangjia.R;

import java.util.List;

public class KehuDingdanXInxiAdapter extends BaseQuickAdapter<ClueGoods.DataListBean,BaseViewHolder> {

    public KehuDingdanXInxiAdapter(int layoutResId, @Nullable List<ClueGoods.DataListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClueGoods.DataListBean item) {
      if(String.valueOf(item.getGoodsOrder().getGoodsorderNum())!=null){
          helper.setText(R.id.dingdan_title_num,item.getGoodsOrder().getGoodsorderNum()+"—");
      }

        if(item.getName()!=null){
            helper.setText(R.id.dingdan_title_name,item.getName());
        }
        else {
            helper.setText(R.id.dingdan_title_name,"无");
        }


        if(item.getTelephone()!=null){
            helper.setText(R.id.dingdan_title_bianhuao,"("+item.getTelephone()+")");
        }
        else {
            helper.setText(R.id.dingdan_title_bianhuao,"");
        }

        if(String.valueOf(item.getGoodsOrder().getGoods().size())!=null){
            helper.setText(R.id.dingdan_title_count,"x"+item.getGoodsOrder().getGoods().size()+"");
        }


        if(item.getGoodsOrder().getOrderManagerName()!=null){
            helper.setText(R.id.dingdan_creater_text,item.getGoodsOrder().getOrderManagerName()+"");
        }
        else {
            helper.setText(R.id.dingdan_creater_text,"无");
        }




        if(String.valueOf(item.getGoodsOrder().getTotalPrice())!=null){
            helper.setText(R.id.dingdan_total_text,item.getGoodsOrder().getTotalPrice()+"元");
        }
        else {
            helper.setText(R.id.dingdan_total_text,"00.0元");
        }



        if(String.valueOf(item.getGoodsOrder().getYingshou())!=null){
            helper.setText(R.id.dingdan_get_text,item.getGoodsOrder().getYingshou()+"元");
        }
        else {
            helper.setText(R.id.dingdan_get_text,"00.0元");
        }




        if(item.getGoodsOrder().getActivityName()!=null){
            helper.setText(R.id.kehu_huodng_text,item.getGoodsOrder().getActivityName()+"");
        }
        else {
           helper.getView(R.id.kehu_huodng).setVisibility(View.GONE);
            helper.getView(R.id.kehu_huodng_text).setVisibility(View.GONE);
        }



        if(String.valueOf(item.getGoodsOrder().getDikoujia())!=null){
            helper.setText(R.id.dingdan_money_text,item.getGoodsOrder().getDikoujia()+"元");
        }
        else {
            helper.setText(R.id.dingdan_money_text,"00.0元");
        }


        if(String.valueOf(item.getGoodsOrder().getOrders().size())!=null){
            helper.setText(R.id.dingdan_dingjin_text,item.getGoodsOrder().getOrders().size()+"单");
        }
        else {
            helper.setText(R.id.dingdan_dingjin_text,"0单");
        }

        if(String.valueOf(item.getGoodsOrder().getShishou())!=null){
            helper.setText(R.id.dingdan_shouldget_text,item.getGoodsOrder().getShishou()+"元");
        }
        else {
            helper.setText(R.id.dingdan_shouldget_text,"00.0元");
        }


    }
}
