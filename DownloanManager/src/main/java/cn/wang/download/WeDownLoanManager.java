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
public class WeDownLoanManager {

    private OkHttpClient okHttpClient;
    private Call mCall;


    public WeDownLoanManager(Builder builder) {
        okHttpClient = builder.okHttpClient;
        mCall = okHttpClient.newCall(builder.request);
    }

    public void execute(){
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response){

            }
        });
    }

    public void stop(){

    }

    private void cancel(){
        if(null != mCall && !mCall.isCanceled()){
            mCall.cancel();
        }
    }



    public static class Builder {
        private OkHttpClient.Builder builder;
        private OkHttpClient okHttpClient;
        private Request request;
        private Request.Builder requestBuilder;

        private WeRequest weRequest;
        private volatile long startPoints;


        public Builder() {
            builder = new OkHttpClient.Builder()
                    .readTimeout(3, TimeUnit.MINUTES)
                    .writeTimeout(3, TimeUnit.MINUTES)
                    .connectTimeout(3, TimeUnit.MINUTES);
            weRequest = new WeRequest();
            requestBuilder = new Request.Builder();

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

        public WeDownLoanManager build() {
            okHttpClient = builder.build();
            request = requestBuilder.addHeader("RANGE", "bytes=" + startPoints + "-")
                    .url(weRequest.getUrl())
                    .build();
            return new WeDownLoanManager(this);
        }
    }
}