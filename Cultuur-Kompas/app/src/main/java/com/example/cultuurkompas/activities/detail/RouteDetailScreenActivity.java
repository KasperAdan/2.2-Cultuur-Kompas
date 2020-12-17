package com.example.cultuurkompas.activities.detail;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cultuurkompas.R;
import com.example.cultuurkompas.activities.popup.AlertDialog;
import com.example.cultuurkompas.activities.popup.DialogListener;
import com.example.cultuurkompas.activities.popup.HelpDialog;

public class RouteDetailScreenActivity extends AppCompatActivity implements DialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail_screen);

//        This is the code to show the Dialog, the callback will contain the button that is pressed
//        AlertDialog dialog = new AlertDialog(this, "Title", "Description", this);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.show();
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
