package cn.wenet.networkcomponent.okhttp;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.wenet.networkcomponent.base.NetBaseParam;
import cn.wenet.networkcomponent.intercepter.BaseInterceptor;
import cn.wenet.networkcomponent.intercepter.NetInterceptorFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

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
        builder.connectTimeout(NetBaseParam.CONNECTION_TIME, TimeUnit.SECONDS)
                .readTimeout(NetBaseParam.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(NetBaseParam.WRITER_TIMEOUT, TimeUnit.SECONDS)
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
