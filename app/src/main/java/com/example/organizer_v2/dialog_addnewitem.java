package com.example.organizer_v2;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;


//import static android.app.Activity.RESULT_OK;
public class dialog_addnewitem extends AppCompatDialogFragment{

    ImageButton ib_dialog_view_top;
    EditText et_add_name, et_add_tag;
    //DBManagerTops dbManagerTops = new DBManagerTops(this);

    final int REQUEST_CODE_GALLERY = 999;
    public static SQLiteHelperTOPS sqLiteHelperTOPS;


    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_addnewitem, null);

        et_add_name =        (EditText) view.findViewById(R.id.et_add_name);
        et_add_tag  =        (EditText) view.findViewById(R.id.et_add_tag);
        ib_dialog_view_top = (ImageButton) view.findViewById(R.id.ib_dialog_view_top);

        ib_dialog_view_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                requestPermissions(//dialog_addnewitem.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            }
        });

      //  sqLiteHelperTOPS = new SQLiteHelperTOPS(this);

     builder.setView(view).
             setTitle("Add Top").
             setPositiveButton("Add", new DialogInterface.OnClickListener(){
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i){
                     // for db: AddData();
                     if (et_add_name.length()  == 0 || et_add_tag.length() == 0){
                         Toast.makeText(getActivity(), "Missing field(s)!", Toast.LENGTH_SHORT).show();
                     }else{
                         Toast.makeText(getActivity(), "Successfully Added!", Toast.LENGTH_SHORT).show();
                     }


                     dismiss();



                 }
             });


     return builder.create();
    }
//for img:    public void onRequestPermissionResult (int requestCode)
}
