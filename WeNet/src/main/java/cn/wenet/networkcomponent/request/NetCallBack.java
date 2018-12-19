package cn.wenet.networkcomponent.request;

import java.util.Map;

import cn.wenet.networkcomponent.base.BaseResultBean;
import io.reactivex.Observable;

/**
 *
 * @author WANG
 * @date 2018/7/19
 */

public interface NetCallBack<T> extends BaseCallBack<T> {
    Observable<BaseResultBean<T>> getMethod(NetRequest request, Map<String, Object> params);
}
