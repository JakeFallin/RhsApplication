package com.jakefallin.rhsapp.UI;

/**
 * Created by Gordon Wong on 7/17/2015.
 *
 * Sample floating action button implementation.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.gordonwong.materialsheetfab.AnimatedFab;
import com.jakefallin.rhsapp.R;

public class Fab extends FloatingActionButton implements AnimatedFab {

    private static final int FAB_ANIM_DURATION = 200;

    public Fab(Context context) {
        super(context);
    }

    public Fab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Fab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Shows the FAB.
     */
    @Override
    public void show() {
        show(0, 0);
    }

    /**
     * Shows the FAB and sets the FAB's translation.
     *
     * @param translationX translation X value
     * @param translationY translation Y value
     */
    @Override
    public void show(float translationX, float translationY) {
        // Set FAB's translation
        setTranslation(translationX, translationY);

        // Only use scale animation if FAB is hidden
        if (getVisibility() != View.VISIBLE) {
            // Pivots indicate where the animation begins from
            float pivotX = getPivotX() + translationX;
            float pivotY = getPivotY() + translationY;

            ScaleAnimation anim;
            // If pivots are 0, that means the FAB hasn't been drawn yet so just use the
            // center of the FAB
            if (pivotX == 0 || pivotY == 0) {
                anim = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
            } else {
                anim = new ScaleAnimation(0, 1, 0, 1, pivotX, pivotY);
            }

            // Animate FAB expanding
            anim.setDuration(FAB_ANIM_DURATION);
            anim.setInterpolator(getInterpolator());
            startAnimation(anim);
        }
        setVisibility(View.VISIBLE);
    }

    /**
     * Hides the FAB.
     */
    @Override
    public void hide() {
        // Only use scale animation if FAB is visible
        if (getVisibility() == View.VISIBLE) {
            // Pivots indicate where the animation begins from
            float pivotX = getPivotX() + getTranslationX();
            float pivotY = getPivotY() + getTranslationY();

            // Animate FAB shrinking
            ScaleAnimation anim = new ScaleAnimation(1, 0, 1, 0, pivotX, pivotY);
            anim.setDuration(FAB_ANIM_DURATION);
            anim.setInterpolator(getInterpolator());
            startAnimation(anim);
        }
        setVisibility(View.INVISIBLE);
    }

    private void setTranslation(float translationX, float translationY) {
        animate().setInterpolator(getInterpolator()).setDuration(FAB_ANIM_DURATION)
                .translationX(translationX).translationY(translationY);
    }

    private Interpolator getInterpolator() {
        return AnimationUtils.loadInterpolator(getContext(), R.interpolator.msf_interpolator);
    }

    /**
     * @author Eric Frohnhoefer
     */
    public static class UnderlineTextView  extends TextView {
        private final Paint mPaint = new Paint();
        private int mUnderlineHeight = 0;

        public UnderlineTextView(Context context) {
            this(context, null);
        }

        public UnderlineTextView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public UnderlineTextView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);

            init(context, attrs);
        }

        private void init(Context context, AttributeSet attrs) {
            Resources r = getResources();
            mUnderlineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics());
        }

        @Override
        public void setPadding(int left, int top, int right, int bottom) {
            super.setPadding(left, top, right, bottom + mUnderlineHeight);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Draw the underline the same color as the text
            mPaint.setColor(getTextColors().getDefaultColor());
            canvas.drawRect(0, getHeight() - mUnderlineHeight, getWidth(), getHeight(), mPaint);
        }
    }
}