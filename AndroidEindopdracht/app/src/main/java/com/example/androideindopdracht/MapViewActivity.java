package com.example.androideindopdracht;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import org.osmdroid.api.IMapView;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;

public class MapViewActivity extends AppCompatActivity implements View.OnClickListener {

    private MapView map;
    private GeoPoint location;

    private boolean isMapCentered = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationsListener locationsListener = new LocationsListener(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, locationsListener);

        map = findViewById(R.id.osm_map);
        map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);

        ImageButton zoomIn = findViewById(R.id.map_zoom_in);
        zoomIn.setOnClickListener(this);
        ImageButton zoomOut = findViewById(R.id.map_zoom_out);
        zoomOut.setOnClickListener(this);
        ImageButton recenterMap = findViewById(R.id.map_recenter);
        recenterMap.setOnClickListener(this);
        ImageButton home = findViewById(R.id.map_home_button);

        map.addMapListener(new MapListener() {
            @Override
            public boolean onScroll(ScrollEvent event) {
                isMapCentered = false;
//                Log.i(IMapView.LOGTAG, System.currentTimeMillis() + " onScroll " + event.getX() + "," + event.getY());
                return true;
            }

            @Override
            public boolean onZoom(ZoomEvent event) {
                return false;
            }
        });
    }

    public void updateLocation(GeoPoint geoPoint) {
        location = geoPoint;
        if (isMapCentered){
            map.getController().setCenter(location);
        }
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
                map.getController().setCenter(location);
                isMapCentered = true;
                break;
            case R.id.map_home_button:
                Intent i = new Intent(this, NavigationViewActivity.class);
                startActivity(i);
                break;
        }
    }
}