package com.example.android.materialdesigncodelab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Jake on 3/14/2016.
 */
public class Settings extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
        // Progress dialog
        private ProgressDialog pDialog;
        // temporary string to show the parsed response

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.settings);
            // Adding Toolbar to Main screen
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
            setSupportActionBar(toolbar);
            // Setting ViewPager for each Tabs

            // Set Tabs inside Toolbar

            // Create Navigation drawer and inlfate layout
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer2);
            // Adding menu icon to Toolbar
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
                supportActionBar.setDisplayHomeAsUpEnabled(true);
            }

            // Set behavior of Navigation drawer
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        // This method will trigger on item Click of navigation menu
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // Set item in checked state
                            // Set item in checked state
                            menuItem.setChecked(true);

                            // TODO: handle navigation

                            // Closing drawer on item click
                            mDrawerLayout.closeDrawers();
                            return true;
                        }
                    });
            // Adding Floating Action Button to bottom right of main view

        }



         class Adapter extends FragmentPagerAdapter {
            private final List<Fragment> mFragmentList = new ArrayList<>();
            private final List<String> mFragmentTitleList = new ArrayList<>();

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
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main2, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings2) {
                Intent intent = new Intent(Settings.this, MainActivity.class);
                startActivity(intent);

            } else if (id == android.R.id.home) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
            return super.onOptionsItemSelected(item);
        }
    }
