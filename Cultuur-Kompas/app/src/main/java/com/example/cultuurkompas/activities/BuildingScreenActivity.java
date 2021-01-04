package com.example.cultuurkompas.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.activities.adapters.BuildingRVAdapter;
import com.example.cultuurkompas.data.datamodel.Waypoint;
import com.example.cultuurkompas.interfaces.DataConnector;

import java.util.ArrayList;
import java.util.List;

public class BuildingScreenActivity extends AppCompatActivity {

    private BuildingRVAdapter adapter;
    private List<Waypoint> waypoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_screen);

        this.waypoints = DataConnector.getInstance().getWaypoints();

        adapter = new BuildingRVAdapter(this.waypoints);
        RecyclerView rc = findViewById(R.id.rv_building_items);
        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(this));

        ImageButton backButton = findViewById(R.id.btn_building_back);
        backButton.setOnClickListener(view -> super.onBackPressed());

        Button all = findViewById(R.id.btn_building_all);
        all.setOnClickListener(view -> {
            adapter.setWaypoints(waypoints);
            adapter.notifyDataSetChanged();
        });

        Button unvisit = findViewById(R.id.btn_building_unvisited);
        unvisit.setOnClickListener(view -> {
            ArrayList<Waypoint> list = new ArrayList<>();
            for (Waypoint point : waypoints) {
                if (!point.isVisited()) {
                    list.add(point);
                }
            }
            adapter.setWaypoints(list);
            adapter.notifyDataSetChanged();
        });

        Button visit = findViewById(R.id.btn_building_visited);
        visit.setOnClickListener(view -> {
            ArrayList<Waypoint> list = new ArrayList<>();
            for (Waypoint point : waypoints) {
                if (point.isVisited()) {
                    list.add(point);
                }
            }
            adapter.setWaypoints(list);
            adapter.notifyDataSetChanged();
        });

    }
}