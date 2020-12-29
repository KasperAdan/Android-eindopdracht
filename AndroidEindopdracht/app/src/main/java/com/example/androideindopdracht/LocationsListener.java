package com.example.androideindopdracht;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;

import org.osmdroid.util.GeoPoint;

public class LocationsListener implements LocationListener {
    private MapViewActivity mapView;

    public LocationsListener(MapViewActivity mapView) {
        this.mapView = mapView;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        mapView.updateLocation(new GeoPoint(location.getLatitude(), location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}
