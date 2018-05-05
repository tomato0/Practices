package com.gionee.practices.animation;

import android.app.Activity;
import android.os.Bundle;

import com.gionee.practices.R;

public class AnimationActivity extends Activity {

    private PointView mPointView;
    private PointViewAnimManager mPointViewAnimManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        mPointView = (PointView) findViewById(R.id.point_view);
        mPointViewAnimManager = new PointViewAnimManager();
        mPointViewAnimManager.bindView(mPointView);
            mPointView.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        mPointViewAnimManager.startMoveAnim();
                        mPointViewAnimManager.startColorAnim();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    @Override
    protected void onDestroy() {
        mPointViewAnimManager.unBindView();
        super.onDestroy();
    }
}
