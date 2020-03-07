package com.example.organizer_v2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.organizer_v2.ValuesModel;

import java.util.ArrayList;

public class SQLiteHelperTOPS extends SQLiteOpenHelper {
    private static final String CREATE_TABLE_TOPS = "CREATE TABLE TOPS (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "topsName TEXT NOT NULL, " +
            "topsTags TEXT, " +
            "topsPhoto TEXT);";

    public static final String DB_NAME = "TOPS.DB";
    public static final String _ID = "_id";
    public static final String TOPSNAME = "topsName";
    public static final String TOPSTAGS = "topsTags";
    public static final String TOPSPHOTO = "topsPhoto";
    public static final String TABLE_NAME_TOPS = "TOPS";

    public SQLiteHelperTOPS(Context context) {
        super(context, DB_NAME, null, 1);
        Log.d("table", CREATE_TABLE_TOPS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TOPS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS TOPS");
        onCreate(db);
    }

    public long addTopsDetail(String name, String tags, String photo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(TOPSNAME, name);
        val.put(TOPSTAGS, tags);
        val.put(TOPSPHOTO, photo);

        long insert = db.insert(DB_NAME, null, val);
        return insert;
    }

    public ArrayList<ValuesModel> getAllTOPS(){
        ArrayList<ValuesModel> valuesModelArrayList = null;//new ArrayList<ValuesModel>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_TOPS, null );

        if(cursor.moveToFirst()){
            valuesModelArrayList = new ArrayList<ValuesModel>();

            do{
                ValuesModel valuesModel = new ValuesModel();
                valuesModel.setName(cursor.getString(cursor.getColumnIndex(TOPSNAME)));
                valuesModel.setTags(cursor.getString(cursor.getColumnIndex(TOPSTAGS)));
                valuesModel.setPhotos(cursor.getString(cursor.getColumnIndex(TOPSPHOTO)));

                valuesModelArrayList.add(valuesModel);
            }while(cursor.moveToNext());
        }
        return valuesModelArrayList;
    }

    public void deleteTOPS(String name){
        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_NAME_TOPS, TOPSNAME + " LIKE ?", new String[] {"%" + name + "%"});
    }

    public ArrayList<ValuesModel> searchTOPS (String name){
        ArrayList<ValuesModel> valuesModelArrayList = null;//new ArrayList<ValuesModel>();

        try{
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_TOPS + " WHERE " + TOPSNAME + " LIKE ?", new String[]{"%" + name + "%"});

            if(cursor.moveToFirst()){
                valuesModelArrayList = new ArrayList<ValuesModel>();
                do{
                    ValuesModel valuesModel = new ValuesModel();
                    valuesModel.setName(cursor.getString(cursor.getColumnIndex(TOPSNAME)));
                    valuesModel.setTags(cursor.getString(cursor.getColumnIndex(TOPSTAGS)));
                    valuesModel.setPhotos(cursor.getString(cursor.getColumnIndex(TOPSPHOTO)));
                    valuesModelArrayList.add(valuesModel);

                }while(cursor.moveToNext());
            }
        }catch (Exception e){
            valuesModelArrayList = null;
        }
        return valuesModelArrayList;
    }


}
