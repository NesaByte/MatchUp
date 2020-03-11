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

    /**
     * INSERTION OF MATCHED TOPS AND BOTTOMS
     *                          0 - int id_m, 1 - String name_m,
     *                          2 - String name_t, 3 - String tags_t, 4 - byte[] img_t,
     *                          5 - String name_b, 6 - String tags_b, 7 - byte[] img_b)
     */
    public void insertDataM(String name_m,
                            //String name_t, String tags_t,
                            byte[] img_t,
                            //String name_b, String tags_b,
                            byte[] img_b){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO TABLE_NAME VALUES(NULL, ?, ?, ?)";//", ?, ?, ? ,?)";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        sqLiteStatement.bindString(1, name_m);
        sqLiteStatement.bindBlob(2, img_t);
        sqLiteStatement.bindBlob(3, img_b);

        /*
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1, name_m);
        sqLiteStatement.bindString(2, name_t);
        sqLiteStatement.bindString(3, tags_t);
        sqLiteStatement.bindBlob(4, img_t);
        sqLiteStatement.bindString(5, name_b);
        sqLiteStatement.bindString(6, tags_b);
        sqLiteStatement.bindBlob(7, img_b);
        */
        sqLiteStatement.executeInsert();
    }

    public Cursor getDataM(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }
/**
 * "CREATE TABLE IF NOT EXISTS TABLE_NAME " +
 *                 "(id INTEGER PRIMARY KEY AUTOINCREMENT, name_m VARCHAR " +
 *                 "name_t VARCHAR,  tag_t VARCHAR, image_b BLOB, " +
 *                 "name_b VARCHAR, tag_b VARCHAR, image_b BLOB)"*/
    public void updateDataM(String name_m,
                            //String name_t, String tags_t,
                            byte[] img_t,
                            //String name_b, String tags_b,
                            byte[] img_b,
                            int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE TABLE_NAME SET name_m = ?, " +
                //"name_t = ?, tag_t = ?,
                "img_t = ?, " +
                //"name_b = ?, tag_b = ?,
                "img_b = ? " +
                "WHERE id = ?";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        //sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1, name_m);
        sqLiteStatement.bindBlob(2, img_t);
        sqLiteStatement.bindBlob(3, img_b);
        sqLiteStatement.bindDouble(4, (double)id);

        /*
        sqLiteStatement.bindString(1, name_m);
        sqLiteStatement.bindString(2, name_t);
        sqLiteStatement.bindString(3, tags_t);
        sqLiteStatement.bindBlob(4, img_t);
        sqLiteStatement.bindString(5, name_b);
        sqLiteStatement.bindString(6, tags_b);
        sqLiteStatement.bindBlob(7, img_b);
        sqLiteStatement.bindDouble(8, (double)id);
        */
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
