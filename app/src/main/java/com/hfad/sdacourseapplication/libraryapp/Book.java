package com.hfad.sdacourseapplication.libraryapp;

import android.support.annotation.DrawableRes;


public class Book {

    private int id;
    private boolean isRead;
    @DrawableRes
    private int imageResId;
    private String title;

    public int getId() {
        return id;
    }

    public Book(@DrawableRes int image, String title, int id) {
        this.imageResId = image;

        this.title = title;
        this.id = id;
    }


    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @DrawableRes
    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }
}
