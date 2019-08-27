package cn.wenet.networkcomponent.intercepter;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author WANG
 * @date 2018/5/3
 */

public class BaseUrlInterceptor extends BaseInterceptor  implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        return response;
    }

    @Override
    public boolean isNetInterceptor() {
        return false;
    }
}
