package cn.wang.network.builder.api;


import java.util.Map;

import cn.wang.network.builder.bean.SongBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by WANG on 17/11/23.
 */

public interface ApiSong {
    //http://gank.io/api/search/query/listview/category/Android/count/10/page/1

    /**
     * 根据category获取Android、iOS等干货数据
     * @param category  类别
     * @param count     条目数目
     * @param page      页数
     */
    @GET("api/search/query/listview/category/{category}/count/{count}/page/{page}")
    Observable<String> getCategoryData(@Path("category") Object category, @Path("count") Object count, @Path("page") Object page);

    @GET("searchPoetry")
    Observable<SongBean> getPoetry(@QueryMap Map<String, Object> params);


}
