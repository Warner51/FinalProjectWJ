package com.example.finalprojectwj;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class notes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);

        Button infoButton = findViewById(R.id.helpButtonNotes);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfoAlertDialog(notes.this);
            }
        });
    }

    private void showInfoAlertDialog(Context context) {
        AlertDialog.Builder build = new AlertDialog.Builder(context);
        build.setTitle("Help Menu")
                .setMessage("This is the notes area. Type some text and click ADD to save it as a note.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // You can add code here to handle the "OK" button click
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}
