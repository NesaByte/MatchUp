/** This is the Main Activity where the databses are created
 * and pathways to different activities are held
 *    @author Nesa Bertanico
 *    @version 1.0
 */
package com.example.organizer_v2;

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

    Button btn_viewInventory, btn_addNewItem, btn_matchUp, btn_viewMatchUp;
    ImageView img;

    public static SQLiteHelperTOPS sqLiteHelperTOPS;
    public static SQLiteHelperBOTTOMS sqLiteHelperBOTTOMS;
    public static SQLiteHelperMATCHUP sqLiteHelperMATCHUP;

    /**
     * initializing my activity as how the programmer wants it to look like.
     * @param savedInstanceState
     */
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


        /**
         *Interface definition for a callback to be invoked when a view is clicked.
         * when view button is clicked, it will send the user to another activity to view top or bottom
         *
         */
        btn_viewInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activity_viewinventory.class));
            }
        });

        /**
         *Interface definition for a callback to be invoked when a view is clicked.
         * when add button is clicked, it will send the user to another activity to add top or bottom
         *
         */
        btn_addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activity_addnewitem.class));
            }
        });


        /**
         *Interface definition for a callback to be invoked when a view is clicked.
         * when matchup button is clicked, it will send the user to another activity to match a top with a bottom
         */
        btn_matchUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activity_matchup.class));
            }
        });

        /**
         *Interface definition for a callback to be invoked when a view is clicked.
         * when view matchup button is clicked, it will send the user to another activity to view paired tops and bottoms
         */
        btn_viewMatchUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activity_viewmatchup.class));
            }
        });
    }

    /**
     *this method accepts an image, then the image is being converted into a bitmap
     * then the bitmap is written into a byte array to be retrieved as a full image easily
     * then it is being compressed to save storage space
     * @param image
     * @return
     */
    public static byte[] imageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArr = stream.toByteArray();
        return byteArr;
    }

    /**
     * this method is to fix the match database, to remove empty mstches
     */
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
}
