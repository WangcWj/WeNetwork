package cn.wenet.networkcomponent;

import android.content.Context;
import android.support.annotation.NonNull;

import cn.wenet.networkcomponent.base.NetBaseParam;
import cn.wenet.networkcomponent.base.NetLifecycleControl;
import cn.wenet.networkcomponent.control.Control;
import cn.wenet.networkcomponent.intercepter.BaseInterceptor;
import cn.wenet.networkcomponent.request.NetRequest;

public class WeNetwork {

    public static WeNetwork init(String baseUrl, Context context){
        Control.getInstance().init(baseUrl,context);
        return new WeNetwork();
    }

    public WeNetwork addBaseInterceptor(@NonNull BaseInterceptor interceptor){
        Control.getInstance().addBaseInterceptor(interceptor);
        return this;
    }

    public WeNetwork successCode(int code){
        NetBaseParam.SUCCESS_CODE = code;
        return this;
    }

    /**
     * 开始网络请求
     *
     * @param tag
     * @return
     */
    public static NetRequest request(NetLifecycleControl tag) {
        return Control.getInstance().request(tag);
    }
}
