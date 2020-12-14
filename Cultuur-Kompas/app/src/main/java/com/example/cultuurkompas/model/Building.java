package com.example.cultuurkompas.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Building implements Serializable {

    private String name;
    private String description;
    private String imgLink;
    private ArrayList<String> tags;
    private Boolean hasVisited;

    public Building(String name, String description, String imgLink, ArrayList<String> tags) {
        this.name = name;
        this.description = description;
        this.imgLink = imgLink;
        this.tags = tags;
        this.hasVisited = false;
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

    public ArrayList<String> getTags() {
        return tags;
    }

    public Boolean getHasVisited() {
        return hasVisited;
    }

    public void setHasVisited(Boolean hasVisited) {
        this.hasVisited = hasVisited;
    }
}
