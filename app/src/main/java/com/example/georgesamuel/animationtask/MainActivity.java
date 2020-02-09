package com.example.georgesamuel.animationtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView ball;
    ConstraintLayout container;
    private int speedX = 20, speedY = 20;
    private int timeX = 1, timeY = 1, time = 1;
    private int pointX = 0, pointY = 0;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ball = findViewById(R.id.ball);
        container = findViewById(R.id.container);

    }

    private int getRandom(int upTo) {
        Random random = new Random();
        return (random.nextInt(upTo) + 1) * 2;
    }

    private int getRandomPoint(int upTo) {
        Random random = new Random();
        return random.nextInt(upTo);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        runVertically();

        runHorizontally();

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getRandomValues();
//                playRandom();
//            }
//        }, 100);
    }

    private void getRandomValues() {
        speedY = speedX = getRandom(4);
        pointX = getRandomPoint(container.getWidth() - ball.getWidth());
        pointY = getRandomPoint(container.getHeight() - ball.getHeight());
        ball.setX(pointX);
        ball.setY(pointY);
    }

    private void playRandom() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pointX += speedX;
                pointY += speedY;
                if(isXOut(pointX)){
                    speedX *= -1;
                    pointX += speedX;
                }
                if(isYOut(pointY)){
                    speedY *= -1;
                    pointY += speedY;
                }
                ball.setX(pointX);
                ball.setY(pointY);
                handler.postDelayed(this, time);
            }
        }, 10);
    }

    private boolean isXOut(int x) {
        return (x < 0 || x >= container.getWidth() - ball.getWidth());
    }

    private boolean isYOut(int y) {
        return (y < 0 || y >= container.getHeight() - ball.getHeight());
    }

    private boolean isOut(int x, int y) {
        return (x < 0 || x > container.getWidth() - ball.getWidth() || y < 0 || y > container.getHeight() - ball.getHeight());
    }

    private void runHorizontally() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pointX += speedX;
                if(isOut(pointX, pointY)) {
                    speedX *= -1;
                    pointX += speedX;
                }
                ball.setX(pointX);
                ball.setY(pointY);
                handler.postDelayed(this, timeX);

            }
        }, 100);
    }

    private void runVertically() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pointY += speedY;
                if(isOut(pointX, pointY)){
                    speedY *= -1;
                    pointY += speedY;
                }
                ball.setX(pointX);
                ball.setY(pointY);
                handler.postDelayed(this, timeY);

            }
        }, 100);
    }
}
