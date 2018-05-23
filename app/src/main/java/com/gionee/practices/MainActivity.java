package com.gionee.practices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gionee.practices.ad.AdActivity;
import com.gionee.practices.animation.AnimationActivity;
import com.gionee.practices.event.EventActivity;
import com.gionee.practices.recycleview.RecycleViewActivity;
import com.gionee.practices.rxjava.RxJavaActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.button_adv)
    Button mButton_AD;

    @BindView(R.id.button_anim)
    Button mButtonAnim;

    @BindView(R.id.button_recycle)
    Button mButtonRecycle;

    @BindView(R.id.button_event)
    Button mButtonEvent;

    @BindView(R.id.button_rxjava)
    Button mButtonRxJava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton_AD = (Button) findViewById(R.id.button_adv);
        mButton_AD.setOnClickListener(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.button_adv)
    public void onClickAd(View view) {
        Intent intent = new Intent(this, AdActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_anim)
    public void onClickAnim(View view) {
        Intent intent = new Intent(this, AnimationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_recycle)
    public void onClickRecycle(View view) {
        Intent intent = new Intent(this, RecycleViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_event)
    public void onClickEvent(View view) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_rxjava)
    public void onClickRxJava(View view) {
        Intent intent = new Intent(this, RxJavaActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_adv:
                Intent intent = new Intent(this, AdActivity.class);
                startActivity(intent);
                break;
        }
    }
}
