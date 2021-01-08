package com.example.androideindopdracht;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Date;

public class Route {
    private Date date;
    private Date endDate;
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

    public void setEndTime(){
        this.endDate = new Date();
    }

    public Date getEndDate() {
        return endDate;
    }
}
