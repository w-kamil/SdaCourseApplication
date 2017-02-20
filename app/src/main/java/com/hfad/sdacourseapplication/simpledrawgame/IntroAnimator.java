package com.hfad.sdacourseapplication.simpledrawgame;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextView;

public class IntroAnimator {

    private final TextView textView;

    public IntroAnimator(TextView textView) {
        this.textView = textView;
    }

    public void showIntro(Runnable animationEnds){
        animateCounter(textView, animationEnds, 3);
    }

    private void animateCounter(final TextView introTextView, final Runnable animationEnds, final int counter) {
        String text = counter == 0 ? "START" : String.valueOf(counter);
        introTextView.setText(text);
        introTextView.setAlpha(1);
        introTextView.setScaleX(1);
        introTextView.setScaleY(1);
        introTextView.animate().alpha(0).scaleX(3).scaleY(3).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (counter > 0) {
                    animateCounter(introTextView, animationEnds, counter - 1);
                } else {
                    animationEnds.run();
                }

            }
        }).start();
    }

}
