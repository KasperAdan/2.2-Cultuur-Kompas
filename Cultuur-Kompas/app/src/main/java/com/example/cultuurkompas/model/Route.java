package com.example.cultuurkompas.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Route implements Serializable {

    private String name;
    private String info;
    private String description;
    private String imgLink;
    private ArrayList<Waypoint> waypoints;
    private ArrayList<String> tags;

    public Route(String name, String info, String description, String imgLink, ArrayList<Waypoint> waypoints, ArrayList<String> tags) {
        this.name = name;
        this.info = info;
        this.description = description;
        this.imgLink = imgLink;
        this.waypoints = waypoints;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgLink() {
        return imgLink;
    }

    public ArrayList<Waypoint> getWaypoints() {
        return waypoints;
    }

    public String getInfo() {
        return info;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
