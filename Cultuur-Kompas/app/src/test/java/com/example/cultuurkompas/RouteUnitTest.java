package com.example.cultuurkompas;

import com.example.cultuurkompas.data.datamodel.Route;
import com.example.cultuurkompas.data.datamodel.Waypoint;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RouteUnitTest {

    private Route route;
    private List<Waypoint> waypoints;

    @Before
    public void createRoute(){
        waypoints = new ArrayList<>();
        waypoints.add(new Waypoint(1,"Stadhuis",51.588750,4.776112, "", null, null,false));
        waypoints.add(new Waypoint(2,"Antonius van Paduakerk",51.587638,4.777250, "",null, null, false));
        waypoints.add(new Waypoint(3,"Bibliotheek-nieuwe Veste",51.588000,4.778945, "", null, null,true));
        waypoints.add(new Waypoint(4,"Kloosterkazerne",51.587722,4.781028, "",null, null, true));
        waypoints.add(new Waypoint(5,"Chasse theater",51.587750,4.782000, "", null, null,false));
        waypoints.add(new Waypoint(6,"Binding van Isaac",51.588612,4.780888, "", null, null,false));
        waypoints.add(new Waypoint(7,"Beyerd",51.589667,4.781000, "", null, null,false));

        route = new Route("TestRoute",waypoints,false,0,null);
    }

    @Test
    public void testRouteProgression(){
        //Test as if the user walks to the waypoints.
        for (Waypoint waypoint : waypoints){
            route.incrementProgressionCounter();
        }
        Assert.assertEquals(route.getWaypoints().size(),route.getProgressionCounter());
    }

    @Test
    public void testRouteNotFinished(){
        route.checkFinished();
        Boolean test = route.isFinished();

        Assert.assertTrue(!test);
    }

    @Test
    public void testRouteFinished(){
        for (Waypoint wp: waypoints) {
            wp.setVisited(true);
            route.incrementProgressionCounter();
        }

        route.checkFinished();
        Boolean test = route.isFinished();

        Assert.assertTrue(test);
    }



}
