package com.example.organizer_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.example.organizer_v2.MainActivity.sqLiteHelperTOPS;

public class secondactivity_matchtop extends AppCompatActivity {
    SearchView sv_searchView;
    ListView lv_listView;
    ArrayList<Model> mList;
    adapter_viewtop mAdapter = null;

    ImageView iv_photo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondmatchtop);

        getSupportActionBar().setTitle("Pick a top for this match");

        sv_searchView = findViewById(R.id.sv_searchView);
        lv_listView = findViewById(R.id.lv_listView);
        mList = new ArrayList<>();
        mAdapter = new adapter_viewtop(this, R.layout.row_layout, mList);
        lv_listView.setAdapter(mAdapter);

        //Intent iin= getIntent();
        //Bundle b = iin.getExtras();

        sv_searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        sv_searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });

        Cursor cursor = sqLiteHelperTOPS.getDataImg("SELECT * FROM TABLE_NAME");
        mList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String tag = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            mList.add(new Model(id, name, tag, image));
        }
        mAdapter.notifyDataSetChanged();

        if (mList.size() == 0) {
            toastMsg("Empty tops");
        }

        lv_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final CharSequence[] items = {"View details", "Pick this top"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(secondactivity_matchtop.this);
                dialog.setTitle("What do you want to do?");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            Cursor c = sqLiteHelperTOPS.getData("SELECT id FROM TABLE_NAME");
                            //toastMsg("long click pick 1: " + position);

                            ArrayList<Integer> arrID = new ArrayList<>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            //toastMsg("long click pick 2: " + position);

                            showDialogRead(secondactivity_matchtop.this, arrID.get(position));

                        }

                        if(which == 1){
                            Cursor c = sqLiteHelperTOPS.getData("SELECT id FROM TABLE_NAME");
                            //toastMsg("long click pick 1: " + position);
                            ArrayList<Integer> arrayList_id = new ArrayList<>();
                            while(c.moveToNext()){
                                arrayList_id.add(c.getInt(0));
                            }
                            //toastMsg("ong click pick 2: " + position);
                            //showDialogPick(arrayList_id.get(position));
                            showDialogPick(secondactivity_matchtop.this, arrayList_id.get(position));

                        }

                    }
                });
                dialog.show();
                return true;
            }
        });

    }

    private void showDialogRead(Activity activity, final int position) {
        final Dialog dialogRead = new Dialog(activity);
        dialogRead.setContentView(R.layout.dialog_viewtop_read);
        dialogRead.setTitle("Details of this top:");

        final TextView tv_name = dialogRead.findViewById(R.id.tv_name);
        final TextView tv_tag = dialogRead.findViewById(R.id.tv_tag);
        iv_photo = dialogRead.findViewById(R.id.iv_photo);
        Button btnClose = dialogRead.findViewById(R.id.btnClose);

        Cursor cursor = sqLiteHelperTOPS.getData(
                "SELECT * FROM TABLE_NAME WHERE id = " + position);
        mList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            tv_name.setText(name);
            String tag = cursor.getString(2);
            tv_tag.setText(tag);
            byte[] image = cursor.getBlob(3);
            iv_photo.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
        }

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialogRead.getWindow().setLayout(width, height);
        dialogRead.show();

        updateListData();

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogRead.dismiss();
            }
        });

    }


    private void showDialogPick(Activity activity, final int position) {
        //final Dialog dialogPick = new Dialog(activity);


        AlertDialog.Builder dialogPick = new AlertDialog.Builder(secondactivity_matchtop.this);

        dialogPick.setTitle("Picking this top");
        dialogPick.setMessage("Are you sure you want to pick this top?");

        dialogPick.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Cursor cursor = sqLiteHelperTOPS.getData(
                        "SELECT * FROM TABLE_NAME WHERE id = " + position);
                //toastMsg("dialog pick: " + position);

                try {
                    //toastMsg("trying dialog pick: " + position);
                    /*mList.clear();
                    while (cursor.moveToNext()) {
                        int id = cursor.getInt(0);
                        byte[] img = cursor.getBlob(3);
                        //iv_photo.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));

                        //toastMsg("trying dialog pick id: " + id);
                        //toastMsg("trying dialog pick img.L: " + img.length);

                        sqLiteHelperMATCHUP.updateTop(img,position);
                    }*/
                    Intent i = new Intent();
                    i.putExtra("idtop",  position);
                    setResult(RESULT_OK, i);
                    toastMsg("Top Picked Successfully.");
                    finish();
                } catch (Exception e) {
                    Log.e("Pick error: ", e.getMessage());
                }
                updateListData();
            }
        });
        dialogPick.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogPick.show();

    }



    private void updateListData() {
        Cursor cursor = sqLiteHelperTOPS.getData("SELECT * FROM TABLE_NAME");
        mList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String tag = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            mList.add(new Model(id, name, tag, image));
        }
        mAdapter.notifyDataSetChanged();
    }

    private void toastMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

/*@Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
      if(resultCode == RESULT_OK){
          Uri resultUri = data.getData();
          iv_phototop.setImageURI(resultUri);

          Uri resultUriB = data.getData();
          iv_photobottom.setImageURI(resultUriB);
      }
      super.onActivityResult(requestCode, resultCode, data);
  }*/
    }
