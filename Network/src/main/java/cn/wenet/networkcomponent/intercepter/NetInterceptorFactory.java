package cn.wenet.networkcomponent.intercepter;


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

    public static BaseUrlInterceptor baseUrlInterceptor() {
        BaseUrlInterceptor httpLoggingInterceptor = new BaseUrlInterceptor();
        return httpLoggingInterceptor;
    }

}
