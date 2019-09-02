package cn.wenet.networkcomponent.request;

import cn.wenet.networkcomponent.exception.NetException;

/**
 * Created to :
 *
 * @author WANG
 * @date 2018/12/18
 */

public interface WeNetErrorCallBack extends WeNetBaseCallBack{

    void onError(NetException e);
}
