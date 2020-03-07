package com.example.organizer_v2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatDialogFragment;

public class dialog_viewbottom extends AppCompatDialogFragment {
    private ImageButton ib_view_top, ib_view_bottom;
    private EditText et_view_name, et_view_tag;

    public Dialog onCreateDialog(Bundle savedIntancesState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_viewbottom , null);

        builder.setView(view).
                setTitle("List of All Bottoms").
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
