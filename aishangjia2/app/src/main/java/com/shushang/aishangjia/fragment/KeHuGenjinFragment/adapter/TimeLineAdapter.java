package com.shushang.aishangjia.fragment.KeHuGenjinFragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.shushang.aishangjia.Bean.KehuGenjinJIlu;
import com.shushang.aishangjia.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder> {

    private List<KehuGenjinJIlu.DataBean.ClueActionsBean> mFeedList;
    private Context mContext;
    private boolean mWithLinePadding;
    private LayoutInflater mLayoutInflater;
    private TextView mTextView1,mTextView2,mTextView3,mTextView4;

    public TimeLineAdapter(List<KehuGenjinJIlu.DataBean.ClueActionsBean> feedList) {
        mFeedList = feedList;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view;
        view = mLayoutInflater.inflate(R.layout.item_kehugenjin, parent, false);
        mTextView1=view.findViewById(R.id.timeline_time);
        mTextView2=view.findViewById(R.id.timeline_title);
        mTextView3=view.findViewById(R.id.timeline_shanghu);
        mTextView4=view.findViewById(R.id.timeline_content);
        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

        mTextView1.setText(mFeedList.get(position).getTime());
        mTextView2.setText(mFeedList.get(position).getTitle());
        mTextView3.setText(mFeedList.get(position).getMerchantName());
        mTextView4.setText(mFeedList.get(position).getDetalInfo());
    }

    @Override
    public int getItemCount() {
        return (mFeedList!=null? mFeedList.size():0);
    }



    class TimeLineViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time_marker)
        TimelineView mTimelineView;

        public TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mTimelineView.initLine(viewType);
        }
    }

}
