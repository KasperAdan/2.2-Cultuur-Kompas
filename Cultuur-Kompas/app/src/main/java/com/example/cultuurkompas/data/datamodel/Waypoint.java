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
        this.imgLink = checkImageLink(imgLink);
        this.tags = new ArrayList<>();
        this.tags.add("tag 1");
        this.tags.add("tag 2");
        this.tags.add("tag 3");
        visited = false;
        visited = isVisited;
    }

    private String checkImageLink(String imgLink){
        if (imgLink == null){
            return "https://images.unsplash.com/photo-1470219556762-1771e7f9427d?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1489&q=80";
        }else if(imgLink != null && imgLink.equals("")){
            return "https://images.unsplash.com/photo-1470219556762-1771e7f9427d?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1489&q=80";

        }else {
            return imgLink;
        }
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
