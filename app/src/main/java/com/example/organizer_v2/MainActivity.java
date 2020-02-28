package com.example.organizer_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //main menu = MATCH UP
    private ImageView img;
    private Button btn_matchUp;
    private Button btn_viewInventory;
    private Button btn_addNewItem;
    private Button btn_removeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assigning the buttons with their id
        btn_matchUp       = (Button) findViewById(R.id.btn_matchUp);
        btn_viewInventory = (Button) findViewById(R.id.btn_viewInventory);
        btn_addNewItem    = (Button) findViewById(R.id.btn_addNewItem);
        btn_removeItem    = (Button) findViewById(R.id.btn_removeItem);

        //listeners
        btn_matchUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activity_matchup.class));
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

        img = (ImageView) findViewById(R.id.imageView);

    }

/*    private void startMatchUp(){
        Intent intent = new Intent( this, MatchUp.class);
        startActivity(intent);
    }

    private void startViewInventory(){
        Intent intent = new Intent( this, ViewInventory.class);
        startActivity(intent);
    }
    private void startAddNewItem(){
        Intent intent = new Intent( this, AddNewItem.class);
        startActivity(intent);
    }
    private void startRemoveItem(){
        Intent intent = new Intent( this, RemoveItem.class);
        startActivity(intent);
    }*/
}
