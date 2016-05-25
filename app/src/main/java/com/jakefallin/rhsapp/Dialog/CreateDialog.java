package com.jakefallin.rhsapp.Dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.jakefallin.rhsapp.R;
import com.jakefallin.rhsapp.StartupActivity;
import com.jakefallin.rhsapp.UI.CustomObjectAnimator;
import com.jakefallin.rhsapp.UI.FloatLabelLayout;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Dialog for adding additional exercises.
 */
public class CreateDialog extends AnimationDialog implements
        CompoundButton.OnCheckedChangeListener,
        View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    public static final String TAG = CreateDialog.class.getName();

    private static final String EXTRA_EXERCISE_ID = "exerciseId";
    private static final String EXTRA_ADDITIONAL_EXERCISE = "additionalExercise";
    private static final String EXTRA_TITLE = "title";

    private AutoCompleteTextView mAutoCompleteNameTextView;
    private EditText mGoalEditText, mWeightEditText, mPercentageEditText;
    private SwitchCompat mMainExerciseSwitch;
    private Spinner mMainExerciseSpinner;
    private FloatLabelLayout mPercentageLabelLayout;
    private boolean mForced = false;

    public CreateDialog() {
    }

    /**
     * Static creation to avoid problems on rotation.
     */
    public static CreateDialog newInstance(String title,

                                           int id,
                                           StartupActivity targetFragment) {
        CreateDialog dialog = new CreateDialog();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE, title);
        bundle.putInt(EXTRA_EXERCISE_ID, id);
        dialog.setArguments(bundle);

        return dialog;
    }

    /**
     * Called when the dialog is started.
     */
    @Override
    public void onStart() {
        super.onStart();
        AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positive = d.getButton(Dialog.BUTTON_POSITIVE);
            if (positive != null) {
                positive.setOnClickListener(this);
            }
        }
    }

    /**
     * Called when the dialog is supposed to be created.
     */
    @NonNull
    @Override
    @SuppressLint("InflateParams")
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString(EXTRA_TITLE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_class, null, false);
        ((TextView) view.findViewById(R.id.title)).setText(title);
        builder.setView(view);
        return builder.create();
    }



    /**
     * Called when the view is attached to the activity.
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getListener() == null) {
            throw new ClassCastException("Class doesn't implement onConfirmClickedListener");
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mMainExerciseSpinner.setEnabled(isChecked);
        mPercentageEditText.setEnabled(isChecked);
        if (!isChecked) {
            mPercentageEditText.setText("");
            mMainExerciseSpinner.setSelection(0);
            mWeightEditText.setSelection(mWeightEditText.getText().length());

            if (mPercentageEditText.hasFocus()) {
                mPercentageEditText.clearFocus();
            }
            if (mPercentageLabelLayout != null) {
                mPercentageLabelLayout.hideLabel(true);
            }
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if ((mPercentageEditText.getText().length() > 0)) {
            mForced = true;

        }
    }

    /**
     * Get an ArrayList with names of all stored additional exercises.
     */
    private ArrayList<String> getStoredAdditionalExerciseNames() {
        ArrayList<String> items = new ArrayList<String>();

        String[] staticList = getResources().getStringArray(R.array.additional_exercises);
        items.addAll(Arrays.asList(staticList));

        return items;
    }

    /**
     * Init the views from an already existing exercise
     */


    /**
     * Check the required fields so they have data.
     */
    private boolean allDataIsOk() {
        if (mAutoCompleteNameTextView.getText() != null
                && mAutoCompleteNameTextView.getText().toString().trim().isEmpty()) {
            CustomObjectAnimator.nope(mAutoCompleteNameTextView).start();
            return false;
        } else if (mGoalEditText.getText() != null
                && mGoalEditText.getText().toString().trim().isEmpty()) {
            CustomObjectAnimator.nope(mGoalEditText).start();
            return false;
        }
        return true;
    }

    /**
     * Set the weight based of an entered percentage.
     */
    private void setWeightFromPercentage(String name, int percentage) {


    }

    /**
     * Get the position of the exercise corresponding to a name.
     */


    /**
     * Get the name of an exercise corresponding to a position
     */



    /**
     * Create an additional exercise.
     */


    /**
     * TextWatcher for tracking changes in the mPercentageEditText field
     */
    private final TextWatcher percentageWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (mPercentageEditText.getText().length() > 0) {
                mForced = true;

            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    /**
     * TextWatcher for keeping track of changes in the mWeight field
     */
    private final TextWatcher weightWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!mForced) {
                mMainExerciseSwitch.setChecked(false);
            }
            mForced = false;
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    /**
     * Make sure our target is listening for our confirmation
     */
    private onConfirmClickedListener getListener() {
        if (getActivity() instanceof onConfirmClickedListener) {
            return (onConfirmClickedListener) getActivity();
        } else if (getTargetFragment() instanceof onConfirmClickedListener) {
            return (onConfirmClickedListener) getTargetFragment();
        }
        return null;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * Interface for confirming the adding of an additional exercise.
     */
    public interface onConfirmClickedListener {
        /**
         * Called when the positive button has been clicked.
         */
    }
}