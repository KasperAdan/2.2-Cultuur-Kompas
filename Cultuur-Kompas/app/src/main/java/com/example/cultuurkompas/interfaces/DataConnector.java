package com.example.cultuurkompas.interfaces;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.cultuurkompas.data.datamodel.Route;
import com.example.cultuurkompas.data.datamodel.Waypoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataConnector {
    private static DataConnector single_instance = null;

    private List<Waypoint> waypoints;
    private List<Route> routes;
    private Activity myActivity;

    public static DataConnector getInstance()
    {
        if (single_instance == null)
            single_instance = new DataConnector();

        return single_instance;
    }

    private DataConnector() {

    }

    public void getAllData(Activity activity) {
        myActivity = activity;
        SharedPreferences sharedPref = myActivity.getPreferences(Context.MODE_PRIVATE);

        waypoints = new ArrayList<>();
        waypoints.add(new Waypoint(1,"Stadhuis",51.588750,4.776112, "VVV beginpunt vanaf 2020 \r\n" +
                "Tel: - \r\n" +
                "Openingstijden: - \r\n" +
                "Website: https://www.Breda.nl \r\n", null, null,sharedPref.getBoolean("waypoint" + 1, false)));
        waypoints.add(new Waypoint(2,"Antonius van Paduakerk",51.587638,4.777250, "St Janstraat \r\n" +
                "Tel: 076 521 5764 \r\n" +
                "Openingstijden: - \r\n" +
                "Website:https://parochiebredacentrum.bisdomvanbreda.nl/",null, null, sharedPref.getBoolean("waypoint" + 2, false)));
        waypoints.add(new Waypoint(3,"Bibliotheek-nieuwe Veste",51.588000,4.778945, "Tel: 076 200 3200 \r\n" +
                "Openingstijden: Ma\t8:30–22:00\n" +
                "Di\t8:30–22:00\n" +
                "Wo\t8:30–22:00\n" +
                "Do\t8:30–22:00\n" +
                "Vr\t8:30–22:00\n" +
                "Za\t9:00–17:00\n" +
                "Zo\t12:00–17:00 \r\n" +
                "Website: https://www.bibliotheekbreda.nl/iguana/www.main.cls", null, null,sharedPref.getBoolean("waypoint" + 3, false)));
        waypoints.add(new Waypoint(4,"Kloosterkazerne",51.587722,4.781028, "Huidige Casino \r\n" +
                "Tel: - \r\n" +
                "Openingstijden: Altijd geopend \r\n" +
                "Website: https://www.hollandcasino.nl/en/vestigingen/breda",null, null, sharedPref.getBoolean("waypoint" + 4, false)));
        waypoints.add(new Waypoint(5,"Chasse theater",51.587750,4.782000, "Tel: 076 530 3132 \r\n" +
                "Openingstijden: - \r\n" +
                "Website: https://www.chasse.nl/ ", null, null,sharedPref.getBoolean("waypoint" + 5, false)));
        waypoints.add(new Waypoint(6,"Binding van Isaac",51.588612,4.780888, "Grasveld Vlaszak \r\n" +
                "Tel: - \r\n" +
                "Openingstijden: - \r\n" +
                "Website: - ", null, null,sharedPref.getBoolean("waypoint" + 6, false)));
        waypoints.add(new Waypoint(7,"Beyerd",51.589667,4.781000, "Tel: 076 521 4265 \r\n" +
                "Openingstijden: \r\n" +
                "Ma\t12:00 - 18:00\n" +
                "Di\t12:00 - 18:00\n" +
                "Wo\tGesloten\t\n" +
                "Do\t12:00 - 18:00\n" +
                "Vr\t12:00 - 20:00\n" +
                "Za\t12:00 - 20:00\n" +
                "Zo\t12:00 - 18:00 \r\n" +
                "Website: https://www.beyerd.nl/", null, null,sharedPref.getBoolean("waypoint" + 7, false)));
        waypoints.add(new Waypoint(8,"Gasthuispoort",51.589555,4.780000, "Tel: - \r\n" +
                "Openingstijden: - \r\n" +
                "Website: -", null, null,sharedPref.getBoolean("waypoint" + 8, false)));
        waypoints.add(new Waypoint(9,"Willem Merkxtuin",51.589112,4.777945, "Ingang Willem Merxtuin \r\n" +
                "Tel: - \r\n" +
                "Openingstijden: \r\n" +
                "Ma 8:00-16:00\n" +
                "Di\t8:00-16:00\n" +
                "Wo\t8:00-16:00\n" +
                "Do\t8:00-16:00\n" +
                "Vr\t8:00-16:00\n" +
                "Za\t11:00-17:00\n" +
                "Zo\t11:00-17:00 \r\n" +
                "Website: -", null, null,sharedPref.getBoolean("waypoint" + 9, false)));
        waypoints.add(new Waypoint(10,"Begijnenhof",51.589695,4.778362, "Ingang Begijnenhof \r\n" +
                "Tel: 076 521 1276 \r\n" +
                "Openingstijden: \r\n" +
                "Ma 9:00-18:00\n" +
                "Di 9:00-18:00\n" +
                "Wo 9:00-18:00\n" +
                "Do 9:00-18:00\n" +
                "Vr 9:00-18:00\n" +
                "Za 9:00-18:00\n" +
                "Zo 9:00-18:00 \r\n" +
                "Website: https://www.begijnhofbreda.nl/", null, null,sharedPref.getBoolean("waypoint" + 10, false)));
        waypoints.add(new Waypoint(11,"Halverwege stadswandeling",51.589500,4.776250, "Halverwege punt 2020", null, null,sharedPref.getBoolean("waypoint" + 11, false)));
        waypoints.add(new Waypoint(12,"Nassau Baronie Monument",51.592500,4.779695, "Tel: - \r\n" +
                "Openingstijden: - \r\n" +
                "Website: https://www.bredanassaustad.nl/monument/nassau-monument-valkenberg", null, null,sharedPref.getBoolean("waypoint" + 12, false)));
        waypoints.add(new Waypoint(13,"The Light House",51.592833,4.778472, "Tel: 076 514 1057 \r\n" +
                "Openingstijden: \r\n" +
                "Ma\t9:00-18:00\n" +
                "Di\t9:00-18:00\n" +
                "Wo\t9:00-18:00\n" +
                "Do\t9:00-18:00\n" +
                "Vr\t9:00-18:00\n" +
                "Za\tClosed\n" +
                "Zo\tClosed \r\n" +
                "Website: -", null, null,sharedPref.getBoolean("waypoint" + 13, false)));
        waypoints.add(new Waypoint(14,"Kasteel van Breda",51.590612,4.776167, "Kasteelplein \r\n" +
                "Tel: 0900 5222444 \r\n" +
                "Openingstijden: - \r\n" +
                "Website: https://www.welkominbreda.nl/locaties/630891090/kasteel-van-breda?ins_rd=d3d3LnZ2dmJyZWRhLm5s", null, null,sharedPref.getBoolean("waypoint" + 14, false)));
        waypoints.add(new Waypoint(15,"Stadhouderspoort",51.589695,4.776138, "Tel: - \r\n" +
                "Openingstijden: Altijd zichtbaar \r\n" +
                "Website: - ", null, null,sharedPref.getBoolean("waypoint" + 15, false)));
        waypoints.add(new Waypoint(16,"Huis van Brecht (rechter zijde)",51.590028,4.7743620, "Tel: - \r\n" +
                "Openingstijden: Altijd zichtbaar \r\n" +
                "Website: - ", null, null,sharedPref.getBoolean("waypoint" + 16, false)));
        waypoints.add(new Waypoint(17,"Spanjaardsgat (rechter zijde)",51.590195,4.773445, "Tel: - \r\n" +
                "Openingstijden: Altijd zichtbaar \r\n" +
                "Website: - ", null, null,sharedPref.getBoolean("waypoint" + 17, false)));
        waypoints.add(new Waypoint(18,"Begin Vismarkt",51.589833,4.773333, "Tel: - \r\n" +
                "Openingstijden: Openingstijden verschillen \r\n" +
                "Website: - ", null, null,sharedPref.getBoolean("waypoint" + 18, false)));
        waypoints.add(new Waypoint(19,"Begin Havermarkt",51.589362,4.774445, "Tel: - \r\n" +
                "Openingstijden: Openingstijden verschillen \r\n" +
                "Website: - ", null, null,sharedPref.getBoolean("waypoint" + 19, false)));
        waypoints.add(new Waypoint(20,"Grote Kerk",51.588833,4.775278, "Tel: - \r\n" +
                "Openingstijden: \r\n" +
                "Ma\t10:00-17:00\n" +
                "Di\t10:00-17:00\n" +
                "Wo\t10:00-17:00\n" +
                "Do\t10:00-17:00\n" +
                "Vr\t10:00-17:00\n" +
                "Za\t10:00-17:00\n" +
                "Zo\t13:00-17:00\n", null, null,sharedPref.getBoolean("waypoint" + 20, false)));
        waypoints.add(new Waypoint(21,"Het Poortje",51.588195,4.775138, "", null, null,sharedPref.getBoolean("waypoint" + 21, false)));
        waypoints.add(new Waypoint(22,"Ridderstraat",51.587083,4.775750, "", null, null,sharedPref.getBoolean("waypoint" + 22, false)));
        waypoints.add(new Waypoint(23,"Grote Markt",51.587417,4.776555, "Zuidpunt Grote Markt", null, null,sharedPref.getBoolean("waypoint" + 23, false)));
        waypoints.add(new Waypoint(24,"Bevrijdingsmonument",51.588028,4.776333, "", null, null,sharedPref.getBoolean("waypoint" + 24, false)));

        String normalRouteName = "Normal route";
        Route routeNormal = new Route(normalRouteName, new ArrayList<>(waypoints), sharedPref.getBoolean(normalRouteName, false), sharedPref.getInt(normalRouteName + "progression", 0));

        String shortRouteName = "Short route";
        List<Waypoint> waypointsShort = waypoints.subList(0, waypoints.size() / 2);
        Route routeShort = new Route(shortRouteName, waypointsShort, sharedPref.getBoolean(shortRouteName, false), sharedPref.getInt(shortRouteName + "progression", 0));

        String longRouteName = "Long route";
        List<Waypoint> waypointsLong = new ArrayList<>(waypoints);
        waypointsLong.addAll(waypoints);
        Route routeLong = new Route(longRouteName, waypointsLong, sharedPref.getBoolean(longRouteName, false), sharedPref.getInt(longRouteName + "progression", 0));


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

        SharedPreferences sharedPref = myActivity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor builder = sharedPref.edit();

        builder.putInt(route.getName() + "progression", route.getProgressionCounter());
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
            builder.putInt(route.getName() + "progression", 0);
        }

        builder.apply();
    }
}
