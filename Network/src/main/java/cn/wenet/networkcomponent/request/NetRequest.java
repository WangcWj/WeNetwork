package cn.wenet.networkcomponent.request;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.wenet.networkcomponent.control.Control;
import cn.wenet.networkcomponent.base.NetBaseObserver;
import cn.wenet.networkcomponent.base.NetLifecycleControl;
import cn.wenet.networkcomponent.intercepter.BaseInterceptor;
import io.reactivex.Observable;
import okhttp3.Interceptor;

/**
 * @author WANG
 * @date 2018/5/4
 * 请求从这里发起.
 * 1.这里管理基础参数
 * 2.管理Header的添加
 * 3.管理文件上传RequestBody
 */

public class NetRequest {

    private Control netControl;

    private NetLifecycleControl mDestroyDisposable;

    private String mCurrentBaseUrl = null;

    public Map<String, Object> mParams = new HashMap<>();

    private ArrayList<Interceptor> mInterceptor;

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
        if (null == mInterceptor) {
            mInterceptor = new ArrayList<>();
        }
        mInterceptor.add(interceptor);
        return this;
    }

    public <T> void execute(NetCallBack<T> callback) {
        //要先执行
        combination();
        Observable observable = callback.getMethod(this, mParams);
        baseExecute(callback, observable);
    }

    public <T> void executeForObject(NetObjectCallBack<T> callback) {
        combination();
        Observable observable = callback.getMethod(this, mParams);
        baseExecute(callback, observable);
    }

    private void baseExecute(BaseCallBack callback, Observable observable) {
        NetBaseObserver baseObserver = netControl.getBaseObserve(callback, mDestroyDisposable);
        subscribe(observable, baseObserver);
    }

    private void combination() {
        netControl.addInterceptor(mInterceptor);
        //这里面重新的组装Retrofit+OKHttp
        netControl.combination(mCurrentBaseUrl);
    }

    private NetRequest subscribe(Observable observable, NetBaseObserver callback) {
        netControl.subscribe(observable, callback);
        return this;
    }

}
