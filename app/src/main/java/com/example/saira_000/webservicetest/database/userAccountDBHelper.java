package com.example.saira_000.webservicetest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.saira_000.webservicetest.UserModel;

import java.util.ArrayList;
import java.util.List;

public class userAccountDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "useraccount";
    private static final int DB_VERSION = 1;
    private static final String DROP = "DROP TABLE IF EXISTS ";



    public userAccountDBHelper(Context mcontext) {
        super(mcontext, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TableDetails.useraccount.CREATE_TABLE);

        /*String Query = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT, "+FIRSTNAME+" varchar,"+LASTNAME+" varchar,"+EMAIL+" varchar,"+PHONE+" varchar,"+PASSWORD+" varchar);";
        db.execSQL(Query);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);

        /*String Query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(Query);
        onCreate(db);*/
    }



}
