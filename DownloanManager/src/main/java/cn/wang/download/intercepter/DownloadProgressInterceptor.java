package cn.wang.download.intercepter;

import java.io.IOException;

import cn.wang.download.response.DownloadProgressListener;
import cn.wang.download.response.DownloadProgressResponseBody;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/4/15
 */
public class DownloadProgressInterceptor implements Interceptor {

    private DownloadProgressListener downloadProgressListener;

    public DownloadProgressInterceptor(DownloadProgressListener downloadProgressListener) {
        this.downloadProgressListener = downloadProgressListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response proceed = chain.proceed(chain.request());
        return proceed.newBuilder().body(new DownloadProgressResponseBody(proceed.body(), downloadProgressListener)).build();
    }
}
