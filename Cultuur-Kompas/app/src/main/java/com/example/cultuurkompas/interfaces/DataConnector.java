package com.example.cultuurkompas.interfaces;

import com.example.cultuurkompas.data.datamodel.Route;
import com.example.cultuurkompas.data.datamodel.Waypoint;

import java.util.ArrayList;
import java.util.List;

public class DataConnector {
    private static DataConnector single_instance = null;

    private List<Waypoint> waypoints;
    private List<Route> routes;

    public static DataConnector getInstance()
    {
        if (single_instance == null)
            single_instance = new DataConnector();

        return single_instance;
    }

    private DataConnector() {
        waypoints = new ArrayList<>();
        waypoints.add(new Waypoint(1,"Stadhuis",51.588750,4.776112, "VVV beginpunt vanaf 2020"));
        waypoints.add(new Waypoint(2,"tussen punt",51.587972,4.776362,"Terug naar begin Grote Markt"));
        waypoints.add(new Waypoint(3,"tussen punt",51.5875000,4.776555,"Zuidpunt Grote Markt"));
        waypoints.add(new Waypoint(4,"Antonius van Paduakerk",51.587638,4.777250, "St Janstraat"));
        waypoints.add(new Waypoint(5,"tussen punt",51.588278,4.778500,"Kruisingn St.Janstraat/Molenstraat"));
        waypoints.add(new Waypoint(6,"Bibliotheel-nieuwe Veste",51.588000,4.778945, ""));
        waypoints.add(new Waypoint(7,"tussen punt",51.587362,4.780222,"Kruising Molenstraat/Kloosterplein"));
        waypoints.add(new Waypoint(8,"Kloosterkazerne",51.587722,4.781028, "Huidige Casino"));
        waypoints.add(new Waypoint(9,"Chasse theater",51.587750,4.782000, ""));
        waypoints.add(new Waypoint(10,"tussen punt",51.587750,4.781250,"Kruising Kloosterplein/Vlaszak"));
        waypoints.add(new Waypoint(11,"Binding van Isaac",51.588612,4.780888, "Grasveld Vlaszak"));
        waypoints.add(new Waypoint(12,"tussen punt",51.589500,4.780445,"Kruising Vlaszak/Boschstraat"));
        waypoints.add(new Waypoint(13,"Beyerd",51.589667,4.781000, ""));
        waypoints.add(new Waypoint(14,"tussen punt",51.589500,4.780445,"Kruising Vlaszak/Boschstraat"));
        waypoints.add(new Waypoint(15,"Gasthuispoort",51.589555,4.780000, ""));
        waypoints.add(new Waypoint(16,"tussen punt",51.589417,4.779862,"Ingang Veemarktstraat"));
        waypoints.add(new Waypoint(17,"tussen punt",51.589028,4.779695,"1e bocht Veemarktstraat"));
        waypoints.add(new Waypoint(18,"tussen punt",51.588555,4.778333,"Kruising Veemarktstraat/St.Annastraat"));
        waypoints.add(new Waypoint(19,"Willem Merkxtuin",51.589112,4.777945, "Ingang Willem Merxtuin"));
        waypoints.add(new Waypoint(20,"tussen punt",51.589667,4.777805,"Kruising St.Annastraat/Catharinastraat"));
        waypoints.add(new Waypoint(21,"Begijnenhof",51.589695,4.778362, "Ingang Begijnenhof"));
        waypoints.add(new Waypoint(22,"tussen punt",51.589667,4.777805,"Kruising St.Annastraat/Catharinastraat"));
        waypoints.add(new Waypoint(23,"Halverwege stadswandeling",51.589500,4.776250, "Halverwege punt 2020"));
        waypoints.add(new Waypoint(24,"Nassau Baronie Monument",51.592500,4.779695, ""));
        waypoints.add(new Waypoint(25,"tussen punt",51.592500,4.779388,"Pad ten westen van monument"));
        waypoints.add(new Waypoint(26,"The Light House",51.592833,4.778472, ""));
        waypoints.add(new Waypoint(27,"tussen punt",51.592667,4.777917,"Halverwege park"));
        waypoints.add(new Waypoint(28,"tussen punt",51.590612,4.777000,"Einde park"));
        waypoints.add(new Waypoint(29,"Kasteel van Breda",51.590612,4.776167, "Kasteelplein"));
        waypoints.add(new Waypoint(30,"Stadhouderspoort",51.589695,4.776138, ""));
        waypoints.add(new Waypoint(31,"tussen punt",51.590333,4.776000,"Kruising Kasteelplein/Cingelstraat"));
        waypoints.add(new Waypoint(32,"tussen punt",51.590388,4.775000,"Bocht Cingelstraat"));
        waypoints.add(new Waypoint(33,"Huis van Brecht (rechter zijde)",51.590028,4.7743620, ""));
        waypoints.add(new Waypoint(34,"Spanjaardsgat (rechter zijde)",51.590195,4.773445, ""));
        waypoints.add(new Waypoint(35,"Begin Vismarkt",51.589833,4.773333, ""));
        waypoints.add(new Waypoint(36,"Begin Havermarkt",51.589362,4.774445, ""));
        waypoints.add(new Waypoint(37,"tussen punt",51.588778,4.774888,"Kruising Torenstraat/Kerkplein"));
        waypoints.add(new Waypoint(38,"Grote Kerk",51.588833,4.775278, ""));
        waypoints.add(new Waypoint(39,"tussen punt",51.588778,4.774888,"Kruising Torenstraat/Kerkplein"));
        waypoints.add(new Waypoint(40,"Het Poortje",51.588195,4.775138, ""));
        waypoints.add(new Waypoint(41,"Ridderstraat",51.587083,4.775750, ""));
        waypoints.add(new Waypoint(42,"Grote Markt",51.587417,4.776555, "Zuidpunt Grote Markt"));
        waypoints.add(new Waypoint(43,"Bevrijdingsmonument",51.588028,4.776333, ""));
=======
        waypoints = new ArrayList<>();
        waypoints.add(new Waypoint(1,"Stadhuis",0,0, "VVV beginpunt vanaf 2020"));
        waypoints.add(new Waypoint(2,"Antonius van Paduakerk",0,0, "St Janstraat"));
        waypoints.add(new Waypoint(3,"Bibliotheel-nieuwe Veste",0,0, ""));
        waypoints.add(new Waypoint(4,"Kloosterkazerne",0,0, "Huidige Casino"));
        waypoints.add(new Waypoint(5,"Chasse theater",0,0, ""));
        waypoints.add(new Waypoint(6,"Binding van Isaac",0,0, "Grasveld Vlaszak"));
        waypoints.add(new Waypoint(7,"Beyerd",0,0, ""));
        waypoints.add(new Waypoint(8,"Gasthuispoort",0,0, ""));
        waypoints.add(new Waypoint(9,"Willem Merkxtuin",0,0, "Ingang Willem Merxtuin"));
        waypoints.add(new Waypoint(10,"Begijnenhof",0,0, "Ingang Begijnenhof"));
        waypoints.add(new Waypoint(11,"Halverwege stadswandeling",0,0, "Halverwege punt 2020"));
        waypoints.add(new Waypoint(12,"Nassau Baronie Monument",0,0, ""));
        waypoints.add(new Waypoint(13,"The Light House",0,0, ""));
        waypoints.add(new Waypoint(14,"Kasteel van Breda",0,0, "Kasteelplein"));
        waypoints.add(new Waypoint(15,"Stadhouderspoort",0,0, ""));
        waypoints.add(new Waypoint(16,"Huis van Brecht (rechter zijde)",0,0, ""));
        waypoints.add(new Waypoint(17,"Spanjaardsgat (rechter zijde)",0,0, ""));
        waypoints.add(new Waypoint(18,"Begin Vismarkt",0,0, ""));
        waypoints.add(new Waypoint(19,"Begin Havermarkt",0,0, ""));
        waypoints.add(new Waypoint(20,"Grote Kerk",0,0, ""));
        waypoints.add(new Waypoint(21,"Het Poortje",0,0, ""));
        waypoints.add(new Waypoint(22,"Ridderstraat",0,0, ""));
        waypoints.add(new Waypoint(23,"Grote Markt",0,0, "Zuidpunt Grote Markt"));
        waypoints.add(new Waypoint(24,"Bevrijdingsmonument",0,0, ""));
>>>>>>> Stashed changes


        Route routeNormal = new Route("Normal route", waypoints);

        List<Waypoint> waypointsShort = waypoints.subList(0, waypoints.size() / 2);
        Route routeShort = new Route("Short route", waypointsShort);

        List<Waypoint> waypointsLong = new ArrayList<>();
        waypointsLong.addAll(waypoints);
        waypointsLong.addAll(waypoints);
        Route routeLong = new Route("Long route", waypointsLong);

        routes = new ArrayList<>();
        routes.add(routeNormal);
        routes.add(routeShort);
        routes.add(routeLong);
    }

    public List<Waypoint> getWaypoints() { return this.waypoints; }
    public List<Route> getRoutes() { return this.routes; }
}
