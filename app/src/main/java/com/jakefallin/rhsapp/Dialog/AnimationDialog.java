package com.jakefallin.rhsapp.Dialog;

/**
 * Created by Jake on 4/26/2016.
 */

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import com.jakefallin.rhsapp.R;

/**
 * Base dialog which adds an enter and exit animation.
 */
public abstract class AnimationDialog extends DialogFragment {

    /**
     * Brand the dialog with our theme color.
     */
    @Override
    public void onStart() {
        super.onStart();
        final Resources res = getResources();
        final int themeColor = res.getColor(R.color.text_black_87);

        // Title
        final int titleId = res.getIdentifier("alertTitle", "id", "android");
        final View title = getDialog().findViewById(titleId);
        if (title != null) {
            ((TextView) title).setTextColor(themeColor);
        }

        // Title divider
        final int titleDividerId = res.getIdentifier("titleDivider", "id", "android");
        final View titleDivider = getDialog().findViewById(titleDividerId);
        if (titleDivider != null) {
            titleDivider.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
    }

    /**
     * Add animations.
     */
    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes();

        //MAYBE BROKEN
    }
}