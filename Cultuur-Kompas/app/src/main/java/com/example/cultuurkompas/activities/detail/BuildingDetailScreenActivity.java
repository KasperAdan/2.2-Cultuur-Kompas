package com.example.cultuurkompas.activities.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.data.datamodel.Waypoint;
import com.example.cultuurkompas.interfaces.WaypointChangedListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BuildingDetailScreenActivity extends AppCompatActivity {

    private Waypoint waypoint;
    private WaypointChangedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail_screen);
        Intent intent = getIntent();
        if (intent.getSerializableExtra("test") != null){
            listener = (WaypointChangedListener) intent.getSerializableExtra("test");
        }

        waypoint = (Waypoint) intent.getSerializableExtra("waypoint");

        ((TextView) findViewById(R.id.tv_buildingdetail_title)).setText(waypoint.getName());
        ((TextView) findViewById(R.id.tv_buildingdetail_text)).setText(waypoint.getDescription());

        Picasso.get().load(waypoint.getImgLink()).into((ImageView) findViewById(R.id.iv_buildingdetail_item));

        LinearLayout ll = findViewById(R.id.ll_buildingdetail_tags);
        ArrayList<String> tags = waypoint.getTags();
        for (int i = 0; i < tags.size(); i++) {
            TextView tv = new TextView(this);
            tv.setText(tags.get(i));
            tv.setId(i);
            tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ll.addView(tv);
        }

        ((CheckBox) findViewById(R.id.cb_buildingdetail_visited)).setChecked(waypoint.isVisited());

        ImageButton backButton = findViewById(R.id.ib_buildingdetail_back);
        backButton.setOnClickListener(v -> {

            boolean ischecked = ((CheckBox)findViewById(R.id.cb_buildingdetail_visited)).isChecked();

            waypoint.setVisited(ischecked);
            if (listener != null){
                listener.onWaypointChanged(waypoint);
            }
            super.onBackPressed();
        });
    }
}