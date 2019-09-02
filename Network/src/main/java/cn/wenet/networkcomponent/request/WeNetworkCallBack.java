package cn.wenet.networkcomponent.request;


import cn.wenet.networkcomponent.exception.NetException;

/**
 * Created to :
 *
 * @author WANG
 * @date 2018/12/18
 */

public interface WeNetworkCallBack<T> {

    void onSuccess(T t);

    void onError(NetException e);

}
