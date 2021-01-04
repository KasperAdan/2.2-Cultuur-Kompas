package com.example.cultuurkompas.data.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Route implements Serializable {
    private String name;;
    private String info;
    private String description;
    private String imgLink;
    private List<Waypoint> waypoints;
    private int progressionCounter;
    private List<String> tags;
    private boolean finished;

    public Route(String name, List<Waypoint> waypoints, boolean isFinished, int progression) {
        this.name = name;
        this.waypoints = waypoints;
        finished = isFinished;
        progressionCounter = progression;
        finished = false;
        this.info ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi interdum nibh accumsan lacinia placerat. Nulla consectetur pulvinar diam, eu luctus leo. ";
        this.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi interdum nibh accumsan lacinia placerat. Nulla consectetur pulvinar diam, eu luctus leo. Nullam varius neque urna, ut laoreet tortor posuere ut. Donec ac leo lorem. Mauris efficitur porta est nec condimentum. Aliquam nisi libero, fermentum eget semper vitae, fringilla ut justo. Aenean cursus, dui in rhoncus gravida, nisi nisl tempus odio, ac feugiat sem massa ac sem. ";
        this.imgLink="https://photoeditor.polarr.co/img/onboarding/02_01_preview_2x.jpg";
        this.tags = new ArrayList<>();
        tags.add("tag 1");
        tags.add("tag 2");
        tags.add("tag 3");
    }

    public Route(String name, String info, String description, String imgLink, List<Waypoint> waypoints, List<String> tags) {
        this.name = name;
        this.info = info;
        this.description = description;
        this.imgLink = imgLink;
        this.waypoints = waypoints;
        this.tags = tags;
        this.finished = false;
    }

    public String getName() {
        return name;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean state) { finished = state; }

    public int getProgressionCounter() { return progressionCounter; }

    public void resetRouteProgression() {
        progressionCounter = 0;
    }

    public boolean incrementProgressionCounter() {
        progressionCounter++;
        if(progressionCounter >= waypoints.size()){
            return true;
        }
        return false;
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
