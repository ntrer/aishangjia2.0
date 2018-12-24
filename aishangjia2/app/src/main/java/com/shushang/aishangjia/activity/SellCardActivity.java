package com.shushang.aishangjia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.shushang.aishangjia.Bean.ShouKaDetail;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.adapter.SellCardAdapter;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseActivity;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;
import com.xys.libzxing.zxing.utils.JSONUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SellCardActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    private Toolbar mToolbar;
    private ProgressBar mLoading;
    private TextView mCardNum;
    private LinearLayout mDingdanTitle;
    private View mLine;
    private CircleImageView mDingjinPic;
    private TextView mMerchat;
    private TextView mPhone;
    private TextView mPhoneText;
    private RelativeLayout mCardInfo;
    private TextView mTextView,mTextView2,mTextView3,mTextView4;
    private View mLine2;
    private LinearLayout mChengjiaoliangTongji;
    private LinearLayout mChengjiaojineTongji;
    private LinearLayout mSignTongji;
    private LinearLayout mDuihuanTongji,mll_nodata;
    private RelativeLayout mCoustomerInfo;
    private RecyclerView mRvSellCard;
    private  SwipeRefreshLayout mSrlSellCard;
    private ShouKaDetail.DataBean data;
    private String cardNum,huodongId;
    private int page=1;
    private String  token_id = PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "token_id");
    private List<String> mStrings=new ArrayList<>();
    private List<ShouKaDetail.DataBean.OrderVosBean> goodsOrderVos=new ArrayList<>();
    private SellCardAdapter mMyCoustomerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar=findViewById(R.id.toolbar);
        mLoading=findViewById(R.id.loading);
        mCardNum=findViewById(R.id.card_num);
        mDingdanTitle=findViewById(R.id.dingdan_title);
        mDingjinPic=findViewById(R.id.dingjin_pic);
        mMerchat=findViewById(R.id.merchat);
        mPhone=findViewById(R.id.phone);
        mPhoneText=findViewById(R.id.phone_text);
        mCardInfo=findViewById(R.id.card_info);
        mCoustomerInfo=findViewById(R.id.coustomer_info);
        mChengjiaoliangTongji=findViewById(R.id.chengjiaoliang_tongji);
        mChengjiaojineTongji=findViewById(R.id.chengjiaojine_tongji);
        mSignTongji=findViewById(R.id.sign_tongji);
        mDuihuanTongji=findViewById(R.id.duihuan_tongji);
        mRvSellCard=findViewById(R.id.rv_sell_card);
        mCoustomerInfo.setVisibility(View.GONE);
        mSrlSellCard=findViewById(R.id.srl_sell_card);
        mSrlSellCard.setOnRefreshListener(this);
        mll_nodata=findViewById(R.id.ll_no_data);
        mTextView=findViewById(R.id.zongchengjiaoliang);
        mTextView2=findViewById(R.id.zongchengjiaojine);
        mTextView3=findViewById(R.id.qiandaocishu);
        mTextView4=findViewById(R.id.duihuancishu);
        Intent intent=getIntent();
        cardNum = intent.getStringExtra("cardNum");
        huodongId=intent.getStringExtra("huodongId");
        getData(cardNum,huodongId);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager manager=new LinearLayoutManager(this);
        mRvSellCard.setLayoutManager(manager);
        mRvSellCard.setHasFixedSize(true);
    }

    private void getData(String cardNum, String huodongId) {
        mSrlSellCard.setRefreshing(true);
        String url = BaseUrl.BASE_URL+"orderController.do?method=getShouKaDetails&token_id="+token_id+"&loginOS=2"+"&cardNum="+cardNum+"&activityId="+huodongId;
        Log.d("BaseUrl", url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            mSrlSellCard.setRefreshing(false);
                            if(response!=null){
                                try {
                                    ShouKaDetail activityList = JSONUtil.fromJson(response, ShouKaDetail.class);
                                    if(activityList.getRet().equals("200")){
                                        data = activityList.getData();
                                        if(data!=null){
                                            mCoustomerInfo.setVisibility(View.VISIBLE);
                                            showTabData(data);
                                            goodsOrderVos = data.getOrderVos();
                                            if(goodsOrderVos.size()!=0){
                                                mll_nodata.setVisibility(View.GONE);
                                                showData(goodsOrderVos);
                                            }
                                            else {
                                                mll_nodata.setVisibility(View.VISIBLE);
                                                showData(goodsOrderVos);
                                            }
                                        }
                                        else {
                                            mll_nodata.setVisibility(View.VISIBLE);
                                            mCoustomerInfo.setVisibility(View.GONE);
                                        }
                                    }
                                    else if(activityList.getRet().equals("101")){
                                        Toast.makeText(SellCardActivity.this, ""+activityList.getMsg(), Toast.LENGTH_SHORT).show();
                                        com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils.putString(SellCardActivity.this,"token_id",null);
                                        startActivity(new Intent(SellCardActivity.this, LoginActivity2.class));
                                        finish();
                                    }
                                    else if(activityList.getRet().equals("201")){
                                        mll_nodata.setVisibility(View.GONE);
                                        Toast.makeText(SellCardActivity.this, ""+activityList.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                    else if(activityList.getRet().equals("-996")){

                                        mll_nodata.setVisibility(View.GONE);
                                        Toast.makeText(SellCardActivity.this, "您无权查看", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){
                                    Toast.makeText(SellCardActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            mSrlSellCard.setRefreshing(false);
                            Toast.makeText(SellCardActivity.this, "获取数据错误了！！！！", Toast.LENGTH_SHORT).show();
                        }
                    }).error(new IError() {
                @Override
                public void onError(int code, String msg) {
                    mSrlSellCard.setRefreshing(false);
                    Toast.makeText(SellCardActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
                }
            })
                    .build()
                    .get();
        }
        catch (Exception e){
            mSrlSellCard.setRefreshing(false);
            ToastUtils.showLong("获取数据错误了");
        }
    }

    private void showTabData(ShouKaDetail.DataBean data) {
        if(data.getKahao()!=null){
            mCardNum.setText("NO."+data.getKahao());
        }

        if(data.getCustomerName()!=null){
            mMerchat.setText(data.getCustomerName());
        }

        if(data.getPhone()!=null){
            mPhoneText.setText(data.getPhone());
        }

        if(String.valueOf(data.getTotalCount())!=null){
            mTextView.setText(data.getTotalCount()+"");
        }
        else {
            mTextView.setText("0");
        }



        if(String.valueOf(data.getTotalPrice())!=null){
            mTextView2.setText(data.getTotalPrice()+"");
        }
        else {
            mTextView2.setText("0");
        }


        if(String.valueOf(data.getQdCiShu())!=null){
            mTextView3.setText(data.getQdCiShu());
        }
        else {
            mTextView3.setText("0");
        }


        if(String.valueOf(data.getDuiHuan())!=null){
            mTextView4.setText(data.getDuiHuan());
        }
        else {
            mTextView4.setText("0");
        }
    }

    private void showData(List<ShouKaDetail.DataBean.OrderVosBean> goodsOrderVos) {
        mMyCoustomerAdapter=new SellCardAdapter(R.layout.item_dingjin_new2,goodsOrderVos);
        mRvSellCard.setAdapter(mMyCoustomerAdapter);
        mRvSellCard.scrollToPosition(0);
    }


    @Override
    public int setLayout() {
        return R.layout.activity_sail_card_detail;
    }

    @Override
    public void init() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 退出activity的动画效果不起作用，要在java代码里写
     * 复写activity的finish方法，在overridePendingTransition中写入自己的动画效果
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }


    @Override
    public void onRefresh() {
        getData(cardNum, huodongId);
    }

//    @Override
//    public void onLoadMoreRequested() {
//        loadMore(cardNum);
//    }
//
//
//    private void loadMore(String cardNum) {
//        page=page+1;
//        String url = BaseUrl.BASE_URL+"goodsOrderController.do?method=getShouKaDetails&token_id="+token_id+"&loginOS=2"+"&page="+page+"&rows=10&cardNum="+cardNum;
//        try {
//            RestClient.builder()
//                    .url(url)
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String response) {
//                            if(response!=null) {
//                                Log.d("nnnnnnn",response);
//                                ShouKaDetail yiXiangJin = JSONUtil.fromJson(response, ShouKaDetail.class);
//                                if(yiXiangJin.getRet().equals("200")){
//                                    if(page>yiXiangJin.getIntmaxPage()){
//                                        page=1;
//                                        mMyCoustomerAdapter.loadMoreComplete();
//                                        mMyCoustomerAdapter.loadMoreEnd();
//                                    }
//                                    else if(yiXiangJin.getData().getGoodsOrderVos().size()!=0){
//                                        List<ShouKaDetail.DataBean.GoodsOrderVosBean> goodsOrderVos = yiXiangJin.getData().getGoodsOrderVos();
//                                        LoadMoreData(goodsOrderVos);
//                                        Log.d("load","loadMoreComplete");
//                                        mMyCoustomerAdapter.loadMoreComplete();
//                                    }
//                                    else if(yiXiangJin.getData().getGoodsOrderVos().size()==0){
//                                        Log.d("load","loadMoreEnd");
//                                        mMyCoustomerAdapter.loadMoreComplete();
//                                        mMyCoustomerAdapter.loadMoreEnd();
//                                    }
//                                }
//                                else {
//                                    mMyCoustomerAdapter.loadMoreComplete();
//                                    mMyCoustomerAdapter.loadMoreEnd();
//                                }
//                            }
//                        }
//                    })
//                    .failure(new IFailure() {
//                        @Override
//                        public void onFailure() {
//                            Toast.makeText(SellCardActivity.this, "错误了", Toast.LENGTH_SHORT).show();
//                            mMyCoustomerAdapter.loadMoreComplete();
//                            mMyCoustomerAdapter.loadMoreEnd();
//                        }
//                    })
//                    .error(new IError() {
//                        @Override
//                        public void onError(int code, String msg) {
//                            Toast.makeText(SellCardActivity.this, "错误了"+code+msg, Toast.LENGTH_SHORT).show();
//                            mMyCoustomerAdapter.loadMoreComplete();
//                            mMyCoustomerAdapter.loadMoreEnd();
//                        }
//                    })
//                    .build()
//                    .get();
//        }
//        catch (Exception e){
//
//        }
//
//
//    }
//
//
//    private void LoadMoreData(List<ShouKaDetail.DataBean.GoodsOrderVosBean> dataList) {
//        if(dataList.size()!=0){
//            mMyCoustomerAdapter.addData(dataList);
//            mMyCoustomerAdapter.loadMoreComplete();
//        }else {
//            mMyCoustomerAdapter.loadMoreComplete();
//        }
//    }
}
