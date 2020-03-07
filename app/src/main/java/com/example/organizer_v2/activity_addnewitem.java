package com.example.organizer_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.organizer_v2.db.DBManagerTops;

public class activity_addnewitem extends AppCompatActivity {

    //TODO: DB here
    //TextView  tv_add_clothing_inventory, tv_add_top_or_bottom;
    ImageButton ib_add_top, ib_add_bottom;
    EditText  et_add_name, et_add_tag;
    //SQLiteHelperTOPS sqLiteHelperTOPS = new SQLiteHelperTOPS(this);
    DBManagerTops dbManagerTops = new DBManagerTops(this);
    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewitem);

        //final Intent intent = new Intent(this, MainActivity.class);
        //TODO: DB here

        getSupportActionBar().setTitle("Add New Item");

        et_add_name =    (EditText) findViewById(R.id.et_add_name);
        et_add_tag  =    (EditText) findViewById(R.id.et_add_tag);

        ib_add_top =    (ImageButton) findViewById(R.id.ib_add_top);
        ib_add_bottom = (ImageButton) findViewById(R.id.ib_add_bottom);


        //for the top dialog
        ib_add_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                /*ActivityCompat.requestPermissions(activity_addnewitem.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);*/
                openTOPDialog();
            }/*
            @Override
            public void onClick(View v) {
                openTOPDialog();
            }*/
/*
            try{
                dbManagerTops.open();
                final String TOPSNAME = et_add_name.getText().toString().trim();
                final String TOPSTASG = et_add_tag.getText().toString().trim();
                String query = "SELECT * FROM TOPS where TOPSNAME LIKE ? '"+new String[] { TOPSNAME }+"' ";
                if
            }*/

        });

        //for the bottom dialog
        ib_add_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBOTTOMDialog();
            }
        });
    }
    public void openTOPDialog(){
        Intent intent = new Intent(this, activity__addtops.class);
        startActivity(intent);
        //dialog_addnewitem exampleDialog = new dialog_addnewitem();
        //exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }
    public void openBOTTOMDialog(){
        dialog_addnewitemBottom tempDialog = new dialog_addnewitemBottom();
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
    }
}
