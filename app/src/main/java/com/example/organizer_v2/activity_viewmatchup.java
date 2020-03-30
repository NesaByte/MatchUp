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
import android.graphics.BitmapFactory;
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

import java.util.ArrayList;

import static com.example.organizer_v2.MainActivity.sqLiteHelperBOTTOMS;
import static com.example.organizer_v2.MainActivity.sqLiteHelperMATCHUP;
import static com.example.organizer_v2.MainActivity.sqLiteHelperTOPS;

public class activity_viewmatchup extends AppCompatActivity {

    SearchView sv_searchView;
    ListView lv_listView;
    ArrayList<Model_matched> mmList;
    adapter_viewmatch mmAdapter = null;

    ImageView iv_phototop;
    ImageView iv_photobottom;

    public static final int TOP_REQUEST = 1;
    public static final int BOTTOM_REQUEST = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmatchup);

        sv_searchView = findViewById(R.id.sv_searchView);
        lv_listView = findViewById(R.id.lv_listView);
        mmList = new ArrayList<>();
        mmAdapter = new adapter_viewmatch(this, R.layout.row_layout_matchup, mmList);
        lv_listView.setAdapter(mmAdapter);


        sv_searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        sv_searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mmAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mmAdapter.getFilter().filter(newText);
                return false;
            }
        });

        Cursor cursor = sqLiteHelperMATCHUP.getDataM("SELECT * FROM DB_MATCHED");
        mmList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name_m = cursor.getString(1);
            byte[] image_t = cursor.getBlob(2);
            byte[] image_b = cursor.getBlob(3);

            try{
            mmList.add(new Model_matched(id, name_m, image_t, image_b));
              //  toastMsg("match name is>> " + id + image_t.length);
              //  toastMsg("match name is>> " + name_m+image_b.length);
            }catch (Exception e) {
                Log.e("Cursor error: ", e.getMessage());
            }

        }

       mmAdapter.notifyDataSetChanged();

        if (mmList.size() == 0) {
            toastMsg("Empty matchups");
        }

        lv_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final CharSequence[] items = {"View details", "Update Match", "Delete Match"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(activity_viewmatchup.this);
                dialog.setTitle("What do you want to do?");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            Cursor c = sqLiteHelperMATCHUP.getDataM("SELECT id FROM DB_MATCHED");
                            ArrayList<Integer> arrID = new ArrayList<>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            showDialogRead(activity_viewmatchup.this, arrID.get(position));

                        }

                        if(which == 1){
                            Cursor c = sqLiteHelperMATCHUP.getDataM("SELECT id FROM DB_MATCHED");
                            ArrayList<Integer> arrayList_id = new ArrayList<>();
                            while(c.moveToNext()){
                                arrayList_id.add(c.getInt(0));
                            }
                            showDialogUpdate(activity_viewmatchup.this, arrayList_id.get(position));

                        }

                        if(which == 2){
                            Cursor c = sqLiteHelperMATCHUP.getDataM("SELECT id FROM DB_MATCHED");
                            ArrayList<Integer> arrayList_id = new ArrayList<>();
                            while(c.moveToNext()){
                                arrayList_id.add(c.getInt(0));
                            }
                            showDialogDelete(arrayList_id.get(position));

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
        dialogRead.setContentView(R.layout.dialog_viewmatch_read);
        dialogRead.setTitle("Details of this top:");

        final TextView tv_name = dialogRead.findViewById(R.id.tv_name);
        iv_phototop = dialogRead.findViewById(R.id.iv_photo_top);
        iv_photobottom = dialogRead.findViewById(R.id.iv_photo_bottom);
        Button btnClose = dialogRead.findViewById(R.id.btnClose);

        Cursor cursor = sqLiteHelperMATCHUP.getDataM(
                "SELECT * FROM DB_MATCHED WHERE id = " + position);
        mmList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            tv_name.setText(name);

            byte[] imagetop = cursor.getBlob(2);
            iv_phototop.setImageBitmap(BitmapFactory.decodeByteArray(imagetop, 0, imagetop.length));

            byte[] imagebottom = cursor.getBlob(3);
            iv_photobottom.setImageBitmap(BitmapFactory.decodeByteArray(imagebottom, 0, imagebottom.length));
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

    private void showDialogUpdate(Activity activity, final int position) {
        final Dialog dialogUpdate = new Dialog(activity);
        dialogUpdate.setContentView(R.layout.dialog_viewmatch_update);
        dialogUpdate.setTitle("Let's update this match's information");

        final EditText et_name = dialogUpdate.findViewById(R.id.et_name);
        iv_phototop = dialogUpdate.findViewById(R.id.iv_phototop);
        iv_photobottom = dialogUpdate.findViewById(R.id.iv_photobottom);
        Button btnUpdate = dialogUpdate.findViewById(R.id.btnUpdate);

        Cursor cursor = sqLiteHelperMATCHUP.getDataM(
                "SELECT * FROM DB_MATCHED WHERE id = " + position);
        mmList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            et_name.setText(name);

            byte[] imaget = cursor.getBlob(2);
            iv_phototop.setImageBitmap(BitmapFactory.decodeByteArray(imaget, 0, imaget.length));

            byte[] imageb = cursor.getBlob(3);
            iv_photobottom.setImageBitmap(BitmapFactory.decodeByteArray(imageb, 0, imageb.length));

            mmList.add(new Model_matched(id, name, imaget, imageb));
        }

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialogUpdate.getWindow().setLayout(width, height);
        dialogUpdate.show();

        iv_phototop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(activity_viewmatchup.this, secondactivity_matchtop.class), TOP_REQUEST);
            }
        });

        iv_photobottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(activity_viewmatchup.this, secondactivity_matchbottom.class), BOTTOM_REQUEST);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sqLiteHelperMATCHUP.updateDataM(
                            et_name.getText().toString().trim(),
                            MainActivity.imageViewToByte(iv_phototop),
                            MainActivity.imageViewToByte(iv_photobottom),
                            position
                    );
                    toastMsg("Updated successfully.");
                    dialogUpdate.dismiss();
                }
                catch (Exception error) {
                    Log.e("Update error: ", error.getMessage());
                }
                updateListData();
            }
        });
    }

    private void showDialogDelete(final int position) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(activity_viewmatchup.this);
        dialogDelete.setTitle("Deleting this match");
        dialogDelete.setMessage("Are you sure you want to delete this match?");
        dialogDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    sqLiteHelperMATCHUP.deleteDataM(position);
                    toastMsg("Deleted Successfully.");
                }
                catch (Exception e) {
                    Log.e("Deletion error: ", e.getMessage());
                }
                updateListData();
            }
        });
        dialogDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateListData() {
        Cursor cursor = sqLiteHelperMATCHUP.getDataM("SELECT * FROM DB_MATCHED");
        mmList.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] imagetop = cursor.getBlob(2);
            byte[] imagebottom = cursor.getBlob(3);

            mmList.add(new Model_matched(id, name, imagetop, imagebottom));
        }
        mmAdapter.notifyDataSetChanged();
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

}
