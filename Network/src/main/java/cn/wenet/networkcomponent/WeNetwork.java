package cn.wenet.networkcomponent;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Map;

import cn.wenet.networkcomponent.base.NetBaseParam;
import cn.wenet.networkcomponent.base.NetLifecycleControl;
import cn.wenet.networkcomponent.control.Control;
import cn.wenet.networkcomponent.okhttp.intercepter.BaseInterceptor;
import cn.wenet.networkcomponent.request.NetRequest;
import okhttp3.HttpUrl;

/**
 * @author WANG
 */
public class WeNetwork {

    private WeNetwork() {

    }

    public static WeNetwork init(Context context) {
        Control instance = Control.getInstance();
        if (!instance.isHaveInit()) {
            instance.init(context);
        }
        return new WeNetwork();
    }

    public WeNetwork addBaseInterceptor(@NonNull BaseInterceptor interceptor) {
        Control.getInstance().addBaseInterceptor(interceptor);
        return this;
    }

    public WeNetwork successCode(int code) {
        NetBaseParam.SUCCESS_CODE = code;
        return this;
    }

    public WeNetwork addBaseUrl(String flag,String url) {
        Control.getInstance().addBaseUrl(flag,url);
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

    public static <T>T getApiServiceInastance(Class<T> clz){
        return Control.getInstance().getApiService(clz);
    }

}
