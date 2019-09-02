package cn.wenet.networkcomponent.okhttp.intercepter;


/**
 * @author WANG
 * @date 2018/7/19
 * 这里来创建拦截器
 */

public class NetInterceptorFactory {

    public static BaseInterceptor logInterceptor() {
        return new BaseLogInterceptor();
    }

    public static BaseInterceptor baseUrlInterceptor() {
        return new BaseUrlInterceptor();
    }

    public static BaseInterceptor baseParamsIntercepter() {
        return new BaseParamsInterceptor();
    }


}
