package com.example.cultuurkompas.activities.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cultuurkompas.R;
import com.squareup.picasso.Picasso;

public class BuildingDetailScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail_screen);
        Intent intent = getIntent();
        ((TextView) findViewById(R.id.tv_buildingdetail_title)).setText(intent.getStringExtra("name"));
        ((TextView) findViewById(R.id.tv_buildingdetail_title)).setText(intent.getStringExtra("description"));

        Picasso.get().load(intent.getStringExtra("imgLink")).into((ImageView) findViewById(R.id.iv_buildingdetail_item));

        ((CheckBox) findViewById(R.id.cb_buildingdetail_visited)).setChecked(intent.getBooleanExtra("hasVisited", false));

//        TODO Add tags to list
//        intent.getStringArrayExtra("tags");

    }
}