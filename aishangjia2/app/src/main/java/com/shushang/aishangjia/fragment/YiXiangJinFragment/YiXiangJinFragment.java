package com.shushang.aishangjia.fragment.YiXiangJinFragment;

import android.app.Dialog;
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

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shushang.aishangjia.Bean.MoneyPeople;
import com.shushang.aishangjia.Bean.Quit;
import com.shushang.aishangjia.Bean.YiXiangJin;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.fragment.MoneyFragment.refreshHandler.MoneyDataRefreshHandler;
import com.shushang.aishangjia.fragment.YiXiangJinFragment.adapter.MoneyPeopleRecyclerViewAdapter2;
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

/**
 * Created by YD on 2018/9/18.
 */

public class YiXiangJinFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{
//    private Toolbar mToolbar;
    private RecyclerView mRecyclerView,mSignPeopleRecyclerView,mRecyclerView2;
    private SwipeRefreshLayout mSwipeRefreshLayout=null;
    private TextView mTextView;
    private ImageView mSearch,tabTextView;
    private ImageView mImageView;
    private MoneyDataRefreshHandler mMoneyDataRefreshHandler;
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
    private Dialog mRequestDialog;
    private YiXiangJin.DataListBean mDataListBean;
    private String orderId=null;
    private String totalPrice=null;
    private String merchantName=null;
    List<YiXiangJin.DataListBean> SignPeopleData = new ArrayList<>();
    List<MoneyPeople.DataListBean> refreshSignPeopleData = new ArrayList<>();
    private String  token_id = PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "token_id");
    private  String activity_id = PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "activityId");
    private String type=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "type");
    String signPelpleUrl = BaseUrl.BASE_URL+"orderController.do?method=getOrdersByPage&token_id="+token_id+"&page=1"+"&rows=10&loginOS=2";
    private MoneyPeopleRecyclerViewAdapter2 mMoneyPeopleRecyclerViewAdapter;
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
                case 4:
                    checkPayStatus();
                    break;
            }
            return false;
        }
    });


    public YiXiangJinFragment() {
        // Required empty public constructor
    }

    public static YiXiangJinFragment newInstance() {
        return new YiXiangJinFragment();
    }



    private void checkPayStatus() {
         if(type.equals("5")){
            ToastUtils.showLong("当前页面无法继续支付，请去活动页面选择对应活动再继续支付");
        }
//        ExtAlertDialog.showSureDlg4(getActivity(), "提醒", "当前页面无法继续支付，请去活动页面选择对应活动再继续支付", "确定",false, new ExtAlertDialog.IExtDlgClick() {
//            @Override
//            public void Oclick(int result) {
//                if(result==1){
//
//                }
//            }
//        });
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
        mRequestDialog = ExtAlertDialog.creatRequestDialog(getActivity(), getString(R.string.request));
        mRequestDialog.setCancelable(false);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        resourceName=PreferencesUtils.getString(mContext,"ResourceName");
    }

    private void getData() {
        mSwipeRefreshLayout.setRefreshing(true);
        Log.d("signPelpleUrl",signPelpleUrl);
        RestClient.builder()
                .url(signPelpleUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("SignP",response);
                        mSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            try {
                                YiXiangJin yiXiangJin = JSONUtil.fromJson(response, YiXiangJin.class);
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
                                else {
                                    llnodata.setVisibility(View.VISIBLE);
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

    private void showTabData(final List<YiXiangJin.DataListBean> signPeopleData) {
        final LinearLayoutManager linermanager=new LinearLayoutManager(getContext());
        mSignPeopleRecyclerView.setLayoutManager(linermanager);
        mMoneyPeopleRecyclerViewAdapter = new MoneyPeopleRecyclerViewAdapter2(R.layout.item_dingjin_new, signPeopleData,mHandler);
        mMoneyPeopleRecyclerViewAdapter.setOnLoadMoreListener(this, mSignPeopleRecyclerView);
        //重复执行动画
        mMoneyPeopleRecyclerViewAdapter.isFirstOnly(false);
        mSignPeopleRecyclerView.setAdapter(mMoneyPeopleRecyclerViewAdapter);
        mLoading.setVisibility(View.GONE);
        mMoneyPeopleRecyclerViewAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                ExtAlertDialog.showSureDlg(getActivity(), "提醒", "确定退掉此单吗?", "确定", new ExtAlertDialog.IExtDlgClick() {
                    @Override
                    public void Oclick(int result) {
                        if(result==1){
                            if(signPeopleData.get(position).getUserType().equals(type)){
                                if(signPeopleData.get(position).getStatus().equals("0")){
                                    final String url= BaseUrl.BASE_URL+"phoneApi/activityController.do?method=refundOrder&token_id="+token_id+"&activity_id="+activity_id+"&order_id="+signPeopleData.get(position).getOrderId();
                                    Log.d("quitUrl",url);
                                    com.xys.libzxing.zxing.net.RestClient.builder()
                                            .url(url)
                                            .success(new com.xys.libzxing.zxing.net.callback.ISuccess() {
                                                @Override
                                                public void onSuccess(String response) {
                                                    if(response!=null){
                                                        Log.d("quitUrl",response);
                                                        Quit quit = com.xys.libzxing.zxing.utils.JSONUtil.fromJson(response, Quit.class);
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
                                else if(signPeopleData.get(position).getStatus().equals("100")||signPeopleData.get(position).getStatus().equals("-300")){
                                    ToastUtils.showLong("您已使用此订金");
                                }
                                else {
                                    ToastUtils.showLong("您已退过此单");
                                }
                            }
                            else {
                                ToastUtils.showLong("您无权退此单");
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
        String url= BaseUrl.BASE_URL+"phoneApi/activityController.do?method=getOrders&token_id="+token_id+"&page="+page+"&rows=10";
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            if(response!=null) {
                                Log.d("nnnnnnn",response);
                                YiXiangJin yiXiangJin = JSONUtil.fromJson(response, YiXiangJin.class);
                                if(yiXiangJin.getRet().equals("200")){
                                    if(page>yiXiangJin.getIntmaxPage()){
                                        page=1;
                                        mMoneyPeopleRecyclerViewAdapter.loadMoreComplete();
                                        mMoneyPeopleRecyclerViewAdapter.loadMoreEnd();
                                    }
                                    else if(yiXiangJin.getDataList().size()!=0){
                                        List<YiXiangJin.DataListBean> dataList = yiXiangJin.getDataList();
                                        LoadMoreData(dataList);
                                        Log.d("load","loadMoreComplete");
                                        mMoneyPeopleRecyclerViewAdapter.loadMoreComplete();
                                    }
                                    else if(yiXiangJin.getDataList().size()==0){
                                        Log.d("load","loadMoreEnd");
                                        mMoneyPeopleRecyclerViewAdapter.loadMoreComplete();
                                        mMoneyPeopleRecyclerViewAdapter.loadMoreEnd();
                                    }
                                }
                                else {
                                    mMoneyPeopleRecyclerViewAdapter.loadMoreComplete();
                                    mMoneyPeopleRecyclerViewAdapter.loadMoreEnd();
                                }
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(getActivity(), "错误了", Toast.LENGTH_SHORT).show();
                            mMoneyPeopleRecyclerViewAdapter.loadMoreComplete();
                            mMoneyPeopleRecyclerViewAdapter.loadMoreEnd();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Toast.makeText(getActivity(), "错误了"+code+msg, Toast.LENGTH_SHORT).show();
                            mMoneyPeopleRecyclerViewAdapter.loadMoreComplete();
                            mMoneyPeopleRecyclerViewAdapter.loadMoreEnd();
                        }
                    })
                    .build()
                    .get();
        }
        catch (Exception e){

        }


    }


    private void LoadMoreData(List<YiXiangJin.DataListBean> dataList) {
            if(dataList.size()!=0){
                mMoneyPeopleRecyclerViewAdapter.addData(dataList);
                mMoneyPeopleRecyclerViewAdapter.loadMoreComplete();
            }else {
                mMoneyPeopleRecyclerViewAdapter.loadMoreComplete();
            }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if(messageEvent.getMessage().equals("添加订金")){
            getData();
        }
        else if(messageEvent.getMessage().equals("活动订金")){
            getData();
        }
    }




//    @Override
//    public void onFabClick(FloatingActionButton fab, Object tag) {
//        if (tag.equals(1)) {
//            fabHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    fabTop.closeAnimate();
//                }
//            },1000);
//            startActivityForResult(new Intent(getActivity(), SignActivity.class),REQUEST_CODE_SIGN);
//        }else if (tag.equals(2)){
//            fabHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    fabTop.closeAnimate();
//                }
//            },1000);
//            startActivityForResult(new Intent(getActivity(), AppPeopleActivity.class),REQUEST_CODE_ADD);
//        }
//        else if (tag.equals(3)){
//            fabHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    fabTop.closeAnimate();
//                }
//            },1000);
//            startActivityForResult(new Intent(getActivity(), XiansuoActivity.class),REQUEST_CODE_ADD);
//        }
//        else if (tag.equals(4)){
//            fabHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    fabTop.closeAnimate();
//                }
//            },1000);
//            //表示所有权限都授权了
//            Intent openCameraIntent = new Intent(getActivity(), ProActivityActivity2.class);
//            openCameraIntent.putExtra("type", "3");
//            openCameraIntent.putExtra("event","7");
//            startActivityForResult(openCameraIntent, REQUEST_CODE_ACTIVITY );
//        }
//        else if (tag.equals(5)){
//            fabHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    fabTop.closeAnimate();
//                }
//            },1000);
//            startActivityForResult(new Intent(getActivity(), DailyOrderActivity.class),REQUEST_CODE_DAILY);
//        }
//    }

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
