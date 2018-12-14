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
 * Created by WANG on 17/11/23.
 * 只管理跟OkHttp有关的业务
 */

public class NetOkHttp {

    private OkHttpClient mOkHttpClient;
    private OkHttpClient.Builder builder;
    private Map<Class<? extends BaseInterceptor>,Interceptor> mCacheIntercepter = new HashMap<>();

    public void init() {
        HttpLoggingInterceptor httpLogInterceptor = NetInterceptorFactory.httpLogInterceptor();
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(BaseParam.CONNECTION_TIME, TimeUnit.SECONDS);
        builder.readTimeout(BaseParam.READ_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(BaseParam.WRITER_TIMEOUT, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(httpLogInterceptor);
        builder.interceptors();
        mOkHttpClient = builder.build();
    }

    public void addLogInterceptor(@NonNull BaseInterceptor interceptor) {
        if(null == mOkHttpClient){
            throw new RuntimeException("NetOkHttp.mOkHttpClient Is Null !");
        }
        Log.e("WANG","NetOkHttp.addLogInterceptor."+interceptor.getClass());
        Interceptor instance =  mCacheIntercepter.get(interceptor.getClass());
        if(null == instance){
          instance = interceptor;
        }
        mCacheIntercepter.put(interceptor.getClass(),instance);
        mOkHttpClient.newBuilder().addInterceptor(instance);
    }

    public void removeLogInterceptor(@NonNull BaseInterceptor interceptor) {


    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public List<Interceptor> getInterceptors() {
        return mOkHttpClient.interceptors();
    }
}
