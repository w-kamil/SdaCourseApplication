package com.hfad.sdacourseapplication.listapp;

/**
 * Created by Frod_ on 23.02.2017.
 */

public class ToDoListItem {

    private String text;
    private boolean isChecked;

    public ToDoListItem(String text
    ) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
