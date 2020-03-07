package com.example.organizer_v2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class dialog_matchupbottom extends AppCompatDialogFragment {

    public Dialog onCreateDialog(Bundle savedIntancesState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_matchupbottom , null);

        builder.setView(view).
                setTitle("Pick a Bottom").
                setPositiveButton("Done", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        // for db: AddData();
                        Toast.makeText(getActivity(), "That's a cute bottom!", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                });
        return builder.create();
    }
}
