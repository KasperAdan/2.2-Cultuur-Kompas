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


    public Waypoint(int number, String name, double longitude, double latitude, String description, String imgLink, ArrayList<String> tags, boolean isVisited) {
        this.number = number;
        this.name = name;
        this.geoPoint = new GeoPoint(longitude, latitude);
        this.description = description;
        this.imgLink = "https://photoeditor.polarr.co/img/onboarding/02_01_preview_2x.jpg";
        this.tags = new ArrayList<>();
        this.tags.add("tag 1");
        this.tags.add("tag 2");
        this.tags.add("tag 3");
        visited = false;
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

    public String getImgLink() {
        return imgLink;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
