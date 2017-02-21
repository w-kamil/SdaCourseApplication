package com.hfad.sdacourseapplication.simpledrawgame;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.sdacourseapplication.MainActivity;
import com.hfad.sdacourseapplication.R;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SimpleDrawingGameMainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Integer> images = Arrays.asList(R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four
            , R.drawable.auta, R.drawable.auto, R.drawable.china);
    private Random random = new Random();
    private ImageView firstImageView;
    private ImageView seccondImageView;
    private Button firstPlayerButton;
    private Button secondPlayerButton;
    private CountDownTimer countDownTimer;
    private Button startButton;
    private boolean isRunning = false;
    private Drawable defaultBackground;
    private IntroAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_draw_game_activity_main);
        firstImageView = (ImageView) findViewById(R.id.first_drawing);
        seccondImageView = (ImageView) findViewById(R.id.second_drawing);
        firstPlayerButton = (Button) findViewById(R.id.player_one_button);
        secondPlayerButton = (Button) findViewById(R.id.player_two_button);
        firstPlayerButton.setOnClickListener(this);
        secondPlayerButton.setOnClickListener(this);
        defaultBackground = firstPlayerButton.getBackground();
        startButton = (Button) findViewById(R.id.start_button);
        final TextView introTextView = (TextView) findViewById(R.id.intro_text_view);
        animator = new IntroAnimator(introTextView);

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int firstImage = images.get(random.nextInt(images.size()));
                int secondImage = images.get(random.nextInt(images.size()));
                firstImageView.setImageResource(firstImage);
                firstImageView.setTag(firstImage);
                seccondImageView.setImageResource(secondImage);
                seccondImageView.setTag(secondImage);

            }

            @Override
            public void onFinish() {
                startButton.setVisibility(View.VISIBLE);
                firstImageView.setVisibility(View.INVISIBLE);
                seccondImageView.setVisibility(View.INVISIBLE);
                isRunning = false;
            }
        };


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstPlayerButton.setBackground(defaultBackground);
                secondPlayerButton.setBackground(defaultBackground);

                animator.showIntro(new Runnable() {
                    @Override
                    public void run() {
                        isRunning = true;
                        countDownTimer.start();
                        firstImageView.setVisibility(View.VISIBLE);
                        seccondImageView.setVisibility(View.VISIBLE);
                    }
                });

                startButton.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.back_from_draw_game) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.draw_game_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        if (!isRunning) return;

        if (firstImageView.getTag().equals(seccondImageView.getTag())) {
            v.setBackgroundColor(Color.GREEN);
            if (v.getId() == firstPlayerButton.getId()) {
                secondPlayerButton.setBackgroundColor(Color.RED);
                Toast.makeText(this, "Pierwszy gracz wygral", Toast.LENGTH_LONG).show();
            } else {
                firstPlayerButton.setBackgroundColor(Color.RED);
                Toast.makeText(this, "Drugi gracz wygral", Toast.LENGTH_LONG).show();
            }
        } else {
            v.setBackgroundColor(Color.RED);
            if (v.getId() == firstPlayerButton.getId()) {
                secondPlayerButton.setBackgroundColor(Color.GREEN);
                Toast.makeText(this, "Pierwszy gracz przegral", Toast.LENGTH_LONG).show();
            } else {
                firstPlayerButton.setBackgroundColor(Color.GREEN);
                Toast.makeText(this, "Drugi gracz przegral", Toast.LENGTH_LONG).show();
            }
        }
        startButton.setVisibility(View.VISIBLE);


//        firstImageView.setVisibility(View.INVISIBLE);
//        seccondImageView.setVisibility(View.INVISIBLE);
        countDownTimer.cancel();
        isRunning = false;
    }
}


