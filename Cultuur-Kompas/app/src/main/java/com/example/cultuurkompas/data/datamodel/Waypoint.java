package com.example.cultuurkompas.data.datamodel;

import org.osmdroid.util.GeoPoint;

import java.io.Serializable;
import java.util.ArrayList;

public class Waypoint implements Serializable {
    private int number;
    private String name;
    private GeoPoint geoPoint;
    private String imgLink;
    private ArrayList<String> tags;
    private String description;
    private boolean visited;


    public Waypoint(int number, String name, double longitude, double latitude, String description, String imgLink, ArrayList<String> tags) {
        this.number = number;
        this.name = name;
        this.geoPoint = new GeoPoint(longitude, latitude);
        this.description = description;
        this.imgLink = imgLink;
        this.tags = tags;
        visited = false;
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

    public String getImgLink() {
        return imgLink;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
