package com.example.saira_000.webservicetest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.saira_000.webservicetest.UserModel;

import java.util.ArrayList;
import java.util.List;

public class RbacDB extends TableDetails.useraccount {

    private userAccountDBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public RbacDB(Context mcontext) {
        this.context = mcontext;
        this.dbHelper = new userAccountDBHelper(mcontext);
    }

    public void insertUser(List<UserModel> data) {
        long insertId = 0;
        Open();

        for (UserModel oo : data) {
            ContentValues values = new ContentValues();

            values.put(ID, oo.getId());
            values.put(FIRSTNAME, oo.getFirstname());
            values.put(LASTNAME, oo.getLastname());
            values.put(EMAIL, oo.getEmail());
            values.put(PHONE, oo.getPhonenumber());
            values.put(PASSWORD, oo.getPassword());

            insertId = database.replace(TABLE_NAME, null, values);
            Log.v("DB_Filter_Insert", "" + insertId);
        }


        /*for(int i=0; i<data.size();i++)
        {
            ContentValues values = new ContentValues();

            values.put(FIRSTNAME ,data.get(i).getFirstname());
            values.put(LASTNAME ,data.get(i).getLastname());
            values.put(EMAIL ,data.get(i).getEmail());
            values.put(PHONE ,data.get(i).getPhonenumber());
            values.put(PASSWORD ,data.get(i).getPassword());


        }*/

    }

    public List<UserModel> getUser() {
        List<UserModel> models = new ArrayList<>();

        Open();
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(countQuery, null);

        if (cursor.moveToFirst())
        {
            do{
                UserModel um = new UserModel();

                um.setId(cursor.getString(cursor.getColumnIndex(ID)));
                um.setFirstname(cursor.getString(cursor.getColumnIndex(FIRSTNAME)));
                um.setFirstname(cursor.getString(cursor.getColumnIndex(FIRSTNAME)));
                um.setLastname(cursor.getString(cursor.getColumnIndex(LASTNAME)));
                um.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                um.setPhonenumber(cursor.getString(cursor.getColumnIndex(PHONE)));
                um.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
                models.add(um);
            }while (cursor.moveToNext());
        }

        cursor.close();
        Close();
        return models;
    }

    public void Open () {
        database = dbHelper.getWritableDatabase();
    }

    public void Close () {
        dbHelper.close();
    }
}
