package com.gionee.practices.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author: wsq
 * Date: 18-5-16
 * Email: wangshaoqiang@gionee.com
 * function:
 */

public class SQLiteDbHelper extends SQLiteOpenHelper {
    private static final String SQL_NAME = "db_demo.db";
    private static final int SQL_DEFAULT_VERSION = 1;
    public static final String TABLE_NAME = "table_demo";
    private static final String SQL_TABLE_CREATE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "chinese TEXT NOT NULL," +
            "english TEXT NOT NULL" +
            ")";

    public SQLiteDbHelper(Context context) {
        super(context, SQL_NAME, null, SQL_DEFAULT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {

        }
    }
}
