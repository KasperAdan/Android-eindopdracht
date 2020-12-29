package com.example.androideindopdracht;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;

public class MapViewActivity extends AppCompatActivity implements View.OnClickListener {

    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        map = findViewById(R.id.osm_map);
        map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);

        ImageButton zoomIn = findViewById(R.id.map_zoom_in);
        zoomIn.setOnClickListener(this);
        ImageButton zoomOut = findViewById(R.id.map_zoom_out);
        zoomOut.setOnClickListener(this);
        ImageButton recenterMap = findViewById(R.id.map_recenter);
        recenterMap.setOnClickListener(this);
        ImageButton home = findViewById(R.id.map_home_button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.map_zoom_in:
                map.getController().zoomIn();
                break;
            case R.id.map_zoom_out:
                map.getController().zoomOut();
                break;
            case R.id.map_recenter:
                break;
            case R.id.map_home_button:
                break;
        }
    }
}