package cn.wang.network.builder.api;


import cn.wang.network.builder.bean.BaseResultBean;
import cn.wang.network.builder.bean.IpInfoBean;
import cn.wang.network.builder.bean.RequestIpBean;
import cn.wenet.networkcomponent.core.WeNetResult;
import retrofit2.http.GET;
import retrofit2.http.Headers;


/**
 * Created by WANG on 17/11/23.
 */

public interface ApiWeather {

    @GET("ipJson.jsp")
    WeNetResult<IpInfoBean> getIpInfo();

    @GET("pc/getIp")
    WeNetResult<BaseResultBean<RequestIpBean>> getRequestIp();

}
