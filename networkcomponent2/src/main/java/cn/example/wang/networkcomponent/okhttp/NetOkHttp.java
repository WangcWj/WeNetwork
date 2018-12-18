package cn.example.wang.networkcomponent.okhttp;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.example.wang.networkcomponent.base.BaseParam;
import cn.example.wang.networkcomponent.intercepter.BaseInterceptor;
import cn.example.wang.networkcomponent.intercepter.LogInterceptor;
import cn.example.wang.networkcomponent.intercepter.NetInterceptorFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author WANG
 * @date 17/11/23
 * 只管理跟OkHttp有关的业务
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

    private Map<Class<? extends BaseInterceptor>, Interceptor> mCacheInterceptor = new HashMap<>();

    public OkHttpClient getOkHttpClient() {
        if (builder == null) {
            throw new NullPointerException("NetOkHttp.OkHttpClient.Builder  is NULL !");
        }
        if (null == mOkHttpClient || haveChange) {
            mOkHttpClient = builder.build();
        }
        return mOkHttpClient;
    }

    public void init() {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(BaseParam.CONNECTION_TIME, TimeUnit.SECONDS)
                .readTimeout(BaseParam.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(BaseParam.WRITER_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(NetInterceptorFactory.httpLogInterceptor());

    }

    public void addLogInterceptor(@NonNull BaseInterceptor interceptor) {
        if (null == builder) {
            throw new RuntimeException("NetOkHttp.mOkHttpClient Is Null !");
        }
        Interceptor instance = mCacheInterceptor.get(interceptor.getClass());
        if (null == instance) {
            instance = interceptor;
        }
        mCacheInterceptor.put(interceptor.getClass(), instance);
        builder.addInterceptor(instance);
        change();
    }

    public void removeLogInterceptor(@NonNull Class<?> interceptorClass) {


    }


    public List<Interceptor> getInterceptors() {
        return mOkHttpClient.interceptors();
    }

    private void change() {
        haveChange = true;
    }
}
