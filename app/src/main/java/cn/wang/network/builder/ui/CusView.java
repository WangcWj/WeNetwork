package cn.wang.network.builder.ui;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/4/10
 */
public class CusView extends View {

    Dialog dialog;

    public CusView(Context context) {
        super(context);
    }
    public CusView(Context context, Dialog dialog) {
        this(context);
        this.dialog = dialog;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(0,0);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        Log.e("cc.wang","CusView.onDetachedFromWindow.");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e("cc.wang","CusView.onAttachedToWindow.");
    }
}
