package com.jakefallin.rhsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Jake on 3/14/2016.
 */
public class SettingsActivity extends AppCompatActivity {

        private DrawerLayout mDrawerLayout;
        private Toolbar toolbar;
        private ActionBar actionBar;
        // temporary string to show the parsed response

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);

            // Set behavior of Navigation drawer
//            assert navigationView != null;
//            navigationView.setNavigationItemSelectedListener(
//                    new NavigationView.OnNavigationItemSelectedListener() {
//                        @Override
//                        public boolean onNavigationItemSelected(MenuItem menuItem) {
//
//                            //highlight which item is selected
//                            menuItem.setChecked(true);
//                            int id = menuItem.getItemId();
//
//                            //load TeacherSearch activity
//                            if (id == R.id.teachers) {
//                                Intent intent = new Intent(SettingsActivity.this, TeacherSearch.class);
//                                startActivity(intent);
//                            }
//                            //load chat activity
//                            else if (id == R.id.chat) {
//                                ConversationActivity.show(SettingsActivity.this);
//                            }
//
//                            // Closing drawer on item click
//                            mDrawerLayout.closeDrawers();
//                            return true;
//                        }
//                    });
            // Adding Floating Action Button to bottom right of main view

            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new SettingsFragment())
                    .commit();

        }

    public static class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        SharedPreferences sharedPref;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.settings);

            sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String defaultPd = sharedPref.getString("pd", "default value");

            for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
                initSummary(getPreferenceScreen().getPreference(i));
            }

        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen()
                    .getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);

        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen()
                    .getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

//            if (key.equals("email")) {
//                Preference pref = findPreference(key);
//                pref.setSummary(sharedPreferences.getString(key, "hi"));
//            }

            updatePreferences(findPreference(key));

        }

        private void initSummary(Preference p) {
            if (p instanceof PreferenceCategory) {
                PreferenceCategory cat = (PreferenceCategory) p;
                for (int i = 0; i < cat.getPreferenceCount(); i++) {
                    initSummary(cat.getPreference(i));
                }
            } else {
                updatePreferences(p);
            }
        }

        private void updatePreferences(Preference p) {
            if (p instanceof EditTextPreference) {
                EditTextPreference editTextPref = (EditTextPreference) p;
                p.setSummary(editTextPref.getText());

            }
        }
    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main_settings, menu);
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
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);

            } else if (id == android.R.id.home) {
            }
            return super.onOptionsItemSelected(item);
        }

    public void onBackPressed(){
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }
    }
