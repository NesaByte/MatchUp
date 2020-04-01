/** This is a
 *    @author Nesa Bertanico
 *    @version 1.0
 */
package com.example.organizer_v2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

/**
 *
 */
public class SQLiteHelperBOTTOMS extends SQLiteOpenHelper {


    SQLiteHelperBOTTOMS(@Nullable Context context,
                     String name,
                     SQLiteDatabase.CursorFactory cursorFactory,
                     int version){
        super(context, name, cursorFactory, version);
    }

    /**
     *
     * @param sql
     */
    public void queryDataB(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    /**
     *
     * @param name
     * @param tag
     * @param image
     */
    public void insertDataB(String name, String tag, byte[] image){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO TABLE_NAME VALUES(NULL, ?, ?, ?)";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1, name);
        sqLiteStatement.bindString(2, tag);
        sqLiteStatement.bindBlob(3, image);
        sqLiteStatement.executeInsert();
    }

    /**
     *
     * @param sql
     * @return
     */
    public Cursor getDataB(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    /**
     *
      * @param name
     * @param tag
     * @param image
     * @param id
     */
//String name, String tag, byte[] image
    public void updateDataB(String name, String tag, byte[] image, int id){
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

    /**
     *
     * @param id
     */
    public void deleteDataB(int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM TABLE_NAME WHERE id = ?";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        sqLiteStatement.clearBindings();
        sqLiteStatement.bindDouble(1, id);
        sqLiteStatement.execute();
        db.close();
    }


    /**
     *
      * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }


}
