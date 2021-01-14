package com.example.cultuurkompas;

import android.content.res.Resources;

import com.example.cultuurkompas.data.datamodel.Waypoint;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class WaypointUnitTest {

    private List<Waypoint> waypoints;

    @Before
    public void createWaypoint(){

        waypoints = new ArrayList<>();
        waypoints.add(new Waypoint(1,"Stadhuis",51.588750,4.776112, "", null, null,false));
        waypoints.add(new Waypoint(2,"Antonius van Paduakerk",51.587638,4.777250, "",null, null, false));
        waypoints.add(new Waypoint(3,"Bibliotheek-nieuwe Veste",51.588000,4.778945, "", null, null,true));
        waypoints.add(new Waypoint(4,"Kloosterkazerne",51.587722,4.781028, "",null, null, true));
        waypoints.add(new Waypoint(5,"Chasse theater",51.587750,4.782000, "", null, null,false));
        waypoints.add(new Waypoint(6,"Binding van Isaac",51.588612,4.780888, "", null, null,false));
        waypoints.add(new Waypoint(7,"Beyerd",51.589667,4.781000, "", null, null,false));
    }

    @Test
    public void changeWaypointVisited(){
        Waypoint waypoint = waypoints.get(1);
        Boolean startingState = waypoint.isVisited();
        waypoint.setVisited(true);
        Boolean endingState = waypoint.isVisited();

        Assert.assertEquals(!startingState,endingState);
    }

    @Test
    public void isWaypointInReachStadhuis(){
        Waypoint waypoint = waypoints.get(0);
        GeoPoint waypointGeopoint = waypoint.getGeoPoint();
        GeoPoint userGeopoint = new GeoPoint(51.5888,4.7763);
        Boolean isInReach = false;

        if (userGeopoint.distanceToAsDouble(waypointGeopoint) <= 25){
            isInReach = true;
        }

        Assert.assertTrue(isInReach);
    }

    @Test
    public void isWaypointNotInReach(){
        Waypoint waypoint = waypoints.get(1);
        GeoPoint waypointGeopoint = waypoint.getGeoPoint();
        GeoPoint userGeopoint = new GeoPoint(51.5888,4.7763);
        Boolean isNotInReach = false;

        if (userGeopoint.distanceToAsDouble(waypointGeopoint) >= 25){
            isNotInReach = true;
        }

        Assert.assertTrue(isNotInReach);
    }
}
