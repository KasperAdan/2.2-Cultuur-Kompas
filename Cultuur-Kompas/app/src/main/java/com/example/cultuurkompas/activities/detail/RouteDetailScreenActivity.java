package com.example.cultuurkompas.activities.detail;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.activities.MapScreenActivity;

public class RouteDetailScreenActivity extends AppCompatActivity {

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
    }


    public void showAlertDialog(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("ALERT");
        alert.setMessage("Weet je zeker dat je de route opnieuw wilt lopen?");
        alert.setPositiveButton("JA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(view.getContext(), "Er is geen nieuwe route gestart", Toast.LENGTH_SHORT);
            }
        });
        alert.setNegativeButton("NEE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(view.getContext(), "Er is een nieuwe route gestart", Toast.LENGTH_SHORT);
            }
        });
    }
}
