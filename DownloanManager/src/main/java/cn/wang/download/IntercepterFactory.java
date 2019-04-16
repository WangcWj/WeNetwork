package cn.wang.download;

import okhttp3.Interceptor;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/4/15
 */
public class IntercepterFactory {

    public static Interceptor createProgressIntercepter(ProgressListener progressListener) {
        return new ProgressInterceptor(progressListener);
    }


}
