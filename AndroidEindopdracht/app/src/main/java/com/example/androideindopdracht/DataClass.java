package com.example.androideindopdracht;

public class DataClass {

    private static DataClass instance;
    private boolean isRunning = false;

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
        isRunning = running;
    }

    private DataClass() {

    }
}
