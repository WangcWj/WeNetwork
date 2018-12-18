package cn.wang.network.builder.ui;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.example.wang.networkcomponent.base.NetLifecycleControl;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by WANG on 2018/7/23.
 * 1.不想要懒加载的话那请求网络可以放到 {@link BaseFragment#pageInitData()}.
 * <p>
 * 2.懒加载+只加载一次的话 {@link BaseFragment#pageLoadDataOnce()}.
 * 具体情况还是要看ViewPager缓存的个数{@link android.support.v4.view.ViewPager#setOffscreenPageLimit(int)}
 * 如果不设置缓存全部的话,那每次创建Fragment的时候都会调用一次.
 * <p>
 * 3.每次页面可见的时候都调用(不是onResume的效果这是切换到当前Fragment){@link BaseFragment#pageLoadDataOnVisible()}
 * 当设置缓存全部的话,该方法实用.如果没设置缓存的话,效果类似{@link BaseFragment#pageLoadDataOnce()}.
 * <p>
 * 4.{@link BaseFragment#pageOnResume()}对ViewPager的缓存个数没啥影响.
 */
public abstract class BaseFragment extends Fragment implements NetLifecycleControl {

    private View mRootView;

    protected Activity mActivity;

    protected Context mContext;

    private boolean mViewRealyCreate = false;

    private boolean mFirstLoad = false;

    private boolean mUserVisible = false;

    private boolean mPageStopOrPause = false;

    private CompositeDisposable mCompositeDisposable;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        mCompositeDisposable = new CompositeDisposable();
        initView();
        mFirstLoad = true;
        mViewRealyCreate = true;
        pageInitData();
        pageVisible();
        initListener();
        return mRootView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            visible();
        } else {
            hide();
        }
    }

    /**
     * 手动调用 show 和 hidden 的时候会调用
     * getFragmentManager().beginTransaction().hide(firstFragment)
     * .show(firstFragment);
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            hide();
        } else {
            visible();
        }
    }

    private void hide() {
        mUserVisible = false;
        pageVisible();
    }

    private void visible() {
        mUserVisible = true;
        pageVisible();
    }

    private void pageVisible() {
        if (mViewRealyCreate && mUserVisible) {
            pageLoadDataOnVisible();
            if (mFirstLoad) {
                mFirstLoad = false;
                pageLoadDataOnce();
            }
        }
    }

    @Override
    public void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    /**
     * 页面可见的时候调用 只会执行一次
     * 不过执行的次数跟ViewPage设置的缓存数有差异,的那个页面被回收的话 还是会走一次
     */
    protected void pageLoadDataOnce() {

    }


    /**
     * 当页面可见的时候就会调用
     */
    protected void pageLoadDataOnVisible() {

    }

    /**
     * 普通加载数据 再OnCreate里面
     */
    protected void pageInitData() {

    }

    /**
     * 每次当页面可见并且执行onResume的时候调用
     */
    protected void pageOnResume() {

    }

    public View findViewById(int layoutId) {
        View view = findView(layoutId);
        if (null == view) throw new NullPointerException(" 找不到对应  " + layoutId + "  的View");
        return view;
    }

    private View findView(@IdRes int id) {
        if (null == mRootView) throw new NullPointerException(this + "   mRootView Is Null !!!");
        View view = mRootView.findViewById(id);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        if (null != mCompositeDisposable && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mUserVisible && mViewRealyCreate) pageOnResume();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initListener();


}
