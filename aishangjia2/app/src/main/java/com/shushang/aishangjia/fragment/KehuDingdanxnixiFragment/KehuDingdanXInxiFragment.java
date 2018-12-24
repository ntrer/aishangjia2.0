package com.shushang.aishangjia.fragment.KehuDingdanxnixiFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.shushang.aishangjia.Bean.ClueGoods;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.fragment.KehuDingdanxnixiFragment.adapter.KehuDingdanXInxiAdapter;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.utils.Json.JSONUtil;
import com.xys.libzxing.zxing.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

public class KehuDingdanXInxiFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    private KehuDingdanXInxiAdapter mKehuDingdanXInxiAdapter;
    private RecyclerView mRecyclerView;
    List<ClueGoods.DataListBean> dataList=new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String clueId;
    private String  token_id;
    private String type="1";
    LinearLayout mLlNoData;
    public KehuDingdanXInxiFragment() {
        // Required empty public constructor
    }

    public static KehuDingdanXInxiFragment newInstance() {
        return new KehuDingdanXInxiFragment();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        clueId=getActivity().getIntent().getStringExtra("clueId");
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        getData(token_id,clueId);
    }

    private void getData(String token_id, String clueId) {
        mSwipeRefreshLayout.setRefreshing(true);
        String url = BaseUrl.BASE_URL+"clueController.do?method=getClueGoodsOrderDetail&token_id=" + token_id+"&loginOS=2&clueId="+clueId;
        Log.d("getClueGoodsOrderDetail", url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            mSwipeRefreshLayout.setRefreshing(false);
                            if (response != null) {
                                try {
                                    ClueGoods yiXiangJin = JSONUtil.fromJson(response, ClueGoods.class);
                                    if(yiXiangJin.getRet().equals("200")){
                                        dataList = yiXiangJin.getDataList();
                                        if(dataList.size()!=0){
                                            showData(dataList);
                                            mLlNoData.setVisibility(View.GONE);
                                        }
                                        else {
                                            showData(dataList);
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

    private void showData(List<ClueGoods.DataListBean> dataList) {
        final LinearLayoutManager linermanager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linermanager);
        mRecyclerView.setHasFixedSize(true);
        mKehuDingdanXInxiAdapter=new KehuDingdanXInxiAdapter(R.layout.item_kehudingdan,dataList);
        mRecyclerView.setAdapter(mKehuDingdanXInxiAdapter);
        mRecyclerView.scrollToPosition(0);
    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_kehudingdan, null);
        mRecyclerView=view.findViewById(R.id.rv_dingdan);
        mSwipeRefreshLayout=view.findViewById(R.id.srl_kehu_dingdan);
        mLlNoData=view.findViewById(R.id.ll_no_data);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }


    @Override
    public void onRefresh() {
        getData(token_id,clueId);
    }
}
