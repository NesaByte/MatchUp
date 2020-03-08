package com.example.organizer_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;


public class activity_addnewitem extends AppCompatActivity {

    ImageButton ib_add_top, ib_add_bottom;
    EditText  et_add_name, et_add_tag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewitem);

        getSupportActionBar().setTitle("Add New Item");

        et_add_name =    findViewById(R.id.et_add_name);
        et_add_tag  =    findViewById(R.id.et_add_tag);

        ib_add_top =     findViewById(R.id.ib_add_top);
        ib_add_bottom =  findViewById(R.id.ib_add_bottom);


        ib_add_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_addnewitem.this, secondactivity_addtop.class));
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

            case R.id.btn_addNewItem:
                break;

            case R.id.btn_matchUp:
                actionMatchUp();
                break;

            case R.id.btn_viewInventory:
                actionViewInventory();
                break;

            case R.id.btn_removeItem:
                actionRemoveItem();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void actionMatchUp(){
        Intent i = new Intent(activity_addnewitem.this, activity_matchup.class);
        startActivity(i);
    }

    private void actionViewInventory(){
        Intent i = new Intent(activity_addnewitem.this, activity_viewinventory.class);
        startActivity(i);
    }

    private void actionRemoveItem(){
        Intent i = new Intent(activity_addnewitem.this, activity_removeitem.class);
        startActivity(i);
    }*/
}
