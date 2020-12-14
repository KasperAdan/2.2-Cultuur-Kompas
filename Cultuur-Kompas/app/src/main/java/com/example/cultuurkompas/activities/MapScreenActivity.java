package com.example.cultuurkompas.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.interfaces.MyLocationListener;
import com.example.cultuurkompas.viewmodel.orsdata.Route;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;

import java.util.ArrayList;

public class MapScreenActivity extends AppCompatActivity {

    private MapView mapView = null;
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private IMapController mapController;
    private GeoPoint cityGeoPoint;
    private Route route;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private Marker marker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);

        requestPermissionsIfNecessary(new String[]{
                // if you need to show the current location, uncomment the line below
                // Manifest.permission.ACCESS_FINE_LOCATION,
                // WRITE_EXTERNAL_STORAGE is required in order to show the map
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });
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


        cityGeoPoint = new GeoPoint(51.589457,4.777006);

        mapView = findViewById(R.id.osm_map);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setDestroyMode(false);
        mapView.setTag("mapView");
        mapView.setMultiTouchControls(true);
        marker = new Marker(mapView);
        marker.setPosition(cityGeoPoint);
        mapView.getOverlays().add(marker);
        mapView.setBuiltInZoomControls(false);
        mapController = mapView.getController();
        mapController.setZoom(17.5);
        mapController.setCenter(cityGeoPoint)
        ;
    }

    public void onButtonCurrentLocationClick(View view){
        Toast.makeText(this,"CURRENT LOCATION", Toast.LENGTH_LONG).show();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    public Polyline drawLine(ArrayList<GeoPoint> geoPoints){
        Polyline line = new Polyline();
        line.setTitle("Road back home");
        line.setSubDescription(Polyline.class.getCanonicalName());
        //line.setWidth(20f);
        line.getOutlinePaint().setStrokeWidth(20f);
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
        mapController.setCenter(geoPoint);
        marker.setPosition(geoPoint);
    }


}