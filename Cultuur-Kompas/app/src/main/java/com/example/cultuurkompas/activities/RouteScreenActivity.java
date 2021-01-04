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

public class RouteScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_screen);

        RouteRVAdapter adapter = new RouteRVAdapter();
        RecyclerView rc = findViewById(R.id.rv_route_items);
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
}