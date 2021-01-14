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

    public Route(String name, List<Waypoint> waypoints, boolean isFinished, int progression, String imgLink) {
        this.name = name;
        this.waypoints = waypoints;
        finished = isFinished;
        progressionCounter = progression;
        this.info ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi interdum nibh accumsan lacinia placerat. Nulla consectetur pulvinar diam, eu luctus leo. ";
        this.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi interdum nibh accumsan lacinia placerat. Nulla consectetur pulvinar diam, eu luctus leo. Nullam varius neque urna, ut laoreet tortor posuere ut. Donec ac leo lorem. Mauris efficitur porta est nec condimentum. Aliquam nisi libero, fermentum eget semper vitae, fringilla ut justo. Aenean cursus, dui in rhoncus gravida, nisi nisl tempus odio, ac feugiat sem massa ac sem. ";
        this.imgLink=checkImageLink(imgLink);
        this.tags = new ArrayList<>();
        tags.add("tag 1");
        tags.add("tag 2");
        tags.add("tag 3");
    }

    private String checkImageLink(String imgLink){
        if (imgLink == null){
            return "https://images.unsplash.com/photo-1483213097419-365e22f0f258?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1350&q=80";
        }else if(imgLink != null && imgLink.equals("")){
            return "https://images.unsplash.com/photo-1483213097419-365e22f0f258?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1350&q=80";

        }else {
            return imgLink;
        }
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

    public void checkFinished(){
        int finishedCounter = 0;
        for (Waypoint waypoint: waypoints){
            if (waypoint.isVisited()){
                finishedCounter ++;
            }
        }

        if (progressionCounter == finishedCounter){
            finished = true;
        }
    }

    public int getProgressionCounter() { return progressionCounter; }

    public void resetRouteProgression() {
        progressionCounter = -1;
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
