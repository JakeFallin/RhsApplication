package com.example.android.materialdesigncodelab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jake on 3/14/2016.
 */
public class InfoFragment extends Fragment {

    private String urlJsonObj = "http://app.ridgewood.k12.nj.us/rhsstu/api/public/mobile/getdashboard.php";
    private String urlJsonObj2 = "http://app.ridgewood.k12.nj.us/rhsstu/api/public/mobile/getdashboard.php";
    TextView tv;
    private static String TAG = MainActivity.class.getSimpleName();


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.info,
                    container, false);
            makeJsonObjectRequest();


            Calendar c = Calendar.getInstance();

            SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            String formattedDate = df.format(c.getTime());
            tv = (TextView) view.findViewById(R.id.x);
            tv.setText(formattedDate);


            return view;
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
                    TextView textView = (TextView) getView().findViewById(R.id.dayyyy);

                    String dayToday = today.getString("day");
                    textView.setText(dayToday + " Day");

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

}
