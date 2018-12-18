package cn.example.wang.networkcomponent.control;

import android.content.Context;
import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import cn.example.wang.networkcomponent.base.BaseObserver;
import cn.example.wang.networkcomponent.base.BaseParam;
import cn.example.wang.networkcomponent.base.NetLifecycleControl;
import cn.example.wang.networkcomponent.intercepter.BaseInterceptor;
import cn.example.wang.networkcomponent.okhttp.NetOkHttp;
import cn.example.wang.networkcomponent.request.BaseCallBack;
import cn.example.wang.networkcomponent.request.NetCallBack;
import cn.example.wang.networkcomponent.request.NetRequest;
import cn.example.wang.networkcomponent.retrofit.NetRetrofit;
import cn.example.wang.networkcomponent.rxjava.NetRetryWhen;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * 整个网络请求的总线 单例模式
 * 1.改变BaseUrl
 * 2.增加拦截器
 * 3.订阅Observable
 * 4.改变Apierver
 * 5.控制日志打印
 *
 * @author WANG
 */

public class Control {

    private static Control instance = null;

    public static Control getInstance() {
        if (null == instance) {
            synchronized (Control.class) {
                if (null == instance) {
                    instance = new Control();
                }
            }
        }
        return instance;
    }

    private static volatile boolean mHaveInit = false;

    private NetOkHttp mNetOkHttp;

    private NetRetrofit mNetRetrofit;

    private Context mContext;

    private String mOrgBaseUrl;

    public Context getContext() {
        return mContext;
    }

    public void addInterceptor(BaseInterceptor baseInterceptor) {
        mNetOkHttp.addLogInterceptor(baseInterceptor);
    }

    /**
     * 开始网络请求
     *
     * @param tag
     * @return
     */
    public NetRequest request(NetLifecycleControl tag) {
        return new NetRequest(Control.getInstance(), tag);
    }

    /**
     * 开始网络请求
     *
     * @param tag
     * @return
     */
    public NetRequest requestJson(NetLifecycleControl tag) {
        return new NetRequest(Control.getInstance(), tag);
    }

    /**
     * 每次发起网络请求的时候都需要去重组Retrofit和OkHttp
     *
     * @param mBaseUrl
     */
    public void combination(String mBaseUrl, boolean needRequestOkhttp) {
        if (!mHaveInit) {
            throw new RuntimeException("初始化过程有误!");
        }
        if (TextUtils.isEmpty(mBaseUrl)) {
            mBaseUrl = mOrgBaseUrl;
        }
        mNetRetrofit.transform(mBaseUrl);
        if (needRequestOkhttp) {
            mNetRetrofit.transform(mNetOkHttp.getOkHttpClient());
        }
    }

    /**
     * 订阅
     *
     * @param observable
     * @param callback
     */
    public void subscribe(Observable observable, BaseObserver callback) {
        toSubscribe(observable, callback);
    }

    /**
     * 获取接收结果的Observe
     *
     * @param netCallBack
     * @return
     */
    public BaseObserver getBaseObserve(BaseCallBack netCallBack, NetLifecycleControl tag) {
        BaseObserver observer = new BaseObserver(netCallBack, tag);
        return observer;
    }

    /**
     * Rxava订阅事件
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    public <T> void toSubscribe(Observable<T> observable, BaseObserver<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(BaseParam.READ_TIMEOUT, TimeUnit.SECONDS)
                .retryWhen(new NetRetryWhen(BaseParam.RETRYWHEN_COUNT, BaseParam.RETRYWHEN_COUNT))
                .subscribe(observer);
    }

    /**
     * 获取API
     *
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T getApiService(Class<T> clz) {
        T apiService = null;
        Retrofit retrofit = mNetRetrofit.getRetrofit();
        if (retrofit != null) {
            apiService = retrofit.create(clz);
        }
        return apiService;
    }

    public boolean init(String baseUrl, Context context) {
        mContext = context;
        mNetOkHttp = NetOkHttp.getInstance();
        mNetRetrofit = NetRetrofit.getInstance();
        this.mOrgBaseUrl = baseUrl;
        mNetRetrofit.transform(mNetOkHttp.getOkHttpClient());
        mHaveInit = true;
        return mHaveInit;
    }

}
