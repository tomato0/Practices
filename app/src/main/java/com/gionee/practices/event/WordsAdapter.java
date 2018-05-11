package com.gionee.practices.event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gionee.practices.R;

import java.util.List;

/**
 * Author: wsq
 * Date: 18-5-11
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class WordsAdapter extends BaseAdapter {
    private Context mContext;
    private List<Word> mWords;

    public WordsAdapter(Context context, List<Word> words) {
        mContext = context;
        mWords = words;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (null != mWords) {
            ret = mWords.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return mWords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WordHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.word_item, parent, false);
            holder = new WordHolder();
            holder.mChinese = convertView.findViewById(R.id.txt_chinese);
            holder.mEnglish = convertView.findViewById(R.id.txt_english);
            convertView.setTag(holder);
        } else {
            holder = ((WordHolder) convertView.getTag());
        }
        holder.mChinese.setText(mWords.get(position).getChinese());
        holder.mEnglish.setText(mWords.get(position).getEnglish());
        return convertView;
    }

    static class WordHolder{
        private TextView mChinese;
        private TextView mEnglish;
        WordHolder() {}
    }
}
