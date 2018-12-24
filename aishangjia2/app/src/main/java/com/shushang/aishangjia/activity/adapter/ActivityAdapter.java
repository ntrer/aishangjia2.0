package com.shushang.aishangjia.activity.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.aishangjia.Bean.ActivityListNew;
import com.shushang.aishangjia.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by YD on 2018/8/7.
 */

public class ActivityAdapter extends BaseQuickAdapter<ActivityListNew.DataListBean,BaseViewHolder> {

    public ActivityAdapter(int item_sheng, List<ActivityListNew.DataListBean> dataListBeen) {
        super(item_sheng, dataListBeen);
    }

    @Override
    protected void convert(BaseViewHolder helper, ActivityListNew.DataListBean item) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
            //获取当前时间
            Date date1 = new Date(item.getSceneStart());
            Date date2 = new Date(item.getSceneEnd());
            helper.setText(R.id.activity_name_text,item.getActivityName());
//            if(item.getShengName()!=null&&item.getShiName()!=null&&item.getQuName()!=null){
//                helper.setText(R.id.activity_address_text,item.getShengName()+item.getShiName()+item.getQuName());
//            }
            helper.setText(R.id.start_time_text,simpleDateFormat.format(date1));
            helper.setText(R.id.end_time_text,simpleDateFormat.format(date2));
        }
        catch (Exception e){

        }

    }
}
