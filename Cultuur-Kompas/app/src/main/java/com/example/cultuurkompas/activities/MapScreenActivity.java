package com.example.cultuurkompas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cultuurkompas.R;

public class MapScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);
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
}