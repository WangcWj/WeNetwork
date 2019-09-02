package cn.wenet.networkcomponent.okhttp;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import cn.wenet.networkcomponent.base.NetBaseParam;
import cn.wenet.networkcomponent.okhttp.intercepter.BaseInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * 只管理跟OkHttp有关的业务,动态添加拦截器的功能去掉了.
 *
 * @author WANG
 * @date 17/11/23
 */

public class NetOkHttp {

    private OkHttpClient mOkHttpClient = null;

    private OkHttpClient.Builder builder;

    public static NetOkHttp getInstance() {
        return new NetOkHttp();
    }

    private NetOkHttp() {
        init();
    }

    /**
     * 当builder内容发生变化的时候就去重新build.
     */
    private volatile boolean haveChange = false;

    private ArrayList<Interceptor> mBaseInterceptors = new ArrayList<>();

    public OkHttpClient getOkHttpClient() {
        emptyBuild();
        if (null == mOkHttpClient || haveChange) {
            mOkHttpClient = builder.build();
        }
        haveChange = false;
        return mOkHttpClient;
    }

    public void init() {
        SSLSocketFactory sslSocketFactory = SSLSocketClient.getSSLSocketFactory(SSLSocketClient.UnSafeTrustManager);
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(NetBaseParam.CONNECTION_TIME, TimeUnit.SECONDS)
                .readTimeout(NetBaseParam.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(NetBaseParam.WRITER_TIMEOUT, TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory,SSLSocketClient.UnSafeTrustManager)
                .hostnameVerifier(SSLSocketClient.UnSafeHostnameVerifier);
        change();
    }

    public void addBaseInterceptor(@NonNull BaseInterceptor interceptor) {
        emptyBuild();
        if (!mBaseInterceptors.contains(interceptor)) {
            mBaseInterceptors.add(interceptor);
            if (interceptor.isNetInterceptor()) {
                builder.addNetworkInterceptor(interceptor);
            } else {
                builder.addInterceptor(interceptor);
            }
            change();
        }
    }

    private void emptyBuild() {
        if (null == builder) {
            throw new NullPointerException("NetOkHttp.mOkHttpClient.Builder  is null !");
        }
    }

    public boolean isHaveChange() {
        return haveChange;
    }

    private void change() {
        haveChange = true;
    }
}
