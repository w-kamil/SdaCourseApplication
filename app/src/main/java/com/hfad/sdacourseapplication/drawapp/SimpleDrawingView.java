package com.hfad.sdacourseapplication.drawapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SimpleDrawingView extends View {

    private Paint paint;
    private List<CustomPath> paths = new ArrayList<CustomPath>();
    @ColorInt
    private int currentlySetColor = Color.BLACK;

    public SimpleDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (CustomPath customPath : paths) {
            paint.setColor(customPath.getColor());
            canvas.drawPath(customPath.getPath(), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                paths.add(new CustomPath(new Point(x, y), currentlySetColor));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                paths.get(paths.size() - 1).getPath().lineTo(x, y);
                break;
            }
        }
        postInvalidate();
        return true;
    }

    public void setCurrentColor(@ColorInt int color) {
        currentlySetColor = color;
    }

    public void clear() {
        paths.clear();
        currentlySetColor = Color.BLACK;
        postInvalidate();
    }




}
