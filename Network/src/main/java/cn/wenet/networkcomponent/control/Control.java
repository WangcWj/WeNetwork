package cn.wenet.networkcomponent.control;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.Transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import cn.wenet.networkcomponent.base.NetBaseObserver;
import cn.wenet.networkcomponent.base.NetBaseParam;
import cn.wenet.networkcomponent.base.NetLifecycleControl;
import cn.wenet.networkcomponent.debug.WeDebug;
import cn.wenet.networkcomponent.intercepter.BaseInterceptor;
import cn.wenet.networkcomponent.intercepter.NetInterceptorFactory;
import cn.wenet.networkcomponent.okhttp.NetOkHttp;
import cn.wenet.networkcomponent.request.WeNetworkCallBack;
import cn.wenet.networkcomponent.request.NetRequest;
import cn.wenet.networkcomponent.retrofit.NetRetrofit;
import cn.wenet.networkcomponent.rxjava.NetRetryWhen;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
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

    private NetRetryWhen retryWhen;

    private Control() {

    }

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

    public final static String GLOBAL_HEADER = "baseUrl-Header";

    public final static String BASE_URL_HEADER = ":BaseUrl";

    public final static String DEFAULT_BASE_URL_FLAG = GLOBAL_HEADER+BASE_URL_HEADER;

    public Map<String, Object> mParams = new Hashtable<>();

    public Map<String, Object> mBaseParams = new HashMap<>();

    public Map<String, HttpUrl> mBaseUrls = new HashMap<>();

    private volatile boolean mHaveInit = false;

    private NetOkHttp mNetOkHttp;

    private NetRetrofit mNetRetrofit;

    private Context mContext;

    private NetBaseObserver mNetBaseObserver;

    public Context getContext() {
        return mContext;
    }

    public boolean isHaveInit() {
        return mHaveInit;
    }

    public void addBaseInterceptor(BaseInterceptor baseInterceptor) {
        mNetOkHttp.addBaseInterceptor(baseInterceptor);
    }

    public void addInterceptor(List<Interceptor> baseInterceptor) {
        mNetOkHttp.addInterceptors(baseInterceptor);
    }

    public void addBaseParams(String key, Object value) {
        checkNull("addBaseParams", "key Or value", key, value);
        mBaseParams.put(key, value);
    }

    public void addBaseParams(Map<String, Object> params) {
        checkNull("addBaseParams", "params", params);
        mBaseParams.clear();
        mBaseParams.putAll(params);
    }

    public void addBaseUrl(String flag, String url) {
        checkNull("init", "baseUrl", url);
        transformationUrl(flag, url);
    }

    public Map<String, Object> clearParams() {
        mParams.clear();
        mParams.putAll(mBaseParams);
        return mParams;
    }

    public Map<String, HttpUrl> getBaseUrls() {
        return mBaseUrls;
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
     */
    public void combination() {
        if (!mHaveInit) {
            throw new RuntimeException("初始化过程有误!");
        }
        boolean haveChange = mNetOkHttp.isHaveChange();
        if (haveChange) {
            mNetRetrofit.transform(mNetOkHttp.getOkHttpClient());
        }
    }

    /**
     * 订阅
     *
     * @param observable
     * @param callback
     */
    public void subscribe(Observable observable, NetBaseObserver callback) {
        checkNull("subscribe", "callback", callback);
        toSubscribe(observable, callback);
    }

    /**
     * 获取接收结果的Observe
     *
     * @param netCallBack
     * @return
     */
    public NetBaseObserver getBaseObserve(WeNetworkCallBack netCallBack, NetLifecycleControl tag) {
        if (null == mNetBaseObserver) {
            mNetBaseObserver = new NetBaseObserver(netCallBack, tag);
        } else {
            mNetBaseObserver.setNetCallBack(netCallBack);
            mNetBaseObserver.setTag(tag);
        }
        return mNetBaseObserver;
    }

    /**
     * RxJava订阅事件
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    public <T> void toSubscribe(Observable<T> observable, NetBaseObserver<T> observer) {
        if(null == retryWhen) {
            retryWhen = new NetRetryWhen(NetBaseParam.RETRYWHEN_COUNT, NetBaseParam.RETRYWHEN_COUNT);
        }
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(NetBaseParam.READ_TIMEOUT, TimeUnit.SECONDS)
                .retryWhen(retryWhen)
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

    public void init(Context context) {
        mContext = context;
        mNetOkHttp = NetOkHttp.getInstance();
        mNetRetrofit = NetRetrofit.getInstance();
        mNetOkHttp.addBaseInterceptor(NetInterceptorFactory.logInterceptor());
        mNetOkHttp.addBaseInterceptor(NetInterceptorFactory.baseUrlInterceptor());
        mHaveInit = true;
    }

    private void transformationUrl(String flag, String url) {
        String[] split = flag.split(":");
        if (split.length <= 0) {
            throw new IllegalArgumentException("Please check that your parameters are correct !");
        }
        String base = BASE_URL_HEADER.replace(":","").trim();
        if (base.equals(split[1])) {
            WeDebug.e("Base url is "+url);
            mNetRetrofit.setBaseUrl(url);
            combination();
        }
        HttpUrl httpUrl = HttpUrl.parse(url);
        mBaseUrls.put(split[1], httpUrl);
    }

    private void checkNull(String method, String filename, Object... o) {
        for (int i = 0; i < o.length; i++) {
            if (null == o[i]) {
                throw new NullPointerException(WeDebug.getNullPointerErrorInfo(method, filename));
            }
        }
    }

}
