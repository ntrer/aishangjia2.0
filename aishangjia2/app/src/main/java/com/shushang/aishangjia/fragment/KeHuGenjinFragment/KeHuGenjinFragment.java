package com.shushang.aishangjia.fragment.KeHuGenjinFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.shushang.aishangjia.Bean.KehuGenjinJIlu;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.fragment.KeHuGenjinFragment.adapter.KeHuGenjinAdapter;
import com.shushang.aishangjia.fragment.KeHuGenjinFragment.adapter.TimeLineAdapter;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.utils.Json.JSONUtil;
import com.xys.libzxing.zxing.ui.dialog.ActionSheetDialog;
import com.xys.libzxing.zxing.utils.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class KeHuGenjinFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView mRecyclerView;
    List<KehuGenjinJIlu.DataBean.ClueActionsBean> clueActions=new ArrayList<>();
    private KeHuGenjinAdapter mKeHuGenjinAdapter;
    private TimeLineAdapter mTimeLineAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String clueId;
    private String  token_id;
    private ImageView mImageView;
    private String type;
    private  String url;
    LinearLayout mLlNoData;
    public KeHuGenjinFragment() {
        // Required empty public constructor
    }

    public static KeHuGenjinFragment newInstance() {
        return new KeHuGenjinFragment();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        EventBus.getDefault().register(this);
        clueId=getActivity().getIntent().getStringExtra("clueId");
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        getData(token_id,clueId,type);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(getActivity())
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("主动跟进", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        type="1";
                                        getData(token_id,clueId,type);
                                    }
                                })
                        .addSheetItem("售卡", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        type="2";
                                        getData(token_id,clueId,type);
                                    }
                                })
                        .addSheetItem("签到", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        type="3";
                                        getData(token_id,clueId,type);
                                    }
                                })
                        .addSheetItem("定金", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        type="4";
                                        getData(token_id,clueId,type);
                                    }
                                })
                        .addSheetItem("领取礼品", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        type="5";
                                        getData(token_id,clueId,type);
                                    }
                                })
                        .addSheetItem("订单", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        type="6";
                                        getData(token_id,clueId,type);
                                    }
                                })
                        .show();
            }
        });
    }

    private void getData(String token_id, String clueId,String type) {
        mSwipeRefreshLayout.setRefreshing(true);
        if(type==null){
            url = BaseUrl.BASE_URL+"clueController.do?method=getClueById&token_id=" + token_id+"&loginOS=2&clueId="+clueId;
        }
        else {
            url = BaseUrl.BASE_URL+"clueController.do?method=getClueById&token_id=" + token_id+"&loginOS=2&clueId="+clueId+"&type="+type;
        }
        Log.d("BaseUrl", url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            mSwipeRefreshLayout.setRefreshing(false);
                            if (response != null) {
                                try {
                                    KehuGenjinJIlu yiXiangJin = JSONUtil.fromJson(response, KehuGenjinJIlu.class);
                                    if(yiXiangJin.getRet().equals("200")){
                                         clueActions = yiXiangJin.getData().getClueActions();
                                        if(clueActions.size()!=0){
                                            showData(clueActions);
                                            mLlNoData.setVisibility(View.GONE);
                                        }
                                        else {
                                            showData(clueActions);
                                            mLlNoData.setVisibility(View.VISIBLE);
                                        }
                                    }
                                    else if(yiXiangJin.getRet().equals("101")){
                                        mSwipeRefreshLayout.setRefreshing(false);
                                        Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                        com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils.putString(getActivity(),"token_id",null);
                                        startActivity(new Intent(getActivity(), LoginActivity2.class));
                                        getActivity().finish();
                                    }
                                    else if(yiXiangJin.getRet().equals("201")){
                                        mSwipeRefreshLayout.setRefreshing(false);
                                        Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    ToastUtils.showLong(e.toString());
                                }
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            mSwipeRefreshLayout.setRefreshing(false);
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            mSwipeRefreshLayout.setRefreshing(false);
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                        }
                    })
                    .build()
                    .get();
        }
        catch (Exception e){
            mSwipeRefreshLayout.setRefreshing(false);
            ToastUtils.showLong("获取数据错误了");
        }
    }

    private void showData(List<KehuGenjinJIlu.DataBean.ClueActionsBean> clueActions) {
        final LinearLayoutManager linermanager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linermanager);
        mRecyclerView.setHasFixedSize(true);
        mTimeLineAdapter=new TimeLineAdapter(clueActions);
        mRecyclerView.setAdapter(mTimeLineAdapter);
        mRecyclerView.scrollToPosition(0);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if(messageEvent.getMessage().equals("跟进记录")){
            getData(token_id,clueId,type);
        }
    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_kehugenjin, null);
        mRecyclerView=view.findViewById(R.id.rv_genjin);
        mSwipeRefreshLayout=view.findViewById(R.id.srl_kehu_genjin);
        mImageView=view.findViewById(R.id.genjin_shaixuan);
        mLlNoData=view.findViewById(R.id.ll_no_data);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onRefresh() {
        getData(token_id,clueId,type);
    }
}
