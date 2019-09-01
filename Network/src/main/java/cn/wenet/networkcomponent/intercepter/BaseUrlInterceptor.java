package cn.wenet.networkcomponent.intercepter;


import android.text.TextUtils;

import java.io.IOException;
import java.util.Map;

import cn.wenet.networkcomponent.WeNetwork;
import cn.wenet.networkcomponent.control.Control;
import cn.wenet.networkcomponent.debug.WeDebug;
import cn.wenet.networkcomponent.urlparse.WeUrlParse;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * https://github.com/JessYanCoding/RetrofitUrlManager/tree/master/manager/src/main/java/me/jessyan/retrofiturlmanager
 *
 * @author WANG
 * @date 2018/5/3
 */

public class BaseUrlInterceptor extends BaseInterceptor implements Interceptor {

    private WeUrlParse urlParse;


    public BaseUrlInterceptor() {
        urlParse = new WeUrlParse();
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Headers headers = request.headers();
        String header = headers.get(Control.GLOBAL_HEADER);
        WeDebug.e("Header  is  "+header);
        if (!TextUtils.isEmpty(header) && !Control.BASE_URL_HEADER.equals(header)) {
            WeDebug.e("检测到新的BaseUrl。。。。");
            Map<String, HttpUrl> baseUrls = WeNetwork.getBaseUrls();
            HttpUrl httpUrl = baseUrls.get(header);
            if (null != httpUrl) {
                HttpUrl newHttpUrl = urlParse.parseUrl(httpUrl, request.url());
                WeDebug.e("新的Url是：" + newHttpUrl);
                return chain.proceed(request.newBuilder().url(newHttpUrl).build());
            }
        }
        return chain.proceed(request);
    }

    @Override
    public boolean isNetInterceptor() {
        return false;
    }
}
