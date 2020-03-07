package com.example.organizer_v2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelperBOTTOMS extends SQLiteOpenHelper {

    private static final String CREATE_TABLE_BOTTOMS = "CREATE TABLE BOTTOMS (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "bottomsName TEXT NOT NULL, " +
            "bottomsTags TEXT, " +
            "bottomsPhoto TEXT);";

    private static final String DB_NAME = "BOTTOMS.DB";
    private static final String _ID = "_id";
    private static final String BOTTOMSNAME = "bottomsName";
    private static final String BOTTOMSTAGS = "bottomsTags";
    private static final String BOTTOMSPHOTO = "bottomsPhoto";
    private static final String TABLE_NAME_BOTTOMS = "BOTTOMS";

    public SQLiteHelperBOTTOMS(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOTTOMS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS BOTTOMS");
        onCreate(db);
    }

}
