package cn.example.wang.networkcomponent.retrofit;

import cn.example.wang.networkcomponent.convert.ToStringConverterFactory;
import cn.example.wang.networkcomponent.okhttp.NetOkHttp;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WANG on 17/11/23.
 * 只管理Retrofit有关的事物
 * 当BaseUrl替换或者是OkHttp动态的添加拦截器的时候 要生成全新的Retrofit.
 */

public class NetRetrofit {

    private Retrofit mRetrofit;
    private String mBaseUrl;

    public void setBaseUrl(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public void init(NetOkHttp netOkHttp) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(ToStringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(netOkHttp.getOkHttpClient())
                .build();
    }

    public void transform(String baseUrl) {
        if (mRetrofit == null) {
            return;
        }
        this.mBaseUrl = baseUrl;
        mRetrofit = mRetrofit.newBuilder().baseUrl(baseUrl).build();
    }

    public void transform(String baseUrl, OkHttpClient okHttpClient) {
        if (mRetrofit == null) {
            return;
        }
        this.mBaseUrl = baseUrl;
        mRetrofit = mRetrofit.newBuilder().baseUrl(baseUrl).client(okHttpClient).build();
    }

    public void transform(OkHttpClient okHttpClient) {
        if (mRetrofit == null) {
            return;
        }
        mRetrofit = mRetrofit.newBuilder().client(okHttpClient).build();
    }


}
