package com.jakefallin.rhsapp;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakefallin.rhsapp.Dialog.CreateDialog;
import com.jakefallin.rhsapp.Util.AppController;
import com.jakefallin.rhsapp.Util.AppInfo;
import com.jakefallin.rhsapp.Objects.ClassTeacherItem;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Jake on 4/26/2016.
 */
public class StartupActivity extends AppCompatActivity implements CreateDialog.onConfirmClickedListener {

    int ii;
    private static final int SCALE_DELAY = 30;
    SharedPreferences prefs;
    private LinearLayout mRowContainer;
    final Context context = this;
    Dialog dialog = null;
    AutoCompleteTextView tvClass, tvTeacher;
    Spinner spinner;
    Button cancel, save;
    View viewForDialog, rowView;
    View v1, v2, v3, v4, v5, v6, v7, v8;
    TextView titleView, descriptionView;
    FloatingActionButton floatingActionButton;
    CheckBox c1, c2, c3, c4;
    CheckBox cb1, cb2, cb3, cb4;
    SwitchCompat switchCompat;
    ClassTeacherItem classTeacherItem;
    ArrayList<ClassTeacherItem> ctiList;
    ArrayList<ClassTeacherItem> arrayListCTI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(AppController.getAppContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        boolean firstTime = sharedPrefs.getBoolean("startupFirst", true);


        ctiList = new ArrayList<>();
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_class);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        mRowContainer = (LinearLayout) findViewById(R.id.row_containerStartup);


        viewForDialog = mRowContainer.findViewById(R.id.period1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarStartup);
        toolbar.setTitle("HI");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (tvClass != null) {
            tvClass.setOnEditorActionListener(new AutoCompleteTextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    return actionId == EditorInfo.IME_ACTION_SEARCH;
                }

            });
        }
        // Handle Back Navigation :D
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }

        });

        // Fab Button
        floatingActionButton = (FloatingActionButton) this.findViewById(R.id.fab_normral1);
        floatingActionButton.setImageResource(R.drawable.ic_favorite_black_24dp);
        floatingActionButton.setOnClickListener(fabClickListener);

        //getWindow().getEnterTransition().removeListener(this);

        for (int i = 1; i < mRowContainer.getChildCount(); i++) {
            View rowView = mRowContainer.getChildAt(i);
            rowView.animate().setStartDelay(100 + i * SCALE_DELAY).scaleX(1).scaleY(1);
        }

        //toolbar.setLogo(mAppInfo.getIcon());
        toolbar.setTitle("Set Classes");


        if(firstTime) {
            v1 = mRowContainer.findViewById(R.id.period1);
            fillRow(v1, "Period 1", "", true, true, true, false);
            v2 = mRowContainer.findViewById(R.id.period2);
            fillRow(v2, "Period 2", "", false, true, true, true);
            v3 = mRowContainer.findViewById(R.id.period3);
            fillRow(v3, "Period 3", "", true, false, true, true);
            v4 = mRowContainer.findViewById(R.id.period4);
            fillRow(v4, "Period 4", "", true, true, false, true);
            v5 = mRowContainer.findViewById(R.id.period5);
            fillRow(v5, "Period 5", "", true, true, true, false);
            v6 = mRowContainer.findViewById(R.id.period6);
            fillRow(v6, "Period 6", "", false, true, true, true);
            v7 = mRowContainer.findViewById(R.id.period7);
            fillRow(v7, "Period 7", "", true, false, true, true);
            v8 = mRowContainer.findViewById(R.id.period8);
            fillRow(v8, "Period 8", "", true, true, false, true);
        } else {
            getData();

            v1 = mRowContainer.findViewById(R.id.period1);
            fillRow(v1, arrayListCTI.get(0).getMyClass() , arrayListCTI.get(0).getMyTeacher(), arrayListCTI.get(0).isDay1(), arrayListCTI.get(0).isDay2(), arrayListCTI.get(0).isDay3(), arrayListCTI.get(0).isDay4());
            v2 = mRowContainer.findViewById(R.id.period2);
            fillRow(v2, arrayListCTI.get(1).getMyClass() , arrayListCTI.get(1).getMyTeacher(), arrayListCTI.get(1).isDay1(), arrayListCTI.get(1).isDay2(), arrayListCTI.get(1).isDay3(), arrayListCTI.get(1).isDay4());
            v3 = mRowContainer.findViewById(R.id.period3);
            fillRow(v3, arrayListCTI.get(2).getMyClass() , arrayListCTI.get(2).getMyTeacher(), arrayListCTI.get(2).isDay1(), arrayListCTI.get(2).isDay2(), arrayListCTI.get(2).isDay3(), arrayListCTI.get(2).isDay4());
            v4 = mRowContainer.findViewById(R.id.period4);
            fillRow(v4, arrayListCTI.get(3).getMyClass() , arrayListCTI.get(3).getMyTeacher(), arrayListCTI.get(3).isDay1(), arrayListCTI.get(3).isDay2(), arrayListCTI.get(3).isDay3(), arrayListCTI.get(3).isDay4());
            v5 = mRowContainer.findViewById(R.id.period5);
            fillRow(v5, arrayListCTI.get(4).getMyClass() , arrayListCTI.get(4).getMyTeacher(), arrayListCTI.get(4).isDay1(), arrayListCTI.get(4).isDay2(), arrayListCTI.get(4).isDay3(), arrayListCTI.get(4).isDay4());
            v6 = mRowContainer.findViewById(R.id.period6);
            fillRow(v6, arrayListCTI.get(5).getMyClass() , arrayListCTI.get(5).getMyTeacher(), arrayListCTI.get(5).isDay1(), arrayListCTI.get(5).isDay2(), arrayListCTI.get(5).isDay3(), arrayListCTI.get(5).isDay4());
            v7 = mRowContainer.findViewById(R.id.period7);
            fillRow(v7, arrayListCTI.get(6).getMyClass() , arrayListCTI.get(6).getMyTeacher(), arrayListCTI.get(6).isDay1(), arrayListCTI.get(6).isDay2(), arrayListCTI.get(6).isDay3(), arrayListCTI.get(6).isDay4());
            v8 = mRowContainer.findViewById(R.id.period8);
            fillRow(v8, arrayListCTI.get(7).getMyClass() , arrayListCTI.get(7).getMyTeacher(), arrayListCTI.get(7).isDay1(), arrayListCTI.get(7).isDay2(), arrayListCTI.get(7).isDay3(), arrayListCTI.get(7).isDay4());
        }
    }

    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    };

    public void fillRow(View view, final String title, final String description, final boolean d1, final boolean d2, final boolean d3, final boolean d4) {

        boolean temp1, temp2, temp3, temp4;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewForDialog = v;
                titleView = (TextView) v.findViewById(R.id.titleStartup);
                descriptionView = (TextView) v.findViewById(R.id.descriptionStartup);

                cb1 = (CheckBox) v.findViewById(R.id.cb1);
                cb2 = (CheckBox) v.findViewById(R.id.cb2);
                cb3 = (CheckBox) v.findViewById(R.id.cb3);
                cb4 = (CheckBox) v.findViewById(R.id.cb4);

                cb1.setChecked(d1);
                cb2.setChecked(d2);
                cb3.setChecked(d3);
                cb4.setChecked(d4);

                c1 = (CheckBox) dialog.findViewById(R.id.checkBox1);
                c2 = (CheckBox) dialog.findViewById(R.id.checkBox2);
                c3 = (CheckBox) dialog.findViewById(R.id.checkBox3);
                c4 = (CheckBox) dialog.findViewById(R.id.checkBox4);

                switchCompat = (SwitchCompat) dialog.findViewById(R.id.switch_free);
                spinner = (Spinner) dialog.findViewById(R.id.spinner);
                cancel = (Button) dialog.findViewById(R.id.button_cancel);
                save = (Button) dialog.findViewById(R.id.button_save);


                c1.setChecked(d1);
                c2.setChecked(d2);
                c3.setChecked(d3);
                c4.setChecked(d4);
                tvClass.setText(title);
                tvTeacher.setText(description);

                dialog.show();


                switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        titleView.setText("Free Period");
                        descriptionView.setText("");
                        cb1.setChecked(true);
                        cb2.setChecked(true);
                        cb3.setChecked(true);
                        cb4.setChecked(true);
                        spinner.setSelection(0, false);
                        buttonView.setChecked(false);

                        dialog.hide();
                    }
                });

                save.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View vv) {

                        titleView.setText(tvClass.getText().toString());
                        descriptionView.setText(tvTeacher.getText().toString());
                        cb1.setChecked(c1.isChecked());
                        cb2.setChecked(c2.isChecked());
                        cb3.setChecked(c3.isChecked());
                        cb4.setChecked(c4.isChecked());
                        tvClass.setText("");
                        tvTeacher.setText("");
                        spinner.setSelection(0, false);
                        c1.setChecked(d1);
                        c2.setChecked(d2);
                        c3.setChecked(d3);
                        c4.setChecked(d4);

                        dialog.hide();

                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View vv) {

                        c1.setChecked(d1);
                        c2.setChecked(d2);
                        c3.setChecked(d3);
                        c4.setChecked(d4);
                        spinner.setSelection(0, false);

                        dialog.hide();
                    }
                });

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position != 0) {
                            String item = parent.getItemAtPosition(position).toString();
                            tvTeacher.setText(item);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });


    }

    @Override
    public void onBackPressed() {
        for (int x = 0; x < mRowContainer.getChildCount(); x++) {
            rowView = mRowContainer.getChildAt(x);
            saveData(rowView);
        }
        for (ii = mRowContainer.getChildCount() - 1; ii > 0; ii--) {
            rowView = mRowContainer.getChildAt(ii);
            ViewPropertyAnimator propertyAnimator = rowView.animate().setStartDelay((mRowContainer.getChildCount() - 1 - ii) * SCALE_DELAY)
                    .scaleX(0).scaleY(0);


            prefs = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);

            propertyAnimator.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) && (ii == 1)) {

                    } else {

                        SharedPreferences.Editor ed = prefs.edit();
                        ed.putBoolean("first", true);
                        ed.commit();

                        Intent intent = new Intent(StartupActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            });

        }
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(AppController.getAppContext());
        SharedPreferences.Editor e = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ctiList);
        e.putString("taco", json);
        e.commit();

        SharedPreferences.Editor ed = prefs.edit();
        ed.putBoolean("first", true);
        ed.apply();
        Intent intent = new Intent(StartupActivity.this, MainActivity.class);
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    public void saveData(View v) {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        titleView = (TextView) v.findViewById(R.id.titleStartup);
        descriptionView = (TextView) v.findViewById(R.id.descriptionStartup);
        cb1 = (CheckBox) v.findViewById(R.id.cb1);
        cb2 = (CheckBox) v.findViewById(R.id.cb2);
        cb3 = (CheckBox) v.findViewById(R.id.cb3);
        cb4 = (CheckBox) v.findViewById(R.id.cb4);

        if (titleView.getText().equals("Free")) {
            classTeacherItem = new ClassTeacherItem(true);
        } else {
            classTeacherItem = new ClassTeacherItem(
                    titleView.getText().toString(),
                    descriptionView.getText().toString(),
                    cb1.isChecked(), cb2.isChecked(), cb3.isChecked(), cb4.isChecked());
        }

        ctiList.add(classTeacherItem);
        Log.d("AddedAddedAdded", "Added");
    }

    public void getData() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(AppController.getAppContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString("taco", null);
        Type type = new TypeToken<ArrayList<ClassTeacherItem>>() {
        }.getType();
        arrayListCTI = gson.fromJson(json, type);
    }
}




