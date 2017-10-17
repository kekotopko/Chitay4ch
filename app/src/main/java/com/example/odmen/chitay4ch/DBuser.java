package com.example.odmen.chitay4ch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by odmen on 11.10.2017.
 */

public class DBuser extends SQLiteOpenHelper {
    public DBuser(Context context) {
        super(context, "publicDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table public ("
                + "_id integer primary key autoincrement,"
                + "vkid integer,"
                + "name text,"
                + "scrn_name text,"
                + "image text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
