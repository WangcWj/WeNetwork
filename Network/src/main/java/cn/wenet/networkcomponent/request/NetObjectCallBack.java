package cn.wenet.networkcomponent.request;

import java.util.Map;
import io.reactivex.Observable;

/**
 * @author WANG
 * @date 2018/7/19
 */

public interface NetObjectCallBack<T> extends BaseCallBack<T> {

    /**
     * 返回Api的接口信息.
     * @param request
     * @param params
     * @return
     */
    Observable<T> getMethod(NetRequest request, Map<String, Object> params);

}
