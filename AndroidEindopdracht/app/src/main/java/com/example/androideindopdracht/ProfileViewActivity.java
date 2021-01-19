package com.example.androideindopdracht;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileViewActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton closeButton;
    private ImageButton settingsButton;
    private TextView totalDistance;
    private TextView totalTime;
    private TextView totalAvgDistance;
    private TextView totalAvgTime;
    private TextView longestRoute;
    private TextView totalAvgSpeed;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        this.closeButton = findViewById(R.id.closeButton);
        this.settingsButton = findViewById(R.id.resetButton);
        this.totalDistance = findViewById(R.id.totalDistanceNumber);
        this.totalTime = findViewById(R.id.totalTimeNumber);
        this.totalAvgDistance = findViewById(R.id.totalAvgDistanceNumber);
        this.totalAvgTime = findViewById(R.id.totalAvgTimeNumber);
        this.longestRoute = findViewById(R.id.longestRouteNumber);
        this.totalAvgSpeed = findViewById(R.id.totalAvgSpeedNumber);

        closeButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);

        if (DataClass.getInstance().getRouteHistory().size() > 0) {

            double distance = 0;
            long timePassed = 0;
            double longestRoute = 0;
            int avgSpeed = 0;
            for (Route route : DataClass.getInstance().getRouteHistory()) {
                distance += route.getDistance();
                timePassed += (route.getEndDate().getTime() - route.getDate().getTime());
                if (route.getDistance() >= longestRoute) {
                    longestRoute = route.getDistance();
                }
            }
            String timePassedString = String.format("%02d:%02d:%02d", timePassed / 3600000, timePassed / 60000, timePassed / 1000);

            long avgMilliSecond = timePassed / DataClass.getInstance().getRouteHistory().size();
            this.totalAvgTime.setText(String.format("%02d:%02d:%02d", avgMilliSecond / 3600000, avgMilliSecond / 60000, avgMilliSecond / 1000));
            this.totalTime.setText(timePassedString);
            this.totalDistance.setText(String.format("%.2f", distance) + "m");
            this.totalAvgDistance.setText(String.format("%.2f", distance / DataClass.getInstance().getRouteHistory().size()) + "m");
            this.longestRoute.setText(String.format("%.2f", longestRoute) + "m");
            this.totalAvgSpeed.setText(String.format("%.2f", (distance / (timePassed / 1000)) * 3.6) + "km/h");
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.closeButton:
                finish();
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
