package com.example.organizer_v2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.organizer_v2.SQLiteHelperTOPS;

public class DBManagerTops {
    /*
    private Context context;
    private SQLiteDatabase databaseTOPS;
    private SQLiteHelperTOPS sqLiteHelperTOPS;

    public DBManagerTops(Context c) {this.context = c;}

    public DBManagerTops open() throws SQLException{
        this.sqLiteHelperTOPS = new SQLiteHelperTOPS(this.context);
        this.databaseTOPS     = this.sqLiteHelperTOPS.getWritableDatabase();
        return this;
    }

    public void close() {this.sqLiteHelperTOPS.close();}

    public void insert(String name, String tags, String photo){
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelperTOPS.TOPSNAME, name);
        cv.put(SQLiteHelperTOPS.TOPSTAGS, tags);
        cv.put(SQLiteHelperTOPS.TOPSPHOTO, photo);
        this.databaseTOPS.insert(SQLiteHelperTOPS.TABLE_NAME_TOPS, null, cv);
    }

*/

}
