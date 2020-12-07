package com.example.cultuurkompas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MapScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapscreen);
    }

    public void onButtonCurrentLocationClick(View view){
        Toast.makeText(this,"CURRENT LOCATION", Toast.LENGTH_LONG).show();
    }

    public void onButtonHelpMapClick(View view){
        Toast.makeText(this,"HELP!", Toast.LENGTH_LONG).show();
    }

    public void onButtonBuildingMapClick(View view){
        Toast.makeText(this,"BUILDINGS", Toast.LENGTH_LONG).show();
    }

    public void onButtonRouteMapClick(View view){
        Toast.makeText(this,"ROUTES", Toast.LENGTH_LONG).show();
    }
}