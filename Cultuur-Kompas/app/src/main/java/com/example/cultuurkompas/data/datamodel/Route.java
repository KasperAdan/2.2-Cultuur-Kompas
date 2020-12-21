package com.example.cultuurkompas.data.datamodel;

import java.util.List;

public class Route {
    private String name;
    private List<Waypoint> waypoints;
    private int progressionCounter;
    private boolean finished;

    public Route(String name, List<Waypoint> waypoints, boolean isFinished, int progression) {
        this.name = name;
        this.waypoints = waypoints;
        finished = isFinished;
        progressionCounter = progression;
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

//    public void reachedNewWaypoint() {
//        waypoints.get(progressionCounter).setVisited(true);
//        progressionCounter++;
//        if(progressionCounter > waypoints.size()){
//            finished = true;
//        }
//    }
}
