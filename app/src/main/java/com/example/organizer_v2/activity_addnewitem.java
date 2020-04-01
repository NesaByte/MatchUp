/** This activity is invoked when the user whats to add a new item in to the inventory
 * then the user will be asked if the he wants to add top or bottom by clicking on an image button
 *
 *    @author Nesa Bertanico
 *    @version 1.0
 */

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

    /**
     * invoking this activity will create the fields specified below
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewitem);

        getSupportActionBar().setTitle("Add New Item");

        et_name =    findViewById(R.id.et_name);
        et_tag  =    findViewById(R.id.et_tag);

        ib_add_top =     findViewById(R.id.ib_add_top);
        ib_add_bottom =  findViewById(R.id.ib_add_bottom);


        /**
         * when image button is clicked, it will send the user to another activity to complete the adding of the top
         */
        ib_add_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_addnewitem.this, secondactivity_addtop.class));
            }
        });
        /**
         * when image button is clicked, it will send the user to another activity to complete the adding of the bottom
         */
        ib_add_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_addnewitem.this, secondactivity_addbottom.class));
            }
        });
    }
}
