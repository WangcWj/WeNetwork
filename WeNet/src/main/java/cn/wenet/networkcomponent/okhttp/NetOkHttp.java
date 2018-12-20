package cn.wenet.networkcomponent.okhttp;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
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

    private ArrayList<Interceptor> mBaseInterceptors = new ArrayList<>();

    private ArrayList<Interceptor> mCacheInterceptor = new ArrayList<>();

    public OkHttpClient getOkHttpClient() {
        emptyBuild();
        if (null == mOkHttpClient || haveChange) {
            Log.e("WANG", "NetOkHttp.getOkHttpClient.从创建了一次");
            mOkHttpClient = builder.build();
        }
        haveChange = false;
        return mOkHttpClient;
    }

    public void init() {
        change();
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(NetBaseParam.CONNECTION_TIME, TimeUnit.SECONDS)
                .readTimeout(NetBaseParam.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(NetBaseParam.WRITER_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(NetInterceptorFactory.httpLogInterceptor());

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

    /**
     * 这些都是每个接口自己的Interceptor跟我们的mBaseInterceptors区分开.
     *
     * @param interceptor 不为空的时候就添加到拦截器中,当为空的时候就需要清除掉之前缓存的拦截器.
     */
    public void addLogInterceptor(List<Interceptor> interceptor) {
        emptyBuild();
        List<Interceptor> interceptors = builder.interceptors();
        if (mCacheInterceptor.size() != 0) {
            interceptors.removeAll(mCacheInterceptor);
            mCacheInterceptor.clear();
            change();
        }
        if (null != interceptor && interceptor.size() >= 0) {
            //当设置的interceptor为null时 就清楚所有非BaseInterceptor的拦截器.
            interceptors.addAll(interceptor);
            mCacheInterceptor.addAll(interceptor);
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
