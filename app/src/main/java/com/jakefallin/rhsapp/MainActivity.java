/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jakefallin.rhsapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.jakefallin.rhsapp.Fragments.AbsencesFragment;
import com.jakefallin.rhsapp.Fragments.AnnouncementsFragment;
import com.jakefallin.rhsapp.Fragments.ScheduleFragment;
import com.jakefallin.rhsapp.UI.Fab;
import com.jakefallin.rhsapp.UI.FloatLabelLayout;
import com.jakefallin.rhsapp.Util.AppController;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;


/**
 * Provides UI for the main screen.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, DatePickerDialog.OnDateSetListener {

    //for passing correct argument to ScheduleFragment snackbar
    public boolean today = true;

    //declaring UI elements for use in this class only
    private DrawerLayout mDrawerLayout;
    private ViewPager viewPager;
    private SmartTabLayout viewPagerTab;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FABToolbarLayout fabLayout;
    private View backFab, forwardFab, calFab, containerFab, dummy;
    private Calendar lastDayOfSchool, firstDayOfSchool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this, R.xml.settings, false);

        SharedPreferences sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean firstTime = sharedPreferences.getBoolean("first", true);




        lastDayOfSchool = Calendar.getInstance();
        lastDayOfSchool.set(2016, 5, 20);
        firstDayOfSchool = Calendar.getInstance();
        firstDayOfSchool.set(2015, 8, 8);

        //Adapter to make tab load a specific fragment within viewpager
        FragmentPagerItemAdapter a = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Schedule", ScheduleFragment.class)
                .add("Absences", AbsencesFragment.class)
                .add("Announcements", AnnouncementsFragment.class)
                .create());

        //instantiate UI elements
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        backFab = findViewById(R.id.backwardFab);
        forwardFab = findViewById(R.id.forwardFab);
        calFab = findViewById(R.id.calFab);
        fabLayout = (FABToolbarLayout) findViewById(R.id.fabtoolbar);
        containerFab = findViewById(R.id.fabtoolbar_fab);
        dummy = findViewById(R.id.dummy);

        backFab.setOnClickListener(this);
        forwardFab.setOnClickListener(this);
        calFab.setOnClickListener(this);
        containerFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabLayout.show();
            }
        });
        dummy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                fabLayout.hide(); return false;
            }
        });

        //this is fine, UI elements will never be null (hopefully)
        //used to ignore error messages
        assert viewPager != null;
        assert viewPagerTab != null;
        assert navigationView != null;

        //viewpager uses FragmentPagerItemAdapter
        viewPager.setAdapter(a);
        //tab uses fragments (switching with tap/swiping) lifecycle
        viewPagerTab.setViewPager(viewPager);
        //start toolbar
        setSupportActionBar(toolbar);
        setupViewPager(viewPager);

        //setup actionbar with toolbar icons and properties
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        //on any click of the navigation view items
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        //highlight which item is selected
                        menuItem.setChecked(true);
                        int id = menuItem.getItemId();

                        //load TeacherSearch activity
                        if (id == R.id.teachers) {
                            Intent intent = new Intent(MainActivity.this, TeachersActivity.class);
                            startActivity(intent);
                        }
                        //load chat activity
                        else if (id == R.id.chat) {
                        }

                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        fabLayout.hide();


        SharedPreferences s = AppController.getAppContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();

        editor.putString("absenceURL", "http://app.ridgewood.k12.nj.us/api/rhs/absences.php");
        editor.putString("dashboardURL", "http://app.ridgewood.k12.nj.us/api/rhs/dashboard.php");
        editor.putString("announcementsURL", "http://app.ridgewood.k12.nj.us/api/rhs/announcements.php");
        editor.apply();
        finish();
        startActivity(getIntent());
    }

    //default adapter that uses SupportFragmentManager, creates new instance of fragment with a tile name
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ScheduleFragment(), "Today's Schedule");
        adapter.addFragment(new AbsencesFragment(), "Absent Teachers");
        adapter.addFragment(new AnnouncementsFragment(), "Announcments");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.calFab) {
            showDialog();
        }

        if(v.getId() == R.id.forwardFab)
        {
            showDay(true);
        }

        if(v.getId() == R.id.backwardFab)
        {
            showDay(false);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        fabLayout.hide();
        return true;
    }

    //Adapter class to setup viewpager
    static class Adapter extends FragmentPagerAdapter {
        List<Fragment> mFragmentList = new ArrayList<>();
        public final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items tw the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //options menu dialog choices
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //load Settings activity
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);

        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDialog()
    {
        Calendar now = Calendar.getInstance();

        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = DatePickerDialog.newInstance(
                MainActivity.this, year, month, day);

        dpd.setMinDate(firstDayOfSchool);
        dpd.setMaxDate(lastDayOfSchool);
        dpd.setThemeDark(false);
        dpd.vibrate(true);
        dpd.dismissOnPause(true);
        dpd.showYearPickerFirst(false);
        dpd.setAccentColor(getResources().getColor(R.color.maroon));

        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");

        if(dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        SharedPreferences s = AppController.getAppContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();

        String absenceURL;
        String dashboardURL;
        String announcementsURL;

        monthOfYear++;

        if(monthOfYear < 10)
        {
            if(dayOfMonth < 10)
            {
                absenceURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/absences.php?date=" + year + "-0" + monthOfYear + "-0" + dayOfMonth;
                dashboardURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/dashboard.php?date=" + year + "-0" + monthOfYear + "-0" + dayOfMonth;
                announcementsURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/announcements.php?date=" + year + "-0" + monthOfYear + "-0" + dayOfMonth;
            } else {
                absenceURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/absences.php?date=" + year + "-0" + monthOfYear + "-" + dayOfMonth;
                dashboardURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/dashboard.php?date=" + year + "-0" + monthOfYear + "-" + dayOfMonth;
                announcementsURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/announcements.php?date=" + year + "-0" + monthOfYear + "-" + dayOfMonth;            }
        } else if(dayOfMonth < 10) {
            absenceURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/absences.php?date=" + year + "-" + monthOfYear + "-0" + dayOfMonth;
            dashboardURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/dashboard.php?date=" + year + "-" + monthOfYear + "-0" + dayOfMonth;
            announcementsURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/announcements.php?date=" + year + "-" + monthOfYear + "-0" + dayOfMonth;
        } else {
            absenceURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/absences.php?date=" + year + "-" + monthOfYear + "-" + dayOfMonth;
            dashboardURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/absences.php?date=" + year + "-" + monthOfYear + "-" + dayOfMonth;
            announcementsURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/announcements.php?date=" + year + "-" + monthOfYear + "-" + dayOfMonth;
        }

        Log.e("ABSENCE", dashboardURL);

        editor.putString("absenceURL", absenceURL);
        editor.putString("dashboardURL", dashboardURL);
        editor.putString("announcementsURL", announcementsURL);
        editor.apply();
        finish();
        startActivity(getIntent());

    }

    public void showDay(boolean next)
    {

        SharedPreferences s = AppController.getAppContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();

        String absenceURL;
        String dashboardURL;
        String announcementsURL;

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int monthOfYear = now.get(Calendar.MONTH);
        int dayOfMonth = now.get(Calendar.DAY_OF_MONTH);

        monthOfYear++;

        if(next)
        {
            dayOfMonth+=1;
        } else {
            dayOfMonth-=1;
        }

        if(monthOfYear < 10)
        {
            if(dayOfMonth < 10)
            {
                absenceURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/absences.php?date=" + year + "-0" + monthOfYear + "-0" + dayOfMonth;
                dashboardURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/dashboard.php?date=" + year + "-0" + monthOfYear + "-0" + dayOfMonth;
                announcementsURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/announcements.php?date=" + year + "-0" + monthOfYear + "-0" + dayOfMonth;
            } else {
                absenceURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/absences.php?date=" + year + "-0" + monthOfYear + "-" + dayOfMonth;
                dashboardURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/dashboard.php?date=" + year + "-0" + monthOfYear + "-" + dayOfMonth;
                announcementsURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/announcements.php?date=" + year + "-0" + monthOfYear + "-" + dayOfMonth;            }
        } else if(dayOfMonth < 10) {
            absenceURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/absences.php?date=" + year + "-" + monthOfYear + "-0" + dayOfMonth;
            dashboardURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/dashboard.php?date=" + year + "-" + monthOfYear + "-0" + dayOfMonth;
            announcementsURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/announcements.php?date=" + year + "-" + monthOfYear + "-0" + dayOfMonth;
        } else {
            absenceURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/absences.php?date=" + year + "-" + monthOfYear + "-" + dayOfMonth;
            dashboardURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/absences.php?date=" + year + "-" + monthOfYear + "-" + dayOfMonth;
            announcementsURL = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/announcements.php?date=" + year + "-" + monthOfYear + "-" + dayOfMonth;
        }

        editor.putString("absenceURL", absenceURL);
        editor.putString("dashboardURL", dashboardURL);
        editor.putString("announcementsURL", announcementsURL);
        editor.apply();
        finish();
        startActivity(getIntent());
    }

}

