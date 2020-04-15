package cn.wang.network.builder.ui.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/4/15
 */
public class CusText extends AppCompatTextView {
    public CusText(Context context) {
        super(context);
    }

    public CusText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CusText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("cc.wang","CusText.onMeasure.");
    }
}
