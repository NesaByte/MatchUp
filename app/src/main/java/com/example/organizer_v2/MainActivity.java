package com.example.organizer_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //main menu = MATCH UP
    /*private ImageView img;
    private Button btn_matchUp;
    private Button btn_viewInventory;
    private Button btn_addNewItem;
    private Button btn_removeItem;*/
    public static SQLiteHelperTOPS sqLiteHelperTOPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_matchUp).setOnClickListener(this);
        findViewById(R.id.btn_viewInventory).setOnClickListener(this);
        findViewById(R.id.btn_addNewItem).setOnClickListener(this);
        findViewById(R.id.btn_removeItem).setOnClickListener(this);

        sqLiteHelperTOPS = new SQLiteHelperTOPS(this, "DBTOPS.sqlite", null, 1);
        sqLiteHelperTOPS.queryData("CREATE TABLE IF NOT EXISTS TABLE_NAME "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, tag VARCHAR, image BLOB)");


        //assigning the buttons with their id
        /*
        btn_matchUp       = findViewById(R.id.btn_matchUp);
        btn_viewInventory = findViewById(R.id.btn_viewInventory);
        btn_addNewItem    = findViewById(R.id.btn_addNewItem);
        btn_removeItem    = findViewById(R.id.btn_removeItem);
        img               = findViewById(R.id.imageView);*/

        /*//listeners
        btn_matchUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, activity_matchup.class);
                startActivity(intent);
                //startActivity(new Intent(MainActivity.this, activity_matchup.class));
            }
        });

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

        btn_removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activity_removeitem.class));
            }
        });

*/
    }

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

}
