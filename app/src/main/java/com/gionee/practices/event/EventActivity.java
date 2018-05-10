package com.gionee.practices.event;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.gionee.practices.R;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private List<BaseFragment> mFragments;
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        mViewPager = (ViewPager) findViewById(R.id.fragment_pager);
        mFragments = new ArrayList<>();
        mFragments.add(new FragmentDemoOne());
        mFragments.add(new FragmentDemoTwo());
        mFragments.add(new FragmentDemoThree());
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mFragmentAdapter);
    }
}
