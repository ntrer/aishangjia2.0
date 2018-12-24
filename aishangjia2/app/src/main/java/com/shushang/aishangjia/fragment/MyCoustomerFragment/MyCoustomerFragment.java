package com.shushang.aishangjia.fragment.MyCoustomerFragment;

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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shushang.aishangjia.Bean.MyClues;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.KeHuDetailActivity;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseFragment;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.fragment.MyCoustomerFragment.adapter.MyCoustomerAdapter;
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

public class MyCoustomerFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{


    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<String> mStrings=new ArrayList<>();
    private MyCoustomerAdapter mMyCoustomerAdapter;
    private String  token_id;
    private String orderType="1";
    private String sort="desc";
    private String source="";
    LinearLayout mLlNoData;
    private ImageView mImageView,mImageView2;
    private int page=1;
    private String clueId;
    private View mLine;
    private boolean isFront,isclick;
    private String url;
    private List<MyClues.DataListBean> dataList=new ArrayList<>();
    public MyCoustomerFragment() {
        // Required empty public constructor
    }

    public static MyCoustomerFragment newInstance() {
        return new MyCoustomerFragment();
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        EventBus.getDefault().register(this);
        isFront=PreferencesUtils.getBoolean(getActivity(),"isFront");
        isclick= com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils.getBoolean(MyApplication.getInstance().getApplicationContext(),"isclick");
        if(String.valueOf(isFront)==null){
            getData(orderType,sort,source);
        }
        else if(isFront==true&&(String.valueOf(isclick)==null||isclick==false)){

            getData(orderType,sort,source);
        }
        else if (isFront==true&&isclick==true){
           Log.d("isFront",""+isFront+"-------"+isclick);
            getData(orderType,sort,source);
            PreferencesUtils.putBoolean(getActivity(),"isclick",false);
        }

        Log.d("isFront",""+isFront+"-------"+isclick);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(getActivity())
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("创建时间(正序)", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        orderType="1";
                                        sort="asc";
                                      getData(orderType,sort,source);
                                    }
                                })
                        .addSheetItem("创建时间(倒序)", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        orderType="1";
                                        sort="desc";
                                        getData(orderType,sort,source);
                                    }
                                })
                        .addSheetItem("沟通时间(正序)", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        orderType="2";
                                        sort="asc";
                                        getData(orderType,sort,source);
                                    }
                                })
                        .addSheetItem("沟通时间(倒序)", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        orderType="2";
                                        sort="desc";
                                        getData(orderType,sort,source);
                                    }
                                })
                        .addSheetItem("成交金额(正序)", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        orderType="3";
                                        sort="asc";
                                        getData(orderType,sort,source);
                                    }
                                })
                        .addSheetItem("成交金额(倒序)", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        orderType="3";
                                        sort="desc";
                                        getData(orderType,sort,source);
                                    }
                                })
                        .addSheetItem("跟进条数(正序)", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        orderType="4";
                                        sort="asc";
                                        getData(orderType,sort,source);
                                    }
                                })
                        .addSheetItem("跟进条数(倒序)", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        orderType="4";
                                        sort="desc";
                                        getData(orderType,sort,source);
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
                        .addSheetItem("全部", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="";
                                        getData(orderType,sort,source);
                                    }
                                })
                        .addSheetItem("未知", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="1";
                                        getData(orderType,sort,source);
                                    }
                                })
                        .addSheetItem("无意向", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="2";
                                        getData(orderType,sort,source);
                                    }
                                })
                        .addSheetItem("有需求暂无意向", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="3";
                                        getData(orderType,sort,source);
                                    }
                                })
                        .addSheetItem("有意向需考虑竞品", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="4";
                                        getData(orderType,sort,source);
                                    }
                                })
                        .addSheetItem("有意向需考虑价格", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="5";
                                        getData(orderType,sort,source);
                                    }
                                })
                        .addSheetItem("非常有意向，考虑成交", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        source="6";
                                        getData(orderType,sort,source);
                                    }
                                })
                        .show();
            }
        });
    }

    private void getData(String orderType,String sort,String source) {
        mSwipeRefreshLayout.setRefreshing(true);
        if(source.equals("")){
            url = BaseUrl.BASE_URL+"clueController.do?method=getMyClues&token_id=" + token_id+"&orderType="+orderType+"&sort="+sort+"&loginOS=2"+"&page=1&rows=10";

        }
        else {
            url = BaseUrl.BASE_URL+"clueController.do?method=getMyClues&token_id=" + token_id+"&orderType="+orderType+"&sort="+sort+"&loginOS=2"+"&page=1&rows=10&nowIntention="+source;

        }
        Log.d("BaseUrl", url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Log.d("SignP",response);
                            mSwipeRefreshLayout.setRefreshing(false);
                            if (response != null) {
                                try {
                                    MyClues yiXiangJin = JSONUtil.fromJson(response, MyClues.class);
                                    if(yiXiangJin.getRet().equals("200")){
                                        dataList = yiXiangJin.getDataList();
                                        if(dataList.size()!=0){
                                            showData(dataList);
                                            mLlNoData.setVisibility(View.GONE);
                                            mLine.setVisibility(View.GONE);
                                        }
                                        else {
                                            showData(dataList);
                                            mLlNoData.setVisibility(View.VISIBLE);
                                            mLine.setVisibility(View.VISIBLE);
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
        catch (Exception e){
            ToastUtils.showLong("获取数据错误了");
        }
    }

    private void showData(final List<MyClues.DataListBean> dataList) {
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mMyCoustomerAdapter=new MyCoustomerAdapter(R.layout.item_kehu,dataList);
        mRecyclerView.setAdapter(mMyCoustomerAdapter);
        mMyCoustomerAdapter.setOnLoadMoreListener(this,mRecyclerView);
        mRecyclerView.scrollToPosition(0);
        mMyCoustomerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(getActivity(),KeHuDetailActivity.class);
                clueId=dataList.get(position).getClueId();
                intent.putExtra("clueId",clueId);
                startActivity(intent);
            }
        });
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if(messageEvent.getMessage().equals("活动拓客")){
               getData(orderType,sort,source);
            }
            else if(messageEvent.getMessage().equals("跟进记录")){
            getData(orderType,sort,source);
        }
        else if(messageEvent.getMessage().equals("退卡")){
            getData(orderType,sort,source);
        }
        else if(messageEvent.getMessage().equals("推送")){
            getData(orderType,sort,source);
            PreferencesUtils.putBoolean(getActivity(),"isclick",false);
        }

    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_my_coustomer, null);
        mRecyclerView=view.findViewById(R.id.rv_my_coustomer);
        mSwipeRefreshLayout=view.findViewById(R.id.srl_my_coustomer);
        mLlNoData=view.findViewById(R.id.ll_no_data);
        mLine=view.findViewById(R.id.line);
        mImageView=view.findViewById(R.id.kehu_paixu);
        mImageView2=view.findViewById(R.id.kehu_shaixuan);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        token_id = PreferencesUtils.getString(getActivity(), "token_id");
        return view;
    }

    @Override
    public void onRefresh() {
        getData(orderType,sort,source);
    }

    @Override
    public void onLoadMoreRequested() {
        loadMore(orderType,sort,source);
    }


    private void loadMore(String orderType,String sort,String source) {
        page=page+1;
        if(source.equals("")){
            url = BaseUrl.BASE_URL+"clueController.do?method=getMyClues&token_id=" + token_id+"&orderType="+orderType+"&sort="+sort+"&loginOS=2"+"&page=1&rows=10";
        }
        else {
            url = BaseUrl.BASE_URL+"clueController.do?method=getMyClues&token_id=" + token_id+"&orderType="+orderType+"&sort="+sort+"&loginOS=2"+"&page=1&rows=10&nowIntention="+source;
        }
        Log.i("Gift",url);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.i("SignPeopleList",response);
                        if (response!=null){
                            try {
                                MyClues moneyPeople = JSONUtil.fromJson(response, MyClues.class);
                                if(moneyPeople.getRet().equals("200")){
                                    if(page>moneyPeople.getIntmaxPage()) {
                                        page=1;
                                        mMyCoustomerAdapter.loadMoreComplete();
                                        mMyCoustomerAdapter.loadMoreEnd();
                                    }
                                    else if(moneyPeople.getDataList().size()!=0){
                                        List<MyClues.DataListBean> data = moneyPeople.getDataList();
                                        LoadMoreData(data);
                                        mMyCoustomerAdapter.loadMoreComplete();
                                    }
                                    else if(moneyPeople.getDataList().size()==0){
                                        mMyCoustomerAdapter.loadMoreComplete();
                                        mMyCoustomerAdapter.loadMoreEnd();
                                    }
                                }
                                else {
                                    mMyCoustomerAdapter.loadMoreComplete();
                                    mMyCoustomerAdapter.loadMoreEnd();
                                }
                            }
                            catch (Exception e){
                              ToastUtils.showLong(e.toString());
                            }

                        }
                    }
                })
                .build()
                .get();

    }

    private void LoadMoreData(List<MyClues.DataListBean> dataList) {
        if(dataList.size()!=0){
            mMyCoustomerAdapter.addData(dataList);
            mMyCoustomerAdapter.loadMoreComplete();
        }else {
            mMyCoustomerAdapter.loadMoreComplete();
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
