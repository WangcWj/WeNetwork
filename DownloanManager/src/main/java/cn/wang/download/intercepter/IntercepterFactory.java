package cn.wang.download.intercepter;

import cn.wang.download.response.DownloadProgressListener;
import okhttp3.Interceptor;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/4/15
 */
public class IntercepterFactory {

    public static Interceptor createProgressIntercepter(DownloadProgressListener downloadProgressListener) {
        return new DownloadProgressInterceptor(downloadProgressListener);
    }


}
