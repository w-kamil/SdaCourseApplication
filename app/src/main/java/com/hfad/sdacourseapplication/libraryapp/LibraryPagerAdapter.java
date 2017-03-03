package com.hfad.sdacourseapplication.libraryapp;

import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.hfad.sdacourseapplication.R;

import java.util.List;


public class LibraryPagerAdapter extends PagerAdapter {

    private SharedPreferences sharedPreferences;
    private List<Book> books;

    public LibraryPagerAdapter(List<Book> books, SharedPreferences preferences) {
        this.books = books;
        sharedPreferences = preferences;

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(container.getContext());
        View pageLayout = layoutInflater.inflate(R.layout.single_book_in_library_layout, container, false);
        ImageView image = (ImageView) pageLayout.findViewById(R.id.single_book_image);
        image.setImageResource(books.get(position).getImageResId());

        CheckBox checkBox = (CheckBox) pageLayout.findViewById(R.id.checkbox_single_book);
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(books.get(position).isRead());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Book book = books.get(position);
                book.setRead(isChecked);
                sharedPreferences.edit().putBoolean(String.valueOf(book.getId()), book.isRead()).apply();

            }
        });

        container.addView(pageLayout);
        return pageLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return books.get(position).getTitle();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
