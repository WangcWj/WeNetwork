package cn.wenet.networkcomponent.request;

import cn.wenet.networkcomponent.exception.NetException;

/**
 * Created to :
 *
 * @author WANG
 * @date 2018/12/18
 */

public interface WeNetworkCallBack<T> extends WeNetErrorCallBack {

    void onSuccess(T t);

}
