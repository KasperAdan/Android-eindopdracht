package com.example.androideindopdracht;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public class Route {
    private ArrayList<GeoPoint> route;

    public Route() {
        route = new ArrayList<>();
    }

    public void addGeoPoint(GeoPoint geoPoint) {
        route.add(geoPoint);
    }

    public ArrayList<GeoPoint> getRoute() {
        return route;
    }
}
