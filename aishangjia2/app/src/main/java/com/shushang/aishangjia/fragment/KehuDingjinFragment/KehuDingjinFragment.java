package com.shushang.aishangjia.fragment.KehuDingjinFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shushang.aishangjia.Bean.kehudingjin;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.fragment.KehuDingjinFragment.adapter.MoneyPeopleRecyclerViewAdapter3;
import com.shushang.aishangjia.fragment.MoneyFragment.refreshHandler.MoneyDataRefreshHandler;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.utils.Json.JSONUtil;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

public class KehuDingjinFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView mRecyclerView,mSignPeopleRecyclerView,mRecyclerView2;
    private SwipeRefreshLayout mSwipeRefreshLayout=null;
    private TextView mTextView;
    private ImageView mSearch,tabTextView;
    private ImageView mImageView;
    private MoneyDataRefreshHandler mMoneyDataRefreshHandler;
    private RelativeLayout mLoading;
    private boolean isFirst=true;
    private int page=1;
    private String clueId;
    private String  token_id;
    private String resourceName=null;
    LinearLayout mLlNoData;
    List<kehudingjin.DataListBean> SignPeopleData = new ArrayList<>();
    private MoneyPeopleRecyclerViewAdapter3 mMoneyPeopleRecyclerViewAdapter;

    public Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case 1:
                    break;
                case 2:
                    break;
            }
            return false;
        }
    });

    public KehuDingjinFragment() {
        // Required empty public constructor
    }

    public static KehuDingjinFragment newInstance() {
        return new KehuDingjinFragment();
    }



    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        clueId=getActivity().getIntent().getStringExtra("clueId");
        token_id = com.xys.libzxing.zxing.utils.PreferencesUtils.getString(getActivity(), "token_id");
        getData(token_id,clueId);
    }


    @Override
    public void initData() {
        super.initData();
        resourceName=PreferencesUtils.getString(mContext,"ResourceName");
    }

    private void getData(String token_id, String clueId) {
        mSwipeRefreshLayout.setRefreshing(true);
        String url = BaseUrl.BASE_URL+"clueController.do?method=getClueOrderDetail&token_id=" + token_id+"&loginOS=2&clueId="+clueId;
        Log.d("url",url);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("SignP",response);
                        mSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            try {
                                kehudingjin yiXiangJin = JSONUtil.fromJson(response, kehudingjin.class);
                                if(yiXiangJin.getRet().equals("200")){
                                    SignPeopleData = yiXiangJin.getDataList();
                                    Log.d("asfasfas",SignPeopleData.size()+"");
                                    if(SignPeopleData.size()!=0){
                                        mLlNoData.setVisibility(View.GONE);
                                        showTabData(SignPeopleData);
                                        Log.d("asfasfas","asfasfasfasfas");
                                    }
                                    else {
                                        showTabData(SignPeopleData);
                                        mLlNoData.setVisibility(View.VISIBLE);
                                    }
                                    isFirst=false;
                                }
                                else if(yiXiangJin.getRet().equals("101")){
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                    PreferencesUtils.putString(getActivity(),"token_id",null);
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
                                Log.d("出错了",e.toString());
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
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                })
                .build()
                .get();
    }

    private void showTabData(final List<kehudingjin.DataListBean> signPeopleData) {
        final LinearLayoutManager linermanager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linermanager);
        mMoneyPeopleRecyclerViewAdapter = new MoneyPeopleRecyclerViewAdapter3(R.layout.item_dingjin_new, signPeopleData,mHandler);
        //重复执行动画
        mMoneyPeopleRecyclerViewAdapter.isFirstOnly(false);
        mRecyclerView.setAdapter(mMoneyPeopleRecyclerViewAdapter);
        mLoading.setVisibility(View.GONE);
    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_kehudingjin, null);
        mRecyclerView=view.findViewById(R.id.rv_dingjin);
        mSwipeRefreshLayout=view.findViewById(R.id.srl_kehu_dingjin);
        mLlNoData=view.findViewById(R.id.ll_no_data);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }


    @Override
    public void onRefresh() {
        getData(token_id,clueId);
    }


}
