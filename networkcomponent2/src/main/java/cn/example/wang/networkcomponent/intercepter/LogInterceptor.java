package cn.example.wang.networkcomponent.intercepter;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author WANG
 * @date 2018/5/3
 */

public class LogInterceptor extends BaseInterceptor implements Interceptor {

    private String mTag;
    private boolean mInterceptor;

    public LogInterceptor(String tag) {
        this.mTag = tag;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (mInterceptor) {
            String s = request.url().toString();
            Log.e(mTag, "url: " + s);
            RequestBody body = request.body();
            if (null != body) {
                String bodyStr = body.toString();
                Log.e(mTag, "RequestBody : " + bodyStr);
            }
            String headers = request.headers().toString();
            Log.e(mTag,"headers:"+headers );
        }
        return response;
    }

    @Override
    public String signInterceptor() {
        return LogInterceptor.class.getName();
    }
}
