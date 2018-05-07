package com.gionee.practices.recycleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.gionee.practices.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewActivity extends Activity {

    @BindView(R.id.recycle_view)
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);

    }
}
