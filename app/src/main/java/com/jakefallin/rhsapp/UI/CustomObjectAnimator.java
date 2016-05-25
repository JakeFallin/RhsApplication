package com.jakefallin.rhsapp.UI;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

import com.jakefallin.rhsapp.R;


/**
 * Animator class based of https://plus.google.com/118417777153109946393/posts/FABaJhRMCuy
 */
public class CustomObjectAnimator {

    /**
     * Animate a shaking factor to indicate something is wrong.
     */
    public static ObjectAnimator nope(View view) {
        int delta = 16;
        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(.10f, -delta),
                Keyframe.ofFloat(.26f, delta),
                Keyframe.ofFloat(.42f, -delta),
                Keyframe.ofFloat(.58f, delta),
                Keyframe.ofFloat(.74f, -delta),
                Keyframe.ofFloat(.90f, delta),
                Keyframe.ofFloat(1f, 0f)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhTranslateX).
                setDuration(500);
    }
}