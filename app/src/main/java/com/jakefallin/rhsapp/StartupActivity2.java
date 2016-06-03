package com.jakefallin.rhsapp;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.jakefallin.rhsapp.Adapters.DialogAdapter;

import com.jakefallin.rhsapp.Adapters.StartupAdapter;
import com.jakefallin.rhsapp.Dialog.CreateDialog;
import com.jakefallin.rhsapp.Objects.Startup;
import com.jakefallin.rhsapp.Util.AppController;
import com.rohit.recycleritemclicksupport.RecyclerItemClickSupport;

import java.util.ArrayList;

/**
 * Created by Jake on 4/26/2016.
 */

public class StartupActivity2 extends AppCompatActivity implements CreateDialog.onConfirmClickedListener {


    private static final int CONTENT_VIEW_ID = 10101010;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout frame = new FrameLayout(this);
        frame.setId(CONTENT_VIEW_ID);
        setContentView(frame, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        if (savedInstanceState == null) {
            Fragment newFragment = new StartupFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(CONTENT_VIEW_ID, newFragment).commit();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "StartupActivity2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.jakefallin.rhsapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "StartupActivity2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.jakefallin.rhsapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @SuppressLint("ValidFragment")
    public class StartupFragment extends Fragment {

        StartupAdapter startupAdapter;
        ArrayList<Startup> startups;

        RecyclerView recyclerView;

        DialogAdapter da;

        Dialog dialog = null;
        FloatingActionButton floatingActionButton;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            startups = new ArrayList<>();
            startupAdapter = new StartupAdapter(startups);

            dialog = new Dialog(StartupActivity2.this);
            dialog.setContentView(R.layout.dialog_add_class);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            firstTime();



        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.startup, container, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.startupRV);


            RecyclerItemClickSupport.addTo(recyclerView).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                    Startup s = startups.get(position);
                    showDialog(v.getContext(),s.getTitle(), s.getDescription(),
                            s.isCb1(), s.isCb2(), s.isCb3(), s.isCb4(), position);

                }
            });

            floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_normral2);
            floatingActionButton.setImageResource(R.drawable.ic_favorite_black_24dp);
            floatingActionButton.setOnClickListener(fabClickListener);


            recyclerView.setAdapter(startupAdapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


            return view;
        }


        @Override
        public void onInflate(Context context, AttributeSet attrs,
                              Bundle savedInstanceState) {
            super.onInflate(context, attrs, savedInstanceState);
        }


        View.OnClickListener fabClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        };
//
//        public void onBackPressed() {
//
//            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(AppController.getAppContext());
//            SharedPreferences.Editor e = sharedPrefs.edit();
//            Gson gson = new Gson();
//            String json = gson.toJson(startups);
//            e.putString("startupInfo", json);
//            e.putBoolean("first", false);
//            e.apply();
//            Intent intent = new Intent(getActivity(), MainActivity.class);
//            startActivity(intent);
//
//        }

        public void firstTime() {

//            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(AppController.getAppContext());
//            SharedPreferences.Editor editor = sharedPrefs.edit();
//            boolean first = sharedPrefs.getBoolean("firstStartup", true);
//
//            if(first) {
//
//                editor.putBoolean("firstStartup", false);
//                editor.apply();
                startups.add(new Startup("Period 1", "", true, false, true, true));
                startups.add(new Startup("Period 2", "", true, true, false, true));
                startups.add(new Startup("Period 3", "", true, true, true, false));
                startups.add(new Startup("Period 4", "", false, true, true, true));
                startups.add(new Startup("Period 5", "", true, false, true, true));
                startups.add(new Startup("Period 6", "", true, true, false, true));
                startups.add(new Startup("Period 7", "", true, true, true, false));
                startups.add(new Startup("Period 8", "", false, true, true, true));
                startupAdapter.notifyDataSetChanged();

//            } else {
//
//                Gson gson = new Gson();
//                String json = sharedPrefs.getString("startupInfo", null);
//                Type type = new TypeToken<ArrayList<Startup>>() {}.getType();
//                startups = gson.fromJson(json, type);
//                startupAdapter.notifyDataSetChanged();
//
//            }
        }

        public void showDialog(Context context, String name, String desc, boolean m1, boolean m2, boolean m3, boolean m4, final int pos ) {
            final EditText title, teacher;
            Button save, cancel;
            SwitchCompat switchCompat;
            final Spinner spinner;
            final CheckBox cb1, cb2, cb3, cb4;

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.dialog_add_class, null);
            alertDialogBuilder.setView(view);
            alertDialogBuilder.setCancelable(false);
            final AlertDialog dialog = alertDialogBuilder.create();

            cb1 = (CheckBox) view.findViewById(R.id.checkBox1);
            cb2 = (CheckBox) view.findViewById(R.id.checkBox2);
            cb3 = (CheckBox) view.findViewById(R.id.checkBox3);
            cb4 = (CheckBox) view.findViewById(R.id.checkBox4);
            teacher = (EditText) view.findViewById(R.id.etTeacher);
            title = (EditText) view.findViewById(R.id.etClass);
            save = (Button) view.findViewById(R.id.button_save);
            cancel = (Button) view.findViewById(R.id.button_cancel);
            switchCompat = (SwitchCompat) view.findViewById(R.id.switch_free);
            spinner = (Spinner) view.findViewById(R.id.spinner);

            cb1.setChecked(m1);
            cb2.setChecked(m2);
            cb3.setChecked(m3);
            cb4.setChecked(m4);

            title.setHint(name);
            teacher.setHint(desc);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Startup s;
                    if (title.getText().toString().length() == 0) {
                        s = new Startup("Period " + (pos + 1), teacher.getText().toString(), cb1.isChecked(), cb2.isChecked(), cb3.isChecked(), cb4.isChecked());
                    } else {
                        s = new Startup(title.getText().toString(), teacher.getText().toString(), cb1.isChecked(), cb2.isChecked(), cb3.isChecked(), cb4.isChecked());
                    }
                    startups.set(pos, s);
                    startupAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });

            switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    Startup s = new Startup("Free Period", "", true, true, true, true);
                    startups.set(pos, s);
                    startupAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            });

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 0) {
                        String item = parent.getItemAtPosition(position).toString();
                        title.setText(item);
                        spinner.setSelection(0);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            dialog.show();
        }



        public void onBackPressed() {

            save();
            Intent intent = new Intent(StartupActivity2.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        public void save()
        {
            SharedPreferences s = AppController.getAppContext().getSharedPreferences("app", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = s.edit();

            editor.putString("absenceURL", "http://app.ridgewood.k12.nj.us/api/rhs/absences.php");
            editor.putString("dashboardURL", "http://app.ridgewood.k12.nj.us/api/rhs/dashboard.php");
            editor.putString("announcementsURL", "http://app.ridgewood.k12.nj.us/api/rhs/announcements.php");

            Gson gson = new Gson();
            String json = gson.toJson(startups);
            editor.putString("startupInfo", json);
            editor.apply();

        }
    }

    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

}





