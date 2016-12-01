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

public class BarXView extends View {

    private double mMaxData = 150;
//    private int mCount = 6;
    private int[] mDatas = new int[]{11,12,13,14,15,16,17,18,19,20,21,22,23,24,1,2,3,4,5,6,7,8,9,10};

    private int mBarXTxtSize;
    private int mBarXTxtColor;

    private int mWidth;
    private int mHeight;
    private int mMarginWidth;

    private Paint mTxtPaint;
    private Paint mDotPaint;

    public BarXView(Context context, int[] datas, int count) {
        this(context);
//        this.mCount = count;
        mDatas = datas;
    }

    public BarXView(Context context) {
        this(context, null);
    }

    public BarXView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarXView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义的属性
        TypedArray types = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BarXView, defStyleAttr, 0);
        int n = types.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = types.getIndex(i);
            if (attr == R.styleable.BarXView_barx_txt_size) {
                mBarXTxtSize = types.getDimensionPixelSize(i, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            } else if (attr == R.styleable.BarXView_barx_txt_color) {
                mBarXTxtColor = types.getColor(i, Color.WHITE);
            } else {
            }
        }
        types.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mTxtPaint = new Paint();
        mTxtPaint.setColor(Color.WHITE);
        mTxtPaint.setTextSize(mBarXTxtSize);
        mTxtPaint.setAntiAlias(true);
        mTxtPaint.setTextAlign(Paint.Align.CENTER);

        mDotPaint = new Paint();
        mDotPaint.setColor(Color.WHITE);
        mDotPaint.setTextSize(mBarXTxtSize);
        mDotPaint.setAntiAlias(true);
        mDotPaint.setTextAlign(Paint.Align.CENTER);

        mWidth = getWidth();
        mHeight = getHeight();
        mMarginWidth = mWidth / mDatas.length;
    }

    String data = "";
    int r = 1;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0; i<mDatas.length; i++) {
            if (mDatas[i] % 4 != 0){
                data = "";
                r = 2;
            }else {
                data = mDatas[i] + ":00";
                r = 4;
            }
            //画上面的小圆点
            canvas.drawCircle(mMarginWidth * i + 28, 20,  r, mTxtPaint);
            //画下面的时间
            canvas.drawText(data, 0, data.length(), mMarginWidth * i + 28, 60 , mTxtPaint);
        }
    }
}
