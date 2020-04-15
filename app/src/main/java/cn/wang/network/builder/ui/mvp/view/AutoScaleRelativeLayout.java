package cn.wang.network.builder.ui.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/4/15
 */
public class AutoScaleRelativeLayout extends RelativeLayout {

    public AutoScaleRelativeLayout(Context context) {
        this(context, null);
    }

    public AutoScaleRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoScaleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private final int DENSITY = 3;

    private float density;
    private boolean mDensityIsStandard;
    private List<View> mMatchParentViews;

    private void init(Context context) {
        density = context.getResources().getDisplayMetrics().density;
        Log.e("cc.wang","AutoScaleRelativeLayout.init."+density);
        mDensityIsStandard = density != DENSITY;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (null != layoutParams && layoutParams.width == ViewGroup.LayoutParams.MATCH_PARENT) {
                if (null == mMatchParentViews) {
                    mMatchParentViews = new ArrayList<>();
                }
                mMatchParentViews.add(view);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (mDensityIsStandard) {
            int newHeight = (int) (heightSize / density * DENSITY);
            int newWidth = (int) (widthSize / density * DENSITY);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(newHeight,MeasureSpec.EXACTLY);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(newWidth,MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
