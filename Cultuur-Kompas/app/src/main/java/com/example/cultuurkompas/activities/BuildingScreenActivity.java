package com.example.cultuurkompas.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.activities.adapters.BuildingRVAdapter;

public class BuildingScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_screen);

        BuildingRVAdapter adapter = new BuildingRVAdapter();
        RecyclerView rc = findViewById(R.id.rv_building_items);
        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(this));

        ImageButton backButton = findViewById(R.id.btn_building_back);
        backButton.setOnClickListener(view -> super.onBackPressed());
    }
}