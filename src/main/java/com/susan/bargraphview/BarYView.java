package com.susan.bargraphview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


/**
 * Created by Blessed on 2016/11/15.
 */

public class BarYView extends View {

    private double mMaxData = 150;
    private int mCount = 6;
    private double[] datas;

    private int mBarYMarginLeft;
    private int mBarYMarginTop;
    private int mBarYTxtSize;
    private int mBarYTxtColor;

    private int mWidth;
    private int mHeight;
    private int mMarginHeight;


    private Paint mTxtPaint;

    public BarYView(Context context, double maxData, int count) {
        this(context);
        this.mMaxData = maxData;
        this.mCount = count;
        datas = new double[mCount];
    }

    public BarYView(Context context) {
        this(context, null);
    }

    public BarYView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarYView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义的属性
        TypedArray types = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BarYView, defStyleAttr, 0);
        int n = types.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = types.getIndex(i);
            if (attr == R.styleable.BarYView_bary_margin_left) {
                mBarYMarginLeft = types.getDimensionPixelSize(i, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            } else if (attr == R.styleable.BarYView_bary_margin_top) {
                mBarYMarginTop = types.getDimensionPixelSize(i, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            } else if (attr == R.styleable.BarYView_bary_txt_size) {
                mBarYTxtSize = types.getDimensionPixelSize(i, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            } else if (attr == R.styleable.BarYView_bary_txt_color) {
                mBarYTxtColor = types.getColor(i, Color.WHITE);
            } else {
            }
        }
        types.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        double data = 0;
        if (datas == null){
            datas = new double[mCount];
        }
        for (int i=1; i<mCount; i++){
            data = (i * 100 / (mCount-1)) * mMaxData / 100;
            datas[i] = data;
        }

        mTxtPaint = new Paint();
        mTxtPaint.setColor(mBarYTxtColor);
        mTxtPaint.setTextSize(mBarYTxtSize);
        mTxtPaint.setAntiAlias(true);

        mWidth = getWidth();
        mHeight = getHeight();
        mMarginHeight = mHeight / mCount - 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String data = "";
        for (int i=0; i<mCount; i++) {
            data = (int)datas[i] + "";
            canvas.drawText(data, 0, data.length(), mBarYMarginLeft, mMarginHeight * (mCount - i - 1) + mBarYMarginTop, mTxtPaint);
        }
    }
}
