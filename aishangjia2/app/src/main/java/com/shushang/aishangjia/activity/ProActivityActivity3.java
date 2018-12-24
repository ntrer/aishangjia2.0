package com.shushang.aishangjia.activity;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shushang.aishangjia.Bean.ActionCustomersBean;
import com.shushang.aishangjia.Bean.ActivityBean;
import com.shushang.aishangjia.Bean.ActivityListNew;
import com.shushang.aishangjia.Bean.CustomersBean;
import com.shushang.aishangjia.Bean.Response;
import com.shushang.aishangjia.Bean.RoleType;
import com.shushang.aishangjia.Bean.UserData;
import com.shushang.aishangjia.Bean.info;
import com.shushang.aishangjia.R;
import com.shushang.aishangjia.activity.adapter.ActivityAdapter;
import com.shushang.aishangjia.application.MyApplication;
import com.shushang.aishangjia.base.BaseActivity;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.greendao.ActionCustomersBeanDao;
import com.shushang.aishangjia.greendao.ActivityBeanDao;
import com.shushang.aishangjia.greendao.CustomersBeanDao;
import com.shushang.aishangjia.greendao.DaoMaster;
import com.shushang.aishangjia.greendao.DaoSession;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.ui.ExtAlertDialog;
import com.shushang.aishangjia.utils.OkhttpUtils.CallBackUtil;
import com.shushang.aishangjia.utils.OkhttpUtils.OkhttpUtil;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.ui.dialog.ActionSheetDialog;
import com.xys.libzxing.zxing.utils.JSONUtil;
import com.xys.libzxing.zxing.utils.PreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class ProActivityActivity3 extends BaseActivity {
    private RecyclerView mRecyclerView;
    private String token_id=null;
    private String activityId=null;
    private String activityCode=null;
    private String activityName=null;
    List<ActivityListNew.DataListBean> dataList;
    private ActivityAdapter mActivityAdapter;
    private Toolbar mToolbar;
    private ProgressBar mProgressBar;
    private LinearLayout ll_nodata;
    private String isUnderLine;
    private String isCheck;
    private String type;
    private Intent data;
    private boolean isSelected=false;
    private Dialog mRequestDialog;
    private String roleType;
    private  List<ActionCustomersBean> actionCustomers=new ArrayList<>();
    private List<CustomersBean> customers=new ArrayList<>();
    private ActivityBean mActivityBean;
    private ActionCustomersBeanDao actionCustomersBeanDao;
    private CustomersBeanDao customersBeanDao;
    private ActivityBeanDao activityBeanDao;
    private static DaoSession daoSession;
    private List<ActionCustomersBean> actionCustomers3=new ArrayList<>();
    private List<info> mInfos=new ArrayList<>();
    private String infos;
    private String sync_time;
    private String merchant_name;
    private String url;
    private static final int REQUEST_CODE_SCAN= 2005;
    private static final int REQUEST_CODE_HUODONG= 2006;
    private static final int REQUEST_CODE_DINGJIN= 2007;
    private static final int REQUEST_CODE_HUODONG_XUANZE= 1007;
    @Override
    public int setLayout() {
        return R.layout.activity_pro_activity;
    }

    @Override
    public void init() {
        mRecyclerView= (RecyclerView) findViewById(R.id.rv_activity);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        mProgressBar=findViewById(R.id.loading);
        ll_nodata= (LinearLayout) findViewById(R.id.ll_no_data);
        //设置支持toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        token_id= PreferencesUtils.getString(this,"token_id");
        type=getIntent().getStringExtra("type");
        mRequestDialog = ExtAlertDialog.creatRequestDialog(ProActivityActivity3.this, getString(R.string.request));
        mRequestDialog.setCancelable(false);
        initData(token_id);
        setUpDataBase();
        initRecyclerView();
    }

    private void initRecyclerView() {
        final LinearLayoutManager linermanager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linermanager);
    }

    private void initData(String token_id) {
        mProgressBar.setVisibility(View.VISIBLE);
        String url= BaseUrl.BASE_URL+"activityController.do?method=getActivity&token_id="+token_id+"&loginOS=2";
        Log.d("BaseUrl",url);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("活动列表",response);
                        if(response!=null){
                            try {
                                ActivityListNew activity = JSONUtil.fromJson(response, ActivityListNew.class);
                                Log.d("进来了","进来了");
                                if(activity.getRet().equals("101")){
                                    Log.d("进来了","进来了2");
                                    Toast.makeText(ProActivityActivity3.this, ""+activity.getMsg(), Toast.LENGTH_SHORT).show();
                                    PreferencesUtils.putString(ProActivityActivity3.this,"token_id",null);
                                    startActivity(new Intent(ProActivityActivity3.this, LoginActivity2.class));
                                    finish();
                                }
                                else if(activity.getRet().equals("200")){
                                    if( activity.getDataList()!=null){
                                        mProgressBar.setVisibility(View.GONE);
                                        dataList = activity.getDataList();
                                        if(dataList.size()==0){
                                            ll_nodata.setVisibility(View.VISIBLE);
                                        }
                                        else {
                                            showData(dataList);
                                            ll_nodata.setVisibility(View.GONE);
                                        }
                                    }
                                    else {
                                        ll_nodata.setVisibility(View.VISIBLE);
                                        mProgressBar.setVisibility(View.GONE);
                                        ToastUtils.showLong(""+activity.getMsg());
                                    }
                                }
                                else {
                                    ll_nodata.setVisibility(View.VISIBLE);
                                    mProgressBar.setVisibility(View.GONE);
                                }
                            }
                            catch (Exception e){
                                mProgressBar.setVisibility(View.GONE);
                                ll_nodata.setVisibility(View.VISIBLE);
                                Log.d("出错了",e.toString());
                            }
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        ll_nodata.setVisibility(View.VISIBLE);
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(ProActivityActivity3.this, "获取数据错误了！！！！", Toast.LENGTH_SHORT).show();
                    }
                }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                ll_nodata.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(ProActivityActivity3.this, ""+msg, Toast.LENGTH_SHORT).show();
            }
        })
                .build()
                .get();
    }

    private void showData(final List<ActivityListNew.DataListBean> dataList) {
        mActivityAdapter=new ActivityAdapter(R.layout.item_activity_new,dataList);
        mRecyclerView.setAdapter(mActivityAdapter);
        mRecyclerView.scrollToPosition(0);
        if(dataList.size()>0){
            mActivityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    isSelected=true;
                    activityId=dataList.get(position).getActivityId();
                    activityName=dataList.get(position).getActivityName();
                    isUnderLine=String.valueOf(dataList.get(position).getIsUnderLine());
                    isCheck=String.valueOf(dataList.get(position).getIsCheck());
                    activityCode=String.valueOf(dataList.get(position).getActivityCode());
                    getRoleType(activityId);
//                    setResult(100,data);
//                    ActivityCompat.finishAfterTransition(ProActivityActivity.this);
                }
            });
        }
    }


    private void getRoleType(String activityId) {
        String url = BaseUrl.BASE_URL+"activityController.do?method=getRoleAtAcitvity&token_id="+token_id+"&loginOS=2&activityId="+activityId;
        Log.d("getRoleType", url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                mRequestDialog.dismiss();
                            }
                            if(response!=null){
                                try {
                                    RoleType activityList = JSONUtil.fromJson(response, RoleType.class);
                                    if(activityList.getRet().equals("200")){
                                        String data = activityList.getData();
                                        if(data!=null){
                                            switch (data){
                                                case "签到员":
                                                    roleType="0";
                                                    //这里进行数据库上传下载
                                                    UpDate();
                                                    break;
                                                case "收银员":
                                                    roleType="1";
                                                    gotoScan(roleType);
                                                    break;
                                                case "礼品发放员":
                                                    roleType="2";
                                                    gotoScan(roleType);
                                                    break;
                                            }
                                        }
                                        else {
                                            roleType=null;
                                            ToastUtils.showLong("你不是该场活动的工作人员");
                                        }
                                    }
                                }
                                catch (Exception e){
                                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                        mRequestDialog.dismiss();
                                    }
                                    Toast.makeText(ProActivityActivity3.this, ""+e, Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(ProActivityActivity3.this, "获取数据错误了！！！！", Toast.LENGTH_SHORT).show();
                        }
                    }).error(new IError() {
                @Override
                public void onError(int code, String msg) {
                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                        mRequestDialog.dismiss();
                    }
                    Toast.makeText(ProActivityActivity3.this, "" + msg, Toast.LENGTH_SHORT).show();
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

    private void gotoScan(final String roleType) {
        if(roleType==null){
            ToastUtils.showLong("请先设置活动相关人员，再进行操作");
        }
        else if(roleType.equals("0")){
            new ActionSheetDialog(ProActivityActivity3.this)
                    .builder()
                    .setCancelable(false)
                    .setCanceledOnTouchOutside(true)
                    .addSheetItem("在线签到", ActionSheetDialog.SheetItemColor.Blue,
                            new ActionSheetDialog.OnSheetItemClickListener() {
                                @Override
                                public void onClick(int which) {
                                    Intent intent=new Intent(ProActivityActivity3.this, CaptureActivity.class);
                                    intent.putExtra("type",roleType);
                                    intent.putExtra("activityId",activityId);
                                    startActivityForResult(intent,REQUEST_CODE_SCAN);
                                    finish();
                                }
                            })
                    .addSheetItem("离线签到", ActionSheetDialog.SheetItemColor.Blue,
                            new ActionSheetDialog.OnSheetItemClickListener() {
                                @Override
                                public void onClick(int which) {
                                    ExtAlertDialog.showSureDlg(ProActivityActivity3.this, "进入离线模式", getString(R.string.message), getString(R.string.sure), new ExtAlertDialog.IExtDlgClick() {
                                        @Override
                                        public void Oclick(int result) {
                                            if(result==1){
                                                sync_time= com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils.getString(ProActivityActivity3.this,"dataSync");
                                                mRequestDialog.show();
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Intent openCameraIntent = new Intent(ProActivityActivity3.this, NoOnLineActivity.class);
                                                        openCameraIntent.putExtra("activityId",activityId);
                                                        startActivity(openCameraIntent);
                                                        finish();
                                                    }
                                                },1000);
                                            }
                                        }
                                    });
                                }
                            })
                    .show();
        }
        else if(roleType.equals("1")){
            Intent intent=new Intent(ProActivityActivity3.this, CaptureActivity.class);
            intent.putExtra("type",roleType);
            intent.putExtra("activityId",activityId);
            startActivityForResult(intent,REQUEST_CODE_SCAN);
            finish();
        }
        else if(roleType.equals("2")){
            Intent intent=new Intent(ProActivityActivity3.this, CaptureActivity.class);
            intent.putExtra("type",roleType);
            intent.putExtra("activityId",activityId);
            startActivityForResult(intent,REQUEST_CODE_SCAN);
            finish();
        }
    }


    private void UpDate() {
        if(infos==null||infos.equals("")){
            mRequestDialog.show();
            getUserData();
        }
        else {
            mRequestDialog.show();
            String url = BaseUrl.BASE_URL+"phoneApi/outLineController.do?method=uploadData&token_id="+token_id;
            HashMap<String, String> paramsMap = new HashMap<>();
            paramsMap.put("info", infos);
            OkhttpUtil.okHttpPost(url, paramsMap, new CallBackUtil.CallBackString() {
                @Override
                public void onFailure(Call call, Exception e) {
                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                        mRequestDialog.dismiss();
                    }
                    ToastUtils.showLong("当前网络链接有问题，同步数据库失败，无法退出离线模式");
                }

                @Override
                public void onResponse(String response) {
                    Log.d("创建活动",response);
                    if(response!=null){
                        try {
                            Response response1 = com.shushang.aishangjia.utils.Json.JSONUtil.fromJson(response, Response.class);
                            if(response1.getRet().equals("200")){
                                getUserData();
                            }
                            else if(response1.getRet().equals("201")){
                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                    mRequestDialog.dismiss();
                                }
                                Toast.makeText(ProActivityActivity3.this, ""+response1.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                            else if (response1.getRet().equals("101")) {
                                Toast.makeText(ProActivityActivity3.this, ""+response1.getMsg(), Toast.LENGTH_SHORT).show();
                                com.xys.libzxing.zxing.utils.PreferencesUtils.putString(ProActivityActivity3.this, "token_id", null);
                                startActivity(new Intent(ProActivityActivity3.this, LoginActivity2.class));
                                finish();
                            }
                            else {
                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                    mRequestDialog.dismiss();
                                }
                                Toast.makeText(ProActivityActivity3.this, ""+response1.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception e){
                            ToastUtils.showLong(e.toString());
                        }

                    }

                }
            });

        }

    }



    private void setUpDataBase() {
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(ProActivityActivity3.this,"dsx.db",null);
        SQLiteDatabase db=helper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(db);
        daoSession=daoMaster.newSession();
        actionCustomersBeanDao = daoSession.getActionCustomersBeanDao();
        customersBeanDao =daoSession.getCustomersBeanDao();
        activityBeanDao=daoSession.getActivityBeanDao();
    }


    private void getUserData() {
        String token_id= com.xys.libzxing.zxing.utils.PreferencesUtils.getString(ProActivityActivity3.this,"token_id");
        url= BaseUrl.BASE_URL+"phoneApi/outLineController.do?method=downloadData&token_id="+token_id+"&activityId="+activityId+"&time=";

        Log.d("userDaata",url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            try {
                                if(response!=null){
                                    try {
                                        UserData userData = com.shushang.aishangjia.utils.Json.JSONUtil.fromJson(response, UserData.class);
                                        if (userData.getRet().equals("101")) {
                                            Toast.makeText(ProActivityActivity3.this, ""+userData.getMsg(), Toast.LENGTH_SHORT).show();
                                            com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils.putString(ProActivityActivity3.this, "token_id", null);
                                            startActivity(new Intent(ProActivityActivity3.this, LoginActivity2.class));
                                            finish();
                                        } else {
                                            if (userData.getRet().equals("200")) {
                                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                                    mRequestDialog.dismiss();
                                                }
                                                Log.d("用户数据",response);
                                                actionCustomers = userData.getData().getActionCustomers();
                                                customers = userData.getData().getCustomers();
                                                mActivityBean=userData.getData().getActivity();
                                                Log.d("mActivityBean",mActivityBean.toString());
                                                if(customers.size()!=0&&mActivityBean!=null&&actionCustomers.size()!=0){
                                                    insertInToDataBase(actionCustomers,customers,mActivityBean);
                                                }
                                                else {
                                                    if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                                        mRequestDialog.dismiss();
                                                    }
                                                    ToastUtils.showLong("暂无活动签到数据");
                                                }
                                            }
                                            else if(userData.getRet().equals("201")){
                                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                                    mRequestDialog.dismiss();
                                                }
                                                Toast.makeText(ProActivityActivity3.this, ""+userData.getMsg(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                    catch (Exception e){
                                        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                            mRequestDialog.dismiss();
                                        }
                                        ToastUtils.showLong("第一个"+e);

                                    }
                                }
                            }
                            catch (Exception e){
                                if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                    mRequestDialog.dismiss();
                                }
                                ToastUtils.showLong("第二个"+e);

                            }

                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                mRequestDialog.dismiss();
                            }
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                                mRequestDialog.dismiss();
                            }
                        }
                    })
                    .build()
                    .get();
        }
        catch (Exception e){
            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                mRequestDialog.dismiss();
            }
            ToastUtils.showLong("第三个"+e);
        }

    }

    private void insertInToDataBase(List<ActionCustomersBean> actionCustomers, List<CustomersBean> customers, ActivityBean activityBean) {
        try {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        com.xys.libzxing.zxing.utils.PreferencesUtils.putString(ProActivityActivity3.this,"dataSync",df.format(new Date())+"");
        actionCustomersBeanDao = MyApplication.getDaoInstant().getActionCustomersBeanDao();
        customersBeanDao = MyApplication.getDaoInstant().getCustomersBeanDao();
        activityBeanDao = MyApplication.getDaoInstant().getActivityBeanDao();
        activityBeanDao.insert(activityBean);
        actionCustomersBeanDao.deleteAll();
        customersBeanDao.deleteAll();
        activityBeanDao.deleteAll();
        activityBeanDao.insert(activityBean);
        if(actionCustomers !=null&& actionCustomers.size()>0) {
            actionCustomersBeanDao.insertInTx(actionCustomers);
//                for (int i = 0; i< actionCustomers.size(); i++){
//                    ActionCustomersBean actionCustomersBean = actionCustomers.get(i);
//                    actionCustomersBeanDao.insert(actionCustomersBean);
//                }
        }
        if(customers !=null&& customers.size()>0) {
            customersBeanDao.insertInTx(customers);
//                for (int i = 0; i< customers.size(); i++){
//                    CustomersBean customersBean = customers.get(i);
//                    customersBeanDao.insert(customersBean);
//                }
        }
        if(mRequestDialog!=null&&mRequestDialog.isShowing()){
            mRequestDialog.dismiss();
        }
        gotoScan(roleType);
//        ToastUtils.showLong("同步数据库成功");
        }
        catch (Exception e){
            if(mRequestDialog!=null&&mRequestDialog.isShowing()){
                mRequestDialog.dismiss();
            }
            ToastUtils.showLong(e.toString());
            Log.d("数据库错误",e.toString());
        }

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
        if(isSelected){

        }
        else {
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            isSelected=false;
        }
    }

}
