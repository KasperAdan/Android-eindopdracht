package com.example.androideindopdracht;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NavigationViewActivity extends AppCompatActivity {
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

        this.profileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NavigationViewActivity.this, ProfileViewActivity.class);
                startActivity(i);
            }
        });

        this.backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NavigationViewActivity.this, ProfileViewActivity.class);
                startActivity(i);
            }
        });

        this.mapButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NavigationViewActivity.this, MapViewActivity.class);
                startActivity(i);
            }
        });

        this.playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NavigationViewActivity.this, MapViewActivity.class);
                startActivity(i);
            }
        });
    }

}
