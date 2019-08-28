package cn.wenet.networkcomponent.intercepter;


import android.text.TextUtils;

import java.io.IOException;

import cn.wenet.networkcomponent.debug.WeDebug;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author WANG
 * @date 2018/5/3
 */

public class BaseParamsInterceptor extends BaseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl httpUrl = request.url();
        if(null != httpUrl && null != httpUrl.toString()){
            String url = httpUrl.toString();
            if(!TextUtils.isEmpty(url)){
                WeDebug.e("URL is : "+url);
            }
        }
        String method = request.method();
        if(!TextUtils.isEmpty(method)){
            WeDebug.e("Method is : "+method);
        }

        RequestBody body = request.body();

        Response response = chain.proceed(request);
        return response;
    }

    @Override
    public boolean isNetInterceptor() {
        return true;
    }
}
