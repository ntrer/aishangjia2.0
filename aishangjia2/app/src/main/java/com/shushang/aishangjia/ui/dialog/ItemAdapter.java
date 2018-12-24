package com.shushang.aishangjia.ui.dialog;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.aishangjia.R;

import java.util.List;

public class ItemAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public ItemAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
     helper.setText(R.id.tishiyu,item.toString());
    }
}
