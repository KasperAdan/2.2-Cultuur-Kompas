package com.example.cultuurkompas.activities.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.activities.adapters.WaypointRVAdapter;
import com.example.cultuurkompas.data.datamodel.Route;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RouteDetailScreenActivity extends AppCompatActivity {

    private Route route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail_screen);

        Intent intent = getIntent();
        route = (Route) intent.getSerializableExtra("route");

        ((TextView) findViewById(R.id.tv_routedetailscreen_title)).setText(route.getName());
        ((TextView) findViewById(R.id.tv_routedetailscreen_description)).setText(route.getDescription());
        Picasso.get().load(route.getImgLink()).into((ImageView) findViewById(R.id.iv_routedetail_item));

        LinearLayout ll = findViewById(R.id.linearlayout_routeDetailScreen_tagList);
        List<String> tags = route.getTags();
        for (int i = 0; i < tags.size(); i++) {
            TextView tv = new TextView(this);
            tv.setText(tags.get(i));
            tv.setId(i);
            tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ll.addView(tv);
        }
        WaypointRVAdapter adapter = new WaypointRVAdapter();
        RecyclerView rc = findViewById(R.id.rv_routedetailscreen_waypoint);

        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(this));

        ImageButton backButton = findViewById(R.id.btn_routedetailscreen_back);
        backButton.setOnClickListener(view -> super.onBackPressed());
    }
}
