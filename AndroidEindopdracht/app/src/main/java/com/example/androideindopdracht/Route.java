package com.example.androideindopdracht;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Date;

public class Route {
    private Date date;
    private ArrayList<GeoPoint> route;

    public Route() {
        route = new ArrayList<>();
        date = new Date();
    }

    public void addGeoPoint(GeoPoint geoPoint) {
        route.add(geoPoint);
    }

    public ArrayList<GeoPoint> getRoute() {
        return route;
    }

    public Date getDate() {
        return date;
    }
}
