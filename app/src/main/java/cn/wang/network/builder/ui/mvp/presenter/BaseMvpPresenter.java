package cn.wang.network.builder.ui.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import java.util.List;

import cn.wang.network.builder.bean.SongBean;
import cn.wang.network.builder.bean.WeatherBean;
import cn.wang.network.builder.ui.mvp.model.MainModel;
import cn.wang.network.builder.ui.mvp.view.BaseMvpView;
import cn.wenet.networkcomponent.base.NetLifecycleControl;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/8/28
 */
public class BaseMvpPresenter implements NetLifecycleControl, LifecycleObserver, MainPresenterApi {

    protected CompositeDisposable mCompositeDisposable;

    private BaseMvpView mView;

    private MainModel mainModel;

    public BaseMvpPresenter() {
        mainModel = new MainModel(this, this);
        mCompositeDisposable = new CompositeDisposable();
    }

    public void setView(BaseMvpView mView) {
        this.mView = mView;
    }

    public void getData() {
        mainModel.getCityWeather("杭州");
    }

    public void getSerachData(){
        mainModel.getSearchData();
    }


    @Override
    public void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.e("WANG", "PageLifecycleObserver.onStop.");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.e("WANG", "PageLifecycleObserver.onDestroy.");
        if(null != mCompositeDisposable && !mCompositeDisposable.isDisposed()){
            mCompositeDisposable.dispose();
        }
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
}
