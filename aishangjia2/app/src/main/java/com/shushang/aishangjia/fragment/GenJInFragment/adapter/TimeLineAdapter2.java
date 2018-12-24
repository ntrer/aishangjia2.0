package com.shushang.aishangjia.fragment.GenJInFragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.shushang.aishangjia.Bean.GenJInJiLu;
import com.shushang.aishangjia.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeLineAdapter2 extends RecyclerView.Adapter<TimeLineAdapter2.TimeLineViewHolder> {

    private List<GenJInJiLu.DataListBean> mFeedList;
    private Context mContext;
    private boolean mWithLinePadding;
    private LayoutInflater mLayoutInflater;

    public TimeLineAdapter2(List<GenJInJiLu.DataListBean> feedList) {
        mFeedList = feedList;
    }


    /**
     * 加载更多时调用
     * @param position
     * @param datas
     */
    public void addData(int position,List<GenJInJiLu.DataListBean> datas){
        if(datas !=null && datas.size()>0) {
            this.mFeedList.addAll(datas);
            this.notifyItemRangeChanged(position, datas.size());
        }
    }

    /**
     * 得到所有数据
     * @return
     */
    public List<GenJInJiLu.DataListBean> getDatas(){

        return  mFeedList;
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
        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        holder.mTextView1.setText(mFeedList.get(position).getTime());
        holder.mTextView2.setText(mFeedList.get(position).getTitle());
        if(mFeedList.get(position).getMerchantName()==null){
            holder.mTextView3.setText("未知");
        }
        else {
            holder.mTextView3.setText(mFeedList.get(position).getMerchantName());
        }
        if(mFeedList.get(position).getDetalInfo()==null){
            holder.mTextView4.setText("无");
        }
        else {
            holder.mTextView4.setText(mFeedList.get(position).getDetalInfo());
        }
//        if(position==mFeedList.size()-1){
//            holder.mTextView5.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public int getItemCount() {
        return (mFeedList!=null? mFeedList.size():0);
    }






    class TimeLineViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time_marker)
        TimelineView mTimelineView;
        TextView mTextView1,mTextView2,mTextView3,mTextView4,mTextView5;
        public TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mTextView1=itemView.findViewById(R.id.timeline_time);
            mTextView2=itemView.findViewById(R.id.timeline_title);
            mTextView3=itemView.findViewById(R.id.timeline_shanghu);
            mTextView4=itemView.findViewById(R.id.timeline_content);
//            mTextView5=itemView.findViewById(R.id.nomore);
            mTimelineView.initLine(viewType);
        }
    }

}
