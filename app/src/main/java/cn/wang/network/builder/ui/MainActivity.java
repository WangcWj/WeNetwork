package cn.wang.network.builder.ui;

import android.arch.lifecycle.LifecycleObserver;
import android.view.View;
import android.widget.TextView;

import cn.wang.network.builder.ui.mvp.presenter.BaseMvpPresenter;
import cn.wang.network.builder.ui.mvp.view.BaseMvpView;
import cn.wang.network.R;
import cn.wang.network.builder.bean.WeatherBean;

public class MainActivity extends BaseActivity implements BaseMvpView {

    private TextView jsonText;

    private BaseMvpPresenter mPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        jsonText = findViewById(R.id.jsonText);
        jsonText.setText("第一个的哈哈看理解");

        findViewById(R.id.uselog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerActivity.start(thisActivity());
            }
        });
    }

    @Override
    public LifecycleObserver getLifecycleObserver() {
        mPresenter = new BaseMvpPresenter();
        mPresenter.setView(this);
        return mPresenter;
    }

    @Override
    public void setData(WeatherBean bean) {

    }
}
