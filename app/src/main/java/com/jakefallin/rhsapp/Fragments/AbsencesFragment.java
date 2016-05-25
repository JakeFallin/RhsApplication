package com.jakefallin.rhsapp.Fragments;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakefallin.rhsapp.Adapters.AbsenceAdapter;
import com.jakefallin.rhsapp.Adapters.ScheduleAdapter;
import com.jakefallin.rhsapp.MainActivity;
import com.jakefallin.rhsapp.Objects.ClassTeacherItem;
import com.jakefallin.rhsapp.Objects.ExploreBean;
import com.jakefallin.rhsapp.Objects.MenuBean;
import com.jakefallin.rhsapp.Objects.Schedule;
import com.jakefallin.rhsapp.Objects.Startup;
import com.jakefallin.rhsapp.R;
import com.jakefallin.rhsapp.Objects.Absence;
import com.jakefallin.rhsapp.Util.AppController;
import com.pacific.adapter.ExpandableAdapter;
import com.pacific.adapter.ExpandableAdapterHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.goncalves.pugnotification.notification.Load;
import br.com.goncalves.pugnotification.notification.PugNotification;

public class AbsencesFragment extends Fragment {
    private boolean called = false;
    private List<Absence> absenceList;
    private RecyclerView recyclerView;
    private AbsenceAdapter mAdapter;
    private String urlJsonObj = "http://app.ridgewood.k12.nj.us/rhsstu/api/public/getabsencelist.php";
    private static String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Schedule> arrayList;
    private int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_absences, container, false);

        absenceList = new ArrayList<>();
        mAdapter = new AbsenceAdapter(absenceList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        makeJsonObjectRequest();
        absenceData();

        save();

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
                    String name = "";
                    String nextName = "New Name String";
                    List<Pair<String,String>> locations = new ArrayList<Pair<String, String>>();
                    Pair <String, String> Loc;
                    count++;

                    for (int i = 0; i < absencesToday.length(); i++) {
                        try {

                            name = absencesToday.getJSONObject(i).getString("name");
                            JSONArray infoInAbsence = new JSONArray(absencesToday.getJSONObject(i).getString("info"));

                            for (int j = 0; j < infoInAbsence.length(); j++) {
                                try {
                                    Loc = new Pair(infoInAbsence.getJSONObject(j).getString("location"), infoInAbsence.getJSONObject(j).getString("reason"));
                                    locations.add(Loc);
                                } catch (JSONException e) {
                                }
                                Absence temp = new Absence(name, locations);
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

        mAdapter.notifyDataSetChanged();


        ArrayList<String> aSchedule = new ArrayList<>();

        String p = String.valueOf(count);
        Log.e("TESTY", p);

//        for(int i = 0; i < absenceList.size(); i++)
//        {
//            String s = getData(absenceList.get(i).getName());
//            Log.e("TESTY", s);
//            aSchedule.add(s);
//        }

        makeNotification(aSchedule);

    }

    private void absenceData() {


        mAdapter.notifyDataSetChanged();
    }

    public void makeNotification(ArrayList<String> s)
    {

        int mId = 1;
        String absentTeachers = "";

        if(s.size() == 0)
        {
            absentTeachers = "No Absent Teachers Today!";
        } else {
            absentTeachers = "Your Teachers: ";

            for(int i = 0; i < s.size(); i++)
            {
                absentTeachers = absentTeachers + s.get(i) + ", ";
            }
            absentTeachers = absentTeachers + " Are Absent Today!";
        }

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(AppController.getAppContext())
                        .setSmallIcon(R.drawable.ic_cal)
                        .setContentTitle("Teacher Absences")
                        .setContentText(absentTeachers)
                        .setAutoCancel(true)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setVibrate(new long[] {100, 200, 300});

        Intent resultIntent = new Intent(AppController.getAppContext(), MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(AppController.getAppContext());
        stackBuilder.addParentStack(MainActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager)
                AppController.getAppContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mId, mBuilder.build());

    }

    public void save()
    {
        SharedPreferences s = AppController.getAppContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();

        Gson gson = new Gson();
        String json = gson.toJson(absenceList);
        editor.putString("absenceInfo", json);
        editor.apply();
    }

    public String getData(String teacherName)
    {
        String tempData = "";

        SharedPreferences s = AppController.getAppContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = s.getString("scheduleInfo", null);
        Type type = new TypeToken<ArrayList<Schedule>>() {}.getType();
        arrayList = gson.fromJson(json, type);

        String temp = teacherName.substring(teacherName.lastIndexOf(".") + 1).trim();


        if(arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {

                if(arrayList.get(i).getTeacher().contains(temp)) {
                    tempData = arrayList.get(i).getTeacher();

                }
            }
        }
        return tempData;
    }


}