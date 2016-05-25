

package com.jakefallin.rhsapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.jakefallin.rhsapp.Adapters.TestBaseAdapter;


import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import butterknife.ButterKnife;

/**
 * Created by Jake on 3/14/2016.
 */
public class TeacherSearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    //search bar
    private MenuItem searchMenu;
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private StickyListHeadersListView stickyListHeadersListView;
    private NavigationView navigationView;

    private TestBaseAdapter testBaseAdapter;

    private String[] teachers = {"Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7", "Period 8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_search);

        //setup UI elements
        stickyListHeadersListView = (StickyListHeadersListView) findViewById(R.id.teacherlist);
        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        navigationView = (NavigationView) findViewById(R.id.nav_view3);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer3);
        searchMenu = toolbar.getMenu().findItem(R.id.action_search);

        // Adding Toolbar to Main screen
        setSupportActionBar(toolbar);

        //used for dialogs
        ButterKnife.bind(this);
        testBaseAdapter = new TestBaseAdapter(this);

        //setup the listview with headers
        stickyListHeadersListView.setPadding(0, 0, 0, 0);
        stickyListHeadersListView.setAdapter(testBaseAdapter);
        stickyListHeadersListView.setFastScrollEnabled(true);


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
                        int id = menuItem.getItemId();

                        if(id == R.id.overview)
                        {
                            Intent intent = new Intent(TeacherSearchActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        // TODO: handle navigation

                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

        stickyListHeadersListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                //call method and pass argument of item clicked
                click(position);
            }
        });

    }

    //Show snackbar and bind dialog selection to list
    public void click(int p)
    {
        final Object s = testBaseAdapter.getItem(p);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings3) {
            Intent intent = new Intent(TeacherSearchActivity.this, SettingsActivity.class);
            startActivity(intent);

        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private int getLayoutManagerOrientation(int activityOrientation) {
        if (activityOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            return LinearLayoutManager.VERTICAL;
        } else {
            return LinearLayoutManager.HORIZONTAL;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items tw the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_search, menu);
        return true;
    }

}
