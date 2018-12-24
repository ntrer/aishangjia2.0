package com.shushang.aishangjia.activity.adapter;

import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.aishangjia.Bean.LeaguesList;

import java.util.List;

public class ZhangDanAdapter2 extends BaseQuickAdapter<LeaguesList.DataListBean,BaseViewHolder> {

    public ZhangDanAdapter2(@LayoutRes int layoutResId, @Nullable List<LeaguesList.DataListBean> data, Handler handler) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final LeaguesList.DataListBean item) {

    }
}
