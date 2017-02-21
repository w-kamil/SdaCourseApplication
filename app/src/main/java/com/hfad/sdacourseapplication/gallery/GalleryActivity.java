package com.hfad.sdacourseapplication.gallery;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.hfad.sdacourseapplication.R;
import com.hfad.sdacourseapplication.drawapp.DrawAppActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GalleryActivity extends AppCompatActivity {

    private DrawingPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity_layout);

        File dir = getExternalFilesDir(DrawAppActivity.DRAWING_GALLERY);
        File[] files = dir.listFiles();
        pagerAdapter = new DrawingPagerAdapter(files);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.erase_item_from_gallery){
            pagerAdapter.deleteItem(viewPager.getCurrentItem());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gallery_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
