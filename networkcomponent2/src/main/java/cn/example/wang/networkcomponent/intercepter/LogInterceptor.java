package cn.example.wang.networkcomponent.intercepter;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;
import okio.Utf8;

/**
 * @author WANG
 * @date 2018/5/3
 */

public class LogInterceptor extends BaseInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private String mTag;

    public LogInterceptor(String tag) {
        this.mTag = tag;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        String s = request.url().toString();
        Log.e(mTag, "url: " + s);
        RequestBody body = request.body();
        if (null != body) {
            String bodyStr = body.toString();
            Log.e(mTag, "RequestBody : " + bodyStr);
        }
        String headers = request.headers().toString();
        Log.e(mTag, "headers:" + headers);

        Headers responseHeaders = response.headers();
        for (int i = 0,count = responseHeaders.size() ; i < count; i++) {
            Log.e(mTag,"response.headers :  name  "+responseHeaders.name(i)+"      value     "+responseHeaders.value(i) );
        }
        ResponseBody responseBody = response.body();
        if(HttpHeaders.hasBody(response) && !bodyEncoded(response.headers())){
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType mediaType = responseBody.contentType();
            if(mediaType != null){
                charset = mediaType.charset(UTF8);
            }
            String readString = buffer.clone().readString(charset);
            Log.e("WANG","responseBody : "+readString );

        }


        return response;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    @Override
    public String signInterceptor() {
        return LogInterceptor.class.getName();
    }
}
