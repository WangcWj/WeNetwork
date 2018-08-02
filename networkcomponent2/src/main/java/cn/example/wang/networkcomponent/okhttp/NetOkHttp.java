package cn.example.wang.networkcomponent.okhttp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.example.wang.networkcomponent.base.BaseParam;
import cn.example.wang.networkcomponent.intercepter.LogInterceptor;
import cn.example.wang.networkcomponent.intercepter.NetInterceptorFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by WANG on 17/11/23.
 * 只管理跟OkHttp有关的业务
 */

public class NetOkHttp {

    private OkHttpClient mOkHttpClient;
    private OkHttpClient.Builder builder;
    private LogInterceptor logInterceptor;
    private Interceptor httpLogInterceptor;
    private long mConnectTimeout;
    private long mReadTimeout;
    private long mWriteTimeout;
    public NetOkHttp() {
        mConnectTimeout = BaseParam.CONNECTION_TIME;
        mReadTimeout = BaseParam.READ_TIMEOUT;
        mWriteTimeout = BaseParam.WRITER_TIMEOUT;
    }

    public void init() {
        logInterceptor = NetInterceptorFactory.logInterceptor();
        httpLogInterceptor = NetInterceptorFactory.httpLogInterceptor();
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(mConnectTimeout, TimeUnit.SECONDS);
        builder.readTimeout(mReadTimeout, TimeUnit.SECONDS);
        builder.writeTimeout(mWriteTimeout, TimeUnit.SECONDS);
        builder.addInterceptor(logInterceptor);
        builder.addNetworkInterceptor(httpLogInterceptor);
        mOkHttpClient = builder.build();
    }

    public void needPrientLog(boolean needLog){
        logInterceptor.setInterceptor(needLog);
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public List<Interceptor> getInterceptors() {
        return mOkHttpClient.interceptors();
    }

    public void setConnectTimeout(long mConnectTimeout) {
        this.mConnectTimeout = mConnectTimeout;
    }

    public void setReadTimeout(long mReadTimeout) {
        this.mReadTimeout = mReadTimeout;
    }

    public void setWriteTimeout(long mWriteTimeout) {
        this.mWriteTimeout = mWriteTimeout;
    }

    public long getConnectTimeout() {
        return mConnectTimeout;
    }

    public long getReadTimeout() {
        return mReadTimeout;
    }

    public long getWriteTimeout() {
        return mWriteTimeout;
    }

}
