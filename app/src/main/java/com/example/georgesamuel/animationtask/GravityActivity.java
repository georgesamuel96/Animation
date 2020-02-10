package com.example.georgesamuel.animationtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class GravityActivity extends AppCompatActivity {

    ImageView ball;
    ConstraintLayout container;
    private int speedX = 20, speedY = 20;
    private int timeX = 1, timeY = 1, time = 1;
    private int pointX = 0, pointY = 0;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity);

        ball = findViewById(R.id.ball);
        container = findViewById(R.id.container);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
