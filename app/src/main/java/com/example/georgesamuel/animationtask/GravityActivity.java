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
    private int timeX = 1, timeY = 5, time = 1;
    private int pointX = 0, pointY = 0;
    private int val = 0, slop = 1;
    private int distance;
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

        runVertically();
    }

    private void runVertically() {
        timeY = (int) getTime();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                distance = container.getHeight() - (ball.getHeight() + val);
                speedY = (int)getSpeed() * slop;
                pointY += speedY;
                ball.setX(pointX);
                ball.setY(
                        (slop > 0)?
                                Math.min(container.getHeight() - ball.getHeight(), pointY) :
                                Math.max(val, Math.min(container.getHeight() - ball.getHeight(), pointY))
                );
                if(isOut(pointX, pointY)){
                    if(slop > 0) {
                        val = Math.min(val + 50, container.getHeight() - ball.getHeight());
                    }
                    timeY = (int) getTime();
                    slop *= -1;
                }
                handler.postDelayed(this, timeY);

            }
        }, 100);
    }

    private double getTime() {
        return Math.sqrt(2.0 * distance / 9.8);
    }

    private double getSpeed() {
        return Math.sqrt(2.0 * 9.8 * (container.getHeight() - (ball.getHeight() + val)));
    }

    private boolean isOut(int x, int y) {
        return (x < 0 || x > container.getWidth() - ball.getWidth() || y < val || y > container.getHeight() - ball.getHeight());
    }

    private boolean isOut_(int x, int y) {
        return (x < 0 || x > container.getWidth() - ball.getWidth() || y < val || y > container.getHeight() - ball.getHeight());
    }

    private void runVertically_() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pointY += speedY;
                if(isOut_(pointX, pointY)){
                    speedY *= -1;
                    pointY += speedY;
                    val += (speedY < 0)? 50 : 0;
                }
                ball.setX(pointX);
                ball.setY(pointY);
                handler.postDelayed(this, timeY);

            }
        }, 100);
    }
}
