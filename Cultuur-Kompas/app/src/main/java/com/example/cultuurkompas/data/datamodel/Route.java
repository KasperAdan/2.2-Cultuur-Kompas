package com.example.cultuurkompas.data.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Route implements Serializable {
    private String name;;
    private String info;
    private String description;
    private String imgLink;
    private List<Waypoint> route;
    private List<String> tags;
    private boolean finished;

    public Route(String name, List<Waypoint> route) {
        this.name = name;
        this.route = route;
        finished = false;
    }

    public Route(String name, String info, String description, String imgLink, List<Waypoint> route, List<String> tags) {
        this.name = name;
        this.info = info;
        this.description = description;
        this.imgLink = imgLink;
        this.route = route;
        this.tags = tags;
        this.finished = false;
    }

    public String getName() {
        return name;
    }

    public List<Waypoint> getRoute() {
        return route;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getInfo() {
        return info;
    }

    public String getDescription() {
        return description;
    }

    public String getImgLink() {
        return imgLink;
    }

    public List<String> getTags() {
        return tags;
    }
}
