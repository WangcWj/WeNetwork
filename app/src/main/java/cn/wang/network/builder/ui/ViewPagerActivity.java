package cn.wang.network.builder.ui;

import androidx.lifecycle.LifecycleObserver;
import android.content.Context;
import android.content.Intent;

import cn.wang.network.R;
import cn.wang.network.builder.ui.mvp.presenter.BaseMvpPresenter;
import cn.wenet.networkcomponent.core.WeNetwork;

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

    }

    @Override
    public LifecycleObserver getLifecycleObserver() {
        baseMvpPresenter = new BaseMvpPresenter(this);
        return baseMvpPresenter;
    }
}
