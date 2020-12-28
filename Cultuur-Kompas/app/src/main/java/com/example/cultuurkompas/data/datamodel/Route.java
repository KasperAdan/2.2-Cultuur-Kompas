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
        this.info ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi interdum nibh accumsan lacinia placerat. Nulla consectetur pulvinar diam, eu luctus leo. ";
        this.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi interdum nibh accumsan lacinia placerat. Nulla consectetur pulvinar diam, eu luctus leo. Nullam varius neque urna, ut laoreet tortor posuere ut. Donec ac leo lorem. Mauris efficitur porta est nec condimentum. Aliquam nisi libero, fermentum eget semper vitae, fringilla ut justo. Aenean cursus, dui in rhoncus gravida, nisi nisl tempus odio, ac feugiat sem massa ac sem. ";
        this.imgLink="https://photoeditor.polarr.co/img/onboarding/02_01_preview_2x.jpg";
        this.tags = new ArrayList<>();
        tags.add("tag 1");
        tags.add("tag 2");
        tags.add("tag 3");
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
