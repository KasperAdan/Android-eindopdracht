package com.example.androideindopdracht;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

public class MapViewActivity extends AppCompatActivity implements View.OnClickListener {

    private MapView map;
    private GeoPoint location;
    private Marker userMarker;
    private Polyline polyline;
    private boolean isMapCentered = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        Configuration.getInstance().setUserAgentValue("com.example.androideindopdracht");
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, locationsListener);

        map = findViewById(R.id.osm_map);
        map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        map.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);
        map.setMultiTouchControls(true);
        map.setDestroyMode(false);
        map.getController().setZoom(17.5);

        userMarker = new Marker(map);
        userMarker.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.map_user_marker_norotation, null));
        userMarker.setInfoWindow(null);
        map.getOverlays().add(userMarker);

        if (DataClass.getInstance().isRunning()) {
            polyline = new Polyline();
            polyline.setTitle("running route");
            polyline.getOutlinePaint().setStrokeWidth(10f);
            polyline.getOutlinePaint().setColor(Color.RED);
            if (polyline != null) {
                polyline.setPoints(DataClass.getInstance().getCurrentRoute().getRoute());
            }
            map.getOverlayManager().add(polyline);
        }

        ImageButton zoomIn = findViewById(R.id.map_zoom_in);
        ImageButton zoomOut = findViewById(R.id.map_zoom_out);
        ImageButton recenterMap = findViewById(R.id.map_recenter);
        ImageButton home = findViewById(R.id.map_home_button);

        zoomIn.setOnClickListener(this);
        zoomOut.setOnClickListener(this);
        recenterMap.setOnClickListener(this);
        home.setOnClickListener(this);

        map.invalidate();
    }

    @Override
    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

    public void updateLocation(GeoPoint geoPoint, float speed) {
        location = geoPoint;
        if (isMapCentered){
            map.getController().setCenter(location);
        }
        userMarker.setPosition(geoPoint);

        if (DataClass.getInstance().isRunning()){
            DataClass.getInstance().getCurrentRoute().addGeoPoint(geoPoint);
            if (polyline != null) {
                updateLine(geoPoint);
            }
        }
//        Toast.makeText(this, "location Update", Toast.LENGTH_SHORT).show();
        map.invalidate();
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
                isMapCentered = !isMapCentered;
                break;
            case R.id.map_home_button:
                finish();
                break;
        }
    }

    public void updateLine(GeoPoint geo) {
        polyline.addPoint(geo);
        map.getOverlayManager().remove(polyline);
        map.getOverlayManager().add(polyline);
        map.invalidate();
    }
}