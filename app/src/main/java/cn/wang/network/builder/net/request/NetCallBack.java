package cn.wang.network.builder.net.request;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by WANG on 2018/7/19.
 */

public interface NetCallBack<T,A> {

    Observable<T> getMethod(A api , Map<String,Object> params);

    void onSuccess(T t);

    void onError(Throwable e);
}