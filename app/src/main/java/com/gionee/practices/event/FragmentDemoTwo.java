package com.gionee.practices.event;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gionee.practices.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDemoTwo extends BaseFragment {


    public FragmentDemoTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_layout_two, container, false);
    }

    @Override
    public String getTitle() {
        return "fragment 2";
    }
}
