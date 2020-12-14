package com.example.cultuurkompas.data;

import android.content.Context;
import android.util.Log;

import com.example.cultuurkompas.data.datamodel.Route;
import com.example.cultuurkompas.data.datamodel.Waypoint;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class GenerateJSON {
    private Context context;

    public GenerateJSON(Context context) {
        this.context = context;
        ArrayList<Waypoint> waypoints = new ArrayList<>();
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


        Route route = new Route("route1", waypoints);

        Gson gson = new Gson();

        String json = gson.toJson(route);

        writeToFile(json, this.context);
    }

    private void writeToFile(String data, Context context) {
        try {
            File path = context.getFilesDir();
            File file = new File(path,"routesJSON.txt");
            Log.e("Path", path.toString());
            FileOutputStream stream = new FileOutputStream(file);
            try {
                stream.write("text-to-write".getBytes());
            } finally {
                stream.close();
            }
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
