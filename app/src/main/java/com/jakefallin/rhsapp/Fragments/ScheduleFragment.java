package com.jakefallin.rhsapp.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakefallin.rhsapp.Adapters.ScheduleAdapter;
import com.jakefallin.rhsapp.MainActivity;
import com.jakefallin.rhsapp.Objects.Startup;
import com.jakefallin.rhsapp.R;
import com.jakefallin.rhsapp.Util.AppController;
import com.jakefallin.rhsapp.Objects.ClassTeacherItem;
import com.jakefallin.rhsapp.Objects.Schedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ScheduleFragment extends Fragment {


    private String jsonResponse;

    private ArrayList<Schedule> scheduleListToday;

    private ScheduleAdapter mAdapterToday;
    private String s;
    private String date;
    private String urlJsonObj = "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/dashboard.php";
    private static String TAG = MainActivity.class.getSimpleName();

    private ArrayList<String> period = new ArrayList<String>();

    TextView textView;
    int dayToday = 0;

    ArrayList<Startup> arrayList;
    private ArrayList<String> customTeachers;

    public static ScheduleFragment newInstance(boolean today) {
        ScheduleFragment myFragment = new ScheduleFragment();

        Bundle args = new Bundle();
        args.putBoolean("b", today);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scheduleListToday = new ArrayList<Schedule>();
        customTeachers = new ArrayList<String>();

        mAdapterToday = new ScheduleAdapter(scheduleListToday);

        SharedPreferences s = AppController.getAppContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        urlJsonObj = s.getString("dashboardURL", "http://app.ridgewood.k12.nj.us/new-rhs-website/api/rhs/dashboard.php");

        makeJsonObjectRequest();
        final CoordinatorLayout c = (CoordinatorLayout)getActivity().findViewById(R.id.main_content);
        getCurrentClass();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        textView = (TextView) view.findViewById(R.id.scheduleDay);

        textView.setText("" + s);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvSchedule);

        mAdapterToday = new ScheduleAdapter(scheduleListToday);
        recyclerView.setAdapter(mAdapterToday);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getCurrentClass();
        save();

        return view;
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs,
                          Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);

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
                    s = "" +  response.get("day");
                    textView.setText("" + s);

                    date = "" + response.get("date");


                    JSONArray scheduleToday = response.getJSONArray("schedule");

                    for (int i = 0; i < scheduleToday.length(); i++) {
                        try {

                            JSONObject scheduleObj = scheduleToday.getJSONObject(i);

                            String e = scheduleObj.getString("end");
                            String s = scheduleObj.getString("start");
                            String p = scheduleObj.getString("label");

                            Startup c = getData(p);

                            if (p.equals("Lunch") || c.getTitle().equals("")) {
                                Schedule temp = new Schedule(p, s, e);
                                scheduleListToday.add(temp);
                                mAdapterToday.notifyDataSetChanged();
                            } else if (!p.matches(".*\\d+.*")) {

                                Schedule temp = new Schedule(p, s, e);
                                scheduleListToday.add(temp);
                                mAdapterToday.notifyDataSetChanged();
                            } else {


                                if (c.getTitle().equals("")) {
                                    Schedule temp = new Schedule(p, s, e);
                                    scheduleListToday.add(temp);
                                    mAdapterToday.notifyDataSetChanged();
                                }
                                Schedule temp = new Schedule(c.getTitle(), c.getDescription(), s, e, c.isCb1(), c.isCb2(), c.isCb3(), c.isCb4());
                                scheduleListToday.add(temp);
                                mAdapterToday.notifyDataSetChanged();

                            }

                        }catch(JSONException e){
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

    private Startup getData(String temp) {

        SharedPreferences s = AppController.getAppContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = s.getString("startupInfo", null);
        Type type = new TypeToken<ArrayList<Startup>>() {}.getType();
        arrayList = gson.fromJson(json, type);

        String numberOnly = temp.replaceAll("[^0-9]", "");
        Integer yo = 1;
        if (numberOnly.length() > 0) {
            yo = Integer.parseInt(numberOnly.trim());
        }

        Startup startup = arrayList.get(yo - 1);

        return startup;
    }

    public void getCurrentClass() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        String strDate = sdf.format(c.getTime());
        int intDate = Integer.parseInt(strDate);

        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(AppController.getAppContext());
        boolean is24H = appPreferences.getBoolean("24h", false);


        Log.d("Current date: ", strDate);
        Log.d("Current date: ", strDate);


        for (int i = 0; i < scheduleListToday.size(); i++) {
            Schedule s = scheduleListToday.get(i);
            String start = s.getStart24();
            String end = s.getEnd24();



                String newStart = start.replaceAll("\\D+", "");
                String newEnd = end.replaceAll("\\D+", "");
                int intStart = Integer.parseInt(newStart);
                int intEnd = Integer.parseInt(newEnd);

                if ((intStart <= intDate) && (intDate <= intEnd)) {
                    mAdapterToday.setSelected(i);
                }
        }
    }

    public void save()
    {
        SharedPreferences s = AppController.getAppContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();

        editor.putString("date", date);
        Gson gson = new Gson();
        String json = gson.toJson(scheduleListToday);
        editor.putString("scheduleInfo", json);
        editor.apply();
    }

}
