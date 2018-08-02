package cn.example.wang.networkcomponent.request;

import java.util.Map;

import cn.example.wang.networkcomponent.base.BaseResultBean;
import cn.example.wang.networkcomponent.exception.NetException;
import io.reactivex.Observable;

/**
 * Created by WANG on 2018/7/19.
 */

public interface NetCallBack<T,A> {

    Observable<BaseResultBean<T>> getMethod(A api, Map<String, Object> params);

    void onSuccess(T t);

    void onError(NetException e);

}
