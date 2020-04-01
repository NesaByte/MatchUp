/** This activity lets user view the bottom values from the database
 * the user can also VIEW, UPDATE, and DELETE the values from the database
 *    @author Nesa Bertanico
 *    @version 1.0
 */

package com.example.organizer_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
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
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;

import static com.example.organizer_v2.MainActivity.sqLiteHelperBOTTOMS;

public class secondactivity_viewbottom extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    SearchView sv_searchView;
    ListView lv_listView;
    ArrayList<Model> mList;
    adapter_viewtop mAdapter = null;

    ImageView iv_photo;

    final int REQUEST_CODE_GALLERY = 888;

    /**
     *upon opening this activity, the activity is designed to look like the activity_secondviewbottom.xml
     * where the values are taken and displayed.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondviewbottom);

        getSupportActionBar().setTitle("Viewing bottoms");

        sv_searchView = findViewById(R.id.sv_searchView);
        lv_listView = findViewById(R.id.lv_listView);
        mList = new ArrayList<>();
        mAdapter = new adapter_viewtop(this, R.layout.row_layoutbottom, mList);
        lv_listView.setAdapter(mAdapter);

        sv_searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        /**
         * this method makes a search query in this activity,
         * it accepts user input and searches the database if the name exists
         */
        sv_searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            /**
             *this method accepts the user input,
             * if it user types in a string and the string matches a name in the database,
             *      the result will be passed into the adapter to be displayed into the screen
             * @param query
             * @return
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            /**
             *this method accepts the user input while the text is being actively changed,
             * if it user types in a string and the string matches a name in the database,
             *      the result will be passed into the adapter to be displayed into the screen
             * @param newText
             * @return
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });

        /**
         *this cursor will go into the database and get all the values from the databases
         */
        Cursor cursor = sqLiteHelperBOTTOMS.getDataB("SELECT * FROM TABLE_NAME");
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
            toastMsg("No data found.");
        }

        /**
         * upon long press of any item on the list, the user will be given 3 options:
         * 1 - view details: opens a dialog box that shows the name, tag, image
         * 2 - update: opens a dialog box that allows user to update name, tag, image of an existing item
         * 3 - delate: deletes the item from the database
         *
         */
        lv_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            /**
             *
             * @param parent
             * @param view
             * @param position
             * @param id
             * @return
             */
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final CharSequence[] items = {"View details", "Update bottom", "Delete bottom"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(secondactivity_viewbottom.this);
                dialog.setTitle("What do you want to do?");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            Cursor c = sqLiteHelperBOTTOMS.getDataB("SELECT id FROM TABLE_NAME");
                            ArrayList<Integer> arrID = new ArrayList<>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            showDialogRead(secondactivity_viewbottom.this, arrID.get(position));

                        }

                        if(which == 1){
                            Cursor c = sqLiteHelperBOTTOMS.getDataB("SELECT id FROM TABLE_NAME");
                            ArrayList<Integer> arrayList_id = new ArrayList<>();
                            while(c.moveToNext()){
                                arrayList_id.add(c.getInt(0));
                            }
                            showDialogUpdate(secondactivity_viewbottom.this, arrayList_id.get(position));

                        }

                        if(which == 2){
                            Cursor c = sqLiteHelperBOTTOMS.getDataB("SELECT id FROM TABLE_NAME");
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

    /**
     *this method will go into the database using a Cursor and find the certain item
     * by its position parameter,
     * then a dilog box is will pop out with the name, tag, image of that position
     * @param activity
     * @param position
     */
    private void showDialogRead(Activity activity, final int position) {
        final Dialog dialogRead = new Dialog(activity);
        dialogRead.setContentView(R.layout.dialog_viewtop_read);
        dialogRead.setTitle("Details of this bottom");

        final TextView tv_name = dialogRead.findViewById(R.id.tv_name);
        final TextView tv_tag = dialogRead.findViewById(R.id.tv_tag);
        iv_photo = dialogRead.findViewById(R.id.iv_photo);
        Button btnClose = dialogRead.findViewById(R.id.btnClose);

        Cursor cursor = sqLiteHelperBOTTOMS.getDataB(
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
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                dialogRead.dismiss();
            }
        });

    }

    /**
     *this method will update the values of the certain position in the parameter
     * first, it will use cursor to find the exact tuple
     * then it will take in the value and allows user to change the existing value
     *
     * @param activity
     * @param position
     */
    private void showDialogUpdate(Activity activity, final int position) {
        final Dialog dialogUpdate = new Dialog(activity);
        dialogUpdate.setContentView(R.layout.dialog_viewtop_update);
        dialogUpdate.setTitle("Let's update this bottom's information");

        final EditText et_name = dialogUpdate.findViewById(R.id.et_name);
        final EditText et_tag = dialogUpdate.findViewById(R.id.et_tag);
        iv_photo = dialogUpdate.findViewById(R.id.iv_photo);
        Button btnUpdate = dialogUpdate.findViewById(R.id.btnUpdate);

        Cursor cursor = sqLiteHelperBOTTOMS.getDataB(
                "SELECT * FROM TABLE_NAME WHERE id = " + position);
        mList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            et_name.setText(name);
            String tag = cursor.getString(2);
            et_tag.setText(tag);
            byte[] image = cursor.getBlob(3);
            iv_photo.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));

            mList.add(new Model(id, name, tag, image));
        }

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialogUpdate.getWindow().setLayout(width, height);
        dialogUpdate.show();

        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(secondactivity_viewbottom.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            }
        });

        /**
         * this method will update the values of the database
         */
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                try {
                    sqLiteHelperBOTTOMS.updateDataB(
                            et_name.getText().toString().trim(),
                            et_tag.getText().toString().trim(),
                            MainActivity.imageViewToByte(iv_photo),
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

    /**
     *this method will delete all
     * @param position
     */
    private void showDialogDelete(final int position) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(secondactivity_viewbottom.this);
        dialogDelete.setTitle("Deleting this bottom");
        dialogDelete.setMessage("Are you sure you want to delete this bottom?");
        dialogDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    sqLiteHelperBOTTOMS.deleteDataB(position);
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

    /**
     *
     */
    private void updateListData() {
        Cursor cursor = sqLiteHelperBOTTOMS.getDataB("SELECT * FROM TABLE_NAME");
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

    /**
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImageFromGallery();
            } else {
                toastMsg("No permission to access file location");
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     *
     */
    private void pickImageFromGallery() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Bottom Picture"), REQUEST_CODE_GALLERY);
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                iv_photo.setImageURI(resultUri);
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     *
     * @param msg
     */
    private void toastMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
