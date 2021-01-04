package com.example.cultuurkompas.activities.detail;

import android.content.Intent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
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
import com.example.cultuurkompas.activities.MapScreenActivity;
import com.example.cultuurkompas.activities.adapters.WaypointRVAdapter;
import com.example.cultuurkompas.activities.popup.DialogListener;
import com.example.cultuurkompas.activities.popup.AlertDialog;
import com.example.cultuurkompas.data.datamodel.Route;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RouteDetailScreenActivity extends AppCompatActivity implements DialogListener {

    private Route route;

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail_screen);
        this.startButton = findViewById(R.id.btn_routedetailscreen_start);

        this.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog(view);
            }
        });

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


    public void showAlertDialog(View view){
        //todo Language check
        new AlertDialog(this, "ALERT", "Weet je zeker dat je de route opnieuw wilt lopen?", this).show();
    }
    @Override
    public void DialogCallback(boolean okPressed) {
        if (okPressed) {
            Toast.makeText(this,"Yes pressed!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"No pressed!", Toast.LENGTH_SHORT).show();
        }
    }
}
