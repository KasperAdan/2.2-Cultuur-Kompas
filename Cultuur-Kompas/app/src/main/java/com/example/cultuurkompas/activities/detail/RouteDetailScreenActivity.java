package com.example.cultuurkompas.activities.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cultuurkompas.R;
import com.squareup.picasso.Picasso;

public class RouteDetailScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail_screen);
        Intent intent = getIntent();
        ((TextView) findViewById(R.id.tv_routedetailscreen_title)).setText(intent.getStringExtra("name"));
        ((TextView) findViewById(R.id.tv_routedetailscreen_description)).setText(intent.getStringExtra("description"));
        Picasso.get().load(intent.getStringExtra("imgLink")).into((ImageView) findViewById(R.id.iv_routedetail_item));

    }
}
