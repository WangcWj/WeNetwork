package cn.wang.download;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/4/15
 */
public class WeDownLoanManager implements Callback {

    private OkHttpClient okHttpClient;
    private WeRequest mWeRequest;
    private Call mOkCall;


    public WeDownLoanManager(Builder builder) {
        okHttpClient = builder.okHttpClient;
        mWeRequest = builder.weRequest;
    }

    public void execute() {
        request(0);
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
        mOkCall.enqueue(this);
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

    }

    public static class Builder {
        private OkHttpClient.Builder builder;
        private OkHttpClient okHttpClient;
        private WeRequest weRequest;

        public Builder() {
            builder = new OkHttpClient.Builder()
                    .readTimeout(3, TimeUnit.MINUTES)
                    .writeTimeout(3, TimeUnit.MINUTES)
                    .connectTimeout(3, TimeUnit.MINUTES);
            weRequest = new WeRequest();
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

        public Builder addListener(ProgressListener progressListener) {
            if (null != builder) {
                builder.addInterceptor(IntercepterFactory.createProgressIntercepter(progressListener));
            }
            return this;
        }

        public WeDownLoanManager build() {
            okHttpClient = builder.build();
            return new WeDownLoanManager(this);
        }
    }
}