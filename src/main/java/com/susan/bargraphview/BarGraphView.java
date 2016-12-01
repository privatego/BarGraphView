package com.susan.bargraphview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;


/**
 * Created by Blessed on 2016/11/14.
 */

public class BarGraphView extends View {
    private static final String TAG = BarGraphView.class.getSimpleName();

    private static final int X_LINE_COUNT = 6;
    private static final int Y_Data_COUNT = 24;

    private int mWidth;//宽度
    private int mHeight;//高度
    private int mBarWidth;//柱宽度
    private int mBarMargin;//柱间距
    private int mBarTxtColor;//文字颜色

    private int xLineMargin;
    private int yDataWidth;

    private String mTitleTxt;
    private int mTitleTxtSize = 40;

    private Paint mTxtPain;
    private Paint mLinePaint;
    private Paint mDataPaint;
    private LinearGradient gradient;

    //
    private int maxData =  150;
    private double[] datas = new double[]{1, 10, 100, 120, 80, 80, 30, 3, 100, 120, 80, 80, 5, 10, 100, 120, 80, 80, 30, 10, 100, 120, 80, 80};

    public BarGraphView(Context context) {
        this(context, null);
    }

    public BarGraphView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarGraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义的属性
        TypedArray types = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BarGraphView, defStyleAttr, 0);
        int n = types.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = types.getIndex(i);
            Log.e(TAG, " attr = " + attr);
            if (attr == R.styleable.BarGraphView_bar_width) {
                mBarWidth = types.getDimensionPixelSize(i, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            } else if (attr == R.styleable.BarGraphView_bar_margin) {
                mBarMargin = types.getDimensionPixelSize(i, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            } else if (attr == R.styleable.BarGraphView_bar_txt_color) {
                mBarTxtColor = types.getColor(i, Color.WHITE);
            } else {
            }
        }
        types.recycle();
    }

    public void setTitleTxt(String txt){
        this.mTitleTxt = txt;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTxtPain = new Paint();
        mTxtPain.setColor(mBarTxtColor);
        mTxtPain.setTextSize(mTitleTxtSize);

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.WHITE);
        mLinePaint.setStrokeWidth(1);
        mLinePaint.setAntiAlias(true);

        mWidth = getWidth();
        mHeight = getHeight();

        //计算坐标横轴线的坐标
        xLineMargin = mHeight  / (X_LINE_COUNT - 1);
        yDataWidth = mWidth / Y_Data_COUNT;

        mDataPaint = new Paint();
//        mDataPaint.setColor(Color.BLUE);
        mDataPaint.setStyle(Paint.Style.FILL);
        mDataPaint.setStrokeWidth(yDataWidth - yDataWidth/3);
        gradient = new LinearGradient(0, 0, 0, mHeight,
                new int[]{getResources().getColor(R.color.tob_data_top),
                        getResources().getColor(R.color.tob_data_middle),
                        getResources().getColor(R.color.tob_data_bottom)},new float[]{0 , 0.5f, 1.0f},
                Shader.TileMode.MIRROR);
        mDataPaint.setShader(gradient);

        Log.e(TAG, " mWidth = " + mWidth + ", mHeight = " + mHeight + ", xLineMargin = " + xLineMargin + ", yDataWidth = " + yDataWidth);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawCoordinate(canvas);//画坐标
        drawBar(canvas);
    }

    //绘制柱型
    private void drawBar(Canvas canvas) {
        for (int i=0; i<datas.length; i++){
            //设置每个柱形的渐变样式
            gradient = new LinearGradient(
                    yDataWidth * (i + 1) - 15,
                    (float) (((maxData - datas[i]) / maxData) * mHeight),
                    yDataWidth * (i + 1) - 15,
                    mHeight,
                    new int[]{getResources().getColor(R.color.tob_data_top),
                            getResources().getColor(R.color.tob_data_middle),
                            getResources().getColor(R.color.tob_data_bottom)},
                            new float[]{0,  0.7f, 1.0f},
                    Shader.TileMode.MIRROR);
            mDataPaint.setShader(gradient);//将样式设置给画笔
            RectF rectF = new RectF(
                    yDataWidth * (i + 1) - 15, //left
                    ((float) (((maxData - datas[i]) / maxData)) * mHeight), //top
                    yDataWidth * (i + 1) - 15 + yDataWidth * 1 / 2, //right
                    mHeight + yDataWidth * 1 / 3 //bottom
            );
            //绘制圆角矩形
            canvas.drawRoundRect(rectF, yDataWidth * 1 / 3, yDataWidth * 1 / 3, mDataPaint);
        }
    }

    private void drawCoordinate(Canvas canvas) {
        drawCoordinateLeft(canvas);
        drawCoordinateRightLine(canvas);
        drawCoordinateRightBottom(canvas);
    }

    //右侧的底部
    private void drawCoordinateRightBottom(Canvas canvas) {

    }

    //右侧的线条
    private void drawCoordinateRightLine(Canvas canvas) {
        for (int i=0; i<(X_LINE_COUNT); i++) {
            canvas.drawLine(0, xLineMargin * i, mWidth, xLineMargin * i, mLinePaint);
        }
    }

    //坐标左侧
    private void drawCoordinateLeft(Canvas canvas) {

    }

}
