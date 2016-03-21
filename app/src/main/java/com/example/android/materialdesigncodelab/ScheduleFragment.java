package com.example.android.materialdesigncodelab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends Fragment {


    private String jsonResponse;

    private List<Schedule> scheduleList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ScheduleAdapter mAdapter;
    private String urlJsonObj = "http://app.ridgewood.k12.nj.us/rhsstu/api/public/mobile/getdashboard.php";
    private static String TAG = MainActivity.class.getSimpleName();

    private ArrayList<String> period = new ArrayList<String>();
    private ArrayList<String> start = new ArrayList<String>();
    private ArrayList<String> end = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view_schedule, container, false);

        mAdapter = new ScheduleAdapter(scheduleList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        makeJsonObjectRequest();
        scheduleData();

        return recyclerView;

    }

    private void makeJsonObjectRequest() {


        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    JSONObject today = response.getJSONObject("today");

                    boolean nonDay = today.getBoolean("nonDay");

                    String dayToday = today.getString("day");
                    String messageToday = today.getString("message");

                    JSONArray scheduleToday = today.getJSONArray("schedule");


                    for (int i = 0; i < scheduleToday.length(); i++) {
                        try {

                            JSONObject scheduleObj = scheduleToday.getJSONObject(i);

                            String e = scheduleObj.getString("end");
                            String s = scheduleObj.getString("start");
                            String p = scheduleObj.getString("period");

                            if(p.equals("Lunch")){
                                Schedule temp = new Schedule(p, s, e);
                                scheduleList.add(temp);
                                mAdapter.notifyDataSetChanged();
                            }else {
                                Schedule temp = new Schedule("Period " + p, s, e);
                                scheduleList.add(temp);
                                mAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

                // hide the progress dialog
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }


    private void scheduleData() {

        int length = period.size();


    }
}