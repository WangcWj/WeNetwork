package cn.example.wang.networkcomponent.rxjava;

import android.accounts.NetworkErrorException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import cn.example.wang.networkcomponent.base.BaseParam;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by WANG on 2018/7/18.
 * 错误重连机制
 * 可以自定义重连的次数和下次请求发出之间的时间间隔
 */

public class NetRetryWhen implements Function<Observable<Throwable>, ObservableSource<?>> {

    private int mRetryCount = BaseParam.RETRYWHEN_COUNT;
    private int mRetryCurrent = 0;
    private long mRetryWhenTime = BaseParam.RETRYWHEN_TIME;

    public NetRetryWhen(int mRetryCount,long mRetryWhenTime) {
        this.mRetryCount = mRetryCount;
        this.mRetryWhenTime = mRetryWhenTime;
    }
    @Override
    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {
                if ((throwable instanceof NetworkErrorException
                        || throwable instanceof ConnectException
                        || throwable instanceof SocketTimeoutException
                        || throwable instanceof TimeoutException) && mRetryCurrent < mRetryCount) {
                    mRetryCurrent++;
                    return Observable.timer(mRetryWhenTime, TimeUnit.MILLISECONDS);
                }
                return Observable.error(throwable);
            }
        });
    }
}
