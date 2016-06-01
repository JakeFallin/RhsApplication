package com.jakefallin.rhsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jakefallin.rhsapp.Adapters.TeacherLettersAdapter;
import com.jakefallin.rhsapp.Objects.ExploreBean;
import com.jakefallin.rhsapp.Objects.Letter;
import com.jakefallin.rhsapp.Util.HomeItem;
import com.pacific.adapter.Adapter;
import com.pacific.adapter.AdapterHelper;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;
import com.riontech.staggeredtextgridview.StaggeredTextGridView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                        menuItem.setChecked(true);
                        int id = menuItem.getItemId();

                        if (id == R.id.overview) {
                            Intent intent = new Intent(TeachersActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        // TODO: handle navigation

                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

}
