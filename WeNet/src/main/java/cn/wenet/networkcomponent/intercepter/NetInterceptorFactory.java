package cn.wenet.networkcomponent.intercepter;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 *
 * @author WANG
 * @date 2018/7/19
 * 这里来创建拦截器
 */

public class NetInterceptorFactory {

    public static LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    public static HttpLoggingInterceptor httpLogInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("WANG","NetInterceptorFactory.log : "+message );
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    public static BaseUrlInterceptor baseUrlInterceptor() {
        BaseUrlInterceptor httpLoggingInterceptor = new BaseUrlInterceptor();
        return httpLoggingInterceptor;
    }

}
