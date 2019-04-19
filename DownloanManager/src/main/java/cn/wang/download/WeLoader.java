package cn.wang.download;

import java.io.File;
import java.util.concurrent.TimeUnit;

import cn.wang.download.intercepter.IntercepterFactory;
import cn.wang.download.request.WeLoaderRequest;
import cn.wang.download.response.DownloadProgressListener;
import cn.wang.download.response.WeLoaderResponse;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/4/15
 */
public class WeLoader {

    private OkHttpClient okHttpClient;
    private WeLoaderRequest mWeRequest;
    private WeLoaderResponse mWeLoaderResponse;
    private Call mOkCall;


    public WeLoader(Builder builder) {
        okHttpClient = builder.okHttpClient;
        mWeRequest = builder.weRequest;
        mWeLoaderResponse = builder.weLoaderResponse;
    }

    public void start() {
        request(0);
    }

    public void keepOn() {
        request(mWeLoaderResponse.getBreakPoint());
    }

    public void stop() {
        cancel();
    }

    private void cancel() {
        if (null != mOkCall && !mOkCall.isCanceled() && !mOkCall.isExecuted()) {
            mOkCall.cancel();
        }
    }

    private void request(long startPoint) {
        Request request = mWeRequest.createRequest(startPoint);
        mOkCall = okHttpClient.newCall(request);
        mOkCall.enqueue(mWeLoaderResponse);
    }

    public static class Builder {
        private OkHttpClient.Builder builder;
        private WeLoaderRequest weRequest;
        private WeLoaderResponse weLoaderResponse;
        private OkHttpClient okHttpClient;

        public Builder() {
            weRequest = new WeLoaderRequest();
            weLoaderResponse = new WeLoaderResponse();
            builder = new OkHttpClient.Builder()
                    .readTimeout(3, TimeUnit.MINUTES)
                    .writeTimeout(3, TimeUnit.MINUTES)
                    .connectTimeout(3, TimeUnit.MINUTES)
                    .addNetworkInterceptor(IntercepterFactory.createProgressIntercepter(weLoaderResponse));
        }

        public Builder url(String url) {
            weRequest.setUrl(url);
            return this;
        }

        public Builder file(String filrPath) {
            weRequest.setFilePath(filrPath);
            return this;
        }

        public Builder file(File file) {
            weRequest.setTargetFile(file);
            return this;
        }

        public Builder addListener(DownloadProgressListener downloadProgressListener) {

            return this;
        }

        public WeLoader build() {
            okHttpClient = builder.build();
            return new WeLoader(this);
        }
    }
}