package com.hfad.sdacourseapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hfad.sdacourseapplication.drawapp.DrawAppActivity;
import com.hfad.sdacourseapplication.gallery.GalleryActivity;
import com.hfad.sdacourseapplication.listapp.ListAppActivity;
import com.hfad.sdacourseapplication.quiz.QuizActivity;
import com.hfad.sdacourseapplication.simpledrawgame.SimpleDrawingGameMainActivity;

public class MainActivity extends AppCompatActivity {

    private static final String NOTES_KEY = "notes";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        TextView textViewDrawApp = (TextView) findViewById(R.id.draw_button);
        TextView textViewSimpleDrawGame = (TextView) findViewById(R.id.game_button);
        TextView textViewGallery = (TextView) findViewById(R.id.gallery_button);
        TextView textViewListApp = (TextView) findViewById(R.id.list_app_button);

        TextView quizApplication = (TextView) findViewById(R.id.quiz_appliacation);



        textViewDrawApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DrawAppActivity.class);
                startActivity(intent);
            }
        });
        textViewSimpleDrawGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SimpleDrawingGameMainActivity.class);
                startActivity(intent);
            }
        });

        textViewGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GalleryActivity.class);
                startActivity(intent);
            }
        });
        textViewListApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListAppActivity.class);
                startActivity(intent);
            }
        });

        quizApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });


        final EditText notesEditText = (EditText) findViewById(R.id.my_note_edit_text);
        notesEditText.setText(readText());

        Button saveButton = (Button) findViewById(R.id.save_note_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveText(notesEditText.getText().toString());
            }
        });
    }


    private String readText(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getString(NOTES_KEY, "");
    }

    private void saveText(String text){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putString(NOTES_KEY, text).apply();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();

    }


}