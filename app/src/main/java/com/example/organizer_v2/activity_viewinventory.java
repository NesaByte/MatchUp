/** This activity is invoked when the user whats to view all items in to the inventory
 * then the user will be asked if the he wants to view top or bottom by clicking on an image button
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
import android.widget.ImageButton;

public class activity_viewinventory extends AppCompatActivity {

    ImageButton ib_view_top, ib_view_bottom;

    /**
     * overrided the oncreate method to make the activity look like how the programmer wants it to be.
     * fields are specified below
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewinventory);

        getSupportActionBar().setTitle("Viewing Inventory");


        ib_view_top =     findViewById(R.id.ib_view_top);
        ib_view_bottom =  findViewById(R.id.ib_view_bottom);

        /**
         * when image button is clicked, it will send the user to another activity to view all top
         */
        ib_view_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_viewinventory.this, secondactivity_viewtop.class));
            }
        });

        /**
         * when image button is clicked, it will send the user to another activity to view all bottom
         */
        ib_view_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_viewinventory.this, secondactivity_viewbottom.class));
            }
        });
    }
}
