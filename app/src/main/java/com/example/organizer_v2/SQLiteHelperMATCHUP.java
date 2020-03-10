package com.example.organizer_v2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class SQLiteHelperMATCHUP extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteHelperMATCHUP";

    SQLiteHelperMATCHUP(@Nullable Context context,
                        String name,
                        SQLiteDatabase.CursorFactory cursorFactory,
                        int version){
        super(context, name, cursorFactory, version);
    }

    public void queryDataM(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public void insertDataM(String name, String tag, byte[] image){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO TABLE_NAME VALUES(NULL, ?, ?, ?)";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1, name);
        sqLiteStatement.bindString(2, tag);
        sqLiteStatement.bindBlob(3, image);
        sqLiteStatement.executeInsert();
    }

    public Cursor getDataM(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    public void updateDataM(String name, String tag, byte[] image, int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE TABLE_NAME SET name = ?, tag = ?, image = ? WHERE id = ?";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        //sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1, name);
        sqLiteStatement.bindString(2, tag);
        sqLiteStatement.bindBlob(3, image);
        sqLiteStatement.bindDouble(4, (double)id);
        sqLiteStatement.execute();
        db.close();
    }

    public void deleteDataM(int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM TABLE_NAME WHERE id = ?";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        sqLiteStatement.clearBindings();
        sqLiteStatement.bindDouble(1, id);
        sqLiteStatement.execute();
        db.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }


}
