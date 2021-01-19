package com.example.androideindopdracht;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;

public class NavigationViewActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton profileButton;
    private ImageButton backButton;
    private ImageButton mapButton;
    private ImageButton playButton;

    private boolean isBackPressedBefore = false;
    private NavigationRecyclerAdapter adapter;
    private RecyclerView rc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        DataClass.getInstance().setContext(this);
        try {
            DataClass.getInstance().readData();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
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

        adapter = new NavigationRecyclerAdapter(DataClass.getInstance().getRouteHistory());
        rc = findViewById(R.id.recyclerView);
        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backButton:
                onBackPressed();
                break;
            case R.id.playButton:
                DataClass.getInstance().setRunning(!DataClass.getInstance().isRunning());
                if (DataClass.getInstance().isRunning()){
                    this.playButton.setImageResource(R.drawable.stop_icon);
                    Intent i = new Intent(this, MapViewActivity.class);
                    startActivity(i);
                }else{
                    this.playButton.setImageResource(R.drawable.play_icon);
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.mapButton:
                Intent i = new Intent(this, MapViewActivity.class);
                startActivity(i);
                break;
            case R.id.profileButton:
                Intent profile = new Intent(this, ProfileViewActivity.class);
                startActivity(profile);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (isBackPressedBefore) {
            super.onBackPressed();
        }
        else {
            isBackPressedBefore = true;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    isBackPressedBefore = false;
                }
            }, 2000);
        }
    }

    @Override
    protected void onDestroy() {
        DataClass.getInstance().setRunning(false);
        try {
            DataClass.getInstance().writeData();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
