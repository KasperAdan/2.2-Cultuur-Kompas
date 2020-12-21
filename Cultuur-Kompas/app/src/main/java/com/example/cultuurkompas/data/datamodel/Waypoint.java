package com.example.cultuurkompas.data.datamodel;

import org.osmdroid.util.GeoPoint;

public class Waypoint {
    private int number;
    private String name;
    private GeoPoint geoPoint;
    private String description;
    private boolean visited;

    public Waypoint(int number, String name, double longitude, double latitude, String description, boolean isVisited) {
        this.number = number;
        this.name = name;
        this.geoPoint = new GeoPoint(longitude, latitude);
        this.description = description;
        visited = isVisited;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
