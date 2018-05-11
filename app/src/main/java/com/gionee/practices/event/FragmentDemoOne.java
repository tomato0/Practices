package com.gionee.practices.event;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gionee.practices.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDemoOne extends BaseFragment {
    private static final String TAG = "FragmentDemoOne";
    private String mTitle;
    private List<Word> mWords;
    private Disposable mDisposable;
    private MyListView mListView;


    public FragmentDemoOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_layout_one, container, false);
        mListView = (MyListView) view.findViewById(R.id.list_words);
        loadData();
        return view;
    }

    private void loadData() {
        LoadNetManager loadNetManager = LoadNetManager.getInstance();
        mWords = new ArrayList<>();
        loadNetManager.loadNetWordData(new Observer<Word>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Word word) {
                Log.d(TAG, "onNext: " + word);
                mWords.add(word);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                showWordsData();
            }
        });
    }

    private void showWordsData() {
        WordsAdapter adapter = new WordsAdapter(getContext(), mWords);
        mListView.setAdapter(adapter);
    }

    @Override
    public String getTitle() {
        return "Fragment 1";
    }

    @Override
    public void onDestroy() {
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        super.onDestroy();
    }
}
