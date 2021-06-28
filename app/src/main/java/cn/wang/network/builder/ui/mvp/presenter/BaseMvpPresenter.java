package cn.wang.network.builder.ui.mvp.presenter;

import android.content.Context;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import cn.wang.network.builder.bean.SongBean;
import cn.wang.network.builder.bean.WeatherBean;
import cn.wang.network.builder.ui.mvp.model.MainModel;
import cn.wang.network.builder.ui.mvp.view.BaseMvpView;
import cn.wenet.networkcomponent.life.ComponentLifeCircle;
import cn.wenet.networkcomponent.life.WeNetLifecycleControl;
import cn.wenet.networkcomponent.request.NetRequestImpl;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/8/28
 */
public class BaseMvpPresenter implements LifecycleObserver, MainPresenterApi {

    protected CompositeDisposable mCompositeDisposable;

    private BaseMvpView mView;

    private MainModel mainModel;

    public BaseMvpPresenter(Context context) {
        mainModel = new MainModel(context, this);
        mCompositeDisposable = new CompositeDisposable();
    }

    public void setView(BaseMvpView mView) {
        this.mView = mView;
    }

    public void getData() {
        mainModel.getIsp();
        String str = null;
       /* if(str.equals("123")){

        }*/
    }

    public void getSerachData(){

    }



    @Override
    public void weatherData(WeatherBean bean, boolean success) {
        if(success) {
            mView.setData(bean);
        }
    }

    @Override
    public void setSearchData(SongBean beans, boolean success) {
        if(success) {
            mView.setSearchData(beans);
        }
    }

    @Override
    public void getDataByPost() {
        mainModel.getDataByPost();
    }

    @Override
    public void getDataByBody() {
        mainModel.getDataBybody();
    }

    @Override
    public void getIp() {
        mainModel.getIP();
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        if(null != mCompositeDisposable && !mCompositeDisposable.isDisposed()){
            mCompositeDisposable.dispose();
        }
        mCompositeDisposable = null;
        mView = null;
        mainModel = null;
    }


}
