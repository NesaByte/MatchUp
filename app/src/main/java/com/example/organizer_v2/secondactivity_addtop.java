package com.example.organizer_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import static com.example.organizer_v2.MainActivity.sqLiteHelperTOPS;


public class secondactivity_addtop extends AppCompatActivity {
    //ImageButton
    ImageView ib_dialog_view_top;
    EditText et_add_name, et_add_tag;
    Button btn_addtops_add;

    final int REQUEST_CODE_GALLERY = 999;
    //public static SQLiteHelperTOPS sqLiteHelperTOPS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondaddtop);

        getSupportActionBar().setTitle("Adding new top");

        et_add_name =         findViewById(R.id.et_add_name);
        et_add_tag  =         findViewById(R.id.et_add_tag);
        ib_dialog_view_top =  findViewById(R.id.ib_dialog_view_top);
        btn_addtops_add =     findViewById(R.id.btn_addtops_add);

        ib_dialog_view_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                ActivityCompat.requestPermissions(secondactivity_addtop.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            }
        });

        /*sqLiteHelperTOPS = new SQLiteHelperTOPS(this, "DBTOPS.sqlite", null, 1);
        sqLiteHelperTOPS.queryData("CREATE TABLE IF NOT EXISTS TABLE_NAME "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, tag VARCHAR, image BLOB)");
*/
        btn_addtops_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sqLiteHelperTOPS.insertData(
                            et_add_name.getText().toString().trim(),
                            et_add_tag.getText().toString().trim(),
                            imageViewToByte(ib_dialog_view_top)
                    );
                    toastMsg("Added successfully.");
                    et_add_name.setText("");
                    et_add_tag.setText("");
                    ib_dialog_view_top.setImageResource(R.drawable.insert_photos);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static byte[] imageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArr = stream.toByteArray();
        return byteArr;
    }

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

    public void pickImageFromGallery(){
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture ..."), REQUEST_CODE_GALLERY);
    }

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
                ib_dialog_view_top.setImageURI(resultUri);
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                toastMsg("Error: " + error);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void toastMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
