package com.shushang.aishangjia.activity.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.aishangjia.Bean.ActivityList;
import com.shushang.aishangjia.R;

import java.util.List;

/**
 * Created by YD on 2018/8/8.
 */

public class ActivityListAdapter extends BaseQuickAdapter<ActivityList.DataListBean,BaseViewHolder> {

    public ActivityListAdapter(@LayoutRes int layoutResId, @Nullable List<ActivityList.DataListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ActivityList.DataListBean item) {

        helper.setText(R.id.activity_name_text,item.getActivityName());
        helper.setText(R.id.user_type,item.getRoleName());
        helper.setText(R.id.start_time,item.getStartTime());
        helper.setText(R.id.end_time,item.getEndTime());

    }
//    Uri path= Uri.parse("android.resource://" + MyApplication.getInstance().getPackageName() + "/" + R.mipmap.azp);

}
