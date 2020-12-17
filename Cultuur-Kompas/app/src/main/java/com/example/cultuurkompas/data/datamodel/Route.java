package com.example.cultuurkompas.data.datamodel;

import java.io.Serializable;
import java.util.ArrayList;

public class Route implements Serializable {
    private String name;
    private ArrayList<Waypoint> route;

    public Route(String name, ArrayList<Waypoint> route) {
        this.name = name;
        this.route = route;
    }
}
