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
import android.widget.EditText;
import android.widget.TextView;

import com.hfad.sdacourseapplication.drawapp.DrawAppActivity;
import com.hfad.sdacourseapplication.fortunetale.FortunetaleActivity;
import com.hfad.sdacourseapplication.gallery.GalleryActivity;
import com.hfad.sdacourseapplication.libraryapp.LibraryApp;
import com.hfad.sdacourseapplication.listapp.ListAppActivity;
import com.hfad.sdacourseapplication.quiz.QuizActivity;
import com.hfad.sdacourseapplication.simpledrawgame.SimpleDrawingGameMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.open_url_button)
    TextView openUrlButton;

    @BindView(R.id.my_note_edit_text)
    EditText notesEditText;

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
        ButterKnife.bind(this);

        notesEditText.setText(readText());
    }

    @OnClick(R.id.draw_button)
    void drawAppStart(View v) {
        Intent intent = new Intent(v.getContext(), DrawAppActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.game_button)
    void drawGameStart(View v) {
        Intent intent = new Intent(v.getContext(), SimpleDrawingGameMainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.library_button)
    void libraryStart(View v) {
        Intent intent = new Intent(v.getContext(), LibraryApp.class);
        startActivity(intent);
    }

    @OnClick(R.id.gallery_button)
    void galleryStart(View v) {
        Intent intent = new Intent(v.getContext(), GalleryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fortunetale_button)
    void fortunetaleAppStart(View v) {
        Intent intent = new Intent(v.getContext(), FortunetaleActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.list_app_button)
    void listAppStart(View v) {
        Intent intent = new Intent(v.getContext(), ListAppActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.quiz_appliacation)
    void quizAppStart(View v) {
        Intent intent = new Intent(v.getContext(), QuizActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.save_note_button)
    void saveText(View v) {
        saveText(notesEditText.getText().toString());
    }


    private String readText() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getString(NOTES_KEY, "");
    }

    private void saveText(String text) {
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