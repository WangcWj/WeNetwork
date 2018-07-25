package cn.wang.network.builder.net.request;


import java.util.HashMap;
import java.util.Map;

import cn.wang.network.builder.api.ApiService;
import cn.wang.network.builder.net.NetControl;
import cn.wang.network.builder.net.base.NetAddDestroyDisposable;

/**
 * Created by WANG on 2018/7/19.
 */

public class BaseRequest {

    protected NetControl netControl;

    protected NetAddDestroyDisposable mDestroyDisposable;

    protected Map<String, Object> mParams = new HashMap<>();

    protected Class mClass = ApiService.class;

    public BaseRequest(NetControl netControl, NetAddDestroyDisposable mDestroyDisposable) {
        this.netControl = netControl;
        this.mDestroyDisposable = mDestroyDisposable;
    }

    public <T> void setApiServer(Class<T> tClass) {
        mClass = tClass;
    }

}
