package com.example.organizer_v2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelperMATCHUP extends SQLiteOpenHelper {

    private static final String CREATE_TABLE_MATCHUP = "CREATE TABLE MATCHUP (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "MATCHUPName TEXT NOT NULL, " +
            "MATCHUPTags TEXT, " +
            "MATCHUPPhoto TEXT);";

    private static final String DB_NAME = "MATCHUP.DB";
    private static final String _ID = "_id";
    private static final String MATCHUPNAME = "matchupName";
    private static final String MATCHUPTAGS = "matchupTags";
    private static final String MATCHUPPHOTO = "matchupPhoto";
    private static final String TABLE_NAME_MATCHUP = "MATCHUP";

    public SQLiteHelperMATCHUP(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MATCHUP);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS MATCHUP");
        onCreate(db);
    }

}
