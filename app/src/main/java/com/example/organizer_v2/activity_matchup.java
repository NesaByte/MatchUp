package com.example.organizer_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class activity_matchup extends AppCompatActivity {



    //ImageButton ib_view_top, ib_view_bottom;
    //EditText et_view_name, et_view_tag;
    Button btn_amu_addtops, btn_amu_addbottoms, btn_amu_submit_mu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchup);

        getSupportActionBar().setTitle("Match Up");

        //et_view_name =    (EditText) findViewById(R.id.et_view_name);
        //et_view_tag  =    (EditText) findViewById(R.id.et_view_tag);

        btn_amu_addtops =    (Button) findViewById(R.id.btn_amu_addtops);
        btn_amu_addbottoms = (Button) findViewById(R.id.amu_addbottoms);

        btn_amu_addtops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTOPDialog();
            }
        });

        btn_amu_addbottoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBOTTOMDialog();
            }
        });
    }

    public void openTOPDialog(){
        dialog_matchuptop exampleDialog = new dialog_matchuptop();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }
    public void openBOTTOMDialog(){
        dialog_matchupbottom tempDialog = new dialog_matchupbottom();
        tempDialog.show(getSupportFragmentManager(), "example dialog");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){


            case R.id.btn_matchUp:
                break;

            case R.id.btn_addNewItem:
                actionAddNewItem();
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

    private void actionViewInventory(){
        Intent i = new Intent(activity_matchup.this, activity_viewinventory.class);
        startActivity(i);
    }

    private void actionAddNewItem(){
        Intent i = new Intent(activity_matchup.this, activity_addnewitem.class);
        startActivity(i);
    }

    private void actionRemoveItem(){
        Intent i = new Intent(activity_matchup.this, activity_removeitem.class);
        startActivity(i);
    }
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchup);
    }*/
}
