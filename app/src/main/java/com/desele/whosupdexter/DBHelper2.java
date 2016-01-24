package com.desele.whosupdexter;

/**
 * Created by lenmor on 24/01/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lenmor on 23/01/16.
 */
public class DBHelper2 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Users_new.db";
    public static final String LOGIN_TABLE_NAME = "Users";
    public static final String LOGIN_COLUMN_USERNAME = "username";
    public static final String LOGIN_COLUMN_PASSWORD = "password";


    private HashMap hp;


    public DBHelper2(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(
                "CREATE TABLE Users " +
                        "(username text, password text)"
        );

       // insertUser("lenny", "1234");

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }

    public boolean insertUser (String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("password", password);

        db.insert("Users", null, contentValues);

        return true;
    }


    public Cursor getData(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM Users WHERE username="+ username +" AND password="+password+"",null );
        return res;

    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, LOGIN_TABLE_NAME);
        return numRows;
    }



}