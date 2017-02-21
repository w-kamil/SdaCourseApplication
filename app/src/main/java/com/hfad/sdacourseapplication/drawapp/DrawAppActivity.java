package com.hfad.sdacourseapplication.drawapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hfad.sdacourseapplication.MainActivity;
import com.hfad.sdacourseapplication.R;
import com.hfad.sdacourseapplication.gallery.GalleryActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DrawAppActivity extends AppCompatActivity {


    public static final String DRAWING_GALLERY = "drawing_gallery";
    private SimpleDrawingView simpleDrawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_app_activity_main);
        simpleDrawingView = (SimpleDrawingView) findViewById(R.id.drawing_view);
        Button button = (Button) findViewById(R.id.clear_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.clear();
            }
        });

        Button blueButton = (Button) findViewById(R.id.blue_button);
        Button redButton = (Button) findViewById(R.id.red_button);

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(DrawAppActivity.this, R.color.blue));
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentColor(ContextCompat.getColor(DrawAppActivity.this, R.color.red));
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.clear) {
            simpleDrawingView.clear();
        } else if (item.getItemId() == R.id.save_draw) {
            saveDrawingToFile();
        } else if(item.getItemId() == R.id.drawing_gallery){
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.back_from_draw_app) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveDrawingToFile() {
        File drawingFile = new File(getDrawingGalleryDirectory(), createFileName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(drawingFile);
            Bitmap bitmap = convertViewToBitmap(simpleDrawingView);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String createFileName(){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "my_drawing" + timeStamp + ".png";
    }

    private File getDrawingGalleryDirectory() {
        return getExternalFilesDir(DRAWING_GALLERY);
    }

    private Bitmap convertViewToBitmap(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.draw_app_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
