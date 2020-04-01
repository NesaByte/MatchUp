/** This is an SQLHelper that helps the programmer to manipulate or take values from the BOTTOM database
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

public class SQLiteHelperBOTTOMS extends SQLiteOpenHelper {

    /**
     * a constructor
     * @param context
     * @param name
     * @param cursorFactory
     * @param version
     */
    SQLiteHelperBOTTOMS(@Nullable Context context,
                     String name,
                     SQLiteDatabase.CursorFactory cursorFactory,
                     int version){
        super(context, name, cursorFactory, version);
    }

    /**
     * accepts an string of SQL command to be executed and performed for the database
     * @param sql
     */
    public void queryDataB(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    /**
     * method inserts new row of data into the database
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
     *this method returns a cursor that is being asked in the sql command
     * @param sql
     * @return
     */
    public Cursor getDataB(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    /**
     * updates either name, tag, image of whatever id that is being passed into the parameter
     * @param name
     * @param tag
     * @param image
     * @param id
     */
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
     * this method delete the entire row of the id that is being passed in the parameter
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
