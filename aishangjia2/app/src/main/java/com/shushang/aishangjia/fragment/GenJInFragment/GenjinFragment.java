package com.shushang.aishangjia.fragment.GenJInFragment;

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
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shushang.aishangjia.Bean.GenJInJiLu;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.fragment.GenJInFragment.adapter.TimeLineAdapter2;
import com.shushang.aishangjia.fragment.GenJInFragment.adapter.TimeLineAdapter3;
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

public class GenjinFragment extends BaseFragment{

    private RecyclerView mRecyclerView;
    private List<String> mStrings=new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SmartRefreshLayout mSmartRefreshLayout;
    private String  token_id;
    private TimeLineAdapter2 mTimeLineAdapter;
    private TimeLineAdapter3 mTimeLineAdapter3;
    LinearLayout mLlNoData;
    private ImageView mImageView,mImageView2;
    private int page=1;
    private String source,nowIntention;
    private String url;
    List<GenJInJiLu.DataListBean> dataList=new ArrayList<>();
    public GenjinFragment() {
        // Required empty public constructor
    }

    public static GenjinFragment newInstance() {
        return new GenjinFragment();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        EventBus.getDefault().register(this);
        getData(source, nowIntention);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getData(source, nowIntention);
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
               loadMore(source,nowIntention);
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
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
                                        source=null;
                                        getData(source,nowIntention);
                                    }
                                })
                        .addSheetItem("微信引流", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="1";
                                        getData(source,nowIntention);
                                    }
                                })
                        .addSheetItem("客户介绍", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="2";
                                        getData(source,nowIntention);
                                    }
                                })
                        .addSheetItem("广告", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="3";
                                        getData(source,nowIntention);
                                    }
                                })
                        .addSheetItem("销售拜访", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="4";
                                        getData(source,nowIntention);
                                    }
                                })
                        .addSheetItem("电话", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="5";
                                        getData(source,nowIntention);
                                    }
                                })
                        .addSheetItem("自然进店", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="6";
                                        getData(source,nowIntention);
                                    }
                                })
                        .addSheetItem("网上宣传", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="7";
                                        getData(source,nowIntention);
                                    }
                                })
                        .addSheetItem("朋友圈宣传", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="8";
                                        getData(source,nowIntention);
                                    }
                                })
                        .addSheetItem("其他", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="9";
                                        getData(source,nowIntention);
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
                        .addSheetItem("未知", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        nowIntention="1";
                                        getData(source,nowIntention);
                                    }
                                })
                        .addSheetItem("无意向", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        nowIntention="2";
                                        getData(source,nowIntention);
                                    }
                                })
                        .addSheetItem("有需求暂无意向", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        nowIntention="3";
                                        getData(source,nowIntention);
                                    }
                                })
                        .addSheetItem("有意向，需考虑竞品", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        nowIntention="4";
                                        getData(source,nowIntention);
                                    }
                                })
                        .addSheetItem("有意向，需考虑价格", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        nowIntention="5";
                                        getData(source,nowIntention);
                                    }
                                })
                        .addSheetItem("非常有意向，考虑成交", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        nowIntention="6";
                                        getData(source,nowIntention);
                                    }
                                })
                        .show();
            }
        });
    }

    private void getData(String source, String nowIntention) {
        if(source==null&&nowIntention==null){
            url = BaseUrl.BASE_URL+"clueController.do?method=getClueActions&token_id=" + token_id+"&loginOS=2"+"&page=1&rows=10";

        }
        else if(source==null&&nowIntention!=null){
            url = BaseUrl.BASE_URL+"clueController.do?method=getClueActions&token_id=" + token_id+"&loginOS=2"+"&page=1&rows=10&nowIntention="+nowIntention;
        }
        else if(source!=null&&nowIntention==null){
            url = BaseUrl.BASE_URL+"clueController.do?method=getClueActions&token_id=" + token_id+"&loginOS=2"+"&page=1&rows=10&source="+source;
        }
        else {
            url = BaseUrl.BASE_URL+"clueController.do?method=getClueActions&token_id=" + token_id+"&loginOS=2"+"&page=1&rows=10&source="+source+"&nowIntention="+nowIntention;

        }
        Log.d("BaseUrl", url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            mSmartRefreshLayout.finishRefresh();
                            page=1;
                            if (response != null) {
                                try {
                                    GenJInJiLu yiXiangJin = JSONUtil.fromJson(response, GenJInJiLu.class);
                                    if(yiXiangJin.getRet().equals("200")){
                                        dataList = yiXiangJin.getDataList();
                                        if(GenjinFragment.this.dataList.size()!=0){
                                            showData(dataList);
                                            mLlNoData.setVisibility(View.GONE);
                                        }
                                        else {
                                            showData(dataList);
                                            mLlNoData.setVisibility(View.VISIBLE);
                                        }
                                    }
                                    else if(yiXiangJin.getRet().equals("101")){
                                        Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                        com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils.putString(getActivity(),"token_id",null);
                                        startActivity(new Intent(getActivity(), LoginActivity2.class));
                                        getActivity().finish();
                                    }
                                    else if(yiXiangJin.getRet().equals("201")){
                                        Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                    else if(yiXiangJin.getRet().equals("-996")){
                                        Toast.makeText(getActivity(), ""+yiXiangJin.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){
                                    mSmartRefreshLayout.finishRefresh();
                                    Log.d("出错了",e.toString());
                                }
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            mSmartRefreshLayout.finishRefresh();
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "服务器内部错误！", Toast.LENGTH_LONG).show();
                            mSmartRefreshLayout.finishRefresh();
                        }
                    })
                    .build()
                    .get();
        }
        catch (Exception e){
            mSmartRefreshLayout.finishRefresh();
            ToastUtils.showLong("获取数据错误了");
        }
    }


    private void showData(List<GenJInJiLu.DataListBean> dataList) {
        final LinearLayoutManager linermanager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linermanager);
        mRecyclerView.setHasFixedSize(true);
        mTimeLineAdapter=new TimeLineAdapter2(dataList);
//        mTimeLineAdapter3=new TimeLineAdapter3(R.layout.item_kehugenjin2,dataList);
//        mTimeLineAdapter3.setOnLoadMoreListener(this,mRecyclerView);
        mRecyclerView.setAdapter(mTimeLineAdapter);
        mRecyclerView.scrollToPosition(0);

    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_kehu_genjin, null);
        mRecyclerView=view.findViewById(R.id.rv_genjinjilu);
        mLlNoData=view.findViewById(R.id.ll_no_data);
        mSmartRefreshLayout=view.findViewById(R.id.refreshLayout);
        mSmartRefreshLayout.setRefreshHeader(new MaterialHeader(getActivity()));
        mSmartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        mImageView=view.findViewById(R.id.kehu_source);
        mImageView2=view.findViewById(R.id.kehu_intent);
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        return view;
    }




    private void loadMore(String source, String nowIntention) {
        page=page+1;
        if(source==null&&nowIntention==null){
            url = BaseUrl.BASE_URL+"clueController.do?method=getClueActions&token_id=" + token_id+"&loginOS=2"+"&page="+page+"&rows=10";

        }
        else if(source==null&&nowIntention!=null){
            url = BaseUrl.BASE_URL+"clueController.do?method=getClueActions&token_id=" + token_id+"&loginOS=2"+"&page="+page+"&rows=10&nowIntention="+nowIntention;
        }
        else if(source!=null&&nowIntention==null){
            url = BaseUrl.BASE_URL+"clueController.do?method=getClueActions&token_id=" + token_id+"&loginOS=2"+"&page="+page+"&rows=10&source="+source;
        }
        else {
            url = BaseUrl.BASE_URL+"clueController.do?method=getClueActions&token_id=" + token_id+"&loginOS=2"+"&page="+page+"&rows=10&source="+source+"&nowIntention="+nowIntention;
        }
        Log.i("Gift",url);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                       mSmartRefreshLayout.finishLoadMore();
                        Log.i("SignPeopleList",response);
                        if (response!=null){
                            try {
                                GenJInJiLu moneyPeople = JSONUtil.fromJson(response, GenJInJiLu.class);
                                if(moneyPeople.getRet().equals("200")){
                                    if(moneyPeople.getDataList().size()!=0){
                                        List<GenJInJiLu.DataListBean> data = moneyPeople.getDataList();
                                        LoadMoreData(data);
                                    }
                                    else {
                                        mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
                                    }
                                }
                            }
                            catch (Exception e){

                                ToastUtils.showLong(e.toString());
                            }

                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                        ToastUtils.showLong("获取数据失败");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                        ToastUtils.showLong(msg+"");
                    }
                })
                .build()
                .get();

    }


    private void LoadMoreData(List<GenJInJiLu.DataListBean> dataList) {
        if(dataList.size()!=0){
            mTimeLineAdapter.addData(mTimeLineAdapter.getDatas().size(),dataList);
            mRecyclerView.scrollToPosition(mTimeLineAdapter.getDatas().size());
            mSmartRefreshLayout.finishLoadMore();
        }else {
            mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
         if(messageEvent.getMessage().equals("跟进记录")){
            getData(source, nowIntention);
        }

    }


    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
