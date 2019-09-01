package cn.wang.network.builder.ui;

import android.arch.lifecycle.LifecycleObserver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import cn.wang.network.R;
import cn.wang.network.builder.api.ApiSong;
import cn.wang.network.builder.bean.SongBean;
import cn.wang.network.builder.ui.mvp.presenter.BaseMvpPresenter;
import cn.wenet.networkcomponent.WeNetwork;
import cn.wenet.networkcomponent.exception.NetException;
import cn.wenet.networkcomponent.request.WeNetworkCallBack;

public class ViewPagerActivity extends BaseActivity {


    private BaseMvpPresenter baseMvpPresenter;

    public static void start(Context context){
        Intent intent = new Intent(context,ViewPagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_viee_pager;
    }

    @Override
    protected void init() {
       /* mViewPager = findViewById(R.id.viewPager);
        final FirstFragment firstFragment = FirstFragment.newInstance();
        SecondFragment secondFragment = SecondFragment.newInstance();
        ThreeFragment threeFragment = ThreeFragment.newInstance();
        FourFragment fourFragment = FourFragment.newInstance();
        fragmentList.add(firstFragment);
        fragmentList.add(secondFragment);
        fragmentList.add(threeFragment);
        fragmentList.add(fourFragment);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });*/

        //getJoke?page=1&count=2&type=video
        WeNetwork.request(baseMvpPresenter)
                .addParams("page", "1")
                .addParams("count", "2")
                .addParams("type", "video")
                .apiMethod(WeNetwork.getApiService(ApiSong.class).getPoetry(WeNetwork.getParams()))
                .execute(new WeNetworkCallBack<SongBean>() {
                    @Override
                    public void onSuccess(SongBean bean) {

                    }

                    @Override
                    public void onError(NetException e) {

                    }
                });
    }

    @Override
    public LifecycleObserver getLifecycleObserver() {
        baseMvpPresenter = new BaseMvpPresenter();
        return baseMvpPresenter;
    }
}
