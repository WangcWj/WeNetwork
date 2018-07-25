package cn.wang.network.builder.net;

import java.util.HashMap;
import java.util.Map;
import cn.wang.network.builder.net.base.BaseObserver;
import cn.wang.network.builder.net.base.BaseParam;
import cn.wang.network.builder.net.dispatcher.NetDispatcher;
import cn.wang.network.builder.net.base.NetAddDestroyDisposable;
import cn.wang.network.builder.net.okhttp.NetOkHttp;
import cn.wang.network.builder.net.request.NetCallBack;
import cn.wang.network.builder.net.request.NetRequest;
import cn.wang.network.builder.net.retrofit.NetRetrofit;
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

    private Map<String, CompositeDisposable> mCompositeDisposableMap = new HashMap<>();

    public void needPrintLog(boolean need) {
        mNetOkHttp.needPrientLog(need);
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
        mCompositeDisposableMap.clear();
        mCompositeDisposableMap = null;
        instance = null;
    }

    public void init() {
        mDispatcher = new NetDispatcher(this);
        mRetryWhenCount = BaseParam.RETRYWHEN_COUNT;
        mRetryWhenTime = BaseParam.RETRYWHEN_TIME;
        mNetOkHttp = new NetOkHttp();
        mNetOkHttp.init();
        mNetRetrofit = new NetRetrofit();
        mNetRetrofit.setBaseUrl(BaseParam.BASE_URL);
        mNetRetrofit.init(mNetOkHttp);
        mHaveInit = true;
    }


}
