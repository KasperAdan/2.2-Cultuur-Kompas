package com.example.cultuurkompas.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.activities.adapters.BuildingRVAdapter;
import com.example.cultuurkompas.data.datamodel.Waypoint;
import com.example.cultuurkompas.interfaces.DataConnector;
import com.example.cultuurkompas.activities.popup.HelpDialog;

import java.util.ArrayList;
import java.util.List;

public class BuildingScreenActivity extends AppCompatActivity {

    private enum ListState{
        ALL,
        VISITED,
        UNVISITED
    }

    RecyclerView rc;
    BuildingRVAdapter adapter;
    ListState listState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_screen);

        adapter = new BuildingRVAdapter();
        rc = findViewById(R.id.rv_building_items);
        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(this));

        ImageButton backButton = findViewById(R.id.btn_building_back);
        backButton.setOnClickListener(view -> super.onBackPressed());

        listState = ListState.ALL;
    }

    // No filter
    public void onButtonAllBuildingClick(View view) {
        adapter.setBuildings(DataConnector.getInstance().getWaypoints());
        adapter.notifyDataSetChanged();
        rc.scrollToPosition(0);
        listState = ListState.ALL;
    }

    // Filter out all unvisited buildings
    public void onButtonVisitedBuildingClick(View view) {
        List<Waypoint> waypoints = new ArrayList<>();
        for(Waypoint waypoint : DataConnector.getInstance().getWaypoints()){
            if(waypoint.isVisited()){
                waypoints.add(waypoint);
            }
        }
        adapter.setBuildings(waypoints);
        adapter.notifyDataSetChanged();

        if(waypoints.size() > 0){
            rc.scrollToPosition(0);
        }
        listState = ListState.VISITED;
    }

    // Filter out all visited buildings
    public void onButtonUnvistedBuildingClick(View view) {
        List<Waypoint> waypoints = new ArrayList<>();
        for(Waypoint waypoint : DataConnector.getInstance().getWaypoints()){
            if(!waypoint.isVisited()){
                waypoints.add(waypoint);
            }
        }
        adapter.setBuildings(waypoints);
        adapter.notifyDataSetChanged();

        if(waypoints.size() > 0){
            rc.scrollToPosition(0);
        }
        listState = ListState.UNVISITED;
    }

    // Update the list with new visited state of waypoint
    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null) {
            switch (listState){
                case VISITED:
                    onButtonVisitedBuildingClick(findViewById(R.id.btn_building_visited));
                    break;
                case UNVISITED:
                    onButtonUnvistedBuildingClick(findViewById(R.id.btn_building_unvisited));
                    break;
            }
        }
    }

    public void onButtonHelpBuildingClick(View view){
        HelpDialog dialog = new HelpDialog(this, getResources().getString(R.string.helpTextBuilding));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}