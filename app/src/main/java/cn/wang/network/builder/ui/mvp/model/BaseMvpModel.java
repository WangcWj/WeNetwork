package cn.wang.network.builder.ui.mvp.model;
import cn.wenet.networkcomponent.WeNetwork;
import cn.wenet.networkcomponent.base.NetLifecycleControl;
import cn.wenet.networkcomponent.request.NetRequest;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/8/28
 */
public class BaseMvpModel {

    protected NetLifecycleControl lifecycleControl;

    public BaseMvpModel(NetLifecycleControl lifecycleControl) {
        this.lifecycleControl = lifecycleControl;
    }

    protected NetRequest getRequest(){
        return WeNetwork.request(lifecycleControl);
    }


}
