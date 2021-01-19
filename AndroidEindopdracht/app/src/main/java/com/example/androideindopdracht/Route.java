package com.example.androideindopdracht;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;
import java.util.Date;

public class Route {
    private Date date;
    private Date endDate;
    private ArrayList<GeoPoint> route;
    private double distance;

    public Route() {
        route = new ArrayList<>();
        date = new Date();
    }

    public Route(Date startDate, Date endDate, double distance) {
        this.date = startDate;
        this.endDate = endDate;
        this.distance = distance;
    }

    public void addGeoPoint(GeoPoint geoPoint) {
        route.add(geoPoint);
    }

    public double getDistance() {
        return distance;
    }

    public ArrayList<GeoPoint> getRoute() {
        return route;
    }

    public Date getDate() {
        return date;
    }

    public void setEndTime(){
        Polyline line = new Polyline();
        line.setPoints(route);
        distance = line.getDistance();

        this.endDate = new Date();
    }

    public Date getEndDate() {
        return endDate;
    }
}
