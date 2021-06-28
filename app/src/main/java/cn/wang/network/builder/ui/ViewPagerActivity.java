package cn.wang.network.builder.ui;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.LifecycleObserver;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import cn.wang.network.R;
import cn.wang.network.builder.ui.mvp.presenter.BaseMvpPresenter;

public class ViewPagerActivity extends BaseActivity {


    private BaseMvpPresenter baseMvpPresenter;

    public static void start(Context context) {
        Intent intent = new Intent(context, ViewPagerActivity.class);
        context.startActivity(intent);
    }

    private ViewPager mViewPager;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_viee_pager;
    }

    @Override
    protected void init() {
        mViewPager = findViewById(R.id.viewPager);
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
        });
    }

}
