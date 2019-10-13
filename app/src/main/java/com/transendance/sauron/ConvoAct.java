package com.transendance.sauron;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ConvoAct extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView backButt;
    public static Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convo_lay);
        activity = this;

        toolbar = findViewById(R.id.convo_toolbar);
        toolbar.setTitle(getIntent().getExtras().getString("docName"));
        setSupportActionBar(toolbar);

        backButt = findViewById(R.id.from_convo_butt);

        backButt.setOnClickListener(goBack);
    }

    private View.OnClickListener goBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            activity.finish();
        }
    };
}
