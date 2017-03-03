package com.hfad.sdacourseapplication.libraryapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hfad.sdacourseapplication.R;

import java.util.Arrays;
import java.util.List;


public class LibraryApp extends AppCompatActivity {


    private LibraryPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_app);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Book effectiveJavaBook = new Book(R.drawable.effectivejava, "Effective Jave", 1);
        effectiveJavaBook.setRead(sharedPreferences.getBoolean(String.valueOf(effectiveJavaBook.getId()), false));
        Book hfAndroidBook = new Book(R.drawable.hfandroid, "HF Android", 2);
        hfAndroidBook.setRead(sharedPreferences.getBoolean(String.valueOf(hfAndroidBook.getId()), false));
        Book czystyKodBook = new Book(R.drawable.czystykod, "Czysty kod", 3);
        czystyKodBook.setRead(sharedPreferences.getBoolean(String.valueOf(czystyKodBook.getId()), false));
        Book hfDesignPatternsBook = new Book(R.drawable.hfdesignpatterns, "HF Design Patterns", 4);
        hfDesignPatternsBook.setRead(sharedPreferences.getBoolean(String.valueOf(hfDesignPatternsBook.getId()), false));
        List<Book> bookList;
        bookList = Arrays.asList(effectiveJavaBook, hfAndroidBook, czystyKodBook, hfDesignPatternsBook);

        pagerAdapter = new LibraryPagerAdapter(bookList, sharedPreferences);
        viewPager = (ViewPager) findViewById(R.id.library_view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(pagerAdapter);

    }
}
