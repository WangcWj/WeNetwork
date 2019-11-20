package cn.wenet.networkcomponent;

import android.content.Context;
import android.support.annotation.NonNull;


import cn.wenet.networkcomponent.base.NetBaseParam;
import cn.wenet.networkcomponent.base.NetLifecycleControl;
import cn.wenet.networkcomponent.control.Control;
import cn.wenet.networkcomponent.okhttp.intercepter.BaseInterceptor;
import cn.wenet.networkcomponent.request.NetRequest;

/**
 * @author WANG
 */
public class WeNetwork {

    private WeNetwork() {

    }

    /**
     * 请务必调用开启框架的初始化。
     *
     * @param context
     * @return
     */
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

    /**
     * 框架会默认一个Url为基础Url，该Url必须支持大部分的接口。个别不同域名的Url就需要在Url中添加特定的Header，
     * 该Key为{@link Control#GLOBAL_HEADER}，值必须区别于{@link Control#BASE_URL_HEADER}。
     *
     * @param flag 添加到Url中的Header值。组成类似："baseUrl-Header:BaseUrl";
     * @param url  新域名的Url。
     * @return
     */
    public WeNetwork addBaseUrl(String flag,String url) {
        Control.getInstance().addBaseUrl(flag,url);
        return this;
    }

    /**
     * 开始网络请求，每个网络请求都是从这里开始的。
     *
     * @param tag
     * @return
     */
    public static NetRequest request(NetLifecycleControl tag) {
        return Control.getInstance().request(tag);
    }

    /**
     * 获取ApiService。
     *
     * @param clz
     * @param <T>
     * @return
     */
    public static <T>T getApiServiceInstance(Class<T> clz){
        return Control.getInstance().getApiService(clz);
    }

    public static void destroy(){
        
    }

}
