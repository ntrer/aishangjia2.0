package com.shushang.aishangjia;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.tts.client.SpeechSynthesizer;
import com.blankj.utilcode.util.ToastUtils;
import com.github.promeg.pinyinhelper.Pinyin;
import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.shushang.aishangjia.Bean.GongGao;
import com.shushang.aishangjia.Bean.QuanXian;
import com.shushang.aishangjia.Bean.UpDate;
import com.shushang.aishangjia.Bean.Voice;
import com.shushang.aishangjia.activity.DailyOrderActivity;
import com.shushang.aishangjia.activity.GoodsActivity;
import com.shushang.aishangjia.activity.LoginActivity2;
import com.shushang.aishangjia.activity.ProActivityActivity;
import com.shushang.aishangjia.activity.ProActivityActivity3;
import com.shushang.aishangjia.activity.XiansuoActivity;
import com.shushang.aishangjia.base.BaseActivity;
import com.shushang.aishangjia.base.BaseUrl;
import com.shushang.aishangjia.base.MessageEvent;
import com.shushang.aishangjia.base.PermissionListener;
import com.shushang.aishangjia.fragment.ActivityFragment.ActivityFragment;
import com.shushang.aishangjia.fragment.AppFragment.AppFragment;
import com.shushang.aishangjia.fragment.CoustomerFragment.CoustomerFragment;
import com.shushang.aishangjia.fragment.CrmFragment.CrmFragment;
import com.shushang.aishangjia.fragment.DingjinFragment.DingjinFragment;
import com.shushang.aishangjia.fragment.LianMengFragment.LianMengFragment;
import com.shushang.aishangjia.fragment.MyCoustomerFragment.MyCoustomerFragment;
import com.shushang.aishangjia.fragment.MyFragment2.MyFragment2;
import com.shushang.aishangjia.fragment.ScanFragment.ScanFragment;
import com.shushang.aishangjia.fragment.ShopTopFragment.ShopTopFragment;
import com.shushang.aishangjia.fragment.SignFragment.SignFragment;
import com.shushang.aishangjia.fragment.YiXiangJinFragment.YiXiangJinFragment;
import com.shushang.aishangjia.net.RestClient;
import com.shushang.aishangjia.net.callback.IError;
import com.shushang.aishangjia.net.callback.IFailure;
import com.shushang.aishangjia.net.callback.ISuccess;
import com.shushang.aishangjia.service.AppUpdateService;
import com.shushang.aishangjia.ui.BottomNavigationViewHelper;
import com.shushang.aishangjia.ui.ExtAlertDialog;
import com.shushang.aishangjia.utils.ActivityManager.ActivityStackManager;
import com.shushang.aishangjia.utils.AudioUtil;
import com.shushang.aishangjia.utils.Json.JSONUtil;
import com.shushang.aishangjia.utils.SharePreferences.PreferencesUtils;
import com.shushang.aishangjia.utils.VoiceUtils;
import com.shushang.aishangjia.utils.permissionUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity2 extends BaseActivity{

    private Context mContext;
    private String mSavePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ejjz.apk";
    private String mDownloadUrl ;
    private static final int REQUEST_CODE_WRITE_STORAGE = 1002;
    private static final int REQUEST_CODE_UNKNOWN_APP = 2001;
    private static final int REQUEST_CODE_SCAN = 2002;
    private Handler mHandler;
    private MyFragment2 mMyFragment;
    private ScanFragment mScanFragment;
    private SignFragment mSignFragment;
    private LianMengFragment mLianMengFragment;
    private DingjinFragment mDingjinFragment;
    private ShopTopFragment mShopTopFragment;
    private CrmFragment mCrmFragment;
    private YiXiangJinFragment mYiXiangJinFragment;
    private MyCoustomerFragment mMyCoustomerFragment;
    private AppFragment mAppFragment;
    private CoustomerFragment mCoustomerFragment;
    private ActivityFragment mActivityFragment;
    private int lastfragment;//用于记录上个选择的Fragment
    private BottomNavigationView navigation;
    private Fragment[] mFragments ;
    private String type;
    private int versionCode;
    private String token_id,userId;
    //退出时的时间
    private long mExitTime;
    private String leagueFlag=null;
    private BottomNavigationView mNavigationView,mNavigationView2;
    private BottomNavigationItemView itemView;
    private ImageView mImageView;
    private GifImageView mGifImageView;
    private  View badge;
    private TextView mTextView;
    private boolean isFront = false;
    private String tuisong,speech_text;
    private static final int REQUEST_CODE_RICHANG= 2003;
    private static final int REQUEST_CODE_HUODONG= 2004;
    private static final int REQUEST_CODE_RICHANGDINGJIN= 2008;
    private static final int REQUEST_CODE_DINGJIN= 2007;
    private static final int REQUEST_CODE_DINGDAN= 2010;
    private List<String> data=new ArrayList<>();
    //    private KqwSpeechCompound kqwSpeechCompound;
    private SpeechSynthesizer mSpeechSynthesizer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            FragmentManager manager = getSupportFragmentManager();
            manager.popBackStackImmediate(null, 1);
        }

        JPushInterface.setAlias(this,2018,userId);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_main2;
    }


    @Override
    public void init() {
        mContext=this;
        AudioUtil audioUtil = AudioUtil.getInstance(this);
        int mediaMaxVolume = audioUtil.getMediaMaxVolume();
        int mediaVolume = audioUtil.getMediaVolume();
        if(mediaVolume!=mediaMaxVolume){
            audioUtil.setMediaVolume(mediaMaxVolume);
        }
//        kqwSpeechCompound=new KqwSpeechCompound(this);
        leagueFlag=PreferencesUtils.getString(mContext,"leagueFlag");
        mNavigationView=findViewById(R.id.navigation_fragment2);
        mNavigationView2=findViewById(R.id.navigation_fragment);
//        mImageView=findViewById(R.id.navigation_center_image);
        mNavigationView2.setItemIconTintList(null);
        mGifImageView=findViewById(R.id.navigation_center_image);
        BottomNavigationMenuView childAt = (BottomNavigationMenuView) mNavigationView2.getChildAt(0);
        View childAt1 = childAt.getChildAt(4);
        itemView= (BottomNavigationItemView) childAt1;
        badge=LayoutInflater.from(this).inflate(R.layout.menu_badge,childAt,false);
        mTextView=badge.findViewById(R.id.tv_msg_count);
        initFragment(leagueFlag);
        token_id= PreferencesUtils.getString(mContext,"token_id");
        userId=PreferencesUtils.getString(mContext,"userId");
        type= PreferencesUtils.getString(mContext,"type");
        inidData(token_id);
        getData2(token_id);
        initItem();
        getQuanxianData(token_id);
        permissionCamera();
        EventBus.getDefault().register(this);
        tuisong=getIntent().getStringExtra("push");
        mGifImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(0,"欢迎使用小e语音助手");
                ExtAlertDialog.showEditDlg2(MainActivity2.this, "您可以这样说", data, true, new ExtAlertDialog.IExtDlgClick2() {
                    @Override
                    public void Oclick(int result, Editable editText) {
                        if(result==1){
                            permissionAudio();
                        }
                    }
                });
            }
        });

    }


    private void initItem() {
        if(type.equals("5")){
            data.add("1.我要去签到");
            data.add("2.我要去收银");
            data.add("3.我要去兑换礼品");
        }
        else {
            data.add("1.我要去活动(线索)拓客");
            data.add("2.我要去活动(日常)订金");
            data.add("3.我要去下订单");
        }
    }



    public void initSpeech(final Context context) {
        //1.创建RecognizerDialog对象
        final RecognizerDialog mDialog = new RecognizerDialog(context, null);
        //2.设置accent、language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        mDialog.setParameter(SpeechConstant.VAD_BOS, "5000");
        //设置语音后端点:后端点静音检测时间，单位ms，即用户停止说话多长时间内即认为不再输入，
        //自动停止录音，范围{0~10000}
        mDialog.setParameter(SpeechConstant.VAD_EOS, "2000");
        //3.设置回调接口
        mDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean isLast) {
                if (!isLast) {
                    //解析语音
                    //返回的result为识别后的汉字,直接赋值到TextView上即可
                    String result = parseVoice(recognizerResult.getResultString());
                    speech_text=result;
                    String s= Pinyin.toPinyin(speech_text,"");
                    gotoActivity(s);
                    if(mDialog!=null&&mDialog.isShowing()){
                        mDialog.dismiss();
                    }
                }
            }

            @Override
            public void onError(SpeechError speechError) {
                speak(0,"未能识别您说的");
                ExtAlertDialog.showEditDlg2(MainActivity2.this, "您可以这样说", data, true, new ExtAlertDialog.IExtDlgClick2() {
                    @Override
                    public void Oclick(int result, Editable editText) {
                        if(result==1){
                            permissionAudio();
                        }
                    }
                });
            }
        });
        //4.显示dialog，接收语音输入
        mDialog.show();
        //获取字体所在的控件，设置为"",隐藏字体，
        TextView txt = (TextView)mDialog.getWindow().getDecorView().findViewWithTag("textlink");
        txt.setText("");
    }

    /**
     * 解析语音json
     */
    public String parseVoice(String resultString) {
        Gson gson = new Gson();
        Voice voiceBean = gson.fromJson(resultString, Voice.class);
        StringBuffer sb = new StringBuffer();
        ArrayList<Voice.WSBean> ws = voiceBean.ws;
        for (Voice.WSBean wsBean : ws) {
            String word = wsBean.cw.get(0).w;
            sb.append(word);
        }
        return sb.toString();
    }




    private void gotoActivity(String final_result) {
        if(type.equals("5")){
            if(final_result.contains("QIANDAO")){
                Intent intent=new Intent(MainActivity2.this, ProActivityActivity3.class);
                startActivityForResult(intent,REQUEST_CODE_SCAN);

            }
            else if(final_result.contains("SHOUYIN")){
                Intent intent=new Intent(MainActivity2.this,ProActivityActivity3.class);
                startActivityForResult(intent,REQUEST_CODE_SCAN);
            }
            else if(final_result.contains("DUIHUAN")){
                startActivityForResult(new Intent(MainActivity2.this, ProActivityActivity3.class),REQUEST_CODE_SCAN);
            }
            else {
                speak(0,"未能识别您说的，我猜你想说：签到");
                ExtAlertDialog.showEditDlg2(MainActivity2.this, "您可以这样说", data, true, new ExtAlertDialog.IExtDlgClick2() {
                    @Override
                    public void Oclick(int result, Editable editText) {
                        if(result==1){
                            permissionAudio();
                        }
                    }
                });
            }
        }
        else {
            if(final_result.contains("HUODONGTUOKE")){
                Intent intent=new Intent(MainActivity2.this, ProActivityActivity.class);
                intent.putExtra("type", "6");
                startActivityForResult(intent,REQUEST_CODE_HUODONG);

            }
            else if(final_result.contains("RICHANGTUOKE")){
                Intent intent=new Intent(MainActivity2.this,XiansuoActivity.class);
                startActivityForResult(intent,REQUEST_CODE_RICHANG);
            }
            else if(final_result.contains("RICHANGDINGJIN")){
                startActivityForResult(new Intent(MainActivity2.this, DailyOrderActivity.class),REQUEST_CODE_RICHANGDINGJIN);
            }
            else if(final_result.contains("HUODONGDINGJIN")){
                Intent openCameraIntent = new Intent(MainActivity2.this, ProActivityActivity.class);
                openCameraIntent.putExtra("type", "3");
//                                    openCameraIntent.putExtra("event","6");
                startActivityForResult(openCameraIntent, REQUEST_CODE_DINGJIN );
            }
            else if(final_result.contains("DINGDAN")){
                startActivityForResult(new Intent(MainActivity2.this, GoodsActivity.class),REQUEST_CODE_DINGDAN);
            }
            else {
                speak(0,"未能识别您说的，我猜你想说：活动拓客");
                ExtAlertDialog.showEditDlg2(MainActivity2.this, "您可以这样说", data, true, new ExtAlertDialog.IExtDlgClick2() {
                    @Override
                    public void Oclick(int result, Editable editText) {
                        if(result==1){
                            permissionAudio();
                        }
                    }
                });
            }
        }



    }








    private void getQuanxianData(String token_id) {
        String url= BaseUrl.BASE_URL+"resouceController.do?method=getResourcesForUser&token_id="+token_id+ "&loginOS=1";
        Log.d("BaseUrl",url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            if(response!=null){
                                Log.d("AppPeopleActivity",response);
                                try {
                                    QuanXian test = JSONUtil.fromJson(response, QuanXian.class);
                                    if(test.getRet().equals("101")){

                                        Toast.makeText(MainActivity2.this, ""+test.getMsg(), Toast.LENGTH_SHORT).show();
                                        PreferencesUtils.putString(MainActivity2.this,"token_id",null);
                                        startActivity(new Intent(MainActivity2.this, LoginActivity2.class));
                                        MainActivity2.this.finish();
                                    }
                                    else if(test.getRet().equals("200")){
                                        if(test.getDataList().size()!=0){
                                            PreferencesUtils.putString(MainActivity2.this,"quanxian",response);
                                        }
                                    }
                                    else if(test.getRet().equals("201")){
                                        Toast.makeText(MainActivity2.this, ""+test.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){

                                    Toast.makeText(MainActivity2.this, ""+e, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {

                            Toast.makeText(MainActivity2.this, "获取数据错误了！！！！", Toast.LENGTH_SHORT).show();
                        }
                    }).error(new IError() {
                @Override
                public void onError(int code, String msg) {

                    Toast.makeText(MainActivity2.this, ""+msg, Toast.LENGTH_SHORT).show();
                }
            })
                    .build()
                    .get();
        }
        catch (Exception e){

            ToastUtils.showLong(e.toString());
        }

    }



    private void getData2(String token_id) {
        String url= BaseUrl.BASE_URL+"phoneLeagueController.do?method=getNoticesByMerchantId&token_id="+token_id+"&page=1&rows=10&isRead=0";
        Log.d("BaseUrl",url);
        try {
            RestClient.builder()
                    .url(url)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            if(response!=null){
                                Log.d("AppPeopleActivity",response);
                                try {
                                    GongGao test = JSONUtil.fromJson(response, GongGao.class);
                                    if(test.getRet().equals("101")){
                                        Toast.makeText(MainActivity2.this, ""+test.getMsg(), Toast.LENGTH_SHORT).show();
                                        PreferencesUtils.putString(MainActivity2.this,"token_id",null);
                                        startActivity(new Intent(MainActivity2.this, LoginActivity2.class));
                                        finish();
                                    }
                                    else if(test.getRet().equals("200")){
                                        int size = test.getDataList().size();
                                        if(size>0){
                                          itemView.addView(badge);
                                            mTextView.setVisibility(View.VISIBLE);
                                        }
                                        else {
                                            mTextView.setVisibility(View.GONE);
                                        }
                                    }
                                    else if(test.getRet().equals("201")){
                                        Toast.makeText(MainActivity2.this, ""+test.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){
                                    Toast.makeText(MainActivity2.this, ""+e, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(MainActivity2.this, "获取数据错误了！！！！", Toast.LENGTH_SHORT).show();
                        }
                    }).error(new IError() {
                @Override
                public void onError(int code, String msg) {

                    Toast.makeText(MainActivity2.this, ""+msg, Toast.LENGTH_SHORT).show();
                }
            })
                    .build()
                    .get();
        }
        catch (Exception e){
            ToastUtils.showLong(e.toString());
        }

    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isFront=true;
        PreferencesUtils.putBoolean(MainActivity2.this,"isFront",isFront);
    }

    @Override
    public void onPause() {
        super.onPause();
        isFront = false;
        PreferencesUtils.putBoolean(MainActivity2.this,"isFront",isFront);
    }


    private void inidData(String token_id) {
        try {
            RestClient.builder()
                    .url(BaseUrl.BASE_URL+"phoneApi/activityLogin.do?method=updateApp&type=1&token_id="+ token_id)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            if(response!=null){
                                Log.d("Update",response);
                                try {
                                    UpDate upDate = JSONUtil.fromJson(response, UpDate.class);
                                    versionCode = getVersionCode(mContext);
                                    if(upDate.getData()!=null){
                                        mDownloadUrl=upDate.getData().getUrl();
                                        if(String.valueOf(versionCode).equals(upDate.getData().getVersion())||upDate.getData()==null){
                                            Log.d("wocao666","无新版本");
                                        }
                                        else {
                                            initDialog(upDate);
                                        }
                                    }
                                    else {
                                        Log.d("wocao666","无新版本");
                                    }
                                }
                                catch (Exception e){

                                }
                            }
                        }
                    })
                    .build()
                    .get();
        }
        catch (Exception e){

        }

    }


    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    //初始化fragment和fragment数组
    private void initFragment(String leagueFlag) {
        mMyFragment = new MyFragment2();
        mScanFragment=new ScanFragment();
        mSignFragment=new SignFragment();
        mShopTopFragment=new ShopTopFragment();
        mCrmFragment=new CrmFragment();
        mYiXiangJinFragment=new YiXiangJinFragment();
        mLianMengFragment=new LianMengFragment();
        mDingjinFragment=new DingjinFragment();
        mAppFragment=new AppFragment();
        mCoustomerFragment=new CoustomerFragment();
        mMyCoustomerFragment=new MyCoustomerFragment();
        mActivityFragment=new ActivityFragment();
        if(leagueFlag==null){
            PreferencesUtils.putString(MainActivity2.this, "token_id", null);
            startActivity(new Intent(MainActivity2.this, LoginActivity2.class));
            finish();
        }
//        else
//            if(leagueFlag.equals("1")){
//            mNavigationView.setVisibility(View.GONE);
            mNavigationView2.setVisibility(View.VISIBLE);
            mFragments = new Fragment[]{mCoustomerFragment,mActivityFragment, mDingjinFragment,mAppFragment};
            lastfragment = 0;
        if(tuisong!=null&&tuisong.equals("有推送")){
            EventBus.getDefault().post(new MessageEvent("推送"));
        }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mCoustomerFragment,"tag1").show(mCoustomerFragment).commit();
            BottomNavigationViewHelper.disableShiftMode(mNavigationView2);
            mNavigationView2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.navigation_fragment_zero:
                        {
                            if(lastfragment!=0)
                            {
                                switchFragment(lastfragment,0);
                                lastfragment=0;

                            }
                            return true;

                        }
                        case R.id.navigation_fragment_one:
                        {
                            if(lastfragment!=1)
                            {
                                switchFragment(lastfragment,1);
                                lastfragment=1;

                            }

                            return true;
                        }
                        case R.id.navigation_fragment_three:
                        {
                            if(lastfragment!=2)
                            {
                                switchFragment(lastfragment,2);
                                lastfragment=2;

                            }

                            return true;
                        }
                        case R.id.navigation_fragment_four:
                        {
                            if(lastfragment!=3)
                            {
                                switchFragment(lastfragment,3);
                                lastfragment=3;

                            }

                            return true;
                        }

                    }
                    return false;
                }
            });

//        }
//        else if(leagueFlag ==null||leagueFlag.equals("")|| leagueFlag.equals("0")){
//            Log.d("mNavigationView","mNavigationView");
//            mNavigationView.setVisibility(View.VISIBLE);
//            mNavigationView2.setVisibility(View.GONE);
//            mFragments = new Fragment[]{mSignFragment,mScanFragment, mYiXiangJinFragment,mMyFragment};
//            lastfragment = 0;
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mSignFragment,"tag1").show(mSignFragment).commit();
//            BottomNavigationViewHelper.disableShiftMode(mNavigationView);
//            mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    switch (item.getItemId())
//                    {
//                        case R.id.navigation_fragment_zero:
//                        {
//                            if(lastfragment!=0)
//                            {
//                                switchFragment(lastfragment,0);
//                                lastfragment=0;
//
//                            }
//                            return true;
//
//                        }
//                        case R.id.navigation_fragment_one:
//                        {
//                            if(lastfragment!=1)
//                            {
//                                switchFragment(lastfragment,1);
//                                lastfragment=1;
//
//                            }
//
//                            return true;
//                        }
//                        case R.id.navigation_fragment_two:
//                        {
//                            if(lastfragment!=2)
//                            {
//                                switchFragment(lastfragment,2);
//                                lastfragment=2;
//
//                            }
//
//                            return true;
//                        }
//                        case R.id.navigation_fragment_four:
//                        {
//                            if(lastfragment!=3)
//                            {
//                                switchFragment(lastfragment,3);
//                                lastfragment=3;
//
//                            }
//
//                            return true;
//                        }
//
//                    }
//                    return false;
//                }
//            });
//
//        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        try {
            if(messageEvent.getMessage().equals("无线索")){
                mTextView.setVisibility(View.GONE);
            }
            else if(messageEvent.getMessage().equals("有线索")){
                mTextView.setVisibility(View.VISIBLE);
            }else if(messageEvent.getMessage().equals("无未读")){
                mTextView.setVisibility(View.GONE);
            }
        }
        catch (Exception e){
             ToastUtils.showLong(e.toString());
        }
    }

    //显示更新对话框
    private void initDialog(UpDate upDate) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MainActivity2.this);
        normalDialog.setTitle("新版本更新");
        normalDialog.setMessage(upDate.getData().getComment());
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        permissionStorage();

                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        normalDialog.setCancelable(false);
        // 显示
        normalDialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_UNKNOWN_APP) {
            downloadAPK();
        }
        else if(requestCode == REQUEST_CODE_SCAN){
            EventBus.getDefault().post(new MessageEvent("活动拓客"));
            EventBus.getDefault().post(new MessageEvent("活动订金"));
        }
        else if(requestCode==REQUEST_CODE_HUODONG){
            EventBus.getDefault().post(new MessageEvent("活动拓客"));
        }
        else if(requestCode==REQUEST_CODE_RICHANG){
            EventBus.getDefault().post(new MessageEvent("活动拓客"));
        }
        else  if(requestCode==REQUEST_CODE_RICHANGDINGJIN){
            EventBus.getDefault().post(new MessageEvent("添加订金"));
        }
        else if(requestCode==REQUEST_CODE_DINGDAN){
            EventBus.getDefault().post(new MessageEvent("活动订金"));
        }
    }

    //下载app
    private void downloadAPK() {
        if (Build.VERSION.SDK_INT >= 26) {
            boolean b = getPackageManager().canRequestPackageInstalls();
            if (b) {
                AppUpdateService.start(mContext, mSavePath, mDownloadUrl);//安装应用的逻辑(写自己的就可以)
            } else {
                Uri packageUri=Uri.parse("package:"+getPackageName());
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,packageUri);
                startActivityForResult(intent, REQUEST_CODE_UNKNOWN_APP);
            }
        } else {
            AppUpdateService.start(mContext, mSavePath, mDownloadUrl);
        }
    }


    //切换Fragment
    private void switchFragment(int lastfragment,int index)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(mFragments[lastfragment]);//隐藏上个Fragment
        if(mFragments[index].isAdded()==false)
        {
            transaction.add(R.id.fragment_container,mFragments[index],"tag"+index);


        }
        transaction.show(mFragments[index]).commitAllowingStateLoss();

    }


    //请求相机权限
    private void permissionCamera(){
        requestRunPermisssion(new String[]{Manifest.permission.CAMERA}, new PermissionListener() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                for(String permission : deniedPermission){
                    reGetPermission();
                }
            }
        });
    }


    //请求相机权限
    private void permissionAudio(){
        requestRunPermisssion(new String[]{Manifest.permission.RECORD_AUDIO}, new PermissionListener() {
            @Override
            public void onGranted() {
                initSpeech(MainActivity2.this);
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                for(String permission : deniedPermission){
                    reGetPermission();
                }
            }
        });
    }


    //请求存储权限
    private void permissionStorage(){
        requestRunPermisssion(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionListener() {
            @Override
            public void onGranted() {
                //表示所有权限都授权了
                downloadAPK();
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                for(String permission : deniedPermission){
                    reGetPermission();
                }
            }
        });
    }


    private void reGetPermission() {
        ExtAlertDialog.showSureDlg(MainActivity2.this, "警告", "权限被拒绝，部分功能将无法使用，请重新授予权限", "确定", new ExtAlertDialog.IExtDlgClick() {
            @Override
            public void Oclick(int result) {
                if(result==1){
                    permissionUtil.GoToSetting(MainActivity2.this);
                    finish();
                }
            }
        });
    }



    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //退出应用
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            PreferencesUtils.putBoolean(MainActivity2.this,"isAlive",false);
            PreferencesUtils.putBoolean(MainActivity2.this,"isclick",false);
            ActivityStackManager.getActivityStackManager().popAllActivity();//remove all activity.
            System.exit(0);
            finish();
        }
    }

    public void setHandler(Handler handler){
        this.mHandler = handler;
    }


    private void speak(int speaker,String text) {
//        kqwSpeechCompound.speaking("欢迎使用小e语音助手");
        //若是每次都这样是不是会有内存问题呢？需要思考改进
        VoiceUtils utils=new VoiceUtils();
        utils.init(this,speaker);
        mSpeechSynthesizer=utils.getSyntheszer();
        this.mSpeechSynthesizer.speak(text);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}

