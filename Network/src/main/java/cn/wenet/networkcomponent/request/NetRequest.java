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

    private ArrayList<Interceptor> mInterceptor;

    private Observable mObservable;

    private String mRequestBaseUrl;

    public boolean baseUrlIsChange(){
        String orgBaseUrl = netControl.getOrgBaseUrl();
        return !mRequestBaseUrl.equals(orgBaseUrl);
    }

    public String getRequestBaseUrl() {
        return mRequestBaseUrl;
    }

    public NetRequest(Control netControl, NetLifecycleControl mDestroyDisposable) {
        this.netControl = netControl;
        this.mDestroyDisposable = mDestroyDisposable;
        netControl.mParams.clear();
    }

    public NetRequest addParams(String key, String value) {
        netControl.mParams.put(key, value);
        return this;
    }

    public NetRequest addParams(Map params) {
        netControl.mParams.putAll(params);
        return this;
    }

    public NetRequest baseUrl(String baseUrl) {
        if (TextUtils.isEmpty(baseUrl) || !baseUrl.startsWith("http")) {
            throw new IllegalStateException("'BaseUrl' is empty or does not start with 'http' !");
        }
        mRequestBaseUrl = baseUrl;
        return this;
    }

    public NetRequest addInterceptor(BaseInterceptor interceptor) {
        if (null == mInterceptor) {
            mInterceptor = new ArrayList<>();
        }
        mInterceptor.add(interceptor);
        return this;
    }

    public Observable getObservable() {
        return mObservable;
    }

    public <T>NetRequest apiMethod(Observable<T> observable){
        mObservable = observable;
        return this;
    }

    public <T> void execute(WeNetworkCallBack<T> callback) {
        if(null == mObservable){
            return;
        }
        //要先执行
        combination();
        baseExecute(callback, mObservable);
    }

    private void baseExecute(WeNetworkCallBack callback, Observable observable) {
        NetBaseObserver baseObserver = netControl.getBaseObserve(callback, mDestroyDisposable);
        subscribe(observable, baseObserver);
    }

    private void combination() {
        netControl.addInterceptor(mInterceptor);
        netControl.combination();
    }

    private NetRequest subscribe(Observable observable, NetBaseObserver callback) {
        netControl.subscribe(observable, callback);
        return this;
    }

}
