package com.shushang.aishangjia.fragment.ShouKaFragment.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.aishangjia.Bean.ShouKaList;
import com.shushang.aishangjia.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ShouKaAdapter extends BaseQuickAdapter<ShouKaList.DataListBean,BaseViewHolder> {


    public ShouKaAdapter(@LayoutRes int layoutResId, @Nullable List<ShouKaList.DataListBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShouKaList.DataListBean item) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(item.getCjsj());
      helper.setText(R.id.goutong_time_text,simpleDateFormat.format(date));
      if(item.getCardNum()!=null){
          helper.setText(R.id.activity_card_num,item.getCardNum());
      }

      if(item.getMerchantName()!=null){
          helper.setText(R.id.shanghu_name,item.getMerchantName());
      }

      if(item.getCustomerMobile()!=null){
          helper.setText(R.id.phone_text,item.getCustomerMobile());
      }

      if(item.getCustomerName()!=null){
          helper.setText(R.id.merchat_name,"("+item.getCustomerName()+")");
      }

      if(item.getQdsucess()!=null){
          if(item.getQdsucess().equals("0")){
             helper.getView(R.id.weiqian).setVisibility(View.VISIBLE);
              helper.getView(R.id.yiqian).setVisibility(View.GONE);
          }
          else {
              helper.getView(R.id.weiqian).setVisibility(View.GONE);
              helper.getView(R.id.yiqian).setVisibility(View.VISIBLE);
          }
      }
      else {
          helper.getView(R.id.weiqian).setVisibility(View.VISIBLE);
          helper.getView(R.id.yiqian).setVisibility(View.GONE);
      }

        if(item.getLqsuccess()!=null){
            if(item.getLqsuccess().equals("0")){
                helper.getView(R.id.weidui).setVisibility(View.VISIBLE);
                helper.getView(R.id.yidui).setVisibility(View.GONE);
            }
            else {
                helper.getView(R.id.weidui).setVisibility(View.GONE);
                helper.getView(R.id.yidui).setVisibility(View.VISIBLE);
            }
        }
        else {
            helper.getView(R.id.weidui).setVisibility(View.VISIBLE);
            helper.getView(R.id.yidui).setVisibility(View.GONE);
        }

    }
}

