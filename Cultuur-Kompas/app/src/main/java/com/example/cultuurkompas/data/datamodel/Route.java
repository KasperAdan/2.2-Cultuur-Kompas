package com.example.cultuurkompas.data.datamodel;

import java.util.List;

public class Route {
    private String name;
    private List<Waypoint> route;
    private boolean finished;

    public Route(String name, List<Waypoint> route) {
        this.name = name;
        this.route = route;
        finished = false;
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
}
