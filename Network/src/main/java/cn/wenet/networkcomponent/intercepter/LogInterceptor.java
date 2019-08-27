package cn.wenet.networkcomponent.intercepter;


import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author WANG
 * @date 2018/5/3
 */

public class LogInterceptor extends BaseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Log.e("WANG", "LogInterceptor.intercept.新增的啊");
        return response;
    }

    @Override
    public boolean isNetInterceptor() {
        return false;
    }
}
