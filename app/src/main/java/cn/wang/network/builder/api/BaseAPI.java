package cn.wang.network.builder.api;

import cn.wenet.networkcomponent.core.Control;

/**
 * Created by WANG on 2018/7/31.
 */

public class BaseAPI {
    public final static String BASE_URL ="https://www.apiopen.top/";

    //https://api.apiopen.top/getJoke?page=1&count=2&type=video

    public final static String BASE_SINGING_URL ="https://api.apiopen.top/";
    public final static String SINGING_URL_FLAG =Control.GLOBAL_HEADER+":singing";
}
