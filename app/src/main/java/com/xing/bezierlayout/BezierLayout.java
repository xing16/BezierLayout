package com.xing.bezierlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;

public class BezierLayout extends LinearLayout {

    private int bottomPadding = 60;

    private static final String TAG = "BezierLayout";

    private Path path;

    private Paint paint;

    private int mWidth;

    private int mHeight;

    private PointF startPoint;

    private PointF endPoint;

    public BezierLayout(Context context) {
        super(context);
        init();
    }

    public BezierLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        Log.d(TAG, ": mwidth = " + mWidth);

        startPoint = new PointF(0, mHeight - dp2Px(bottomPadding));
        endPoint = new PointF(mWidth, mHeight - dp2Px(bottomPadding));

        path.moveTo(0, mHeight - dp2Px(bottomPadding));
        path.lineTo(0, mHeight);
        path.lineTo(mWidth, mHeight);
        path.lineTo(mWidth, mHeight - dp2Px(bottomPadding));
        // 贝塞尔曲线
        path.quadTo(mWidth / 2f, mHeight, 0, mHeight - dp2Px(bottomPadding));
        path.close();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);


    }

    private int dp2Px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }


}