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

    ImageButton ib_add_top, ib_add_bottom;
    EditText  et_add_name, et_add_tag;
    //SQLiteHelperTOPS sqLiteHelperTOPS = new SQLiteHelperTOPS(this);
    //DBManagerTops dbManagerTops = new DBManagerTops(this);

    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewitem);

        getSupportActionBar().setTitle("Add New Item");

        et_add_name =    (EditText) findViewById(R.id.et_add_name);
        et_add_tag  =    (EditText) findViewById(R.id.et_add_tag);

        ib_add_top =    (ImageButton) findViewById(R.id.ib_add_top);
        ib_add_bottom = (ImageButton) findViewById(R.id.ib_add_bottom);


        ib_add_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_addnewitem.this, secondactivity_addtop.class));
            }
        });


        //for the bottom dialog
        ib_add_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBOTTOMDialog();
            }
        });
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
