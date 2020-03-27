package cn.wang.network.builder.ui.mvp.model;
import android.content.Context;

import cn.wenet.networkcomponent.core.WeNetwork;
import cn.wenet.networkcomponent.base.NetLifecycleControl;
import cn.wenet.networkcomponent.request.NetRequest;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/8/28
 */
public class BaseMvpModel {

    protected Context mContext;

    public BaseMvpModel(Context lifecycleControl) {
        this.mContext = lifecycleControl;
    }



}
