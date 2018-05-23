package com.gionee.practices.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gionee.practices.event.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wsq
 * Date: 18-5-16
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class SQLiteOperator {
    private static final String TAG = "SQLiteOperator";

    private final SQLiteDbHelper mSqLiteDbHelper;

    public SQLiteOperator(Context context) {
        mSqLiteDbHelper = new SQLiteDbHelper(context.getApplicationContext());
    }

    public long insert(List<Word> words) {
        SQLiteDatabase database = mSqLiteDbHelper.getWritableDatabase();
        long ret = 0;
        try {
            database.beginTransaction();
            ContentValues values = new ContentValues();
            for (Word word : words) {
                values.put("chinese", word.getChinese());
                values.put("english", word.getEnglish());
                Log.d(TAG, "insert: " + word.toString());
                ret = database.insert(SQLiteDbHelper.TABLE_NAME, null, values);
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        return ret;
    }

    public List<Word> query() {
        List<Word> words = new ArrayList<>();
        SQLiteDatabase database = mSqLiteDbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = database.query(SQLiteDbHelper.TABLE_NAME, new String[]{"chinese", "english"}, "id > ?",
                    new String[]{"4"}, null, null, null);
            while (cursor.moveToNext()) {
                String chinese = cursor.getString(0);
                String english = cursor.getString(1);
                Word word = new Word();
                word.setChinese(chinese);
                word.setEnglish(english);
                words.add(word);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return words;
    }

    public void delete() {
        SQLiteDatabase database = mSqLiteDbHelper.getWritableDatabase();
        int delete = database.delete(SQLiteDbHelper.TABLE_NAME, "id < ?", new String[]{"4"});
    }

    public void update() {}
}
