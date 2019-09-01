package cn.wang.network.builder.ui.mvp.view;

import java.util.List;

import cn.wang.network.builder.bean.SongBean;
import cn.wang.network.builder.bean.WeatherBean;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/8/28
 */
public interface BaseMvpView {

    void setData(WeatherBean bean);

    void setSearchData(SongBean bean);
}
