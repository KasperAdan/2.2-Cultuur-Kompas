package com.example.cultuurkompas.interfaces;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cultuurkompas.R;
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
        /*"VVV beginpunt vanaf 2020 \r\n" +
                "Tel: - \r\n" +
                "Openingstijden: - \r\n" +
                "Website: https://www.Breda.nl \r\n"*/

        String shortRouteName = "Short route";
        List<Waypoint> waypointsShort = waypoints;
        Route routeShort = new Route(shortRouteName, waypointsShort, sharedPref.getBoolean(shortRouteName, false), sharedPref.getInt(shortRouteName + "progression", -1));

        String longRouteName = "Long route";
        List<Waypoint> waypointsLong = new ArrayList<>(waypoints);
        waypointsLong.addAll(waypoints);
        Route routeLong = new Route(longRouteName, waypointsLong, sharedPref.getBoolean(longRouteName, false), sharedPref.getInt(longRouteName + "progression", -1));


        routes = new ArrayList<>();
        routes.add(routeShort);
        routes.add(createHistoricRoute(sharedPref));
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
        for(Route route : routes) {
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

    public void EndActiveRoute() {
        for(Route route : routes){
            if(!route.isFinished()){
                resetRoute(route);
            }
        }
    }

    private Route createHistoricRoute(SharedPreferences sharedPref){
        waypoints = new ArrayList<>();
        waypoints.add(new Waypoint(1,"Stadhuis",51.588750,4.776112, "" + myActivity.getString(R.string.StadHuisInfo), "https://upload.wikimedia.org/wikipedia/commons/0/08/RM10176_Breda_-_Grote_Markt_38.jpg", null,sharedPref.getBoolean("waypoint" + 1, false)));
        waypoints.add(new Waypoint(2,"Antonius van Paduakerk",51.587638,4.777250, "" + myActivity.getString(R.string.AntoniusVanPaduakerkInfo),"https://upload.wikimedia.org/wikipedia/commons/3/37/P1000965copySint-Antoniuskathedraal.jpg", null, sharedPref.getBoolean("waypoint" + 2, false)));
        waypoints.add(new Waypoint(3,"Bibliotheek-nieuwe Veste",51.588000,4.778945, "" + myActivity.getString(R.string.BibliotheekNieuweVesteInfo), "https://www.architectuur.org/images/hertzberger04_2.jpg", null,sharedPref.getBoolean("waypoint" + 3, false)));
        waypoints.add(new Waypoint(4,"Kloosterkazerne",51.587722,4.781028, "" + myActivity.getString(R.string.KloosterkazerneInfo),"https://upload.wikimedia.org/wikipedia/commons/d/df/Breda_kloosterkazerne.jpg", null, sharedPref.getBoolean("waypoint" + 4, false)));
        waypoints.add(new Waypoint(5,"Chasse theater",51.587750,4.782000, "" + myActivity.getString(R.string.ChasseTheaterInfo), "https://media.indebuurt.nl/breda/2017/10/06002757/vacatures-in-breda-3-2.jpg", null,sharedPref.getBoolean("waypoint" + 5, false)));
        waypoints.add(new Waypoint(6,"Binding van Isaac",51.588612,4.780888, "" + myActivity.getString(R.string.BindingVanIsaacInfo), "https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Het_offer_van_Isaac_Menashe_Kadishman_Vlaszak_Breda.JPG/1200px-Het_offer_van_Isaac_Menashe_Kadishman_Vlaszak_Breda.JPG", null,sharedPref.getBoolean("waypoint" + 6, false)));
        waypoints.add(new Waypoint(7,"Beyerd",51.589667,4.781000, "" + myActivity.getString(R.string.BeyerdInfo), "https://media.indebuurt.nl/breda/2017/09/06004121/favorieten-van-cafe-de-beyerd-e1506679616907.jpg", null,sharedPref.getBoolean("waypoint" + 7, false)));
        waypoints.add(new Waypoint(8,"Gasthuispoort",51.589555,4.780000, "" + myActivity.getString(R.string.GasthuispoortInfo), "https://m.stappen-shoppen.nl/uploads/image/5b8fdcd72a5ab0bb55f28a1d-large.jpg", null,sharedPref.getBoolean("waypoint" + 8, false)));
        waypoints.add(new Waypoint(9,"Willem Merkxtuin",51.589112,4.777945, "" + myActivity.getString(R.string.WillemMerkxtuinInfo), "https://media.insiders.nl/thumbs/bre/square/files/image/willem-merkxtuin-breda-wv_2869557525.jpeg", null,sharedPref.getBoolean("waypoint" + 9, false)));
        waypoints.add(new Waypoint(10,"Begijnenhof",51.589695,4.778362, "" + myActivity.getString(R.string.BegijnenhofInfo), "https://www.seebreda.nl/wp-content/uploads/2019/05/begijnhof.jpg", null,sharedPref.getBoolean("waypoint" + 10, false)));
        waypoints.add(new Waypoint(11,"Halverwege stadswandeling",51.589500,4.776250, "" + myActivity.getString(R.string.Halverwegen), null, null,sharedPref.getBoolean("waypoint" + 11, false)));
        waypoints.add(new Waypoint(12,"Nassau Baronie Monument",51.592500,4.779695, "" + myActivity.getString(R.string.NassauBaronieMonumentInfo), "https://media-cdn.tripadvisor.com/media/photo-s/0a/81/d0/7b/nassau-baroniemonument.jpg", null,sharedPref.getBoolean("waypoint" + 12, false)));
        waypoints.add(new Waypoint(13,"The Light House",51.592833,4.778472, "" + myActivity.getString(R.string.TheLightHouseInfo), "https://kunstinbreda.files.wordpress.com/2012/09/academiesingel-1024.jpg", null,sharedPref.getBoolean("waypoint" + 13, false)));
        waypoints.add(new Waypoint(14,"Kasteel van Breda",51.590612,4.776167, "" + myActivity.getString(R.string.KasteelVanBredaInfo), "https://upload.wikimedia.org/wikipedia/commons/1/1a/Breda_kasteel.jpg", null,sharedPref.getBoolean("waypoint" + 14, false)));
        waypoints.add(new Waypoint(15,"Stadhouderspoort",51.589695,4.776138, "" + myActivity.getString(R.string.StadhouderspoortInfo), "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/0191Stadhouderspoort%2C_Kasteel_van_Breda.JPG/1200px-0191Stadhouderspoort%2C_Kasteel_van_Breda.JPG", null,sharedPref.getBoolean("waypoint" + 15, false)));
        waypoints.add(new Waypoint(16,"Huis van Brecht (rechter zijde)",51.590028,4.7743620, "" + myActivity.getString(R.string.HuisVanBrechtInfo), "https://erfgoed.breda.nl/upload/iblock/8e3/339.jpg", null,sharedPref.getBoolean("waypoint" + 16, false)));
        waypoints.add(new Waypoint(17,"Spanjaardsgat (rechter zijde)",51.590195,4.773445, "" + myActivity.getString(R.string.SpanjaardsgatInfo), "https://upload.wikimedia.org/wikipedia/commons/c/cd/Spanjaardsgat_2505_klein.jpg", null,sharedPref.getBoolean("waypoint" + 17, false)));
        waypoints.add(new Waypoint(18,"Begin Vismarkt",51.589833,4.773333, "" + myActivity.getString(R.string.BeginVismarktInfo), "https://upload.wikimedia.org/wikipedia/commons/f/f7/RM10195_Breda_-_Haven_22_%28bij%29.jpg", null,sharedPref.getBoolean("waypoint" + 18, false)));
        waypoints.add(new Waypoint(19,"Begin Havermarkt",51.589362,4.774445, "" + myActivity.getString(R.string.BeginHavermarktInfo), "https://upload.wikimedia.org/wikipedia/commons/5/52/P1000886Havermarkt.JPG", null,sharedPref.getBoolean("waypoint" + 19, false)));
        waypoints.add(new Waypoint(20,"Grote Kerk",51.588833,4.775278, "" + myActivity.getString(R.string.GroteKerkInfo), "https://api.omroepbrabant.nl/img/f/768/432/0.5/0.5/bWVkaWEvcHJvZC8yODc5MDE4LmpwZz9zdj0yMDE5LTA3LTA3JnN0PTIwMjAtMDUtMDFUMTYlM0E0MCUzQTAyWiZzZT0yMDQwLTA1LTAxVDE2JTNBNDAlM0EwMlomc3I9YiZzcD1yJnNpZz16JTJCMkluWTRsdlk4Z0lCNHl0SXIxd1AzR0ZZcTg1OER0cnFXc2xvOUV5VHMlM0Q=", null,sharedPref.getBoolean("waypoint" + 20, false)));
        waypoints.add(new Waypoint(21,"Het Poortje",51.588195,4.775138, "" + myActivity.getString(R.string.HetPoortjeInfo), "https://upload.wikimedia.org/wikipedia/commons/f/f5/Breda_Oude_Vest13.jpg", null,sharedPref.getBoolean("waypoint" + 21, false)));
        waypoints.add(new Waypoint(22,"Ridderstraat",51.587083,4.775750, "" + myActivity.getString(R.string.RidderstraatInfo), "https://media.indebuurt.nl/breda/2019/05/05200758/reigerstraat-1.jpg", null,sharedPref.getBoolean("waypoint" + 22, false)));
        waypoints.add(new Waypoint(23,"Grote Markt",51.587417,4.776555, "" + myActivity.getString(R.string.GroteMarktInfo), "https://d1ds1nqrpp2srf.cloudfront.net/photos/37299/Parc.terras.zondergevel_original.jpg?1515614752", null,sharedPref.getBoolean("waypoint" + 23, false)));
        waypoints.add(new Waypoint(24,"Bevrijdingsmonument",51.588028,4.776333, "" + myActivity.getString(R.string.BevrijdingsmonumentInfo), "https://www.tracesofwar.com/upload/6549190804164903.JPG", null,sharedPref.getBoolean("waypoint" + 24, false)));

        String normalRouteName = "Historic Route";
        return new Route(normalRouteName, new ArrayList<>(waypoints), sharedPref.getBoolean(normalRouteName, false), sharedPref.getInt(normalRouteName + "progression", -1),"https://2.bp.blogspot.com/-T84cLT0C0SA/U954Qf_026I/AAAAAAAAG5w/wZH1oQ-zPTs/s1600/collage+1.jpg");
    }
}
  