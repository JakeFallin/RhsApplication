package com.example.android.materialdesigncodelab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AbsencesFragment extends Fragment {
    private boolean called = false;
    private List<Absence> absenceList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AbsenceAdapter mAdapter;
    private String urlJsonObj = "http://app.ridgewood.k12.nj.us/rhsstu/api/public/getabsencelist.php";
    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view_absences, container, false);

        mAdapter = new AbsenceAdapter(absenceList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(!called)
            makeJsonObjectRequest();
        absenceData();

        return recyclerView;

    }


    private void makeJsonObjectRequest() {

        called = true;
        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {


                    JSONArray absencesToday = response.getJSONArray("absences");
                    String name = "", location = "", reason = "";

                    for (int i = 0; i < absencesToday.length(); i++) {
                        try {

                            name = absencesToday.getJSONObject(i).getString("name");
                            JSONArray infoInAbsence = new JSONArray(absencesToday.getJSONObject(i).getString("info"));

                            for (int j = 0; j < infoInAbsence.length(); j++) {
                                try {
                                     location = infoInAbsence.getJSONObject(j).getString("location");
                                     reason = infoInAbsence.getJSONObject(j).getString("reason");




                                } catch (JSONException e) {

                                }
                                Absence temp = new Absence(name, location, reason);
                                absenceList.add(temp);
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


    private void absenceData() {


        mAdapter.notifyDataSetChanged();
    }
}