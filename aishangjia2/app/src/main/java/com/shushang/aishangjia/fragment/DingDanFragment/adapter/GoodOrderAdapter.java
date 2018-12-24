package com.shushang.aishangjia.fragment.DingDanFragment.adapter;

import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shushang.aishangjia.Bean.GoodsOrder;
import com.shushang.aishangjia.R;
import com.xys.libzxing.zxing.utils.PreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by YD on 2018/7/19.
 */

public class GoodOrderAdapter extends BaseQuickAdapter<GoodsOrder.DataListBean,BaseViewHolder> {

    private Handler mHandler;

    public GoodOrderAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsOrder.DataListBean> data, Handler handler) {
        super(layoutResId, data);
        this.mHandler=handler;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GoodsOrder.DataListBean item) {

        final String tokenId= PreferencesUtils.getString(mContext,"token_id");
        final String  goodsorderId=item.getGoodsorderId();

        if(String.valueOf(item.getCjsj())!=null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=new Date(item.getCjsj());
            String time=simpleDateFormat.format(date);
            helper.setText(R.id.diingdan_time,time);
        }


     if(String.valueOf(item.getTotalPrice())!=null){
         helper.setText(R.id.dingdan_total_text,item.getTotalPrice()+"元");
     }

     if(String.valueOf(item.getYingshou())!=null){
         helper.setText(R.id.dingdan_get_text,item.getYingshou()+"元");
     }

     if(String.valueOf(item.getShishou())!=null){
         helper.setText(R.id.dingdan_shouldget_text,item.getShishou()+"元");
     }

     if(item.getOrderManagerName()!=null){
         helper.setText(R.id.dingdan_creater_text,item.getOrderManagerName());
     }

     if(String.valueOf(item.getDikoujia())!=null){
         helper.setText(R.id.dingdan_money_text,item.getDikoujia()+"元");
     }

     if(item.getOrders().size()!=0){
         helper.setText(R.id.dingdan_dingjin_text,item.getOrders().size()+"单");
     }
     else {
         helper.setText(R.id.dingdan_dingjin_text,"0单");
     }

    if(String.valueOf(item.getGoodsorderNum())!=null){
         helper.setText(R.id.dingdan_title_num,item.getGoodsorderNum()+"-");
    }

        if(String.valueOf(item.getCustomerName())!=null){
            helper.setText(R.id.dingdan_title_name,item.getCustomerName());
        }

        if(String.valueOf(item.getCustomerPhone())!=null){
            helper.setText(R.id.dingdan_title_bianhuao,"("+item.getCustomerPhone()+") x");
        }

        helper.setText(R.id.dingdan_title_count,item.getGoods().size()+"");


        if(item.getEnable().equals("0")){
            helper.getView(R.id.yituidan).setVisibility(View.VISIBLE);
        }
        else {
            helper.getView(R.id.yituidan).setVisibility(View.GONE);
        }


//    helper.getView(R.id.tuidan).setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//
//            ExtAlertDialog.showSureDlg2(mContext, "提醒", "确定要退单吗？", "确定", false,new ExtAlertDialog.IExtDlgClick() {
//                @Override
//                public void Oclick(int result) {
//                    if(result==1){
//                        if(item.getEnable().equals("0")){
//                            ToastUtils.showLong("您已退过此单");
//                        }
//                        else if(item.getEnable().equals("1")){
//                            String url=BaseUrl.BASE_URL+"goodsOrderController.do?method=refundGoodsOrder&token_id="+tokenId+"&goodsorderId="+goodsorderId;
//                            Log.d("url",url);
//                            try {
//                                RestClient.builder()
//                                        .url(url)
//                                        .success(new ISuccess() {
//                                            @Override
//                                            public void onSuccess(String response) {
//                                                if(response!=null){
//                                                    Log.d("quitUrl",response);
//                                                    Quit quit = JSONUtil.fromJson(response, Quit.class);
//                                                    if(quit.getRet().equals("200")){
//                                                        Message message=Message.obtain();
//                                                        message.what=2;
//                                                        mHandler.sendMessage(message);
//                                                        ToastUtils.showLong("退单成功");
//                                                    }
//                                                    else {
//                                                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "退单失败："+quit.getMsg(), Toast.LENGTH_SHORT).show();
//                                                    }
//                                                }
//                                            }
//                                        })
//                                        .build()
//                                        .get();
//                            }
//                            catch (Exception e){
//                                ToastUtils.showLong(e.toString());
//                            }
//                        }
//                    }
//                }
//            });
//
//        }
//    });

    }
}
