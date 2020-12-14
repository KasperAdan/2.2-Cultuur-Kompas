package com.example.cultuurkompas.activities.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.model.Route;
import com.squareup.picasso.Picasso;

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

    }
}
