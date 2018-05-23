package com.gionee.practices.event;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.gionee.practices.R;
import com.gionee.practices.db.SQLAsyncTask;
import com.gionee.practices.db.SQLiteOperator;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDemoTwo extends BaseFragment {
    private static final String TAG = "FragmentDemoTwo";
    private List<Word> mWords;
    private Disposable mDisposable;
    private ListView mListView;

    public FragmentDemoTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_layout_two, container, false);
        mListView = (ListView) view.findViewById(R.id.list_view2);
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
                cacheWordsData();
            }
        });
    }

    private void showWordsData() {
        WordsAdapter adapter = new WordsAdapter(getContext(), mWords);
        mListView.setAdapter(adapter);
    }

    private void cacheWordsData() {
        new SQLAsyncTask<Long>() {
            @Override
            public Long doInBackground() {
                return new SQLiteOperator(getContext()).insert(mWords);
            }

            @Override
            public void onPostExecute(Long result) {
                Toast.makeText(getContext(), "insert: " + String.valueOf(result), Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    @Override
    public String getTitle() {
        return "fragment 2";
    }
}
