package com.desele.whosupdexter;

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
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Buddies_new.db";
    public static final String BUDDIES_TABLE_NAME = "Buddies";
    public static final String BUDDIES_COLUMN_NAME = "name";
    public static final String BUDDIES_COLUMN_ID = "ID";
    public static final String BUDDIES_COLUMN_STATUS = "status";
    public static final String BUDDIES_COLUMN_PHONE = "phone";


    private HashMap hp;


    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(
                "CREATE TABLE Buddies " +
                        "(ID integer primary key, name text, status int, phone text)"
        );

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS Buddies");
        onCreate(db);
    }

    public boolean insertBuddy (String name, int ID, int status, String phone) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("ID", ID);
        contentValues.put("status", status);
        contentValues.put("phone", phone);

        db.insert("Buddies", null, contentValues);


        return true;
    }


    public Cursor getData(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM Buddies WHERE ID="+id+"",null );
        return res;

    }
    
    
    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, BUDDIES_TABLE_NAME);
        return numRows;
    }


    public boolean updateBuddy(Integer ID, String name,  int status, String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("ID", ID);
        contentValues.put("status", status);
        contentValues.put("phone", phone);

        db.update("Buddies", contentValues, "ID = ? ", new String[] {
                Integer.toString(ID) }
        );

        return true;

    }


    public Integer deleteBuddy (Integer ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Buddies",
                "ID = ? ",
                new String[] { Integer.toString(ID) });
    }

    public ArrayList<String> getAllBuddies()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * from Buddies", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(BUDDIES_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

}