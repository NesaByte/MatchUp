package com.example.organizer_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity{

    //main menu = MATCH UP
    /*private ImageView img;
    private Button btn_matchUp;
    private Button btn_viewInventory;
    private Button btn_addNewItem;
    private Button btn_removeItem;*/
    Button btn_viewInventory, btn_addNewItem;
    ImageView img;

    public static SQLiteHelperTOPS sqLiteHelperTOPS;
    public static SQLiteHelperBOTTOMS sqLiteHelperBOTTOMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_viewInventory = findViewById(R.id.btn_viewInventory);
        btn_addNewItem    = findViewById(R.id.btn_addNewItem);
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
         * CREATION OF DATABASE FOR TOPS
         */
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
    }

    public static byte[] imageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArr = stream.toByteArray();
        return byteArr;
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
}
