package com.hfad.sdacourseapplication.quiz;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.gson.Gson;
import com.hfad.sdacourseapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String INDEX_KEY = "index_key";
    private int currentQuestuionIndex;
    private boolean wasViewClicked;
    private ValueAnimator objectAnimator;
    private MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        final ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.view_switcher);


        currentQuestuionIndex = getIntent().getIntExtra(INDEX_KEY, 0);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        objectAnimator = ObjectAnimator.ofInt(0, 100);
        objectAnimator.setDuration(10000);
        objectAnimator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });

        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mp.stop();
            }
        });
        progressBar.setProgress(0);


        String uri = "http://www.kakofonia.pl/PL/PLtele/intro.mp3";
        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mp.setDataSource(uri);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new AsyncTask<String, Void, QuizContainer>() {
            String json = null;

            @Override
            protected QuizContainer doInBackground(String... params) {
                try {
                    json = loadQuizJsonFromURL(params[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                QuizContainer quizContainer = new Gson().fromJson(json, QuizContainer.class);
                return quizContainer;
            }

            @Override
            protected void onPostExecute(QuizContainer quizContainer) {
                displayResultInScreen(quizContainer);
                viewSwitcher.setDisplayedChild(1);
                objectAnimator.start();
            }
        }.execute("https://sdacourse-142f9.firebaseio.com/quiz.json");


    }

    private void displayResultInScreen(QuizContainer quizContainer) {
        TextView questionTextView = (TextView) findViewById(R.id.quiz_question);
        QuizQuestion quizQuestion = quizContainer.getQuestions().get(currentQuestuionIndex);
        questionTextView.setText(quizQuestion.getQuestion());
        Button firstButton = (Button) findViewById(R.id.first);
        Button secondButton = (Button) findViewById(R.id.second);
        Button thirdButton = (Button) findViewById(R.id.third);
        Button fourthButton = (Button) findViewById(R.id.fourth);

        SingleAnswer firstAnswer = quizQuestion.getAnswers().get(0);
        SingleAnswer secondAnswer = quizQuestion.getAnswers().get(1);
        SingleAnswer thirdAnswer = quizQuestion.getAnswers().get(2);
        SingleAnswer fourthAnswer = quizQuestion.getAnswers().get(3);

        firstButton.setText(firstAnswer.getText());
        firstButton.setTag(firstAnswer.isCorrect());
        secondButton.setText(secondAnswer.getText());
        secondButton.setTag(secondAnswer.isCorrect());
        thirdButton.setText(thirdAnswer.getText());
        thirdButton.setTag(thirdAnswer.isCorrect());
        fourthButton.setText(fourthAnswer.getText());
        fourthButton.setTag(fourthAnswer.isCorrect());

        firstButton.setOnClickListener(this);
        secondButton.setOnClickListener(this);
        thirdButton.setOnClickListener(this);
        fourthButton.setOnClickListener(this);
    }

    private String loadQuizJsonFromURL(String strinGurl) throws IOException {
        URL url = new URL(strinGurl);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        StringBuilder buf = new StringBuilder();
        InputStream json = httpURLConnection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
        String str;
        while ((str = in.readLine()) != null) {
            buf.append(str);
        }
        in.close();
        return buf.toString();
    }

    @Override
    public void onClick(View v) {
        objectAnimator.end();


        if (!wasViewClicked) {

            if ((Boolean) v.getTag()) {
                Toast.makeText(v.getContext(), "Odpowiedz poprawna", Toast.LENGTH_LONG).show();
                v.setBackgroundColor(Color.GREEN);
            } else {
                Toast.makeText(v.getContext(), "Zla odpowiedz", Toast.LENGTH_LONG).show();
                v.setBackgroundColor(Color.RED);
            }


            v.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                    intent.putExtra(INDEX_KEY, ++currentQuestuionIndex);
                    startActivity(intent);
                }
            }, 3000);
            wasViewClicked = true;
        }
    }
}
