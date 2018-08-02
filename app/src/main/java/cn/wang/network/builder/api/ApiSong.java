package cn.wang.network.builder.api;


import cn.wang.network.builder.bean.DuanZiBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

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
    Observable<DuanZiBean> getCategoryData(@Path("category") Object category, @Path("count") Object count, @Path("page") Object page);



}
