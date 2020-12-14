package com.example.cultuurkompas.activities.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.model.Building;
import com.squareup.picasso.Picasso;

public class BuildingDetailScreenActivity extends AppCompatActivity {

    private Building building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail_screen);
        Intent intent = getIntent();

        building = (Building) intent.getSerializableExtra("building");

        ((TextView) findViewById(R.id.tv_buildingdetail_title)).setText(building.getName());
        ((TextView) findViewById(R.id.tv_buildingdetail_text)).setText(building.getDescription());

        Picasso.get().load(building.getImgLink()).into((ImageView) findViewById(R.id.iv_buildingdetail_item));

        ((CheckBox) findViewById(R.id.cb_buildingdetail_visited)).setChecked(building.getHasVisited());

//        TODO Add tags to list
//        building.getTags();

    }
}