package com.example.furkan.dictionary;

/**
 * Created by furkan on 16.04.2017.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dictionaries.db";
    public static final String CONTACTS_TABLE_NAME = "dictionary";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_turkish = "turkish";
    public static final String CONTACTS_COLUMN_english = "english";

    private HashMap hp;

    public Database(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table dictionary " +
                        "(id integer primary key, english text,turkish text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertContact (String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("english", name);
        contentValues.put("turkish", phone);

        db.insert("dictionary", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from dictionary where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<dictionary> getAllCotacts() {
        ArrayList<dictionary> array_list = new ArrayList<dictionary>();
        ArrayList<String> array_list2 = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from dictionary", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            dictionary a = new dictionary();
            a.setEnglish(res.getString(res.getColumnIndex(CONTACTS_COLUMN_english)));
            a.setTurkish(res.getString(res.getColumnIndex(CONTACTS_COLUMN_turkish)));
            array_list.add(a);
            //array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_english)));
           // array_list2.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_turkish)));
            res.moveToNext();
        }
        return array_list;
    }
}