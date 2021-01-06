package com.example.cultuurkompas.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.activities.adapters.RouteRVAdapter;
import com.example.cultuurkompas.activities.popup.HelpDialog;
import com.example.cultuurkompas.data.datamodel.Route;
import com.example.cultuurkompas.data.datamodel.Waypoint;
import com.example.cultuurkompas.interfaces.DataConnector;

import java.util.ArrayList;
import java.util.List;

public class RouteScreenActivity extends AppCompatActivity {

    RouteRVAdapter adapter;
    RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_screen);

        adapter = new RouteRVAdapter();
        rc = findViewById(R.id.rv_route_items);
        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(this));

        ImageButton backButton = findViewById(R.id.ib_routescreen_back);
        backButton.setOnClickListener(view -> super.onBackPressed());
    }

    public void onButtonHelpRouteClick(View view){
        HelpDialog dialog = new HelpDialog(this, getResources().getString(R.string.helpTextRoute));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    // Show all routes
    public void onButtonAllRouteClick(View view) {
        adapter.setRoutes(DataConnector.getInstance().getRoutes());
        adapter.notifyDataSetChanged();
        rc.scrollToPosition(0);
    }

    // Filter out all unfinished routes
    public void onButtonCompleteRouteClick(View view) {
        List<Route> routes = new ArrayList<>();
        for(Route route : DataConnector.getInstance().getRoutes()){
            if(route.isFinished()){
                routes.add(route);
            }
        }
        adapter.setRoutes(routes);
        adapter.notifyDataSetChanged();

        if(routes.size() > 0){
            rc.scrollToPosition(0);
        }
    }

    // Filter out all finished routes
    public void onButtonInCompleteRouteClick(View view) {
        List<Route> routes = new ArrayList<>();
        for(Route route : DataConnector.getInstance().getRoutes()){
            if(!route.isFinished()){
                routes.add(route);
            }
        }
        adapter.setRoutes(routes);
        adapter.notifyDataSetChanged();

        if(routes.size() > 0){
            rc.scrollToPosition(0);
        }
    }
}