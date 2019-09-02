package cn.wenet.networkcomponent.okhttp.intercepter;


import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cn.wenet.networkcomponent.control.Control;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author WANG
 * @date 2018/5/3
 */

public class BaseParamsInterceptor extends BaseInterceptor implements Interceptor {

    private final String POST = "POST";
    private final String GET = "GET";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oriRequest = chain.request();
        Map<String, Object> params = Control.getInstance().getParams();
        if (params.size() <= 0) {
            return chain.proceed(oriRequest);
        }
        if (POST.equals(oriRequest.method()) && null != oriRequest.body()) {
            RequestBody body = oriRequest.body();
            RequestBody newBody = null;
            if (body instanceof FormBody) {
                //表单请求 也就是一般的POST请求.
                newBody = addParamsToFormBody(params, (FormBody) body);
            } else if (oriRequest.body() instanceof MultipartBody) {
                //文件请求
                newBody = addParamsToMultipartBody(params, (MultipartBody) body);
            }
            if (null == newBody) {
                return chain.proceed(oriRequest);
            }
            //重新组装Request
            Request.Builder builder = oriRequest.newBuilder()
                    .url(oriRequest.url())
                    .method(oriRequest.method(), newBody);
            return chain.proceed(builder.build());

        } else if (GET.equals(oriRequest.method())) {
            //GET请求拼接参数给HttpUrl.
            HttpUrl httpUrl = oriRequest.url();
            HttpUrl newHttpUrl = addParamsToHttpUrl(params, httpUrl);
            //重新组装
            Request.Builder requestBuilder = oriRequest.newBuilder();
            requestBuilder.url(newHttpUrl);
            return chain.proceed(requestBuilder.build());
        }
        return chain.proceed(oriRequest);
    }

    private HttpUrl addParamsToHttpUrl(Map<String, Object> params, HttpUrl httpUrl) {
        HttpUrl.Builder newBuilder = httpUrl.newBuilder();
        Set<String> keySet = params.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = params.get(key);
            newBuilder.addEncodedQueryParameter(key, (String) value);
        }
        return newBuilder.build();
    }

    private MultipartBody addParamsToMultipartBody(Map<String, Object> params, MultipartBody body) {
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //添加新的参数
        Iterator iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            multipartBuilder.addFormDataPart((String) entry.getKey(), null, RequestBody.create(MediaType.parse("multipart/form-data; charset=utf-8"), (String) entry.getValue()));
        }
        //添加原始的参数 目前是没有原始参数的.
        return multipartBuilder.build();
    }

    private FormBody addParamsToFormBody(Map<String, Object> params, FormBody body) {
        FormBody.Builder builder = new FormBody.Builder();
        //添加新的参数
        if (params.size() > 0) {
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                builder.add((String) entry.getKey(), (String) entry.getValue());
            }
        }
        //添加原始的参数 目前是没有原始参数的.
        return builder.build();
    }

    @Override
    public boolean isNetInterceptor() {
        return false;
    }
}
