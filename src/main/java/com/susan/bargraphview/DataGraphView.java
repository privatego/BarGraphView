package com.susan.bargraphview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * Created by Blessed on 2016/11/14.
 */

public class DataGraphView extends RelativeLayout {

    private ViewGroup mRoot;

    public DataGraphView(ViewGroup root, Context context) {
        this(context);
        this.mRoot = root;
    }


    public DataGraphView(Context context) {
        this(context, null);
    }

    public DataGraphView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DataGraphView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //init(context);
    }

    public void show(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_graph, null, true);
        mRoot.addView(view);


    }
}
