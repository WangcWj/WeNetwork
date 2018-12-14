package cn.example.wang.networkcomponent;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import cn.example.wang.networkcomponent.base.BaseObserver;
import cn.example.wang.networkcomponent.base.BaseParam;
import cn.example.wang.networkcomponent.base.NetAddDestroyDisposable;
import cn.example.wang.networkcomponent.dispatcher.NetDispatcher;
import cn.example.wang.networkcomponent.intercepter.BaseInterceptor;
import cn.example.wang.networkcomponent.okhttp.NetOkHttp;
import cn.example.wang.networkcomponent.request.NetCallBack;
import cn.example.wang.networkcomponent.request.NetRequest;
import cn.example.wang.networkcomponent.retrofit.NetRetrofit;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by WANG on 2018/5/3.
 * 整个网络请求的总线 单例模式
 * 1.改变BaseUrl
 * 2.增加拦截器
 * 3.订阅Observable
 * 4.改变Apierver
 * 5.控制日志打印
 */

public class NetControl {

    private static NetControl instance = null;
    public static NetControl getInstance() {
        if(null == instance) {
            synchronized (NetControl.class) {
                if (null == instance) {
                    instance = new NetControl();
                }
            }
        }
        return instance;
    }

    private NetOkHttp mNetOkHttp;

    private NetRetrofit mNetRetrofit;

    private int mRetryWhenCount;

    private long mRetryWhenTime;

    private volatile boolean mHaveInit = false;

    private NetDispatcher mDispatcher;

    private Context mContext;

    private Class mClass;

    private String mBaseUrl;

    private boolean isDebug;

    private String mTag = "WANG";

    public void needPrintLog() {

    }

    public void addInterceptor(BaseInterceptor baseInterceptor){
        mNetOkHttp.addLogInterceptor(baseInterceptor);
    }

    public int getRetryWhenCount() {
        return mRetryWhenCount;
    }

    public long getRetryWhenTime() {
        return mRetryWhenTime;
    }

    public NetRetrofit getNetRetrofit() {
        return mNetRetrofit;
    }

    public NetOkHttp getNetOkhttp() {
        return mNetOkHttp;
    }

    public Context getContext() {
        if(null == mContext) throw new NullPointerException("mContext is null !");
        return mContext;
    }

    public Class getApiServerClass() {
        return mClass;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public String getTag() {
        return mTag;
    }

    /**
     * 设置API接口  必须设置
     * @param mClass API接口的class对象
     * @return
     */
    public NetControl setApiServerClass(Class mClass) {
        this.mClass = mClass;
        return this;
    }

    /**
     * 设置基础url  必须设置
     * @param baseUrl 如: https://www.apiopen.top
     * @return
     */
    public NetControl setBaseUrl(String baseUrl) {
        this.mBaseUrl = baseUrl;
        return this;
    }

    /**
     * 设置是否为debug 可自动 也可手动 非必须 打开后你可观察到一些错误提示
     * @param isDebug
     * @return
     */
    public NetControl debug(boolean isDebug) {
        this.isDebug = isDebug;
        return this;
    }

    /**
     * 设置提示日志的tag 非必须参数
     * @param tag
     * @return
     */
    public NetControl setTag(String tag) {
        this.mTag = tag;
        return this;
    }

    /**
     * 开始网络请求
     *
     * @param tag
     * @return
     */
    public static NetRequest request(NetAddDestroyDisposable tag) {
        return new NetRequest(NetControl.getInstance(), tag);
    }

    /**
     * 订阅
     *
     * @param observable
     * @param callback
     */
    public void subscribe(Observable observable, BaseObserver callback) {
        mDispatcher.toSubscribe(observable, callback);
    }

    /**
     * 获取接收结果的Observe
     *
     * @param netCallBack
     * @return
     */
    public BaseObserver getBaseObserve(NetCallBack netCallBack, NetAddDestroyDisposable tag) {
        BaseObserver observer = new BaseObserver(netCallBack, tag);
        return observer;
    }

    /**
     * 获取 API接口
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getApiServer(Class<T> tClass) {
        return mDispatcher.getApiService(tClass);
    }

    public boolean isHaveInit() {
        return mHaveInit;
    }

    public void destroy() {
        mHaveInit = false;
        instance = null;
    }

    public void init(Context context) {
        if(isDebug){
            if(TextUtils.isEmpty(mBaseUrl)) {
                Log.e(mTag, "BaseURl 不能为空或者为null !");
                return;
            }
        }
        mContext = context;
        mDispatcher = new NetDispatcher(this);
        mRetryWhenCount = BaseParam.RETRYWHEN_COUNT;
        mRetryWhenTime = BaseParam.RETRYWHEN_TIME;
        mNetOkHttp = new NetOkHttp();
        mNetOkHttp.init();
        mNetRetrofit = new NetRetrofit();
        mNetRetrofit.setBaseUrl(mBaseUrl);
        mNetRetrofit.init(mNetOkHttp);
        mHaveInit = true;
    }

}
