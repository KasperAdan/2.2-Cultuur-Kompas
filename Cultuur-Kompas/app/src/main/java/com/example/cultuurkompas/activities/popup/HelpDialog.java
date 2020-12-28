package com.example.cultuurkompas.activities.popup;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cultuurkompas.R;

public class HelpDialog extends Dialog implements View.OnClickListener {

    private final Activity activity;
    private final String helpText;
    private ImageButton exitButton;

    public HelpDialog(Activity a, String helpText) {
        super(a);
        this.activity = a;
        this.helpText = helpText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.help_dialog);
        TextView textView = findViewById(R.id.helpPopup_text);
        textView.setText(helpText);
        this.exitButton = findViewById(R.id.helpPopup_closeButton);
        this.exitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.helpPopup_closeButton:
                dismiss();
                break;
            default:
                break;
        }
    }
}
