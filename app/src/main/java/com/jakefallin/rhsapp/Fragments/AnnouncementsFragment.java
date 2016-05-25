package com.jakefallin.rhsapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import com.jakefallin.rhsapp.MainActivity;
import com.jakefallin.rhsapp.Objects.ExploreBean;
import com.jakefallin.rhsapp.Objects.MenuBean;
import com.jakefallin.rhsapp.R;
import com.jakefallin.rhsapp.Util.AppController;
import com.pacific.adapter.ExpandableAdapter;
import com.pacific.adapter.ExpandableAdapterHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 5/9/2016.
 */
public class AnnouncementsFragment extends Fragment {
    List<MenuBean> list0;
    List<MenuBean> myList;


    private ExpandableListView listView;
    private ExpandableAdapter<MenuBean, ExploreBean> adapter;
    private String urlJsonObj = "http://app.ridgewood.k12.nj.us/rhsstu/api/public/getannouncements.php";
    private static String TAG = MainActivity.class.getSimpleName();

    public AnnouncementsFragment() {

    }

    public static AnnouncementsFragment newInstance() {
        AnnouncementsFragment fragment = new AnnouncementsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        adapter = new ExpandableAdapter<MenuBean, ExploreBean>(getContext(), R.layout.announcements_expanded_base_view, R.layout.announcements_expanded_view) {


            @Override
            protected List<ExploreBean> getChildren(int groupPosition) {
                return get(groupPosition).getExploreBeanList();
            }

            @Override
            protected void convertGroupView(final boolean isExpanded, final ExpandableAdapterHelper helper, MenuBean item) {

                helper.setText(R.id.ann_title, item.getTitle())
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
            protected void convertChildView(boolean isLastChild, final ExpandableAdapterHelper helper, ExploreBean item) {
                helper.setText(R.id.ann_when, item.getWhen())
                .setText(R.id.ann_where, item.getWhere())
                .setText(R.id.ann_more, item.getMore())
                .setText(R.id.ann_date, item.getDate())
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

        View view = inflater.inflate(R.layout.announcements_expanded_list_view, container, false);

        adapter.notifyDataSetChanged();

        listView = (ExpandableListView) view.findViewById(R.id.elv_list);
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
        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {

                    JSONArray announcements = response.getJSONArray("announcements");

                    String title = "", when = "", where = "", more = "", date = "";

                    for (int i = 0; i < announcements.length(); i++) {

                        try {
                            Log.d("!!!!!!", "!!!!!");

                            title = announcements.getJSONObject(i).getString("title");
                            when = announcements.getJSONObject(i).getString("when");
                            where = announcements.getJSONObject(i).getString("where");
                            more = announcements.getJSONObject(i).getString("more");
                            date = announcements.getJSONObject(i).getString("date");

                            MenuBean m = new MenuBean(title);
                            List<ExploreBean> l = new ArrayList<>();
                            l.add(new ExploreBean(when, where, more, date));
                            m.setExploreBeanList(l);

                            list0.add(m);

                            adapter.add(m);
                            adapter.notifyDataSetChanged();

                        }catch (JSONException e) {

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
    }
}


