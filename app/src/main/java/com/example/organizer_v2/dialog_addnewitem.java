package com.example.organizer_v2;

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

import com.example.organizer_v2.db.DBManagerTops;
import com.example.organizer_v2.db.SQLiteHelperTOPS;


//import static android.app.Activity.RESULT_OK;
public class dialog_addnewitem extends AppCompatDialogFragment {

    //private ImageButton ib_add_top, ib_add_bottom;
    private EditText et_add_name, et_add_tag;
    //DBManagerTops dbManagerTops = new DBManagerTops(this);

    //SQLiteHelperTOPS sqLiteHelperTOPS;


    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_addnewitem, null);

        et_add_name =    (EditText) view.findViewById(R.id.et_add_name);
        et_add_tag  =    (EditText) view.findViewById(R.id.et_add_tag);

        //upload img
        //upload img listener



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
