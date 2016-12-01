package com.susan.bargraphview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.sijifengling.basemodule.BaseActivity;

/**
 * Created by Blessed on 2016/11/14.
 */

public class BarGraphActivity extends BaseActivity {

    private RelativeLayout mRlGraphContainer;
    private DataGraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barview);
        setLeftBtnPress();

        mRlGraphContainer = (RelativeLayout)findViewById(R.id.rl_graph_container);



        graphView = new DataGraphView(mRlGraphContainer, this);
        graphView.show(this);
        graphView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onResume() {
        super.onResume();

        int width = mRlGraphContainer.getWidth();
        int height = mRlGraphContainer.getHeight();
        Log.e("BarGraphActivity", " width = " + width + ", height = " + height);
    }
}
