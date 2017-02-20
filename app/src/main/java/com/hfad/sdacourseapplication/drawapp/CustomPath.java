package com.hfad.sdacourseapplication.drawapp;


import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.ColorInt;

public class CustomPath {


    @ColorInt
    int color;
    private Path path;


    public CustomPath(Point point, int color) {
        this.color = color;
        this.path = new Path();
        this.path.moveTo(point.x, point.y);
    }

    public int getColor() {
        return color;
    }

    public Path getPath() {
        return path;
    }
}
