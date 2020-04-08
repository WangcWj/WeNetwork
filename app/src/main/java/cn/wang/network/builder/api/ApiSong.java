package cn.wang.network.builder.api;



import cn.wang.network.builder.bean.SongBean;
import cn.wenet.networkcomponent.core.WeNetResult;
import retrofit2.http.GET;


/**
 * Created by WANG on 17/11/23.
 */

public interface ApiSong {
    //getJoke?page=1&count=2&type=video

    @GET("getJoke")
    WeNetResult<SongBean> getPoetry();
}
