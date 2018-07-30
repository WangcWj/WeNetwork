package cn.wang.network.builder.net.request;

import java.util.Map;

import cn.wang.network.builder.net.base.BaseResultBean;
import cn.wang.network.builder.net.exception.NetException;
import io.reactivex.Observable;

/**
 * Created by WANG on 2018/7/19.
 */

public interface NetCallBack<T,A> {

    Observable<BaseResultBean<T>> getMethod(A api , Map<String,Object> params);

    void onSuccess(T t);

    void onError(NetException e);
}
