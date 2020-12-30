package com.example.androideindopdracht;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NavigationViewActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton profileButton;
    private ImageButton backButton;
    private ImageButton mapButton;
    private ImageButton playButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        this.profileButton = findViewById(R.id.profileButton);
        this.backButton = findViewById(R.id.backButton);
        this.mapButton = findViewById(R.id.mapButton);
        this.playButton = findViewById(R.id.playButton);

        profileButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        mapButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
        if (DataClass.getInstance().isRunning()){
            this.playButton.setImageResource(R.drawable.stop_icon);
        }else{
            this.playButton.setImageResource(R.drawable.play_icon);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backButton:
                break;
            case R.id.playButton:
                DataClass.getInstance().setRunning(!DataClass.getInstance().isRunning());
                if (DataClass.getInstance().isRunning()){
//                    this.playButton.setImageResource(R.drawable.stop_icon);
                    Intent i = new Intent(this, MapViewActivity.class);
                    startActivity(i);
                }else{
                    this.playButton.setImageResource(R.drawable.play_icon);
                }
                break;
            case R.id.mapButton:
                Intent i = new Intent(this, MapViewActivity.class);
                startActivity(i);
                break;
            case R.id.profileButton:
                break;
        }
    }
}
