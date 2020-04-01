/** This is an activity designed to add items into the BOTTOM database
 *    @author Nesa Bertanico
 *    @version 1.0
 */

package com.example.organizer_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import static com.example.organizer_v2.MainActivity.sqLiteHelperBOTTOMS;

public class secondactivity_addbottom extends AppCompatActivity {

    ImageView iv_photoB;
    EditText et_nameB, et_tagB;
    Button btn_addbottoms_add;

    final int REQUEST_CODE_GALLERY = 999;

    /**
     *upon opening this activity, the activity is designed to look like the activity_secondaddbottom.xml
     * where the values are taken and displayed.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondaddbottom);

        getSupportActionBar().setTitle("Adding new bottom");

        et_nameB =            findViewById(R.id.et_nameB);
        et_tagB  =            findViewById(R.id.et_tagB);
        iv_photoB =           findViewById(R.id.iv_photoB);
        btn_addbottoms_add =  findViewById(R.id.btn_addbottoms_add);

        /**
         *Interface definition for a callback to be invoked when a view is clicked.
         * when bottom image view is clicked, it will perform the onlick method below
         * to ask persmission from the user to allow access to the phone's storage
         * then it goes into the phone's storage to retrieve an image
         */
        iv_photoB.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v){
                ActivityCompat.requestPermissions(secondactivity_addbottom.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            }
        });

        /**
         *Interface definition for a callback to be invoked when a view is clicked.
         * when add button is clicked, it will perform the onlick method below
         */
        btn_addbottoms_add.setOnClickListener(new View.OnClickListener() {
            /**
             *this method overrides the onClick method which will insert the data given by the user
             * into the database with a unique id
             * @param v
             */
            @Override
            public void onClick(View v) {
                try {
                    sqLiteHelperBOTTOMS.insertDataB(
                            et_nameB.getText().toString().trim(),
                            et_tagB.getText().toString().trim(),
                            MainActivity.imageViewToByte(iv_photoB)
                    );
                    toastMsg("Added successfully.");
                    et_nameB.setText("");
                    et_tagB.setText("");
                    iv_photoB.setImageResource(R.drawable.insert_photos);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     *this methods checks if the user gave access to the application into the phone's storage
     * if permission is granted, it will continue into the phone's storage
     * if permission is denied, it will NOT go in the phone's storage. it will tell user that access was not granted
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
                toastMsg("You don't have permission to access file location");
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     *this method will lead the application into the phone's storage and get its content.
     * it will only show any file with "image/*"
     */
    public void pickImageFromGallery(){
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture ..."), REQUEST_CODE_GALLERY);
    }

    /**
     *this method is called after the activity is called and received a result
     * if the request code is 999, which means the user gave permission to this application to access it phone's storage
     *      and the resultcode is RESULT_OK =
     *          the image that will be selected in the phone can be cropped in a 1x1 ratio
     * if the request code is matches with the CROP_IMAGE_ACTIVITY_REQUEST_CODE
     *          the result of the crop will be assigned to the iv_photoB
     * if request code is an error code, error message will appear
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
                iv_photoB.setImageURI(resultUri);
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                toastMsg("Error: " + error);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     *this method makes it easier to use toast to output a message in the screen
     * @param msg
     */
    private void toastMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }



}
