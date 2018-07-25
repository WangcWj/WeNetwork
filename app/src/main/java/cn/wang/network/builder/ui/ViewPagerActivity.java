package cn.wang.network.builder.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import cn.wang.network.R;

public class ViewPagerActivity extends BaseActivity {

    private ViewPager mViewPager;
    private List<BaseFragment> fragmentList = new ArrayList<>();

    public static void start(Context context){
        Intent intent = new Intent(context,ViewPagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_viee_pager;
    }

    @Override
    protected void initView() {
       mViewPager = findViewById(R.id.viewPager);
    }

    @Override
    protected void initData() {
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

    @Override
    protected void initListener() {

    }
}
