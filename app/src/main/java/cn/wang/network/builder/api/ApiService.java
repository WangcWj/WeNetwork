package cn.wang.network.builder.api;


import java.util.Map;

import cn.wang.network.builder.bean.JokeBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by WANG on 17/11/23.
 */

public interface ApiService {
    //http://v5.pc.duomi.com/search-ajaxsearch-searchall?kw=%E8%96%9B%E4%B9%8B%E8%B0%A6&pi=1&pz=20

    @GET("meituApi")
    Observable<JokeBean> getSingleData(@QueryMap Map<String,Object> params);



}
