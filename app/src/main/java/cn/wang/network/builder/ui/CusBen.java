package cn.wang.network.builder.ui;

import java.util.List;

import cn.wang.network.builder.bean.BaseResultBean;
import cn.wang.network.builder.bean.IpInfoBean;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/11/4
 */
public interface CusBen {

    BaseResultBean<List<IpInfoBean>> getData();

}
