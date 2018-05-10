package com.gionee.practices.recycleview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gionee.practices.R;
import com.gionee.practices.ad.AdEntity;
import com.gionee.practices.ad.IAdNetCallBack;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewActivity extends Activity implements IAdNetCallBack {
    private static final String TAG = "RecycleViewActivity";

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;
    private NetWorkManager mNetWorkManager;
    private List<AdEntity> adEntities;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            showView();
        }
    };

    private void showView() {
        RecycleViewAdapter adapter = new RecycleViewAdapter(this, adEntities);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,5));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);
        mNetWorkManager = NetWorkManager.INSTANCE;
        mNetWorkManager.postNetWorkByOkHttp(this);

    }

    @Override
    public void onSuccess(List<AdEntity> adEntities) {
        Log.d(TAG, "onSuccess: "+adEntities.toString());
        this.adEntities = adEntities;
        mHandler.sendEmptyMessage(0);
    }

    @Override
    public void onError(String errorMessage) {

    }
}
