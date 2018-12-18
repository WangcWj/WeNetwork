package cn.example.wang.networkcomponent.retrofit;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import cn.example.wang.networkcomponent.convert.ToStringConverterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author WANG
 * @date 17/11/23
 * 只管理Retrofit有关的事物
 * 当BaseUrl替换或者是OkHttp动态的添加拦截器的时候 要生成全新的Retrofit.
 */

public class NetRetrofit {

    private Retrofit mRetrofit = null;
    private Retrofit.Builder builder;

    /**
     * 防止同一个BaseUrl每次都重新创建Retrofit对象.
     */
    private String mPreBaseUrl;

    /**
     * 当builder内容发生变化的时候就去重新build.
     */
    private volatile boolean haveChange = false;

    public static NetRetrofit getInstance() {
        return new NetRetrofit();
    }

    private NetRetrofit() {
        init();
    }

    public Retrofit getRetrofit() {
        emptyBuild();
        if (null == mRetrofit || haveChange) {
            Log.e("WANG", "NetRetrofit.getRetrofit.重新创建了一次");
            mRetrofit = builder.build();
            haveChange = false;
        }
        return mRetrofit;
    }

    public void init() {
        builder = new Retrofit.Builder()
                .addConverterFactory(ToStringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    public void transform(String mBaseUrl) {
        emptyBuild();
        builder.baseUrl(mBaseUrl);
        if (!mBaseUrl.equals(mPreBaseUrl)) {
            change();
            mPreBaseUrl = mBaseUrl;
        }
    }

    public void transform(OkHttpClient okHttpClient) {
        emptyBuild();
        this.builder.client(okHttpClient);
        change();
    }

    private void emptyBuild() {
        if (null == builder) {
            throw new NullPointerException("NetRetrofit.Retrofit.Builder  is NULL !");
        }
    }

    private void change() {
        haveChange = true;
    }

}
