package com.example.cultuurkompas.activities.detail;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.example.cultuurkompas.activities.popup.HelpDialog;
import com.example.cultuurkompas.activities.MapScreenActivity;
import com.example.cultuurkompas.data.datamodel.Waypoint;
import com.example.cultuurkompas.interfaces.DataConnector;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BuildingDetailScreenActivity extends AppCompatActivity {

    private Waypoint waypoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail_screen);
        Intent intent = getIntent();
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
        backButton.setOnClickListener(view -> super.onBackPressed());

        ImageButton mapDetailButton = findViewById(R.id.ib_buildingdetail_map);
        mapDetailButton.setOnClickListener(view -> {
            //TODO
            Intent mapIntent = new Intent(BuildingDetailScreenActivity.this, MapScreenActivity.class);
            mapIntent.putExtra("waypoint",waypoint);
            startActivity(mapIntent);
        });
    }

    public void onButtonVisitedBuildingDetailClick(View view) {
        DataConnector.getInstance().overrideWaypointVisitedState(waypoint.getNumber() - 1);
    }

    public void onButtonHelpBuildingDetailClick(View view){
        HelpDialog dialog = new HelpDialog(this, getResources().getString(R.string.helpTextBuildingDetail));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}