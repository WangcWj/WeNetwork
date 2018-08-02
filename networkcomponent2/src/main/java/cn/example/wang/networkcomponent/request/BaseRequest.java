package cn.example.wang.networkcomponent.request;


import java.util.HashMap;
import java.util.Map;

import cn.example.wang.networkcomponent.NetControl;
import cn.example.wang.networkcomponent.base.NetAddDestroyDisposable;


/**
 * Created by WANG on 2018/7/19.
 */

public class BaseRequest {

    protected NetControl netControl;

    protected NetAddDestroyDisposable mDestroyDisposable;

    protected Map<String, Object> mParams = new HashMap<>();

    protected Class mClass ;

    public BaseRequest(NetControl netControl, NetAddDestroyDisposable mDestroyDisposable) {
        this.netControl = netControl;
        this.mDestroyDisposable = mDestroyDisposable;
        mClass = NetControl.getInstance().getApiServerClass();
    }

    public <T> void setApiServer(Class<T> tClass) {
        mClass = tClass;
    }

}
