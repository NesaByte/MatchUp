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

import static android.app.Activity.RESULT_OK;
public class dialog_viewtop extends AppCompatDialogFragment {

    private ImageButton ib_view_top, ib_view_bottom;
    private EditText et_view_name, et_view_tag;

    public Dialog onCreateDialog(Bundle savedIntancesState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_viewtop , null);

        builder.setView(view).
                setTitle("List of All Tops").
                setPositiveButton("done", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        // for db: AddData();
                        //Toast.makeText(getActivity(), "Successfully Added", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                });
        return builder.create();
    }
}
