package com.example.cultuurkompas.viewmodel;
import android.util.Log;

import com.example.cultuurkompas.viewmodel.orsdata.TravelType;

import org.osmdroid.util.GeoPoint;

import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OpenRouteServiceConnection {

    private static OpenRouteServiceConnection instance = null;
    private static final String TAG = OpenRouteServiceConnection.class.getName();
    private OkHttpClient client = null;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    synchronized public static OpenRouteServiceConnection getInstance(){
        if (instance == null){
            instance = new OpenRouteServiceConnection();
        }
        return instance;
    }

    public OpenRouteServiceConnection() {
        client = new OkHttpClient();
    }

    public Call getRouteInfo(String key, List<GeoPoint> geoPoints, TravelType travelType, Callback callback){
        Log.d(TAG, "Start: " + geoPoints.get(0) + ", End: " + geoPoints.get(geoPoints.size() - 1));

        final String url = "https://api.openrouteservice.org/v2/directions/" +
                TravelType.getTravelType(travelType) +
                "?api_key=" + key;

        Log.d(TAG, url);

        String bodyString = "{\"coordinates\":[";
        for(int i = 0; i < geoPoints.size(); i++){
            bodyString += "[" + geoPoints.get(i).getLongitude() + "," + geoPoints.get(i).getLatitude() + "]";
            if(i != geoPoints.size() - 1) {
                bodyString += ",";
            }
        }
        bodyString += "]}";

        RequestBody requestBody = RequestBody.create(bodyString, JSON);
        Log.d(TAG, requestBody.toString());
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public Call getRouteInfo(String key, GeoPoint start, GeoPoint end, TravelType travelType, Callback callback){
        Log.d(TAG, "Start: " + start + ", End: " + end);
        final String url = "https://api.openrouteservice.org/v2/directions/" +
                TravelType.getTravelType(travelType) +
                "?api_key=" + key +
                "&start=" + start.getLongitude() + "," + start.getLatitude() +
                "&end=" + end.getLongitude() + "," + end.getLatitude();
        Log.d(TAG, url);
        final Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public Call getCoordinatesOfAddress(String key, String address, String city, Callback callback){
        final String url = "https://api.openrouteservice.org/geocode/search/structured?" +
                "api_key=" + key +
                "&address=" + address +
                "&locality=" + city;
        final Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }
}
