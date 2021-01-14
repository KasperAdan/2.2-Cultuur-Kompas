package com.example.cultuurkompas.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.activities.detail.BuildingDetailScreenActivity;
import com.example.cultuurkompas.activities.popup.AlertDialog;
import com.example.cultuurkompas.activities.popup.DialogListener;
import com.example.cultuurkompas.activities.popup.HelpDialog;
import com.example.cultuurkompas.data.datamodel.Waypoint;
import com.example.cultuurkompas.interfaces.DataConnector;
import com.example.cultuurkompas.interfaces.LocationService;
import com.example.cultuurkompas.viewmodel.OpenRouteServiceConnection;
import com.example.cultuurkompas.viewmodel.orsdata.Route;
import com.example.cultuurkompas.viewmodel.orsdata.TravelType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MapScreenActivity extends AppCompatActivity {

    private MapView mapView = null;
    private IMapController mapController;
    private GeoPoint cityGeoPoint;
    private Route route;
    private LocationService locationService;
    private LocationManager locationManager;
    private Marker marker;
    private Intent serviceIntent;
    private ArrayList<Marker> markers;
    private boolean finished = false;
    private List<GeoPoint> routeGeoPoints;
    private GeoPoint myLocation;
    private com.example.cultuurkompas.data.datamodel.Route selectedRoute;
    private Polyline line;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLocationEvent(LocationService.LocationEvent event) {
        myLocation = event.getGeoPoint();
        marker.setPosition(myLocation);
        DataConnector.getInstance().setLastKnownLocation(myLocation);
        Log.d("LOCATION_CHANGED:", myLocation.getLatitude() + "," + myLocation.getLongitude());
        mapView.invalidate();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWaypointEvent(LocationService.WayPointEvent event) {
        Log.d("Waypoint Event:", "Waypoint Reached");

        if (selectedRoute != null) {
            if (selectedRoute.getWaypoints().get(selectedRoute.getProgressionCounter()) == event.getWaypoint()) {
                reachedWaypoint();
                Intent intent = new Intent(this, BuildingDetailScreenActivity.class);
                intent.putExtra("waypoint", event.getWaypoint());
                startActivity(intent);

                /*mapView.getOverlays().remove(markers.get(selectedRoute.getProgressionCounter()-1));
                mapView.invalidate();
                Marker marker = new Marker(mapView);

                marker.setPosition(event.getWaypoint().getGeoPoint());
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(MapScreenActivity.this, R.drawable.icon_waypoint);
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                DrawableCompat.setTint(wrappedDrawable, Color.GREEN);
                marker.setIcon(wrappedDrawable);
                mapView.invalidate();*/

            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

        Intent intent = getIntent();

        if (intent.getStringExtra("routestart") != null) {
            DataConnector.getInstance().EndActiveRoute();
            startRoute(DataConnector.getInstance().getRouteWithName(intent.getStringExtra("routestart")));
            EventBus.getDefault().post(new LocationService.RouteEvent(DataConnector.getInstance().getRouteWithName(intent.getStringExtra("routestart"))));
            intent.removeExtra("routestart");
        } else if (intent.getStringExtra("routestop") != null) {
            //DataConnector.getInstance().resetRoute(DataConnector.getInstance().getRouteWithName(intent.getStringExtra("routestop")));
            DataConnector.getInstance().stopRoute(DataConnector.getInstance().getRouteWithName(intent.getStringExtra("routestop")));
            EventBus.getDefault().post(new LocationService.RouteEvent(null));
            intent.removeExtra("routestop");
        } else if (intent.getStringExtra("routerestart") != null) {
            DataConnector.getInstance().EndActiveRoute();
            DataConnector.getInstance().resetRoute(DataConnector.getInstance().getRouteWithName(intent.getStringExtra("routerestart")));
            startRoute(DataConnector.getInstance().getRouteWithName(intent.getStringExtra("routerestart")));
            EventBus.getDefault().post(new LocationService.RouteEvent(DataConnector.getInstance().getRouteWithName(intent.getStringExtra("routerestart"))));
            intent.removeExtra("routerestart");
        }

        if (selectedRoute == null) {
            // Checks if there is an ongoing route and resumes it
            for (com.example.cultuurkompas.data.datamodel.Route route : DataConnector.getInstance().getRoutes()) {
                if (route.isStarted()) {
                    if (intent.getSerializableExtra("waypoint") != null) {
                        startRoute(route);
                    } else {
                        AlertDialog routeAlertDialog = new AlertDialog(this, "Route", getResources().getText(R.string.routeContinueText).toString(), new DialogListener() {
                            @Override
                            public void DialogCallback(boolean okPressed) {
                                if (okPressed) {
                                    startRoute(route);
                                    drawMarkers();
                                } else {
                                    DataConnector.getInstance().stopRoute(route);
                                }
                            }
                        });
                        routeAlertDialog.show();
                    }
                }
            }
        }
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);

        Configuration.getInstance().setUserAgentValue("com.example.cultuurkompas");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, (LocationListener) locationService);

        DataConnector.getInstance().getAllData(this);
        // Enable this to reset all progress
//        DataConnector.getInstance().resetProgression();

        cityGeoPoint = new GeoPoint(51.589457, 4.777006);

        markers = new ArrayList<>();

        mapView = findViewById(R.id.osm_map);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setDestroyMode(false);
        mapView.setTag("mapView");
        mapView.setMultiTouchControls(true);
        marker = new Marker(mapView);
        marker.setOnMarkerClickListener((marker, mapView) -> false);
//        marker.setPosition(cityGeoPoint);
        marker.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_my_location, null));
        mapView.getOverlays().add(marker);
        mapView.setBuiltInZoomControls(false);
        mapController = mapView.getController();
        mapController.setZoom(17.5);
        mapController.setCenter(cityGeoPoint);

        serviceIntent = new Intent(this, LocationService.class);
//        intent.putExtra("geoFenceRoute", DataConnector.getInstance().getRoutes().get(0));
        startForegroundService(serviceIntent);

        if (DataConnector.getInstance().getLastKnownLocation() != null) {
            onLocationEvent(new LocationService.LocationEvent(DataConnector.getInstance().getLastKnownLocation()));
        }

    }


    public void onButtonCurrentLocationClick(View view) {
        if (myLocation != null) {
            mapController.setCenter(myLocation);
        }

        // TESTING
//        if (selectedRoute != null) {
//            reachedWaypoint();
//            stopCurrentRoute();
//        }
    }

    public void onButtonHelpMapClick(View view) {
        HelpDialog dialog = new HelpDialog(this, getResources().getString(R.string.helpTextMap));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void onButtonBuildingMapClick(View view) {
        Toast.makeText(this, "BUILDINGS", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MapScreenActivity.this, BuildingScreenActivity.class);
        startActivity(i);
    }

    public void onButtonRouteMapClick(View view) {
        Toast.makeText(this, "ROUTES", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MapScreenActivity.this, RouteScreenActivity.class);
        startActivity(i);
    }


    @Override
    public void onResume() {
        super.onResume();

        Log.e("Onresume", "resume");

        Intent intent = getIntent();

        if (intent.getSerializableExtra("waypoint") != null) {
            Waypoint waypoint = (Waypoint) intent.getSerializableExtra("waypoint");
            mapController.setCenter(waypoint.getGeoPoint());
            mapController.setZoom(20);
        }


        drawMarkers();


        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }


    public Polyline drawLine(List<GeoPoint> geoPoints) {
        Polyline line = new Polyline();
        line.setTitle("Road back home");
        line.setSubDescription(Polyline.class.getCanonicalName());
        //line.setWidth(20f);
        line.getOutlinePaint().setStrokeWidth(10f);
        line.getOutlinePaint().setColor(Color.RED);
        line.setPoints(geoPoints);
        line.setGeodesic(true);
        line.setInfoWindow(new BasicInfoWindow(R.layout.bonuspack_bubble, mapView));

        return line;
    }

    public void startRoute(com.example.cultuurkompas.data.datamodel.Route route) {
        if (selectedRoute != null) {
            return;
        }
        selectedRoute = route;
        DataConnector.getInstance().startRoute(selectedRoute);

        if (DataConnector.getInstance().getLastKnownLocation() != null) {
            getDirectionsToNextWaypoint(selectedRoute.getWaypoints().get(selectedRoute.getProgressionCounter()));
        }
        Toast.makeText(this, getResources().getText(R.string.routeBeginMessage), Toast.LENGTH_LONG).show();
    }

    public void stopCurrentRoute() {
        selectedRoute = null;
        if (line != null) {
            mapView.getOverlayManager().remove(line);
            line = null;
            mapView.invalidate();
        }
    }

    private void reachedWaypoint() {
        if (line != null) {
            mapView.getOverlayManager().remove(line);
            mapView.invalidate();
        }
        if (selectedRoute != null) {
            DataConnector.getInstance().reachedNewWaypoint(selectedRoute);
            if (!selectedRoute.isFinished()) {
                if (selectedRoute.getProgressionCounter() == selectedRoute.getWaypoints().size() / 2) {
                    Toast.makeText(this, getResources().getText(R.string.routeMidMessage), Toast.LENGTH_LONG).show();
                }
                getDirectionsToNextWaypoint(selectedRoute.getWaypoints().get(selectedRoute.getProgressionCounter()));
            } else {
                Toast.makeText(this, getResources().getText(R.string.routeEndMessage), Toast.LENGTH_LONG).show();
                stopCurrentRoute();
            }
        }
    }

    private void getDirectionsToNextWaypoint(Waypoint waypoint) {
        routeGeoPoints = new ArrayList<>();

        GeoPoint myGeoPoint = myLocation != null ? myLocation : DataConnector.getInstance().getLastKnownLocation();
        OpenRouteServiceConnection.getInstance().getRouteInfo("5b3ce3597851110001cf62488c97c6c701f64827afad2deda82ec4da", myGeoPoint, waypoint.getGeoPoint(), TravelType.FOOT_WALKING, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
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
        while (!finished) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        finished = false;
        line = drawLine(routeGeoPoints);
        if (line != null) {
            mapView.getOverlayManager().add(line);
            mapView.invalidate();
        }
    }

    private void drawMarkers(){
        if(!markers.isEmpty()){
            for(Marker marker : markers){
                mapView.getOverlays().remove(marker);
            }
        }

        if (selectedRoute != null) {
            for (Waypoint waypoint : selectedRoute.getWaypoints()) {
//        mapView.getOverlays().remove(mapView.getOverlays().indexOf(marker.getPosition().equals(waypoint.getGeoPoint())));
                Marker marker = new Marker(mapView);
                marker.setPosition(waypoint.getGeoPoint());
                markers.add(marker);
                if (!waypoint.isVisited()) {
                    Drawable unwrappedDrawable = AppCompatResources.getDrawable(MapScreenActivity.this, R.drawable.icon_waypoint);
                    Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                    DrawableCompat.setTint(wrappedDrawable, Color.GRAY);
                    marker.setIcon(wrappedDrawable);
                } else {
                    Drawable unwrappedDrawable = AppCompatResources.getDrawable(MapScreenActivity.this, R.drawable.icon_waypoint);
                    Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                    DrawableCompat.setTint(wrappedDrawable, Color.GREEN);
                    marker.setIcon(wrappedDrawable);
                }
                marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker, MapView mapView) {
                        Intent intent = new Intent(MapScreenActivity.this, BuildingDetailScreenActivity.class);
                        intent.putExtra("waypoint", waypoint);
                        startActivity(intent);
                        return false;
                    }
                });
                mapView.getOverlays().add(marker);

                mapView.invalidate();
            }
        }
        else {
            markers.clear();
            for(Waypoint waypoint : DataConnector.getInstance().getWaypoints()){
                Marker marker = new Marker(mapView);
                marker.setPosition(waypoint.getGeoPoint());
                Drawable unwrappedDrawable = AppCompatResources.getDrawable(MapScreenActivity.this, R.drawable.icon_waypoint);
                Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                if(waypoint.isVisited()){
                    DrawableCompat.setTint(wrappedDrawable, Color.GREEN);
                }
                else {
                    DrawableCompat.setTint(wrappedDrawable, Color.GRAY);
                }
                marker.setIcon(wrappedDrawable);

                marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker, MapView mapView) {
                        Intent intent = new Intent(MapScreenActivity.this, BuildingDetailScreenActivity.class);
                        intent.putExtra("waypoint", waypoint);
                        startActivity(intent);
                        return false;
                    }
                });
                markers.add(marker);
            }

            mapView.getOverlays().addAll(markers);
            mapView.invalidate();
        }
    }
}