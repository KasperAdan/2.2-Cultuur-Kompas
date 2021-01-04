package com.example.cultuurkompas.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.activities.adapters.BuildingRVAdapter;
import com.example.cultuurkompas.data.datamodel.Waypoint;
import com.example.cultuurkompas.interfaces.DataConnector;

import java.util.ArrayList;
import java.util.List;

public class BuildingScreenActivity extends AppCompatActivity {

    BuildingRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_screen);

        adapter = new BuildingRVAdapter();
        RecyclerView rc = findViewById(R.id.rv_building_items);
        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(this));

        ImageButton backButton = findViewById(R.id.btn_building_back);
        backButton.setOnClickListener(view -> super.onBackPressed());
    }

    public void onButtonAllBuildingClick(View view) {
        adapter.setBuildings(DataConnector.getInstance().getWaypoints());
        adapter.notifyDataSetChanged();
    }

    public void onButtonVisitedBuildingClick(View view) {
        List<Waypoint> waypoints = new ArrayList<>();
        for(Waypoint waypoint : DataConnector.getInstance().getWaypoints()){
            if(waypoint.isVisited()){
                waypoints.add(waypoint);
            }
        }
        adapter.setBuildings(waypoints);
        adapter.notifyDataSetChanged();
    }

    public void onButtonUnvistedBuildingClick(View view) {
        List<Waypoint> waypoints = new ArrayList<>();
        for(Waypoint waypoint : DataConnector.getInstance().getWaypoints()){
            if(!waypoint.isVisited()){
                waypoints.add(waypoint);
            }
        }
        adapter.setBuildings(waypoints);
        adapter.notifyDataSetChanged();
    }
}