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
                            byte[] image_t,
                            //String name_b, String tags_b,
                            byte[] image_b){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO DB_MATCHED VALUES(NULL, ?, ?, ?)";//", ?, ?, ? ,?)";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1, name_m);
        sqLiteStatement.bindBlob(2, image_t);
        sqLiteStatement.bindBlob(3, image_b);

        sqLiteStatement.executeInsert();
        /*

        sqLiteStatement.bindString(1, name_m);
        sqLiteStatement.bindString(2, name_t);
        sqLiteStatement.bindString(3, tags_t);
        sqLiteStatement.bindBlob(4, img_t);
        sqLiteStatement.bindString(5, name_b);
        sqLiteStatement.bindString(6, tags_b);
        sqLiteStatement.bindBlob(7, img_b);
        */

    }

    /*public int getID(byte[] image_t, byte[] image_b){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT id FROM DB_MATCHED WHERE image_t = ? and image_b = ?";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        sqLiteStatement.clearBindings();
        sqLiteStatement.bindBlob(1, image_t);
        sqLiteStatement.bindBlob(2, image_b);

        String idthis = sqLiteStatement.execute();
        return idthis;
    }*/

    public Cursor getDataM(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }
/**
 * "CREATE TABLE IF NOT EXISTS TABLE_NAME " +
 *                 "(id INTEGER PRIMARY KEY AUTOINCREMENT, name_m VARCHAR " +
 *                 "name_t VARCHAR,  tag_t VARCHAR, image_b BLOB, " +
 *                 "name_b VARCHAR, tag_b VARCHAR, image_b BLOB)"*/

    public void updateTop(byte[] image_t, int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE DB_MATCHED SET image_t = ? WHERE id = ?";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);
        sqLiteStatement.bindBlob(1, image_t);
        sqLiteStatement.bindDouble(2, (double)id);
        sqLiteStatement.execute();
        //Log.i(db.execSQL("SELECT * FROM TAABLE_NAME WHERE  id = id"));
        db.close();
    }

    public void updateBottom(byte[] image_b, int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE DB_MATCHED SET image_b = ? WHERE id = ?";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);
        sqLiteStatement.bindBlob(1, image_b);
        sqLiteStatement.bindDouble(2, (double)id);
        sqLiteStatement.execute();
        db.close();
    }

    public void updateName(String name_m, byte[] image_t, byte[] image_b){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE DB_MATCHED SET name_m = ? WHERE image_t = ? and image_b = ?";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);
        sqLiteStatement.bindString(1, name_m);
        sqLiteStatement.bindBlob(2, image_t);
        sqLiteStatement.bindBlob(3, image_b);
        sqLiteStatement.execute();
        db.close();

    }

    public void updateDataM(String name_m,
                            //String name_t, String tags_t,
                            byte[] image_t,
                            //String name_b, String tags_b,
                            byte[] image_b,
                            int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE DB_MATCHED SET name_m = ?, " +
                //"name_t = ?, tag_t = ?,
                "image_t = ?, " +
                //"name_b = ?, tag_b = ?,
                "image_b = ? " +
                "WHERE id = ?";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        //sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1, name_m);
        sqLiteStatement.bindBlob(2, image_t);
        sqLiteStatement.bindBlob(3, image_b);
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
        String sql = "DELETE FROM DB_MATCHED WHERE id = ?";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);

        sqLiteStatement.clearBindings();
        sqLiteStatement.bindDouble(1, id);
        sqLiteStatement.execute();
        db.close();
    }

    public void pickData(int id){
        SQLiteDatabase db = getWritableDatabase();

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}
