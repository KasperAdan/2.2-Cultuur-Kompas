package com.example.cultuurkompas.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.data.datamodel.Waypoint;
import com.example.cultuurkompas.interfaces.DataConnector;
import com.example.cultuurkompas.interfaces.MyLocationListener;
import com.example.cultuurkompas.viewmodel.OpenRouteServiceConnection;
import com.example.cultuurkompas.viewmodel.orsdata.Route;
import com.example.cultuurkompas.viewmodel.orsdata.TravelType;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class MapScreenActivity extends AppCompatActivity {

    private MapView mapView = null;
    private IMapController mapController;
    private GeoPoint cityGeoPoint;
    private Route route;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private Marker marker;
    private boolean finished = false;
    private List<GeoPoint> routeGeoPoints;
    private GeoPoint myLocation;
//    private List<Waypoint> selectedRoute;
    private com.example.cultuurkompas.data.datamodel.Route selectedRoute;
    private Polyline line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);

        Configuration.getInstance().setUserAgentValue("com.example.cultuurkompas");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

        DataConnector.getInstance().getAllData(this);
        // Enable this to reset all progress
//        DataConnector.getInstance().resetProgression();

        cityGeoPoint = new GeoPoint(51.589457,4.777006);

        mapView = findViewById(R.id.osm_map);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setDestroyMode(false);
        mapView.setTag("mapView");
        mapView.setMultiTouchControls(true);
        marker = new Marker(mapView);
        marker.setPosition(cityGeoPoint);
        marker.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_my_location, null));
        mapView.getOverlays().add(marker);
        mapView.setBuiltInZoomControls(false);
        mapController = mapView.getController();
        mapController.setZoom(17.5);
        mapController.setCenter(cityGeoPoint);
    }

    public void onButtonCurrentLocationClick(View view){
        Toast.makeText(this,"CURRENT LOCATION", Toast.LENGTH_LONG).show();
        if(myLocation != null) {
            mapController.setCenter(myLocation);
        }

        // TESTING
        if(selectedRoute != null) {
            reachedWaypoint();
        }
    }

    public void onButtonHelpMapClick(View view){
        Toast.makeText(this,"HELP!", Toast.LENGTH_LONG).show();
    }

    public void onButtonBuildingMapClick(View view){
        Toast.makeText(this,"BUILDINGS", Toast.LENGTH_LONG).show();
        Intent i = new Intent(MapScreenActivity.this, BuildingScreenActivity.class);
        startActivity(i);
    }

    public void onButtonRouteMapClick(View view){
        Toast.makeText(this,"ROUTES", Toast.LENGTH_LONG).show();
        Intent i = new Intent(MapScreenActivity.this, RouteScreenActivity.class);
        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        mapView.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        mapView.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }


    public Polyline drawLine(List<GeoPoint> geoPoints){
        Polyline line = new Polyline();
        line.setTitle("Road back home");
        line.setSubDescription(Polyline.class.getCanonicalName());
        //line.setWidth(20f);
        line.getOutlinePaint().setStrokeWidth(10f);
        line.getOutlinePaint().setColor(Color.RED);
        line.setPoints(geoPoints);
        line.setGeodesic(true);
        line.setInfoWindow(new BasicInfoWindow(R.layout.bonuspack_bubble, mapView));
        line.setOnClickListener(new Polyline.OnClickListener() {
            @Override
            public boolean onClick(Polyline polyline, MapView mapView, GeoPoint eventPos) {
                Toast.makeText(getApplicationContext(),"WAT KLIK JE",Toast.LENGTH_LONG).show();
                return false;
            }
        });

        return line;
    }

    public void locationChanged(GeoPoint geoPoint){
        myLocation = geoPoint;
        marker.setPosition(geoPoint);
        mapView.invalidate();

        if(selectedRoute == null){
            // TESTING
            startRoute(DataConnector.getInstance().getRoutes().get(0));
            
//            for(com.example.cultuurkompas.data.datamodel.Route route : DataConnector.getInstance().getRoutes()){
//                if(route.getProgressionCounter() > 0){
//                    startRoute(route);
//                }
//            }
        }

    }

    public void startRoute(com.example.cultuurkompas.data.datamodel.Route route){
//        selectedRoute = new ArrayList<>();
//        selectedRoute.addAll(route.getWaypoints().subList(route.getProgressionCounter(), route.getWaypoints().size()));
        selectedRoute = route;

        if(myLocation != null) {
            //getDirectionsToNextWaypoint(selectedRoute.getWaypoints().get(selectedRoute.getProgressionCounter()));
        }
        mapView.invalidate();
    }

    private void reachedWaypoint(){
        if(line != null) {
            mapView.getOverlayManager().remove(line);
            mapView.invalidate();
        }
        if(selectedRoute != null){
            DataConnector.getInstance().reachedNewWaypoint(selectedRoute);
            if(!selectedRoute.isFinished()){
                //getDirectionsToNextWaypoint(selectedRoute.getWaypoints().get(selectedRoute.getProgressionCounter()));
            }
//            selectedRoute.remove(0);
//            if(selectedRoute.size() > 0) {
//                getDirectionsToNextWaypoint(selectedRoute.get(0));
//            }
        }
    }

    private void getDirections(List<Waypoint> waypoints) {
        List<GeoPoint> geoPoints = new ArrayList<>();
        for(Waypoint waypoint : waypoints) {
            geoPoints.add(waypoint.getGeoPoint());
        }

        routeGeoPoints = Collections.synchronizedList(new ArrayList<GeoPoint>());

        for(int i = 0; i < geoPoints.size() - 1; i++) {
            while(!finished && i != 0){}
            finished = false;
            OpenRouteServiceConnection.getInstance().getRouteInfo("5b3ce3597851110001cf62488c97c6c701f64827afad2deda82ec4da", geoPoints.get(i), geoPoints.get(i+1), TravelType.FOOT_WALKING, new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    //TODO handle error
                    Log.e("MAP", "ERROR on route response");
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    Log.d("MAP", response.toString());

                    JSONObject responseJson = null;
                    try {
                        synchronized (routeGeoPoints) {
                            responseJson = new JSONObject(response.body().string());
                            route = new Route(responseJson);
                            ArrayList<double[]> coordinates = route.features.get(0).geometry.coordinates;
                            for (double[] coordinate : coordinates) {
                                routeGeoPoints.add(new GeoPoint(coordinate[1], coordinate[0]));
                            }
                            System.out.println("GeoPoints: " + routeGeoPoints.size() + " Coordinates: " + coordinates.size());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finished = true;
                }
            });
        }
        while(routeGeoPoints.size() < 294){
        }
        //mapController.setCenter(routeGeoPoints.get(0));
        line = drawLine(routeGeoPoints);
        if (line != null){
            mapView.getOverlayManager().add(line);
        }
    }

    private void getDirectionsToNextWaypoint(Waypoint waypoint) {
        routeGeoPoints = new ArrayList<>();

        OpenRouteServiceConnection.getInstance().getRouteInfo("5b3ce3597851110001cf62488c97c6c701f64827afad2deda82ec4da", myLocation, waypoint.getGeoPoint(), TravelType.FOOT_WALKING, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //TODO handle error
                Log.e("MAP", "ERROR on route response");
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d("MAP", response.toString());

                JSONObject responseJson = null;
                try {
                    responseJson = new JSONObject(response.body().string());
                    route = new Route(responseJson);
                    ArrayList<double[]> coordinates = route.features.get(0).geometry.coordinates;
                    for (double[] coordinate : coordinates) {
                        routeGeoPoints.add(new GeoPoint(coordinate[1], coordinate[0]));
                    }
                    System.out.println("GeoPoints: " + routeGeoPoints.size() + " Coordinates: " + coordinates.size());

                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    finished = true;
                }
            }
        });

        // Wait for the API to respond
        while(!finished) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        finished = false;
        line = drawLine(routeGeoPoints);
        if (line != null){
            mapView.getOverlayManager().add(line);
            mapView.invalidate();
        }
    }
}