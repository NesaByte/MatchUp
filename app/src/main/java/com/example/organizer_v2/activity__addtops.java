package com.example.organizer_v2;

import androidx.annotation.NonNull;
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
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;

import com.theartofdev.edmodo.cropper.CropImage;

public class activity__addtops extends AppCompatActivity {

    ImageButton ib_dialog_view_top;
    EditText et_add_name, et_add_tag;
    Button btn_addtops_add;
    //DBManagerTops dbManagerTops = new DBManagerTops(this);

    final int REQUEST_CODE_GALLERY = 999;
    public static SQLiteHelperTOPS sqLiteHelperTOPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__addtops);

        et_add_name =         findViewById(R.id.et_add_name);
        et_add_tag  =         findViewById(R.id.et_add_tag);
        ib_dialog_view_top =  findViewById(R.id.ib_dialog_view_top);
        btn_addtops_add =     findViewById(R.id.btn_addtops_add);

        ib_dialog_view_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                ActivityCompat.requestPermissions(activity__addtops.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            }
        });

        sqLiteHelperTOPS = new SQLiteHelperTOPS(this, "DB_TOPS.sqlite", null, 1);
        sqLiteHelperTOPS.queryData("CREATE TABLE IF NOT EXISTS TOPS.DB "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, tag VARCHAR, image BLOB");

        btn_addtops_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    sqLiteHelperTOPS.insertData(
                            et_add_name.getText().toString().trim(),
                            et_add_tag.getText().toString().trim(),
                            imageViewToByte(ib_dialog_view_top));
//                    Toast.makeText(getActivity(), "Successfully Added", Toast.LENGTH_SHORT).show()
                    toastMsg("Added successfully.");
                    et_add_name.setText("");
                    et_add_tag.setText("");
                    ib_dialog_view_top.setImageResource(R.drawable.insert_photos);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public static byte[] imageViewToByte(ImageButton image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytesArr = stream.toByteArray();
        return bytesArr;
    }
/*
    @Override
    public void onRequestPermissionResult(int rqstCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(rqstCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                pickImageFromGallery();
            }else{
                toastMsg("No permission to access files");
            }
            return;
        }
        super.onRequestPermissionsResult(rqstCode, permissions, grantResults);
    }
*/
    public void pickImageFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a cute top"), REQUEST_CODE_GALLERY);

    }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode == REQUEST_CODE_GALLERY && requestCode == RESULT_OK){
            Uri uri = intent.getData();
            CropImage.activity(uri).setGuidelines

        }
    }*/
}


