package cn.wenet.networkcomponent.okhttp.intercepter;

import okhttp3.Interceptor;

/**
 *
 * @author WANG
 * @date 2018/7/19
 */

public abstract class BaseInterceptor implements Interceptor {

 public abstract boolean isNetInterceptor();

}
