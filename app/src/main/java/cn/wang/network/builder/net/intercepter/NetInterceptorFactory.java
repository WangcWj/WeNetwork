package cn.wang.network.builder.net.intercepter;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by WANG on 2018/7/19.
 * 这里来创建拦截器
 */

public class NetInterceptorFactory {

    public static LogInterceptor logInterceptor() {
        return new LogInterceptor("WANG");
    }

    public static Interceptor httpLogInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

}
