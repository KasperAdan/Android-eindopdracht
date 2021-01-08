package com.example.androideindopdracht;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileViewActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton closeButton;
    private ImageButton settingsButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        this.closeButton = findViewById(R.id.closeButton);
        this.settingsButton = findViewById(R.id.resetButton);

        closeButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.closeButton:
                Intent close = new Intent(this, NavigationViewActivity.class);
                startActivity(close);
                break;
            case R.id.resetButton:
                new AlertDialog.Builder(this)
                        .setTitle("Reset stats")
                        .setMessage("Are you sure you want to reset your statistics?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;
        }
    }
}
