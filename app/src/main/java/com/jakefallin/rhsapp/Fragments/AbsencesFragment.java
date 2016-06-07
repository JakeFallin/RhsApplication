package com.jakefallin.rhsapp.Fragments;

/**
 * Created by Jake on 3/26/2016.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakefallin.rhsapp.MainActivity;
import com.jakefallin.rhsapp.Objects.AbsenceBean;
import com.jakefallin.rhsapp.Objects.AbsenceInfoBean;
import com.jakefallin.rhsapp.Objects.Schedule;
import com.jakefallin.rhsapp.R;
import com.jakefallin.rhsapp.Util.AppController;
import com.pacific.adapter.ExpandableAdapter;
import com.pacific.adapter.ExpandableAdapterHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AbsencesFragment extends Fragment {
    List<AbsenceBean> list0;
    private boolean called = false;
    private List<AbsenceBean> absenceList = new ArrayList<>();
    private ExpandableListView listView;
    private ExpandableAdapter<AbsenceBean, AbsenceInfoBean> adapter;
    private String urlJsonObj = "http://app.ridgewood.k12.nj.us/rhsstu/api/public/getabsencelist.php";
    private static String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Schedule> arrayList;
    private int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ExpandableAdapter<AbsenceBean, AbsenceInfoBean>(getContext(), R.layout.absence_expanded_base_view, R.layout.absence_child) {

            @Override
            protected List<AbsenceInfoBean> getChildren(int groupPosition) {
                return get(groupPosition).getAbsenceInfoBeanList();
            }

            @Override
            protected void convertGroupView(final boolean isExpanded, final ExpandableAdapterHelper helper, AbsenceBean item) {
                helper.setText(R.id.teach_name, item.getName())
                        .getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isExpanded) {
                            listView.collapseGroup(helper.getGroupPosition());
                        } else {
                            listView.expandGroup(helper.getGroupPosition());
                        }
                    }
                });
                helper.getItemView().setTag("hello world");
            }

            @Override
            protected void convertChildView(boolean isLastChild, final ExpandableAdapterHelper helper, AbsenceInfoBean item) {
                helper.setText(R.id.loc, item.getLocation())
                        .setText(R.id.time, item.getPeriod())
                        .getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickSnack(helper.getGroupPosition(), helper.getChildPosition());
                    }
                });
                helper.getItemView().setTag("hello world");
            }
        };
        list0 = new ArrayList<>();

        SharedPreferences s = AppController.getAppContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        urlJsonObj = s.getString("absenceURL", "http://app.ridgewood.k12.nj.us/api/rhs/absences.php");

        makeJsonObjectRequest();
    }

    public void clickSnack(int g, int c) {
        String str = "click group " + String.valueOf(g) + " child " + String.valueOf(c);
        Snackbar.make(listView, str, Snackbar.LENGTH_SHORT)
                .setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.absence_expanded_list_view, container, false);

        adapter.notifyDataSetChanged();

        listView = (ExpandableListView) view.findViewById(R.id.abs_list);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });


        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                    AbsenceInfoBean Loc;

                    for (int i = 0; i < absencesToday.length(); i++) {
                        try {

                            name = absencesToday.getJSONObject(i).getString("name");
                            List<AbsenceInfoBean> locations = new ArrayList<>();
                            JSONArray infoInAbsence = new JSONArray(absencesToday.getJSONObject(i).getString("info"));
                            AbsenceBean temp = new AbsenceBean(name);

                            for (int j = 0; j < infoInAbsence.length(); j++) {
                                try {
                                    Loc = new AbsenceInfoBean(infoInAbsence.getJSONObject(j).getString("location"), infoInAbsence.getJSONObject(j).getString("reason"));
                                    locations.add(Loc);
                                } catch (JSONException e) {
                                }
                            }
                            temp.setAbsenceInfoBeanList(locations);
                            list0.add(temp);
                            adapter.add(temp);
                            adapter.notifyDataSetChanged();
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

        adapter.notifyDataSetChanged();


        ArrayList<String> aSchedule = new ArrayList<>();

        String p = String.valueOf(count);
        Log.e("TESTY", p);

//        for(int i = 0; i < absenceList.size(); i++)
//        {
//            String s = getData(absenceList.get(i).getName());
//            Log.e("TESTY", s);
//            aSchedule.add(s);
//        }


    }

    private void absenceData() {

        adapter.notifyDataSetChanged();
    }

    public void save() {
        SharedPreferences s = AppController.getAppContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();

        Gson gson = new Gson();
        String json = gson.toJson(absenceList);
        editor.putString("absenceInfo", json);
        editor.apply();
    }

    public String getData(String teacherName) {
        String tempData = "";

        SharedPreferences s = AppController.getAppContext().getSharedPreferences("app", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = s.getString("scheduleInfo", null);
        Type type = new TypeToken<ArrayList<Schedule>>() {
        }.getType();
        arrayList = gson.fromJson(json, type);

        String temp = teacherName.substring(teacherName.lastIndexOf(".") + 1).trim();


        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {

                if (arrayList.get(i).getTeacher().contains(temp)) {
                    tempData = arrayList.get(i).getTeacher();

                }
            }
        }
        return tempData;
    }


}