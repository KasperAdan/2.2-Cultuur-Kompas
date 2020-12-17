package com.example.cultuurkompas.data.datamodel;

import java.util.List;

public class Route {
    private String name;
    private List<Waypoint> waypoints;
    private boolean finished;

    public Route(String name, List<Waypoint> waypoints) {
        this.name = name;
        this.waypoints = waypoints;
        finished = false;
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

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
