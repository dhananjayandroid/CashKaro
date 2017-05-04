package com.dhananjay.cashkaro_poc.ui.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridView;

/**
 * {@link GridView} class which always have full height
 *
 * @author Dhananjay Kumar
 */
public class FullHeightGridView extends GridView {

    public FullHeightGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullHeightGridView(Context context) {
        super(context);
    }

    public FullHeightGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        final int count = getChildCount();
        int columNum = getNumColumns();
        int num_of_rows = count / columNum;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int bottom = child.getBottom();
            int left = child.getLeft();
            int right = child.getRight();
            int top = child.getTop();
            Paint paint = new Paint();
            paint.setColor(0xffececec);
            Resources resources = getContext().getResources();
            DisplayMetrics metrics = resources.getDisplayMetrics();
            paint.setStrokeWidth(Math.round(1 * metrics.density));
            int offset = 2;
            try {
                if ((i + 1) / num_of_rows <= 1)
                    canvas.drawLine(left + offset, bottom, right - offset, bottom, paint);
                if ((i + 1) / columNum < 1 || (i + 1) % columNum != 0)
                    canvas.drawLine(right, top, right, bottom, paint);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.dispatchDraw(canvas);
    }

}
