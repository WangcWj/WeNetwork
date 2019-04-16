package cn.wang.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/4/15
 */
public class ProgressInterceptor implements Interceptor {

    private ProgressListener progressListener;

    public ProgressInterceptor(ProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response proceed = chain.proceed(chain.request());
        return proceed.newBuilder().body(new ProgressResposeBody(proceed.body(),progressListener)).build();
    }
}
