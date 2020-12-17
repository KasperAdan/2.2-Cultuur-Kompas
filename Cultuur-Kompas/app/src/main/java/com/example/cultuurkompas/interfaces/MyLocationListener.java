package com.example.cultuurkompas.interfaces;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.cultuurkompas.activities.MapScreenActivity;

import org.osmdroid.util.GeoPoint;

public class MyLocationListener implements LocationListener {

    MapScreenActivity activity;
    public MyLocationListener(MapScreenActivity activity) {
        this.activity=activity;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        activity.locationChanged(new GeoPoint(location.getLatitude(),location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}
