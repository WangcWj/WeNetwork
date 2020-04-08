package cn.wang.network.builder.api;

import cn.wenet.networkcomponent.core.Control;

/**
 * Created by WANG on 2018/7/31.
 */

public class BaseAPI {
    public final static String BASE_URL ="https://www.apiopen.top/";
    public final static String WEATHER_BASE_URL ="http://apis.juhe.cn/";


    //https://api.apiopen.top/getJoke?page=1&count=2&type=video
    public final static String WEATHER_URL_FLAG =Control.GLOBAL_HEADER+":weather";
}
