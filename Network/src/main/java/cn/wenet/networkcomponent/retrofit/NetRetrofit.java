package cn.wenet.networkcomponent.retrofit;

import android.util.Log;

import cn.wenet.networkcomponent.convert.ToStringConverterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 只管理Retrofit有关的事物.
 * BaseUrl的替换在拦截器中处理.
 *
 * @author WANG
 * @date 17/11/23
 */

public class NetRetrofit {

    private Retrofit mRetrofit = null;
    private Retrofit.Builder builder;

    /**
     * 当builder内容发生变化的时候就去重新build.
     */
    private volatile boolean haveChange = false;

    public static NetRetrofit getInstance() {
        return new NetRetrofit();
    }

    public void setBaseUrl(String mBaseUrl) {
        builder.baseUrl(mBaseUrl);
        change();
    }

    private NetRetrofit() {
        init();
    }

    public Retrofit getRetrofit() {
        emptyBuild();
        if (null == mRetrofit || haveChange) {
            Log.e("WANG", "NetRetrofit.getRetrofit.重新创建了一次");
            mRetrofit = builder.build();
        }
        haveChange = false;
        return mRetrofit;
    }

    public void init() {
        builder = new Retrofit.Builder()
                .addConverterFactory(ToStringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    public void transform(OkHttpClient okHttpClient) {
        emptyBuild();
        builder.client(okHttpClient);
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
