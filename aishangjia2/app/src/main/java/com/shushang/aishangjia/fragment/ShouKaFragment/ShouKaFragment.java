package com.shushang.aishangjia.fragment.ShouKaFragment;

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
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shushang.aishangjia.Bean.HuoDongTongji;
import com.shushang.aishangjia.Bean.ShouKaList;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.activity.SellCardActivity;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.fragment.ShouKaFragment.adapter.ShouKaAdapter;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.utils.Json.JSONUtil;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;
import com.xys.libzxing.zxing.ui.dialog.ActionSheetDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ShouKaFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{

    List<ShouKaList.DataListBean> SignPeopleData = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ShouKaAdapter mMyCoustomerAdapter;
    private View mView;
    LinearLayout mLlNoData;
    private TextView mTextView1,mTextView2,mTextView3,mTextView4;
    private int page=1;
    private boolean isAll=false;
    private String  token_id = PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "token_id");
    private  String activity_id,merchantId;
    private String signPelpleUrl;
    private String url;
    private ImageView mImageView1,mImageView2;
    private String signStatus,lipin;
    private String sort="desc";
    private String type=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "type");

    public ShouKaFragment() {
        // Required empty public constructor
    }

    public static ShouKaFragment newInstance() {
        return new ShouKaFragment();
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        EventBus.getDefault().register(this);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shouka, null);
        mRecyclerView=view.findViewById(R.id.rv_shouka);
        mSwipeRefreshLayout=view.findViewById(R.id.srl_shouka);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mLlNoData=view.findViewById(R.id.ll_no_data);
        return view;
    }

    private void getData(String activity_id, String merchantId,String signStatus,String sort,String lipin) {
        if(isAll){
             if(signStatus==null&&lipin==null){
                 url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                         +"&page=1"+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort;
             }
             else if(signStatus==null&&lipin!=null){
                 url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                         +"&page=1"+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort+"&lipin="+lipin;

             }
             else if(signStatus!=null&&lipin==null){
                 url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                         +"&page=1"+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort+"&signStatus="+signStatus;
             }
             else if(signStatus!=null&&lipin!=null){
                 url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                         +"&page=1"+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort+"&signStatus="+signStatus+"&lipin="+lipin;
             }
        }
        else {
            if(signStatus==null&&lipin==null){
                url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort+"&merchantId="+merchantId;
            }
            else if(signStatus==null&&lipin!=null){
                url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort+"&lipin="+lipin+"&merchantId="+merchantId;

            }
            else if(signStatus!=null&&lipin==null){
                url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort+"&signStatus="+signStatus+"&merchantId="+merchantId;
            }
            else if(signStatus!=null&&lipin!=null){
                url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort+"&signStatus="+signStatus+"&lipin="+lipin+"&merchantId="+merchantId;
             }
            }
        Log.d("getshouka",url);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("SignP",response);
                        mSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            try {
                                ShouKaList yiXiangJin = JSONUtil.fromJson(response, ShouKaList.class);
                                if(yiXiangJin.getRet().equals("200")){
                                    SignPeopleData = yiXiangJin.getDataList();
                                    if(SignPeopleData.size()!=0){
                                        showTabData(SignPeopleData);
                                        mLlNoData.setVisibility(View.GONE);
                                    }
                                    else {
                                        showTabData(SignPeopleData);
                                        mLlNoData.setVisibility(View.VISIBLE);
                                    }
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



    private void getTongjiData(final String activity_id, final String merchantId){
        if(isAll){
            signPelpleUrl = BaseUrl.BASE_URL+"activityController.do?method=getStatisticsForActivity&token_id="+token_id+"&activityId="+ activity_id+"&loginOS=2";
        }
        else {
             signPelpleUrl = BaseUrl.BASE_URL+"activityController.do?method=getStatisticsForActivity&token_id="+token_id+"&activityId="+ activity_id +"&merchantId="+merchantId+"&loginOS=2";
        }

        mSwipeRefreshLayout.setRefreshing(true);
        Log.d("getTongjiData",signPelpleUrl);
        RestClient.builder()
                .url(signPelpleUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("SignP",response);
                        mSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            try {
                                HuoDongTongji yiXiangJin = JSONUtil.fromJson(response, HuoDongTongji.class);
                                if(yiXiangJin.getRet().equals("200")){
                                    if(yiXiangJin.getData()!=null){
                                        showTongjiData(yiXiangJin);
                                        if(signStatus==null&&lipin==null){
                                            getData(activity_id,merchantId,null,sort,null);
                                        }
                                        else if(signStatus==null&&lipin!=null){
                                            getData(activity_id,merchantId,null,sort,lipin);
                                        }
                                        else if(signStatus!=null&&lipin==null){
                                            getData(activity_id,merchantId,signStatus,sort,null);
                                        }
                                        else if(signStatus!=null&&lipin!=null){
                                            getData(activity_id,merchantId,signStatus,sort,lipin);
                                        }
                                    }
                                    else {
                                        showTabData(SignPeopleData);
                                        if(signStatus==null&&lipin==null){
                                            getData(activity_id,merchantId,null,sort,null);
                                        }
                                        else if(signStatus==null&&lipin!=null){
                                            getData(activity_id,merchantId,null,sort,lipin);
                                        }
                                        else if(signStatus!=null&&lipin==null){
                                            getData(activity_id,merchantId,signStatus,sort,null);
                                        }
                                        else if(signStatus!=null&&lipin!=null){
                                            getData(activity_id,merchantId,signStatus,sort,lipin);
                                        }
                                    }
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
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }

    private void showTongjiData(HuoDongTongji yiXiangJin) {
        mView=View.inflate(MyApplication.getInstance().getApplicationContext(), R.layout.heardview_activity_new,null);
        mTextView1=mView.findViewById(R.id.yaoqing_text);
        mTextView2=mView.findViewById(R.id.merchat_text);
        mTextView3=mView.findViewById(R.id.sign_text);
        mTextView4=mView.findViewById(R.id.duihuan_text);
        mImageView1=mView.findViewById(R.id.kehu_paixu);
        mImageView2=mView.findViewById(R.id.kehu_shaixuan);
        mTextView1.setText(yiXiangJin.getData().getShoukaCount());
        mTextView2.setText(yiXiangJin.getData().getMerchantCount());
        mTextView3.setText(yiXiangJin.getData().getQdCount());
        mTextView4.setText(yiXiangJin.getData().getLipinCount());

        mImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(getActivity())
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("全部", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        signStatus=null;
                                        lipin=null;
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .addSheetItem("已签到", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        signStatus="1";
                                        lipin=null;
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .addSheetItem("未签到", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        signStatus="2";
                                        lipin=null;
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .addSheetItem("未兑换礼品", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        signStatus=null;
                                        lipin="0";
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .addSheetItem("已兑换礼品", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        signStatus=null;
                                        lipin="1";
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .show();
            }
        });

        mImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(getActivity())
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("正序", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        sort="asc";
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .addSheetItem("倒序", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        sort="desc";
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .show();
            }
        });

    }


    private void showTabData(final List<ShouKaList.DataListBean> signPeopleData) {
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mMyCoustomerAdapter=new ShouKaAdapter(R.layout.item_activity_detail_new,signPeopleData);
        mRecyclerView.setAdapter(mMyCoustomerAdapter);
        mMyCoustomerAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.scrollToPosition(0);
        mMyCoustomerAdapter.addHeaderView(mView);
        mMyCoustomerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(getActivity(),SellCardActivity.class);
                intent.putExtra("cardNum",signPeopleData.get(position).getCardNum());
                intent.putExtra("huodongId",activity_id);
                startActivity(intent);
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if(messageEvent.getMessage().equals("活动拓客")){
           if(isAll){
               activity_id=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "huodongactivityId");
               getTongjiData(activity_id, "");
           }
           else {
               activity_id=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "huodongactivityId");
               merchantId=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "huodongmerchantId");
               getTongjiData(activity_id,merchantId);
           }
        }
        else if(messageEvent.getMessage().equals("获取所有商户信息")){
            isAll=true;
            activity_id=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "huodongactivityId");
            getTongjiData(activity_id, "");
        }
        else if(messageEvent.getMessage().equals("获取商户信息")){
            isAll=false;
            activity_id=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "huodongactivityId");
            merchantId=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "huodongmerchantId");
            getTongjiData(activity_id,merchantId);
        }
        else if(messageEvent.getMessage().equals("选择活动")){
            activity_id=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "huodongactivityId");
            merchantId=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "huodongmerchantId");
            getTongjiData(activity_id,merchantId);
        }
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
        if(isAll){
            getTongjiData(activity_id, "");
        }
        else {
            getTongjiData(activity_id, merchantId);
        }

    }

    @Override
    public void onLoadMoreRequested() {
        page=page+1;
        if(isAll){
            if(signStatus==null&&lipin==null){
                url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort;
            }
            else if(signStatus==null&&lipin!=null){
                url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort+"&lipin="+lipin;

            }
            else if(signStatus!=null&&lipin==null){
                url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort+"&signStatus="+signStatus;
            }
            else if(signStatus!=null&&lipin!=null){
                url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort+"&signStatus="+signStatus+"&lipin="+lipin;
            }
            loadMore(url);
        }
        else {
            if(signStatus==null&&lipin==null){
                url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort+"&merchantId="+merchantId;
            }
            else if(signStatus==null&&lipin!=null){
                url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort+"&lipin="+lipin+"&merchantId="+merchantId;

            }
            else if(signStatus!=null&&lipin==null){
                url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort+"&signStatus="+signStatus+"&merchantId="+merchantId;
            }
            else if(signStatus!=null&&lipin!=null){
                url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id+"&loginOS=2&sort="+sort+"&signStatus="+signStatus+"&lipin="+lipin+"&merchantId="+merchantId;
            }
            loadMore(url);
        }

    }


    private void loadMore(String url) {
//        page=page+1;
//        String url = BaseUrl.BASE_URL+"customerActionController.do?method=getCustomerActionsByActivityId&token_id="+token_id+"&page="+page+"&rows=10&activityId="+activity_id+"&merchantId="+merchantId+"&loginOS=2";
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            if(response!=null) {
                                Log.d("nnnnnnn",response);
                                ShouKaList yiXiangJin = JSONUtil.fromJson(response, ShouKaList.class);
                                if(yiXiangJin.getRet().equals("200")){
                                    if(page>yiXiangJin.getIntmaxPage()){
                                        page=1;
                                        mMyCoustomerAdapter.loadMoreComplete();
                                        mMyCoustomerAdapter.loadMoreEnd();
                                    }
                                    else if(yiXiangJin.getDataList().size()!=0){
                                        List<ShouKaList.DataListBean> dataList = yiXiangJin.getDataList();
                                        LoadMoreData(dataList);
                                        Log.d("load","loadMoreComplete");
                                        mMyCoustomerAdapter.loadMoreComplete();
                                    }
                                    else if(yiXiangJin.getDataList().size()==0){
                                        Log.d("load","loadMoreEnd");
                                        mMyCoustomerAdapter.loadMoreComplete();
                                        mMyCoustomerAdapter.loadMoreEnd();
                                    }
                                }
                                else {
                                    mMyCoustomerAdapter.loadMoreComplete();
                                    mMyCoustomerAdapter.loadMoreEnd();
                                }
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(getActivity(), "错误了", Toast.LENGTH_SHORT).show();
                            mMyCoustomerAdapter.loadMoreComplete();
                            mMyCoustomerAdapter.loadMoreEnd();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Toast.makeText(getActivity(), "错误了"+code+msg, Toast.LENGTH_SHORT).show();
                            mMyCoustomerAdapter.loadMoreComplete();
                            mMyCoustomerAdapter.loadMoreEnd();
                        }
                    })
                    .build()
                    .get();
        }
        catch (Exception e){

        }


    }


    private void LoadMoreData(List<ShouKaList.DataListBean> dataList) {
        if(dataList.size()!=0){
            mMyCoustomerAdapter.addData(dataList);
            mMyCoustomerAdapter.loadMoreComplete();
        }else {
            mMyCoustomerAdapter.loadMoreComplete();
        }
    }
}
