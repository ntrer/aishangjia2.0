package com.shushang.aishangjia.fragment.HuodongDingjinFragment;

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
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shushang.aishangjia.Bean.HuoDongTongji;
import com.shushang.aishangjia.Bean.Quit;
import com.shushang.aishangjia.Bean.YiXiangJin;
import com.shushang.aishangjia.Bean.payStatus;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.fragment.HuodongDingjinFragment.adapter.HoudongDingjinAdapter;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.ui.ExtAlertDialog;
import com.shushang.aishangjia.utils.Json.JSONUtil;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.ui.dialog.ActionSheetDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class HoudongDingjinFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{

    List<YiXiangJin.DataListBean> SignPeopleData = new ArrayList<>();
    private HoudongDingjinAdapter mMoneyPeopleRecyclerViewAdapter;
    RecyclerView mRvDingjin;
    SwipeRefreshLayout mSrlDingjin;
    LinearLayout mLlNoData;
    private int page=1;
    private View mView;
    private boolean isAll=false;
    private TextView mTextView1,mTextView2,mTextView3,mTextView4;
    private String  token_id = PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "token_id");
    private  String activity_id,merchantId;
    private String signPelpleUrl;
    private String url;
    private String cjsjSort="desc";
    private String priceSort,status;
    private ImageView mImageView1,mImageView2;
    private String syyroleType=null;
    private String orderId=null;
    private String totalPrice=null;
    private String merchantName=null;
    private String openWxPay=null;
    private String openZfbPay=null;
    private YiXiangJin.DataListBean mDataListBean;
    private Dialog mRequestDialog;
    private String type=PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "type");

    public Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(final Message message) {
            switch (message.what){
                case 1:
                    getTongjiData(activity_id, merchantId);
                    Log.d("请求数据","11111");
                    break;
                case 2:
                    getTongjiData(activity_id, merchantId);
                    Log.d("请求数据","22222");
                    break;
                case 3:
                    checkPayStatus(message);
                    break;
                case 4:
                   ToastUtils.showLong("您已支付");
                    break;
            }
            return false;
        }
    });

    private void checkPayStatus(Message message) {
        mRequestDialog.show();
        mDataListBean= (YiXiangJin.DataListBean) message.obj;
        orderId=mDataListBean.getOrderId();
        totalPrice=String.valueOf(mDataListBean.getTotalPrice());
        merchantName=mDataListBean.getOrderMerchantName();
        String url ="http://payment.ejjzcloud.com:44000/asj-pay/payController?method=getOrderStatus&orderId=" + orderId;
        Log.d("getMerchats", url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            if(response!=null){
                                try {
                                    payStatus activityList = JSONUtil.fromJson(response, payStatus.class);
                                    if(activityList.getRet().equals("200")){
                                       if(activityList.getCode()==1){
                                           if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                               mRequestDialog.dismiss();
                                           }
                                           ToastUtils.showLong("您已支付成功");
                                       }
                                       else if(activityList.getCode()==2){
                                           if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                               mRequestDialog.dismiss();
                                           }
                                           ToastUtils.showLong("支付数据异常");
                                       }
                                       else if(activityList.getCode()==-1){
                                           if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                               mRequestDialog.dismiss();
                                           }
                                           new ActionSheetDialog(getActivity())
                                                   .builder()
                                                   .setCancelable(false)
                                                   .setCanceledOnTouchOutside(true)
                                                   .addSheetItem("继续支付", ActionSheetDialog.SheetItemColor.Blue,
                                                           new ActionSheetDialog.OnSheetItemClickListener() {
                                                               @Override
                                                               public void onClick(int which) {
                                                                   final Intent intent=new Intent(getActivity(),CaptureActivity.class);
                                                                   intent.putExtra("orderId",orderId);
                                                                   intent.putExtra("totalPrice",totalPrice);
                                                                   intent.putExtra("merchantName",merchantName);
                                                                   startActivityForResult(intent,9012);
                                                               }
                                                           })
                                                   .show();
                                       }
                                       else if(activityList.getCode()==-2){
                                           if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                               mRequestDialog.dismiss();
                                           }
                                           ToastUtils.showLong("支付数据异常");
                                       }
                                       else if(activityList.getCode()==0){
                                           if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                               mRequestDialog.dismiss();
                                           }
                                           new ActionSheetDialog(getActivity())
                                                   .builder()
                                                   .setCancelable(false)
                                                   .setCanceledOnTouchOutside(true)
                                                   .addSheetItem("继续支付", ActionSheetDialog.SheetItemColor.Blue,
                                                           new ActionSheetDialog.OnSheetItemClickListener() {
                                                               @Override
                                                               public void onClick(int which) {
                                                                   final Intent intent=new Intent(getActivity(),CaptureActivity.class);
                                                                   intent.putExtra("orderId",orderId);
                                                                   intent.putExtra("totalPrice",totalPrice);
                                                                   intent.putExtra("merchantName",merchantName);
                                                                   startActivityForResult(intent,9012);
                                                               }
                                                           })
                                                   .show();
                                       }
                                    }
                                }
                                catch (Exception e){
                                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                        mRequestDialog.dismiss();
                                    }
                                    Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                mRequestDialog.dismiss();
                            }
                            Toast.makeText(getActivity(), "获取数据错误了！！！！", Toast.LENGTH_SHORT).show();
                        }
                    }).error(new IError() {
                @Override
                public void onError(int code, String msg) {
                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                        mRequestDialog.dismiss();
                    }
                    Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
                }
            })
                    .build()
                    .get();
        }
        catch (Exception e){
            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                mRequestDialog.dismiss();
            }
            ToastUtils.showLong("获取数据错误了");
        }
    }

    public HoudongDingjinFragment() {
        // Required empty public constructor
    }

    public static HoudongDingjinFragment newInstance() {
        return new HoudongDingjinFragment();
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        EventBus.getDefault().register(this);
        openWxPay=PreferencesUtils.getString(mContext,"openWxPay");
        openZfbPay=PreferencesUtils.getString(mContext,"openZfbPay");
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_huodongdingjin, null);
        mRvDingjin=view.findViewById(R.id.rv_dingjin);
        mSrlDingjin=view.findViewById(R.id.srl_dingjin);
        mSrlDingjin.setOnRefreshListener(this);
        mLlNoData=view.findViewById(R.id.ll_no_data);
        mRequestDialog = ExtAlertDialog.creatRequestDialog(getActivity(), getString(R.string.request));
        mRequestDialog.setCancelable(false);
        return view;
    }



    private void getTongjiData(final String activity_id, final String merchantId){
        if(isAll){
           signPelpleUrl = BaseUrl.BASE_URL+"activityController.do?method=getStatisticsForActivity&token_id="+token_id+"&activityId="+ activity_id +"&loginOS=2";

        }
        else {
            signPelpleUrl = BaseUrl.BASE_URL+"activityController.do?method=getStatisticsForActivity&token_id="+token_id+"&activityId="+ activity_id +"&merchantId="+merchantId+"&loginOS=2";

        }
        mSrlDingjin.setRefreshing(true);
        Log.d("signPelpleUrl2",signPelpleUrl);
        RestClient.builder()
                .url(signPelpleUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mSrlDingjin.setRefreshing(false);
                        if (response != null) {
                            try {
                                HuoDongTongji yiXiangJin = JSONUtil.fromJson(response, HuoDongTongji.class);
                                if(yiXiangJin.getRet().equals("200")){
                                    if(yiXiangJin.getData()!=null){
                                        showTongjiData(yiXiangJin);
                                        getData(activity_id,merchantId,cjsjSort,priceSort,status);
                                    }
                                    else {
                                        showTongjiData(yiXiangJin);
                                        getData(activity_id,merchantId,cjsjSort,priceSort,status);
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
                                mSrlDingjin.setRefreshing(false);
                                Log.d("出错了",e.toString());
                            }
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        mSrlDingjin.setRefreshing(false);
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        mSrlDingjin.setRefreshing(false);
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }

    private void showTongjiData(HuoDongTongji yiXiangJin) {
        mView=View.inflate(MyApplication.getInstance().getApplicationContext(), R.layout.heardview_activity_dingjin,null);
        mTextView1=mView.findViewById(R.id.shanghu_text);
        mTextView2=mView.findViewById(R.id.chengdan_text);
        mTextView3=mView.findViewById(R.id.chengdanjine_text);
        mTextView4=mView.findViewById(R.id.pingjun_text);
        mImageView1=mView.findViewById(R.id.kehu_paixu);
        mImageView2=mView.findViewById(R.id.kehu_shaixuan);
        mTextView1.setText(yiXiangJin.getData().getMerchantCount());
        mTextView2.setText(yiXiangJin.getData().getChengDanCount());
        mTextView3.setText(String.valueOf(Math.round(Double.parseDouble(yiXiangJin.getData().getTotalPrice()))));
        mTextView4.setText(String.valueOf(Math.round(Double.parseDouble(yiXiangJin.getData().getTotalPrice())/Double.parseDouble(yiXiangJin.getData().getChengDanCount()))));
        mImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(getActivity())
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("创建时间正序", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        cjsjSort="asc";
                                        priceSort=null;
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .addSheetItem("创建时间倒序", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        cjsjSort="desc";
                                        priceSort=null;
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .addSheetItem("成交金额正序", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        priceSort="asc";
                                        cjsjSort=null;
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .addSheetItem("成交金额倒序", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        priceSort="desc";
                                        cjsjSort=null;
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .show();
            }
        });

        mImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(getActivity())
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("正常", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        status="0";
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .addSheetItem("完成", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        status="100";
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .addSheetItem("待审核", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        status="50";
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .addSheetItem("被拒绝", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        status="-100";
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .addSheetItem("退单", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        status="-200";
                                        getTongjiData(activity_id,merchantId);
//                                        getData(activity_id,merchantId,signStatus,sort,lipin);
                                    }
                                })
                        .show();
            }
        });

    }


    private void getData(String activity_id, String merchantId,String cjsjSort,String priceSort,String status) {
        if(isAll){
            if(priceSort==null&&status==null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2";

            }
            else if(priceSort==null&&status!=null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&status="+status;
            }
            else if(priceSort!=null&&status==null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2"+"&priceSort="+priceSort;
            }
            else if(priceSort!=null&&status!=null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2"+"&priceSort="+priceSort+"&status="+status;
            }
            else if(priceSort!=null&&status==null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&priceSort="+priceSort;
            }
            else if(priceSort==null&&status==null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort;
            }
            else if(priceSort==null&&status!=null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&status="+status;
            }
            else if(priceSort!=null&&status!=null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&status="+status+"&priceSort="+priceSort;
            }

        }
        else {
            if(priceSort==null&&status==null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2"+ "&merchantId=" + merchantId;

            }
            else if(priceSort==null&&status!=null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&status="+status+ "&merchantId=" + merchantId;
            }
            else if(priceSort!=null&&status==null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2"+"&priceSort="+priceSort+ "&merchantId=" + merchantId;
            }
            else if(priceSort!=null&&status!=null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2"+"&priceSort="+priceSort+"&status="+status+ "&merchantId=" + merchantId;
            }
            else if(priceSort!=null&&status==null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&priceSort="+priceSort+ "&merchantId=" + merchantId;
            }
            else if(priceSort==null&&status==null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+ "&merchantId=" + merchantId;
            }
            else if(priceSort==null&&status!=null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&status="+status+ "&merchantId=" + merchantId;
            }
            else if(priceSort!=null&&status!=null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page=1"+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&status="+status+"&priceSort="+priceSort+ "&merchantId=" + merchantId;
            }

        }
        Log.d("sssss",url);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("SignP",response);
                        if (response != null) {
                            try {
                                YiXiangJin yiXiangJin = JSONUtil.fromJson(response, YiXiangJin.class);
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
                                mSrlDingjin.setRefreshing(false);
                                Log.d("出错了",e.toString());
                            }
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        mSrlDingjin.setRefreshing(false);
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                        mSrlDingjin.setRefreshing(false);
                    }
                })
                .build()
                .get();
    }

    private void showTabData(final List<YiXiangJin.DataListBean> signPeopleData) {
        final LinearLayoutManager linermanager=new LinearLayoutManager(getContext());
        mRvDingjin.setLayoutManager(linermanager);
        mMoneyPeopleRecyclerViewAdapter = new HoudongDingjinAdapter(R.layout.item_dingjin_new, signPeopleData,mHandler);
        mMoneyPeopleRecyclerViewAdapter.setOnLoadMoreListener(this, mRvDingjin);
        mMoneyPeopleRecyclerViewAdapter.addHeaderView(mView);
        //重复执行动画
        mMoneyPeopleRecyclerViewAdapter.isFirstOnly(false);
        mRvDingjin.setAdapter(mMoneyPeopleRecyclerViewAdapter);
        mMoneyPeopleRecyclerViewAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, final View view, final int position) {
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
            if(priceSort==null&&status==null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2";

            }
            else if(priceSort==null&&status!=null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&status="+status;
            }
            else if(priceSort!=null&&status==null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2"+"&priceSort="+priceSort;
            }
            else if(priceSort!=null&&status!=null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2"+"&priceSort="+priceSort+"&status="+status;
            }
            else if(priceSort!=null&&status==null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&priceSort="+priceSort;
            }
            else if(priceSort==null&&status==null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort;
            }
            else if(priceSort==null&&status!=null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&status="+status;
            }
            else if(priceSort!=null&&status!=null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&status="+status+"&priceSort="+priceSort;
            }

            loadMore(url);
        }
        else {
            if(priceSort==null&&status==null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2"+ "&merchantId=" + merchantId;

            }
            else if(priceSort==null&&status!=null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&status="+status+ "&merchantId=" + merchantId;
            }
            else if(priceSort!=null&&status==null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2"+"&priceSort="+priceSort+ "&merchantId=" + merchantId;
            }
            else if(priceSort!=null&&status!=null&&cjsjSort==null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2"+"&priceSort="+priceSort+"&status="+status+ "&merchantId=" + merchantId;
            }
            else if(priceSort!=null&&status==null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&priceSort="+priceSort+ "&merchantId=" + merchantId;
            }
            else if(priceSort==null&&status==null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+ "&merchantId=" + merchantId;
            }
            else if(priceSort==null&&status!=null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&status="+status+ "&merchantId=" + merchantId;
            }
            else if(priceSort!=null&&status!=null&&cjsjSort!=null){
                url = BaseUrl.BASE_URL+"orderController.do?method=getOrdersForActiivity&token_id="+token_id
                        +"&page="+page+"&rows=10&activityId="+activity_id +"&loginOS=2&cjsjSort="+cjsjSort+"&status="+status+"&priceSort="+priceSort+ "&merchantId=" + merchantId;
            }
            loadMore(url);
        }

    }

    private void loadMore(String url) {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==9012){
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
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if(messageEvent.getMessage().equals("活动订金")){
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




    private void LoadMoreData(List<YiXiangJin.DataListBean> dataList) {
        if(dataList.size()!=0){
            mMoneyPeopleRecyclerViewAdapter.addData(dataList);
            mMoneyPeopleRecyclerViewAdapter.loadMoreComplete();
        }else {
            mMoneyPeopleRecyclerViewAdapter.loadMoreComplete();
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
