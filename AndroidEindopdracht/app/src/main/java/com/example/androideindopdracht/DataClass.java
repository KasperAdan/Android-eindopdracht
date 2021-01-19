package com.example.androideindopdracht;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Polyline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class DataClass {
    private final String fileName = "runBoyRunData.txt";

    private static DataClass instance;
    private boolean isRunning = false;
    private Route currentRoute;
    private ArrayList<Route> routeHistory;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;

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

    public void readData() throws IOException, JSONException {
        File file = new File(context.getFilesDir(), fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null){
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        String json = stringBuilder.toString();

        ArrayList<Route> routes = new ArrayList<>();
        JSONObject jsonObject  = new JSONObject(json);

        int size = (int) jsonObject.get("historyCount");
        JSONArray array = (JSONArray) jsonObject.get("history");
        for (int i = 0; i < size; i++) {
            JSONObject obj = (JSONObject) array.get(i);
            Route route = new Route(
                    new Date((Long) obj.get("startDate")),
                    new Date((Long) obj.get("endDate")),
                    (double)obj.get("distance")
            );
            routes.add(route);
        }
        routeHistory = routes;
    }

    public void writeData() throws JSONException, IOException {
        JSONArray array = new JSONArray();
        for (Route route : routeHistory) {
            JSONObject obj = new JSONObject();
            obj.put("startDate", route.getDate().getTime());
            obj.put("endDate", route.getEndDate().getTime());
            obj.put("distance", route.getDistance());

            array.put(obj);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("historyCount", routeHistory.size());
        jsonObject.put("history", array);

        // Convert JsonObject to String Format
        String userString = jsonObject.toString();
        // Define the File Path and its Name
        File file = new File(context.getFilesDir(), fileName);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();
    }
}
