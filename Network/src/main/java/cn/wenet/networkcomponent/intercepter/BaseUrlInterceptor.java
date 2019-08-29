package cn.wenet.networkcomponent.intercepter;


import java.io.IOException;

import cn.wenet.networkcomponent.WeNetwork;
import cn.wenet.networkcomponent.request.NetRequest;
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

public class BaseUrlInterceptor extends BaseInterceptor  implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        NetRequest currentRequest = WeNetwork.getCurrentRequest();
        if(null != currentRequest && currentRequest.baseUrlIsChange()){
            String baseUrl = currentRequest.getRequestBaseUrl();
            HttpUrl parse = HttpUrl.parse(baseUrl);
            HttpUrl httpUrl = request.url();
            Request.Builder builder = request.newBuilder();

            return chain.proceed(builder.url(parse).build());

        }
        return chain.proceed(request);
    }

    @Override
    public boolean isNetInterceptor() {
        return false;
    }
}
