package com.jakefallin.rhsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.jakefallin.rhsapp.Adapters.TeacherLettersAdapter;

import com.jakefallin.rhsapp.Objects.Letter;
import com.jakefallin.rhsapp.Util.AppController;
import com.rohit.recycleritemclicksupport.RecyclerItemClickSupport;

import java.util.ArrayList;

/**
 * Created by Jake on 5/31/2016.
 */
public class TeachersActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private ListView listview;
    private StaggeredGridLayoutManager gridLayoutManager;

    private static final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_expanded_list_view);

        toolbar = (Toolbar) findViewById(R.id.toolbarTeacher);
        navigationView = (NavigationView) findViewById(R.id.nav_viewTeacher);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerTeacher);
        recyclerView = (RecyclerView) findViewById(R.id.rvTeacherList);

        recyclerView.setHasFixedSize(true);

        gridLayoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        final ArrayList<Letter> list = new ArrayList<Letter>();
        for (int i = 0; i < letters.length; i++) {
            Letter l = new Letter(letters[i]);
            list.add(l);
        }

        TeacherLettersAdapter rcAdapter = new TeacherLettersAdapter(
                TeachersActivity.this, list);
        recyclerView.setAdapter(rcAdapter);

        RecyclerItemClickSupport.addTo(recyclerView).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                String s = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/extra/teachers.php?query=";
                s += letters[position];

                SharedPreferences sp = AppController.getAppContext().getSharedPreferences("app", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("query", s);
                editor.apply();

                Intent i = new Intent(TeachersActivity.this, TeachersListActivity.class);
                startActivity(i);

            }
        });

        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Set item in checked state
                        // Set item in checked state
                        int id = menuItem.getItemId();

                        if (id == R.id.overview) {
                            Intent intent = new Intent(TeachersActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        //load chat activity
                        else if (id == R.id.settings) {
                            Intent intent = new Intent(TeachersActivity.this, SettingsActivity.class);
                            startActivity(intent);
                        }


                        // TODO: handle navigation

                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items tw the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //load Settings activity
        if (id == R.id.action_settings) {
            Intent intent = new Intent(TeachersActivity.this, SettingsActivity.class);
            startActivity(intent);

        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }
}

