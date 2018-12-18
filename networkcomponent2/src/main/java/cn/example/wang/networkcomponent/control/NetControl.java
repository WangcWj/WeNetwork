package cn.example.wang.networkcomponent.control;

import android.content.Context;

import cn.example.wang.networkcomponent.base.NetLifecycleControl;
import cn.example.wang.networkcomponent.request.NetRequest;

/**
 ** 整个网络请求的总线 单例模式
 * 1.改变BaseUrl
 * 2.增加拦截器
 * 3.订阅Observable
 * 4.改变ApiServer
 * 5.控制日志打印
 * @author WANG
 * @date 2018/5/3
 */

public class NetControl {

    public static NetControl init(String baseUrl,Context context){
        Control.getInstance().init(baseUrl,context);
        return new NetControl();
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

    /**
     * 开始网络请求
     *
     * @param tag
     * @return
     */
    public static NetRequest requestJson(NetLifecycleControl tag) {
        return Control.getInstance().requestJson(tag);
    }
}
