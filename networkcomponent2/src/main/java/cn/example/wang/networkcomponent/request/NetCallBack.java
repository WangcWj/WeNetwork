package cn.example.wang.networkcomponent.request;

import java.util.Map;

import cn.example.wang.networkcomponent.base.BaseResultBean;
import cn.example.wang.networkcomponent.exception.NetException;
import io.reactivex.Observable;

/**
 *
 * @author WANG
 * @date 2018/7/19
 */

public interface NetCallBack<T> extends BaseCallBack<T> {
    Observable<BaseResultBean<T>> getMethod(NetRequest request, Map<String, Object> params);
}
