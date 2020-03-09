package com.example.organizer_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.rec

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;

public class activity_viewinventory extends AppCompatActivity {

    ImageButton ib_view_top, ib_view_bottom;
    //EditText et_view_name, et_view_tag;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewinventory);

        getSupportActionBar().setTitle("Add New Item");

        //et_view_name =    (EditText) findViewById(R.id.et_view_name);
        //et_view_tag  =    (EditText) findViewById(R.id.et_view_tag);

        ib_view_top =     findViewById(R.id.ib_view_top);
        ib_view_bottom =  findViewById(R.id.ib_view_bottom);

        ib_view_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_viewinventory.this, secondactivity_viewtop.class));
            }
        });
        ib_view_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_viewinventory.this, secondactivity_viewbottom.class));
            }
        });
    }



/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){


            case R.id.btn_viewInventory:
                break;

            case R.id.btn_addNewItem:
                actionAddNewItem();
                break;

            case R.id.btn_matchUp:
                actionMatchUp();
                break;

            case R.id.btn_removeItem:
                actionRemoveItem();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void actionMatchUp(){
        Intent i = new Intent(activity_viewinventory.this, activity_matchup.class);
        startActivity(i);
    }

    private void actionAddNewItem(){
        Intent i = new Intent(activity_viewinventory.this, activity_addnewitem.class);
        startActivity(i);
    }

    private void actionRemoveItem(){
        Intent i = new Intent(activity_viewinventory.this, activity_removeitem.class);
        startActivity(i);
    }*/
}
