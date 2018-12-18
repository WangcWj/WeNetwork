package cn.example.wang.networkcomponent.request;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import cn.example.wang.networkcomponent.control.Control;
import cn.example.wang.networkcomponent.base.BaseObserver;
import cn.example.wang.networkcomponent.base.NetLifecycleControl;
import cn.example.wang.networkcomponent.intercepter.BaseInterceptor;
import io.reactivex.Observable;

/**
 * Created by WANG on 2018/5/4.
 * 请求从这里发起.
 * 1.这里管理基础参数
 * 2.管理Header的添加
 * 3.管理文件上传RequestBody
 */

public class NetRequest {

    private Control netControl;

    private NetLifecycleControl mDestroyDisposable;

    private String mCurrentBaseUrl = null;

    private boolean mOkhttpNeedChange = false;

    public Map<String, Object> mParams = new HashMap<>();

    public NetRequest(Control netControl, NetLifecycleControl mDestroyDisposable) {
        this.netControl = netControl;
        this.mDestroyDisposable = mDestroyDisposable;
    }

    public NetRequest addParams(String key, String value) {
        mParams.put(key, value);
        return this;
    }

    public NetRequest addParams(Map params) {
        mParams.putAll(params);
        return this;
    }

    public NetRequest baseUrl(String baseUrl) {
        if (TextUtils.isEmpty(baseUrl) || !baseUrl.startsWith("http")) {
            throw new IllegalStateException("'BaseUrl' is empty or does not start with 'http' !");
        }
        mCurrentBaseUrl = baseUrl;
        return this;
    }

    public <T> T getApiService(Class<T> claz) {
        return netControl.getApiService(claz);
    }

    public NetRequest addInterceptor(BaseInterceptor interceptor) {
        netControl.addInterceptor(interceptor);
        mOkhttpNeedChange = true;
        return this;
    }

    /**
     * Observable observable = callback.getMethod(this, mParams).map(new Function<BaseResultBean<T>,T>() {
     *
     * @param callback
     * @param <T>
     * @Override public T apply(BaseResultBean<T> tBaseResultBean) throws Exception {
     * T data = tBaseResultBean.getData();
     * Log.e("WANG","NetRequest.apply."+tBaseResultBean.toString() );
     * return data;
     * }
     * });
     */
    public <T> void execute(NetCallBack<T> callback) {
        //要先执行
        combination();
        Observable observable = callback.getMethod(this, mParams);
        baseExecute(callback, observable);
    }

    public <T> void executeForJson(NetJsonCallBack<T> callback) {
        combination();
        Observable observable = callback.getMethod(this, mParams);
        baseExecute(callback, observable);
    }

    private void baseExecute(BaseCallBack callback, Observable observable) {
        BaseObserver baseObserver = netControl.getBaseObserve(callback, mDestroyDisposable);
        subscribe(observable, baseObserver);
    }

    private void combination() {
        //这里面重新的组装Retrofit+OKHttp
        netControl.combination(mCurrentBaseUrl, mOkhttpNeedChange);
    }

    private NetRequest subscribe(Observable observable, BaseObserver callback) {
        netControl.subscribe(observable, callback);
        return this;
    }

}
