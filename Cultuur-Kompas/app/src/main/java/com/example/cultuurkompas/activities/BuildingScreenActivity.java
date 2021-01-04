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
import com.example.cultuurkompas.activities.popup.HelpDialog;

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

    public void onButtonHelpBuildingClick(View view){
        HelpDialog dialog = new HelpDialog(this, getResources().getString(R.string.helpTextBuilding));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}