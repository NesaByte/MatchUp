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

public class dialog_addnewitemBottom extends AppCompatDialogFragment {
    private ImageButton ib_add_top, ib_add_bottom;
    private EditText et_add_nameB, et_add_tagB;

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builderB = new AlertDialog.Builder(getActivity());
        LayoutInflater inflaterB = getActivity().getLayoutInflater();
        View view = inflaterB.inflate(R.layout.dialog_addnewitembottom, null);

        et_add_nameB =    (EditText) view.findViewById(R.id.et_add_Bname);
        et_add_tagB  =    (EditText) view.findViewById(R.id.et_add_Btag);

        //upload img
        //upload img listener

        builderB.setView(view).
                setTitle("Add Bottom").
                setPositiveButton("Add", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        // for db: AddData();
                        Toast.makeText(getActivity(), "Successfully Added!", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                });
        return builderB.create();
    }
//for img:    public void onRequestPermissionResult (int requestCode)

}
