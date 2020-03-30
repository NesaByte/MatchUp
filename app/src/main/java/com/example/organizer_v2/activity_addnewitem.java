package com.example.organizer_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class activity_addnewitem extends AppCompatActivity {

    ImageButton ib_add_top, ib_add_bottom;
    EditText  et_name, et_tag;
    //public static SQLiteHelperTOPS sqLiteHelperTOPS;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewitem);

        getSupportActionBar().setTitle("Add New Item");

        et_name =    findViewById(R.id.et_name);
        et_tag  =    findViewById(R.id.et_tag);

        ib_add_top =     findViewById(R.id.ib_add_top);
        ib_add_bottom =  findViewById(R.id.ib_add_bottom);

  /*      sqLiteHelperTOPS = new SQLiteHelperTOPS(this, "DB_TOPS.sqlite", null, 1);

        sqLiteHelperTOPS.queryData("CREATE TABLE IF NOT EXISTS TABLE_NAME " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, tag VARCHAR, image BLOB)");
*/

        ib_add_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_addnewitem.this, secondactivity_addtop.class));
            }
        });

        ib_add_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_addnewitem.this, secondactivity_addbottom.class));
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
