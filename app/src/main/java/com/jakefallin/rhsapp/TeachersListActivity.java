package com.jakefallin.rhsapp;

/**
 * Created by Jake on 6/3/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.jakefallin.rhsapp.Adapters.TeacherListAdapter;
import com.jakefallin.rhsapp.Objects.Teacher;
import com.jakefallin.rhsapp.Util.AppController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class TeachersListActivity extends AppCompatActivity {

    private ArrayList<Teacher> teacherList;
    private TeacherListAdapter adapter;
    private Toolbar toolbar;

    private String urlJsonObj = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/extra/teachers.php?query=a";
    private static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;

    private ArrayList<String> period = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_list);

        recyclerView = (RecyclerView) findViewById(R.id.rvTeachers1);
        toolbar = (Toolbar) findViewById(R.id.toolbar4);


        SharedPreferences sp = AppController.getAppContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        urlJsonObj = sp.getString("query", "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/extra/teachers.php?query=a");

        teacherList = new ArrayList<Teacher>();
        adapter = new TeacherListAdapter(teacherList);

        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        makeJsonObjectRequest();

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void makeJsonObjectRequest() {

        JsonArrayRequest req = new JsonArrayRequest(urlJsonObj, new Response.Listener<JSONArray> () {


            @Override
            public void onResponse(JSONArray response) {

                Log.d(TAG, response.toString());

                try {
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject jresponse = response.getJSONObject(i);

                        String first = jresponse.getString("first");
                        String last = jresponse.getString("last");
                        String email = jresponse.getString("email");

                        Teacher t = new Teacher(first, last, email);
                        teacherList.add(t);
                        adapter.notifyDataSetChanged();
                    }

                }catch(JSONException e){
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items tw the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main3, menu);
        return true;
    }

    //options menu dialog choices
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //load Settings activity

        if (id == android.R.id.home) {
            Intent i = new Intent(TeachersListActivity.this, TeachersActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
