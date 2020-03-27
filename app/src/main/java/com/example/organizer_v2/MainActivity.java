package com.example.organizer_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity{

    //main menu = MATCH UP
    /*private ImageView img;
    private Button btn_matchUp;
    private Button btn_viewInventory;
    private Button btn_addNewItem;
    private Button btn_removeItem;*/
    Button btn_viewInventory, btn_addNewItem, btn_matchUp, btn_viewMatchUp;
    ImageView img;

    public static SQLiteHelperTOPS sqLiteHelperTOPS;
    public static SQLiteHelperBOTTOMS sqLiteHelperBOTTOMS;
    public static SQLiteHelperMATCHUP sqLiteHelperMATCHUP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_viewInventory = findViewById(R.id.btn_viewInventory);
        btn_addNewItem    = findViewById(R.id.btn_addNewItem);
        btn_matchUp       = findViewById(R.id.btn_matchUp);
        btn_viewMatchUp   =  findViewById(R.id.btn_viewMatchUp);
        img               = findViewById(R.id.imageView);

        /**
         * CREATION OF DATABASE FOR TOPS
         */
        sqLiteHelperTOPS = new SQLiteHelperTOPS(this, "DB_TOPS.sqlite", null, 1);
        sqLiteHelperTOPS.queryData("CREATE TABLE IF NOT EXISTS TABLE_NAME " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, tag VARCHAR, image BLOB)");

        /**
         * CREATION OF DATABASE FOR BOTTOMS
         */
        sqLiteHelperBOTTOMS = new SQLiteHelperBOTTOMS(this, "DB_BOTTOMS.sqlite", null, 1);
        sqLiteHelperBOTTOMS.queryDataB("CREATE TABLE IF NOT EXISTS TABLE_NAME " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, tag VARCHAR, image BLOB)");


        /**
         * CREATION OF DATABASE FOR MATCHED TOPS AND BOTTOMS
         *                          (int id_m, String name_m,
         *                          String name_t, String tags_t, byte[] img_t,
         *                          String name_b, String tags_b, byte[] img_b)
         */
        sqLiteHelperMATCHUP = new SQLiteHelperMATCHUP(this, "DB_MATCHED.sqlite", null, 1);
        sqLiteHelperMATCHUP.queryDataM("CREATE TABLE IF NOT EXISTS DB_MATCHED " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, name_m VARCHAR, image_t BLOB, image_b BLOB)");

    fixmatch();



        btn_viewInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activity_viewinventory.class));
            }
        });

        btn_addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activity_addnewitem.class));
            }
        });


        btn_matchUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activity_matchup.class));
            }
        });

        btn_viewMatchUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activity_viewmatchup.class));
            }
        });


    }

    public static byte[] imageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArr = stream.toByteArray();
        return byteArr;
    }

    public static void fixmatch(){
        Cursor cursor = sqLiteHelperMATCHUP.getDataM(
                "SELECT * FROM DB_MATCHED");

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image_t = cursor.getBlob(2);
            byte[] image_b = cursor.getBlob(3);

            if (    name.length()< 1 ||
                    image_t.toString().length() == 109 ||
                    image_t.toString().length() == 117 ||
                    image_b.toString().length() == 112 ||
                    image_b.toString().length() == 121){
                sqLiteHelperMATCHUP.deleteDataM(id);
                //toastMsg("fixed: " + id);
            }
        }
    }
/*
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_addNewItem:
                startActivity(new Intent(this, activity_addnewitem.class));
                break;

            case R.id.btn_matchUp:
                startActivity(new Intent(this, activity_matchup.class));
                break;

            case R.id.btn_removeItem:
                startActivity(new Intent(this, activity_removeitem.class));
                break;

            case R.id.btn_viewInventory:
                startActivity(new Intent(this, activity_viewinventory.class));
                break;
        }
    }
*/
private void toastMsg(String msg){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
}

}
