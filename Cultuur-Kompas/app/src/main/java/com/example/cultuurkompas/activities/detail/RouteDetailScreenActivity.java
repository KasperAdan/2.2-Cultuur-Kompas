package com.example.cultuurkompas.activities.detail;

import android.content.Intent;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.example.cultuurkompas.activities.popup.HelpDialog;
import com.example.cultuurkompas.data.datamodel.Route;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RouteDetailScreenActivity extends AppCompatActivity {

    private Route route;

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail_screen);
        this.startButton = findViewById(R.id.btn_routedetailscreen_start);

        this.startButton.setOnClickListener(view -> {
            if(route.isFinished()){
                showRestartAlertDialog(view);
            }
            else if(route.isStarted()){
                showStopAlertDialog(view);
            }
            else {
                showStartAlertDialog(view);
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
        WaypointRVAdapter adapter = new WaypointRVAdapter(route.getWaypoints());
        RecyclerView rc = findViewById(R.id.rv_routedetailscreen_waypoint);

        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(this));

        ImageButton backButton = findViewById(R.id.btn_routedetailscreen_back);
        backButton.setOnClickListener(view -> super.onBackPressed());
        findViewById(R.id.ib_routedetailscreen_help).setOnClickListener(view -> onButtonHelpRouteDetailClick(view));

        if(route.isStarted()){
            startButton.setText("Stop Route");
        }
    }


    public void showRestartAlertDialog(View view){
        new AlertDialog(this, "ALERT", getResources().getText(R.string.routeRestartText).toString(), okPressed -> {
            if(okPressed) {
                Intent mapIntent = new Intent(RouteDetailScreenActivity.this, MapScreenActivity.class);
                mapIntent.putExtra("routerestart", route.getName());
                startActivity(mapIntent);
            }
        }).show();
    }
    public void showStartAlertDialog(View view){
        new AlertDialog(this, "ALERT", getResources().getText(R.string.routeStartText).toString(), okPressed -> {
            if(okPressed) {
                Intent mapIntent = new Intent(RouteDetailScreenActivity.this, MapScreenActivity.class);
                mapIntent.putExtra("routestart", route.getName());
                startActivity(mapIntent);
            }
        }).show();
    }

    public void onButtonHelpRouteDetailClick(View view){
        HelpDialog dialog = new HelpDialog(this, getResources().getString(R.string.helpTextRouteDetail));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    public void showStopAlertDialog(View view){
        new AlertDialog(this, "ALERT", getResources().getText(R.string.routeStopText).toString(), okPressed -> {
            if(okPressed) {
                Intent mapIntent = new Intent(RouteDetailScreenActivity.this, MapScreenActivity.class);
                mapIntent.putExtra("routestop", route.getName());
                startActivity(mapIntent);
            }
        }).show();
    }
}
