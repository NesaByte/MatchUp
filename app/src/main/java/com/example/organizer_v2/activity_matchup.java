package com.example.organizer_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.organizer_v2.MainActivity.sqLiteHelperBOTTOMS;
import static com.example.organizer_v2.MainActivity.sqLiteHelperMATCHUP;
import static com.example.organizer_v2.MainActivity.sqLiteHelperTOPS;

public class activity_matchup extends AppCompatActivity {

    ImageView iv_phototop, iv_photobottom;
    EditText et_matchname;
    Button btn_amu_submit_mu;
    static final int PICK_REQUEST = 0;
    public static final int TOP_REQUEST = 1;
    public static final int BOTTOM_REQUEST = 2;

    final int REQUEST_CODE_GALLERY = 999;
    int top = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchup);

        getSupportActionBar().setTitle("Match Up");

        et_matchname      = findViewById(R.id.et_matchname);
        iv_phototop       = findViewById(R.id.iv_phototop);
        iv_photobottom    = findViewById(R.id.iv_photobottom);
        btn_amu_submit_mu = findViewById(R.id.btn_amu_submit_mu);


        MainActivity.fixmatch();
/*        try{
            sqLiteHelperMATCHUP.insertDataM( //null, null, null

                    "",
                    MainActivity.imageViewToByte(iv_phototop),
                    MainActivity.imageViewToByte(iv_photobottom)
                    );
            toastMsg("iv_photo length: "+ iv_phototop.toString().length());
            toastMsg("iv_bottom length: "+ iv_photobottom.toString().length());

        }catch(Exception e){
            toastMsg("ERROR: matchup row: " + e);
            e.printStackTrace();
        }
*/
        iv_phototop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivityForResult(new Intent(activity_matchup.this, secondactivity_matchtop.class), TOP_REQUEST);
                /*int idtop = top;//getText().toString();

                Intent ii=new Intent(activity_matchup.this, secondactivity_matchtop.class);
                ii.getExtra("id", idtop);
                startActivity(ii);*/

            }
        });
        iv_photobottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(activity_matchup.this, secondactivity_matchbottom.class), BOTTOM_REQUEST);
            }
        });

       btn_amu_submit_mu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sqLiteHelperMATCHUP.insertDataM(
                            et_matchname.getText().toString().trim(),
                            MainActivity.imageViewToByte(iv_phototop),
                            MainActivity.imageViewToByte(iv_photobottom)
                    );
                    toastMsg("Added successfully.");
                    et_matchname.setText("");
                    iv_phototop.setImageResource(R.drawable.insert_photos);
                    iv_photobottom.setImageResource(R.drawable.insert_photos);
                } catch (Exception e) {
                    toastMsg("Error: " + e);
                    e.printStackTrace();
                }
                /*

                int n = et_matchname.length();
                int t = iv_phototop.toString().length();
                int b = iv_photobottom.toString().length();

                //toastMsg("t should not be 109 or 117: " + t);
                //toastMsg("b should not be 112 or 121: " + b);
                if(
                        t == 109 ||
                        t == 117 ||
                        b == 112 ||
                        b == 121){
                    MainActivity.fixmatch();
                    et_matchname.setText("");
                    iv_phototop.setImageResource(R.drawable.insert);
                    iv_photobottom.setImageResource(R.drawable.insert);
                    toastMsg("iv_photo length: "+ iv_phototop.toString().length());
                    toastMsg("iv_bottom length: "+ iv_photobottom.toString().length());

                    toastMsg("ERROR: Adding unsuccessful!");

                }else{

                    try {
                        String name = et_matchname.getText().toString().trim();
                        //toastMsg("updated name is: " + name);
                        sqLiteHelperMATCHUP.updateName(name,
                                MainActivity.imageViewToByte(iv_phototop),
                                MainActivity.imageViewToByte(iv_photobottom));
                        toastMsg("nameeeeeeeeeeee: "+ name);
                        toastMsg("iv_photo length: "+ iv_phototop.toString().length());
                        toastMsg("iv_bottom length: "+ iv_photobottom.toString().length());

                        toastMsg("Added successfully.");
                        et_matchname.setText("");
                        iv_phototop.setImageResource(R.drawable.insert);
                        iv_photobottom.setImageResource(R.drawable.insert);
                    } catch (Exception e) {
                        MainActivity.fixmatch();
                        toastMsg("ERROR: Adding unsuccessful" );
                        e.printStackTrace();
                    }
                }*/

            }
        });
    }

    public void pickImageFromDBTOP(){
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture ..."), REQUEST_CODE_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //toastMsg("onActivityresult used");
        if(requestCode == TOP_REQUEST){
            //toastMsg("onActivityresult used: top res");
            if(resultCode == RESULT_OK){
                toastMsg("onActivityresult used: res ok");
                //String sid = data.getStringExtra("idtop");
                Integer sid = data.getIntExtra("idtop", -1);
                Cursor cursor = sqLiteHelperTOPS.getData(
                        "SELECT * FROM TABLE_NAME WHERE id = " + sid);
                toastMsg("onActivityresult used sid = " + sid);

                try {
                    toastMsg("onActivityresult used: try");
                    while (cursor.moveToNext()) {
                        toastMsg("onActivityresult used: while");
                        int id = cursor.getInt(0);
                        byte[] img = cursor.getBlob(3);
                        iv_phototop.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));


                        //toastMsg("trying dialog pick id: " + id);
                        //toastMsg("trying dialog pick img.L: " + img.length);

                        //sqLiteHelperMATCHUP.updateTop(img,position);

                        //Uri resultUri = data.getData();
                        //iv_phototop.setImageURI(resultUri);

                        toastMsg("Picked Successfully.");
                    }
                } catch (Exception e) {
                    Log.e("Pick error: ", e.getMessage());
                }


            }
        }if(requestCode == BOTTOM_REQUEST){
            //toastMsg("onActivityresult used: top res");
            if(resultCode == RESULT_OK){
                toastMsg("onActivityresult used: res ok");
                //String sid = data.getStringExtra("idtop");
                Integer sid = data.getIntExtra("idbottom", -1);
                Cursor cursor = sqLiteHelperBOTTOMS.getDataB(
                        "SELECT * FROM TABLE_NAME WHERE id = " + sid);
                toastMsg("onActivityresult used sid = " + sid);

                try {
                    toastMsg("onActivityresult used: try");
                    while (cursor.moveToNext()) {
                        toastMsg("onActivityresult used: while");
                        int id = cursor.getInt(0);
                        byte[] img = cursor.getBlob(3);
                        iv_photobottom.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));

                        toastMsg("Picked Successfully.");
                    }
                } catch (Exception e) {
                    Log.e("Pick error: ", e.getMessage());
                }


            }
        }

        super.onActivityResult(requestCode, resultCode, data);
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
