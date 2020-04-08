package cn.wang.network.builder.ui.mvp.model;
import android.content.Context;

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
