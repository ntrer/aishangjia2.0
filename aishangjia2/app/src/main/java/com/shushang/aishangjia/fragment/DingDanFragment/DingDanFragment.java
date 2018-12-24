package com.shushang.aishangjia.fragment.DingDanFragment;

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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shushang.aishangjia.Bean.GoodsOrder;
import com.shushang.aishangjia.Bean.Quit;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.fragment.DingDanFragment.adapter.GoodOrderAdapter;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.ui.ExtAlertDialog;
import com.shushang.aishangjia.ui.MyFab.SuspensionFab;
import com.shushang.aishangjia.utils.Json.JSONUtil;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class DingDanFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{


    private RecyclerView mRecyclerView,mSignPeopleRecyclerView,mRecyclerView2;
    private SwipeRefreshLayout mSwipeRefreshLayout=null;
    private RelativeLayout mLoading;
    private boolean isFirst=true;
    private LinearLayout llnodata;
    private Handler fabHandler=new Handler();
    private static final int REQUEST_CODE_SIGN= 2003;
    private static final int REQUEST_CODE_ADD= 2004;
    private static final int REQUEST_CODE_ACTIVITY = 2005;
    private static final int REQUEST_CODE_DAILY = 2006;
    private static final int REQUEST_CODE_XIANSUO = 2002;
    private int page=1;
    private String resourceName=null;
    private SuspensionFab fabTop;
    List<GoodsOrder.DataListBean> SignPeopleData = new ArrayList<>();
    List<GoodsOrder.DataListBean> refreshSignPeopleData = new ArrayList<>();
    private String  token_id = PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "token_id");
    String signPelpleUrl = BaseUrl.BASE_URL+"goodsOrderController.do?method=getGoodsOrders&token_id="+token_id+"&page=1"+"&rows=10&loginOS=2";
    private GoodOrderAdapter mGoodOrderAdapter;
    public Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case 1:
                    getData();
                    break;
                case 2:
                    getData();
                    break;
            }
            return false;
        }
    });




    public DingDanFragment() {
        // Required empty public constructor
    }

    public static DingDanFragment newInstance() {
        return new DingDanFragment();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mSignPeopleRecyclerView=rootView.findViewById(R.id.rv_sign);
        mSwipeRefreshLayout=rootView.findViewById(R.id.srl_home);
        llnodata=rootView.findViewById(R.id.ll_no_data);
        mLoading=rootView.findViewById(R.id.loading);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        getData();
        EventBus.getDefault().register(this);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.activity_yi_xiang_jin, null);
        mSwipeRefreshLayout=view.findViewById(R.id.srl_home);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }


    private void getData() {
        mSwipeRefreshLayout.setRefreshing(true);
        RestClient.builder()
                .url(signPelpleUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("SignP",response);
                        mSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            try {
                                GoodsOrder yiXiangJin = JSONUtil.fromJson(response, GoodsOrder.class);
                                if(yiXiangJin.getRet().equals("200")){
                                    SignPeopleData = yiXiangJin.getDataList();
                                    if(SignPeopleData.size()!=0){
                                        showTabData(SignPeopleData);
                                        llnodata.setVisibility(View.GONE);
                                    }
                                    else {
                                        showTabData(SignPeopleData);
                                        llnodata.setVisibility(View.VISIBLE);
                                    }
                                    isFirst=false;
                                }
                                else if(yiXiangJin.getRet().equals("101")){
                                    Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                    PreferencesUtils.putString(getActivity(),"token_id",null);
                                    startActivity(new Intent(getActivity(), LoginActivity2.class));
                                    getActivity().finish();
                                }
                                else if(yiXiangJin.getRet().equals("201")){
                                    Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (Exception e){
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

    private void showTabData(final List<GoodsOrder.DataListBean> signPeopleData) {
        final LinearLayoutManager linermanager=new LinearLayoutManager(getContext());
        mSignPeopleRecyclerView.setLayoutManager(linermanager);
        mGoodOrderAdapter = new GoodOrderAdapter(R.layout.item_dingdan_new, signPeopleData,mHandler);
        mGoodOrderAdapter.setOnLoadMoreListener(this, mSignPeopleRecyclerView);
        //重复执行动画
        mGoodOrderAdapter.isFirstOnly(false);
        mSignPeopleRecyclerView.setAdapter(mGoodOrderAdapter);
        mLoading.setVisibility(View.GONE);
        mGoodOrderAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                            ExtAlertDialog.showSureDlg2(mContext, "提醒", "确定要退单吗？", "确定", false,new ExtAlertDialog.IExtDlgClick() {
                @Override
                public void Oclick(int result) {
                    if(result==1){
                        if(signPeopleData.get(position).getEnable().equals("0")){
                            ToastUtils.showLong("您已退过此单");
                        }
                        else if(signPeopleData.get(position).getEnable().equals("1")){
                            String url=BaseUrl.BASE_URL+"goodsOrderController.do?method=refundGoodsOrder&token_id="+token_id+"&goodsorderId="+signPeopleData.get(position).getGoodsorderId()+"&loginOS=2";
                            Log.d("url",url);
                            try {
                                RestClient.builder()
                                        .url(url)
                                        .success(new ISuccess() {
                                            @Override
                                            public void onSuccess(String response) {
                                                if(response!=null){
                                                    Log.d("quitUrl",response);
                                                    Quit quit = JSONUtil.fromJson(response, Quit.class);
                                                    if(quit.getRet().equals("200")){
                                                        Message message=Message.obtain();
                                                        message.what=2;
                                                        mHandler.sendMessage(message);
                                                        ToastUtils.showLong("退单成功");
                                                    }
                                                    else {
                                                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "退单失败："+quit.getMsg(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        })
                                        .build()
                                        .get();
                            }
                            catch (Exception e){
                                ToastUtils.showLong(e.toString());
                            }
                        }
                    }
                }
            });
                return false;
            }
        });
    }



    @Override
    public void onRefresh() {
        getData();
    }

    @Override
    public void onLoadMoreRequested() {
        loadMore();
    }

    private void loadMore() {
        page=page+1;
        String url= BaseUrl.BASE_URL+"phoneApi/goodsOrderController.do?method=getGoodsOrders&token_id="+token_id+"&page="+page+"&rows=10";
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            if(response!=null) {
                                Log.d("nnnnnnn",response);
                                GoodsOrder yiXiangJin = JSONUtil.fromJson(response, GoodsOrder.class);
                                if(yiXiangJin.getRet().equals("200")){
                                    if(page>yiXiangJin.getIntmaxPage()){
                                        page=1;
                                        mGoodOrderAdapter.loadMoreComplete();
                                        mGoodOrderAdapter.loadMoreEnd();
                                    }
                                    else if(yiXiangJin.getDataList().size()!=0){
                                        List<GoodsOrder.DataListBean> dataList = yiXiangJin.getDataList();
                                        LoadMoreData(dataList);
                                        Log.d("load","loadMoreComplete");
                                        mGoodOrderAdapter.loadMoreComplete();
                                    }
                                    else if(yiXiangJin.getDataList().size()==0){
                                        Log.d("load","loadMoreEnd");
                                        mGoodOrderAdapter.loadMoreComplete();
                                        mGoodOrderAdapter.loadMoreEnd();
                                    }
                                }
                                else {
                                    mGoodOrderAdapter.loadMoreComplete();
                                    mGoodOrderAdapter.loadMoreEnd();
                                }
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(getActivity(), "错误了", Toast.LENGTH_SHORT).show();
                            mGoodOrderAdapter.loadMoreComplete();
                            mGoodOrderAdapter.loadMoreEnd();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Toast.makeText(getActivity(), "错误了"+code+msg, Toast.LENGTH_SHORT).show();
                            mGoodOrderAdapter.loadMoreComplete();
                            mGoodOrderAdapter.loadMoreEnd();
                        }
                    })
                    .build()
                    .get();
        }
        catch (Exception e){

        }


    }


    private void LoadMoreData(List<GoodsOrder.DataListBean> dataList) {
        if(dataList.size()!=0){
            mGoodOrderAdapter.addData(dataList);
            mGoodOrderAdapter.loadMoreComplete();
        }else {
            mGoodOrderAdapter.loadMoreComplete();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if(messageEvent.equals("订单")){
            getData();
        }
        getData();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ACTIVITY||requestCode == REQUEST_CODE_DAILY){
            getData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
