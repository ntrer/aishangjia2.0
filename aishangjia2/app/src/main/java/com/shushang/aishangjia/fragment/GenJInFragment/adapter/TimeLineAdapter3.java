package com.shushang.aishangjia.fragment.GenJInFragment.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.aishangjia.Bean.GenJInJiLu;
import com.shushang.aishangjia.R;

import java.util.List;

public class TimeLineAdapter3 extends BaseQuickAdapter<GenJInJiLu.DataListBean,BaseViewHolder> {



    public TimeLineAdapter3(int layoutResId, @Nullable List<GenJInJiLu.DataListBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, GenJInJiLu.DataListBean item) {
        helper.setText(R.id.timeline_time,item.getTime());
        helper.setText(R.id.timeline_title,item.getTitle());
        helper.setText(R.id.timeline_shanghu,item.getMerchantName());
        helper.setText(R.id.timeline_content,item.getDetalInfo());

    }


}
