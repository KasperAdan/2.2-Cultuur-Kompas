package com.example.cultuurkompas.activities.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.data.datamodel.Waypoint;
import com.squareup.picasso.Picasso;

public class BuildingDetailScreenActivity extends AppCompatActivity {

    private Waypoint building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail_screen);
        Intent intent = getIntent();

        building = (Waypoint) intent.getSerializableExtra("building");

        ((TextView) findViewById(R.id.tv_buildingdetail_title)).setText(building.getName());
        ((TextView) findViewById(R.id.tv_buildingdetail_text)).setText(building.getDescription());

        Picasso.get().load(building.getImgLink()).into((ImageView) findViewById(R.id.iv_buildingdetail_item));

        ((CheckBox) findViewById(R.id.cb_buildingdetail_visited)).setChecked(building.isVisited());

        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_buildingdetail_tags);
        for (String tag: building.getTags()) {
            TextView tv = new TextView(this);
            tv.setText(tag);
            ll.addView(tv);
        }
    }
}