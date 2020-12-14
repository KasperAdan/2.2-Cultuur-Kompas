package com.example.cultuurkompas.data.datamodel;

import java.io.Serializable;

public class Waypoint implements Serializable {
    private int number;
    private String name;
    private double longitude;
    private double latitude;
    private String description;

    public Waypoint(int number, String name, double longitude, double latitude, String description) {
        this.number = number;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
    }
}
