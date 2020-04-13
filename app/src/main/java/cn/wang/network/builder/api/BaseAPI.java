package cn.wang.network.builder.api;

import cn.wenet.networkcomponent.core.Control;

/**
 * Created by WANG on 2018/7/31.
 */

public class BaseAPI {
    public final static String BASE_URL = "https://******/";
    public final static String BASE_URL_FLAG = Control.DEFAULT_BASE_URL_FLAG;


    public final static String WEATHER_BASE_URL = "http://******/";
    public final static String WEATHER_URL_FLAG = Control.GLOBAL_HEADER + ":weather";


    public final static String IP_URL_FLAG = Control.GLOBAL_HEADER + ":ip";
    public final static String IP_BASE_URL = "http://********/";
}
