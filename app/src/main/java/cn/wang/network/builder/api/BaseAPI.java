package cn.wang.network.builder.api;

import cn.wenet.networkcomponent.core.Control;

/**
 * Created by WANG on 2018/7/31.
 */

public class BaseAPI {
    public static final String BASE_URL = "http://whois.pconline.com.cn/";
    public static final String BASE_GAODE_URL = "http://restapi.amap.com/";


    public static final String BASE_FLAG = Control.DEFAULT_BASE_URL_FLAG;
    public static final String GAODE_FLAG = Control.GLOBAL_HEADER + ":gaoDe";
}
