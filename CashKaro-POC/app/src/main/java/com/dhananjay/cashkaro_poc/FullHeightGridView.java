package com.dhananjay.cashkaro_poc;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridView;

/**
 * Created by Dhananjay on 03-03-2017.
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
  /*  private Bitmap background;

    private int mShelfWidth;
    private int mShelfHeight;


    public void setBackground(Bitmap background) {
        this.background = background;

        mShelfWidth = background.getWidth();
        mShelfHeight = background.getHeight();
    }
*/

    /*@Override
    protected void dispatchDraw(Canvas canvas) {
        final int count = getChildCount();
        final int top = count > 0 ? getChildAt(0).getTop() : 0;
        final int shelfWidth = mShelfWidth;
        final int shelfHeight = mShelfHeight;
        final int width = getWidth();
        final int height = getHeight();
        final Bitmap background = this.background;

        for (int x = 0; x < width; x += shelfWidth) {
            for (int y = top; y < height; y += shelfHeight) {
                canvas.drawBitmap(background, x, y, null);
            }

            //This draws the top pixels of the shelf above the current one

            Rect source = new Rect(0, mShelfHeight - top, mShelfWidth, mShelfHeight);
            Rect dest = new Rect(x, 0, x + mShelfWidth, top);

            canvas.drawBitmap(background, source, dest, null);
        }


        super.dispatchDraw(canvas);
    }*/


}
