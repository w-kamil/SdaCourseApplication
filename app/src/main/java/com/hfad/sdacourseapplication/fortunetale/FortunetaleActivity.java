package com.hfad.sdacourseapplication.fortunetale;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.sdacourseapplication.R;
import com.squareup.seismic.ShakeDetector;

import java.util.List;
import java.util.Random;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class FortunetaleActivity extends AppCompatActivity implements ShakeDetector.Listener {

    Random random = new Random();
    private FrameLayout layer;
    private Fortunes fortunes = new Fortunes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortunetale);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.start(sensorManager);

        layer = (FrameLayout) findViewById(R.id.fortune_container);
        FrameLayout parentLayout = (FrameLayout) findViewById(R.id.fortune_parent_layout);


        parentLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    animateCiruclarReveal((int) event.getX(), (int) event.getY());
                }
                return true;
            }

        });
    }

    private void animateCiruclarReveal(int x, int y) {
        if (layer.getVisibility() == View.VISIBLE) {
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(layer, x, y, layer.getHeight(), 0);
            circularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    layer.setVisibility(View.INVISIBLE);
                }
            });
            circularReveal.start();

        } else {
            String fortune = fortunes.getFortune(random.nextInt(fortunes.getCount()));
            TextView fortuneText = (TextView) findViewById(R.id.fortune_text);
            fortuneText.setText(fortune);
            int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
            layer.setBackgroundColor(color);
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(layer, x, y, 0, layer.getHeight());
            layer.setVisibility(View.VISIBLE);
            circularReveal.start();

        }
    }

    @Override
    public void hearShake() {

        animateCiruclarReveal(getResources().getDisplayMetrics().widthPixels / 2, getResources().getDisplayMetrics().heightPixels / 2);
    }
}