package com.shushang.aishangjia.fragment.YiXiangJinFragment.adapter;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.aishangjia.Bean.YiXiangJin;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by YD on 2018/7/19.
 */

public class MoneyPeopleRecyclerViewAdapter2 extends BaseQuickAdapter<YiXiangJin.DataListBean,BaseViewHolder> {

    private Handler mHandler;
    private String syyroleType = null;
    private String openWxPay = null;
    private String openZfbPay = null;
    public MoneyPeopleRecyclerViewAdapter2(@LayoutRes int layoutResId, @Nullable List<YiXiangJin.DataListBean> data, Handler handler) {
        super(layoutResId, data);
        this.mHandler=handler;
        syyroleType = PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "syyroleType");
        openWxPay = com.xys.libzxing.zxing.utils.PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "openWxPay");
        openZfbPay = com.xys.libzxing.zxing.utils.PreferencesUtils.getString(MyApplication.getInstance().getApplicationContext(), "openZfbPay");
    }

    @Override
    protected void convert(final BaseViewHolder helper, final YiXiangJin.DataListBean item) {
        helper.setText(R.id.people,item.getCustomerName());
        helper.setText(R.id.phone,item.getCustomerPhone());


        if (item.getOrderNumber() != null) {
            if(!String.valueOf(item.getOrderNumber()).equals("")){
                helper.setText(R.id.dingjinbianhao, item.getOrderNumber() + "");
            }
        }
        else if(String.valueOf(item.getOrderNum())!=null){
            if(!String.valueOf(item.getOrderNum()).equals("")){
                helper.setText(R.id.dingjinbianhao, item.getOrderNum() + "");
            }
        }
        else {
            helper.setText(R.id.dingjinbianhao,  "无");
        }

        if (item.getPayType() != null) {
            if (item.getPayType().equals("1")) {
                helper.setText(R.id.paytype, "现金");
            } else if (item.getPayType().equals("2")) {
                helper.setText(R.id.paytype, "刷卡");
            } else if (item.getPayType().equals("3")) {
                helper.setText(R.id.paytype, "支付宝");
            } else if (item.getPayType().equals("4")) {
                helper.setText(R.id.paytype, "微信");
            } else if (item.getPayType().equals("5")) {
                helper.setText(R.id.paytype, "其他");
            }


            if (item.getPayStatus() == 0) {
                if (item.getPayType().equals("21")) {
                    helper.setText(R.id.paytype, "支付宝扫码收(未付款)");
                    helper.getView(R.id.dingjin_item).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Message message = Message.obtain();
                            message.what = 4;
                            mHandler.sendMessage(message);

                        }
                    });
                } else if (item.getPayType().equals("22")) {
                    helper.setText(R.id.paytype, "微信扫码收(未付款)");
                    helper.getView(R.id.dingjin_item).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Message message = Message.obtain();
                            message.what = 4;
                            mHandler.sendMessage(message);

                        }
                    });
                }
            } else if (String.valueOf(item.getPayStatus()).equals("10")) {
                if (item.getPayType().equals("21")) {
                    helper.setText(R.id.paytype, "支付宝扫码收(支付中)");
                    helper.getView(R.id.dingjin_item).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Message message = Message.obtain();
                            message.what = 4;
                            mHandler.sendMessage(message);

                        }
                    });
                } else if (item.getPayType().equals("22")) {
                    helper.setText(R.id.paytype, "微信扫码收(支付中)");
                    helper.getView(R.id.dingjin_item).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Message message = Message.obtain();
                            message.what = 4;
                            mHandler.sendMessage(message);

                        }
                    });
                }
            } else if (String.valueOf(item.getPayStatus()).equals("100")) {
                if (item.getPayType().equals("21")) {
                    helper.setText(R.id.paytype, "支付宝扫码收(支付成功)");
                                helper.getView(R.id.dingjin_item).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Message message = Message.obtain();
                                        message.what = 4;
                                        mHandler.sendMessage(message);

                                    }
                                });
                } else if (item.getPayType().equals("22")) {
                    helper.setText(R.id.paytype, "微信扫码收(支付成功)");
                                helper.getView(R.id.dingjin_item).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Message message = Message.obtain();
                                        message.what = 4;
                                        mHandler.sendMessage(message);

                                    }
                                });
                }
            } else if (String.valueOf(item.getPayStatus()).equals("-100")) {
                if (item.getPayType().equals("21")) {
                    helper.setText(R.id.status, "支付宝扫码收(支付失败)");
                                if (item.getStatus().equals("0")) {
                                    helper.getView(R.id.dingjin_item).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Message message = Message.obtain();
                                            message.what = 4;
                                            mHandler.sendMessage(message);

                                        }
                                    });

                    }
                }
                if (item.getPayType().equals("22")) {
                    helper.setText(R.id.status, "微信扫码收(支付失败)");
                                if (item.getStatus().equals("0")) {
                                    helper.getView(R.id.dingjin_item).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Message message = Message.obtain();
                                            message.what = 4;
                                            mHandler.sendMessage(message);

                                        }
                                    });

                    }
                }

            }
        }






        if(item.getUserType()!=null){
            if(item.getUserType().equals("2")){
                helper.getView(R.id.dingjin_pic).setVisibility(View.GONE);
                helper.getView(R.id.dingjin_pic2).setVisibility(View.VISIBLE);
            }
            else if(item.getUserType().equals("5")){
                helper.getView(R.id.dingjin_pic).setVisibility(View.VISIBLE);
                helper.getView(R.id.dingjin_pic2).setVisibility(View.GONE);
            }
        }

//        if(String.valueOf(item.getOpenPay())!=null){
//            if(item.getPayType()!=null){
//                if(item.getPayType().equals("1")){
//                    helper.setText(R.id.paytype,"现金");
//                }
//                else if(item.getPayType().equals("2")){
//                    helper.setText(R.id.paytype,"刷卡");
//                }
//                else if(item.getPayType().equals("3")){
//                    helper.setText(R.id.paytype,"支付宝");
//                }
//                else if(item.getPayType().equals("4")){
//                    helper.setText(R.id.paytype,"微信");
//                }
//                else if(item.getPayType().equals("5")){
//                    helper.setText(R.id.paytype,"其他");
//                }
//
//            }
//            else {
//                helper.setText(R.id.paytype,"未知");
//            }
//
//            if(item.getOpenPay()==1){
//                if(String.valueOf(item.getPayStatus())!=null){
//                    helper.getView(R.id.status).setVisibility(View.VISIBLE);
//                    if(item.getPayStatus()==0){
//                        helper.setText(R.id.status,"未支付");
//                    }
//                    else if(item.getPayStatus()==10){
//                        helper.setText(R.id.status,"支付中");
//                    }
//                    else if(item.getPayStatus()==100){
//                        helper.setText(R.id.status,"支付成功");
//                    }
//                    else if(item.getPayStatus()==-100){
//                        helper.setText(R.id.status,"支付失败");
//                    }
//
//                }
//                else {
//                    helper.getView(R.id.status).setVisibility(View.GONE);
//                }
//            }
//            else {
//                helper.setText(R.id.status,"支付成功");
//            }
//        }
//        else {
//            helper.setText(R.id.status,"支付成功");
//        }


        if(item.getSource().equals("0")){
            helper.setText(R.id.dingjin_type,"日常");
        }
        else {
            helper.setText(R.id.dingjin_type,"活动");
        }

        if(item.getStatus().equals("0")){
            helper.getView(R.id.yituidan).setVisibility(View.GONE);
            helper.getView(R.id.yishiyong).setVisibility(View.GONE);
        }
        else if(item.getStatus().equals("100")||item.getStatus().equals("-300")){
            helper.getView(R.id.yishiyong).setVisibility(View.VISIBLE);
            helper.getView(R.id.yituidan).setVisibility(View.GONE);
        }
        else if(item.getStatus().equals("-200")){
            helper.getView(R.id.yishiyong).setVisibility(View.GONE);
            helper.getView(R.id.yituidan).setVisibility(View.VISIBLE);
        }


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(item.getCjsj());
        String time=simpleDateFormat.format(date);
        helper.setText(R.id.create_time_text,time);
        if(item.getOrderMerchantName()!=null){
            helper.setText(R.id.label,item.getOrderMerchantName().toString());
        }
        helper.setText(R.id.money,String.valueOf(item.getTotalPrice())+"元");
//        final String tokenId= PreferencesUtils.getString(mContext,"token_id");
//        final String activity_id=PreferencesUtils.getString(mContext,"activityId");
//        final String order_id=item.getOrderId();
//            helper.getView(R.id.quit).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(item.getStatus().equals("0")){
//                        final String url= BaseUrl.BASE_URL+"phoneApi/activityController.do?method=refundOrder&token_id="+tokenId+"&activity_id="+activity_id+"&order_id="+order_id;
//                        Log.d("quitUrl",url);
//                        RestClient.builder()
//                                .url(url)
//                                .success(new ISuccess() {
//                                    @Override
//                                    public void onSuccess(String response) {
//                                        if(response!=null){
//                                            Log.d("quitUrl",response);
//                                            Quit quit = JSONUtil.fromJson(response, Quit.class);
//                                            if(quit.getRet().equals("200")){
//                                                Message message=Message.obtain();
//                                                message.what=2;
//                                                mHandler.sendMessage(message);
//                                                ToastUtils.showLong("退单成功");
//                                            }
//                                            else {
//                                                Toast.makeText(MyApplication.getInstance().getApplicationContext(), "退单失败："+quit.getMsg(), Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    }
//                                })
//                                .build()
//                                .get();
//                    }
//                    else if(item.getStatus().equals("100")||item.getStatus().equals("-300")){
//                        ToastUtils.showLong("您已使用此订金");
//                    }
//                    else {
//                        ToastUtils.showLong("您已退过此单");
//                    }
//
//                }
//            });

    }
}
