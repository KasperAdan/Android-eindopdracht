package com.example.androideindopdracht;

import java.util.ArrayList;

public class DataClass {

    private static DataClass instance;
    private boolean isRunning = false;
    private Route currentRoute;
    private ArrayList<Route> routeHistory;

    public static DataClass getInstance() {
        if(instance==null) {
            instance = new DataClass();
        }
        return instance;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        if (!this.isRunning && running) {
            currentRoute = new Route();
        }
        else if (this.isRunning && !running){
            currentRoute.setEndTime();
            routeHistory.add(currentRoute);
            currentRoute = null;
        }
        isRunning = running;
    }

    private DataClass() {
        routeHistory = new ArrayList<>();
    }

    public Route getCurrentRoute() {
        return currentRoute;
    }

    public ArrayList<Route> getRouteHistory() {
        return routeHistory;
    }
}
