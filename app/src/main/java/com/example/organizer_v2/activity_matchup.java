package com.example.organizer_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.organizer_v2.MainActivity.sqLiteHelperMATCHUP;

public class activity_matchup extends AppCompatActivity {

    ImageView iv_phototop, iv_photobottom;
    EditText et_matchname;
    Button btn_amu_submit_mu;

    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchup);

        getSupportActionBar().setTitle("Match Up");

        et_matchname      = findViewById(R.id.et_matchname);
        iv_phototop       = findViewById(R.id.iv_phototop);
        iv_photobottom    = findViewById(R.id.iv_photobottom);
        btn_amu_submit_mu = findViewById(R.id.btn_amu_submit_mu);


        try{
            sqLiteHelperMATCHUP.insertDataM( //null, null, null

                    et_matchname.getText().toString().trim(),
                    MainActivity.imageViewToByte(iv_phototop),
                    MainActivity.imageViewToByte(iv_photobottom)
                    );
            toastMsg("matchup row: ");

        }catch(Exception e){
            toastMsg("ERROR: matchup row: " + e);
            e.printStackTrace();
        }

        iv_phototop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(activity_matchup.this, secondactivity_matchtop.class));
            }
        });
        iv_photobottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_matchup.this, secondactivity_matchbottom.class));
            }
        });

       btn_amu_submit_mu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sqLiteHelperMATCHUP.updateName(
                            et_matchname.getText().toString().trim()
                            //et_tagB.getText().toString().trim(),
                            //MainActivity.imageViewToByte(iv_phototop),
                            //MainActivity.imageViewToByte(iv_photobottom),

                    );
                    toastMsg("Added successfully.");
                    et_matchname.setText("");
                    //et_tagB.setText("");
                    iv_phototop.setImageResource(R.drawable.insert_photos);
                    iv_photobottom.setImageResource(R.drawable.insert_photos);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });/**/
    }

  /*  @Override
    public void onActivityResult(Intent data){
        Uri resultUri = result.getUri();
        iv_photo.setImageURI(resultUri);
    }
*/

    public void pickImageFromDBTOP(){
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture ..."), REQUEST_CODE_GALLERY);
    }

    private void toastMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
