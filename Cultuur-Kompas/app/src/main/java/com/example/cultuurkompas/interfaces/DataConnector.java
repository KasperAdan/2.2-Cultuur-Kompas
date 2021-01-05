package com.example.cultuurkompas.interfaces;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.cultuurkompas.data.datamodel.Route;
import com.example.cultuurkompas.data.datamodel.Waypoint;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataConnector {
    private static DataConnector single_instance = null;

    private List<Waypoint> waypoints;
    private List<Route> routes;
    private Activity myActivity;
    private GeoPoint lastKnownLocation;

    public static DataConnector getInstance()
    {
        if (single_instance == null)
            single_instance = new DataConnector();

        return single_instance;
    }

    private DataConnector() {
    }

    public GeoPoint getLastKnownLocation() { return this.lastKnownLocation; }
    public void setLastKnownLocation(GeoPoint location) { this.lastKnownLocation = location; }

    public void getAllData(Activity activity) {
        myActivity = activity;
        SharedPreferences sharedPref = myActivity.getPreferences(Context.MODE_PRIVATE);

        waypoints = new ArrayList<>();
        waypoints.add(new Waypoint(1,"Stadhuis",51.588750,4.776112, "VVV beginpunt vanaf 2020", null, null,sharedPref.getBoolean("waypoint" + 1, false)));
        waypoints.add(new Waypoint(2,"Antonius van Paduakerk",51.587638,4.777250, "St Janstraat",null, null, sharedPref.getBoolean("waypoint" + 2, false)));
        waypoints.add(new Waypoint(3,"Bibliotheel-nieuwe Veste",51.588000,4.778945, "", null, null,sharedPref.getBoolean("waypoint" + 3, false)));
        waypoints.add(new Waypoint(4,"Kloosterkazerne",51.587722,4.781028, "Huidige Casino",null, null, sharedPref.getBoolean("waypoint" + 4, false)));
        waypoints.add(new Waypoint(5,"Chasse theater",51.587750,4.782000, "", null, null,sharedPref.getBoolean("waypoint" + 5, false)));
        waypoints.add(new Waypoint(6,"Binding van Isaac",51.588612,4.780888, "Grasveld Vlaszak", null, null,sharedPref.getBoolean("waypoint" + 6, false)));
        waypoints.add(new Waypoint(7,"Beyerd",51.589667,4.781000, "", null, null,sharedPref.getBoolean("waypoint" + 7, false)));
        waypoints.add(new Waypoint(8,"Gasthuispoort",51.589555,4.780000, "", null, null,sharedPref.getBoolean("waypoint" + 8, false)));
        waypoints.add(new Waypoint(9,"Willem Merkxtuin",51.589112,4.777945, "Ingang Willem Merxtuin", null, null,sharedPref.getBoolean("waypoint" + 9, false)));
        waypoints.add(new Waypoint(10,"Begijnenhof",51.589695,4.778362, "Ingang Begijnenhof", null, null,sharedPref.getBoolean("waypoint" + 10, false)));
        waypoints.add(new Waypoint(11,"Halverwege stadswandeling",51.589500,4.776250, "Halverwege punt 2020", null, null,sharedPref.getBoolean("waypoint" + 11, false)));
        waypoints.add(new Waypoint(12,"Nassau Baronie Monument",51.592500,4.779695, "", null, null,sharedPref.getBoolean("waypoint" + 12, false)));
        waypoints.add(new Waypoint(13,"The Light House",51.592833,4.778472, "", null, null,sharedPref.getBoolean("waypoint" + 13, false)));
        waypoints.add(new Waypoint(14,"Kasteel van Breda",51.590612,4.776167, "Kasteelplein", null, null,sharedPref.getBoolean("waypoint" + 14, false)));
        waypoints.add(new Waypoint(15,"Stadhouderspoort",51.589695,4.776138, "", null, null,sharedPref.getBoolean("waypoint" + 15, false)));
        waypoints.add(new Waypoint(16,"Huis van Brecht (rechter zijde)",51.590028,4.7743620, "", null, null,sharedPref.getBoolean("waypoint" + 16, false)));
        waypoints.add(new Waypoint(17,"Spanjaardsgat (rechter zijde)",51.590195,4.773445, "", null, null,sharedPref.getBoolean("waypoint" + 17, false)));
        waypoints.add(new Waypoint(18,"Begin Vismarkt",51.589833,4.773333, "", null, null,sharedPref.getBoolean("waypoint" + 18, false)));
        waypoints.add(new Waypoint(19,"Begin Havermarkt",51.589362,4.774445, "", null, null,sharedPref.getBoolean("waypoint" + 19, false)));
        waypoints.add(new Waypoint(20,"Grote Kerk",51.588833,4.775278, "", null, null,sharedPref.getBoolean("waypoint" + 20, false)));
        waypoints.add(new Waypoint(21,"Het Poortje",51.588195,4.775138, "", null, null,sharedPref.getBoolean("waypoint" + 21, false)));
        waypoints.add(new Waypoint(22,"Ridderstraat",51.587083,4.775750, "", null, null,sharedPref.getBoolean("waypoint" + 22, false)));
        waypoints.add(new Waypoint(23,"Grote Markt",51.587417,4.776555, "Zuidpunt Grote Markt", null, null,sharedPref.getBoolean("waypoint" + 23, false)));
        waypoints.add(new Waypoint(24,"Bevrijdingsmonument",51.588028,4.776333, "", null, null,sharedPref.getBoolean("waypoint" + 24, false)));

        String normalRouteName = "Normal route";
        Route routeNormal = new Route(normalRouteName, new ArrayList<>(waypoints), sharedPref.getBoolean(normalRouteName, false), sharedPref.getInt(normalRouteName + "progression", -1));

        String shortRouteName = "Short route";
        List<Waypoint> waypointsShort = waypoints.subList(0, waypoints.size() / 2);
        Route routeShort = new Route(shortRouteName, waypointsShort, sharedPref.getBoolean(shortRouteName, false), sharedPref.getInt(shortRouteName + "progression", -1));

        String longRouteName = "Long route";
        List<Waypoint> waypointsLong = new ArrayList<>(waypoints);
        waypointsLong.addAll(waypoints);
        Route routeLong = new Route(longRouteName, waypointsLong, sharedPref.getBoolean(longRouteName, false), sharedPref.getInt(longRouteName + "progression", -1));


        routes = new ArrayList<>();
        routes.add(routeNormal);
        routes.add(routeShort);
        routes.add(routeLong);
    }

    public List<Waypoint> getWaypoints() { return this.waypoints; }
    public List<Route> getRoutes() { return this.routes; }

    public void reachedNewWaypoint(Route route){
        // Get the editor
        SharedPreferences sharedPref = myActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor builder = sharedPref.edit();

        // Update and save the waypoint
        route.getWaypoints().get(route.getProgressionCounter()).setVisited(true);
        builder.putBoolean("waypoint" + route.getWaypoints().get(route.getProgressionCounter()).getNumber(), true);

        // Update and save the route
        if(route.incrementProgressionCounter()) {
            route.setFinished(true);
            builder.putBoolean(route.getName(), true);
            route.resetRouteProgression();
        }
        builder.putInt(route.getName() + "progression", route.getProgressionCounter());

        // Apply changes
        builder.apply();
    }

    public void resetRoute(Route route){
        route.resetRouteProgression();
        route.setFinished(false);

        SharedPreferences sharedPref = myActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor builder = sharedPref.edit();

        builder.putInt(route.getName() + "progression", route.getProgressionCounter());
        builder.putBoolean(route.getName(), false);
        builder.apply();
    }

    // TESTING
    public void resetProgression(){
        SharedPreferences sharedPref = myActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor builder = sharedPref.edit();

        for (int i = 1; i <= waypoints.size(); i++){
            waypoints.get(i-1).setVisited(false);
            builder.putBoolean("waypoint" + i, false);
        }

        for(Route route : routes){
            route.setFinished(false);
            route.resetRouteProgression();
            builder.putBoolean(route.getName(), false);
            builder.putInt(route.getName() + "progression", -1);
        }

        builder.apply();
    }

    public void overrideWaypointVisitedState(int waypointNumber) {
        SharedPreferences sharedPref = myActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor builder = sharedPref.edit();

        waypoints.get(waypointNumber).setVisited(!waypoints.get(waypointNumber).isVisited());
        builder.putBoolean("waypoint" + (waypointNumber + 1), waypoints.get(waypointNumber).isVisited());

        builder.apply();
    }

    public Route getRouteWithName(String name) {
        for(com.example.cultuurkompas.data.datamodel.Route route : DataConnector.getInstance().getRoutes()) {
            if(route.getName().equals(name)) {
                return route;
            }
        }
        return null;
    }

    public void routeSetup(Route route) {
        route.incrementProgressionCounter();

        SharedPreferences sharedPref = myActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor builder = sharedPref.edit();

        builder.putInt(route.getName() + "progression", route.getProgressionCounter());
        builder.apply();
    }
}
