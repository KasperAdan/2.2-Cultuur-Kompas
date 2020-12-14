package com.example.cultuurkompas.activities.popup;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.cultuurkompas.R;

public class AlertDialog extends Dialog implements View.OnClickListener {

    private final Activity activity;
    private final String title;
    private final String message;

    public AlertDialog(Activity a, String title, String message) {
        super(a);
        this.activity = a;
        this.title = title;
        this.message = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_dialog);
        TextView title = findViewById(R.id.alertDialog_title);
        TextView message = findViewById(R.id.alertDialog_message);
        Button negative = findViewById(R.id.alertDialog_negative);
        Button positive = findViewById(R.id.alertDialog_positive);

        title.setText(this.title);
        message.setText(this.message);
        negative.setOnClickListener(this);
        positive.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alertDialog_positive:
                //todo add functionality Yes button
                dismiss();
                break;
            case R.id.alertDialog_negative:
                //todo add functionality No button
                dismiss();
                break;
            default:
                break;
        }
    }
}
