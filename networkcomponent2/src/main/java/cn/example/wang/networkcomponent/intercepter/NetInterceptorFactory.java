package cn.example.wang.networkcomponent.intercepter;

import cn.example.wang.networkcomponent.NetControl;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 *
 * @author WANG
 * @date 2018/7/19
 * 这里来创建拦截器
 */

public class NetInterceptorFactory {

    public static LogInterceptor logInterceptor() {
        return new LogInterceptor(NetControl.getInstance().getTag());
    }

    public static HttpLoggingInterceptor httpLogInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    public static BaseUrlInterceptor baseUrlInterceptor() {
        BaseUrlInterceptor httpLoggingInterceptor = new BaseUrlInterceptor();
        return httpLoggingInterceptor;
    }

}
